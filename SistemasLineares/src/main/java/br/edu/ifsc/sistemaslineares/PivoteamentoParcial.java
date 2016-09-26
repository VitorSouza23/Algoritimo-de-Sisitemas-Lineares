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
public class PivoteamentoParcial {

    private static double matriz[][] = {
        {2, 3, -1, 5},
        {4, 4, -3, 3},
        {2, -3, 1, 1}
    };
    private static double pivo;
    private static int linhaAtual;
    private static int colunaAtual;
    private static double mnn[];

    public static void calcularVetorX() {
        pivo = 0;
        linhaAtual = -1;
        colunaAtual = -1;
        mostrarMatriz();
        System.out.println("\n");
        do {
            linhaAtual++;
            colunaAtual++;
            pivoteamentoParcial();
            mostrarMatriz();
            System.out.println("\n");
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

    }

    private static void mostrarTodasAsInformacoes() {
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

    private static void pivoteamentoParcial() {
        double maiorEmModulo = 0;
        int linha = linhaAtual;
        for (int w = linhaAtual; w < matriz[linhaAtual].length - 1; w++) {
            if (maiorEmModulo < matriz[w][colunaAtual]) {
                maiorEmModulo = matriz[w][colunaAtual];
                linha = w;
            }
        }
        if (linha != linhaAtual) {
            double aux[] = new double[matriz[linhaAtual].length];
            for (int a = 0; a < matriz[linhaAtual].length; a++) {
                aux[a] = matriz[linhaAtual][a];
                matriz[linhaAtual][a] = matriz[linha][a];
                matriz[linha][a] = aux[a];
            }
        }

    }
}
