/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.bo;

import br.cefet.lp2.BDApplicationTeste.dao.OSDao;
import br.cefet.lp2.BDApplicationTeste.dao.OSServicoDao;
import br.cefet.lp2.BDApplicationTeste.entidade.OS;
import br.cefet.lp2.BDApplicationTeste.entidade.Pet;
import br.cefet.lp2.BDApplicationTeste.entidade.Servico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class OrdemServicoBo {
    
    public void inserir(OS o) throws Exception{
        OSDao osD = new OSDao();
        int codOS = osD.inserir(o);
        
        OSServicoDao osServicoD = new OSServicoDao();
        
        List<Servico> sList = o.getServicoList();
        for(int i = 0; i < sList.size(); i++){
            Servico s = sList.get(i);
            osServicoD.inserir(codOS, s);
        }
    }
    
    public static void main(String[] args) throws Exception {
        OS os = new OS();
        
        Pet p = new Pet();
        p.setCod(10);
        p.setNome("Lala");
        p.setDono("Funlano");
        p.setRaca("Dalmata");
        os.setPet(p);
        
        List<Servico> sList = new ArrayList();
        
        Servico s = new Servico();
        s.setCod(9);
        s.setDescricao("Tosa");
        s.setValor(20);
        sList.add(s);
        
        s = new Servico();
        s.setCod(10);
        s.setDescricao("Banho");
        s.setValor(15);
        sList.add(s);
        
        os.setServicoList(sList);
        
        OrdemServicoBo osBo = new OrdemServicoBo();
        osBo.inserir(os);
    }
    
}
