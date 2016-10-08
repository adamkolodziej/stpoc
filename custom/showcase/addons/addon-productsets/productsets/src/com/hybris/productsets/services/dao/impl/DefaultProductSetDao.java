/**
 * 
 */
package com.hybris.productsets.services.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.model.ProductSetPromotionModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.hybris.productsets.enums.ProductSetType;
import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.dao.ProductSetDao;


/**
 * @author dominik.strzyzewski
 * 
 */
public class DefaultProductSetDao extends AbstractItemDao implements ProductSetDao
{

	private static final String QUERY_SETS_HAVING_ANY_PRODUCT = " select {ps.pk} from {ProductSet as ps "
			+ " left join ProductSetRelation as psr on {ps.pk} = {psr.source} "
			+ " left join Product as p on {psr.target} = {p.pk} } where {p.PK} in (?products) AND ( {ps.triggerFor} is null OR {ps.triggerFor} in (?products) ) ";

	private static final String QUERY_SETS_HAVING_ANY_PRODUCT_AND_TYPE = QUERY_SETS_HAVING_ANY_PRODUCT
			+ " and {ps.productSetType} = ?productSetType ";

	private static final String PRODUCT_SET_PROMO_QUERY = "SELECT DISTINCT pprom.pk, pprom.prio FROM ( {{ SELECT {psp.PK} as pk, {psp.priority} as prio FROM {ProductSetPromotion AS psp} ,   {ProductSetPromotionRelation AS pspr}  "
			+ " WHERE {pspr.source} = ?set "
			+ " AND {pspr.target} = {psp.PK}  "
			+ " AND {psp.promotionGroup} IN (?promotionGroups) "
			+ " AND {psp.enabled} = ?true  "
			+ " AND {psp.startDate} <= ?now "
			+ " AND ?now <= {psp.endDate}  }} )pprom ORDER BY pprom.prio DESC ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productsets.services.dao.ProductSetDao#findSetsHavingAnyProducts(java.util.List)
	 */
	@Override
	public List<ProductSetModel> findSetsHavingAnyProduct(final List<ProductModel> products)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(QUERY_SETS_HAVING_ANY_PRODUCT);
		fsq.addQueryParameter("products", products);
		final SearchResult<ProductSetModel> result = getFlexibleSearchService().search(fsq);
		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productsets.services.dao.ProductSetDao#findSetsHavingAnyProductsAndType(java.util.List,
	 * com.hybris.productsets.enums.ProductSetType)
	 */
	@Override
	public List<ProductSetModel> findSetsHavingAnyProductAndType(final List<ProductModel> products,
			final ProductSetType productSetType)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(QUERY_SETS_HAVING_ANY_PRODUCT_AND_TYPE);
		fsq.addQueryParameter("products", products);
		fsq.addQueryParameter("productSetType", productSetType);
		final SearchResult<ProductSetModel> result = getFlexibleSearchService().search(fsq);
		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productsets.services.dao.ProductSetDao#findSetByCode(java.lang.String)
	 */
	@Override
	public ProductSetModel findSetByCode(final String code)
	{
		final ProductSetModel example = new ProductSetModel();
		example.setCode(code);

		try
		{
			return getFlexibleSearchService().getModelByExample(example);
		}
		catch (final ModelNotFoundException e)
		{
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productsets.services.dao.ProductSetDao#findPromotionsForSet(com.hybris.productsets.model.ProductSetModel
	 * , java.util.Collection, java.util.Date)
	 */
	@Override
	public List<ProductSetPromotionModel> findPromotionsForSet(final ProductSetModel set,
			final Collection<PromotionGroupModel> promotionGroups, final Date date)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(PRODUCT_SET_PROMO_QUERY);
		fsq.addQueryParameter("promotionGroups", promotionGroups);
		fsq.addQueryParameter("set", set);
		fsq.addQueryParameter("now", date);
		fsq.addQueryParameter("true", Boolean.TRUE);

		return getFlexibleSearchService().<ProductSetPromotionModel> search(fsq).getResult();

	}



}
