package com.hybris.showcase.guidedselling.controllers.pages;

/**
 * Created by I303845 on 2015-02-02.
 */
public class BundleRequest {
    private String productCode;
    private String bundleTemplateId;
    private int bundleNo;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBundleTemplateId() {
        return bundleTemplateId;
    }

    public void setBundleTemplateId(String bundleTemplateId) {
        this.bundleTemplateId = bundleTemplateId;
    }

    public int getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(int bundleNo) {
        this.bundleNo = bundleNo;
    }
}
