/*
 * The MIT License
 *
 * Copyright 2023 Rodrigo Cananea <rodrigoaguiar35@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.swconsultoria.impressao.util;

import java.io.IOException;
import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Rodrigo Cananea <rodrigoaguiar35@gmail.com>
 */
public class JasperResourceLoader {
    
    public JasperReport loadJasperFile(String category, String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        String filePath = "jasper/" + category + "/" + fileName;

        try (InputStream in = loader.getResourceAsStream(filePath)) {
            return (JasperReport) JRLoader.loadObject(in);
        } catch (IOException | JRException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
