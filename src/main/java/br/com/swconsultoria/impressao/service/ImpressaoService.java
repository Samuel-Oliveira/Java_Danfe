package br.com.swconsultoria.impressao.service;

import br.com.swconsultoria.impressao.model.Impressao;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Serviço principal para criar as Impressões
 */
public class ImpressaoService {

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

    private static JasperPrint geraImpressao(Impressao impressao) throws IOException, SAXException, ParserConfigurationException, JRException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(impressao.getXml())));
        JRDataSource xmlDataSource = new JRXmlDataSource(document, impressao.getPathExpression());
        return JasperFillManager.fillReport(impressao.getJasper(), impressao.getParametros(), xmlDataSource);
    }
}
