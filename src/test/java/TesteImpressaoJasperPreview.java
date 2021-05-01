import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.view.JasperViewer;

public class TesteImpressaoJasperPreview {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = ImpressaoUtil.leArquivo("/d/teste/nfe.xml");

            //Aqui está pegando o Layout Padrão
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xml);

            //Faz a impressão e retorna um Jasper Preview
            JasperViewer jasperViewer = ImpressaoService.impressaoPreview(impressao);

            //PAra mostrar o Preview
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}