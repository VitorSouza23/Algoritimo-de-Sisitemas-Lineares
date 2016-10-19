/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.sistemaslineares.iterativos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


/**
 *
 * @author Aluno
 */
public class GaussSeidel {
    private static final double matrizDouble[][] = {
        {5, 1, 1, 5},
        {3, 4, 1, 6},
        {3, 3, 6, 0}
    };
    
    private static BigDecimal matriz[][];
    private static BigDecimal pivo;
    private static BigDecimal vetb[];
    private static BigDecimal vetx[];
    private static BigDecimal matA[][];
    private static int n = 0;
    private static final RoundingMode ROUND_MODE = RoundingMode.HALF_EVEN;
    private static final int CASA_DECIMAL = 3;
    private static final int CASA_DECIMAL_DIVISOR = 15;
    private static BigDecimal vetxRef[];
    
    private static void criarMatrizBigDecimal(){
        matriz = new BigDecimal[matrizDouble.length][matrizDouble[0].length];
        for(int l = 0; l < matrizDouble.length; l++){
            for(int c = 0; c < matrizDouble[0].length; c++){
                matriz[l][c] = new BigDecimal(matrizDouble[l][c]).setScale(CASA_DECIMAL, ROUND_MODE);
            }
        }
    }
    
    private static void inicializarDados(){
        criarMatrizBigDecimal();
        inicializarVetX();
        criarMatA();
        criarVetB();
        
        
    }
    
    public static  void calcularVetorX(double erro){
        inicializarDados();
        BigDecimal epslon = new BigDecimal(erro);
        BigDecimal soma;
        n = 0;
        mostrarVetX();
        mostrarMatrizA();
        mostrarVetorB();
        do{
            n++;
            vetxRef = new BigDecimal[vetx.length];
            for(int x = 0; x < vetxRef.length; x++){
                vetxRef[x] = vetx[x];
            }
            
            for(int l = 0; l < matA.length; l++){
                soma = vetb[l];
                for(int c = 0; c < matA[0].length; c++){
                    if(l != c){
                        if(l > c){
                            soma = soma.subtract(matA[l][c].multiply(vetx[c]).setScale(CASA_DECIMAL, ROUND_MODE)).setScale(CASA_DECIMAL, ROUND_MODE);
                        }else{
                            soma = soma.subtract(matA[l][c].multiply(vetxRef[c]).setScale(CASA_DECIMAL, ROUND_MODE)).setScale(CASA_DECIMAL, ROUND_MODE);
                        }
                        
                    }
                    
                }
                vetx[l] = soma.divide(matA[l][l], CASA_DECIMAL_DIVISOR, ROUND_MODE);
            }
            mostrarVetX();
        }while(epslon.compareTo(verificarErro()) == -1);
    }
    
    private static void inicializarVetX(){
        vetx = new BigDecimal[matriz.length];
        for(int x = 0; x < vetx.length; x++){
            vetx[x] = BigDecimal.ZERO;
        }
    }
    
    private static void criarVetB(){
        vetb = new BigDecimal[matriz.length];
        for(int b = 0; b < vetb.length; b++){
            vetb[b] = matriz[b][matriz[0].length -1];
        }
    }
    
    private static void criarMatA(){
        matA = new BigDecimal[matriz.length][matriz[0].length - 1];
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[0].length - 1; c++) {
                matA[l][c] = matriz[l][c];
            } 
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
    
    private static void mostrarVetX(){
        System.out.println("\n");
        System.out.println("Etapa nº: " + n);
        System.out.println("Vetor X:");
        for(int x = 0; x < vetx.length; x++){
            System.out.println(vetx[x]);
        }
    }
    private static  BigDecimal verificarErro(){
        BigDecimal maior = BigDecimal.ZERO;
        BigDecimal res;
        System.out.println("\n");
        for(int x = 0 ; x < vetx.length; x++){
            res = vetx[x].subtract(vetxRef[x]).setScale(CASA_DECIMAL, ROUND_MODE).abs();
            System.out.println("d[" + x + "]: " + res);
            if(res.compareTo(maior) == 1){
                maior = res;
            }
        }
        System.out.println("dMax: " + maior);
        return maior;
    }
    
    public static void criterioDeSassenfeld(){
        inicializarDados();
        BigDecimal maior = BigDecimal.ZERO;
        BigDecimal res = BigDecimal.ZERO;
        ArrayList<BigDecimal> beta = new ArrayList<>();
        System.out.println("\n");
        
        for(int l = 0; l < matA.length; l++){
            for(int c = 0; c < matA[0].length; c++){
                if(l != c){
                    if(l < c){
                        res = res.abs().add(matA[l][c].abs());
                    }else{
                        res = res.abs().add(matA[l][c].abs()).multiply(beta.get(c)).setScale(CASA_DECIMAL, ROUND_MODE);
                    }
                }
                
            }
                res = res.abs().divide(matA[l][l].abs(), CASA_DECIMAL_DIVISOR, ROUND_MODE);
                beta.add(res);
                System.out.println("alfa[" + l + "]" + res);
                if(maior.compareTo(res) == -1){
                    maior = res;
                }
                res = BigDecimal.ZERO;
            
        }
        if(maior.compareTo(BigDecimal.ONE) == -1){
                System.out.println("Método converge!");
            }else{
                System.out.println("Talves o método não vá convergir!");
            }
            System.out.println("alfaMax: " + maior);
    }
}
