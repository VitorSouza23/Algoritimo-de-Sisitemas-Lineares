/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.sistemaslineares;

import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class FatoracaoLU {
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
    private static double matL[][];
    private static double matU[][];
    private static double vety[];
    private static ArrayList<Double> mnnTotal;
    private static int n = 0;

    public static void calcularVetorX() {
        n = 0;
        pivo = 0;
        linhaAtual = -1;
        colunaAtual = -1;
        mnnTotal = new ArrayList<>();
        matU = new double[matriz.length][matriz[0].length - 1];
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[0].length - 1; c++) {
                matU[l][c] = matriz[l][c];
            } 
        }
        matL = new double[matriz.length][matriz[0].length - 1];
        vety = new double[matriz[0].length];
        mostrarMatriz();
        System.out.println("\n");
        do {
            n++;
            linhaAtual++;
            colunaAtual++;
            pivo = matU[linhaAtual][linhaAtual];
            mnn = new double[matriz.length - linhaAtual];
            for (int x = 0; x < mnn.length; x++) {
                mnn[x] = matU[x + linhaAtual][colunaAtual] / pivo;
                if(x != 0){
                    mnnTotal.add(mnn[x]);
                }
                
            }
            criarMatrizU(linhaAtual, colunaAtual);
            mostrarTodasAsInformacoes();
            System.out.println("\n");
        } while (linhaAtual < matU.length - 1);
        criarMatrizL();
        mostrarVetorX();
    }

    private static void mostrarTodasAsInformacoes() {
        System.out.println("Nº: " + n);
        System.out.println("Pivô: " + pivo);

        for (int x = 0; x < mnn.length; x++) {
            System.out.println("m[" + x + "][" + colunaAtual + "]: " + mnn[x]);
        }

       mostrarMatrizU();
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
        
        vetx = new double[matriz.length];
        vetb = new double[matriz.length];
        
        for (int x = 0; x < vetx.length; x++) {
            vetx[x] = 1.0;
        }
        
        for(int b = 0; b < vetb.length; b++){
            vetb[b] = matriz[b][matriz[0].length -1];
        }
        
        mostrarVetorB();
        mostrarMatrizU();
        mostrarMatrizL();
        
        calcularLyb();
        mostrarVetY();
        
        calcularUxy();
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
    
    
    private static void criarMatrizU(int linhaAtual, int colunaAtual){       
        int contr = 1;
        for (int l = linhaAtual + 1; l < matU.length; l++) {
                for (int c = colunaAtual; c < matU[l].length; c++) {
                    matU[l][c] = matU[l][c] - (mnn[contr] * matU[linhaAtual][c]);
                }
                contr++;
            }
    }
    
    private static void mostrarMatrizU(){
        System.out.println("\n");
        System.out.println("Matriz U:");
        for(int l = 0; l < matU.length; l++){
            for(int c = 0; c < matU[0].length; c++){
                System.out.print(" " + matU[l][c] + " ");
            }
            System.out.println("");
        }
    }
    
    private static void criarMatrizL(){
        
        int ctrl = 0;
        for (int l = 0; l < matL.length; l++) {
            for (int c = 0; c < matL[0].length; c++) {
                if(l > c){
                    matL[l][c] = mnnTotal.get(ctrl);
                    ctrl++;
                }else if(l == c){
                    matL[l][c] = 1;
                }else{
                    matL[l][c] = 0;
                }
            } 
        }
    }
    
    private static void mostrarMatrizL(){
        System.out.println("\n");
        System.out.println("Matriz L:");
        for(int l = 0; l < matL.length; l++){
            for(int c = 0; c < matL[0].length; c++){
                System.out.print(" " + matL[l][c] + " ");
            }
            System.out.println("");
        }
    }
    
    private static void calcularLyb(){
        double res = 0.0;
        for(int l = 0; l < matL.length; l++){
            for(int c = 0; c < matL[0].length; c++){
                if(l == c){
                    break;
                }else{
                    res += matL[l][c] * vety[c];
                }   
            }
            vety[l] = (vetb[l] - res) / matL[l][l];
            res = 0;
        }
    }
    
    private static void mostrarVetY(){
        System.out.println("\n");
        System.out.println("Vetor Y:");
        for(int l = 0; l < vety.length; l++){
                System.out.println(" " + vety[l] + " ");
        }
    }
    
    private static void calcularUxy(){
        double res = 0.0;
        for(int l = matU.length - 1; l >= 0; l--){
            for(int c = matU[0].length - 1; c >= 0; c--){
                if(l == c){
                    break;
                }else{
                    res += matU[l][c] * vetx[c];
                }   
            }
            vetx[l] = (vety[l] - res) / matU[l][l];
            res = 0;
        }
    }
    
}
