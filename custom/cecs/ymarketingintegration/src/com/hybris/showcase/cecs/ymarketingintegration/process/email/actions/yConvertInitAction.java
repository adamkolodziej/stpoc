package com.hybris.showcase.cecs.ymarketingintegration.process.email.actions;

import com.hybris.showcase.cecs.ymarketingintegration.process.email.context.YConvertEmailContext;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.processengine.model.BusinessProcessParameterModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.processengine.action.AbstractAction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * CECS-548 migrate seewhy e-mails into hybris e-mail process<br />
 * 
 * Created by miroslaw.szot@sap.com on 2015-09-02.
 */
public class yConvertInitAction extends AbstractAction<StoreFrontCustomerProcessModel> {
    private static final Logger LOG = Logger.getLogger(yConvertInitAction.class);

    public static final String CONTINUE = "CONTINUE";
    public static final String END = "END";
    public static final String NOK = "NOK";

    private FlexibleSearchService flexibleSearchService;

    public final String execute(StoreFrontCustomerProcessModel process) throws RetryLaterException, Exception {
        try {

            BusinessProcessParameterModel yConvertStep = process.getContextParameters().stream().filter(p -> p.getName().equals(YConvertEmailContext.Y_CONVERT_STEP)).findFirst().orElse(null);

            if (yConvertStep == null) {
                yConvertStep = new BusinessProcessParameterModel();
                yConvertStep.setName(YConvertEmailContext.Y_CONVERT_STEP);
                yConvertStep.setValue(1);
                yConvertStep.setProcess(process);

                final Collection<BusinessProcessParameterModel> parameters = new ArrayList<BusinessProcessParameterModel>(process.getContextParameters());
                parameters.add(yConvertStep);
                process.setContextParameters(parameters);
                getModelService().save(process);

                final long delay = TimeUnit.MINUTES.toMillis(1);
                LOG.info("yConversion e-mail campaign, step: 1, waiting " + delay + " milliseconds");
                Thread.sleep(delay);
            } else {
                int step = (Integer) yConvertStep.getValue() + 1;
                if (step > 3) {
                    LOG.info("Finishing yConversion e-mail campaign");
                    return END;
                } else {
                    yConvertStep.setValue(step);
                    getModelService().save(yConvertStep);
                    final long delay = TimeUnit.SECONDS.toMillis(30);
                    LOG.info("yConversion e-mail campaign, step: " + step + ", waiting " + delay + " milliseconds");
                    Thread.sleep(delay);
                }
            }

            if( shouldContinue(process, yConvertStep) ) {
                return CONTINUE;
            }

            LOG.info("yConversion e-mail campaign, step: " + yConvertStep.getValue() + ", has been stopped");
            return END;
        } catch (InterruptedException | ModelSavingException e ) {
            LOG.error("unable to send yConvert e-mail: ", e);
            return NOK;
        }
    }

    private boolean shouldContinue(StoreFrontCustomerProcessModel process, BusinessProcessParameterModel yConvertStep) {
        final FlexibleSearchQuery fsq = new FlexibleSearchQuery("SELECT count({o:pk}) FROM {Order as o} WHERE {o:creationtime} > ?startDate");
        fsq.addQueryParameter("startDate", process.getCreationtime());
        fsq.setResultClassList(Arrays.asList(Long.class));
        final List<Long> result = flexibleSearchService.<Long>search(fsq).getResult();

        return result.get(0).longValue() == 0; //if no orders were created after business process started, continue process
    }

    @Override
    public Set<String> getTransitions() {
        return createTransitions(CONTINUE, END, NOK);
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    @Required
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
