/**
 *
 */
package com.seewhy.addon.common;

import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 *         This class holds general data relating to Seewhy actions.
 *
 */
public class SeewhyGeneralData extends SeewhyContextData
{
	/**
	 *
	 * @return
	 */
	public String getServerContext()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_GENERAL_SERVERCONTEXT);
	}

	/**
	 *
	 * @param strPageType
	 */
	public void setServerContext(final String strServerContext)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERAL_SERVERCONTEXT, strServerContext);
	}

	/**
	 *
	 * @return
	 */
	public String getPageType()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_GENERAL_PAGETYPE);
	}

	/**
	 *
	 * @param strPageType
	 */
	public void setPageType(final String strPageType)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERAL_PAGETYPE, strPageType);
	}


	/**
	 *
	 */
	@Override
	public String toString()
	{
		return SeewhyConstants.SEEWHY_ACTION_GENERAL + ":" + super.toString();
	}
}
