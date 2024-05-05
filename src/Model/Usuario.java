package Model;

import java.util.*;
import DAO.UsuarioDAO;
import java.sql.SQLException;

public class Usuario {

    private int id_usuario;
    private String nome;
    private String permissao; 
    private String email;
    private Date data_cadastro;
    private String senha;
    private final UsuarioDAO dao; 

    public Usuario() {
        this.dao = new UsuarioDAO();
    }

    public Usuario(int id_usuario, String nome, String permissao, String email, Date data_cadastro, String senha) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.permissao = permissao;
        this.email = email;
        this.data_cadastro = data_cadastro;
        this.senha = senha;
        this.dao = new UsuarioDAO();
    }
    
    public int getID() {
        return id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return data_cadastro;
    }

    public void setDataCadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "\n ID: " + this.id_usuario
                + "\n Nome: " + this.nome
                + "\n Permissão: " + this.permissao
                + "\n Email: " + this.email
                + "\n Data de Cadastro: " + this.data_cadastro
                + "\n -----------";
    }

    public boolean inserirUsuarioBD(String nome, String permissao, String email, Date data_cadastro, String senha) throws SQLException {
        Usuario objeto = new Usuario(dao.maiorID(), nome, permissao, email, data_cadastro, senha);
        return dao.inserirUsuarioBD(objeto);
    }

    public boolean DeleteUsuario(int id) {
        dao.deleteUsuarioBD(id);
        return true;
    }

    public boolean UpdateUsuarioBD(String nome, String permissao, String email, Date data_cadastro, String senha) {
        Usuario objeto = new Usuario(dao.getID(nome), nome, permissao, email, data_cadastro, senha);
        dao.updateUsuarioBD(objeto);
        return true;
    }

    public Usuario carregaUsuario(int id) {
        return dao.carregaUsuario(id);
    }

    public int maiorID() throws SQLException {
        return dao.maiorID();
    }   
}
