package br.com.inventario.modelo;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private Categoria categoria;

    public Produto() {
        this.categoria = Categoria.OUTROS;
    }

    public Produto(int id, String nome, double preco, int quantidadeEstoque, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        setPreco(preco);
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public Produto(int id, String nome, double preco, int quantidadeEstoque) {
        this(id, nome, preco, quantidadeEstoque, Categoria.OUTROS);
    }

    public void setPreco(double novoPreco) {
        if (novoPreco > 0) {
            this.preco = novoPreco;
        } else {
            System.err.println("Erro de preço");
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public boolean registrarVenda(int quantidade) {
        if (this.quantidadeEstoque >= quantidade) {
            this.quantidadeEstoque -= quantidade;
            return true;
        }
        return false;
    }

    public double calcularValorVenda(int quantidade) {
        return this.preco * quantidade;
    }

    public double calcularValorVenda(int quantidade, double taxaImposto) {
        double subtotal = this.calcularValorVenda(quantidade);
        return subtotal * (1 + taxaImposto);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | nome: %s | preço: R$%.2f | estoque: %d | categoria: %s",
                id, nome, preco, quantidadeEstoque, categoria.getNomeExibicao());
    }
}