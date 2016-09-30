package com.hybris.showcase.guidedselling.controllers.pages;

/**
 * Created by I303845 on 2015-02-04.
 */
public class OptionAddRequest extends BundleRequest {
    private boolean removeOthers;

    public boolean getRemoveOthers() {
        return removeOthers;
    }

    public void setRemoveOthers(boolean removeOthers) {
        this.removeOthers = removeOthers;
    }
}
