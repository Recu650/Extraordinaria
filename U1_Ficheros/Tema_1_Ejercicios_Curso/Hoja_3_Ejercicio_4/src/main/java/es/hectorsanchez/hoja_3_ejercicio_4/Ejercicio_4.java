/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_3_ejercicio_4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author usuario
 */
public class Ejercicio_4
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        int caracter;
        File f = new File("alumnos.txt");
        FileInputStream fi = null;
        String dato = "";
        
        try{
            fi = new FileInputStream(f);
            while(fi.available() > 0){
                caracter = fi.read();
                //System.out.print((char)caracter);
                if(caracter != 10){
                    dato = dato + (char) caracter;
                    //System.out.println(dato);
                }else{
                    int posGuion = 0;
                    for (int i = 0; i < dato.length(); i++)
                    {
                        char c = dato.charAt(i);
                        if(c == '-') posGuion = i;
                    }
                    int edad = Integer.valueOf(dato.substring(posGuion+1));
                    //System.out.println(edad);
                    if(edad>20) System.out.println(dato);
                    dato = "";
                }
            }
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
    }
    
}
