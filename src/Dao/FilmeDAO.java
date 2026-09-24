
package Dao;

import Conexao.Conexao;
import Modelo.Filme;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmeDAO {
    public boolean cadastrar(Filme filme){
        String sql = "INSERT INTO filmes (nome, datalancamento, categoria) VALUES (?, ?, ?)";
        
        try(Connection conn = Conexao.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, filme.getNome());
            stmt.setDate(2, Date.valueOf(filme.getDataLancamento()));
            stmt.setString(3, filme.getCategoria());
            
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Erro ao cadastrar filme: " + e.getMessage());
            return false;
        }
    }
}
        