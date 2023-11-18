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
package br.com.swconsultoria.impressao.view;

import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import br.com.swconsultoria.impressao.util.Prefs;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Rodrigo Cananea <rodrigoaguiar35@gmail.com>
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();

        jbBuscar.putClientProperty(FlatClientProperties.STYLE_CLASS, "primary");
        jbDoc.putClientProperty(FlatClientProperties.STYLE_CLASS, "secondary-outlined");
        jbGithub.putClientProperty(FlatClientProperties.STYLE_CLASS, "secondary-outlined");
        jbTema.putClientProperty(FlatClientProperties.STYLE_CLASS, "secondary-outlined");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbDoc = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jbTema = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jbGithub = new javax.swing.JButton();
        jcbTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jbBuscar = new javax.swing.JButton();
        jcbSalvarPDF = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java-Danfe");
        setMinimumSize(new java.awt.Dimension(600, 325));
        setPreferredSize(new java.awt.Dimension(600, 325));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 18, 22));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 26)); // NOI18N
        jLabel1.setText("Java Danfe");

        jbDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Google Docs.png"))); // NOI18N
        jbDoc.setText("Documentação");
        jbDoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbDoc.setFocusPainted(false);
        jbDoc.setFocusable(false);
        jbDoc.setPreferredSize(new java.awt.Dimension(120, 35));
        jbDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDocActionPerformed(evt);
            }
        });

        jLabel2.setText("Esta biblioteca tem como propósito facilitar a impressão dos documentos NF-e, NFC-e e CT-e.");

        jbTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Paint Roller.png"))); // NOI18N
        jbTema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbTema.setFocusPainted(false);
        jbTema.setFocusable(false);
        jbTema.setPreferredSize(new java.awt.Dimension(45, 35));
        jbTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTemaActionPerformed(evt);
            }
        });

        jLabel3.setText("Caso tenha dúvidas sobre o seu funcionamento pode consultar os links abaixo.");

        jbGithub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/GitHub_1.png"))); // NOI18N
        jbGithub.setText("Github");
        jbGithub.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbGithub.setFocusPainted(false);
        jbGithub.setFocusable(false);
        jbGithub.setPreferredSize(new java.awt.Dimension(120, 35));
        jbGithub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGithubActionPerformed(evt);
            }
        });

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NFe - Nota Fiscal Eletrônica", "NFCe - Nota Fiscal Consumidor Eletrônica", "CTe - Conhecimento de Transporte Eletrônico" }));
        jcbTipo.setFocusable(false);
        jcbTipo.setMinimumSize(new java.awt.Dimension(300, 35));
        jcbTipo.setPreferredSize(new java.awt.Dimension(300, 35));

        jLabel4.setText("Tipo do XML:");

        jbBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search.png"))); // NOI18N
        jbBuscar.setText("Buscar");
        jbBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar.setFocusPainted(false);
        jbBuscar.setFocusable(false);
        jbBuscar.setPreferredSize(new java.awt.Dimension(100, 35));
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jcbSalvarPDF.setText("Salvar PDF");
        jcbSalvarPDF.setPreferredSize(new java.awt.Dimension(85, 35));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jbDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbGithub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbSalvarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSalvarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGithub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTemaActionPerformed
        alterarTema();
    }//GEN-LAST:event_jbTemaActionPerformed

    private void jbDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDocActionPerformed
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("https://java-brasil.netlify.app/danfe/");
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbDocActionPerformed

    private void jbGithubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGithubActionPerformed
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("https://github.com/Samuel-Oliveira/Java_Danfe");
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbGithubActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        String ultimaPasta = Prefs.getState().get(Prefs.LAST_FOLDER, FileSystemView.getFileSystemView().getHomeDirectory().getPath());
        JFileChooser fileChooser = new JFileChooser(ultimaPasta);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "Arquivos XML (*.xml)";
            }
        };

        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            Prefs.getState().put(Prefs.LAST_FOLDER, arquivoSelecionado.getParent());
            visualizar(arquivoSelecionado);
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void visualizar(File arquivo) {
        try {
            String xml = ImpressaoUtil.leArquivo(arquivo.getPath());

            Impressao impressao;

            switch (jcbTipo.getSelectedIndex()) {
                case 1: {
                    impressao = ImpressaoUtil.impressaoPadraoNFCe(xml, "www.consulta-sefaz-uf.com");
                    break;
                }
                case 2: {
                    impressao = ImpressaoUtil.impressaoPadraoCTe(xml);
                    break;
                }
                default: {
                    impressao = ImpressaoUtil.impressaoPadraoNFe(xml);
                    break;
                }
            }

            if (jcbSalvarPDF.isSelected()) {
                String ultimaPasta = Prefs.getState().get(Prefs.LAST_FOLDER, FileSystemView.getFileSystemView().getHomeDirectory().getPath());
                JFileChooser fileChooser = new JFileChooser(ultimaPasta);
                int result = fileChooser.showSaveDialog(this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();

                    byte[] bytes = ImpressaoService.impressaoPdfByte(impressao);
                    
                    saveByteArrayToPDF(bytes, fileToSave.getAbsolutePath() + ".pdf");
                    openFolderContainingFile(fileToSave.getAbsolutePath());
                } else {
                    System.out.println("Operação de salvamento cancelada pelo usuário.");
                }
            } else {
                JasperViewer jasperViewer = ImpressaoService.impressaoPreview(impressao);

                JDialog dialog = new JDialog(this, jcbTipo.getSelectedItem().toString(), true);

                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();
                int width = screenRect.width;
                int height = screenRect.height;

                dialog.setSize(width, height);

                GraphicsConfiguration gc = getGraphicsConfiguration();
                Rectangle bounds = gc.getBounds();
                dialog.setLocation(bounds.x + bounds.width / 2 - dialog.getWidth() / 2, bounds.y + bounds.height / 2 - dialog.getHeight() / 2);
                dialog.setResizable(true);

                dialog.setLayout(new BorderLayout());
                dialog.add(jasperViewer.getContentPane(), BorderLayout.CENTER);

                dialog.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Problemas ao realizar impressão do arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void saveByteArrayToPDF(byte[] byteArray, String outputPath) {
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            // Escreve o array de bytes no arquivo
            fos.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void openFolderContainingFile(String filePath) {
        try {
            // Obtém a pasta que contém o arquivo
            File file = new File(filePath);
            File parentFolder = file.getParentFile();

            // Abre a pasta no explorador de arquivos
            Desktop.getDesktop().open(parentFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void alterarTema() {
        if (!FlatLaf.isLafDark()) {
            Prefs.getState().put(Prefs.THEME, "escuro");
            EventQueue.invokeLater(() -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMacDarkLaf.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });
        } else {
            Prefs.getState().put(Prefs.THEME, "claro");
            EventQueue.invokeLater(() -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMacLightLaf.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });
        }
    }

    public static void main(String args[]) {
        Prefs.init("/Java-Danfe");

        FlatLaf.registerCustomDefaultsSource("theme");

        String tema = Prefs.getState().get(Prefs.THEME, "escuro");

        if ("claro".equals(tema)) {
            FlatMacLightLaf.setup();
        } else {
            FlatMacDarkLaf.setup();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbDoc;
    private javax.swing.JButton jbGithub;
    private javax.swing.JButton jbTema;
    private javax.swing.JCheckBox jcbSalvarPDF;
    private javax.swing.JComboBox<String> jcbTipo;
    // End of variables declaration//GEN-END:variables
}
