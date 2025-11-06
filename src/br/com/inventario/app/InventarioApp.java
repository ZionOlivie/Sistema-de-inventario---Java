package br.com.inventario.app;

import br.com.inventario.modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventarioApp {

    private static List<Produto> listaDeProdutos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int proximoId = 1;

    public static void main(String[] args) {

        System.out.println("Sistema de inventário - Feature 1 (Zion Olivie)");

        int opcao;

        while (true) {
            exibirMenu();

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Digite um número de 1 a 3.");
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
                    System.out.println("Saindo... Até mais!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção não reconhecid, tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Lista de produtos");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- CADASTRO DE PRODUTO ---");

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade em estoque: ");
        int quantidade = scanner.nextInt();

        Produto novoProduto = new Produto(proximoId++, nome, preco, quantidade);

        listaDeProdutos.add(novoProduto);

        System.out.println("Produto " + nome + " (ID: " + novoProduto.getId() + ") cadastrado!");
    }

    private static void listarProdutos() {
        System.out.println("\n--- PRODUTOS CADASTRADOS (" + listaDeProdutos.size() + ") ---");
        if (listaDeProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : listaDeProdutos) {
            System.out.println(p.toString());
        }
    }
}