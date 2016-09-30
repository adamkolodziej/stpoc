package com.hybris.platform.wec.btg;

import java.util.Collection;
import java.util.HashSet;


/**
 *
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class SAPCategorySet extends HashSet<String>
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public SAPCategorySet()
	{
		super();
	}

	/**
	 * @param col
	 */
	public SAPCategorySet(final Collection<? extends String> col)
	{
		super(col);
	}

	/**
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public SAPCategorySet(final int initialCapacity, final float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}

	/**
	 * @param initialCapacity
	 */
	public SAPCategorySet(final int initialCapacity)
	{
		super(initialCapacity);
	}

}
