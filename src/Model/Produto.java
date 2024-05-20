/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.ProdutoDAO;
import java.util.*;
import java.sql.SQLException;


/**
 *
 * @author emily
 */
public class Produto {

    private int codigo_produto;
    private String nome_produto;
    private String descricao_produto;
    private String categoria_produto;
    private int quantidade_estoque;
    private double preco;
    private String data_cadastro;
    private final ProdutoDAO dao;

    public Produto() {
        this.dao = new ProdutoDAO();
    }

    public Produto(int codigo_produto, String nome_produto, String descricao_produto, String categoria_produto, int quantidade_estoque, double preco, String data_cadastro) {
        this.codigo_produto = codigo_produto;
        this.nome_produto = nome_produto;
        this.descricao_produto = descricao_produto;
        this.categoria_produto = categoria_produto;
        this.quantidade_estoque = quantidade_estoque;
        this.preco = preco;
        this.data_cadastro = data_cadastro;
        this.dao = new ProdutoDAO();
    }

    public Produto(int codigo_produto, String nome_produto, String descricao_produto, String categoria_produto, double preco, String data_cadastro, ProdutoDAO dao) {
        this.codigo_produto = codigo_produto;
        this.nome_produto = nome_produto;
        this.descricao_produto = descricao_produto;
        this.categoria_produto = categoria_produto;
        this.preco = preco;
        this.data_cadastro = data_cadastro;
        this.dao = dao;
    }

    
    
    
    public int getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }

    public String getCategoria_produto() {
        return categoria_produto;
    }

    public void setCategoria_produto(String categoria_produto) {
        this.categoria_produto = categoria_produto;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    @Override
    public String toString() {
        return "\n Código do prodduto: " + this.getCodigo_produto()
                +"\n Nome do produto: " + this.getNome_produto()
                +"\n Descrição do produto: " + this.getDescricao_produto()
                +"\n Categoria do produto:" + this.getCategoria_produto()
                +"\n Quantidade de estoque: " + this.getQuantidade_estoque()
                +"\n Preço do produto: " + this.getPreco()
                +"\n Data de cadastro:" + this.getData_cadastro();

    }

    public ArrayList getMinhaLinsta(){
        return dao.getMinhaLista();


    }

    public int maiorCodigo_produto() throws SQLException{
        return dao.maiorCodigo_produto();
    }   

    public boolean InsertProdutoDB(String nome_produto, String descricao_produto, String categoria_produto, int quantidade_estoque, double preco, String data_cadastro) throws SQLException{
     int codigo_produto = this.maiorCodigo_produto() +1;
     Produto objeto = new Produto(codigo_produto, nome_produto, descricao_produto, categoria_produto,quantidade_estoque, preco, data_cadastro);
     dao.InsertProdutoDB(objeto);
     return true;
    }
    public boolean DeleteAlunoBD(int codigo_produto) {
        dao.DeleteProdutoDB(codigo_produto);
        return true;
    }

    public boolean UpdateProdutoBD(int codigo_produto, String nome_produto, String descricao_produto, String categoria_produto, int quantidade_estoque, double preco, String data_cadastro){
        Produto objeto = new Produto(codigo_produto, nome_produto, descricao_produto, categoria_produto, quantidade_estoque, preco, data_cadastro);
        dao.UpdateProdutoDB(objeto);
        return true;
    }

    public Produto carregaProdutoDB(int codigo_produto){
        dao.carregaProdutoDB(codigo_produto);
        return null;

    }


    }