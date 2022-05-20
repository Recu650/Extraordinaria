/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_3_ejercicio_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author usuario
 */
public class Ejercicio_2
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        File f = new File("texto.txt");
        FileInputStream fi = null;
        char c;
        int contador = 0;
        int posicion = 0;
        String posiciones = "";
        try{
            fi = new FileInputStream(f);
            while (fi.available() > 0){
                c = (char) fi.read();
                
                if(c == 'e' || c == 'E'){
                    posiciones = posiciones + "\n" + c + " posicion " + posicion;
                    contador++;
                }
                posicion++;
            }
            System.out.println("La letra 'e' aparece " + contador + " veces");
            System.out.println(posiciones);
        }catch(IOException e){
            e.toString();
        }
    }
    
}
