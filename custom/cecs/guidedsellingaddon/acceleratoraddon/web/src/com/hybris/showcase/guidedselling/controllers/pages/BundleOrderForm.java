package com.hybris.showcase.guidedselling.controllers.pages;

/**
 * Created by miroslaw.szot@sap.com on 2015-03-17.
 */
public class BundleOrderForm {
    private int bundleNo;
    private String rootBundleTemplateId;
    private String sourceProductCode;
    private String sourcePackageCode;

    public int getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(int bundleNo) {
        this.bundleNo = bundleNo;
    }

    public String getRootBundleTemplateId() {
        return rootBundleTemplateId;
    }

    public void setRootBundleTemplateId(String rootBundleTemplateId) {
        this.rootBundleTemplateId = rootBundleTemplateId;
    }

    public String getSourceProductCode() {
        return sourceProductCode;
    }

    public void setSourceProductCode(String sourceProductCode) {
        this.sourceProductCode = sourceProductCode;
    }

    public String getSourcePackageCode() {
        return sourcePackageCode;
    }

    public void setSourcePackageCode(String sourcePackageCode) {
        this.sourcePackageCode = sourcePackageCode;
    }
}
