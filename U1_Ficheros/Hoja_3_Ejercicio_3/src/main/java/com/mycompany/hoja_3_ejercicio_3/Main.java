/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_3_ejercicio_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Realiza un programa que actúa sobre un fichero personas.txt que tiene en cada línea el nombre y
 * apellidos en mayúsculas de una persona. Estos datos deben estar en el formato APELLIDOS, NOMBRE
 * 
 * El programa presenta un menú como el siguiente: 
 * 1. Añadir persona 
 * 2. Buscar persona 
 * 3. Buscar nombre 
 * 4. Apellidos comienzan por 
 * 5. Eliminar persona 
 * 0. Salir 
 * 
 * Y en función de la opción elegida realiza el proceso correspondiente. 
 * 
 * Opción 1: Se recogen por teclado los apellidos y el nombre de la persona y se añaden en una
 * línea de texto al fichero. 
 * 
 * Opción 2: Se recoge por teclado los apellidos y el nombre de una persona y se indica si se
 * encuentra o no en el fichero. 
 * 
 * Opción 3: Se recoge por teclado un nombre de persona y se devuelve una lista con las personas 
 * del fichero que tienen ese nombre. Luego se imprime la lista. 
 * 
 * Opción 4: Se introduce los primeros caracteres de apellidos de persona y se devuelve una lista 
 * con las personas del fichero cuyos apellidos comienzan por esos primeros caracteres. 
 * Luego se imprime la lista. 
 * 
 * Opción 5: Se recogen por teclado el nombre y apellidos de una persona y, si se encuentra en el
 * fichero, se elimina del fichero. Para hacer esto se necesita un fichero auxiliar en el que se 
 * van guardando todos los nombres que no se tengan que eliminar.
 */
public class Main {
    public static void main(String[] args) {
        File fichero = new File("personas.txt");
        String persona = "";
        String texto = "";
        int select;
        do {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Añadir persona       |");
            System.out.println("| 2.Buscar persona       |");
            System.out.println("| 3.Buscar nombre        |");
            System.out.println("| 4.Apell. comienzan por |");
            System.out.println("| 5.Eliminar persona     |");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = Teclado.introInt("");
            System.out.println("|------------------------|");
            switch (select) {
                case 1:
                    persona = personaMaker();
                    escribir(fichero, persona);
                    break;
                case 2:
                    persona = personaMaker();
                    texto = leer(fichero);
                    if(texto.contains(persona)) System.out.println(persona + " está en el fichero");
                    else System.out.println(persona + " no está en el fichero");
                    break;
                case 3:
                    String nombre = Teclado.introString("Nombre: ");
                    System.out.println(buscarNombre(fichero, nombre));
                    break;
                case 4:
                    String apell = Teclado.introString("Inicio Apellido: ");
                    System.out.println(buscarInicioApellido(fichero, apell));
                    break;
                case 5:
                    persona = personaMaker();
                    eliminar(fichero, persona);
                    break;
                case 6:
                    System.out.println(leer(fichero));
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    public static void escribir(File fichero, String entrada) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fichero, true);
            // * true = añade el texto a lo ya escrito.
            // * false = machaca el contenido del fichero.
            for (int i = 0; i < entrada.length(); i++) {
                char letra = entrada.charAt(i);
                fos.write(letra);
            }
            fos.write(10); //El 10 corresponde al salto de linea en ASCII.
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally //El finally es para cerrar el flujo de salida.
        {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    System.out.println(ex.toString());
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

    public static String personaMaker() {
        String nombre = Teclado.introString("Nombre: ");
        String apellido = Teclado.introString("Apellidos: ");
        String texto = apellido.toUpperCase() + ", " + nombre.toUpperCase();
        return texto;
    }
    
    public static String buscarNombre(File fichero, String nombre){
        BufferedReader br = null;
        String texto = "";
        String linea = "";
        
        try{
            br = new BufferedReader(new FileReader(fichero));
            while((linea = br.readLine()) != null){
                if(linea.contains(nombre.toUpperCase())){
                    texto = texto + linea + "\n";
                }
            }
            br.close();
        }  catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return texto;
    }
    
    public static String buscarInicioApellido(File fichero, String apell){
        BufferedReader br = null;
        String texto = "";
        String linea = "";
        
        try{
            br = new BufferedReader(new FileReader(fichero));
            while((linea = br.readLine()) != null){
                if(linea.startsWith(apell.toUpperCase())){
                    texto = texto + linea + "\n";
                }
            }
            br.close();
        }  catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return texto;
    }
    
    public static void eliminar (File fichero, String nombre){
        //Leo todo el fichero menos el nombre que voy a borrar y lo meto en un String
        BufferedReader br = null;
        String texto = "";
        String linea = "";
        
        try{
            br = new BufferedReader(new FileReader(fichero));
            while((linea = br.readLine()) != null){
                if(linea.contains(nombre.toUpperCase()) == false){
                    texto = texto + linea + "\n";
                }
            }
            br.close();
        }  catch (IOException ex) {
            System.out.println(ex.toString());
        }
        //Escribo el String en el fichero machacando lo anterior (quito el true)
        
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fichero);
            // * true = añade el texto a lo ya escrito.
            // * false = machaca el contenido del fichero.
            for (int i = 0; i < texto.length(); i++) {
                char letra = texto.charAt(i);
                fos.write(letra);
            }
            fos.write(10); //El 10 corresponde al salto de linea en ASCII.
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally //El finally es para cerrar el flujo de salida.
        {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        System.out.println("Eliminado correctamente!");
    }
}
