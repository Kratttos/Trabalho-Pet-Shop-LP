/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.util;

import java.util.Scanner;

/**
 *
 * @author douglas
 */
public class Ler {
    //criar um método que recebe uma mensagem que descreve que valor será lido,
    //uma mensagem de erro se a informação não for valida e retorna um inteiro
    
    public static int lerInt(String msgDescreve){
        int r = 0;
        Scanner sc = new Scanner(System.in);
        
        String msgErro = "Valor não representa um inteiro.";
        
        //Corpo do método
        
        return r;
    }
    
    public static void main(String[] args) {
        int teste = lerInt("Entre com um valor inteiro: ");
        System.out.println("teste = " + teste);
    }
}
