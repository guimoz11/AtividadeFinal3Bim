package dao;
/* @author guilherme.vcmoz*/
import beans.Recursos;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecursosDAO {
    private Conexao conexao;
    private Connection conn;
    
    
    public RecursosDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Recursos recursos) {
        String sql = "INSERT INTO recursos (id, tipo, qtde, unidade) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, recursos.getId());
            stmt.setString(2, recursos.getTipo());
            stmt.setString(3, recursos.getQtde());
            stmt.setString(4, recursos.getUnidade());
            stmt.execute();
            
        }catch(Exception e) {
            System.out.println("Erro ao inserir recursos: " + e.getMessage());
        }
    }
    
    public void alterar(Recursos recursos) {
        String sql = "UPDATE recursos SET Tipo=?, Qtde=?, Unidade=? WHERE id=?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, recursos.getId());
            stmt.setString(2, recursos.getTipo());
            stmt.setString(3, recursos.getQtde());
            stmt.setString(4, recursos.getUnidade());
            stmt.execute();
            
            }catch(Exception e) {
            System.out.println("Erro ao atualizar recursos: " + e.getMessage());
        }
    }
    
   public void excluir(int id) {
        String sql = "DELETE FROM recursos WHERE id = ?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            }catch(Exception e) {
            System.out.println("Erro ao excluir recursos: " + e.getMessage());
        }
    }
   
   public Recursos getRecursos(int id) {
       String sql = "SELECT * FROM recursos WHERE id = ?";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Recursos recursos = new Recursos();
            rs.next();
            recursos.setId(rs.getInt("Id"));
            recursos.setTipo(rs.getString("nome"));
            recursos.setQtde(rs.getString("pais"));
            recursos.setUnidade(rs.getString("ano"));
            return recursos;

        }catch(Exception e) {
            System.out.println("Erro ao excluir recursos: " + e.getMessage());
            return null;
        }
       
    }
   
   public  List<Recursos> getRecursos() {
       String sql = "SELECT * FROM recursos";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Recursos> listaRecursos = new ArrayList<>();
            
            while(rs.next()) {
                Recursos p = new Recursos();
                p.setId(rs.getInt("Id"));
                p.setTipo(rs.getString("nome"));
                p.setQtde(rs.getString("pais"));
                p.setUnidade(rs.getString("ano"));
                listaRecursos.add(p);
        }
        return listaRecursos;
        }catch(Exception e) {
            return null;
        }
   }
}

