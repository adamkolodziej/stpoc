/**
 *
 */
package com.seewhy.addon.common;

import java.util.HashMap;

import org.apache.log4j.Logger;


/**
 * @author Mike Tinnion
 *
 *         A Repository for context (generally session id) variables used by the Seewhy process.
 *
 */
public class SeewhyInMemoryRepository
{
	private static final Logger LOG = Logger.getLogger(SeewhyInMemoryRepository.class);

	private final HashMap allContextsVariables = new HashMap();

	/**
	 *
	 */
	public SeewhyInMemoryRepository()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyInMemoryRepository constructor called.");
		}
	}

	/**
	 * Retrieves the Seewhy context variables for a specified context.
	 *
	 * @param strContext
	 * @return
	 */
	public HashMap getContextVariables(final String strContext)
	{
		HashMap hmContextVariables = null;

		synchronized (allContextsVariables)
		{
			hmContextVariables = (HashMap) allContextsVariables.get(strContext);

			if (hmContextVariables == null)
			{
				hmContextVariables = new HashMap();
				allContextsVariables.put(strContext, hmContextVariables);
			}
		}

		return hmContextVariables;
	}

	/**
	 * Sets the value of a specified variable in a specified context.
	 *
	 * @param strContext
	 * @param strKey
	 * @param objValue
	 */
	public void setVariable(final String strContext, final String strKey, final Object objValue)
	{
		HashMap hmContextVariables = null;
		hmContextVariables = getContextVariables(strContext);

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyInMemoryRepository setVariable called: " + strContext + "/" + strKey + "/" + objValue);
		}

		hmContextVariables.put(strKey, objValue);
	}

	/**
	 * Retrieves the value of a specified variable from a specified context.
	 *
	 * @param strContext
	 * @param strKey
	 * @return
	 */
	public Object getVariable(final String strContext, final String strKey)
	{
		HashMap hmContextVariables = null;
		hmContextVariables = getContextVariables(strContext);

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyInMemoryRepository getVariable called: " + strContext + "/" + strKey + "/"
					+ hmContextVariables.get(strKey));
		}

		return hmContextVariables.get(strKey);
	}

	/**
	 * Removes all variables for a given context.
	 *
	 * @param strContext
	 */
	public void removeVariables(final String strContext)
	{
		synchronized (allContextsVariables)
		{
			allContextsVariables.remove(strContext);

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyInMemoryRepository removeVariables called: " + strContext);
			}
		}
	}
}
