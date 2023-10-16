    import br.com.swconsultoria.impressao.model.Impressao;
    import br.com.swconsultoria.impressao.service.ImpressaoService;
    import br.com.swconsultoria.impressao.util.ImpressaoUtil;

    public class TesteImpressaoModificado {

        public static void main(String[] args) {
            try {
                //Faça a leitura do Arquivo
                String xml = ImpressaoUtil.leArquivo("D:\\teste\\nfe.xml");

                //Aqui está pegando o Layout Padrão
                Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xml);
                
                //Vc pode informar qlq jasper da sua escolha
                impressao.setJasper(ImpressaoUtil.loadReport("danfe-modificado.jasper"));
                //Para trocar a Logo
                impressao.getParametros().put("Logo", TesteImpressaoModificado.class.getResourceAsStream("/logo-java.png"));

                //Faz a impressão
                byte[] bytes = ImpressaoService.impressaoPdfByte(impressao);

            } catch (Exception e) {
                //Trate seus erros aqui
                e.printStackTrace();
            }
        }
    }
