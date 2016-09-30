/**
 *
 */
package com.seewhy.addon.common;

import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 *         This class holds data relating to an 'order placed' action.
 *
 */
public class SeewhyOrderPlacedData extends SeewhyContextData
{
	/**
	 *
	 * @return
	 */
	public String getTotalValue()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED_TOTALVALUE);
	}

	/**
	 *
	 * @param strTotalValue
	 */
	public void setTotalValue(final String strTotalValue)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED_TOTALVALUE, strTotalValue);
	}


	/**
	 *
	 * @return
	 */
	public String getOrderValue()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED_ORDERVALUE);
	}

	/**
	 *
	 * @param strOrderValue
	 */
	public void setOrderValue(final String strOrderValue)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED_ORDERVALUE, strOrderValue);
	}

	/**
	 *
	 * @return
	 */
	public String getOrderNumber()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED_ORDERNUMBER);
	}

	/**
	 *
	 * @param strOrderNumber
	 */
	public void setOrderNumber(final String strOrderNumber)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED_ORDERNUMBER, strOrderNumber);
	}

	/**
	 *
	 */
	@Override
	public String toString()
	{
		return SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED + ":" + super.toString();
	}
}
