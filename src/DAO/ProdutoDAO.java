package DAO;

import Model.Produto;
import Result.Resultado;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class ProdutoDAO {

    public static ArrayList<Produto> listaProdutos = new ArrayList<>();

    public ProdutoDAO() {}

     public int getMaiorId() throws SQLException {
        int maiorCodigoProduto = 0;
        Statement stmt = null;
        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(codigo_produto) FROM db_produtos.produtos;");
            res.next();
            maiorCodigoProduto = res.getInt("MAX(codigo_produto)");
        } catch (SQLException ex) {
            System.out.println("Erro obtendo maior ID: " + ex.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }

        return maiorCodigoProduto + 1;
    }

    public Connection getConexao() {
        Connection connection = null;  
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            String server = "database-1.clakmqs02kce.sa-east-1.rds.amazonaws.com";;
            String database = "db_produtos";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "admin";
            String password = "adminadmin";

            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }

            return connection;
            
        } catch (ClassNotFoundException e) { 
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;
        } catch (SQLException e) {
            System.out.println("Não foi possivel conectar.");
            return null;
        }
    }
    
    public ArrayList<String> getListaCategorias() {
        ArrayList<String> listaCategorias = new ArrayList<>();
        Statement stmt = null;

        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT DISTINCT categoria FROM produtos");
            while (res.next()) {
                String categoria = res.getString("categoria");
                listaCategorias.add(categoria);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao obter lista de categorias: " + ex.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o Statement: " + e.toString());
            }
        }

        return listaCategorias;
    }

    public ArrayList getListaProdutos() {
        listaProdutos.clear(); 
        Statement stmt = null;

        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produtos");
            while (res.next()) {
                Produto produto = new Produto(
                    res.getInt("codigo_produto"),
                    res.getString("nome_produto"),
                    res.getString("descricao_produto"),
                    res.getString("categoria"),
                    res.getInt("quantidade_estoque"),
                    res.getDouble("preco"),
                    res.getString("data_cadastro")
                );
                listaProdutos.add(produto);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao obter lista: " + ex.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }

        return listaProdutos;
    }
    
    public ArrayList<Produto> getListaProdutos(String categoria) {
        listaProdutos.clear();
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            if (categoria != null && !categoria.equals("")) {
                String sql = "SELECT * FROM produtos WHERE categoria = ?";
                stmt = this.getConexao().prepareStatement(sql);
                stmt.setString(1, categoria);
            } else {
                String sql = "SELECT * FROM produtos";
                stmt = this.getConexao().prepareStatement(sql);
            }

            res = stmt.executeQuery();
            while (res.next()) {
                Produto produto = new Produto(
                    res.getInt("codigo_produto"),
                    res.getString("nome_produto"),
                    res.getString("descricao_produto"),
                    res.getString("categoria"),
                    res.getInt("quantidade_estoque"),
                    res.getDouble("preco"),
                    res.getString("data_cadastro")
                );
                listaProdutos.add(produto);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao obter lista de produtos: " + ex.toString());
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.toString());
            }
        }

        return listaProdutos;
    }
    
    public int getQuantidadeProdutos() {
        Statement stmt = null;
        int quantidadeProdutos = 0;
        
        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT COUNT(codigo_produto) as qnt_produtos FROM produtos");
            while (res.next()) {
                quantidadeProdutos = res.getInt("qnt_produtos");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter quantidade de produtos: " + ex.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }
        return quantidadeProdutos;
    }
    
    public Double getValorEstoque() {
        Statement stmt = null;
        Double valorTotal = 0.00;

        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produtos");
            while (res.next()) {
                valorTotal += res.getDouble("preco") * res.getInt("quantidade_estoque");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter valor do estoque: " + ex.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }
        return valorTotal;
    }
    
    public ArrayList getProdutosEmFalta() {
        listaProdutos.clear(); 
        Statement stmt = null;

        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produtos WHERE quantidade_estoque = 0");
            while (res.next()) {
                Produto produto = new Produto(
                    res.getInt("codigo_produto"),
                    res.getString("nome_produto"),
                    res.getString("descricao_produto"),
                    res.getString("categoria"),
                    res.getInt("quantidade_estoque"),
                    res.getDouble("preco"),
                    res.getString("data_cadastro")
                );
                listaProdutos.add(produto);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao obter lista: " + ex.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }

        return listaProdutos;
    }

    public Resultado inserirProduto(Produto objeto) {
        String sql = "INSERT INTO produtos(codigo_produto, nome_produto, descricao_produto, categoria, quantidade_estoque, preco, data_cadastro) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        boolean sucesso = false;

        try {
            stmt = this.getConexao().prepareStatement(sql);
            
            stmt.setInt(1, objeto.getCodigoProduto());
            stmt.setString(2, objeto.getNomeProduto());
            stmt.setString(3, objeto.getDescricaoProduto());
            stmt.setString(4, objeto.getCategoria());
            stmt.setInt(5, objeto.getQuantidadeEstoque());
            stmt.setDouble(6, objeto.getPreco());
            stmt.setString(7, objeto.getDataCadastro());

            sucesso = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            return new Resultado(false, "Erro ao deletar produto. Erro: " + erro.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
            if (sucesso) {
                return new Resultado(true, "Produto inserido com sucesso.");
            } else {
                return new Resultado(false, "Erro ao inserir produto.");
            }
        }

    }

    public Resultado deletarProduto(int codigoProduto) {
        String sql = "DELETE FROM produtos WHERE codigo_produto = ?";
        PreparedStatement stmt = null;
        boolean sucesso = false;
        try (Connection con = getConexao();) {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigoProduto);
            sucesso = stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            return new Resultado(false, "Erro ao deletar produto. Erro: " + erro.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
            if (sucesso) {
                return new Resultado(true, "Produto deletado com sucesso.");
            } else {
                return new Resultado(false, "Erro ao deletar produto.");
            }
        }
    }

    public Resultado atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome_produto = ?, descricao_produto = ?, categoria = ?, quantidade_estoque = ?, preco = ? WHERE codigo_produto = ?";
        PreparedStatement stmt = null;
        boolean sucesso = false;
        
        try (Connection con = getConexao()) {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            stmt.setDouble(5, produto.getPreco());
            stmt.setInt(6, produto.getCodigoProduto());
            
            sucesso = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return new Resultado(false, "Erro ao atualizar produto. Erro: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
            if (sucesso) {
                return new Resultado(true, "Produto atualizado com sucesso.");
            } else {
                return new Resultado(false, "Erro ao atualizar produto.");
            }
        }

    }

    public Produto carregarProduto(int codigoProduto) {
        Produto produto = new Produto();
        produto.setCodigoProduto(codigoProduto);

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produtos WHERE codigo_produto = " + codigoProduto);
            res.next();

            produto.setCodigoProduto(res.getInt("codigo_produto"));
            produto.setNomeProduto(res.getString("nome_produto"));
            produto.setDescricaoProduto(res.getString("descricao_produto"));
            produto.setCategoria(res.getString("categoria"));
            produto.setQuantidadeEstoque(res.getInt("quantidade_estoque"));
            produto.setPreco(res.getDouble("preco"));
            produto.setDataCadastro(res.getString("data_cadastro"));

            stmt.close();            

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return produto;
    }

}