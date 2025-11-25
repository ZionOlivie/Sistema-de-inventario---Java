package br.com.inventario.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido {
    private int id;
    private LocalDateTime dataPedido;
    private String cliente;
    private List<Produto> itensVendidos;
    private double valorTotal;

    public Pedido() {
        this.dataPedido = LocalDateTime.now();
        this.itensVendidos = new ArrayList<>();
        this.cliente = "Cliente padrao";
    }

    public Pedido(int id, String cliente) {
        this();
        this.id = id;
        this.cliente = cliente;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        for(int i = 0; i < quantidade; i++) {
            this.itensVendidos.add(produto);
        }
        this.valorTotal += produto.calcularValorVenda(quantidade, 0.0);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        String nomesProdutos = itensVendidos.stream()
                .map(Produto::getNome)
                .distinct()
                .collect(Collectors.joining(", "));

        return String.format("Pedido ID: %d | cliente: %s | data: %s | itens unicos: %s | total: R$%.2f",
                id, cliente, dataPedido.toLocalTime(), nomesProdutos, valorTotal);
    }
}