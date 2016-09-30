/**
 *
 */
package com.seewhy.addon.common;

import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 *         This class holds data relating to an 'add to cart' action.
 *
 */
public class SeewhyAddToCartData extends SeewhyContextData
{
	/**
	 * @return the ReturnToLink
	 */
	public String getReturnToLink()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_RETURNTOLINK);
	}

	/**
	 * @param strReturnToLink
	 */
	public void setReturnToLink(final String strReturnToLink)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_RETURNTOLINK, strReturnToLink);
	}

	/**
	 * @return the Code
	 */
	public String getCode()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_PRODUCTCODE);
	}

	/**
	 * @param strCode
	 */
	public void setCode(final String strCode)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_PRODUCTCODE, strCode);
	}

	/**
	 * @return the Code
	 */
	public String getQuantity()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_PRODUCTQTY);
	}

	/**
	 * @param strQuantity
	 */
	public void setQuantity(final String strQuantity)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_PRODUCTQTY, strQuantity);
	}

	/**
	 *
	 * @return
	 */
	public String getCartValue()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_CARTVALUE);
	}

	/**
	 *
	 * @param strCartValue
	 */
	public void setCartValue(final String strCartValue)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART_CARTVALUE, strCartValue);
	}


	/**
	 *
	 */
	@Override
	public String toString()
	{
		return SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART + ":" + super.toString();
	}
}
