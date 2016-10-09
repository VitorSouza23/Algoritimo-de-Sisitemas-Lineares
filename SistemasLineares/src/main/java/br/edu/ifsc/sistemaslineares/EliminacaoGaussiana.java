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
         {2, 3, -1, 5},
        {4, 4, -3, 3},
        {2, -3, 1, -1}
    };
    private static double pivo;
    private static int linhaAtual;
    private static int colunaAtual;
    private static double mnn[];
    private static double vetb[];
    private static double vetx[];
    private static double matA[][];
    private static int n = 0;

    public static void calcularVetorX() {
        n = 0;
        pivo = 0;
        linhaAtual = -1;
        colunaAtual = -1;
        mostrarMatriz();
        System.out.println("\n");
        do {
            n++;
            linhaAtual++;
            colunaAtual++;
            pivo = matriz[linhaAtual][linhaAtual];
            mnn = new double[matriz.length - linhaAtual];
            int contr = 1;
            for (int x = 0; x < mnn.length; x++) {
                mnn[x] = matriz[x + linhaAtual][colunaAtual] / pivo;
            }
            for (int l = linhaAtual + 1; l < matriz.length; l++) {
                for (int c = colunaAtual; c < matriz[l].length; c++) {
                    matriz[l][c] = matriz[l][c] - (mnn[contr] * matriz[linhaAtual][c]);
                }
                contr++;
            }

            mostrarTodasAsInformacoes();
            System.out.println("\n");
        } while (linhaAtual < matriz.length - 1);
        mostrarVetorX();
    }

    private static void mostrarTodasAsInformacoes() {
        System.out.println("Nº: " + n);
        System.out.println("Pivô: " + pivo);

        for (int x = 0; x < mnn.length; x++) {
            System.out.println("m[" + x + "][" + colunaAtual + "]: " + mnn[x]);
        }

        System.out.println("Matriz:");
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print(" " + matriz[x][y] + " ");
            }
            System.out.println("");
        }
    }

    private static void mostrarMatriz() {
        System.out.println("Matriz:");
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print(" " + matriz[x][y] + " ");
            }
            System.out.println("");
        }
    }

    private static void mostrarVetorX() {
        double res = 0.0;
        vetx = new double[matriz.length];
        vetb = new double[matriz.length];
        matA = new double[matriz.length][matriz[0].length - 1];
        
        for (int x = 0; x < vetx.length; x++) {
            vetx[x] = 1.0;
        }
        
        for(int b = 0; b < vetb.length; b++){
            vetb[b] = matriz[b][matriz[0].length -1];
        }
        
        
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[0].length - 1; c++) {
                matA[l][c] = matriz[l][c];
            } 
        }
        
        mostrarVetorB();
        mostrarMatrizA();
        
        for(int l = matA.length - 1; l >= 0; l--){
            for(int c = matA[0].length - 1; c >= 0; c--){
                if(l == c){
                    break;
                }else{
                    res += matA[l][c] * vetx[c];
                }   
            }
            vetx[l] = (vetb[l] - res) / matA[l][l];
            res = 0;
        }
        System.out.println("\n");
        System.out.println("Vetor X:");
        for(int x = 0; x < vetx.length; x++){
            System.out.println(vetx[x]);
        }
    }
    
    private static void mostrarVetorB(){
        System.out.println("\n");
        System.out.println("Vetor B:");
        for(int b = 0; b < vetb.length; b++){
            System.out.println(vetb[b]);
        }
    }
    
    private static void mostrarMatrizA(){
        System.out.println("\n");
        System.out.println("Matriz A:");
        for(int l = 0; l < matA.length; l++){
            for(int c = 0; c < matA[0].length; c++){
                System.out.print(" " + matA[l][c] + " ");
            }
            System.out.println("");
        }
    }

}
