package View;

import DAO.UsuarioDAO;
import Model.Usuario;
import Result.Resultado;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GerenciaUsuario extends javax.swing.JFrame {

    private final UsuarioDAO usuarioDAO;
    private ArrayList<Usuario> listaUsuarios;
    private String nomeAntigo = "";

    public GerenciaUsuario() {
        initComponents();
        this.usuarioDAO = new UsuarioDAO();
        this.carregaTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        b_cancelar = new javax.swing.JButton();
        b_alterar = new javax.swing.JButton();
        b_apagar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        c_nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        c_email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        c_senha = new javax.swing.JPasswordField();
        c_permissao = new javax.swing.JComboBox<>();

        setTitle("Gerenciamento de Usuários");
        setResizable(false);

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Email", "Permissão", "Senha", "Data Cadastro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsuarios);
        if (jTableUsuarios.getColumnModel().getColumnCount() > 0) {
            jTableUsuarios.getColumnModel().getColumn(0).setMinWidth(15);
            jTableUsuarios.getColumnModel().getColumn(0).setMaxWidth(30);
            jTableUsuarios.getColumnModel().getColumn(1).setMinWidth(150);
            jTableUsuarios.getColumnModel().getColumn(2).setMinWidth(60);
            jTableUsuarios.getColumnModel().getColumn(3).setMinWidth(50);
        }

        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        b_alterar.setText("Alterar");
        b_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_alterarActionPerformed(evt);
            }
        });

        b_apagar.setText("Apagar");
        b_apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_apagarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jLabel2.setText("Email: ");

        jLabel3.setText("Permissão: ");

        jLabel4.setText("Senha: ");

        c_permissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "admin", "normal" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b_cancelar)
                                .addGap(18, 18, 18)
                                .addComponent(b_alterar)
                                .addGap(18, 18, 18)
                                .addComponent(b_apagar))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(c_senha))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(c_permissao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(c_email))
                                .addComponent(c_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(c_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(c_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(c_permissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(c_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_cancelar)
                    .addComponent(b_alterar)
                    .addComponent(b_apagar))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Gerenciamento de Usuários");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_alterarActionPerformed

        try {
            int id = 0;
            String nome = "";
            String email = "";
            String permissao = "";
            String senha = "";

            if (this.c_nome.getText().length() < 2) {
                throw new Exception("Nome deve conter ao menos 2 caracteres.");
            } else {
                nome = this.c_nome.getText();
            }

            if (this.c_email.getText().length() <= 0) {
                throw new Exception("Email não pode ser vazio.");
            } else {
                email = this.c_email.getText();
            }

            if (this.c_permissao.getSelectedItem().toString().equals("-")) {
                throw new Exception("Selecione uma permissão válida.");
            } else {
                permissao = this.c_permissao.getSelectedItem().toString();
            }

            if (this.c_senha.getText().length() <= 0) {
                throw new Exception("Senha não pode ser vazia.");
            } else {
                senha = this.c_senha.getText();
            }

            if (this.jTableUsuarios.getSelectedRow() == -1) {
                throw new Exception("Primeiro selecione um usuário para alterar.");
            } else {
                id = Integer.parseInt(this.jTableUsuarios.getValueAt(this.jTableUsuarios.getSelectedRow(), 0).toString());
            }
            
            Usuario usuario = new Usuario(this.usuarioDAO.getID(nomeAntigo), nome, permissao, email, null, senha);
            Resultado resultado = this.usuarioDAO.updateUsuarioBD(usuario);
            if (resultado.isSucesso()) {
                this.c_nome.setText("");
                this.c_email.setText("");
                this.c_permissao.setSelectedIndex(0);
                this.c_senha.setText("");
                carregaTabela();
            } 
            JOptionPane.showMessageDialog(rootPane, resultado.getMensagem());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_b_alterarActionPerformed

    private void jTableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuariosMouseClicked

        if (this.jTableUsuarios.getSelectedRow() != -1) {

            String nome = this.jTableUsuarios.getValueAt(this.jTableUsuarios.getSelectedRow(), 1).toString();
            String email = this.jTableUsuarios.getValueAt(this.jTableUsuarios.getSelectedRow(), 2).toString();
            String permissao = this.jTableUsuarios.getValueAt(this.jTableUsuarios.getSelectedRow(), 3).toString();
            String senha = usuarioDAO.obterSenha((int) this.jTableUsuarios.getValueAt(this.jTableUsuarios.getSelectedRow(), 0));

            this.c_nome.setText(nome);
            this.c_email.setText(email);
            this.c_permissao.setSelectedItem(permissao);
            this.c_senha.setText(senha);
            
            nomeAntigo = this.c_nome.getText();

        }
    }//GEN-LAST:event_jTableUsuariosMouseClicked

    private void b_apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_apagarActionPerformed
        try {
            int id = 0;
            if (this.jTableUsuarios.getSelectedRow() == -1) {
                throw new Exception("Primeiro selecione um usuário para apagar.");
            } else {
                id = Integer.parseInt(this.jTableUsuarios.getValueAt(this.jTableUsuarios.getSelectedRow(), 0).toString());
            }

            int resposta_usuario = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar este usuário?");

            if (resposta_usuario == 0) {
                Resultado resultado = this.usuarioDAO.deleteUsuarioBD(id);
                if (resultado.isSucesso()) {
                    this.c_nome.setText("");
                    this.c_email.setText("");
                    this.c_permissao.setSelectedIndex(0);
                    this.c_senha.setText("");
                }
                JOptionPane.showMessageDialog(rootPane, resultado.getMensagem());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            carregaTabela();
        }
    }//GEN-LAST:event_b_apagarActionPerformed

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("unchecked")
    public void carregaTabela() {

        DefaultTableModel modelo = (DefaultTableModel) this.jTableUsuarios.getModel();
        modelo.setNumRows(0);
        this.listaUsuarios = this.usuarioDAO.carregaUsuarios();

        for (Usuario usuario : listaUsuarios) {
            String senhaOfuscada = "";

            for (char letra : usuario.getSenha().toCharArray()) {
                senhaOfuscada += "*";
            }
            modelo.addRow(new Object[]{
                usuario.getID(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPermissao(),
                senhaOfuscada,
                usuario.getDataCadastro()
            });
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciaUsuario().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_alterar;
    private javax.swing.JButton b_apagar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JTextField c_email;
    private javax.swing.JTextField c_nome;
    private javax.swing.JComboBox<String> c_permissao;
    private javax.swing.JPasswordField c_senha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuarios;
    // End of variables declaration//GEN-END:variables
}
