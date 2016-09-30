package com.hybris.showcase.emsextras.facades.impl;

import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.model.BundlePackageModel;
import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.b2ctelcoservices.model.ServicePlanModel;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;

/**
 * CECS-244 in case of missing entitlement show something (proposal or open transactional site - product page)
 * 
 * Created by miroslaw.szot@sap.com on 2015-05-27.
 */
public class EntitlementProposalActivationUrlResolver implements UrlResolver<ProductModel> {

    private ContractDao contractDao;
    private UserService userService;
    private UrlResolver<ProductModel> productModelUrlResolver;

    @Override
    public String resolve(ProductModel product) {
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();
        ContractModel contract = getCurrentContract(customer);
        if( contract != null ) {
            BundleTemplateModel rootBundle = null;
            for (AbstractOrderEntryModel entry : contract.getOrderEntries()) {
                if( entry.getBundleTemplate() != null ) {
                    rootBundle = entry.getBundleTemplate().getParentTemplate();
                    break;
                }
            }

            if( rootBundle != null ) {
                BundleTemplateModel focusedComponent = null;
                for (BundleTemplateModel comp : rootBundle.getChildTemplates()) {
                    if( comp.getProducts().contains(product) ) {
                        focusedComponent = comp;
                        break;
                    }
                }
                if( focusedComponent != null ) {
                    return resolveContractUpdateUrl(product, contract, focusedComponent);
                }
            }
        }

        if( product instanceof ServicePlanModel ) {
            BundlePackageModel bpackage = getBundlePackage((ServicePlanModel) product);
            if( bpackage != null ) { // is part of package
                return resolveIfPartOfPackage(product, bpackage);
            }
            BundleTemplateModel bundle = getRootBundle((ServicePlanModel) product, BundleComponentType.CONDITIONAL);
            if( bundle != null ) { // is a bundled service plan
                return resolveIfBundleConditional(product, bundle);
            }
        }

        BundleTemplateModel bundle = getRootBundle(product, null);
        if( bundle != null ) { // is part of bundle
            return resolveIfPartOfBundle(product, bundle);
        }
        return resolveDefault(product);
    }

    protected String resolveIfPartOfBundle(ProductModel product, BundleTemplateModel bundle) {
        return "/packages/" + bundle.getId();
    }

    protected String resolveDefault(ProductModel product) {
        return "/instantcheckout/single/" + product.getCode();
    }

    protected String resolveIfBundleConditional(ProductModel product, BundleTemplateModel bundle) {
        return "/guidedselling/" + bundle.getId() + "/conditional/" + product.getCode();
    }

    protected String resolveIfPartOfPackage(ProductModel product, BundlePackageModel bpackage) {
        return "/guidedselling/" + bpackage.getBundleTemplate().getId() + "/package/" + bpackage.getCode();
    }

    protected String resolveContractUpdateUrl(ProductModel product, ContractModel contract, BundleTemplateModel focusedComponent) {
        return "/guidedselling/instantEdit?productCode=" + product.getCode() +
                "&bundleTemplateId=" + focusedComponent.getId() + "&bundleNo=1&removeOthers=true";
    }

    protected ContractModel getCurrentContract(CustomerModel customer) {
        return contractDao.getLatestContractForUser(customer);
    }

    protected BundleTemplateModel getRootBundle(ProductModel product, BundleComponentType type) {
        final Collection<BundleTemplateModel> bundleTemplates = product.getBundleTemplates();
        for (BundleTemplateModel bundleTemplate : bundleTemplates) {
            if( type == null ) {
                return bundleTemplate.getParentTemplate();
            }
            if( bundleTemplate.getBundleComponentType() == type) {
                return bundleTemplate.getParentTemplate();
            }
        }
        return null;
    }

    protected BundlePackageModel getBundlePackage(ServicePlanModel product) {
        final Collection<BundlePackageModel> packages = product.getPackages();
        if( packages.isEmpty() ) return null;
        return packages.iterator().next();
    }

    public ContractDao getContractDao() {
        return contractDao;
    }

    @Required
    public void setContractDao(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    public UserService getUserService() {
        return userService;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UrlResolver<ProductModel> getProductModelUrlResolver() {
        return productModelUrlResolver;
    }

    @Required
    public void setProductModelUrlResolver(UrlResolver<ProductModel> productModelUrlResolver) {
        this.productModelUrlResolver = productModelUrlResolver;
    }
}
