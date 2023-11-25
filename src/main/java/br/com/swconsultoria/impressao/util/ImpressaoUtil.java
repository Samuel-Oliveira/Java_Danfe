package br.com.swconsultoria.impressao.util;

import br.com.swconsultoria.impressao.exception.DanfeException;
import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.model.JasperEnum;
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

import static br.com.swconsultoria.impressao.util.ConstantesUtil.*;

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

    public static JasperReport carregaJasperResources(String caminhoJasper) {
        try (InputStream in = ImpressaoUtil.class.getResourceAsStream(caminhoJasper)) {
            if(in == null){
                throw new DanfeException(String.format("Jasper não encontrado %s", caminhoJasper));
            }
            return (JasperReport) JRLoader.loadObject(in);
        } catch (IOException | JRException e) {
            throw new DanfeException(String.format("Erro ao carregar Jasper %s", caminhoJasper),e);
        }
    }

    /**
     * Gera Objeto padrão para impressão da NFe
     *
     * @return
     */
    public static Impressao impressaoPadraoNFe(String xml) {
        Impressao impressaoNFe = new Impressao();
        impressaoNFe.setXml(xml);
        impressaoNFe.setPathExpression(PATH_NFE);
        impressaoNFe.setJasper(JasperEnum.NFE.getJasper());
        impressaoNFe.getParametros().put(PARAM_LOGO_NFE, ImpressaoService.class.getResourceAsStream(PATH_LOGO_NFE));
        impressaoNFe.getParametros().put("SUBREPORT", JasperEnum.NFE_FATURA.getJasper());
        return impressaoNFe;
    }

    /**
     * Gera Objeto padrão para impressão da NFCe
     *
     * @return
     */
    public static Impressao impressaoPadraoNFCe(String xml, String urlConsulta) {
        Impressao impressaoNFCe = new Impressao();
        impressaoNFCe.setXml(xml);
        impressaoNFCe.setPathExpression(PATH_NFCE);
        impressaoNFCe.setJasper(JasperEnum.NFCE.getJasper());
        impressaoNFCe.getParametros().put(PARAM_LOGO_NFCE, ImpressaoService.class.getResourceAsStream(PATH_LOGO_NFCE));
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
        impressaoCTe.setPathExpression(PATH_CTE);
        impressaoCTe.setJasper(JasperEnum.CTE.getJasper());
        impressaoCTe.getParametros().put("SubPrestacaoServico",JasperEnum.CTE_PRESTACAO_SERVICO.getJasper());
        impressaoCTe.getParametros().put("SubDocumentosOriginariosNF", JasperEnum.CTE_DOC_NF.getJasper());
        impressaoCTe.getParametros().put("SubDocumentosOriginariosNFe", JasperEnum.CTE_DOC_NFE.getJasper());
        impressaoCTe.getParametros().put("SubDocumentosOriginariosOutros", JasperEnum.CTE_DOC_OUTROS.getJasper());
        impressaoCTe.getParametros().put("SubVeiculosNovos", JasperEnum.CTE_VEICULOS.getJasper());
        impressaoCTe.getParametros().put(PARAM_LOGO_CTE, ImpressaoService.class.getResourceAsStream(PATH_LOGO_CTE));
        return impressaoCTe;
    }

    /**
     * Gera Objeto padrão para impressão da MDFe
     *
     * @return
     */
    public static Impressao impressaoPadraoMDFe(String xml) {
        Impressao impressaoMDFe = new Impressao();
        impressaoMDFe.setXml(xml);
        impressaoMDFe.setPathExpression(PATH_MDFE);
        impressaoMDFe.setJasper(JasperEnum.MDFE.getJasper());
        impressaoMDFe.getParametros().put(PARAM_LOGO_MDFE, ImpressaoService.class.getResourceAsStream(PATH_LOGO_MDFE));
        return impressaoMDFe;
    }

}
