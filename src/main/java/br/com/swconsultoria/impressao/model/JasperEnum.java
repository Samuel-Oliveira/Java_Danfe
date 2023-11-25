package br.com.swconsultoria.impressao.model;

import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.engine.JasperReport;

public enum JasperEnum {

    NFE("/nfe/danfe"),
    NFE_FATURA("/nfe/danfe_fatura"),
    NFCE("/nfce/danfce"),
    CTE("/cte/dacte"),
    CTE_PRESTACAO_SERVICO("/cte/subreport/DACTE_sub_prestacao_servico"),
    CTE_DOC_NF("/cte/subreport/DACTE_sub_documentos_originarios_nf"),
    CTE_DOC_NFE("/cte/subreport/DACTE_sub_documentos_originarios_nfe"),
    CTE_DOC_OUTROS("/cte/subreport/DACTE_sub_documentos_originarios_outros"),
    CTE_VEICULOS("/cte/subreport/DACTE_sub_veiculos_novos"),
    MDFE("/mdfe/damdfe");

    private final String caminho;

    JasperEnum(String caminho) {
        this.caminho = caminho;
    }

    public JasperReport getJasper() {
        String caminhoJasper = "/jasper" + caminho + ".jasper";
        return ImpressaoUtil.carregaJasperResources(caminhoJasper);
    }
}
