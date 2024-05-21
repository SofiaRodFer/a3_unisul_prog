package Model;

public class Produto {

    private int codigoProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private String categoria;
    private int quantidadeEstoque;
    private double preco;
    private String dataCadastro;
    
    public Produto() {}

    public Produto(
        int codigoProduto, 
        String nomeProduto, 
        String descricaoProduto, 
        String categoria, 
        int quantidadeEstoque, 
        double preco, 
        String dataCadastro
    ) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "\n Código do produto: " + this.getCodigoProduto()
                +"\n Nome do produto: " + this.getNomeProduto()
                +"\n Descrição do produto: " + this.getDescricaoProduto()
                +"\n Categoria do produto:" + this.getCategoria()
                +"\n Quantidade de estoque: " + this.getQuantidadeEstoque()
                +"\n Preço do produto: " + this.getPreco()
                +"\n Data de cadastro:" + this.getDataCadastro();
    }
}