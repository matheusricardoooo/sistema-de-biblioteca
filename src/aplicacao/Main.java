package aplicacao;
import dao.LivroDao;
import dao.UsuarioDao;
import model.Livro;
import model.Usuario;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final LivroDao livroDao = new LivroDao();
    private static final UsuarioDao usuarioDao = new UsuarioDao();
    public static void main (String[] args) {
        int opcao;
        do {
            exibirMenu();
            System.out.println("Escolha uma opção");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;

                case 2:
                    listarUsuarios();
                    break;

                case 3:
                    buscarUsuario();
                    break;

                case 4:
                    deletarUsuario();
                    break;

                case 5:
                    cadastrarLivro();
                    break;

                case 6:
                    listarUsuarios();
                    break;

                case 7:
                    buscarLivroPorId();
                    break;

                case 8:
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

        System.out.println("[1] - CADASTRAR NOVO USUÁRIO");
        System.out.println("[2] - VISUALIZAR USUÁRIOS CADASTRADOS");
        System.out.println("[3] - BUSCAR USUÁRIO");
        System.out.println("[4] - EXCLUIR USUÁRIO");
        System.out.println("[5] - CADASTRAR NOVO LIVRO");
        System.out.println("[6] - VISUALIZAR LIVROS CADASTRADOS");
        System.out.println("[7] - BUSCAR LIVRO PELO ID");
        System.out.println("[8] - EXCLUIR LIVRO");
        System.out.println("[0] - ENCERRAR O PROGRAMA");
        System.out.println("============================");
    }

    public static void cadastrarUsuario() {
        Usuario usuario = new Usuario();

        System.out.print("Digite o nome do usuário: ");
        usuario.setNomeUsuario(sc.nextLine());

        System.out.print("Digite o telefone do usuário: ");
        usuario.setTelefoneUsuario(sc.nextLine());

        usuarioDao.cadastrarUsuario(usuario);
    }

    public static void listarUsuarios() {
        usuarioDao.listarUsuario();
    }

    public static void buscarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nomeBusca = sc.nextLine();
        usuarioDao.buscarUsuarioPorNome(nomeBusca);
    }

    public static void deletarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nomeDeletar = sc.nextLine();
        usuarioDao.deletarUsuario(nomeDeletar);
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
        int idLivro = sc.nextInt();
        sc.nextLine();
        livroDao.buscarLivroPorId(idLivro);
    }

    public static void deletarLivro() {
        System.out.print("Digite o ID do livro: ");
        int id = sc.nextInt();
        sc.nextLine();
        livroDao.deletarLivro(id);
    }
}