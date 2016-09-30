/**
 * 
 */
package com.hybris.showcase.cecs.ymarketingintegration;

import com.sap.wec.adtreco.bo.impl.SAPInitiative;


/**
 * @author n.pavlovic
 * 
 */
public class ServicesSAPInitiative extends SAPInitiative
{
	protected String categoryId;

	public String getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(final String categoryId)
	{
		this.categoryId = categoryId;
	}
}
