package com.hybris.showcase.ybillingintegration.ssc.impl;

import static com.hybris.showcase.guidedselling.enums.BundleComponentType.ADDON;
import static com.hybris.showcase.guidedselling.enums.BundleComponentType.CONDITIONAL;
import static com.hybris.showcase.guidedselling.enums.BundleComponentType.OTHER;
import static com.hybris.showcase.guidedselling.enums.BundleComponentType.TARGET;

import de.hybris.platform.b2ctelcoservices.model.DeviceModel;
import de.hybris.platform.b2ctelcoservices.model.ServiceAddOnModel;
import de.hybris.platform.b2ctelcoservices.model.ServicePlanModel;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.configurablebundleservices.bundle.BundleTemplateService;
import de.hybris.platform.configurablebundleservices.enums.BundleTemplateStatusEnum;
import de.hybris.platform.configurablebundleservices.model.BundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateStatusModel;
import de.hybris.platform.configurablebundleservices.model.PickExactlyNBundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.PickNToMBundleSelectionCriteriaModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.daos.UnitDao;
import de.hybris.platform.sap.productconfig.runtime.interf.impl.KBKeyImpl;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConfigModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.CsticModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.CsticValueModel;
import de.hybris.platform.sap.productconfig.services.intf.ProductConfigurationService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionTermModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.model.BundlePackageModel;
import com.hybris.showcase.ybillingintegration.model.ProductConfigurationTemplateModel;
import com.hybris.showcase.ybillingintegration.ssc.ConfigurationMappingService;


/**
 * CECS-381 Testing implementation for SSC Created by miroslaw.szot@sap.com on 2015-10-15.
 */
public class SSCConfigurationMappingService implements ConfigurationMappingService
{

	// configurable settigns to map cstics to bundle templates
	public static final String CONF_PACKAGE_KEY = "ybillingintegration.cstic.package";
	public static final String CONF_CONDITIONAL_KEY = "ybillingintegration.cstic.conditional";
	public static final String CONF_TARGET_KEY = "ybillingintegration.cstic.target";
	public static final String CONF_VARCOND_KEY = "ybillingintegration.cstic.varcond";

	public static final String CONF_TRICAST_BUNDLE_TEMPLATE_ID = "ybillingintegration.bundletemplate.tricast.id";

	// category where new ServicePlan's should be assigned
	public static final String CONF_PLANS_CATEGORY_KEY = "ybillingintegration.category.plans";

	public static final String CONF_YBILLING_ENABLED = "showcasecommon.ybillingintegration.enabled";

	public static final String TV_ADDONS_NAME = "TriCast-TV-Addons";

	private static final Logger LOG = Logger.getLogger(SSCConfigurationMappingService.class);

	private ModelService modelService;
	private CommerceCommonI18NService commerceCommonI18NService;
	private FlexibleSearchService flexibleSearchService;
	private BundleTemplateService bundleTemplateService;
	private SSCProductConfigurationLoader productConfigurationLoader;
	private UnitDao unitDao;
	private ProductService productService;
	private ProductConfigurationService productConfigurationService;
	private CategoryService categoryService;
	private SessionService sessionService;
	private BaseSiteService baseSiteService;
	private CMSSiteService cmsSiteService;
	private UserService userService;
	private KeyGenerator bundleSelectionCriteriaIdGenerator;
	private ConfigurationService configurationService;


	@Override
	public ProductConfigurationTemplateModel getProductConfigurationTemplate(final BundleTemplateModel bundleTemplate)
	{
		final BundleTemplateModel rootBundleTemplate = getBundleTemplateService().getRootBundleTemplate(bundleTemplate);
		return rootBundleTemplate.getMappingTemplate();
	}

	@Override
	public BundleTemplateModel getBundleTemplateModel(final ProductConfigurationTemplateModel template, final CMSSiteModel site,
			final CatalogVersionModel catalogVersion)
	{
		final String code = StringUtils.isNotBlank(template.getYBillingId()) ? template.getYBillingId() : template.getCode();
		final ConfigModel configModel = getProductConfigurationLoader().getConfigurationModel(code, site, catalogVersion);

		final BundleTemplateModel rootBundleTemplate;

		if (configModel == null)
		{
			LOG.error("Unable to find SSC configuration for " + code);
			rootBundleTemplate = null;
		}
		else
		{
			rootBundleTemplate = createBundleTemplateModel(template, configModel, site, catalogVersion);


		}
		return rootBundleTemplate;
	}

	@Override
	public boolean isIntegrationWithYbilling()
	{
		return configurationService.getConfiguration().getBoolean(CONF_YBILLING_ENABLED, false);
	}

	public String getTricastBundleTemplateId()
	{
		return configurationService.getConfiguration().getString(CONF_TRICAST_BUNDLE_TEMPLATE_ID, "TRICAST_PACK");
	}

	private BundleTemplateModel createBundleTemplateModel(final ProductConfigurationTemplateModel template,
			final ConfigModel configModel, final CMSSiteModel site, final CatalogVersionModel catalogVersion)
	{

		List<BundleTemplateModel> childTemplates = new ArrayList<>();

		final Date curDate = new Date();
		final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		final String version = format.format(curDate);

		final String configRootProductCode = configModel.getRootInstance().getName();

		final BundleTemplateStatusModel bundleTemplateStatus = createNewBundleTemplateStatus(catalogVersion, version,
				configRootProductCode, BundleTemplateStatusEnum.CHECK);

		final BundleTemplateModel rootBundleTemplate = getModelService().create(BundleTemplateModel.class);
		LOG.info("creating root bundle template: " + configRootProductCode);

		rootBundleTemplate.setCatalogVersion(catalogVersion);
		rootBundleTemplate.setVersion(version);
		rootBundleTemplate.setStatus(bundleTemplateStatus);
		rootBundleTemplate.setBundleComponentType(OTHER);
		rootBundleTemplate.setYBillingId(configRootProductCode);
		rootBundleTemplate.setMappingTemplate(template);

		rootBundleTemplate.setName(configModel.getRootInstance().getLanguageDependentName());
		rootBundleTemplate.setId(getTricastBundleTemplateId());

		final UnitModel unitModel = getUnitByCode("pieces");

		final Configuration yConfig = configurationService.getConfiguration();
		final String conditionalCsticId = yConfig.getString(CONF_CONDITIONAL_KEY);
		final String targetCsticId = yConfig.getString(CONF_TARGET_KEY);
		final String packageCsticId = yConfig.getString(CONF_PACKAGE_KEY);
		final String varCondCsticId = yConfig.getString(CONF_VARCOND_KEY);
		final String plansCategoryCode = yConfig.getString(CONF_PLANS_CATEGORY_KEY);

		List<CsticModel> cstics = configModel.getRootInstance().getCstics();
		CsticModel packageCstic = null;

		if (StringUtils.isNotBlank(packageCsticId))
		{
			packageCstic = cstics.stream() //
					.filter(c -> packageCsticId.equals(c.getName())) //
					.findFirst() //
					.orElse(null);
		}

		final Set<String> blacklisted = new HashSet<>(Arrays.asList(packageCsticId, varCondCsticId));

		cstics = cstics.stream() //
				.filter(c -> !blacklisted.contains(c.getName())) //
				.collect(Collectors.toList());

		final List<PriceRowModel> allPrices = new ArrayList<>();

		for (final CsticModel cstic : cstics)
		{
			if (packageCstic == cstic)
			{
				continue;
			}

			BundleTemplateModel childTemplate;
			if (cstic.getName().startsWith("TV_ADD_"))
			{

				// getBundleTemplate
				try
				{
					childTemplate = bundleTemplateService.getBundleTemplateForCode(TV_ADDONS_NAME, version);
				}
				catch (final ModelNotFoundException ex)
				{
					childTemplate = childTemplates.stream().filter(c -> TV_ADDONS_NAME.equals(c.getId())).findFirst().orElse(null);
					if (childTemplate == null)
					{
						childTemplate = createBundleTemplate(catalogVersion, version, bundleTemplateStatus, rootBundleTemplate, cstic,
								conditionalCsticId, targetCsticId, TV_ADDONS_NAME, "TV Add-ons");
					}
				}
			}
			else
			{

				childTemplate = createBundleTemplate(catalogVersion, version, bundleTemplateStatus, rootBundleTemplate, cstic,
						conditionalCsticId, targetCsticId, null, null);

			}



			final List<ProductModel> products = childTemplate.getProducts() == null ? new ArrayList<>()
					: childTemplate.getProducts();
			final List<CsticValueModel> assignableValues = cstic.getAssignableValues();

			if (isCsticBoolean(cstic))
			{
				final ProductModel product;

				if (cstic.getAssignableValues().get(0).getName().endsWith("_Y"))
				{
					product = getOrCreateProduct(catalogVersion, unitModel, conditionalCsticId, targetCsticId, plansCategoryCode,
							allPrices, cstic, cstic.getAssignableValues().get(0), cstic.getAssignableValues().get(1));


				}
				else
				{
					product = getOrCreateProduct(catalogVersion, unitModel, conditionalCsticId, targetCsticId, plansCategoryCode,
							allPrices, cstic, cstic.getAssignableValues().get(1), cstic.getAssignableValues().get(0));

				}
				products.add(product);
			}
			else
			{

				for (final CsticValueModel value : assignableValues)
				{
					final ProductModel product = getOrCreateProduct(catalogVersion, unitModel, conditionalCsticId, targetCsticId,
							plansCategoryCode, allPrices, cstic, value, null);

					products.add(product);
				}
			}


			childTemplate.setProducts(products);
			childTemplates.add(childTemplate);
		}

		childTemplates = childTemplates.stream() //
				.sorted(new BundleComponentTypeComparator()) //
				.collect(Collectors.toList());

		rootBundleTemplate.setChildTemplates(childTemplates);

		if (packageCstic != null)
		{
			mapBundlePackages(catalogVersion, rootBundleTemplate, packageCstic);
		}

		final List<BundlePackageModel> bundlePackageModels = rootBundleTemplate.getPackages();
		if (CollectionUtils.isNotEmpty(bundlePackageModels))
		{
			addPreselectionsToPackages(bundlePackageModels, rootBundleTemplate.getYBillingId(), site, catalogVersion);
		}

		getModelService().save(rootBundleTemplate);
		getModelService().saveAll(allPrices);



		return rootBundleTemplate;
	}

	private BundleTemplateModel createBundleTemplate(final CatalogVersionModel catalogVersion, final String version,
			final BundleTemplateStatusModel bundleTemplateStatus, final BundleTemplateModel rootBundleTemplate,
			final CsticModel cstic, final String conditionalCsticId, final String targetCsticId, final String code,
			final String name)
	{
		LOG.info("Creating new characteristic: " + rootBundleTemplate.getYBillingId() + "/" + cstic.getName());
		final BundleTemplateModel childTemplate = getModelService().create(BundleTemplateModel.class);
		childTemplate.setName(name == null ? cstic.getLanguageDependentName() : name);
		childTemplate.setId(code == null ? cstic.getName() : code);
		childTemplate.setYBillingId(cstic.getName());
		childTemplate.setVersion(version);
		childTemplate.setStatus(bundleTemplateStatus);
		childTemplate.setCatalogVersion(catalogVersion);
		childTemplate.setParentTemplate(rootBundleTemplate);

		BundleSelectionCriteriaModel bundleSelectionCriteria = null;
		if (TV_ADDONS_NAME.equals(code))
		{
			LOG.info("selection criteria: 0..100");

			bundleSelectionCriteria = getModelService().create(PickNToMBundleSelectionCriteriaModel.class);
			((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setN(Integer.valueOf(0));
			((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setM(Integer.valueOf(100));
		}
		else if (cstic.isMultivalued())
		{
			if (cstic.isRequired())
			{
				LOG.info("selection criteria: 1..100");
				//                    bundleSelectionCriteria = new PickNToMBundleSelectionCriteriaModel();
				bundleSelectionCriteria = getModelService().create(PickNToMBundleSelectionCriteriaModel.class);
				((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setN(Integer.valueOf(1));
				((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setM(Integer.valueOf(100));
			}
			else
			{
				LOG.info("selection criteria: 0..100");
				//bundleSelectionCriteria = new PickNToMBundleSelectionCriteriaModel();
				bundleSelectionCriteria = getModelService().create(PickNToMBundleSelectionCriteriaModel.class);
				((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setN(Integer.valueOf(0));
				((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setM(Integer.valueOf(100));
			}
		}
		else
		{
			if (cstic.isRequired())
			{
				LOG.info("selection criteria: exactly 1");
				//					bundleSelectionCriteria = new PickExactlyNBundleSelectionCriteriaModel();
				bundleSelectionCriteria = getModelService().create(PickExactlyNBundleSelectionCriteriaModel.class);
				((PickExactlyNBundleSelectionCriteriaModel) bundleSelectionCriteria).setN(Integer.valueOf(1));
			}
			else
			{
				LOG.info("selection criteria: 0..1");
				//					bundleSelectionCriteria = new PickNToMBundleSelectionCriteriaModel();
				bundleSelectionCriteria = getModelService().create(PickNToMBundleSelectionCriteriaModel.class);
				((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setN(Integer.valueOf(0));
				((PickNToMBundleSelectionCriteriaModel) bundleSelectionCriteria).setM(Integer.valueOf(1));
			}
		}

		//bundleSelectionCriteria.setId(bundleSelectionCriteriaIdGenerator.generate().toString());
		bundleSelectionCriteria.setCatalogVersion(catalogVersion);
		childTemplate.setBundleSelectionCriteria(bundleSelectionCriteria);

		final BundleComponentType compType = determinateBundleComponentType(conditionalCsticId, targetCsticId, cstic);
		childTemplate.setBundleComponentType(compType);
		LOG.info("Setting component type " + compType.getCode() + " for: " + rootBundleTemplate.getYBillingId() + "/"
				+ cstic.getName());
		LOG.info("cstic value type is: " + cstic.getValueType());




		return childTemplate;
	}

	private boolean isCsticBoolean(final CsticModel cstic)
	{
		final List<CsticValueModel> ctsicValues = cstic.getAssignableValues();
		if (ctsicValues.size() == 2 && (ctsicValues.get(0).getName().endsWith("_Y") || ctsicValues.get(0).getName().endsWith("_N"))
				&& (ctsicValues.get(1).getName().endsWith("_Y") || ctsicValues.get(1).getName().endsWith("_N")))
		{

			return true;
		}
		return false;
	}

	private ProductModel getOrCreateProduct(final CatalogVersionModel catalogVersion, final UnitModel unitModel,
			final String conditionalCsticId, final String targetCsticId, final String plansCategoryCode,
			final List<PriceRowModel> allPrices, final CsticModel cstic, final CsticValueModel value,
			final CsticValueModel onRemoveValue)
	{
		final String csvCode = value.getName();
		final String productCode = cstic.getName() + '-' + csvCode;

		ProductModel product = getProductByYBillingId(csvCode, catalogVersion, false);

		if (product == null)
		{
			LOG.info("Can't find product by yBilling Id: " + csvCode);
			product = getProductByCode(productCode, catalogVersion);
		}

		if (product == null)
		{
			final Class<? extends ProductModel> productModelClass = determinateProductClass(conditionalCsticId, targetCsticId,
					cstic);
			LOG.info("Creating new product " + productCode + " of type " + productModelClass.getSimpleName());

			product = getModelService().create(productModelClass);
			product.setCatalogVersion(catalogVersion);
			product.setName(value.getLanguageDependentName());
			product.setYBillingId(csvCode);

			product.setCode(productCode);
			product.setUnit(unitModel);
			product.setApprovalStatus(ArticleApprovalStatus.APPROVED);
			if (onRemoveValue != null)
			{
				product.setYBillingIdOnRemove(onRemoveValue.getName());
			}

			if (product instanceof SubscriptionProductModel)
			{
				final SubscriptionProductModel subscription = ((SubscriptionProductModel) product);
				final SubscriptionTermModel term = getSubscriptionTerm(cstic, value, product);
				subscription.setSubscriptionTerm(term);

				final CategoryModel category = categoryService.getCategoryForCode(catalogVersion, plansCategoryCode);
				product.setSupercategories(Arrays.asList(category));
			}

			allPrices.add(createPriceRow(catalogVersion, unitModel, value, product));
		}
		return product;
	}

	private BundleTemplateStatusModel createNewBundleTemplateStatus(final CatalogVersionModel catalogVersion, final String version,
			final String configRootProductCode, final BundleTemplateStatusEnum status)
	{
		final BundleTemplateStatusModel bundleTemplateStatus = getModelService().create(BundleTemplateStatusModel.class);
		bundleTemplateStatus.setId(configRootProductCode + "-" + version);
		bundleTemplateStatus.setCatalogVersion(catalogVersion);
		bundleTemplateStatus.setStatus(status);
		getModelService().save(bundleTemplateStatus);
		return bundleTemplateStatus;
	}

	private UnitModel getUnitByCode(final String unitCode)
	{
		UnitModel unitModel;
		final Set<UnitModel> units = getUnitDao().findUnitsByCode(unitCode);

		if (!units.isEmpty())
		{
			unitModel = units.iterator().next();
		}
		else
		{
			LOG.warn("creating new unit 'pieces', that shouldn't happen!");
			unitModel = getModelService().create(UnitModel.class);
			unitModel.setCode(unitCode);
			getModelService().save(unitModel);
		}
		return unitModel;
	}

	private Class<? extends ProductModel> determinateProductClass(final String conditionalCsticId, final String targetCsticId,
			final CsticModel cstic)
	{
		Class<? extends ProductModel> productModelClass;
		if (conditionalCsticId.equals(cstic.getName()))
		{
			productModelClass = ServicePlanModel.class;
		}
		else if (StringUtils.isNotBlank(targetCsticId) && targetCsticId.equals(cstic.getName()))
		{
			productModelClass = DeviceModel.class;
		}
		else
		{
			productModelClass = ServiceAddOnModel.class;
		}
		return productModelClass;
	}

	private BundleComponentType determinateBundleComponentType(final String conditionalCsticId, final String targetCsticId,
			final CsticModel cstic)
	{
		BundleComponentType compType;

		if (conditionalCsticId.equals(cstic.getName()))
		{
			compType = CONDITIONAL;
		}
		else if (StringUtils.isNotBlank(targetCsticId) && targetCsticId.equals(cstic.getName()))
		{
			compType = TARGET;
		}
		else
		{
			compType = ADDON;
		}
		return compType;
	}

	private void mapBundlePackages(final CatalogVersionModel catalogVersion, final BundleTemplateModel rootBundleTemplate,
			final CsticModel packageCstic)
	{
		final List<BundlePackageModel> packages = new ArrayList<>();
		final List<CsticValueModel> assignableValues = packageCstic.getAssignableValues();

		for (final CsticValueModel value : assignableValues)
		{
			final String packageCode = rootBundleTemplate.getId() + "-" + rootBundleTemplate.getVersion() + "-" + value.getName();
			LOG.info("creating package: " + packageCode);
			final BundlePackageModel bundlePackage = getModelService().create(BundlePackageModel.class);
			bundlePackage.setCode(packageCode);
			bundlePackage.setCatalogVersion(catalogVersion);
			bundlePackage.setYBillingId(value.getName());
			bundlePackage.setName(value.getLanguageDependentName());
			bundlePackage.setBundleTemplate(rootBundleTemplate);

			// getModelService().save(bundlePackage);
			packages.add(bundlePackage);
		}

		rootBundleTemplate.setPackages(packages);
	}

	private void addPreselectionsToPackages(final List<BundlePackageModel> bundlePackages, final String yBillingId,
			final CMSSiteModel site, final CatalogVersionModel catalogVersion)
	{
		sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public void executeWithoutResult()
			{
				baseSiteService.setCurrentBaseSite(site, true);

				try
				{
					cmsSiteService.setCurrentCatalogVersion(catalogVersion);
				}
				catch (final CMSItemNotFoundException e)
				{
					LOG.error("Unable to set current catalog version to: " + catalogVersion.getMnemonic(), e);
				}

				userService.setCurrentUser(userService.getAdminUser());

				final Configuration yConfig = configurationService.getConfiguration();
				final String packageCsticId = yConfig.getString(CONF_PACKAGE_KEY);
				final String varCondCsticId = yConfig.getString(CONF_VARCOND_KEY);

				final Set<String> blacklisted = new HashSet<>(Arrays.asList(packageCsticId, varCondCsticId));
				final KBKeyImpl kbKeyImpl = new KBKeyImpl(yBillingId);

				for (final BundlePackageModel bundlePackageModel : bundlePackages)
				{
					final ConfigModel selectedConfigModel = productConfigurationService.createDefaultConfiguration(kbKeyImpl);
					final List<CsticModel> selectedCstics = selectedConfigModel.getRootInstance().getCstics();
					final CsticModel selectedPackageCstic = selectedCstics.stream().filter(c -> packageCsticId.equals(c.getName())) //
							.findFirst() //
							.orElse(null);

					if (selectedPackageCstic != null)
					{
						final String bundlePackageCode = bundlePackageModel.getYBillingId();
						selectedPackageCstic.addValue(bundlePackageCode);

						productConfigurationService.updateConfiguration(selectedConfigModel);


						final ConfigModel updatedConfigModel = productConfigurationService
								.retrieveConfigurationModel(selectedConfigModel.getId());
						final List<CsticModel> updatedCstics = updatedConfigModel.getRootInstance().getCstics();

						final List<ProductModel> productModelList = updatedCstics.stream() //
								.filter(c -> !blacklisted.contains(c.getName())) //
								.flatMap(c -> c.getAssignedValues().stream())//
								.peek(c -> LOG.info("***" + c.getName()))//
								.map(cv -> getProductByYBillingId(cv.getName(), catalogVersion, true)) //
								.filter(p -> p != null) //
								.collect(Collectors.toList());

						bundlePackageModel.setSelectedProducts(productModelList);

						LOG.info(bundlePackageCode + " -> "
								+ Arrays.toString(productModelList.stream().map(pm -> pm.getCode()).toArray()));

						productConfigurationService.releaseSession(selectedConfigModel.getId());
					}
				}
			}
		});
	}

	private PriceRowModel createPriceRow(final CatalogVersionModel cv, final UnitModel unit, final CsticValueModel value,
			final ProductModel product)
	{
		final PriceRowModel priceRowModel;
		if (product instanceof SubscriptionProductModel)
		{
			priceRowModel = new SubscriptionPricePlanModel();
		}
		else
		{
			priceRowModel = new PriceRowModel();
		}
		priceRowModel.setProduct(product);
		priceRowModel.setCatalogVersion(cv);

		if (null != value.getDeltaPrice() && null != value.getDeltaPrice().getPriceValue())
		{
			final String currencyCode = value.getDeltaPrice().getCurrency();
			final CurrencyModel example = new CurrencyModel();

			if (StringUtils.isEmpty(currencyCode))
			{
				example.setBase(Boolean.TRUE);
			}
			else
			{
				example.setIsocode(currencyCode);
			}

			final CurrencyModel currencyModel = getFlexibleSearchService().getModelByExample(example);
			priceRowModel.setCurrency(currencyModel);

			priceRowModel.setPrice(value.getDeltaPrice().getPriceValue().doubleValue());
		}
		else
		{
			priceRowModel.setCurrency(getCommerceCommonI18NService().getDefaultCurrency());
			priceRowModel.setPrice(0d);
		}

		priceRowModel.setUnit(unit);
		return priceRowModel;
	}

	private ProductModel getProductByYBillingId(final String csvCode, final CatalogVersionModel catalogVersionModel,
			final boolean allowDups)
	{
		final ProductModel example = new ProductModel();
		example.setYBillingId(csvCode);
		example.setCatalogVersion(catalogVersionModel);

		final List<ProductModel> results = flexibleSearchService.getModelsByExample(example);
		if (results.size() == 1)
		{
			return results.iterator().next();
		}
		else if (results.size() > 1)
		{
			LOG.warn(
					"multiple products for: " + csvCode + " -> " + Arrays.toString(results.stream().map(p -> p.getCode()).toArray()));
			if (allowDups)
			{
				return results.iterator().next();
			}
		}
		return null;
	}

	private ProductModel getProductByCode(final String code, final CatalogVersionModel catalogVersionModel)
	{
		try
		{
			return productService.getProductForCode(catalogVersionModel, code);
		}
		catch (final UnknownIdentifierException e)
		{
			return null;
		}
	}

	protected SubscriptionTermModel getSubscriptionTerm(final CsticModel cstic, final CsticValueModel value,
			final ProductModel product)
	{
		// YTODO this should be dynamically mapped but for now let's just hardcode it
		final SubscriptionTermModel term = new SubscriptionTermModel();
		term.setId("monthly_12");
		return flexibleSearchService.getModelByExample(term);
	}

	public static class BundleComponentTypeComparator implements Comparator<BundleTemplateModel>
	{
		@Override
		public int compare(final BundleTemplateModel o1, final BundleTemplateModel o2)
		{
			return o1.getBundleComponentType().ordinal() - o2.getBundleComponentType().ordinal();
		}
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	public BundleTemplateService getBundleTemplateService()
	{
		return bundleTemplateService;
	}

	@Required
	public void setBundleTemplateService(final BundleTemplateService bundleTemplateService)
	{
		this.bundleTemplateService = bundleTemplateService;
	}

	public SSCProductConfigurationLoader getProductConfigurationLoader()
	{
		return productConfigurationLoader;
	}

	@Required
	public void setProductConfigurationLoader(final SSCProductConfigurationLoader productConfigurationLoader)
	{
		this.productConfigurationLoader = productConfigurationLoader;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18NService;
	}

	@Required
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18NService)
	{
		this.commerceCommonI18NService = commerceCommonI18NService;
	}


	public UnitDao getUnitDao()
	{
		return unitDao;
	}

	@Required
	public void setUnitDao(final UnitDao unitDao)
	{
		this.unitDao = unitDao;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	public CategoryService getCategoryService()
	{
		return categoryService;
	}

	@Required
	public void setCategoryService(final CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	public ProductConfigurationService getProductConfigurationService()
	{
		return productConfigurationService;
	}

	@Required
	public void setProductConfigurationService(final ProductConfigurationService productConfigurationService)
	{
		this.productConfigurationService = productConfigurationService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public KeyGenerator getBundleSelectionCriteriaIdGenerator()
	{
		return bundleSelectionCriteriaIdGenerator;
	}

	@Required
	public void setBundleSelectionCriteriaIdGenerator(final KeyGenerator bundleSelectionCriteriaIdGenerator)
	{
		this.bundleSelectionCriteriaIdGenerator = bundleSelectionCriteriaIdGenerator;
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}


}
