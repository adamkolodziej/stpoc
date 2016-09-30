/**
 *
 */
package com.seewhy.addon.common;

import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 *         This class holds data relating to a 'product browsed' action.
 *
 */
public class SeewhyProductBrowsedData extends SeewhyContextData
{
	/**
	 *
	 */
	public SeewhyProductBrowsedData()
	{
		super();
		this.setBrowseType("" + SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_NONE);
	}

	/**
	 * @return the Code
	 */
	public String getCode()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_PRODUCTCODE);
	}

	/**
	 * @param strCode
	 *           the strCode to set
	 */
	public void setCode(final String strCode)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_PRODUCTCODE, strCode);
	}

	/**
	 * @return the BrowseType
	 */
	public String getBrowseType()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_BROWSETYPE);
	}

	/**
	 * @param BrowseType
	 *           the iBrowseType to set
	 */
	public void setBrowseType(final String strBrowseType)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_BROWSETYPE, strBrowseType);
	}

	/**
	 *
	 */
	@Override
	public String toString()
	{
		return SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED + ":" + super.toString();
	}
}
