package dao;
/* @author guilherme.vcmoz*/
import beans.Oceanos;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OceanosDAO {
    private Conexao conexao;
    private Connection conn;
    
    
    public OceanosDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Oceanos oceanos) {
        String sql = "INSERT INTO oceanos (id, nome, area, profundidade) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, oceanos.getId());
            stmt.setString(2, oceanos.getNome());
            stmt.setInt(3, oceanos.getArea());
            stmt.setInt(4, oceanos.getProfundidade());
            stmt.execute();
            
        }catch(Exception e) {
            System.out.println("Erro ao inserir oceanos: " + e.getMessage());
        }
    }
    
    public void alterar(Oceanos oceanos) {
        String sql = "UPDATE oceanos SET Nome=?, Area=?, Profuncidade=? WHERE id=?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, oceanos.getId());
            stmt.setString(2, oceanos.getNome());
            stmt.setInt(3, oceanos.getArea());
            stmt.setInt(4, oceanos.getProfundidade());
            stmt.execute();
            
            }catch(Exception e) {
            System.out.println("Erro ao atualizar oceanos: " + e.getMessage());
        }
    }
    
   public void excluir(int id) {
        String sql = "DELETE FROM oceanos WHERE id = ?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            }catch(Exception e) {
            System.out.println("Erro ao excluir oceanos: " + e.getMessage());
        }
    }
   
   public Oceanos getOceanos(int id) {
       String sql = "SELECT * FROM oceanos WHERE id = ?";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Oceanos oceanos = new Oceanos();
            rs.next();
            oceanos.setId(rs.getInt("Id"));
            oceanos.setNome(rs.getString("nome"));
            oceanos.setArea(rs.getInt("area"));
            oceanos.setProfundidade(rs.getInt("profundidade"));
            return oceanos;

        }catch(Exception e) {
            System.out.println("Erro ao excluir oceanos: " + e.getMessage());
            return null;
        }
       
    }
   
   public  List<Oceanos> getOceanos() {
       String sql = "SELECT * FROM oceanos";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Oceanos> listaOceanos = new ArrayList<>();
            
            while(rs.next()) {
                Oceanos p = new Oceanos();
                p.setId(rs.getInt("Id"));
                p.setNome(rs.getString("nome"));
                p.setArea(rs.getInt("area"));
                p.setProfundidade(rs.getInt("profundidade"));
                listaOceanos.add(p);
        }
        return listaOceanos;
        }catch(Exception e) {
            return null;
        }
   }
}

