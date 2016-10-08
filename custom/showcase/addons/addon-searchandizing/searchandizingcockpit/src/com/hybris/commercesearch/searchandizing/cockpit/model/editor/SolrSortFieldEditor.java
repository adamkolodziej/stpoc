/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.model.editor;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.impl.DefaultSelectUIEditor;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedPropertyModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.HtmlBasedComponent;

import com.hybris.commercesearch.searchandizing.cockpit.service.SolrPerspectiveAdminService;


/**
 * Shows a list of sort fields based on the selected index. If no index has been selected then based on the selected
 * facet search config, if no config selected then for all facet search configs.
 * 
 * @author rmcotton
 * 
 */
public class SolrSortFieldEditor extends DefaultSelectUIEditor
{

	@Override
	public HtmlBasedComponent createViewComponent(final Object initialValue, final Map<String, ? extends Object> parameters,
			final EditorListener listener)
	{
		setAvailableValues(getSortFieldNames());

		return super.createViewComponent(initialValue, parameters, listener);
	}


	protected List<String> getSortFieldNames()
	{
		if (getAdminService().getActiveIndexedType() != null)
		{
			return getSortedIndexPropertyNames(getAdminService().getActiveIndexedType().getSolrIndexedProperties());
		}
		else if (getAdminService().getActiveFacetSearchConfig() != null)
		{
			final List<SolrIndexedPropertyModel> allIndexProperties = new LinkedList<SolrIndexedPropertyModel>();

			for (final SolrIndexedTypeModel indexedType : getAdminService().getActiveFacetSearchConfig().getSolrIndexedTypes())
			{
				allIndexProperties.addAll(indexedType.getSolrIndexedProperties());
			}

			return getSortedIndexPropertyNames(allIndexProperties);
		}
		else
		{
			final List<SolrIndexedPropertyModel> allIndexProperties = new LinkedList<SolrIndexedPropertyModel>();
			for (final SolrFacetSearchConfigModel config : getAdminService().getAllFacetSearchConfigs())
			{
				for (final SolrIndexedTypeModel indexedType : config.getSolrIndexedTypes())
				{
					allIndexProperties.addAll(indexedType.getSolrIndexedProperties());
				}
			}
			return getSortedIndexPropertyNames(allIndexProperties);
		}
	}

	protected List<String> getSortedIndexPropertyNames(final Collection<SolrIndexedPropertyModel> props)
	{
		final Set<String> fields = new LinkedHashSet<String>();
		for (final SolrIndexedPropertyModel indexedProperty : props)
		{
			fields.add(indexedProperty.getName());
		}

		final List<String> result = new LinkedList<String>(fields);
		Collections.sort(result);
		return result;
	}

	protected SolrPerspectiveAdminService getAdminService()
	{
		return (SolrPerspectiveAdminService) SpringUtil.getBean("solrPerspectiveAdminService", SolrPerspectiveAdminService.class);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.cockpit.model.editor.UIEditor#getEditorType()
	 */
	@Override
	public String getEditorType()
	{
		return PropertyDescriptor.TEXT;
	}

}
