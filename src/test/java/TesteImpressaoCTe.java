import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.engine.JRException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TesteImpressaoCTe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = ImpressaoUtil.leArquivo("d:/teste/cte.xml");

            //Aqui está pegando o Layout Padrão
            Impressao impressaoNFe = ImpressaoUtil.impressaoPadraoCTe(xml);

            //Faz a impressão em pdf File
            impressaoPdfArquivo(impressaoNFe);
            System.out.println("Impressão Pdf Arquivo OK");
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }

    private static void impressaoPdfArquivo(Impressao impressao) throws IOException, JRException, ParserConfigurationException, SAXException {
        ImpressaoService.impressaoPdfArquivo(impressao, "d:/teste/teste-cte.pdf");
    }
}