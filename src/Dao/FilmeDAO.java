
package Dao;

import Conexao.Conexao;
import Modelo.Filme;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
*Classe responsavel pelas operações de persistencia e consulta de registro de filmes do banco de dados Atividade1.
*@author Henrique Carvalho de Vasconcelos
*@version 2.0
*/

public class FilmeDAO {
    /**
    *Realiza a inserção de um novo registro de filme no banco de dados.
    *@param filme Objeto contendo os dados a serem persistidos.
    *@return {@code true} se o  cadastro for conclúido com sucesso, {@code false} caso contrario.
    */
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
    /**
    *Consultar a lista de filmes com opções de filtro por categoria.
    *Se a categoria informadar for vazia ou null, retornar todos os filmes cadastrados.
    *
    *@param categoria Termo a ser pesquisado na coluna de categoria (buscar parcial via LIKE).
    *@return Lista de objetos {@link Filme} encontrado.
    */
    public List<Filme> listar(String categoria){
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT id, nome, datalancamento, categoria FROM filmes WHERE categoria LIKE ? ORDER BY id DESC";
        
        try(Connection conn = Conexao.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            String filtro = (categoria == null || categoria.trim().isEmpty())? "%" : "%" + categoria.trim() + "%";
            stmt.setString(1, filtro);
            
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Filme f = new Filme();
                    f.setId(rs.getInt("id"));
                    f.setNome(rs.getString("nome"));
                    f.setDataLancamento(rs.getDate("dataLancamento").toLocalDate());
                    f.setCategoria(rs.getString("categoria"));
                    filmes.add(f);
                }
            }
        }catch(SQLException e){
            System.err.println("Não foi possível consultar os dados! erro ao carregar a lista de filmes. Detalhes: " + e.getMessage());
        }
        return filmes;
    }
    /**
    *Atualiza os dados de um filme já existente no banco de dados a partir de seu identificador.
    *
    *@param filme Objeto contendo o ID e os novos dados a serem salvos.
    *@return  {@code true} se o registro foi atualizado , {@code false} caso ocorra falha.
    */
    public boolean atualizar(Filme filme){
        String sql = "UPDATE filmes SET nome = ?, dataLancamento = ?, categoria = ? WHERE  id = ?";
        
        try(Connection conn = Conexao.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, filme.getNome());
            stmt.setDate(2, Date.valueOf(filme.getDataLancamento()));
            stmt.setString(3, filme.getCategoria());
            stmt.setInt(4, filme.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }catch(SQLException e){
            System.err.println("Não foi possivel atualizar o registro ! verifique os dados fornecidos. Detalhe: " + e.getMessage());
            return false;
        }
    }
    /**
    *Exclui um filme do banco de dados a partir de seu identificador exclusivo.
    *
    *@param id Identificador numérico do filme.
    *@return {@code true} se a exclusão for efetuada com sucesso, {@code false} em caso de erro.
    */
    public boolean excluir(int id){
        String sql = "DELETE FROM filmes WHERE id = ?";
        
        try (Connection conn = Conexao.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }catch(SQLException e){
            System.err.println("Não foi possivel excluir o filme selecionado! verifique a conexão com o banco. Detalhes: " + e.getMessage());
            return false;
        }    
    }
}
        
