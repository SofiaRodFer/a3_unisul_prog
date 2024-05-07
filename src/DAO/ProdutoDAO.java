/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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


/**
 *
 * @author emily
 */
public class ProdutoDAO {

    public static ArrayList<Produto> MinhaLista = new ArrayList<Produto>();

    public ProdutoDAO() {
    }


     public int maiorCodigo_produto() throws SQLException {

        int maiorCodigo_produto = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(codigo_produto) FROM produtos");
            res.next();
            maiorCodigo_produto = res.getInt("codigo_produto");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorCodigo_produto;
    }


    public Connection getConexao() {

        Connection connection = null;  

        try {


            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);


            // Configurar a conex�o
            String server = "database-1.clakmqs02kce.sa-east-1.rds.amazonaws.com"; //caminho do MySQL
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

    public ArrayList getMinhaLista() {

        MinhaLista.clear(); 

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produtos");
            while (res.next()) {
                int codigo_produto = res.getInt("codigo_produto");
                String nome_produto = res.getString("nome_produto");
                String descricao_produto = res.getString("descricao_produto");
                String categoria_produto = res.getString("categoria_produto");
                int quantidade_estoque = res.getInt("quantidade_produto");
                double preco = res.getDouble("preco");
                String data_cadastro= res.getString("data_cadastro");

                Produto objeto = new Produto(codigo_produto, nome_produto, descricao_produto, categoria_produto, quantidade_estoque, preco, data_cadastro );



                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }

    public boolean InsertProdutoDB(Produto objeto) {
        String sql = "INSERT INTO produtos(codigo_produto, nome_produto, descricao_produto, categoria_produto, quantidade_estoque, preco, data_cadastro) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getCodigo_produto());
            stmt.setString(2, objeto.getNome_produto());
            stmt.setString(3, objeto.getDescricao_produto());
            stmt.setString(4, objeto.getCategoria_produto());
            stmt.setInt(5, objeto.getQuantidade_estoque());
            stmt.setDouble(6, objeto.getPreco());
            stmt.setString(7, objeto.getData_cadastro());


            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public Resultado DeleteProdutoDB(int codigo_produto) {
        String sql = "DELETE FROM produtos WHERE codigo_produto = ?";
        PreparedStatement stmt = null;
        boolean sucesso = false;
        try (Connection con = getConexao();) {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo_produto);
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

    public boolean UpdateProdutoDB(Produto objeto) {

        String sql = "UPDATE produto set codigo_produto = ? , nome_produto = ?, descricao_produto = ?, categoria_produto = ?, quantidade_estoque = ?, preco = ?, data_cadastro = ?) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getCodigo_produto());
            stmt.setString(2, objeto.getNome_produto());
            stmt.setString(3, objeto.getDescricao_produto());
            stmt.setString(4, objeto.getCategoria_produto());
            stmt.setInt(5, objeto.getQuantidade_estoque());
            stmt.setDouble(6, objeto.getPreco());
            stmt.setString(7, objeto.getData_cadastro());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public Produto carregaProdutoDB(int codigo_produto) {

        Produto objeto = new Produto();
        objeto.setCodigo_produto(codigo_produto);

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produto WHERE codigo_produto = " + codigo_produto);
            res.next();

            objeto.setCategoria_produto(res.getString("codigo_produto"));
            objeto.setNome_produto(res.getString("nome_produto"));
            objeto.setDescricao_produto(res.getString("descricao_produto"));
            objeto.setCategoria_produto(res.getString("categoria_produto"));
            objeto.setQuantidade_estoque(res.getInt("quantidade_produto"));
            objeto.setPreco(res.getDouble("preco"));
            objeto.setData_cadastro(res.getString("data_cadastro"));


            stmt.close();            

        } catch (SQLException erro) {
        }
        return objeto;
    }

}