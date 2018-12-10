/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import classes.bean.Clientes;
import classes.bean.Produtos;
import classes.connection.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ClientesDAO {
    
     public void salvar(Clientes c) {
        
        try (Connection con = Conexao.getConnection()){
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO clientes (cpf_cliente, nome_completo_cliente,"
                    + "rua_endereco, complemento_endereco, cidade_endereco, setor_endereco, uf_endereco, cep_endereco,"
                    + "ddd_telefone, numero_telefone, tipo_telefone) values (?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, c.getCpf());
            pstmt.setString(2, c.getNome());
            pstmt.setString(3, c.getRua());
            pstmt.setString(4, c.getComplemento());
            pstmt.setString(5, c.getCidade());
            pstmt.setString(6, c.getSetor());
            pstmt.setString(7, c.getUf());
            pstmt.setString(8, c.getCep());
            pstmt.setString(9, c.getDdd());
            pstmt.setString(10, c.getNumero());
            pstmt.setString(11, c.getTipo());
            pstmt.executeUpdate();
   
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
           
        }
    }
     /*Public List<Cliente> read() {
     
     Connection con = ConnectionFactory.getConnection();
     
     PreparedStatement stmt = null;
     ResultSet rs = null;
     
     List<Clientes> clientes = new ArrayList<>();
     
     try {
     stmt = con.prepareStatement("SELECT * FROM produto");
     rs = stmt.executeQuery();
     
     
     while (rs.next()) {
     
     Clientes cliente = new Clientes();
     
     produto.setId(rs.getInt("id"));
     produto.setDescricao(rs.getString("descricao"));
     produto.setQtd(rs.getInt("qtd"));
     produto.setPreco(rs.getDouble("preco"));
     produtos.add(produto);
     }
     
     } catch (SQLException ex) {
     Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
     } finally {
     ConnectionFactory.closeConnection(con, stmt, rs);
     }
     
     return produtos;
     
     }
*/
    public List<Clientes> readForDesc(String desc) {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Clientes> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE cpf_cliente LIKE ?");
            stmt.setString(1, "%"+desc+"%");
                 
            rs = stmt.executeQuery();

            while (rs.next()) {

                Clientes cliente = new Clientes();
                cliente.setNome(rs.getString("nome_completo_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                clientes.add(cliente);
                
            }
           

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }
    
    public List<Clientes> readForEnd(String desc) {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;

        List<Clientes> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE cpf_cliente LIKE ?");
            stmt.setString(1, "%"+desc+"%");              
            rs = stmt.executeQuery();
            
            
            
            while (rs.next()) {

                Clientes cliente = new Clientes();
                cliente.setNome(rs.getString("nome_completo_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                clientes.add(cliente);
                
            }
            
           stmt2 = con.prepareStatement("SELECT * FROM enderecos WHERE codigo_endereco LIKE ?");
            
           
                
            
                   
           
         
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }
}
