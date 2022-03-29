/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_3_ejercicio_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Realiza un programa que cuenta cuantas veces aparece un carácter introducido por teclado en el
 * fichero texto.txt y escribe en qué posiciones aparece ese carácter. Si el carácter es una letra,
 * se deben controlar las apariciones de esa letra tanto en mayúscula como en minúscula.
 */
public class Main {
    public static void main(String[] args) {
        File fichero = new File("texto.txt");
        String texto = leer(fichero);

        char caracterBuscar = 'f';
        int contadorMayus = 0;
        int contadorMinus = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (Character.toString(texto.charAt(i)).equalsIgnoreCase(Character.toString(caracterBuscar))) {
                if (Character.isLetter(texto.charAt(i))) {
                    if (Character.isUpperCase(texto.charAt(i))) {
                        contadorMayus++;
                    } else if (Character.isLowerCase(texto.charAt(i))) {
                        contadorMinus++;
                    }
                    System.out.println(caracterBuscar + " -> posicion: " + i + "\t|| Mayus: " + contadorMayus
                            + " veces  \t|| Minus: " + contadorMinus);
                }else{
                    contadorMinus++;
                    System.out.println(caracterBuscar + " -> posicion: " + i + "\t|| " + contadorMayus + " veces");
                }
            }
        }
    }

    public static String leer(File fichero) {
        FileInputStream fis = null;
        char caracter;
        String texto = "";
        try {
            fis = new FileInputStream(fichero);
            while (fis.available() > 0) {
                caracter = (char) fis.read();
                texto = texto + caracter;
            }
        } catch (IOException ex) {
            ex.toString();
        } finally //El finally es para cerrar el flujo de entrada.
        {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return texto;
    }
}
