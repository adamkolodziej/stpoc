package com.hybris.showcase.controllers.pages;

import com.hybris.showcase.controllers.pages.ExportInvoiceController;
import com.hybris.showcase.facades.ExpenseFacade;
import com.hybris.showcase.facades.InvoiceFacade;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.io.FileOutputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by i323354 on 05/11/15.
 */

public class ExportInvoiceControllerIntegrationTest extends ServicelayerTransactionalTest {
    @Resource
    protected ModelService modelService;

    @Resource
    protected InvoiceFacade invoiceFacade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        ExportInvoiceController controller = new ExportInvoiceController();
        super.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(controller);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testExportToPDF() throws Exception {

        MvcResult result = mockMvc.perform(
                get(ExportInvoiceController.EXPORT_INVOICE_REQUESTPATH)
                        .param("invoiceNumber", "0001")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();

        String tempDir = System.getProperty("java.io.tmpdir");
        FileOutputStream fos = new FileOutputStream(tempDir + "/invoice_" + System.currentTimeMillis() + ".pdf");
        fos.write(result.getResponse().getContentAsByteArray());
        fos.close();
    }
}
