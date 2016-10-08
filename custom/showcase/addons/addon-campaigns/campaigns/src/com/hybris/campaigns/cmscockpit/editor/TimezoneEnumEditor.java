package com.hybris.campaigns.cmscockpit.editor;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.impl.DefaultEnumUIEditor;
import de.hybris.platform.core.Registry;
import de.hybris.platform.enumeration.EnumerationService;

import java.util.Map;

import org.zkoss.zk.ui.HtmlBasedComponent;

import com.hybris.campaigns.enums.Timezone;


public class TimezoneEnumEditor extends DefaultEnumUIEditor
{
	@Override
	public HtmlBasedComponent createViewComponent(final Object initialValue, final Map<String, ?> parameters,
			final EditorListener listener)
	{
		final EnumerationService enumService = (EnumerationService) Registry.getApplicationContext().getBean("enumerationService");

		setAvailableValues(enumService.getEnumerationValues(Timezone._TYPECODE));
		return super.createViewComponent(initialValue, parameters, listener);
	}
}
