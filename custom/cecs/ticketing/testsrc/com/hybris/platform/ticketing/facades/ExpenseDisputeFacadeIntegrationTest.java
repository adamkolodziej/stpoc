package com.hybris.platform.ticketing.facades;

import com.hybris.platform.ticketing.data.ExpenseDisputeData;
import com.hybris.platform.ticketing.data.TicketAreaData;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.data.TicketReasonData;
import com.hybris.platform.ticketing.enums.TicketArea;
import com.hybris.showcase.data.CurrentExpensesData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import static org.junit.Assert.*;

import de.hybris.platform.ticket.enums.CsEventReason;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by I323354
 */
public class ExpenseDisputeFacadeIntegrationTest extends ServicelayerTransactionalTest {
    private static final Logger LOG = Logger.getLogger(ExpenseDisputeFacadeIntegrationTest.class);

    @Resource
    protected ModelService modelService;

    @Resource
    protected ExpenseDisputeFacade expenseDisputeFacade;


    @Test
    public void testCreateDispute() {
        ExpenseDisputeData dispute = fakeExpenseDisputeData();
        ExpenseDisputeData savedDispute = expenseDisputeFacade.createDispute(dispute);

        assertNotNull(savedDispute);
        assertNotNull(savedDispute.getCsTicket());
        assertNotNull(savedDispute.getExpenseCode());

        LOG.debug("Created dispute through facade");
    }

    private ExpenseDisputeData fakeExpenseDisputeData() {
        ExpenseDisputeData d = new ExpenseDisputeData();
        d.setCode("test");
        d.setDescription("this is a test");
        d.setExpenseCode("1");
        d.setCsTicket(fakeTicketData());
        return d;
    }

    private TicketData fakeTicketData() {
        final TicketAreaData area = new TicketAreaData();
        area.setCode(TicketArea.EXPENSEDISPUTES.getCode());
        area.setName("Expense Disputes");
        TicketData td = new TicketData();

        td.setArea(area);
        final TicketReasonData reason = new TicketReasonData();
        reason.setCode(CsEventReason.COMPLAINT.getCode());
        reason.setCode("Complaint");
        td.setReason(reason);
        return td;
    }

    private CurrentExpensesData fakeExpense(){
        PriceData price = new PriceData();
        price.setCurrencyIso("EUR");
        price.setValue(BigDecimal.valueOf(20));

        CurrentExpensesData fakeExpense = new CurrentExpensesData();
        fakeExpense.setCode("1");
        fakeExpense.setName("A testing expense");
        fakeExpense.setType("Testing expense type");
        fakeExpense.setAmount(price);
        return fakeExpense;
    }

}
