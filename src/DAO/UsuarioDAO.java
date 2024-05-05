package DAO;

import Model.Usuario;
import Result.Resultado;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class UsuarioDAO {

    public static ArrayList<Usuario> MinhaLista = new ArrayList<Usuario>();

    public UsuarioDAO() {
    }

    public int maiorID() throws SQLException {

        int maiorID = 0;
        Statement stmt = null;
        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id_usuario) id_usuario FROM usuarios");
            res.next();
            maiorID = res.getInt("id_usuario");

            stmt.close();

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

        return maiorID + 1;
    }

    public Connection getConexao() {

        Connection connection = null;  //inst�ncia da conex�o

        try {

            // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configurar a conex�o
            String server = "database-1.clakmqs02kce.sa-east-1.rds.amazonaws.com"; //caminho do MySQL
            String database = "db_produtos";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "admin";
            String password = "adminadmin";

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
    
    public String obterSenha(int id) {
        String sql = "SELECT senha FROM usuarios WHERE id_usuario = ?";
        PreparedStatement stmt = null;
        String senha = "";

        try (Connection con = getConexao(); ) {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                senha = rs.getString("senha");
            }
        } catch (SQLException e) {
            System.out.println("Erro verificando email: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }

        return senha;
    }

    public Resultado inserirUsuarioBD(Usuario objeto) {
        // Verifique se o email já existe
        if (emailExiste(objeto.getEmail())) {
            return new Resultado(false, "Erro: O email " + objeto.getEmail() + " já está em uso.");
        }

        String sql = "INSERT INTO usuarios(id_usuario, nome, permissao, email, data_cadastro, senha) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        boolean sucesso = false;

        try (Connection con = getConexao(); ) {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objeto.getID());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getPermissao());
            stmt.setString(4, objeto.getEmail());
            stmt.setDate(5, new Date(objeto.getDataCadastro().getTime())); // Converter para java.sql.Date
            stmt.setString(6, objeto.getSenha());
            
            sucesso = stmt.executeUpdate() > 0;

            if (sucesso) {
                return new Resultado(true, "Usuário inserido com sucesso.");
            } else {
                return new Resultado(false, "Erro ao inserir usuário.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
            if (sucesso) {
                return new Resultado(true, "Usuário adicionado com sucesso.");
            } else {
                return new Resultado(false, "Erro ao adicionar usuário.");
            }
        }
    }


    private boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE email = ?";
        PreparedStatement stmt = null;
        boolean sucesso = false;

        try (Connection con = getConexao();) {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                sucesso =  count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro verificando email: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }

        return sucesso;
    }

    
    public ArrayList getMinhaLista() {
        
        MinhaLista.clear(); // Limpa nosso ArrayList
        Statement stmt = null;

        try {
            stmt = this.getConexao().createStatement();
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


    public Resultado deleteUsuarioBD(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        PreparedStatement stmt = null;
        boolean sucesso = false;
        
        try (Connection con = getConexao();) {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            sucesso = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return new Resultado(false, "Erro ao deletar usuário. Erro: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
            if (sucesso) {
                return new Resultado(true, "Usuário deletado com sucesso.");
            } else {
                return new Resultado(false, "Erro ao deeltar usuário.");
            }
        }
    }

    public Resultado updateUsuarioBD(Usuario objeto) {
        String sql = "UPDATE usuarios SET nome = ?, permissao = ?, email = ?, senha = ? WHERE id_usuario = ?";
        PreparedStatement stmt = null;
        boolean sucesso = false;
        
        try (Connection con = getConexao();) {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getPermissao());
            stmt.setString(3, objeto.getEmail());
            stmt.setString(4, objeto.getSenha());
            stmt.setInt(5, objeto.getID());
            
            sucesso = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return new Resultado(false, "Erro ao atualizar usuário. Erro: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
            if (sucesso) {
                return new Resultado(true, "Usuário atualizado com sucesso.");
            } else {
                return new Resultado(false, "Erro ao atualizar usuário.");
            }
        }
    }

    public Usuario carregaUsuario(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        PreparedStatement stmt = null;
        
        try (Connection con = getConexao();) {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nome"),
                rs.getString("permissao"),
                rs.getString("email"),
                rs.getDate("data_cadastro"),
                rs.getString("senha")  
                );
                
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro carregando usuario: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }
        
        return usuario;
    }
    
    public ArrayList<Usuario> carregaUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement stmt = null;
        
        try (Connection con = getConexao()) {
            stmt = con.prepareStatement(sql);
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
        } catch (SQLException e) {
            System.out.println("Erro carregando usuarios: " + e.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }
        
        return usuarios;
    }
    
    public Usuario login(String email, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        PreparedStatement stmt = null;

        try (Connection con = getConexao()) {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Verificar se a senha fornecida corresponde à senha do usuário recuperado
                if (rs.getString("senha").equals(senha)) {
                    // Senha correta, retornar o usuário
                    usuario = new Usuario(
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
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e.toString());
            }
        }

        return usuario;
    }


}
