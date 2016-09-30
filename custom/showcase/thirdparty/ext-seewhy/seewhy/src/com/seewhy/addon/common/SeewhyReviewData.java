/**
 *
 */
package com.seewhy.addon.common;

import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 *         This class holds data relating to a 'product reviewed' action.
 *
 */
public class SeewhyReviewData extends SeewhyContextData
{
	/**
	 * @return the Rating
	 */
	public String getRating()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_REVIEWED_RATING);
	}

	/**
	 * @param strRating
	 *           the Rating to set
	 */
	public void setRating(final String strRating)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_REVIEWED_RATING, strRating);
	}

	/**
	 * @return the Code
	 */
	public String getCode()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_REVIEWED_PRODUCTCODE);
	}

	/**
	 * @param strCode
	 *           the strCode to set
	 */
	public void setCode(final String strCode)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_PRODUCT_REVIEWED_PRODUCTCODE, strCode);
	}


	/**
	 *
	 */
	@Override
	public String toString()
	{
		return SeewhyConstants.SEEWHY_ACTION_PRODUCT_REVIEWED + ":" + super.toString();
	}
}
