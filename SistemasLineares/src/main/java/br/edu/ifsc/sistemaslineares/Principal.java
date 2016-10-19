/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.sistemaslineares;

import br.edu.ifsc.sistemaslineares.exatos.EliminacaoGaussiana;
import br.edu.ifsc.sistemaslineares.exatos.FatoracaoCholesky;
import br.edu.ifsc.sistemaslineares.exatos.FatoracaoLU;
import br.edu.ifsc.sistemaslineares.exatos.MetodoJordan;
import br.edu.ifsc.sistemaslineares.exatos.PivoteamentoParcial;
import br.edu.ifsc.sistemaslineares.iterativos.GaussJacobi;
import br.edu.ifsc.sistemaslineares.iterativos.GaussSeidel;

/**
 *
 * @author Vitor
 */
public class Principal {
    public static void main(String[] args) {
        //EliminacaoGaussiana.calcularVetorX();
        //PivoteamentoParcial.calcularVetorX();
        //MetodoJordan.calcularVetorX();
        //FatoracaoLU.calcularVetorX();
        //FatoracaoCholesky.calcularVetorX();
        
        //GaussJacobi.calcularVetorX(0.35);
        //GaussJacobi.criterioDeConvergencia();
        
        //GaussSeidel.calcularVetorX(0.05);
        //GaussSeidel.criterioDeSassenfeld();
    }
}
