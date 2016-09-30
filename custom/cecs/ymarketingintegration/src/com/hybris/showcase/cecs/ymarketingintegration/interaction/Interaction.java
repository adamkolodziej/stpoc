/**
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.interaction;

/**
 * @author marius.bogdan.ionescu@sap.com
 */
public class Interaction
{
	private String uid;
	private String initiativeId;
	private String sourceDataURL;
	private String sourceObjectId;
	private String IAType;
	private String commMedium;
	private String idOrigin;
	private String contentTitle;
	private String contentData;
	private Integer valuation;
	private String itemOfInterest;

	public String getUid()
	{
		return uid;
	}

	public void setUid(final String uid)
	{
		this.uid = uid;
	}

	public String getInitiativeId()
	{
		return initiativeId;
	}

	public void setInitiativeId(final String initiativeId)
	{
		this.initiativeId = initiativeId;
	}

	public String getSourceDataURL()
	{
		return sourceDataURL;
	}

	public void setSourceDataURL(final String sourceDataURL)
	{
		this.sourceDataURL = sourceDataURL;
	}

	public String getIAType()
	{
		return IAType;
	}

	public void setIAType(final String IAType)
	{
		this.IAType = IAType;
	}

	public String getCommMedium()
	{
		return commMedium;
	}

	public void setCommMedium(final String commMedium)
	{
		this.commMedium = commMedium;
	}

	public String getIdOrigin()
	{
		return idOrigin;
	}

	public void setIdOrigin(final String idOrigin)
	{
		this.idOrigin = idOrigin;
	}

	public String getSourceObjectId()
	{
		return sourceObjectId;
	}

	public void setSourceObjectId(String sourceObjectId)
	{
		this.sourceObjectId = sourceObjectId;
	}

	public String getContentTitle()
	{
		return contentTitle;
	}

	public void setContentTitle(String contentTitle)
	{
		this.contentTitle = contentTitle;
	}

	public String getContentData()
	{
		return contentData;
	}

	public void setContentData(String contentData)
	{
		this.contentData = contentData;
	}

	public Integer getValuation()
	{
		return valuation;
	}

	public void setValuation(Integer valuation)
	{
		this.valuation = valuation;
	}

	public String getItemOfInterest()
	{
		return itemOfInterest;
	}

	public void setItemOfInterest(String itemOfInterest)
	{
		this.itemOfInterest = itemOfInterest;
	}
}
