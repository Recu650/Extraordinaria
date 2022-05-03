/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_1_ejercicio_2;

import java.io.File;

/**
 *
 * @author usuario
 */
public class H1E2_Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        GestorXML gestor = new GestorXML();
        File fichero = new File("futbolistas.xml");
        gestor.abrirXML(fichero);
        int select;
        do {
            System.out.println("|----------------MENU---------------|");
            System.out.println("| 1.Listar futbolistas              |");
            System.out.println("| 2.Añadir futbolista               |");
            System.out.println("| 3.Consultar futbolista por número |");
            System.out.println("| 4.Modificar equipo de futbolista  |");
            System.out.println("| 5.Modificar altura de futbolista  |");
            System.out.println("| 6.Eliminar futbolista             |");
            System.out.println("| 7.Grabar en fichero               |");
            System.out.println("| 0.Salir                           |");
            System.out.println("|-----------------------------------|");
            System.out.println("| Selecciona opcion:                |");
            select = Teclado.introInt("");
            System.out.println("|-----------------------------------|");
            switch (select) {
                case 1:   
                    System.out.println(gestor.mostrarDOM());
                    break;
                case 2:                   
                    break;
                case 3:                   
                    break;
                case 4:                  
                    break;
                case 5:                   
                    break;
                case 6:                  
                    break;
                case 7:                  
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }
    
}
