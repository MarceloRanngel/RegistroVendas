/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.mathematics;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author marce
 */
public class Matematicos {
    
    public static ArrayList<QuantidadeDeVendas> DadosArrumados(ArrayList<Integer> xiDesorganizado){
        for (int i = 0; i < xiDesorganizado.size(); i++) {
            System.out.println("xi  " + xiDesorganizado.get(i));
        }
        ArrayList<Integer> xiOrganizado = new ArrayList<>();
        ArrayList<QuantidadeDeVendas> qtdVendasArray = new ArrayList<>();
        
        HashSet<Integer> hashSet = new HashSet<Integer>(xiDesorganizado);  
        xiOrganizado.addAll(hashSet);
        
        ArrayList<Integer> ni = new ArrayList<>();
        
        for(int i = 0;i < xiDesorganizado.size(); i++){
            Integer contador = 0;
                for (int j = 0; j < xiDesorganizado.size(); j++) {
                    if (xiOrganizado.get(i).equals(xiDesorganizado.get(j))) {
                        contador++;
                    }
                }
                ni.add(i, contador);
        }
        
        //niSoma
        int niSoma = 0;
        for (int i = 0; i < ni.size(); i++) {
            niSoma = niSoma + ni.get(i);
            
        }
        //nixiTotal
        int nixiTotal = 0;
        
        for (int i = 0; i < ni.size(); i++) {
                             
                nixiTotal = nixiTotal + (xiOrganizado.get(i) * ni.get(i));

            }
        double media = 00.00;
        
        media = nixiTotal/xiOrganizado.size();
        
        
        for(int u = 0; u < xiOrganizado.size(); u++){
            QuantidadeDeVendas vendas = new QuantidadeDeVendas();
            vendas.setXi(xiOrganizado.get(u));
            vendas.setNi(ni.get(u));
            double porcentagemm = (vendas.getNi()/niSoma)* 100;
            System.out.println(porcentagemm);
            vendas.setPorcentagem(porcentagemm);
            vendas.setContaDoida(Math.pow(vendas.getXi() - media, 2) * vendas.getNi());
            qtdVendasArray.add(vendas);
        }
       
      return qtdVendasArray;
    }
}
