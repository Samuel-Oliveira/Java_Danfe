import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class TesteImpressaoModificado {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
//            String xml = ImpressaoUtil.leArquivo("/d/teste/nfe.xml");
            String xml = ImpressaoUtil.leArquivo("/d/teste/nfe.xml");

            //Aqui está pegando o Layout Padrão
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xml);
            impressao.setJasper(TesteImpressaoModificado.class.getResourceAsStream("/danfe-modificado.jasper"));
            impressao.getParametros().put("Logo", TesteImpressaoModificado.class.getResourceAsStream("/logo-java.png"));

            //Faz a impressão em pdf File
            impressaoPdfArquivo(impressao);
            System.out.println("Impressão Pdf Arquivo OK");

        }catch (Exception e){
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }

    private static void impressaoPdfArquivo(Impressao impressao) throws IOException, JRException, ParserConfigurationException, SAXException {
        ImpressaoService.impressaoPdfByte(impressao);
    }

}