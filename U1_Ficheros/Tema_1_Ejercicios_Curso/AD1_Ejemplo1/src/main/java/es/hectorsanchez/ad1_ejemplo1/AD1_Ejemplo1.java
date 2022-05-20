/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.ad1_ejemplo1;

import java.io.File;

/**
 *
 * @author usuario
 */
public class AD1_Ejemplo1
{

    public static void main(String[] args)
    {
        File directorios = new File(".");
        System.out.println("Ficheros de directorios acutales: " + directorios.getAbsolutePath());
        String[] archivos = directorios.list();
        for (int i = 0; i < archivos.length; i++)
        {
            System.out.println(archivos[i]);
        }
    }

}
