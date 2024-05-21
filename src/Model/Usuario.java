package Model;

import java.util.*;

public class Usuario {

    private int id;
    private String nome;
    private String permissao; 
    private String email;
    private Date dataCadastro;
    private String senha;

    public Usuario(int id, String nome, String permissao, String email, Date dataCadastro, String senha) {
        this.id = id;
        this.nome = nome;
        this.permissao = permissao;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.senha = senha;
    }
    
    public int getID() {
        return id;
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
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "\n ID: " + this.id
                + "\n Nome: " + this.nome
                + "\n Permissão: " + this.permissao
                + "\n Email: " + this.email
                + "\n Data de Cadastro: " + this.dataCadastro
                + "\n -----------";
    }
 
}
