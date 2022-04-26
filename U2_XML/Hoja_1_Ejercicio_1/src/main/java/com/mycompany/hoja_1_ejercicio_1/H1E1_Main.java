/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_1_ejercicio_1;

/**
 *
 * @author usuario
 */
public class H1E1_Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        GestorXML gestor = new GestorXML();
        gestor.crearXML();
        
        gestor.insertarFutbolista(new Futbolista((short)1, "Recu", (byte)2, (float)1.9, "TOR"));
        gestor.insertarFutbolista(new Futbolista((short)2, "Rina", (byte)1, (float)1.8, "TOR"));
        gestor.insertarFutbolista(new Futbolista((short)3, "Meda", (byte)4, (float)1.8, "TOR"));
        
        gestor.guardarDOMcomoFILE("futbolistas.xml");
    }
    
}
