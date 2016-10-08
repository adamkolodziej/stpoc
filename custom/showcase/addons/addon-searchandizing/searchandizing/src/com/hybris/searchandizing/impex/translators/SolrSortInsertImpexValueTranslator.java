/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.searchandizing.impex.translators;

import de.hybris.platform.commerceservices.jalo.solrsearch.config.SolrSort;
import de.hybris.platform.impex.jalo.header.StandardColumnDescriptor;
import de.hybris.platform.impex.jalo.translators.CollectionValueTranslator;
import de.hybris.platform.impex.jalo.translators.SingleValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SearchResult;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearch;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.TypeManager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;



/**
 * @author rmcotton
 * 
 */
public class SolrSortInsertImpexValueTranslator extends CollectionValueTranslator
{

	private int elementCounter = 0;
	protected String after;


	/**
	 * @param targetType
	 * @param elementTranslator
	 */
	public SolrSortInsertImpexValueTranslator()
	{
		super((CollectionType) TypeManager.getInstance().getType("SolrIndexedType2SolrSortRelsortsColl"), new SolrSortTranslator());
	}



	public static class SolrSortTranslator extends SingleValueTranslator
	{
		@Override
		protected Object convertToJalo(final String expr, final Item forItem)
		{
			if (forItem == null)
			{
				setError();
				return null;
			}
			@SuppressWarnings("deprecation")
			final FlexibleSearch flexis = FlexibleSearch.getInstance();
			@SuppressWarnings("deprecation")
			final TypeManager typedManager = TypeManager.getInstance();
			final SessionContext ctx = JaloSession.getCurrentSession().getSessionContext();

			final String query = "SELECT {" + Item.PK + "} " + "FROM {" + typedManager.getComposedType(SolrSort.class).getCode()
					+ "} " + "WHERE {code}  = ?value and {indexedType} = ?indexedType ";


			final Map<String, Object> values = new HashMap<String, Object>();
			values.put("value", expr);
			values.put("indexedType", forItem);

			final SearchResult result = flexis.search(ctx, query, values, Collections.singletonList(SolrSort.class), true, true, 0,
					-1);

			if (result.getCount() > 0)
			{
				final SolrSort type = (SolrSort) result.getResult().get(0);
				return type;
			}
			else
			{

				setError();
				return null;
			}
		}

		@Override
		protected String convertToString(final Object value)
		{
			throw new UnsupportedOperationException("Export isn't supported, yet!");
		}
	}




	@Override
	public void init(final StandardColumnDescriptor descriptor)
	{
		super.init(descriptor);

		this.after = descriptor.getDescriptorData().getModifier("after");
	}

	@Override
	protected boolean processItem(final String token, final Item forItem, final Collection col, final boolean append,
			final boolean allowNull)
	{
		if (!(col instanceof List))
		{
			return super.processItem(token, forItem, col, append, allowNull);
		}

		final List list = (List) col;

		final Object element = getElementTranslator().importValue(isEmpty(token) ? null : token, forItem);

		if (getElementTranslator().wasUnresolved())
		{
			setError();
			return false;
		}
		if (element == null && !allowNull)
		{
			return false;
		}

		if (append)
		{
			final int indexPos = resolveInsertIndexPos(list);
			if (indexPos == -1 || indexPos >= list.size())
			{
				list.add(element);
			}
			else
			{
				list.add(indexPos, element);
			}
		}
		else
		{
			list.remove(element);
		}

		return true;
	}

	protected int resolveInsertIndexPos(final List col)
	{

		if (after != null)
		{
			final int indexPos = getSortIndexByCode(after, col);
			if (indexPos != -1)
			{
				return indexPos + ++elementCounter;
			}
		}
		return -1;

	}

	protected int getSortIndexByCode(final String code, final List col)
	{
		for (final ListIterator li = col.listIterator(); li.hasNext();)
		{
			final Object next = li.next();
			if (next instanceof SolrSort)
			{
				if (code.equals(((SolrSort) next).getCode()))
				{
					return li.previousIndex();
				}
			}
		}
		return -1;
	}
}
