import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.engine.JRException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class TesteImpressaoNFCe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = ImpressaoUtil.leArquivo("/d/teste/nfce.xml");

            //Informe a Url de Consulta do NFCe de seu Estado
            String urlConsulta = "www.sefaznet.ac.gov.br/nfce/consulta";
            //Aqui está pegando o Layout Padrão
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFCe(xml,urlConsulta);

            //Faz a impressão em pdf File
            impressaoPdfArquivo(impressao);
            System.out.println("Impressão Pdf Arquivo OK");

            //Faz a impressão em pdf byte
            impressao = ImpressaoUtil.impressaoPadraoNFCe(xml,urlConsulta);
            System.out.println("Impressão Pdf Byte OK: "+ Arrays.toString(impressaoPdfByte(impressao)));

            //Faz a impressão em pdf Html
            impressao = ImpressaoUtil.impressaoPadraoNFCe(xml,urlConsulta);
            impressaoHtml(impressao);
            System.out.println("Impressão Pdf Html OK");
        }catch (Exception e){
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }

    private static void impressaoPdfArquivo(Impressao impressao) throws IOException, JRException, ParserConfigurationException, SAXException {
        ImpressaoService.impressaoPdfArquivo(impressao, "/d/teste/teste-nfce.pdf");
    }

    private static byte[] impressaoPdfByte(Impressao impressao) throws IOException, JRException, ParserConfigurationException, SAXException {
        return ImpressaoService.impressaoPdfByte(impressao);
    }

    private static void impressaoHtml(Impressao impressao) throws IOException, JRException, ParserConfigurationException, SAXException {
        ImpressaoService.impressaoHtml(impressao, "/d/teste/teste-nfce.html");
    }
}