package dao;

import database.Conexao; // pega os comandos do arquivo Conexao que está na pasta database
import model.Livro; // pega os comandos do arquivo da classe livro que está na pasta model

import java.sql.Connection; // representa a conexão com o banco de dados
import java.sql.PreparedStatement; // envia os comandos SQL para o postgre
import java.sql.SQLException; // captura os erros relacionados ao banco

import java.sql.ResultSet; // serve para realizar consultas no sql

public class LivroDao {
    /* Livro = tipo do parâmetro, ele só aceita o que foi determinado no objeto
    criarLivro= variável onde será armazenado a informação
     */
    public void cadastrarLivro(Livro livro) {

        // código para inserção de dados no banco
        String sql =
                "INSERT INTO livros" +
                        "(nome_livro, nome_autor, ano_publicado, disponibilidade)" +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            // código para preencher as interrogações (1 = ?)
            stmt.setString(1, livro.getNomeLivro());
            stmt.setString(2, livro.getNomeAutor());
            stmt.setInt(3, livro.getAnoPublicado());
            stmt.setBoolean(4, livro.getDisponibilidade());

            // passo final e execução do insert
            stmt.executeUpdate();

            System.out.println("Livro cadastrado com sucesso");
        }

        // caso a comunicação com o banco de dados falhe
        catch (SQLException e) {
            System.out.println("Erro ao cadastrar o livro");
            e.printStackTrace();
        }

    }

    public void listarLivros() {

        try (Connection conn = Conexao.conectar()){

            // código para selecionar a tabela no banco de dados
            String sql = "SELECT * FROM livros ";

            // preparar o comando (receber as informações pedidas do banco de dados
            PreparedStatement stmt = conn.prepareStatement(sql);

            // este comando serve para ser uma tabela temporária (armazena o resultado da consulta)
            ResultSet rs = stmt.executeQuery();

            // confirmação de que tem livros cadastrados ou não (validação)
            boolean encontrouLivro = false;

            // rs.next controla o while, o while é executado conforme a quantidade de livros cadastrados
            while (rs.next()) {

                // caso tenha livro cadastrado no banco
                encontrouLivro = true;

                /* serve para pegar os dados da linha
                id, nome, autor, ano vira uma espécie de variavel para simplificar no sout*/
                int id = rs.getInt("id_livro");
                String nome = rs.getString("nome_livro");
                String autor = rs.getString("nome_autor");
                int ano = rs.getInt("ano_publicado");
                boolean disponibilidade = rs.getBoolean("disponibilidade");

                // exibir os resultados da consulta (livros cadastrados e sua situação)
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Autor: " + autor);
                System.out.println("Ano: " + ano);
                System.out.println("Disponibilidade: " + disponibilidade);
                System.out.println();
            }

            // caso não tenha nenhum livro cadastrado
            if (!encontrouLivro) {
                System.out.println("Não há livros cadastrados");
            }

        }

        // caso haja algum erro na consulta com o banco de dados
        catch (SQLException e) {
            System.out.println("Erro ao visualizar livros cadastrados");
            e.printStackTrace();
        }
    }

    public void buscarLivroPorId (int id) {

        try (Connection conn = Conexao.conectar()){

            // consulta no banco de dados a partir do id
            String sql =
                    "SELECT * FROM livros " +
                    "WHERE id_livro = ?";

            // prepara o código para ser executado
            PreparedStatement stmt = conn.prepareStatement(sql);

            // colocar valor no lugar da interrogação
            stmt.setInt(1, id);

            // executar a consulta
            ResultSet rs = stmt.executeQuery();

            // verificação caso exista ou não o livro
            if (rs.next()) {
                int codigo = rs.getInt("id_livro");
                String nome = rs.getString("nome_livro");
                String autor = rs.getString("nome_autor");
                int ano = rs.getInt("ano_publicado");
                boolean disponibilidade = rs.getBoolean("disponibilidade");
                String status = disponibilidade ? "Sim" : "Não";

                System.out.println("ID: " + codigo);
                System.out.println("Nome: " + nome);
                System.out.println("Autor: " + autor);
                System.out.println("Ano: " + ano);
                System.out.println("Disponível: " + status);

            }
            else {
                System.out.println("Livro não cadastrado");
            }
        }
        // caso haja algum erro durante a consulta no banco de dados
        catch (SQLException e) {
            System.out.println("Erro ao buscar livro");
            e.printStackTrace();
        }
    }

    public void deletarLivro (int id) {

        try {

            // abre a conexão com o banco de dados
           Connection conn = Conexao.conectar();
           // realiza a consulta nas tabelas do banco de dados
            String sql =
                    "DELETE FROM livros " + "WHERE id_livro = ?";

            // prepara o código para ser executado
            PreparedStatement stmt = conn.prepareStatement(sql);

            // colocar valor no lugar da interrogação
            stmt.setInt(1, id);

            // apaga a linha a partir do id
            int linhasAfetadas = stmt.executeUpdate();

            // verificação de exclusão
            if (linhasAfetadas > 0) {
                System.out.println("Remoção de livro realizada com sucesso");
            }
            else {
                System.out.println("Livro não encontrado");
            }
        }
        // caso dê erro na consulta no banco de dados
        catch (SQLException e) {
            System.out.println("Erro na exclusão do livro");
            e.printStackTrace();
        }

    }
}
