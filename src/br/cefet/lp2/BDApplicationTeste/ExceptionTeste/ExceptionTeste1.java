/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.lp2.BDApplicationTeste.ExceptionTeste;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author douglas
 */
public class ExceptionTeste1 {

    
    
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean divisaoOk = false;

        while (!divisaoOk) {
            try {
                System.out.print("Entre com o denominador:");
                int i = sc.nextInt();
                System.out.print("Entre com o numerador:");
                int j = sc.nextInt();
                int d = i / j;

                System.out.println("O valor da divisão é: " + d);
                
                divisaoOk = true;
            } catch (ArithmeticException e) {
                System.out.println("Nao podemos realizar uma divisão por zero.");
                System.out.println("Entre com novos valores.");
            } catch (InputMismatchException e) {
                System.out.println("Você digitou um valores não numérico.");
                System.out.println("Entre com novos valores.");
                sc = new Scanner(System.in);
            }
        }

        System.out.println("Programa não finalizour indevidamente.");
    }
    
    public static void main(String[] args) {
        
        try {
            testarException();
        } catch (Exception e) {
            System.out.println("Exception ocorrida (" +e.getClass().getName()+ "):" + e.getMessage());
        }
        
    }
    
    public static void testarException() throws Exception{
        System.out.println("Vou lançar uma exception");
        
        throw new Exception ("Exception de teste do meu programa");
        
    }
}
