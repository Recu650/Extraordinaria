/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_1_ejercicio_1;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.w3c.dom.Document;

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
        GestorXML gestor = new GestorXML();
        Document doc = gestor.crearXML();
        
        
    }
    
}
