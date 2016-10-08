/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 2015-01-07 08:51:01                         ---
 * ----------------------------------------------------------------
 */
package com.hybris.cms.productpages.jalo;

import com.hybris.cms.productpages.constants.ProductpagepackConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.hybris.cms.productpages.jalo.SimpleProductVariantSelectorComponent SimpleProductVariantSelectorComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSimpleProductVariantSelectorComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SimpleProductVariantSelectorComponent.selectorType</code> attribute **/
	public static final String SELECTORTYPE = "selectorType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SELECTORTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductVariantSelectorComponent.selectorType</code> attribute.
	 * @return the selectorType
	 */
	public EnumerationValue getSelectorType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, SELECTORTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductVariantSelectorComponent.selectorType</code> attribute.
	 * @return the selectorType
	 */
	public EnumerationValue getSelectorType()
	{
		return getSelectorType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductVariantSelectorComponent.selectorType</code> attribute. 
	 * @param value the selectorType
	 */
	public void setSelectorType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, SELECTORTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductVariantSelectorComponent.selectorType</code> attribute. 
	 * @param value the selectorType
	 */
	public void setSelectorType(final EnumerationValue value)
	{
		setSelectorType( getSession().getSessionContext(), value );
	}
	
}
