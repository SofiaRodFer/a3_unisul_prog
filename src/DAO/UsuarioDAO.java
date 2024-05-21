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

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public UsuarioDAO() {}

    public int getMaiorId() throws SQLException {
        int maiorID = 0;
        Statement stmt = null;
        try {
            stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id_usuario) id_usuario FROM usuarios");
            res.next();
            maiorID = res.getInt("id_usuario");
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

        Connection connection = null; 

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "database-1.clakmqs02kce.sa-east-1.rds.amazonaws.com";
            String database = "db_produtos";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "admin";
            String password = "adminadmin";

            connection = DriverManager.getConnection(url, user, password);

            return connection;

        } catch (ClassNotFoundException e) { 
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }
    
    public String getSenha(int id) {
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

    public Resultado inserirUsuario(Usuario objeto) {
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
            stmt.setDate(5, new Date(objeto.getDataCadastro().getTime()));
            stmt.setString(6, objeto.getSenha());
            
            sucesso = stmt.executeUpdate() > 0;
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
    
    public ArrayList getListaUsuarios() {
        listaUsuarios.clear();
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
                listaUsuarios.add(usuario);
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

        return listaUsuarios;
    }
    
    public int getID(String nome) {
        this.getListaUsuarios();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario.getID();
            }
        }
        return -1;
    }


    public Resultado deletarUsuario(int id) {
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
                return new Resultado(false, "Erro ao deletar usuário.");
            }
        }
    }

    public Resultado atualizarUsuario(Usuario objeto) {
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

    public Usuario carregarUsuario(int id) {
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
    
    public Usuario login(String email, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        PreparedStatement stmt = null;

        try (Connection con = getConexao()) {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("senha").equals(senha)) {
                    usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("permissao"),
                        rs.getString("email"),
                        rs.getDate("data_cadastro"),
                        rs.getString("senha")
                    );
                } else {
                    System.out.println("Senha incorreta para o email fornecido.");
                }
            } else {
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
