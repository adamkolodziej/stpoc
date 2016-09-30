/**
 *
 */
package com.seewhy.addon.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author Mike Tinnion
 *
 *         This abstract class is the base class for all classes holding Seewhy 'action' data.
 *
 */
public abstract class SeewhyContextData
{
	private final HashMap<String, String> m_Data = new HashMap();

	/**
	 *
	 * @param strKey
	 * @param strValue
	 */
	public void setDataItem(final String strKey, String strValue)
	{
		if (strKey != null)
		{
			if (strValue == null)
			{
				strValue = "";
			}

			m_Data.put(strKey, strValue);
		}
	}

	/**
	 *
	 * @param strKey
	 * @return
	 */
	public String getDataItem(final String strKey)
	{
		String strValue = null;

		if (strKey != null)
		{
			strValue = m_Data.get(strKey);
		}

		return strValue;
	}

	/**
	 *
	 * @return
	 */
	public HashMap getDataItems()
	{
		return m_Data;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString()
	{
		final StringBuffer sbSeewhyContextData = new StringBuffer("");
		Iterator itr = null;
		Map.Entry entry = null;
		boolean blnFirst = true;

		//Iterate through the stored data and add the key/value to the string buffer.
		synchronized (m_Data)
		{
			itr = m_Data.entrySet().iterator();
			while (itr.hasNext() == true)
			{
				entry = (Map.Entry) itr.next();

				if (blnFirst == false)
				{
					sbSeewhyContextData.append(",");
				}

				sbSeewhyContextData.append(entry.getKey());
				sbSeewhyContextData.append("=");
				sbSeewhyContextData.append(entry.getValue());

				blnFirst = false;
			}
		}

		return sbSeewhyContextData.toString();
	}
}
