/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_3_ejercicio_4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File fichero = new File("alumnos.txt");
        int caracter;
        String dato = "";
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fichero);
            while (fis.available() > 0) {
                caracter = fis.read();
                if (caracter != 10) {
                    dato = dato + (char) caracter;
                    //Mientras no encuentre un salto de linea va formando el dato (linea)
                } else {
                    //En el salto de linea busco la posicion de - para hacer un substring de la edad
                    int posicionGuion = 0;
                    for (int i = 0; i < dato.length(); i++) {
                        char c = dato.charAt(i);
                        if (c == '-')
                            posicionGuion = i;
                    }
                    int edad = Integer.valueOf(dato.substring(posicionGuion + 1));
                    if (edad > 18)
                        System.out.println(dato);
                    dato = "";
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
