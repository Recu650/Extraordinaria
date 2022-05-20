/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_2;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        GestorDB gestor = new GestorDB();

        List<Voto> votos;
        
        votos = gestor.listadoCincoVotos();
        
        for(Voto voto: votos) {
            System.out.println(voto.getUsuario() + " - " +
                    voto.getFecha() + " - " +
                    voto.getCancion().getNumcancion());
        }
        
        System.out.println("-------------------------------------------");
        
        gestor.ModificarAliasComponentes();
        
        System.out.println("-------------------------------------------");
        
        votos = gestor.listadoCincoVotos();
        
        for(Voto voto: votos) {
            System.out.println(voto.getUsuario() + " - " +
                    voto.getFecha() + " - " +
                    voto.getCancion().getNumcancion());
        }
    }
    
}
