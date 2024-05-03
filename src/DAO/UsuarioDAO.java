package DAO;

import Model.Usuario;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    public static ArrayList<Usuario> MinhaLista = new ArrayList<Usuario>();

    public UsuarioDAO() {
    }

    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id_usuario) id_usuario FROM usuarios");
            res.next();
            maiorID = res.getInt("id_usuario");

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro obtendo maior ID: " + ex.toString());
        }

        return maiorID + 1;
    }

    public Connection getConexao() {

        Connection connection = null;  //inst�ncia da conex�o

        try {

            // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configurar a conex�o
            String server = "localhost"; //caminho do MySQL
            String database = "db_produtos";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "12345";

            connection = DriverManager.getConnection(url, user, password);

            // Testando..
//            if (connection == null) {
//                System.out.println("Status: Conexão aberta.");
//            } else {
//                System.out.println("Status: N�O CONECTADO!");
//            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver n�o encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    public boolean inserirUsuarioBD(Usuario objeto) {
        String sql = "INSERT INTO usuarios(id_usuario, nome, permissao, email, data_cadastro, senha) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, objeto.getID());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getPermissao());
            stmt.setString(4, objeto.getEmail());
            stmt.setDate(5, new java.sql.Date(objeto.getDataCadastro().getTime())); // Converter para java.sql.Date
            stmt.setString(6, objeto.getSenha());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro inserindo usuario: " + e.toString());
            return false;
        }
    }
    
    public ArrayList getMinhaLista() {
        
        MinhaLista.clear(); // Limpa nosso ArrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                
                Usuario usuario = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nome"),
                rs.getString("permissao"),
                rs.getString("email"),
                rs.getDate("data_cadastro"),
                rs.getString("senha")  
                );
                MinhaLista.add(usuario);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }
    
    public int getID(String nome) {
    this.getMinhaLista();
    // Iterar sobre a lista de usuários (ou consultar o banco de dados)
    for (Usuario usuario : MinhaLista) {
        if (usuario.getNome().equals(nome)) {
            return usuario.getID();
        }
    }
    // Se o nome não for encontrado, retorna -1
    return -1;
}


    public boolean deleteUsuarioBD(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        
        try (Connection con = getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro deletando usuario: " + e.toString());
            return false;
        }
    }

    public boolean updateUsuarioBD(Usuario objeto) {
        String sql = "UPDATE usuarios SET nome = ?, permissao = ?, email = ?, senha = ? WHERE id_usuario = ?";
        
        try (Connection con = getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getPermissao());
            stmt.setString(3, objeto.getEmail());
            stmt.setString(4, objeto.getSenha());
            stmt.setInt(5, objeto.getID());
            
            System.out.println("Usuario: " + objeto);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro atualizando usuario: " + e.toString());
            return false;
        }
    }

    public Usuario carregaUsuario(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        
        try (Connection con = getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nome"),
                rs.getString("permissao"),
                rs.getString("email"),
                rs.getDate("data_cadastro"),
                rs.getString("senha")  
                );
                
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro carregando usuario: " + e.toString());
        }
        
        return null;
    }
    
    public ArrayList<Usuario> carregaUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuarios";
        
        try (Connection con = getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nome"),
                rs.getString("permissao"),
                rs.getString("email"),
                rs.getDate("data_cadastro"),
                rs.getString("senha")  
                );
                usuarios.add(usuario);
            }
            
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println("Erro carregando usuarios: " + e.toString());
        }
        
        return usuarios;
    }
    
    public Usuario login(String email, String senha) {
    String sql = "SELECT * FROM usuarios WHERE email = ?";
    
    try (Connection con = getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Verificar se a senha fornecida corresponde à senha do usuário recuperado
            if (rs.getString("senha").equals(senha)) {
                // Senha correta, retornar o usuário
                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("permissao"),
                    rs.getString("email"),
                    rs.getDate("data_cadastro"),
                    rs.getString("senha")
                );
            } else {
                // Senha incorreta
                System.out.println("Senha incorreta para o email fornecido.");
            }
        } else {
            // Usuário não encontrado com o email fornecido
            System.out.println("Nenhum usuário encontrado com o email fornecido.");
        }
    } catch (SQLException e) {
        System.out.println("Erro no login: " + e.toString());
    }
    
    return null;
}

}
