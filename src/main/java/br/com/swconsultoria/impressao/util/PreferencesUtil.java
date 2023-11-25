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

import java.util.prefs.Preferences;

/**
 *
 * @author Rodrigo Cananea <rodrigoaguiar35@gmail.com>
 */
public class PreferencesUtil {

    private PreferencesUtil(){}
    
    private static Preferences state;
    
    public static Preferences getState() {
        return state;   
    }

    public static void init(String rootPath) {
        state = Preferences.userRoot().node(rootPath);
    }
    
    public static final String THEME = "java-danfe.tema";
    public static  final String LAST_FOLDER = "java-danfe.ultima-pasta";
}
