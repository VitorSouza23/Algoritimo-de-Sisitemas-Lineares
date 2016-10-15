/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.sistemaslineares;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class EliminacaoGaussiana {

    private static final double matrizDouble[][] = {
        {9,-6,-3,-4,-5,-5,-8,6,-2,2,-7,-1,4,-9,4,5,9,4,9,-5,25},
            {-8,5,-7,-8,0,2,10,-8,-2,-8,10,-2,-10,2,7,-7,6,-8,-4,-8,-184},
            {0,-1,-3,9,-5,-4,-7,8,0,-3,-1,-2,-8,10,-1,5,-9,10,1,-9,3},
            {9,-10,7,-10,-4,7,4,9,9,-2,2,10,-2,1,2,8,4,-4,6,5,185},
            {-6,-8,-4,3,0,6,4,-3,3,-5,-2,-7,-9,-10,8,0,-9,-7,3,0,-208},
            {8,1,5,-1,-7,6,-6,-8,-7,8,6,2,-6,-7,5,7,-9,-6,3,-9,-33},
            {7,1,3,-6,-9,4,0,5,-4,-2,-2,-2,-6,0,-6,0,4,-3,0,-6,142},
            {-8,6,-1,-9,0,-10,-10,-10,5,-10,-2,4,-10,2,7,1,-5,-6,0,-4,-71},
            {7,-2,-8,-5,-2,-9,10,-7,-8,-2,5,2,3,-3,4,-10,10,5,4,-5,-103},
            {7,-9,6,5,5,-6,-3,-3,1,-4,0,-6,10,0,7,8,-5,10,-4,-3,-73},
            {6,-9,4,5,-1,-5,10,10,-8,9,3,9,-9,6,-10,5,-1,-7,-5,3,220},
            {4,3,-10,-1,-3,5,4,-10,2,-8,-4,9,7,7,6,-3,1,-4,-6,2,-83},
            {7,10,10,2,6,-7,10,1,2,6,-6,8,6,10,1,-1,7,-2,5,9,209},
            {-7,9,9,-3,10,4,6,3,-9,-2,-6,-4,-6,3,-8,-6,7,1,8,-9,147},
            {1,1,-4,-4,-4,1,-5,-6,-4,-1,5,7,-2,-5,9,-2,0,4,3,-8,-159},
            {5,0,-10,-6,-7,-1,8,4,1,-10,4,-9,-4,-7,-4,3,5,8,2,6,-88},
            {-10,-2,10,7,-7,5,-8,6,5,8,9,-7,-5,-10,7,-1,-2,-4,2,7,-97},
            {-6,6,1,4,0,-2,3,-8,9,-6,7,-6,-4,-2,10,7,10,1,1,2,-193},
            {1,-8,8,-9,10,7,-5,5,-2,-9,10,5,6,0,-8,-8,-3,7,-7,-1,46},
            {6,9,-7,-4,-2,0,3,1,-9,-7,0,-8,1,-5,-8,-4,10,-3,8,6,30}

    };
    
    private static BigDecimal matriz[][];
    private static BigDecimal pivo;
    private static int linhaAtual;
    private static int colunaAtual;
    private static ArrayList<BigDecimal> mnnArray;
    private static BigDecimal vetb[];
    private static BigDecimal vetx[];
    private static BigDecimal matA[][];
    private static int n = 0;

    private static void criarMatrizBigDecimal(){
        matriz = new BigDecimal[matrizDouble.length][matrizDouble[0].length];
        for(int l = 0; l < matriz.length; l++){
            for(int c = 0; c < matriz[0].length; c++){
                matriz[l][c] = new BigDecimal(Double.toString(matrizDouble[l][c]));
            }
        }
    }
    
    public static void calcularVetorX() {
        
        criarMatrizBigDecimal();
        n = 0;
        pivo = BigDecimal.ZERO;
        linhaAtual = -1;
        colunaAtual = -1;
        mostrarMatriz();
        System.out.println("\n");
        do {
            n++;
            linhaAtual++;
            colunaAtual++;
            pivo = matriz[linhaAtual][linhaAtual];
            mnnArray = new ArrayList<>();
            BigDecimal mnn;
            for (int l = linhaAtual + 1; l < matriz.length; l++) {
                mnn = matriz[l][colunaAtual].divide(pivo, 16, RoundingMode.DOWN);
                mnnArray.add(mnn);
                for (int c = colunaAtual; c < matriz[0].length; c++) {
                    matriz[l][c] = matriz[l][c].subtract(mnn.multiply(matriz[linhaAtual][c]));
                }
                
            }

            mostrarTodasAsInformacoes();
            System.out.println("\n");
        } while (linhaAtual < matriz.length - 1);
        mostrarVetorX();
    }

    private static void mostrarTodasAsInformacoes() {
        System.out.println("Nº: " + n);
        System.out.println("Pivô: " + pivo);

        for (int x = 0; x < mnnArray.size(); x++) {
            System.out.println("m[" + (x + 1) + "][" + colunaAtual + "]: " + mnnArray.get(x));
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
        BigDecimal res = BigDecimal.ZERO;
        vetx = new BigDecimal[matriz.length];
        vetb = new BigDecimal[matriz.length];
        matA = new BigDecimal[matriz.length][matriz[0].length - 1];
        
        /*for (int x = 0; x < vetx.length; x++) {
            vetx[x] = 1.0;
        }*/
        
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
                    res = res.add(matA[l][c].multiply(vetx[c]));
                }   
            }
            vetx[l] = (vetb[l].subtract(res)).divide(matA[l][l], 16, RoundingMode.DOWN);
            res = BigDecimal.ZERO;
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
    
         /* {9,-6,-3,-4,-5,-5,-8,6,-2,2,-7,-1,4,-9,4,5,9,4,9,-5,25},
            {-8,5,-7,-8,0,2,10,-8,-2,-8,10,-2,-10,2,7,-7,6,-8,-4,-8,-184},
            {0,-1,-3,9,-5,-4,-7,8,0,-3,-1,-2,-8,10,-1,5,-9,10,1,-9,3},
            {9,-10,7,-10,-4,7,4,9,9,-2,2,10,-2,1,2,8,4,-4,6,5,185},
            {-6,-8,-4,3,0,6,4,-3,3,-5,-2,-7,-9,-10,8,0,-9,-7,3,0,-208},
            {8,1,5,-1,-7,6,-6,-8,-7,8,6,2,-6,-7,5,7,-9,-6,3,-9,-33},
            {7,1,3,-6,-9,4,0,5,-4,-2,-2,-2,-6,0,-6,0,4,-3,0,-6,142},
            {-8,6,-1,-9,0,-10,-10,-10,5,-10,-2,4,-10,2,7,1,-5,-6,0,-4,-71},
            {7,-2,-8,-5,-2,-9,10,-7,-8,-2,5,2,3,-3,4,-10,10,5,4,-5,-103},
            {7,-9,6,5,5,-6,-3,-3,1,-4,0,-6,10,0,7,8,-5,10,-4,-3,-73},
            {6,-9,4,5,-1,-5,10,10,-8,9,3,9,-9,6,-10,5,-1,-7,-5,3,220},
            {4,3,-10,-1,-3,5,4,-10,2,-8,-4,9,7,7,6,-3,1,-4,-6,2,-83},
            {7,10,10,2,6,-7,10,1,2,6,-6,8,6,10,1,-1,7,-2,5,9,209},
            {-7,9,9,-3,10,4,6,3,-9,-2,-6,-4,-6,3,-8,-6,7,1,8,-9,147},
            {1,1,-4,-4,-4,1,-5,-6,-4,-1,5,7,-2,-5,9,-2,0,4,3,-8,-159},
            {5,0,-10,-6,-7,-1,8,4,1,-10,4,-9,-4,-7,-4,3,5,8,2,6,-88},
            {-10,-2,10,7,-7,5,-8,6,5,8,9,-7,-5,-10,7,-1,-2,-4,2,7,-97},
            {-6,6,1,4,0,-2,3,-8,9,-6,7,-6,-4,-2,10,7,10,1,1,2,-193},
            {1,-8,8,-9,10,7,-5,5,-2,-9,10,5,6,0,-8,-8,-3,7,-7,-1,46},
            {6,9,-7,-4,-2,0,3,1,-9,-7,0,-8,1,-5,-8,-4,10,-3,8,6,30}
*/

}
