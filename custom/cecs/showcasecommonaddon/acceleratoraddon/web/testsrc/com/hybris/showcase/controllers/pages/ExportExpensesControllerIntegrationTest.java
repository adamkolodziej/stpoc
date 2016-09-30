package com.hybris.showcase.controllers.pages;

import com.hybris.showcase.facades.ExpenseFacade;
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

public class ExportExpensesControllerIntegrationTest extends ServicelayerTransactionalTest {
    @Resource
    protected ModelService modelService;

    @Resource
    protected ExpenseFacade expenseFacade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        ExportExpensesController controller = new ExportExpensesController();
        super.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(controller);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testExportToPDF() throws Exception {

        MvcResult result = mockMvc.perform(
                get(ExportExpensesController.EXPORT_EXPENSES_REQUESTPATH)
                        .param("expenseCode", "0001", "0002", "0003")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();

        String tempDir = System.getProperty("java.io.tmpdir");
        FileOutputStream fos = new FileOutputStream(tempDir + "expenses_report_" + System.currentTimeMillis() + ".pdf");
        fos.write(result.getResponse().getContentAsByteArray());
        fos.close();
    }
}
