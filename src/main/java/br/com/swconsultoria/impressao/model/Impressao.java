package br.com.swconsultoria.impressao.model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Model resposnsavel por armazenar as informações da Impressao
 */
public class Impressao {

    private String xml;
    private InputStream jasper;
    private String pathExpression;
    private Map<String, Object> parametros;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public InputStream getJasper() {
        return jasper;
    }

    public void setJasper(InputStream jasper) {
        this.jasper = jasper;
    }

    public String getPathExpression() {
        return pathExpression;
    }

    public void setPathExpression(String pathExpression) {
        this.pathExpression = pathExpression;
    }

    public Map<String, Object> getParametros() {
        if(this.parametros == null){
            this.parametros = new HashMap<>();
        }
        return parametros;
    }
}