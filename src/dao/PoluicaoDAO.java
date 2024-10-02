package dao;
/* @author guilherme.vcmoz*/
import beans.Poluicao;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PoluicaoDAO {
    private Conexao conexao;
    private Connection conn;
    
    
    public PoluicaoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Poluicao poluicao) {
        String sql = "INSERT INTO poluicao (id, nome, qtde, local ocorrencia) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, poluicao.getId());
            stmt.setString(2, poluicao.getTipo());
            stmt.setInt(3, poluicao.getQtde());
            stmt.setString(4, poluicao.getLocalocorrencia());
            stmt.execute();
            
        }catch(Exception e) {
            System.out.println("Erro ao inserir poluicao: " + e.getMessage());
        }
    }
    
    public void alterar(Poluicao poluicao) {
        String sql = "UPDATE poluicao SET Nome=?, Qtde=?, Local ocorrencia=? WHERE id=?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, poluicao.getId());
            stmt.setString(2, poluicao.getTipo());
            stmt.setInt(3, poluicao.getQtde());
            stmt.setString(4, poluicao.getLocalocorrencia());
            stmt.execute();
            
            }catch(Exception e) {
            System.out.println("Erro ao atualizar poluicao: " + e.getMessage());
        }
    }
    
   public void excluir(int id) {
        String sql = "DELETE FROM poluicao WHERE id = ?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            }catch(Exception e) {
            System.out.println("Erro ao excluir poluicao: " + e.getMessage());
        }
    }
   
   public Poluicao getPoluicao(int id) {
       String sql = "SELECT * FROM poluicao WHERE id = ?";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Poluicao poluicao = new Poluicao();
            rs.next();
            poluicao.setId(rs.getInt("Id"));
            poluicao.setTipo(rs.getString("nome"));
            poluicao.setQtde(rs.getInt("qtde"));
            poluicao.setLocalocorrencia(rs.getString("local ocorrencia"));
            return poluicao;

        }catch(Exception e) {
            System.out.println("Erro ao excluir poluicao: " + e.getMessage());
            return null;
        }
       
    }
   
   public  List<Poluicao> getPoluicao() {
       String sql = "SELECT * FROM poluicao";
       
       try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Poluicao> listaPoluicao = new ArrayList<>();
            
            while(rs.next()) {
                Poluicao p = new Poluicao();
                p.setId(rs.getInt("Id"));
                p.setTipo(rs.getString("nome"));
                p.setQtde(rs.getInt("qtde"));
                p.setLocalocorrencia(rs.getString("profundidade"));
                listaPoluicao.add(p);
        }
        return listaPoluicao;
        }catch(Exception e) {
            return null;
        }
   }
}

