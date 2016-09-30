package com.hybris.showcase.guidedselling.facades;

import java.util.Date;

/**
 * @author Sebastian Weiner on 2016-02-01.
 */
public interface ContractOptionsFacade {

    boolean isStartDateSetForCart();

    Date getContractStartDate();

    Date initContractStartDate();

    void changeContractStartDate(Date startDate);
}
