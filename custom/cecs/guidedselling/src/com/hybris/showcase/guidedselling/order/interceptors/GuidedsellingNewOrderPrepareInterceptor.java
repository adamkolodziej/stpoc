package com.hybris.showcase.guidedselling.order.interceptors;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.time.TimeService;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Sebastian Weiner on 2015-12-18.
 */
public class GuidedsellingNewOrderPrepareInterceptor implements PrepareInterceptor{

    private TimeService timeService;

    @Override
    public void onPrepare(final Object model, InterceptorContext ctx) throws InterceptorException {
        if (model instanceof OrderModel)
        {
            final OrderModel order = (OrderModel) model;
            if (ctx.isNew(order))
            {
                order.setDate(getTimeService().getCurrentTime());
            }
        }
    }

    public TimeService getTimeService() {
        return timeService;
    }

    @Required
    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }
}
