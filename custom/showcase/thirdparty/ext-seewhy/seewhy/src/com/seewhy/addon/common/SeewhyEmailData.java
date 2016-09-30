/**
 *
 */
package com.seewhy.addon.common;

import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 *         This class holds data relating to an 'Email Captured' action/event.
 *
 */
public class SeewhyEmailData extends SeewhyContextData
{
	/**
	 * @return the EmailAddress
	 */
	public String getEmailAddress()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED_ADDRESS);
	}

	/**
	 * @param strEmailAddress
	 *           the Email Address to set
	 */
	public void setEmailAddress(final String strEmailAddress)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED_ADDRESS, strEmailAddress);
	}

	/**
	 * @return the first name
	 */
	public String getFirstName()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED_FIRSTNAME);
	}

	/**
	 * @param strFirstName
	 *
	 */
	public void setFirstName(final String strFirstName)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED_FIRSTNAME, strFirstName);
	}

	/**
	 * @return the last name
	 */
	public String getLastName()
	{
		return super.getDataItem(SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED_LASTNAME);
	}

	/**
	 * @param strLastName
	 *
	 */
	public void setLastName(final String strLastName)
	{
		super.setDataItem(SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED_LASTNAME, strLastName);
	}

	/**
	 *
	 */
	@Override
	public String toString()
	{
		return SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED + ":" + super.toString();
	}
}
