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
 * Generated class for type {@link com.hybris.cms.productpages.jalo.SimpleProductImageComponent SimpleProductImageComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSimpleProductImageComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SimpleProductImageComponent.showGallery</code> attribute **/
	public static final String SHOWGALLERY = "showGallery";
	/** Qualifier of the <code>SimpleProductImageComponent.galleryPosition</code> attribute **/
	public static final String GALLERYPOSITION = "galleryPosition";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SHOWGALLERY, AttributeMode.INITIAL);
		tmp.put(GALLERYPOSITION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductImageComponent.galleryPosition</code> attribute.
	 * @return the galleryPosition
	 */
	public EnumerationValue getGalleryPosition(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, GALLERYPOSITION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductImageComponent.galleryPosition</code> attribute.
	 * @return the galleryPosition
	 */
	public EnumerationValue getGalleryPosition()
	{
		return getGalleryPosition( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductImageComponent.galleryPosition</code> attribute. 
	 * @param value the galleryPosition
	 */
	public void setGalleryPosition(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, GALLERYPOSITION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductImageComponent.galleryPosition</code> attribute. 
	 * @param value the galleryPosition
	 */
	public void setGalleryPosition(final EnumerationValue value)
	{
		setGalleryPosition( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductImageComponent.showGallery</code> attribute.
	 * @return the showGallery
	 */
	public Boolean isShowGallery(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, SHOWGALLERY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductImageComponent.showGallery</code> attribute.
	 * @return the showGallery
	 */
	public Boolean isShowGallery()
	{
		return isShowGallery( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductImageComponent.showGallery</code> attribute. 
	 * @return the showGallery
	 */
	public boolean isShowGalleryAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isShowGallery( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleProductImageComponent.showGallery</code> attribute. 
	 * @return the showGallery
	 */
	public boolean isShowGalleryAsPrimitive()
	{
		return isShowGalleryAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductImageComponent.showGallery</code> attribute. 
	 * @param value the showGallery
	 */
	public void setShowGallery(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, SHOWGALLERY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductImageComponent.showGallery</code> attribute. 
	 * @param value the showGallery
	 */
	public void setShowGallery(final Boolean value)
	{
		setShowGallery( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductImageComponent.showGallery</code> attribute. 
	 * @param value the showGallery
	 */
	public void setShowGallery(final SessionContext ctx, final boolean value)
	{
		setShowGallery( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleProductImageComponent.showGallery</code> attribute. 
	 * @param value the showGallery
	 */
	public void setShowGallery(final boolean value)
	{
		setShowGallery( getSession().getSessionContext(), value );
	}
	
}
