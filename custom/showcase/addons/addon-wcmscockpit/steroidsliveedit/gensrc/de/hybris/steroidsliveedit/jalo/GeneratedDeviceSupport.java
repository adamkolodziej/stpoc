/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 2015-01-07 08:51:01                         ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *  
 */
package de.hybris.steroidsliveedit.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.steroidsliveedit.constants.SteroidsliveeditConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.steroidsliveedit.jalo.DeviceSupport DeviceSupport}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDeviceSupport extends GenericItem
{
	/** Qualifier of the <code>DeviceSupport.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>DeviceSupport.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>DeviceSupport.width</code> attribute **/
	public static final String WIDTH = "width";
	/** Qualifier of the <code>DeviceSupport.height</code> attribute **/
	public static final String HEIGHT = "height";
	/** Qualifier of the <code>DeviceSupport.Orientation</code> attribute **/
	public static final String ORIENTATION = "Orientation";
	/** Qualifier of the <code>DeviceSupport.supportedUiExperience</code> attribute **/
	public static final String SUPPORTEDUIEXPERIENCE = "supportedUiExperience";
	/** Qualifier of the <code>DeviceSupport.default</code> attribute **/
	public static final String DEFAULT = "default";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(WIDTH, AttributeMode.INITIAL);
		tmp.put(HEIGHT, AttributeMode.INITIAL);
		tmp.put(ORIENTATION, AttributeMode.INITIAL);
		tmp.put(SUPPORTEDUIEXPERIENCE, AttributeMode.INITIAL);
		tmp.put(DEFAULT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.default</code> attribute.
	 * @return the default - Defines if the device is optional for current ui experience
	 */
	public Boolean isDefault(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, DEFAULT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.default</code> attribute.
	 * @return the default - Defines if the device is optional for current ui experience
	 */
	public Boolean isDefault()
	{
		return isDefault( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.default</code> attribute. 
	 * @return the default - Defines if the device is optional for current ui experience
	 */
	public boolean isDefaultAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isDefault( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.default</code> attribute. 
	 * @return the default - Defines if the device is optional for current ui experience
	 */
	public boolean isDefaultAsPrimitive()
	{
		return isDefaultAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.default</code> attribute. 
	 * @param value the default - Defines if the device is optional for current ui experience
	 */
	public void setDefault(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, DEFAULT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.default</code> attribute. 
	 * @param value the default - Defines if the device is optional for current ui experience
	 */
	public void setDefault(final Boolean value)
	{
		setDefault( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.default</code> attribute. 
	 * @param value the default - Defines if the device is optional for current ui experience
	 */
	public void setDefault(final SessionContext ctx, final boolean value)
	{
		setDefault( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.default</code> attribute. 
	 * @param value the default - Defines if the device is optional for current ui experience
	 */
	public void setDefault(final boolean value)
	{
		setDefault( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.height</code> attribute.
	 * @return the height - Defines the device viewport height size
	 */
	public Integer getHeight(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, HEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.height</code> attribute.
	 * @return the height - Defines the device viewport height size
	 */
	public Integer getHeight()
	{
		return getHeight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.height</code> attribute. 
	 * @return the height - Defines the device viewport height size
	 */
	public int getHeightAsPrimitive(final SessionContext ctx)
	{
		Integer value = getHeight( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.height</code> attribute. 
	 * @return the height - Defines the device viewport height size
	 */
	public int getHeightAsPrimitive()
	{
		return getHeightAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.height</code> attribute. 
	 * @param value the height - Defines the device viewport height size
	 */
	public void setHeight(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, HEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.height</code> attribute. 
	 * @param value the height - Defines the device viewport height size
	 */
	public void setHeight(final Integer value)
	{
		setHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.height</code> attribute. 
	 * @param value the height - Defines the device viewport height size
	 */
	public void setHeight(final SessionContext ctx, final int value)
	{
		setHeight( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.height</code> attribute. 
	 * @param value the height - Defines the device viewport height size
	 */
	public void setHeight(final int value)
	{
		setHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.name</code> attribute.
	 * @return the name - Defines the name of device
	 */
	public String getName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedDeviceSupport.getName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.name</code> attribute.
	 * @return the name - Defines the name of device
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.name</code> attribute. 
	 * @return the localized name - Defines the name of device
	 */
	public Map<Language,String> getAllName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,NAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.name</code> attribute. 
	 * @return the localized name - Defines the name of device
	 */
	public Map<Language,String> getAllName()
	{
		return getAllName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.name</code> attribute. 
	 * @param value the name - Defines the name of device
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedDeviceSupport.setName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.name</code> attribute. 
	 * @param value the name - Defines the name of device
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.name</code> attribute. 
	 * @param value the name - Defines the name of device
	 */
	public void setAllName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.name</code> attribute. 
	 * @param value the name - Defines the name of device
	 */
	public void setAllName(final Map<Language,String> value)
	{
		setAllName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.Orientation</code> attribute.
	 * @return the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public Boolean isOrientation(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ORIENTATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.Orientation</code> attribute.
	 * @return the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public Boolean isOrientation()
	{
		return isOrientation( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.Orientation</code> attribute. 
	 * @return the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public boolean isOrientationAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isOrientation( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.Orientation</code> attribute. 
	 * @return the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public boolean isOrientationAsPrimitive()
	{
		return isOrientationAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.Orientation</code> attribute. 
	 * @param value the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public void setOrientation(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ORIENTATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.Orientation</code> attribute. 
	 * @param value the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public void setOrientation(final Boolean value)
	{
		setOrientation( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.Orientation</code> attribute. 
	 * @param value the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public void setOrientation(final SessionContext ctx, final boolean value)
	{
		setOrientation( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.Orientation</code> attribute. 
	 * @param value the Orientation - Defines if the device has orientation support like horizontal, vertical
	 */
	public void setOrientation(final boolean value)
	{
		setOrientation( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.supportedUiExperience</code> attribute.
	 * @return the supportedUiExperience - Defines the ui experience level which is supported by this device
	 */
	public EnumerationValue getSupportedUiExperience(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, SUPPORTEDUIEXPERIENCE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.supportedUiExperience</code> attribute.
	 * @return the supportedUiExperience - Defines the ui experience level which is supported by this device
	 */
	public EnumerationValue getSupportedUiExperience()
	{
		return getSupportedUiExperience( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.supportedUiExperience</code> attribute. 
	 * @param value the supportedUiExperience - Defines the ui experience level which is supported by this device
	 */
	public void setSupportedUiExperience(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, SUPPORTEDUIEXPERIENCE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.supportedUiExperience</code> attribute. 
	 * @param value the supportedUiExperience - Defines the ui experience level which is supported by this device
	 */
	public void setSupportedUiExperience(final EnumerationValue value)
	{
		setSupportedUiExperience( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.width</code> attribute.
	 * @return the width - Defines the device viewport width size
	 */
	public Integer getWidth(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, WIDTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.width</code> attribute.
	 * @return the width - Defines the device viewport width size
	 */
	public Integer getWidth()
	{
		return getWidth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.width</code> attribute. 
	 * @return the width - Defines the device viewport width size
	 */
	public int getWidthAsPrimitive(final SessionContext ctx)
	{
		Integer value = getWidth( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeviceSupport.width</code> attribute. 
	 * @return the width - Defines the device viewport width size
	 */
	public int getWidthAsPrimitive()
	{
		return getWidthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.width</code> attribute. 
	 * @param value the width - Defines the device viewport width size
	 */
	public void setWidth(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, WIDTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.width</code> attribute. 
	 * @param value the width - Defines the device viewport width size
	 */
	public void setWidth(final Integer value)
	{
		setWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.width</code> attribute. 
	 * @param value the width - Defines the device viewport width size
	 */
	public void setWidth(final SessionContext ctx, final int value)
	{
		setWidth( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeviceSupport.width</code> attribute. 
	 * @param value the width - Defines the device viewport width size
	 */
	public void setWidth(final int value)
	{
		setWidth( getSession().getSessionContext(), value );
	}
	
}
