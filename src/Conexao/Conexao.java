package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gerencia a conexão com o banco de dados MySQL para a aplicação Cenaflix
 * @author Henrique Vasconcelos
 * @version 1.0
 */
public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/Atividade1?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    /**
     * Estabelece e retorna uma conexão ativa com o banco de dados
     * @return Objeto {@link Connection} aberto, ou {@code null} caso ocarra falha.
     */
    public static Connection getConexao(){
        try{
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }catch(SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
