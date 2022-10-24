package br.com.swconsultoria.impressao.util;

import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Classe responsavel por armazenar os metodos uteis
 */
public class ImpressaoUtil {

    /**
     * Verifica se um objeto é vazio.
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Optional<T> verifica(T obj) {
        if (obj == null)
            return Optional.empty();
        if (obj instanceof Collection)
            return ((Collection<?>) obj).size() == 0 ? Optional.empty() : Optional.of(obj);

        final String s = String.valueOf(obj).trim();

        return s.length() == 0 || s.equalsIgnoreCase("null") ? Optional.empty() : Optional.of(obj);
    }

    /**
     * Le o Arquivo e retona String do conteudo
     *
     * @return String
     * @throws IOException
     */
    public static String leArquivo(String caminhoArquivo) throws IOException {

        verifica(caminhoArquivo).orElseThrow(() -> new IllegalArgumentException("Arquivo não pode ser nulo/vazio."));
        if (!Files.exists(Paths.get(caminhoArquivo))) {
            throw new FileNotFoundException("Arquivo " + caminhoArquivo + " não encontrado.");
        }
        List<String> list = Files.readAllLines(Paths.get(caminhoArquivo));
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        list.forEach(joiner::add);

        return joiner.toString();
    }

    /**
     * Gera Objeto padrão para impressão da NFe
     *
     * @return
     */
    public static Impressao impressaoPadraoNFe(String xml) {
        Impressao impressaoNFe = new Impressao();
        impressaoNFe.setXml(xml);
        impressaoNFe.setPathExpression("/nfeProc/NFe/infNFe/det");
        impressaoNFe.setJasper(ImpressaoUtil.class.getResourceAsStream("/jasper/nfe/danfe.jasper"));
        impressaoNFe.getParametros().put("Logo", ImpressaoService.class.getResourceAsStream("/img/nfe.jpg"));
        impressaoNFe.getParametros().put("SUBREPORT", ImpressaoService.class.getResourceAsStream("/jasper/nfe/danfe_fatura.jasper"));
        return impressaoNFe;
    }

    /**
     * Gera Objeto padrão para impressão da NFCe
     *
     * @return
     */
    public static Impressao impressaoPadraoNFCe(String xml,String urlConsulta) {
        Impressao impressaoNFCe = new Impressao();
        impressaoNFCe.setXml(xml);
        impressaoNFCe.setPathExpression("/");
        impressaoNFCe.setJasper(ImpressaoUtil.class.getResourceAsStream("/jasper/nfce/danfce.jasper"));
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
        Impressao impressaoCTe = new Impressao();
        impressaoCTe.setXml(xml);
        impressaoCTe.setPathExpression("/");
        impressaoCTe.setJasper(ImpressaoUtil.class.getResourceAsStream("/jasper/cte/dacte.jasper"));

        impressaoCTe.getParametros().put("SubPrestacaoServico", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport/DACTE_sub_prestacao_servico.jasper"));
        impressaoCTe.getParametros().put("SubDocumentosOriginariosNF", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport/DACTE_sub_documentos_originarios_nf" +
                ".jasper"));
        impressaoCTe.getParametros().put("SubDocumentosOriginariosNFe", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport/DACTE_sub_documentos_originarios_nfe" +
                ".jasper"));
        impressaoCTe.getParametros().put("SubDocumentosOriginariosOutros", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport" +
                "/DACTE_sub_documentos_originarios_outros.jasper"));
        impressaoCTe.getParametros().put("SubVeiculosNovos", ImpressaoService.class.getResourceAsStream("/jasper/cte/subreport" +
                "/DACTE_sub_veiculos_novos.jasper"));
        return impressaoCTe;
    }

}
