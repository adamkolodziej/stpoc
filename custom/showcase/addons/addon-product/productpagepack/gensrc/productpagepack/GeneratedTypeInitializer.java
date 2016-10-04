

package productpagepack;

import java.util.*;
import java.io.Serializable;
import de.hybris.platform.util.*;
import de.hybris.platform.core.*;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.type.*;
import de.hybris.platform.persistence.type.*;
import de.hybris.platform.persistence.enumeration.*;
import de.hybris.platform.persistence.property.PersistenceManager;
import de.hybris.platform.persistence.*;

/**
 * Generated by hybris Platform.
 */
@SuppressWarnings({"cast","unused","boxing","null", "PMD"})
public class GeneratedTypeInitializer extends AbstractTypeInitializer
{
	/**
	 * Generated by hybris Platform.
	 */
	public GeneratedTypeInitializer( ManagerEJB manager, Map params )
	{
		super( manager, params );
	}


	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected void performRemoveObjects( ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// no-op by now
	}

	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected final void performCreateTypes( final ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// performCreateTypes
	
	
		createItemType(
			"SimpleProductImageComponent",
			"SimpleCMSComponent",
			com.hybris.cms.productpages.jalo.SimpleProductImageComponent.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"SimpleProductVariantSelectorComponent",
			"SimpleCMSComponent",
			com.hybris.cms.productpages.jalo.SimpleProductVariantSelectorComponent.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"SimpleProductReviewSummaryComponent",
			"SimpleCMSComponent",
			com.hybris.cms.productpages.jalo.SimpleProductReviewSummaryComponent.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"SimpleProductPromotionSummaryComponent",
			"SimpleCMSComponent",
			com.hybris.cms.productpages.jalo.SimpleProductPromotionSummaryComponent.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"SimpleProductSummaryComponent",
			"SimpleCMSComponent",
			com.hybris.cms.productpages.jalo.SimpleProductSummaryComponent.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"SimpleProductPageTabsComponent",
			"SimpleCMSComponent",
			com.hybris.cms.productpages.jalo.SimpleProductPageTabsComponent.class,
			null,
			false,
			null,
			false
		);
	
		createEnumerationType(
			"ProductImageGalleryPosition",
			null
		);
	
		createEnumerationType(
			"VariantSelectorType",
			null
		);
	
	}

	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected final void performModifyTypes( final ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// performModifyTypes
	

	
	
				single_createattr_SimpleProductImageComponent_showGallery();
			
				single_createattr_SimpleProductImageComponent_galleryPosition();
			
				single_createattr_SimpleProductVariantSelectorComponent_selectorType();
			

	}

	
	public void single_createattr_SimpleProductImageComponent_showGallery() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"SimpleProductImageComponent", 
					"showGallery",  
					null,
					"java.lang.Boolean",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_SimpleProductImageComponent_galleryPosition() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"SimpleProductImageComponent", 
					"galleryPosition",  
					null,
					"ProductImageGalleryPosition",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_SimpleProductVariantSelectorComponent_selectorType() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"SimpleProductVariantSelectorComponent", 
					"selectorType",  
					null,
					"VariantSelectorType",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	


	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected final void performCreateObjects( final ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// performCreateObjects
	
	
		createEnumerationValues(
			"ProductImageGalleryPosition",
			true,
			Arrays.asList( new String[] {
			
				"left",
				"right"
			} )
		);
	
		createEnumerationValues(
			"VariantSelectorType",
			true,
			Arrays.asList( new String[] {
			
				"default",
				"apparel"
			} )
		);
	
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"SimpleProductImageComponent",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_SimpleProductImageComponent_showGallery();
		
			single_setAttributeProperties_SimpleProductImageComponent_galleryPosition();
		
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"SimpleProductVariantSelectorComponent",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_SimpleProductVariantSelectorComponent_selectorType();
		
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"SimpleProductReviewSummaryComponent",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"SimpleProductPromotionSummaryComponent",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"SimpleProductSummaryComponent",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"SimpleProductPageTabsComponent",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
				setDefaultProperties(
					"ProductImageGalleryPosition",
					true,
					true,
					null
				);
			
				setDefaultProperties(
					"VariantSelectorType",
					true,
					true,
					null
				);
			
	}


		
						public void single_setAttributeProperties_SimpleProductImageComponent_showGallery() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"SimpleProductImageComponent", 
								"showGallery",
								false, 
								java.lang.Boolean.TRUE,
								"java.lang.Boolean.TRUE",
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_SimpleProductImageComponent_galleryPosition() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"SimpleProductImageComponent", 
								"galleryPosition",
								false, 
								em().getEnumerationValue("ProductImageGalleryPosition","left"),
								"em().getEnumerationValue(\"ProductImageGalleryPosition\",\"left\")",
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_SimpleProductVariantSelectorComponent_selectorType() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"SimpleProductVariantSelectorComponent", 
								"selectorType",
								false, 
								em().getEnumerationValue("VariantSelectorType","default"),
								"em().getEnumerationValue(\"VariantSelectorType\",\"default\")",
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
}

	