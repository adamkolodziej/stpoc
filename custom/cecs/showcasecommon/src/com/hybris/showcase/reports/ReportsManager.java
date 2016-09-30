package com.hybris.showcase.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

/**
 * Created by i323354 on 06/11/15.
 */
public class ReportsManager {


    /**
     * Executes the report and returns an output stream containing the PDF
     *
     * @param parameters
     * @param reportResourceName
     * @param beanList
     * @return
     * @throws JRException
     */
    public static void executeToPdfStream(Map parameters,
                                          String reportResourceName,
                                          Collection beanList,
                                          OutputStream out
    ) throws JRException, IOException {
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanList);

        InputStream reportInputStream = JRLoader.getResourceInputStream(reportResourceName);
        JasperReport report = JasperCompileManager.compileReport(reportInputStream);

        JasperPrint print  = JasperFillManager.fillReport(report, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfStream(print, out);

/*        byte[] pdfBytes = JasperRunManager.runReportToPdf(reportInputStream, parameters, beanColDataSource);
        out.write(pdfBytes);*/
    }
}
