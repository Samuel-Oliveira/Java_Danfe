import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;

public class TesteImpressaoPdfArquivo {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = ImpressaoUtil.leArquivo("/d/teste/nfe.xml");

            //Aqui está pegando o Layout Padrão
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xml);

            //Faz a impressão em pdf File passando o caminho do Arquivo
            ImpressaoService.impressaoPdfArquivo(impressao, "/d/teste/teste-nfe.pdf");

        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}