/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.ProdutoDAO;
import Model.Produto;
import Result.Resultado;
import javax.swing.JOptionPane;

/**
 *
 * @author Sofia
 */
public class VisualizaProduto extends javax.swing.JFrame {
    
    private Produto produtoSelecionado;
    private final ProdutoDAO dao;
    private final boolean possuiAdmin;

    /**
     * Creates new form VisualizaProduto
     * @param produto
     * @param possuiAdmin
     */
    public VisualizaProduto(Produto produto, boolean possuiAdmin) {
        System.out.println(produto);
        this.dao = new ProdutoDAO();
        initComponents();
        this.produtoSelecionado = produto;
        this.possuiAdmin = possuiAdmin;
        this.iniciarCampos();
        this.preencherDados();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jNomeProduto = new javax.swing.JLabel();
        jValorEstoqueLabel = new javax.swing.JLabel();
        jDescricaoProduto = new javax.swing.JLabel();
        jQuantidadeLabel = new javax.swing.JLabel();
        jQuantidadeProduto = new javax.swing.JLabel();
        jCodigoProduto = new javax.swing.JLabel();
        jCodigoLabel = new javax.swing.JLabel();
        jCategoriaLabel = new javax.swing.JLabel();
        jCategoriaProduto = new javax.swing.JLabel();
        jPrecoLabel = new javax.swing.JLabel();
        jPrecoProduto = new javax.swing.JLabel();
        jDataLabel = new javax.swing.JLabel();
        jDataProduto = new javax.swing.JLabel();
        b_alterar = new javax.swing.JButton();
        b_salvarAlteracao = new javax.swing.JButton();
        b_apagar = new javax.swing.JButton();
        b_cancelarAlteracao = new javax.swing.JButton();
        jDescricaoEdita = new javax.swing.JTextField();
        jQuantidadeEdita = new javax.swing.JTextField();
        jCategoriaEdita = new javax.swing.JTextField();
        jPrecoEdita = new javax.swing.JTextField();
        jNomeEdita = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jDescricaoLabel1 = new javax.swing.JLabel();
        jValorEstoque = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(729, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jNomeProduto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jNomeProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jNomeProduto.setText("placeholder");
        getContentPane().add(jNomeProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 14, 754, 48));

        jValorEstoqueLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jValorEstoqueLabel.setText("Valor total do estoque:");
        getContentPane().add(jValorEstoqueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        jDescricaoProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDescricaoProduto.setText("placeholder");
        getContentPane().add(jDescricaoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 270, 20));

        jQuantidadeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jQuantidadeLabel.setText("Quantidade em estoque:");
        getContentPane().add(jQuantidadeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jQuantidadeProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jQuantidadeProduto.setText("placeholder");
        getContentPane().add(jQuantidadeProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 270, -1));

        jCodigoProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCodigoProduto.setText("placeholder");
        getContentPane().add(jCodigoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 220, -1));

        jCodigoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCodigoLabel.setText("Código:");
        getContentPane().add(jCodigoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jCategoriaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCategoriaLabel.setText("Categoria:");
        getContentPane().add(jCategoriaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jCategoriaProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCategoriaProduto.setText("placeholder");
        getContentPane().add(jCategoriaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 230, -1));

        jPrecoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPrecoLabel.setText("Preço:");
        getContentPane().add(jPrecoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jPrecoProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPrecoProduto.setText("placeholder");
        getContentPane().add(jPrecoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        jDataLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDataLabel.setText("Data de cadastro:");
        getContentPane().add(jDataLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jDataProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDataProduto.setText("placeholder");
        getContentPane().add(jDataProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        b_alterar.setText("Alterar");
        b_alterar.setEnabled(false);
        b_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_alterarActionPerformed(evt);
            }
        });
        getContentPane().add(b_alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 383, -1, -1));

        b_salvarAlteracao.setText("Salvar");
        b_salvarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_salvarAlteracaoActionPerformed(evt);
            }
        });
        getContentPane().add(b_salvarAlteracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 383, -1, -1));

        b_apagar.setText("Apagar");
        b_apagar.setEnabled(false);
        b_apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_apagarActionPerformed(evt);
            }
        });
        getContentPane().add(b_apagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 383, -1, -1));

        b_cancelarAlteracao.setText("Cancelar");
        b_cancelarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarAlteracaoActionPerformed(evt);
            }
        });
        getContentPane().add(b_cancelarAlteracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 383, -1, -1));

        jDescricaoEdita.setText("placeholderDescricao");
        jDescricaoEdita.setToolTipText("");
        jDescricaoEdita.setName(""); // NOI18N
        getContentPane().add(jDescricaoEdita, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 274, -1));
        jDescricaoEdita.getAccessibleContext().setAccessibleParent(jDescricaoProduto);

        jQuantidadeEdita.setText("placeholderQuantidade");
        getContentPane().add(jQuantidadeEdita, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 270, -1));

        jCategoriaEdita.setText("placeholderCategoria");
        getContentPane().add(jCategoriaEdita, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 216, -1));

        jPrecoEdita.setText("placeholderPreco");
        getContentPane().add(jPrecoEdita, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 284, -1));

        jNomeEdita.setText("placeholderNome");
        getContentPane().add(jNomeEdita, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 22, 710, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 750, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 700, 10));

        jDescricaoLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDescricaoLabel1.setText("Descrição:");
        getContentPane().add(jDescricaoLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jValorEstoque.setText("placeholder");
        getContentPane().add(jValorEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_alterarActionPerformed
        try {
            alterarModo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_b_alterarActionPerformed

    private void b_apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_apagarActionPerformed
        try {
            int id = this.produtoSelecionado.getCodigo_produto();

            int resposta_usuario = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar este produto?");

            if (resposta_usuario == 0) {
                Resultado resultado = this.dao.DeleteProdutoDB(id);
                JOptionPane.showMessageDialog(rootPane, resultado.getMensagem());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            this.dispose();
        }
    }//GEN-LAST:event_b_apagarActionPerformed

    private void b_salvarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_salvarAlteracaoActionPerformed
        String categoria;
        String descricao;
        int quantidade;
        Double preco;
        String nome;
        Produto produto;
        
        try {
            if (this.jNomeEdita.getText().length() < 2) {
                throw new Exception("Nome deve conter ao menos 2 caracteres.");
            } else {
                nome = this.jNomeEdita.getText();
            }
            
            if (this.jCategoriaEdita.getText().length() < 2) {
                throw new Exception("Categoria deve conter ao menos 2 caracteres.");
            } else {
                categoria = this.jCategoriaEdita.getText();
            }
            
            if (this.jDescricaoEdita.getText().length() < 2) {
                throw new Exception("Descrição deve conter ao menos 2 caracteres.");
            } else {
                descricao = this.jDescricaoEdita.getText();
            }
            
            try {
                quantidade = Integer.parseInt(this.jQuantidadeEdita.getText());
            } catch (NumberFormatException e) {
                throw new Exception("Quantidade deve ser um número inteiro.");
            }
            
            try {
                preco = Double.valueOf(this.jPrecoEdita.getText());
            } catch (NumberFormatException e) {
                throw new Exception("Preço deve ser um número válido.");
            }
            
            produto = new Produto(
                this.produtoSelecionado.getCodigo_produto(),
                nome,
                descricao,
                categoria,
                quantidade,
                preco,
                this.produtoSelecionado.getData_cadastro()
            );
            Resultado resultado = this.dao.UpdateProdutoDB(produto);
            if (resultado.isSucesso()) {
                this.produtoSelecionado = produto;
                preencherDados();
                alterarModo();
            } 
            JOptionPane.showMessageDialog(rootPane, resultado.getMensagem());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
   
    }//GEN-LAST:event_b_salvarAlteracaoActionPerformed

    private void b_cancelarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarAlteracaoActionPerformed
        alterarModo();
    }//GEN-LAST:event_b_cancelarAlteracaoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new GerenciaProduto(possuiAdmin).setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void preencherDados() {
        String nome = this.produtoSelecionado.getNome_produto();
        String categoria = this.produtoSelecionado.getCategoria_produto();
        int codigo = this.produtoSelecionado.getCodigo_produto();
        String data = this.produtoSelecionado.getData_cadastro();
        String descricao = this.produtoSelecionado.getDescricao_produto();
        Double preco = this.produtoSelecionado.getPreco();
        int quantidade = this.produtoSelecionado.getQuantidade_estoque();
        
        this.jCategoriaProduto.setText(categoria);
        this.jCodigoProduto.setText(String.valueOf(codigo));
        this.jDataProduto.setText(data);
        this.jDescricaoProduto.setText(descricao);
        this.jNomeProduto.setText(nome);
        this.jPrecoProduto.setText("R$ " + String.valueOf(preco));
        this.jQuantidadeProduto.setText(String.valueOf(quantidade));
        this.jValorEstoque.setText("R$ " + String.valueOf(calcularValorEstoque()));
        
        this.jDescricaoEdita.setText(descricao);
        this.jQuantidadeEdita.setText(String.valueOf(quantidade));
        this.jCategoriaEdita.setText(categoria);
        this.jPrecoEdita.setText(String.valueOf(preco));
        this.jNomeEdita.setText(nome);
    }
    
    private void alterarModo() {
        this.b_alterar.setVisible(!this.b_alterar.isVisible());
        this.b_apagar.setVisible(!this.b_apagar.isVisible());
        this.jCategoriaProduto.setVisible(!this.jCategoriaProduto.isVisible());
        this.jDescricaoProduto.setVisible(!this.jDescricaoProduto.isVisible());
        this.jNomeProduto.setVisible(!this.jNomeProduto.isVisible());
        this.jPrecoProduto.setVisible(!this.jPrecoProduto.isVisible());
        this.jQuantidadeProduto.setVisible(!this.jQuantidadeProduto.isVisible());
        this.b_salvarAlteracao.setVisible(!this.b_salvarAlteracao.isVisible());
        this.b_cancelarAlteracao.setVisible(!this.b_cancelarAlteracao.isVisible());
        this.jDescricaoEdita.setVisible(!this.jDescricaoEdita.isVisible());
        this.jQuantidadeEdita.setVisible(!this.jQuantidadeEdita.isVisible());
        this.jCategoriaEdita.setVisible(!this.jCategoriaEdita.isVisible());
        this.jPrecoEdita.setVisible(!this.jPrecoEdita.isVisible());
        this.jNomeEdita.setVisible(!this.jNomeEdita.isVisible());
    }
    
    private void iniciarCampos() {
        this.b_salvarAlteracao.setVisible(false);
        this.b_cancelarAlteracao.setVisible(false);
        this.jDescricaoEdita.setVisible(false);
        this.jQuantidadeEdita.setVisible(false);
        this.jCategoriaEdita.setVisible(false);
        this.jPrecoEdita.setVisible(false);
        this.jNomeEdita.setVisible(false);
        
        this.b_alterar.setEnabled(possuiAdmin);
        this.b_apagar.setEnabled(possuiAdmin);
    }
    
    private Double calcularValorEstoque() {
        Double quantidade = Double.valueOf(this.produtoSelecionado.getQuantidade_estoque());
        Double preco = this.produtoSelecionado.getPreco();
        return Double.valueOf(Math.round(quantidade * preco));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_alterar;
    private javax.swing.JButton b_apagar;
    private javax.swing.JButton b_cancelarAlteracao;
    private javax.swing.JButton b_salvarAlteracao;
    private javax.swing.JTextField jCategoriaEdita;
    private javax.swing.JLabel jCategoriaLabel;
    private javax.swing.JLabel jCategoriaProduto;
    private javax.swing.JLabel jCodigoLabel;
    private javax.swing.JLabel jCodigoProduto;
    private javax.swing.JLabel jDataLabel;
    private javax.swing.JLabel jDataProduto;
    private javax.swing.JTextField jDescricaoEdita;
    private javax.swing.JLabel jDescricaoLabel1;
    private javax.swing.JLabel jDescricaoProduto;
    private javax.swing.JTextField jNomeEdita;
    private javax.swing.JLabel jNomeProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jPrecoEdita;
    private javax.swing.JLabel jPrecoLabel;
    private javax.swing.JLabel jPrecoProduto;
    private javax.swing.JTextField jQuantidadeEdita;
    private javax.swing.JLabel jQuantidadeLabel;
    private javax.swing.JLabel jQuantidadeProduto;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jValorEstoque;
    private javax.swing.JLabel jValorEstoqueLabel;
    // End of variables declaration//GEN-END:variables
}
