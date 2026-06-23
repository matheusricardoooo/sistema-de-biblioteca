package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    private static String URL;
    private static String USUARIO;
    private static String SENHA;

    // Executa uma única vez quando a classe é carregada
    static {
        try {
            Properties prop = new Properties();

            InputStream input =
                    Conexao.class.getClassLoader()
                            .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado.");
            }

            prop.load(input);

            URL = prop.getProperty("url");
            USUARIO = prop.getProperty("usuario");
            SENHA = prop.getProperty("senha");

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar o arquivo config.properties",
                    e
            );
        }
    }

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(
                    URL,
                    USUARIO,
                    SENHA
            );

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao conectar com o banco de dados",
                    e
            );
        }
    }
}
