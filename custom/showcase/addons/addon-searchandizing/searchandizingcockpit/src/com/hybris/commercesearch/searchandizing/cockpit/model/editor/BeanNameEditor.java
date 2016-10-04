package com.hybris.commercesearch.searchandizing.cockpit.model.editor;


import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.impl.DefaultSelectUIEditor;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.core.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.HtmlBasedComponent;


/**
 * Used for properties that are bean names. This editor resolves all beans in the application context that belong to the
 * specified beanClass parameter and set these as the available values. Values can be sorted with a configured parameter
 * otherwise they are lexicographically sorted.
 * 
 * @author rmcotton
 * 
 */
public class BeanNameEditor extends DefaultSelectUIEditor
{
	private static final Logger LOG = Logger.getLogger(BeanNameEditor.class);

	private final String BEAN_CLASS_PARAM = "beanClass";
	private final String COMPARATOR_CLASS_PARAM = "comparatorClass";

	@Override
	public HtmlBasedComponent createViewComponent(final Object initialValue, final Map<String, ? extends Object> parameters,
			final EditorListener listener)
	{
		final Class clazz = resolveBeanClass(parameters);

		if (clazz != null)
		{
			final List<String> beanNames = Arrays.asList(Registry.getGlobalApplicationContext().getBeanNamesForType(clazz));
			setAvailableValues(sortValues(beanNames, initialValue, parameters, listener));
		}
		else
		{
			LOG.error("BeanNameEditor missing beanClass parameter");
		}

		return super.createViewComponent(initialValue, parameters, listener);
	}

	protected List<String> sortValues(final List<String> valuesToSort, final Object initialValue,
			final Map<String, ? extends Object> parameters, final EditorListener listener)
	{

		final Comparator<String> comparator = resolveComparator(parameters);
		final List<String> sortedValues = new ArrayList<String>(valuesToSort);
		Collections.sort(sortedValues, comparator);
		sortedValues.add(0, StringUtils.EMPTY);
		return sortedValues;
	}

	protected Comparator<String> resolveComparator(final Map<String, ? extends Object> parameters)
	{
		final String comparatorClass = (String) parameters.get(COMPARATOR_CLASS_PARAM);
		if (StringUtils.isNotBlank(comparatorClass))
		{
			try
			{
				final Class<Comparator<String>> clazz = (Class<Comparator<String>>) Class.forName(comparatorClass);
				return clazz.newInstance();
			}
			catch (final ClassNotFoundException cnfe)
			{
				LOG.error("cannot resolve BeanNameEditor comparatorClass [" + comparatorClass + "]");
			}
			catch (final InstantiationException ie)
			{
				LOG.error("cannot instantiate BeanNameEditor comparatorClass [" + comparatorClass + "]", ie);
			}
			catch (final IllegalAccessException iae)
			{
				LOG.error("cannot instantiate BeanNameEditor comparatorClass [" + comparatorClass + "]", iae);

			}
		}
		return null;
	}

	protected Class resolveBeanClass(final Map<String, ? extends Object> parameters)
	{
		final String beanClass = (String) parameters.get(BEAN_CLASS_PARAM);
		if (StringUtils.isNotBlank(beanClass))
		{
			try
			{
				return Class.forName(beanClass);
			}
			catch (final ClassNotFoundException cnfe)
			{
				LOG.error("cannot resolve BeanNameEditor beanClass [" + beanClass + "]");
			}
		}
		return null;
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
