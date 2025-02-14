package main;

import model.Usuario;
import service.EstoqueService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstoqueService estoqueService = new EstoqueService();

        // Lista de usuários cadastrados
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("gerente", "senha123", "GERENTE"));
        usuarios.add(new Usuario("vendedor", "senha456", "VENDEDOR"));

        // Autenticação
        Usuario usuarioLogado = null;
        while (usuarioLogado == null) {
            System.out.print("Usuário: ");
            String nomeUsuario = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            for (Usuario usuario : usuarios) {
                if (usuario.getNome().equals(nomeUsuario) && usuario.autenticar(senha)) {
                    usuarioLogado = usuario;
                    break;
                }
            }

            if (usuarioLogado == null) {
                System.out.println("Usuário ou senha incorretos. Tente novamente.");
            }
        }

        System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");

        // Menu interativo
        while (true) {
            System.out.println("\n--- Sistema de Gerenciamento de Estoque ---");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Remover Produto");
            System.out.println("3. Registrar Entrada");
            System.out.println("4. Registrar Saída");
            System.out.println("5. Verificar Alertas de Estoque");
            System.out.println("6. Gerar Relatório de Movimentação");
            System.out.println("7. Mostrar Produtos");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: // Adicionar Produto
                    if (usuarioLogado.getRole().equals("GERENTE")) {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Preço: ");
                        double preco = scanner.nextDouble();
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();
                        System.out.print("Nível Mínimo: ");
                        int nivelMinimo = scanner.nextInt();
                        estoqueService.adicionarProduto(new Produto(id, nome, preco, quantidade, nivelMinimo));
                    } else {
                        System.out.println("Acesso negado! Apenas o gerente pode adicionar produtos.");
                    }
                    break;
                case 2: // Remover Produto
                    if (usuarioLogado.getRole().equals("GERENTE")) {
                        System.out.print("ID do produto a ser removido: ");
                        int idRemover = scanner.nextInt();
                        estoqueService.removerProduto(idRemover);
                    } else {
                        System.out.println("Acesso negado! Apenas o gerente pode remover produtos.");
                    }
                    break;
                case 3: // Registrar Entrada
                    if (usuarioLogado.getRole().equals("GERENTE")) {
                        System.out.print("ID do produto: ");
                        int idEntrada = scanner.nextInt();
                        System.out.print("Quantidade de entrada: ");
                        int quantidadeEntrada = scanner.nextInt();
                        estoqueService.registrarEntrada(idEntrada, quantidadeEntrada);
                    } else {
                        System.out.println("Acesso negado! Apenas o gerente pode registrar entradas.");
                    }
                    break;
                case 4: // Registrar Saída
                    if (usuarioLogado.getRole().equals("GERENTE")) {
                        System.out.print("ID do produto: ");
                        int idSaida = scanner.nextInt();
                        System.out.print("Quantidade de saída: ");
                        int quantidadeSaida = scanner.nextInt();
                        estoqueService.registrarSaida(idSaida, quantidadeSaida);
                    } else {
                        System.out.println("Acesso negado! Apenas o gerente pode registrar saídas.");
                    }
                    break;
                case 5: // Verificar Alertas
                    estoqueService.verificarAlertasEstoqueMinimo();
                    break;
                case 6: // Gerar Relatório
                    estoqueService.gerarRelatorioMovimentacao();
                    break;
                case 7: // Mostrar Produtos
                    estoqueService.mostrarProdutos();
                    break;
                case 8: // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
