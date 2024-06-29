package br.com.swconsultoria.impressao.service;

import br.com.swconsultoria.impressao.exception.DanfeException;
import br.com.swconsultoria.impressao.model.Impressao;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.StringReader;

/**
 * Serviço principal para criar as Impressões
 */
public class ImpressaoService {

    private ImpressaoService(){}

    /**
     * Cria a impressão no caminho definido e no formato PDF
     * @param impressao
     * @param destinoPdf
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void impressaoPdfArquivo(Impressao impressao, String destinoPdf) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        JasperExportManager.exportReportToPdfFile(jasperPrint, destinoPdf);
    }

    /**
     * Cria a impressão Retornando o byte[]
     * @param impressao
     * @return
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static byte[] impressaoPdfByte(Impressao impressao) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    /**
     * Cria a impressão no caminho definido e no formato HTML
     * @param impressao
     * @param destinoHtml
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void impressaoHtml(Impressao impressao, String destinoHtml) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        JasperExportManager.exportReportToHtmlFile(jasperPrint, destinoHtml);
    }

    /**
     * Cria a impressão em um preview, use setVisible(true) para mostrar a janela
     * @param impressao
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static JasperViewer impressaoPreview(Impressao impressao) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        return new JasperViewer(jasperPrint, true);
    }

    /**
     * Cria a impressão direta na impressora padrão
     * @param impressao
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws PrinterException
     */
    public static void impressaoDireta(Impressao impressao) throws JRException, ParserConfigurationException, IOException, SAXException, PrinterException {
        PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
        impressaoDireta(impressao, impressoraPadrao, null);
    }

    /**
     * Cria a impressão direta na impressora padrão passando configuration
     * @param impressao
     * @param configuration
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws PrinterException
     */
    public static void impressaoDireta(Impressao impressao, SimplePrintServiceExporterConfiguration configuration) throws JRException, ParserConfigurationException, IOException, SAXException, PrinterException {
        PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
        impressaoDireta(impressao, impressoraPadrao, configuration);
    }

    /**
     * Cria a impressão direta na impressora informada
     * @param impressao
     * @param impressora
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws PrinterException
     */
    public static void impressaoDireta(Impressao impressao, PrintService impressora) throws JRException, ParserConfigurationException, IOException, SAXException, PrinterException {
        SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
        configuration.setPrintRequestAttributeSet(new HashPrintRequestAttributeSet());
        configuration.setDisplayPageDialog(false);
        configuration.setDisplayPrintDialog(false);

        impressaoDireta(impressao, impressora, configuration);
    }

    /**
     * Cria a impressão direta na impressora informada
     * @param impressao
     * @param impressora
     * @throws JRException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws PrinterException
     */
    public static void impressaoDireta(Impressao impressao, PrintService impressora, SimplePrintServiceExporterConfiguration configuration) throws JRException, ParserConfigurationException, IOException, SAXException, PrinterException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        if (impressora == null) {
            throw new DanfeException("Impressora não encontrada");
        }

        if (configuration == null) {
            configuration = new SimplePrintServiceExporterConfiguration();
            configuration.setPrintRequestAttributeSet(new HashPrintRequestAttributeSet());
            configuration.setDisplayPageDialog(false);
            configuration.setDisplayPrintDialog(false);
        }

        // Configurações de impressão
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(impressora);

        // Crie uma impressão Jasper a partir do objeto JasperPrint
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        configuration.setPrintService(impressora);
        exporter.setConfiguration(configuration);

        // Exportar e imprimir
        exporter.exportReport();
    }

    /**
     * Cria a impressão
     * @param impressao
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws JRException
     */
    public static JasperPrint geraImpressao(Impressao impressao) throws IOException, SAXException, ParserConfigurationException, JRException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(impressao.getXml())));
        JRDataSource xmlDataSource = new JRXmlDataSource(document, impressao.getPathExpression());
        return JasperFillManager.fillReport(impressao.getJasper(), impressao.getParametros(), xmlDataSource);
    }
}
