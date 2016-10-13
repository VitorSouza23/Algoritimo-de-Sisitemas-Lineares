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
public class FatoracaoCholesky {
     private static double matriz[][] = {
         {16, 4, 8, 4, 32},
        {4, 10, 8, 4, 26},
        {8, 8, 12, 10, 38},
        {4, 4, 10, 12, 30}
    };
    
    private static double vetb[];
    private static double vetx[];
    private static double matG[][];
    private static double matGt[][];
    private static double vety[];
   

    public static void calcularVetorX() {
       
        matG = new double[matriz.length][matriz[0].length - 1];
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[0].length - 1; c++) {
                if(l == c){
                    double resultado = 0.0;
                    for(int i = 0; i < c; i++){
                        resultado += Math.pow(matG[l][i], 2);
                    }
                    matG[l][c] = Math.sqrt(matriz[l][c] - resultado);
                    
                }else if(l > c){
                    double resultado = 0.0;
                    for(int i = 0; i < c ; i++){
                        resultado += matG[l][i] * matG[c][i];
                    }
                    matG[l][c] = (matriz[l][c] - resultado)/matG[c][c];
                    
                }else{
                    matG[l][c] = 0.0;
                }
            } 
        }
        matGt = new double[matriz.length][matriz[0].length - 1];
        for(int l = 0; l < matG.length; l++){
            for(int c = 0; c < matG[0].length; c++){
                matGt[c][l] = matG[l][c]; 
            }
        }
        vety = new double[matriz.length];
        mostrarMatriz(matG, "G");
        mostrarMatriz(matGt, "G Transposta");
        System.out.println("\n");
        
        
        mostrarVetorX();
    }

    

    private static void mostrarMatriz(double mat[][], String nome) {
        System.out.println("Matriz " + nome + ":");
        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat[0].length; y++) {
                System.out.print(" " + mat[x][y] + " ");
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
        calcularGyb();
        mostrarVetY();
        calcularGtxy();
        
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
    
    
    
  
    
    private static void calcularGyb(){
        double res = 0.0;
        for(int l = 0; l < matG.length; l++){
            for(int c = 0; c < matG[0].length; c++){
                if(l == c){
                    break;
                }else{
                    res += matG[l][c] * vety[c];
                }   
            }
            vety[l] = (vetb[l] - res) / matG[l][l];
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
    
    private static void calcularGtxy(){
        double res = 0.0;
        for(int l = matGt.length - 1; l >= 0; l--){
            for(int c = matGt[0].length - 1; c >= 0; c--){
                if(l == c){
                    break;
                }else{
                    res += matGt[l][c] * vetx[c];
                }   
            }
            vetx[l] = (vety[l] - res) / matGt[l][l];
            res = 0;
        }
    }
}
