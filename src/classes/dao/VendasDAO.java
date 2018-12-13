/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.bean.Produtos;
import classes.bean.Vendas;
import classes.connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class VendasDAO {
    
    public void salvar(Vendas c) {
        
        try (Connection con = Conexao.getConnection()){
            
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO vendas (cliente_venda, valor_desconto_venda, valor_total_venda, forma_pagamento_venda) values (?,?,?,?)");
            pstmt.setString(1, c.getCliente());
            pstmt.setInt(2, c.getValorDesconto());
            pstmt.setInt(3, c.getValorTotal());
            pstmt.setString(4, c.getFormaPagamento());
            pstmt.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
           
        }
    }
    
}
