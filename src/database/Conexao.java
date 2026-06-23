package database;

import java.sql.Connection; // import para conexão do banco de dados e o java
import java.sql.DriverManager; // import responsável pela abertura da conexão
import java.sql.SQLException; // import responsável pela exibição do erros ao banco de dados

public class Conexao { // linha que declara o método
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "6284387";

    public static Connection conectar() {

        try { // (tente executar este código, caso der erro, parta para o catch)
            // aqui ele faz a verificação dos itens do url, usuario e senha
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA); //
            return conn;
        }

        catch (SQLException e) { // serve para avisar que houve um erro
            throw new RuntimeException(
                    "Erro ao conectar com o banco de dados",
                    e
            );
        }
    }
}
