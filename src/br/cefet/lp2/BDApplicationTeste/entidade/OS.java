/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class OS {
    private int cod;
    private int codPet;
    private Pet pet;
    private List<Servico> servicoList = new ArrayList<Servico>();

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodPet() {
        return codPet;
    }

    public void setCodPet(int codPet) {
        this.codPet = codPet;
    }
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        this.codPet = pet.getCod();
    }

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }

   
    
    
}
