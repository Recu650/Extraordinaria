/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_3_ejercicio_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Ejercicio_1
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Direccion:");
        String direccion = teclado.nextLine();
        File fichero = new File(direccion);

        System.out.println(fichero.exists());

        escribir(fichero);
        leerCambiar(fichero);
    }

    public static void escribir(File fichero)
    {
        FileOutputStream fo = null;

        Scanner teclado = new Scanner(System.in);
        System.out.println("Texto:");
        String texto = teclado.nextLine();
        try
        {
            fo = new FileOutputStream(fichero, true);
            for (int i = 0; i < texto.length(); i++)
            {
                char letra = texto.charAt(i);
                fo.write(letra);
            }
            fo.write(10);
        } catch (IOException e)
        {
            System.out.println(e.toString());
        } finally
        {
            if (fo != null)
            {
                try
                {
                    fo.close();
                } catch (IOException ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    public static void leerCambiar(File fichero)
    {
        
        char caracter;
        FileInputStream fi = null;
        try
        {
            fi = new FileInputStream(fichero);

            while (fi.available() > 0)
            {
                
                caracter = (char) fi.read();
                
                if (Character.isUpperCase(caracter))
                {
                    caracter = Character.toLowerCase(caracter);
                } else if (Character.isLowerCase(caracter))
                {
                    caracter = Character.toUpperCase(caracter);
                }
                System.out.print(caracter);
            }

        } catch (IOException e)
        {
            e.toString();
        } finally
        {
            if (fi != null)
            {
                try
                {
                    fi.close();
                } catch (IOException ex)
                {
                    ex.toString();
                }
            }
        }
    }
}
