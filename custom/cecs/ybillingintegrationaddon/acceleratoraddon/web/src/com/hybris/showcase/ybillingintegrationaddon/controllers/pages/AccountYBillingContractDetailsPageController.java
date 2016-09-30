package com.hybris.showcase.ybillingintegrationaddon.controllers.pages;

import com.hybris.showcase.ybillingintegrationaddon.controllers.YbillingintegrationaddonControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hybris.showcase.ybillingintegration.data.YBillingCharacteristicData;
import com.hybris.showcase.ybillingintegration.data.YBillingContractDetailsData;
import com.hybris.showcase.ybillingintegration.services.YBillingOrderService;


/**
 * @author Sebastian Weiner on 2015-11-16.
 */
@Controller
@RequestMapping("/my-account")
public class AccountYBillingContractDetailsPageController extends AbstractAddOnPageController
{

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource(name = "yBillingOrderService")
	private YBillingOrderService yBillingOrderService;

	@Resource(name = "userService")
	private UserService userService;

	private static final String ACCOUNT_CURRENT_YBILLING_CONTRACT_DETAILS_PAGE_ID = "accountYBillingContractDetails";

	@RequestMapping(value = "/ybillingContractDetails", method = RequestMethod.GET)
	public String ybillingContractDetails(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final ContentPageModel page = getContentPageForLabelOrId(ACCOUNT_CURRENT_YBILLING_CONTRACT_DETAILS_PAGE_ID);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);


		final String yBillingCustomerId = getCustomer().getYBillingCustomerId();
		List<YBillingContractDetailsData> yBillingContractDetailsDatas = yBillingOrderService
				.getContractDetails(yBillingCustomerId);

		if (null == yBillingContractDetailsDatas)
		{
			yBillingContractDetailsDatas = getYBillingContractDetailsDataMock();
		}


		final Map<Long, YBillingContractDetailsData> sortedYBillingContractDetailsDatas = new TreeMap<Long, YBillingContractDetailsData>();

		for (final YBillingContractDetailsData data : yBillingContractDetailsDatas)
		{
			if (data.getContractStartTerm() == null)
			{
				data.setContractStartTerm(new Date(0));
			}
			if (data.getContractStartTermTimeZone() == null)
			{
				data.setContractStartTermTimeZone("UTC");
			}
			sortedYBillingContractDetailsDatas.put(
					data.getContractStartTerm().getTime() + TimeZone.getTimeZone(data.getContractStartTermTimeZone()).getRawOffset(),
					data);
		}

		model.addAttribute("showHistory", false);
		model.addAttribute("contractList", sortedYBillingContractDetailsDatas.values());
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.contract.ybilling.details"));

		return getViewForPage(model);
	}

	@RequestMapping(value = "/ybillingContractDetails/{contractId}/{version}", method = RequestMethod.GET)
	public String ybillingContractHistory(@PathVariable("contractId") final String contractId,
			@PathVariable("version") final String version, final Model model, final HttpServletRequest request)
					throws CMSItemNotFoundException
	{

		final List<YBillingContractDetailsData> contractList = new ArrayList<>();
		final List<Map<String, String>> characteristicMapList = new ArrayList<>();
		final Map<String,String> chracteristicMap = new HashMap<>();

		final ContentPageModel page = getContentPageForLabelOrId(ACCOUNT_CURRENT_YBILLING_CONTRACT_DETAILS_PAGE_ID);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);


		final String yBillingCustomerId = getCustomer().getYBillingCustomerId();
		List<YBillingContractDetailsData> yBillingContractDetailsDatas = yBillingOrderService
				.getContractDetails(yBillingCustomerId);

		if (null == yBillingContractDetailsDatas)
		{
			yBillingContractDetailsDatas = getYBillingContractDetailsDataMock();
		}

		for (final YBillingContractDetailsData yBillingContractDetailsData : yBillingContractDetailsDatas)
		{
			if (contractId.equals(yBillingContractDetailsData.getContractNumber()))
			{
				contractList.add(yBillingContractDetailsData);

				final Map<String, String> changesMap = new HashMap<>();
				if (null != yBillingContractDetailsData.getChangesList() && !yBillingContractDetailsData.getChangesList().isEmpty())
				{
					for (final YBillingCharacteristicData yBillingCharacteristicData : yBillingContractDetailsData.getChangesList())
					{
						if(!yBillingCharacteristicData.getCharCode().equals(YbillingintegrationaddonControllerConstants.Views.Pages.VARIANT_CONDITIONS)) {
							changesMap.put(yBillingCharacteristicData.getCharCode(), yBillingCharacteristicData.getProductCode());
							chracteristicMap.put(yBillingCharacteristicData.getCharCode(), yBillingCharacteristicData.getBundleTemplate());
						}
					}
				}

				characteristicMapList.add(changesMap);
			}

		}

		model.addAttribute("showHistory", true);
		model.addAttribute("contractList", contractList);
		model.addAttribute("characteristicMapList", characteristicMapList);
		model.addAttribute("chracteristicMap", chracteristicMap);
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.contract.ybilling.details"));

		return getViewForPage(model);
	}


	private List<YBillingContractDetailsData> getYBillingContractDetailsDataMock()
	{
		final List<YBillingContractDetailsData> mockData = new ArrayList<>();
		final Random rand = new Random();
		final String[] timezones = java.util.TimeZone.getAvailableIDs();

		for (int i = 1; i < 11; i++)
		{
			final YBillingContractDetailsData yBillingContractDetailsData = new YBillingContractDetailsData();

			if ((i % 2) != 0)
			{
				yBillingContractDetailsData.setContractNumber(String.valueOf(i * 123));
				yBillingContractDetailsData.setContractVersion("1");
				yBillingContractDetailsData.setActivationStatus(false);
			}
			else
			{
				yBillingContractDetailsData.setContractNumber(String.valueOf((i - 1) * 123));
				yBillingContractDetailsData.setContractVersion("2");
				yBillingContractDetailsData.setActivationStatus(true);
			}

			yBillingContractDetailsData.setYBillingOrderId(String.valueOf(i * 10));

			yBillingContractDetailsData.setContractEndDate(new Date());
			yBillingContractDetailsData.setContractStartDate(new Date());
			yBillingContractDetailsData.setContractVersionStartDate(new Date());
			yBillingContractDetailsData.setContractVersionEndDate(new Date());
			yBillingContractDetailsData.setOrderDate(new Date());

			yBillingContractDetailsData.setOrderedProductCode("SAMPLE_PRODUCT");
			yBillingContractDetailsData.setOrderStatus("RELEASED");
			yBillingContractDetailsData.setOrderType("Sales Order");
			yBillingContractDetailsData.setProcessType("ISTD");

			final List<YBillingCharacteristicData> changesList = new ArrayList<>();

			final YBillingCharacteristicData yBillingCharacteristicData1 = new YBillingCharacteristicData();
			yBillingCharacteristicData1.setBundleTemplate("FREE_MINUTES_OPTION");
			yBillingCharacteristicData1.setProductCode("FM_N" + i);
			changesList.add(yBillingCharacteristicData1);

			final YBillingCharacteristicData yBillingCharacteristicData2 = new YBillingCharacteristicData();
			yBillingCharacteristicData2.setBundleTemplate("FREE_SMS_OPTION");
			yBillingCharacteristicData2.setProductCode("FS_N" + i);
			changesList.add(yBillingCharacteristicData2);

			final YBillingCharacteristicData yBillingCharacteristicData3 = new YBillingCharacteristicData();
			yBillingCharacteristicData3.setBundleTemplate("NUMBER_OF_FREE_MIN");
			yBillingCharacteristicData3.setProductCode("" + (10 + i));
			changesList.add(yBillingCharacteristicData3);

			final YBillingCharacteristicData yBillingCharacteristicData4 = new YBillingCharacteristicData();
			yBillingCharacteristicData4.setBundleTemplate("NUMBER_OF_FREE_SMS");
			yBillingCharacteristicData4.setProductCode("" + (10 + i));
			changesList.add(yBillingCharacteristicData4);

			yBillingContractDetailsData.setChangesList(changesList);

			yBillingContractDetailsData.setContractStartTerm(addDay(new Date(), rand.nextInt(10)));
			yBillingContractDetailsData.setContractStartTermTimeZone(timezones[rand.nextInt(timezones.length)]);

			mockData.add(yBillingContractDetailsData);
		}
		return mockData;
	}


	private Date addDay(final Date d, final int noDays)
	{
		final Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, noDays);

		return c.getTime();
	}

	private CustomerModel getCustomer()
	{
		final UserModel currentUser = userService.getCurrentUser();
		if (currentUser instanceof CustomerModel)
		{
			return (CustomerModel) currentUser;
		}
		return null;
	}


}
