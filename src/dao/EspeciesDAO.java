package dao;
/* @author guilherme.vcmoz*/
import beans.Especies;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EspeciesDAO {
    private Conexao conexao;
    private Connection conn;
    
    
    public EspeciesDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Especies especies) {
        String sql = "INSERT INTO especies (id, nome, classificacao, conservacao) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, especies.getId());
            stmt.setString(2, especies.getNome());
            stmt.setString(3, especies.getClassificacao());
            stmt.setString(4, especies.getConservacao());
            stmt.execute();
            
        }catch(Exception e) {
            System.out.println("Erro ao inserir especies: " + e.getMessage());
        }
    }
    
    public void alterar(Especies especies) {
        String sql = "UPDATE especies SET Nome=?, Classificacao=?, Conservacao=? WHERE id=?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, especies.getId());
            stmt.setString(2, especies.getNome());
            stmt.setString(3, especies.getClassificacao());
            stmt.setString(4, especies.getConservacao());
            stmt.execute();
            
            }catch(Exception e) {
            System.out.println("Erro ao atualizar especies: " + e.getMessage());
        }
    }
    
   public void excluir(int id) {
        String sql = "DELETE FROM especies WHERE id = ?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            }catch(Exception e) {
            System.out.println("Erro ao excluir especies: " + e.getMessage());
        }
    }
   
   public Especies getEspecies(int id) {
       String sql = "SELECT * FROM especies WHERE id = ?";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Especies especies = new Especies();
            rs.next();
            especies.setId(rs.getInt("Id"));
            especies.setNome(rs.getString("nome"));
            especies.setClassificacao(rs.getString("classificacao"));
            especies.setConservacao(rs.getString("conservacao"));
            return especies;

        }catch(Exception e) {
            System.out.println("Erro ao excluir especies: " + e.getMessage());
            return null;
        }
       
    }
   
   public  List<Especies> getEspecies() {
       String sql = "SELECT * FROM especies";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Especies> listaEspecies = new ArrayList<>();
            
            while(rs.next()) {
                Especies p = new Especies();
                p.setId(rs.getInt("Id"));
                p.setNome(rs.getString("nome"));
                p.setClassificacao(rs.getString("classificacao"));
                p.setConservacao(rs.getString("profundidade"));
                listaEspecies.add(p);
        }
        return listaEspecies;
        }catch(Exception e) {
            return null;
        }
   }
}

