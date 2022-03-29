/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_3_ejercicio_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Realiza un programa que pide la ruta y nombre de un fichero por teclado y el texto que queremos
 * añadir a ese fichero (el fichero puede ya existir y tener contenido). Después de añadir el
 * contenido al fichero, se deberá mostrar en pantalla el texto completo del fichero pero variando
 * entre mayúsculas y minúsculas, es decir, si contiene “Bienvenido” deberá devolver “bIENVENIDO”.
 * Si tiene cualquier otro carácter que no sea una letra, se escribirá ese carácter sin
 * modificación.
 */
public class Main
{
    public static void main(String[] args)
    {
        String ruta = Teclado.introString("Ruta del fichero:");
        File fichero = new File(ruta);

        if (fichero.exists())
            System.out.println("El fichero ya existe");
        else
            System.out.println("El fichero no existe");

        escribir(fichero);
        leerCambiar(fichero);

    }

    public static void escribir(File fichero)
    {
        FileOutputStream fos = null;
        String nuevoTexto = Teclado.introString("Nuevo Texto:");
        try
        {
            fos = new FileOutputStream(fichero, true);
            // * true = añade el texto a lo ya escrito.
            // * false = machaca el contenido del fichero.
            for (int i = 0; i < nuevoTexto.length(); i++)
            {
                char letra = nuevoTexto.charAt(i);
                fos.write(letra);
            }
            fos.write(10); //El 10 corresponde al salto de linea en ASCII.
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally //El finally es para cerrar el flujo de salida.
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                } catch (IOException ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    public static void leerCambiar(File fichero)
    {
        FileInputStream fis = null;
        char caracter;
        try
        {
            fis = new FileInputStream(fichero);
            while (fis.available() > 0)
            {
                caracter = (char) fis.read();

                if (Character.isUpperCase(caracter))
                {
                    caracter = Character.toLowerCase(caracter);
                } else if (Character.isLowerCase(caracter))
                {
                    caracter = Character.toUpperCase(caracter);
                }
                System.out.print(caracter);
            }
        } catch (IOException ex)
        {
            ex.toString();
        } finally //El finally es para cerrar el flujo de entrada.
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                } catch (IOException ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
    }

}
