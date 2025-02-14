package main;

import model.Usuario;
import repository.UsuarioRepository;
import service.EstoqueService;
import service.VendaService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        List<Usuario> usuarios = usuarioRepository.carregar();

        System.out.print("Usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioLogado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.autenticar(senha)) {
                usuarioLogado = usuario;
                break;
            }
        }

        if (usuarioLogado == null) {
            System.out.println("Autenticação falhou!");
            return;
        }

        EstoqueService estoqueService = new EstoqueService();
        VendaService vendaService = new VendaService();

        while (true) {
            System.out.println("\n--- Sistema de Gerenciamento ---");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Registrar Venda");
            System.out.println("4. Relatório de Vendas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (usuarioLogado.getRole().equals("ADMIN")) {
                        System.out.print("Nome do produto: ");
                        String nome = scanner.nextLine();
                        System.out.print("Preço: ");
                        double preco = scanner.nextDouble();
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();
                        System.out.print("Nível mínimo de estoque: ");
                        int nivelMinimo = scanner.nextInt();
                        estoqueService.adicionarProduto(nome, preco, quantidade, nivelMinimo);
                    } else {
                        System.out.println("Acesso negado! Apenas administradores podem adicionar produtos.");
                    }
                    break;
                case 2:
                    System.out.println("\n--- Produtos ---");
                    for (Produto produto : estoqueService.listarProdutos()) {
                        System.out.println(produto);
                    }
                    break;
                case 3:
                    System.out.print("Nome do produto: ");
                    String nomeVenda = scanner.nextLine();
                    Produto produto = estoqueService.buscarProduto(nomeVenda);
                    if (produto != null) {
                        System.out.print("Quantidade: ");
                        int qtdVenda = scanner.nextInt();
                        vendaService.registrarVenda(produto, qtdVenda);
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n--- Relatório de Vendas ---");
                    for (Venda venda : vendaService.listarVendas()) {
                        System.out.println(venda);
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
