package com.hybris.showcase.crm.idoc.xml.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hybris.datahub.dto.item.ResultData;
import com.hybris.datahub.validation.ValidationException;
import com.hybris.showcase.crm.idoc.xml.IdocXmlImportFacade;

/**
 * CECS-587 create DataHub endpoint to load CRM iDoc XML
 *
 * @author Sebastian Weiner
 */
public class DefaultIdocXmlImportFacade implements IdocXmlImportFacade {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultIdocXmlImportFacade.class);
	public static final String MONTHLY = "MONTH";
	private static String PRODUCT_ID = null;
	private MessageChannel rawFragmentInputChannel;

	// Task's precondition
	final String DISTR_CHAN_VALUE = "10";

	@Override
	public ResultData importIdocXml(final String feedName, final String itemType, final InputStream xmlInput)
			throws ValidationException {

		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;
		InputStream parsedxmlInput = null;
		try{
			parsedxmlInput = parseInput(xmlInput);
		}catch(IOException ioe){
			throw new RuntimeException(ioe);
		}

		try {
			builder = builderFactory.newDocumentBuilder();

			final Document document = builder.parse(parsedxmlInput);

			final XPath xPath = XPathFactory.newInstance().newXPath();

			final NodeList relevantNodes = getOnlyNodesForDistrChan(xPath, document, DISTR_CHAN_VALUE);

			final List<Map<String, String>> rawProductFragments = createRawProductFragments(xPath, document,
					relevantNodes);

			LOG.info("Importing " + rawProductFragments.size() + " raw fragments of type " + itemType
					+ " into data feed " + feedName);
			rawFragmentInputChannel
					.send(new GenericMessage<Object>(rawProductFragments, constructMessageHeader(itemType, feedName)));

			final List<Map<String, String>> rawLocalizedProductFragments = createRawLocalizedProductFragments(xPath,
					document);

			LOG.info("Importing " + rawLocalizedProductFragments.size() + " raw fragments of type " + itemType
					+ " into data feed " + feedName);
			rawFragmentInputChannel.send(new GenericMessage<Object>(rawLocalizedProductFragments,
					constructMessageHeader(itemType, feedName)));

			final List<Map<String, String>> rawTechAttrFragments = createRawTechAttrFragments(xPath, document);

			String taItemType = "RawTechnicalAttribute";
			LOG.info("Importing " + rawTechAttrFragments.size() + " raw fragments of type " + taItemType
					+ " into data feed " + feedName);
			rawFragmentInputChannel.send(
					new GenericMessage<Object>(rawTechAttrFragments, constructMessageHeader(taItemType, feedName)));

			final ResultData result = new ResultData();
			result.setNumberProcessed(rawProductFragments.size());
			return result;
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	private InputStream parseInput(InputStream in) throws IOException{

		InputStreamReader ir = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(ir);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(os);
		BufferedWriter bw = new BufferedWriter(pw);

		StringBuilder sb = null;
		String line = br.readLine();

        if (LOG.isDebugEnabled())
        {
            StringBuilder before = null;
            BufferedWriter beforebw = new BufferedWriter(pw);
            while (line != null) {
                before = new StringBuilder();
                for(int i = 0; i < line.length(); i++){
                    Character c = line.charAt(i);
                    before.append(c);
                }
                beforebw.write(before.toString());
                line = br.readLine();

                if(line != null)
                    beforebw.write("\n");

            }

			LOG.debug("--------------------------- IDOC XML START ----------------------------------------------");
			LOG.debug(before.toString());
			LOG.debug("--------------------------- IDOC XML END ----------------------------------------------");
		}

		while (line != null) {

			sb = new StringBuilder();
			for(int i = 0; i < line.length(); i++){
				Character c = line.charAt(i);

				if(c == ' '){
					if(i > 0 && (line.charAt(i-1) == '<' || line.charAt(i-1) == '>' || line.charAt(i-1) == '/'))
						continue;
					else if(i+1 < line.length() && (line.charAt(i+1) == '>' || line.charAt(i+1) == '<'))
						continue;
				}

				if(c == '&'){
					if(i + 4 >= line.length()  || (i + 4 < line.length() && (line.charAt(i+1) != 'a' || line.charAt(i+2) != 'm' || line.charAt(i+2) != 'p' || line.charAt(i+2) != ';')))
						sb.append("&amp;");
					continue;
				}

				sb.append(c);
			}

			bw.write(sb.toString());
			line = br.readLine();

			if(line != null)
				bw.write("\n");
		}

		System.out.println("--------------------------- IDOC XML START fixed ----------------------------------------------");
		System.out.println(sb.toString());
		System.out.println("--------------------------- IDOC XML END ----------------------------------------------");


		br.close();
		ir.close();

		bw.close();
		pw.close();

		os.close();

		return new ByteArrayInputStream(os.toByteArray());
	}

	private Map<String, Object> constructMessageHeader(final String itemType, final String feedName) {
		final Map<String, Object> header = new HashMap<>();
		header.put("itemType", itemType);
		header.put("feedName", feedName);
		return header;
	}

	private List<Map<String, String>> createRawProductFragments(final XPath xPath, final Document document,
																final NodeList relevantNodes) throws XPathExpressionException {
		final List<Map<String, String>> productList = new ArrayList<Map<String, String>>();
		final Map<String, String> productValueMap = new HashMap<String, String>();

		final String expression = "//IDOC/E101COMXIF_PRODUCT_MATERIAL/PRODUCT_ID";
		// System.out.println(expression);
		putSingleListedValues(xPath, document, expression, productValueMap);

		PRODUCT_ID = productValueMap.get("PRODUCT_ID");

		final String expression1 = "//IDOC/E101COMXIF_PRODUCT_MATERIAL/CONFIG";
		// System.out.println(expression1);
		putSingleListedValues(xPath, document, expression1, productValueMap);

		final String expression2 = "//IDOC/E101COMXIF_PRODUCT_MATERIAL/E101RMXIF_PR_MATERIAL_SALES[1]/SALES_ORG";
		// System.out.println(expression2);
		putSingleValues(xPath, document, expression2, productValueMap);

		// final String expression3 =
		// "//IDOC/E101COMXIF_PRODUCT_MATERIAL/E101RMXIF_PR_MATERIAL_SALES[1]/DISTR_CHAN";
		// System.out.println(expression3);
		// putSingleValues(xPath, document, expression3, ValueMap);
		// System.out.println("DISTR_CHAN : " + DISTR_CHAN_VALUE);
		productValueMap.put("DISTR_CHAN", DISTR_CHAN_VALUE);

		getUnitsNumerators(xPath, document, productValueMap);

		getCRMT_CTR_DURs(relevantNodes, xPath, productValueMap);

		productList.add(productValueMap);

		return productList;
	}

	private List<Map<String, String>> createRawLocalizedProductFragments(final XPath xPath, final Document document)
			throws XPathExpressionException {
		return getLocalizedProductNames(xPath, document);
	}

	private List<Map<String, String>> createRawTechAttrFragments(final XPath xPath, final Document document)
			throws XPathExpressionException {
		final List<Map<String, String>> techAttrList = new ArrayList<Map<String, String>>();

		try {
			getTechnicalAttributes(xPath, document, techAttrList);
		} catch (IllegalArgumentException e){
			LOG.error(e.getMessage(), e);
		}


		return techAttrList;
	}

	private void getTechnicalAttributes(final XPath xPath, final Document document,
										final List<Map<String, String>> techAttrList) throws XPathExpressionException, IllegalArgumentException {
		String TECRES_GROUP_VALUE = "CRM_ISX_TECRES";

		final String expression = "/CRMXIF_PRODUCT_MATERIAL_SAVE02/IDOC/E101COMXIF_PRODUCT_MATERIAL/E101COMXIF_PR_S_PRODUCT_SET[normalize-space(SETTYP_ID)='"
				+ TECRES_GROUP_VALUE + "']";

		InnerTechnicalAttribute attr = null;

		try {
			NodeList resultList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			if(resultList.getLength() != 1){
				throw new IllegalArgumentException("FATAL - False number of Technical Attribute found through IDOC parsing. Result list size: " + resultList.getLength());
			}

			// The has to be only one such a node
			NodeList nodeList = resultList.item(0).getChildNodes();

			List<InnerTechnicalAttribute> attrs = new ArrayList<>();

			String versionAttrHeader = "CRM_ISX_VERSION";
			String guidAttrHeader = "CRM_ISX_ASSIGN_GUID";
			String dataIdAttrHeader = "CRM_ISX_TECH_DATA_ID";
			String dataDescAttrHeader = "CRM_ISX_TECH_DATA_DS";
			String dataTypeAttrHeader = "CRM_ISX_RESOURCE_TYP";

			for (int j = 0; null != nodeList && j < nodeList.getLength(); j++) {
				NodeList internalNodeList = nodeList.item(j).getChildNodes();

				String key = null;
				String value = null;
				for (int k = 0; null != internalNodeList && k < internalNodeList.getLength(); k++) {
					final Node internalNod = internalNodeList.item(k);
					if ("ATTR_ID".equals(internalNod.getNodeName())) {
						// System.out.println("KEY : " +
						// internalNod.getFirstChild().getNodeValue());
						key = internalNod.getFirstChild().getNodeValue().trim();
					} else if ("VALUE".equals(internalNod.getNodeName())) {
						// System.out.println("VALUE : " +
						// internalNod.getFirstChild().getNodeValue());
						value = internalNod.getFirstChild().getNodeValue().trim();
					}
				}

				if (null != value) {
					if (versionAttrHeader.equals(key)) {
						attr = new InnerTechnicalAttribute();
						attr.setVersion(value);
					} else if (guidAttrHeader.equals(key)) {
						attr.setGuid(value);
					} else if (dataIdAttrHeader.equals(key)) {
						attr.setId(value);
					} else if (dataDescAttrHeader.equals(key)) {
						attr.setDesc(value);
					} else {
						// CRM_ISX_RESOURCE_TYP
						attr.setType(value);

						for (InnerTechnicalAttribute innerTechnicalAttribute : attrs) {
							if (innerTechnicalAttribute.getId().equals(value)) {
								attrs.remove(innerTechnicalAttribute);
								break;
							}
						}

						attrs.add(attr);
					}
				}
			}

			System.out.println("PRODUCT_ID| : |" + versionAttrHeader + "| : |"
					+ guidAttrHeader + "| : |" + dataIdAttrHeader + "| : |"
					+ dataDescAttrHeader + "| : |" + dataTypeAttrHeader + "|");

			for (InnerTechnicalAttribute innerTechnicalAttribute : attrs) {

				Map<String, String> TECH_ATTR_Map = new HashMap<>();
				TECH_ATTR_Map.put("PRODUCT_ID", PRODUCT_ID.trim());
				TECH_ATTR_Map.put("VERSION", innerTechnicalAttribute.getVersion());
				TECH_ATTR_Map.put("GUID", innerTechnicalAttribute.getGuid());
				TECH_ATTR_Map.put("DATA_ID", innerTechnicalAttribute.getId());
				TECH_ATTR_Map.put("DATA_DESC", innerTechnicalAttribute.getDesc());
				TECH_ATTR_Map.put("DATA_TYPE", innerTechnicalAttribute.getType());
				System.out.println(PRODUCT_ID.trim() + "| : |" + innerTechnicalAttribute.getVersion() + "| : |"
						+ innerTechnicalAttribute.getGuid() + "| : |" + innerTechnicalAttribute.getId() + "| : |"
						+ innerTechnicalAttribute.getDesc() + "| : |" + innerTechnicalAttribute.getType() + "|");

				techAttrList.add(TECH_ATTR_Map);
			}

		} catch (final XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	private NodeList getOnlyNodesForDistrChan(final XPath xPath, final Document document, final String DISTR_CHAN_VALUE)
			throws XPathExpressionException {
		final String expression = "//E101RMXIF_PR_MATERIAL_SALES[normalize-space(DISTR_CHAN)='" + DISTR_CHAN_VALUE
				+ "']";
		// System.out.println(expression);

		final NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
		// System.out.println(nodeList.getLength());
		return nodeList;

	}

	private void getUnitsNumerators(final XPath xPath, final Document document, Map<String, String> ValueMap)
			throws XPathExpressionException {
		final String expression = "//E101MXIF_PRD_S_COMM_PR_UNIT";
		// System.out.println(expression1);

		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
		for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
			final Node nod = nodeList.item(i);
			NodeList internalNodeList = nod.getChildNodes();
			boolean relevant = false;

			for (int j = 0; null != internalNodeList && j < internalNodeList.getLength(); j++) {
				final Node internalNod = internalNodeList.item(j);
				if ("UNIT_ISO".equals(internalNod.getNodeName())) {
					// System.out.println(j + " : " + internalNod.getNodeName()
					// + " : "
					// + internalNod.getFirstChild().getNodeValue());
					ValueMap.put(internalNod.getNodeName().trim(), internalNod.getFirstChild().getNodeValue().trim());
					relevant = true;
				} else if (relevant && "NUMERATOR".equals(internalNod.getNodeName())) {
					// System.out.println(j + " : " + internalNod.getNodeName()
					// + " : "
					// + internalNod.getFirstChild().getNodeValue());
					ValueMap.put(internalNod.getNodeName().trim(), internalNod.getFirstChild().getNodeValue().trim());
				}
			}
		}
	}

	private void getCRMT_CTR_DURs(final NodeList relevantNodes, final XPath xPath, Map<String, String> ValueMap)
			throws XPathExpressionException {

		final String expression = "//E102XIF_PR_PRODUCT_SET_CUST";
		// System.out.println(expression1);

		final String VALUE_1 = "CRMT_CTR_DUR";
		final String VALUE_2 = "CRMT_CTR_PERIOD_UNIT";

		for (int i = 0; null != relevantNodes && i < relevantNodes.getLength(); i++) {
			final Node nod = relevantNodes.item(i);

			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(nod, XPathConstants.NODESET);

			for (int j = 0; null != nodeList && j < nodeList.getLength(); j++) {
				final NodeList internalNodeList = nodeList.item(j).getChildNodes();

				boolean ATTR_ID_VALUE_Relevant = false;
				boolean CRMT_CTR_PERIOD_UNIT_Relevant = false;

				for (int k = 0; null != internalNodeList && k < internalNodeList.getLength(); k++) {
					final Node internalNod = internalNodeList.item(k);

					if ("ATTR_ID".equals(internalNod.getNodeName())) {
						if (VALUE_1.equals(internalNod.getFirstChild().getNodeValue().trim())) {
							ATTR_ID_VALUE_Relevant = true;
						} else if (VALUE_2.equals(internalNod.getFirstChild().getNodeValue().trim())) {
							CRMT_CTR_PERIOD_UNIT_Relevant = true;
						}
					} else if ("VALUE".equals(internalNod.getNodeName())) {
						if (ATTR_ID_VALUE_Relevant) {
							// System.out.println(VALUE_1 + " : " +
							// internalNod.getFirstChild().getNodeValue());
							ValueMap.put(VALUE_1, internalNod.getFirstChild().getNodeValue());
						} else if (CRMT_CTR_PERIOD_UNIT_Relevant) {
							// System.out.println(VALUE_2 + " : " +
							// internalNod.getFirstChild().getNodeValue());
							String nodeValue = internalNod.getFirstChild().getNodeValue();
							//System.out.println(nodeValue+" : ");
							if("2".equals(nodeValue.trim())){
								ValueMap.put(VALUE_2, MONTHLY);
								//System.out.println(VALUE_2+" : MONTHLY");
							} else {
								ValueMap.put(VALUE_2, "UNKNOWN");
								//System.out.println(VALUE_2+" : UNKNOWN");
							}
						}

					}
				}
			}
		}

	}

	private List<Map<String, String>> getLocalizedProductNames(final XPath xPath, final Document document)
			throws XPathExpressionException {

		List<Map<String, String>> localizedProductList = new ArrayList<Map<String, String>>();
		Map<String, String> localizedValueMap = new HashMap<>();

		final String expression = "//E101COMXIF_PRD_S_SHORT_TEXT";
		// System.out.println(expression1);

		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
		for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
			final Node nod = nodeList.item(i);
			NodeList internalNodeList = nod.getChildNodes();
			String language = null;
			String name = null;
			for (int j = 0; null != internalNodeList && j < internalNodeList.getLength(); j++) {
				final Node internalNod = internalNodeList.item(j);
				if ("LANGUAGE_ISO".equals(internalNod.getNodeName())) {
					language = internalNod.getFirstChild().getNodeValue();
				} else if ("SHORT_TEXT".equals(internalNod.getNodeName())) {
					name = internalNod.getFirstChild().getNodeValue();
				}
			}

			localizedValueMap.put("PRODUCT_ID", PRODUCT_ID);
			localizedValueMap.put("isoCode", language.trim());
			localizedValueMap.put("name", name.trim());

			localizedProductList.add(localizedValueMap);
			localizedValueMap = new HashMap<>();
		}
		return localizedProductList;
	}

	public void putSingleListedValues(final XPath xPath, final Document document, final String expression,
									  final Map<String, String> productValueMap) throws XPathExpressionException {
		final Node node = (Node) xPath.compile(expression).evaluate(document, XPathConstants.NODE);

		if (null != node) {
			final NodeList nodeList = node.getChildNodes();
			for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
				final Node nod = nodeList.item(i);
				// System.out.println(node.getNodeName() + " : " +
				// nod.getNodeValue());
				productValueMap.put(node.getNodeName().trim(), nod.getNodeValue().trim());
			}
		}

	}

	public void putSingleValues(final XPath xPath, final Document document, final String expression,
								final Map<String, String> productValueMap) throws XPathExpressionException {
		final Node node = (Node) xPath.compile(expression).evaluate(document, XPathConstants.NODE);

		if (null != node) {
			final NodeList nodeList = node.getChildNodes();
			final Node nod = nodeList.item(0);
			// System.out.println(node.getNodeName() + " : " +
			// nod.getNodeValue());
			productValueMap.put(node.getNodeName().trim(), nod.getNodeValue().trim());
		}

	}

	private class InnerTechnicalAttribute {

		public InnerTechnicalAttribute() {
			super();
		}

		String version;
		String id;
		String guid;
		String desc;
		String type;

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}
	}

	public MessageChannel getRawFragmentInputChannel() {
		return rawFragmentInputChannel;
	}

	@Required
	public void setRawFragmentInputChannel(MessageChannel rawFragmentInputChannel) {
		this.rawFragmentInputChannel = rawFragmentInputChannel;
	}
}