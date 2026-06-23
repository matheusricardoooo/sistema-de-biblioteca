package aplicacao;
import dao.LivroDao;
import model.Livro;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final LivroDao livroDao = new LivroDao();
    public static void main (String[] args) {
        int opcao;
        do {
            exibirMenu();
            System.out.println("Escolha uma opção");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;

                case 2:
                    listarLivros();
                    break;

                case 3:
                    buscarLivroPorId();
                    break;

                case 4:
                    deletarLivro();
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção Inválida");
            }
        }
        while (opcao !=0);
        sc.close();
    }

    public static void exibirMenu() {
        System.out.println("============================");
        System.out.println("    SISTEMA DE BIBLIOTECA   ");
        System.out.println("============================");

        System.out.println("[1] - CADASTRAR NOVO LIVRO");
        System.out.println("[2] - VISUALIZAR LIVROS CADASTRADOS");
        System.out.println("[3] - BUSCAR LIVRO PELO ID");
        System.out.println("[4] - EXCLUIR LIVRO");
        System.out.println("[0] - ENCERRAR O PROGRAMA");
        System.out.println("============================");
    }

    public static void cadastrarLivro() {
        Livro livro = new Livro();

        System.out.print("Digite o nome do livro: ");
        livro.setNomeLivro(sc.nextLine());

        System.out.print("Digite o nome do autor: ");
        livro.setNomeAutor(sc.nextLine());

        System.out.print("Digite o ano que o livro foi publicado: ");
        livro.setAnoPublicado(sc.nextInt());
        sc.nextLine();

        livro.setDisponibilidade(true);
        livroDao.cadastrarLivro(livro);
    }

    public static void listarLivros() {
        livroDao.listarLivros();
    }

    public static void buscarLivroPorId() {
        System.out.print("Digite o ID do livro: ");
        int id = sc.nextInt();
        sc.nextLine();
        livroDao.buscarLivroPorId(id);
    }

    public static void deletarLivro() {
        System.out.print("Digite o ID do livro: ");
        int id = sc.nextInt();
        sc.nextLine();
        livroDao.deletarLivro(id);
    }
}