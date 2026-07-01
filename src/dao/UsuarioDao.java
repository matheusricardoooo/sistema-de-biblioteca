package dao;
import database.Conexao;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    public void cadastrarUsuario(Usuario usuario) {
        String sql =
                "INSERT INTO usuarios" +
                        "(nome_usuario, telefone_usuario)" +
                        "VALUES( ?, ?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            // código para preencher as interrogações (1 = ?)
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getTelefoneUsuario());

            // passo final e execução do insert
            stmt.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso");
        }
        // caso a comunicação com o banco de dados falhe
        catch (SQLException e) {
            System.out.println("Erro ao cadastrar o usuário");
            e.printStackTrace();
        }
    }

    public void listarUsuario() {
        try (
                Connection conn = Conexao.conectar();
        ) {

            String sql = "SELECT * FROM usuarios";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            boolean encontrouUsuario = false;
            while(rs.next()) {
                encontrouUsuario = true;
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome_usuario");
                String telefone = rs.getString("telefone_usuario");
                System.out.println("ID: " + id);
                System.out.println("NOME: " + nome);
                System.out.println("TELEFONE: " + telefone);
            }
            if(!encontrouUsuario) {
                System.out.println("Não há usuários cadastrados");
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao listar os usuários cadastrados");
            e.printStackTrace();
        }
    }

    // nome: variável que vai guardar o que o usuário digitar na main
    public void buscarUsuarioPorNome(String nome) {

        String sql =
                "SELECT * FROM usuarios " + "WHERE nome_usuario = ?";
        try(
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nomeUsuario = rs.getString("nome_usuario");
                String telefoneUsuario = rs.getString("telefone_usuario");

                System.out.println("Usuário encontrado!");
                System.out.println("ID DO USUÁRIO: " + idUsuario);
                System.out.println("NOME DO USUÁRIO: " + nomeUsuario);
                System.out.println("TELEFONE DO USUÁRIO: " + telefoneUsuario);
            }
            else {
                System.out.println("Usuário não encontrado");
            }

        }
        catch (SQLException e) {
            System.out.println("Erro ao encontrar usuário");
            e.printStackTrace();
        }
    }

    public void deletarUsuario(String nome) {
        String sql =
                "DELETE FROM usuarios " + "WHERE nome_usuario = ?";
        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // colocar valor no lugar da interrogação
            stmt.setString(1, nome);

            // apaga a linha a partir do id
            int linhasAfetadas = stmt.executeUpdate();

            // verificação de exclusão
            if (linhasAfetadas > 0) {
                System.out.println("Remoção do usuário realizada com sucesso");
            }
            else {
                System.out.println("Usuário não encontrado");
            }
        }
        // caso dê erro na consulta no banco de dados
        catch (SQLException e) {
            System.out.println("Erro na exclusão do usuário");
            e.printStackTrace();
        }
    }
}
