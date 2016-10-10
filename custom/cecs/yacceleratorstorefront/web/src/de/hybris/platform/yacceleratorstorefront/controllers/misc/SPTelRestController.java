package de.hybris.platform.yacceleratorstorefront.controllers.misc;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.GenericSearchConstants;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.services.BaseStoreService;
import java.lang.Exception;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zkoss.util.logging.Log;

/**
 * Created by Adam on 2016-10-06.
 */
@RestController
@Scope("tenant")
@RequestMapping("/rest")
public class SPTelRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private BaseStoreService baseStoreService;

    protected static final Logger LOG = Logger.getLogger(SPTelRestController.class);

    @RequestMapping(value = "/orders/{orderCode}/{orderStatus}", method = RequestMethod.POST)
    boolean updateOrderStatus(@PathVariable String orderCode, @PathVariable String orderStatus) {
        try {
            OrderModel order = customerAccountService.getOrderForCode(orderCode, baseStoreService.getCurrentBaseStore());
            order.setStatus(OrderStatus.valueOf(orderStatus));
            orderService.saveOrder(order);
            modelService.save(order);
            return true;
        } catch (Exception ex){
            LOG.info(ex.getMessage());
            return false;
        }

    }

    @RequestMapping(value = "/orders/status/{orderCode}", method = RequestMethod.GET)
    String getOrderStatus(@PathVariable String orderCode) {
        try {
            OrderModel order = customerAccountService.getOrderForCode(orderCode, baseStoreService.getCurrentBaseStore());
            return order.getStatusDisplay();
        } catch (Exception ex){
            LOG.info(ex.getMessage());
            return "ERROR_WHILE_GETTING_STATUS";
        }
    }

    @RequestMapping(value = "/orders/{orderCode}", method = RequestMethod.GET)
    OrderModel getOrder(@PathVariable String orderCode) {
        try {
            OrderModel order = customerAccountService.getOrderForCode(orderCode, baseStoreService.getCurrentBaseStore());
            return order;
        } catch (Exception ex){
            LOG.info(ex.getMessage());
            return null;
        }
    }
}