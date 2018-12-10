/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.bean.Produtos;
import classes.connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class ProdutosDAO {
    
    public void salvar(Produtos c) {
        
        try (Connection con = Conexao.getConnection()){
            
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO produtos (descricao_produto, preco_unitario_produto, unidade_produto) values (?,?,?)");
            pstmt.setString(1, c.getDescricao());
            pstmt.setString(2, c.getPreco());
            pstmt.setString(3, c.getUnidade());
            pstmt.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
           
        }
    }
    public List<Produtos> read() {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produtos> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos produto = new Produtos();

                produto.setCodigoProduto(rs.getInt("codigo_produto"));
                produto.setDescricao(rs.getString("descricao_produto"));
                produto.setPreco(rs.getString("preco_unitario_produto"));
                produto.setUnidade(rs.getString("unidade_produto"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return produtos;

    }
    public void update(Produtos p) {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET descricao_produto = ? ,preco_unitario_produto = ?,unidade_produto = ? WHERE codigo_produto = ?");
            
            stmt.setString(1, p.getDescricao());
            stmt.setString(2, p.getPreco());
            stmt.setString(3, p.getUnidade());
            stmt.setInt(4, p.getCodigoProduto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }

    }
    public void delete(Produtos p) {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE codigo_produto = ?");
            stmt.setInt(1, p.getCodigoProduto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }
    }
    public List<Produtos> readForDesc(String desc) {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produtos> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE descricao_produto LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos produto = new Produtos();

                produto.setCodigoProduto(rs.getInt("codigo_produto"));
                produto.setDescricao(rs.getString("descricao_produto"));
                produto.setPreco(rs.getString("preco_unitario_produto"));
                produto.setUnidade(rs.getString("unidade_produto"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }
}
