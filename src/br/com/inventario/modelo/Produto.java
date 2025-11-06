package br.com.inventario.modelo;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean registrarVenda(int quantidade) {
        if (this.quantidadeEstoque >= quantidade) {
            this.quantidadeEstoque -= quantidade;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nome: " + nome +
                " | Preço: R$" + String.format("%.2f", preco) +
                " | Estoque: " + quantidadeEstoque;
    }
}