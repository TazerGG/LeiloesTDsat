/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        try {        
            String sql = "INSERT INTO PRODUTOS (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, "A Venda");
            
            prep.executeUpdate();
            
            prep.close();
            
            JOptionPane.showMessageDialog(null, "Produto: " + produto.getNome() + ". Cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void venderProduto(int id) {
        conn = new conectaDAO().connectDB();
        try {
            String sql = "UPDATE PRODUTOS SET status = 'Vendido' WHERE id = ?";
            prep = conn.prepareStatement(sql);
            
            prep.setInt(1, id);
            
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        
        try {
            String sql = "SELECT * FROM PRODUTOS";
            Statement stm = conn.createStatement();
            resultset = stm.executeQuery(sql);
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
            resultset.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listagem;
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        conn = new conectaDAO().connectDB();
        
        try {
            String sql = "SELECT * FROM PRODUTOS WHERE status = 'Vendido'";
            Statement stm = conn.createStatement();
            resultset = stm.executeQuery(sql);
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
            resultset.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listagem;
    }
    
    
    
        
}

