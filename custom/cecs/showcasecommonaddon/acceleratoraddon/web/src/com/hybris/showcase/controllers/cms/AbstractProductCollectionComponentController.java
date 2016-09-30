package com.hybris.showcase.controllers.cms;

import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryTermData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.lang.String;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;

import com.hybris.showcase.model.components.AbstractProductCollectionComponentModel;


/**
 * CECS-79 ProductCarouselComponent and FixedProductSetComponent Created by mgolubovic on 4.2.2015..
 */
public abstract class AbstractProductCollectionComponentController<T extends AbstractProductCollectionComponentModel>
		extends AbstractCMSAddOnComponentController<AbstractProductCollectionComponentModel>
{
	protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);

	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	@Resource(name = "productSearchFacade")
	private ProductSearchFacade<ProductData> productSearchFacade;

	@Resource(name = "solrSearchQueryDecoder")
	private Converter<SearchQueryData, SolrSearchQueryData> solrSearchQueryDecoder;

	@Resource(name = "solrSearchQueryEncoder")
	private Converter<SolrSearchQueryData, SearchQueryData> solrSearchQueryEncoder;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
							 final AbstractProductCollectionComponentModel component)
	{
		final List<ProductData> products = new ArrayList<>();

		RequestContextData requestContextData = getRequestContextData(request);

		ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData = (ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData>) requestContextData
				.getSearch();

		products.addAll(collectLinkedProducts(component));
		final ProductSearchPageData<SearchStateData, ProductData> componentResults = collectSearchProducts(component,
				searchPageData, component.getMaximumNumberProducts());
		if (componentResults != null)
		{
			model.addAttribute("componentQuery", componentResults.getCurrentQuery().getUrl());
			products.addAll(componentResults.getResults());
		}
		else
		{
			model.addAttribute("componentQuery", null);
		}


		model.addAttribute("title", component.getTitle());
		model.addAttribute("cssClass", component.getCssClass());
		model.addAttribute("displayProductName", component.getDisplayProductName());
		model.addAttribute("showSeeAllBtn", component.getShowSeeAllBtn());
		model.addAttribute("subtitle", component.getSubtitle());
		model.addAttribute("showPrice", component.getShowPrice());
		model.addAttribute("showRates", component.getShowRates());
		if (component.getMaximumNumberProducts() != null && component.getMaximumNumberProducts().intValue() < products.size())
		{
			model.addAttribute("productData", products.subList(0, component.getMaximumNumberProducts().intValue()));
		}
		else
		{
			model.addAttribute("productData", products);
		}
	}

	protected List<ProductData> collectLinkedProducts(final AbstractProductCollectionComponentModel component)
	{
		final List<ProductData> products = new ArrayList<>();

		for (final ProductModel productModel : component.getProducts())
		{
			products.add(productFacade.getProductForOptions(productModel, PRODUCT_OPTIONS));
		}

		for (final CategoryModel categoryModel : component.getCategories())
		{
			for (final ProductModel productModel : categoryModel.getProducts())
			{
				products.add(productFacade.getProductForOptions(productModel, PRODUCT_OPTIONS));
			}
		}

		return products;
	}

	protected ProductSearchPageData<SearchStateData, ProductData> collectSearchProducts(
			final AbstractProductCollectionComponentModel component,
			ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData, Integer maximumNumberProducts)
	{
		SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(component.getSearchQuery());
		final String categoryCode = component.getCategoryCode();
		String freeTextSearch = null;

		if (searchPageData != null)
		{
			SolrSearchQueryData compQuery = solrSearchQueryDecoder.convert(searchQueryData);
			SolrSearchQueryData pageQuery = solrSearchQueryDecoder.convert(searchPageData.getCurrentQuery().getQuery());

			compQuery.setFilterTerms(mergeFilterTerms(compQuery, pageQuery));

			if (StringUtils.isNotBlank(pageQuery.getFreeTextSearch()))
			{
				compQuery.setFreeTextSearch(pageQuery.getFreeTextSearch());
			}
			freeTextSearch = compQuery.getFreeTextSearch();
			searchQueryData = solrSearchQueryEncoder.convert(compQuery);
		}

		if (searchQueryData.getValue() != null && categoryCode != null)
		{
			final SearchStateData searchState = new SearchStateData();
			searchState.setQuery(searchQueryData);

			final PageableData pageableData = new PageableData();
			pageableData.setPageSize(maximumNumberProducts != null ? maximumNumberProducts : 100); // Limit to 100 matching results

			if (StringUtils.isNotBlank(freeTextSearch))
			{
				return productSearchFacade.textSearch(searchState, pageableData);
			}
			else
			{
				return productSearchFacade.categorySearch(categoryCode, searchState, pageableData);
			}
		}

		return null;
	}

	private ArrayList<SolrSearchQueryTermData> mergeFilterTerms(SolrSearchQueryData compQuery, SolrSearchQueryData pageQuery) {
		if (pageQuery.getFilterTerms() != null)
		{
			if (compQuery.getFilterTerms() == null)
			{
				compQuery.setFilterTerms(new ArrayList<>());
			}
			Map<String, SolrSearchQueryTermData> termsMap = new LinkedHashMap<>();

			for (SolrSearchQueryTermData term : compQuery.getFilterTerms())
			{
				termsMap.put(term.getKey() + ":" + term.getValue(), term);
			}

			for (SolrSearchQueryTermData term : pageQuery.getFilterTerms())
			{
				termsMap.put(term.getKey() + ":" + term.getValue(), term);
			}

			return new ArrayList<>(termsMap.values());
		}

		return null;
	}

}
