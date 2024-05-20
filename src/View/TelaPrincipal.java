/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author ricas
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    
    private final Usuario usuarioLogado; // Variável para armazenar o usuário logado
    
    public TelaPrincipal(Usuario usuario) {
        usuarioLogado = usuario;
        initComponents();
        bemvindo.setText("Bem-vindo, " + usuarioLogado.getNome()); // Define o texto de boas-vindas
        
        if ("admin".equals(usuarioLogado.getPermissao())) {
            btnGerenciarUsuarios.setEnabled(true);
            btnGerenciarUsuarios.setVisible(true);
            
            btnAdicionarUsuario.setEnabled(true);
            btnAdicionarUsuario.setVisible(true);
            
            labelUsuario.setEnabled(true);
            labelUsuario.setVisible(true);

            btnAdicionarProduto.setEnabled(true);
            btnAdicionarProduto.setVisible(true);
            
            btnRelatorioAdm.setEnabled(true);
            btnRelatorioAdm.setVisible(true);
        } else {
            btnGerenciarUsuarios.setEnabled(false);
            btnGerenciarUsuarios.setVisible(false);
            
            btnAdicionarUsuario.setEnabled(false);
            btnAdicionarUsuario.setVisible(false);
            
            labelUsuario.setEnabled(false);
            labelUsuario.setVisible(false);

            btnAdicionarProduto.setEnabled(false);
            btnAdicionarProduto.setVisible(false);
            
            btnRelatorioAdm.setEnabled(false);
            btnRelatorioAdm.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bemvindo = new javax.swing.JLabel();
        btnVerEstoque = new javax.swing.JButton();
        btnAdicionarProduto = new javax.swing.JButton();
        btnProdutosEmFalta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        btnGerenciarUsuarios = new javax.swing.JButton();
        btnAdicionarUsuario = new javax.swing.JButton();
        btnRelatorioAdm = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Inicial - Null Alliance");

        bemvindo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bemvindo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnVerEstoque.setText("Ver Estoque");
        btnVerEstoque.setMaximumSize(new java.awt.Dimension(93, 23));
        btnVerEstoque.setPreferredSize(new java.awt.Dimension(93, 23));
        btnVerEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEstoqueActionPerformed(evt);
            }
        });

        btnAdicionarProduto.setText("Adicionar Produto");
        btnAdicionarProduto.setEnabled(false);
        btnAdicionarProduto.setPreferredSize(new java.awt.Dimension(93, 23));
        btnAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProdutoActionPerformed(evt);
            }
        });

        btnProdutosEmFalta.setText("Ver Produtos em Falta");
        btnProdutosEmFalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutosEmFaltaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Produtos");

        labelUsuario.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setText("Usuarios");

        btnGerenciarUsuarios.setText("Gerenciar Usuários");
        btnGerenciarUsuarios.setEnabled(false);
        btnGerenciarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarUsuariosActionPerformed(evt);
            }
        });

        btnAdicionarUsuario.setText("Adicionar Usuário");
        btnAdicionarUsuario.setEnabled(false);
        btnAdicionarUsuario.setPreferredSize(new java.awt.Dimension(93, 23));
        btnAdicionarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarUsuarioActionPerformed(evt);
            }
        });

        btnRelatorioAdm.setText("Relatório de Administrador");
        btnRelatorioAdm.setEnabled(false);
        btnRelatorioAdm.setPreferredSize(new java.awt.Dimension(93, 23));
        btnRelatorioAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioAdmActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProdutosEmFalta, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(btnGerenciarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRelatorioAdm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(232, Short.MAX_VALUE))
            .addComponent(bemvindo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(bemvindo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnVerEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProdutosEmFalta)
                .addGap(18, 18, 18)
                .addComponent(btnAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRelatorioAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(labelUsuario)
                .addGap(18, 18, 18)
                .addComponent(btnGerenciarUsuarios)
                .addGap(18, 18, 18)
                .addComponent(btnAdicionarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void btnProdutosEmFaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutosEmFaltaActionPerformed
        GerenciaProduto produto = new GerenciaProduto(possuiAdmin(), true);
        produto.setVisible(true);
        produto.carregaTabela();
    }//GEN-LAST:event_btnProdutosEmFaltaActionPerformed

    private void btnGerenciarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarUsuariosActionPerformed
        if (usuarioLogado != null && "admin".equals(usuarioLogado.getPermissao())) {
            // Se estiver logado, abrir a tela de gerenciamento de alunos
            GerenciaUsuario objeto = new GerenciaUsuario();
            objeto.setVisible(true);
            objeto.carregaTabela();
        } else {
            // Se não estiver logado, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(this, "Permissão insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGerenciarUsuariosActionPerformed

    private void btnAdicionarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarUsuarioActionPerformed
        if (usuarioLogado != null && "admin".equals(usuarioLogado.getPermissao())) {
            // Se estiver logado, abrir a tela de gerenciamento de alunos
            CadastroUsuario objeto = new CadastroUsuario();
            objeto.setVisible(true);
        } else {
            // Se não estiver logado, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(this, "Permissão insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAdicionarUsuarioActionPerformed

    private void btnVerEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEstoqueActionPerformed
        GerenciaProduto produto = new GerenciaProduto(possuiAdmin(), false);
        produto.setVisible(true);
        produto.carregaTabela();
    }//GEN-LAST:event_btnVerEstoqueActionPerformed

    private void btnRelatorioAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioAdmActionPerformed
        RelatorioAdministrador relatorio = new RelatorioAdministrador();
        relatorio.setVisible(true);
    }//GEN-LAST:event_btnRelatorioAdmActionPerformed

    private boolean possuiAdmin() {
        return this.usuarioLogado.getPermissao().equals("admin");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bemvindo;
    private javax.swing.JButton btnAdicionarProduto;
    private javax.swing.JButton btnAdicionarUsuario;
    private javax.swing.JButton btnGerenciarUsuarios;
    private javax.swing.JButton btnProdutosEmFalta;
    private javax.swing.JButton btnRelatorioAdm;
    private javax.swing.JButton btnVerEstoque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelUsuario;
    // End of variables declaration//GEN-END:variables
}
