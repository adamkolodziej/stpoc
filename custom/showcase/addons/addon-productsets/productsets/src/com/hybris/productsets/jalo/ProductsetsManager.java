package com.hybris.productsets.jalo;

import com.hybris.productsets.constants.ProductsetsConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class ProductsetsManager extends GeneratedProductsetsManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( ProductsetsManager.class.getName() );
	
	public static final ProductsetsManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ProductsetsManager) em.getExtension(ProductsetsConstants.EXTENSIONNAME);
	}
	
}
