package br.com.inventario.app;

import br.com.inventario.modelo.Categoria;
import br.com.inventario.modelo.Pedido;
import br.com.inventario.modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventarioApp {

    private static List<Produto> listaDeProdutos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int proximoId = 1;

    public static void main(String[] args) {

        System.out.println("Sistema Básico de Inventário - Feature 3 (Zion)");

        int opcao;

        while (true) {
            exibirMenu();
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida, igite um número inteiro");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    registrarVenda();
                    break;
                case 4:
                    System.out.println("Saindo do sistema..");
                    scanner.close();
                    return;
                default:
                    if (!(opcao >= 1 && opcao <= 4)) {
                        System.out.println("Opção nao existe");
                    }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Listar todos os produtos");
        System.out.println("3 - Registrar venda");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- CADASTRO ---");

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Produto novoProduto = new Produto(proximoId++, nome, preco, quantidade);
        listaDeProdutos.add(novoProduto);

        System.out.println("Produto " + nome + " (ID: " + novoProduto.getId() + ") cadastrado");
    }

    private static void listarProdutos() {
        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        if (listaDeProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        System.out.println("Itens com estoque esgotado não listados");
        int produtosEmEstoque = 0;

        for (Produto p : listaDeProdutos) {

            if (p.getQuantidadeEstoque() <= 0) {
                continue;
            }

            System.out.println(p.toString());
            produtosEmEstoque++;
        }

        if (produtosEmEstoque == 0) {
            System.out.println("Nenhum produto disponível em estoque.");
        }
        System.out.println("------------------------------------");
    }

    private static void registrarVenda() {
        System.out.println("\n--- REGISTRO DE VENDA ---");

        int idVenda = -1;
        do {
            System.out.print("Digite o ID do produto para venda (0 para voltar): ");
            if (scanner.hasNextInt()) {
                idVenda = scanner.nextInt();
                scanner.nextLine();

                if (idVenda == 0) {
                    System.out.println("Venda cancelada");
                    return;
                }
                break;
            } else {
                System.out.println("Entrada inválida por favor, digite um número inteiro.");
                scanner.nextLine();
            }
        } while (true);

        Produto produtoParaVenda = encontrarProdutoPorId(idVenda);

        if (produtoParaVenda == null) {
            System.out.println("Produto com ID " + idVenda + " não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade a vender: ");
        int quantidadeVenda = scanner.nextInt();
        scanner.nextLine();

        if (quantidadeVenda > 0 && produtoParaVenda.getQuantidadeEstoque() >= quantidadeVenda) {

            produtoParaVenda.registrarVenda(quantidadeVenda);
            System.out.println("Venda de " + quantidadeVenda + " unidades de " + produtoParaVenda.getNome() + " registrada.");
            System.out.println("Estoque atual: " + produtoParaVenda.getQuantidadeEstoque());

        } else if (quantidadeVenda <= 0) {
            System.out.println("A quantidade vendida deve ser positiva.");

        } else {
            System.out.println("Estoque insuficiente! Disponível: " + produtoParaVenda.getQuantidadeEstoque());
        }
    }

    private static Produto encontrarProdutoPorId(int id) {
        for (Produto p : listaDeProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}