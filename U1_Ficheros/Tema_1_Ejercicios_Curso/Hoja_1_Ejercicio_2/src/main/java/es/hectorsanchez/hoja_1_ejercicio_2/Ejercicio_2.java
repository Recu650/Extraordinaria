/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_1_ejercicio_2;

import java.io.File;

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
        File directorio = new File(".");
        if(directorio.exists()){
            File[] archivos = directorio.listFiles();
            for(File archivo: archivos){
                if(archivo.isFile()){
                    System.out.println("Nombre: " + archivo.getName());
                    System.out.println("Ruta: " + archivo.getPath());
                    System.out.println("Ruata absoluta: " + archivo.getAbsolutePath());
                    System.out.println("Escribible: " + archivo.canWrite());
                    System.out.println("Legible: " + archivo.canRead());
                }else System.out.println("Carpeta " + archivo.getName());
            }
        }else System.out.println("El directorio no existe");
    }
    
}
