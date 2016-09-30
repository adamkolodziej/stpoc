package com.hybris.showcase.guidedselling.controllers.pages;

/**
 * Created by I303845 on 2015-02-04.
 */
public class OptionRemoveRequest extends BundleRequest {
    private int entryNumber;

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }
}
