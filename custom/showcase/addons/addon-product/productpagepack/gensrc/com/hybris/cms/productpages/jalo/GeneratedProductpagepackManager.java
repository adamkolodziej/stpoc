/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 2015-01-07 08:51:01                         ---
 * ----------------------------------------------------------------
 */
package com.hybris.cms.productpages.jalo;

import com.hybris.cms.productpages.constants.ProductpagepackConstants;
import com.hybris.cms.productpages.jalo.SimpleProductImageComponent;
import com.hybris.cms.productpages.jalo.SimpleProductPageTabsComponent;
import com.hybris.cms.productpages.jalo.SimpleProductPromotionSummaryComponent;
import com.hybris.cms.productpages.jalo.SimpleProductReviewSummaryComponent;
import com.hybris.cms.productpages.jalo.SimpleProductSummaryComponent;
import com.hybris.cms.productpages.jalo.SimpleProductVariantSelectorComponent;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>ProductpagepackManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductpagepackManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public SimpleProductImageComponent createSimpleProductImageComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductpagepackConstants.TC.SIMPLEPRODUCTIMAGECOMPONENT );
			return (SimpleProductImageComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SimpleProductImageComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SimpleProductImageComponent createSimpleProductImageComponent(final Map attributeValues)
	{
		return createSimpleProductImageComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SimpleProductPageTabsComponent createSimpleProductPageTabsComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductpagepackConstants.TC.SIMPLEPRODUCTPAGETABSCOMPONENT );
			return (SimpleProductPageTabsComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SimpleProductPageTabsComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SimpleProductPageTabsComponent createSimpleProductPageTabsComponent(final Map attributeValues)
	{
		return createSimpleProductPageTabsComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SimpleProductPromotionSummaryComponent createSimpleProductPromotionSummaryComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductpagepackConstants.TC.SIMPLEPRODUCTPROMOTIONSUMMARYCOMPONENT );
			return (SimpleProductPromotionSummaryComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SimpleProductPromotionSummaryComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SimpleProductPromotionSummaryComponent createSimpleProductPromotionSummaryComponent(final Map attributeValues)
	{
		return createSimpleProductPromotionSummaryComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SimpleProductReviewSummaryComponent createSimpleProductReviewSummaryComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductpagepackConstants.TC.SIMPLEPRODUCTREVIEWSUMMARYCOMPONENT );
			return (SimpleProductReviewSummaryComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SimpleProductReviewSummaryComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SimpleProductReviewSummaryComponent createSimpleProductReviewSummaryComponent(final Map attributeValues)
	{
		return createSimpleProductReviewSummaryComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SimpleProductSummaryComponent createSimpleProductSummaryComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductpagepackConstants.TC.SIMPLEPRODUCTSUMMARYCOMPONENT );
			return (SimpleProductSummaryComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SimpleProductSummaryComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SimpleProductSummaryComponent createSimpleProductSummaryComponent(final Map attributeValues)
	{
		return createSimpleProductSummaryComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SimpleProductVariantSelectorComponent createSimpleProductVariantSelectorComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ProductpagepackConstants.TC.SIMPLEPRODUCTVARIANTSELECTORCOMPONENT );
			return (SimpleProductVariantSelectorComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SimpleProductVariantSelectorComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SimpleProductVariantSelectorComponent createSimpleProductVariantSelectorComponent(final Map attributeValues)
	{
		return createSimpleProductVariantSelectorComponent( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return ProductpagepackConstants.EXTENSIONNAME;
	}
	
}
