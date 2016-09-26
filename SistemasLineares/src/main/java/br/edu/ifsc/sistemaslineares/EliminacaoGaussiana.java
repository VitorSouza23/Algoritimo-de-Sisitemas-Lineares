/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.sistemaslineares;

/**
 *
 * @author Vitor
 */
public class EliminacaoGaussiana {
    private static double matriz[][] = {
        {2,3,-1,5},
        {4,4,-3,3},
        {2,-3,1,1}
    };
    private static double pivo;
    private static int linhaAtual;
    private static int colunaAtual;
    private static double mnn[];
    
    public static void calcularVetorX(){
        pivo = 0;
        linhaAtual = -1;
        colunaAtual = -1;
        mostrarMatriz();
        System.out.println("\n");
        do {
            linhaAtual++;
            colunaAtual++;
            pivo = matriz[linhaAtual][linhaAtual];
            mnn = new double[matriz.length - linhaAtual];
            int contr = 1;
           for(int x = 0; x < mnn.length; x++){
                mnn[x] = matriz[x + linhaAtual][colunaAtual] / pivo;
            }
            for(int l = linhaAtual + 1; l < matriz.length; l++){
                for(int c = colunaAtual; c < matriz[l].length; c++){
                    matriz[l][c] = matriz[l][c] - (mnn[contr] * matriz[linhaAtual][c]);
                }
                contr++;
            }
           
            mostrarTodasAsInformacoes();
            System.out.println("\n");
        }while (linhaAtual < matriz.length - 1);
        
        
    }
    
    private static void mostrarTodasAsInformacoes(){
        System.out.println("Pivô: " + pivo);
        
        for(int x = 0; x < mnn.length; x++){
            System.out.println("m[" + x + "][" + colunaAtual +"]: " + mnn[x]);
        }
        
        System.out.println("Matriz:");
        for(int x = 0; x < matriz.length; x++){
            for(int y = 0; y < matriz[x].length; y++){
                System.out.print(" " + matriz[x][y] + " ");
            }
            System.out.println("");
        }
    }
    
    private static void mostrarMatriz(){
        System.out.println("Matriz:");
        for(int x = 0; x < matriz.length; x++){
            for(int y = 0; y < matriz[x].length; y++){
                System.out.print(" " + matriz[x][y] + " ");
            }
            System.out.println("");
        }
    }
    
    
    
    
}
