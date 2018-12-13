/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.bean.Produtos;
import classes.connection.Conexao;
import classes.mathematics.QuantidadeDeVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class MathDAO {
    
    public static ArrayList<Integer> readInteger() {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

     
        ArrayList<Integer> arrayInteger = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select count (data_venda), data_venda from vendas group by data_venda having count(data_venda)>=1 order by 1");
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                int xi = rs.getInt("count");
                arrayInteger.add(xi);
                System.out.println("oiiii "+ xi);
                
                      //cliente.setNome(rs.getString("nome_completo_cliente"));
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return arrayInteger;

    }
    
}
