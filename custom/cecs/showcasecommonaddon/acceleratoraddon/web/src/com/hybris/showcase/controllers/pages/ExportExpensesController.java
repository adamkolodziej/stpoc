package com.hybris.showcase.controllers.pages;

import com.hybris.showcase.reports.ExpenseExportData;
import com.hybris.showcase.reports.ExpenseExportDataConverter;
import com.hybris.showcase.reports.ReportsManager;
import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.facades.ExpenseFacade;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import net.sf.jasperreports.engine.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by i323354 on 05/11/15.
 */
@Controller
@RequestMapping(ExportExpensesController.EXPORT_EXPENSES_REQUESTPATH)
public class ExportExpensesController {

    @Resource
    private ConfigurationService configurationService;

    private static final Logger LOG = Logger.getLogger(ExportExpensesController.class);

    public static final String EXPORT_EXPENSES_REQUESTPATH = "/my-account/exportExpenses";
    public static final String RESOURE_EXPORT_EXPENSES_REPORT_JRXML = "reports/ExportExpensesReport.jrxml";

    @Resource
    private ExpenseFacade expenseFacade;

    @RequestMapping(produces = "application/pdf", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<byte[]> exportExpenses(@ModelAttribute ExportExpensesForm form,
                                                 final BindingResult bindingResult,
                                                 final Model model,
                                                 final RedirectAttributes redirectAttributes) throws JRException, IOException {


        final String logoPath = configurationService.getConfiguration().getString("reports.logo.path");
        final BufferedImage image = ImageIO.read(getClass().getResource(logoPath));

        Map parameters = new HashMap();
        parameters.put("LOGO", image);

        Collection<ExpenseExportData> expenses = loadExpenseExportDataList(form.getExpenseCode());

        LOG.debug("loaded expenses list");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ReportsManager.executeToPdfStream(parameters, RESOURE_EXPORT_EXPENSES_REPORT_JRXML, expenses, out);
        byte[] pdfContents = out.toByteArray();

        Date today = new Date();
        String filename = String.format("expenses_%td_%tm_%ty.pdf", today, today, today);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.add("content-disposition", "inline;filename=" + filename);

        LOG.info("exported");

        ResponseEntity<byte[]> response = new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        return response;
    }

    public Collection<ExpenseExportData> loadExpenseExportDataList(String[] expenseCodes) {
        Collection<ExpenseExportData> exportList = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(expenseCodes)){
            for (String code : expenseCodes){
                ExpenseExportData data = loadExpenseExportData(code);
                if (data != null){
                    exportList.add(data);
                }
            }
        }

        return exportList;
    }

    public ExpenseExportData loadExpenseExportData(String expenseCode){
        CurrentExpensesData expense = expenseFacade.getExpenseForCode(expenseCode);
        if (expense != null){
            ExpenseExportDataConverter converter = new ExpenseExportDataConverter();
            return converter.convert(expense);
        }

        return null;
    }


    /**
     * Form bean used for expenses
     */
    public static class ExportExpensesForm{
        String[] expenseCode;

        public String[] getExpenseCode() {
            return expenseCode;
        }

        public void setExpenseCode(String[] expenseCode) {
            this.expenseCode = expenseCode;
        }
    }



}


