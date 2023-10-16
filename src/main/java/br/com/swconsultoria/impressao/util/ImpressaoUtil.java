package br.com.swconsultoria.impressao.util;

import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Classe responsavel por armazenar os metodos uteis
 */
public class ImpressaoUtil {

    private ImpressaoUtil() {
    }

    /**
     * Verifica se um objeto é vazio.
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Optional<T> verifica(T obj) {
        if (obj == null) {
            return Optional.empty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty() ? Optional.empty() : Optional.of(obj);
        }

        final String s = String.valueOf(obj).trim();

        return s.isEmpty() || s.equalsIgnoreCase("null") ? Optional.empty() : Optional.of(obj);
    }

    /**
     * Le o Arquivo e retona String do conteudo
     *
     * @return String
     * @throws IOException
     */
    public static String leArquivo(String caminhoArquivo) throws IOException {
        if (!Files.exists(Paths.get(
                verifica(caminhoArquivo)
                        .orElseThrow(() -> new IllegalArgumentException("Arquivo não pode ser nulo/vazio."))))) {
            throw new FileNotFoundException("Arquivo " + caminhoArquivo + " não encontrado.");
        }
        List<String> list = Files.readAllLines(Paths.get(caminhoArquivo));
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        list.forEach(joiner::add);

        return joiner.toString();
    }

    public static JasperReport loadReport(String fileName) {
        try (InputStream in = ImpressaoUtil.class.getResourceAsStream(fileName)) {
            return (JasperReport) JRLoader.loadObject(in);
        } catch (IOException | JRException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Gera Objeto padrão para impressão da NFe
     *
     * @return
     */
    public static Impressao impressaoPadraoNFe(String xml) {
        JasperResourceLoader loader = new JasperResourceLoader();
        Impressao impressaoNFe = new Impressao();
        impressaoNFe.setXml(xml);
        impressaoNFe.setPathExpression("/nfeProc/NFe/infNFe/det");
        impressaoNFe.setJasper(loader.loadJasperFile("nfe", "danfe.jasper"));
        impressaoNFe.getParametros().put("Logo", ImpressaoService.class.getResourceAsStream("/img/nfe.jpg"));
        impressaoNFe.getParametros().put("SUBREPORT", loader.loadJasperFile("nfe", "danfe_fatura.jasper"));
        return impressaoNFe;
    }

    /**
     * Gera Objeto padrão para impressão da NFCe
     *
     * @return
     */
    public static Impressao impressaoPadraoNFCe(String xml, String urlConsulta) {
        JasperResourceLoader loader = new JasperResourceLoader();
        Impressao impressaoNFCe = new Impressao();
        impressaoNFCe.setXml(xml);
        impressaoNFCe.setPathExpression("/");
        impressaoNFCe.setJasper(loader.loadJasperFile("nfce", "danfce.jasper"));
        impressaoNFCe.getParametros().put("Logo", ImpressaoService.class.getResourceAsStream("/img/nfe.jpg"));
        impressaoNFCe.getParametros().put("UrlConsulta", urlConsulta);
        return impressaoNFCe;
    }

    /**
     * Gera Objeto padrão para impressão da CTe
     *
     * @return
     */
    public static Impressao impressaoPadraoCTe(String xml) {
        JasperResourceLoader loader = new JasperResourceLoader();
        Impressao impressaoCTe = new Impressao();
        impressaoCTe.setXml(xml);
        impressaoCTe.setPathExpression("/");
        impressaoCTe.setJasper(loader.loadJasperFile("cte", "dacte.jasper"));

        impressaoCTe.getParametros().put("SubPrestacaoServico", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport/DACTE_sub_prestacao_servico.jasper"));
        impressaoCTe.getParametros().put("SubDocumentosOriginariosNF", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport/DACTE_sub_documentos_originarios_nf"
                + ".jasper"));
        impressaoCTe.getParametros().put("SubDocumentosOriginariosNFe", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport/DACTE_sub_documentos_originarios_nfe"
                + ".jasper"));
        impressaoCTe.getParametros().put("SubDocumentosOriginariosOutros", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport"
                + "/DACTE_sub_documentos_originarios_outros.jasper"));
        impressaoCTe.getParametros().put("SubVeiculosNovos", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport"
                + "/DACTE_sub_veiculos_novos.jasper"));
        return impressaoCTe;
    }

}
