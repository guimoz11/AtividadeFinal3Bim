package dao;
/* @author guilherme.vcmoz*/
import beans.Conservacao;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConservacaoDAO {
    private Conexao conexao;
    private Connection conn;
    
    
    public ConservacaoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Conservacao conservacao) {
        String sql = "INSERT INTO conservacao (id, nome, pais, ano) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, conservacao.getId());
            stmt.setString(2, conservacao.getNome());
            stmt.setString(3, conservacao.getPais());
            stmt.setInt(4, conservacao.getAno());
            stmt.execute();
            
        }catch(Exception e) {
            System.out.println("Erro ao inserir conservacao: " + e.getMessage());
        }
    }
    
    public void alterar(Conservacao conservacao) {
        String sql = "UPDATE conservacao SET Nome=?, Pais=?, Ano=? WHERE id=?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, conservacao.getId());
            stmt.setString(2, conservacao.getNome());
            stmt.setString(3, conservacao.getPais());
            stmt.setInt(4, conservacao.getAno());
            stmt.execute();
            
            }catch(Exception e) {
            System.out.println("Erro ao atualizar conservacao: " + e.getMessage());
        }
    }
    
   public void excluir(int id) {
        String sql = "DELETE FROM conservacao WHERE id = ?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            }catch(Exception e) {
            System.out.println("Erro ao excluir conservacao: " + e.getMessage());
        }
    }
   
   public Conservacao getConservacao(int id) {
       String sql = "SELECT * FROM conservacao WHERE id = ?";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Conservacao conservacao = new Conservacao();
            rs.next();
            conservacao.setId(rs.getInt("Id"));
            conservacao.setNome(rs.getString("nome"));
            conservacao.setPais(rs.getString("pais"));
            conservacao.setAno(rs.getInt("ano"));
            return conservacao;

        }catch(Exception e) {
            System.out.println("Erro ao excluir conservacao: " + e.getMessage());
            return null;
        }
       
    }
   
   public  List<Conservacao> getConservacao() {
       String sql = "SELECT * FROM conservacao";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Conservacao> listaConservacao = new ArrayList<>();
            
            while(rs.next()) {
                Conservacao p = new Conservacao();
                p.setId(rs.getInt("Id"));
                p.setNome(rs.getString("nome"));
                p.setPais(rs.getString("pais"));
                p.setAno(rs.getInt("ano"));
                listaConservacao.add(p);
        }
        return listaConservacao;
        }catch(Exception e) {
            return null;
        }
   }
}

