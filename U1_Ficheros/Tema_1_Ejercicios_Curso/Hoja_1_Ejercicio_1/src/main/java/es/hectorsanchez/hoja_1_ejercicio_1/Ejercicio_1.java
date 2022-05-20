/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_1_ejercicio_1;

import java.io.File;

/**
 *
 * @author usuario
 */
public class Ejercicio_1
{

    public static void main(String[] args){
        if(args.length > 0){
            File directorio = new File(args[0]);
            if(directorio.exists()){
                File[] archivos = directorio.listFiles();
                System.out.println(analizador(archivos));
            }else System.out.println("El directorio no existe");
        }else System.out.println("No me has pasado direccion");  
    }
    
    public static String analizador(File[] archivos) {
        String resul = "";
        for (File archivo: archivos){
            if(archivo.isFile()){    
                resul = resul + "Archivo -> " + archivo.getName() + " - " + archivo.length() + " bytes\n";
            }else if(archivo.isDirectory()){
                resul = resul + "----------------------------------------------------------------------------\n";
                resul = resul + "Carpeta -> " + archivo.getName() + " - " + archivo.length() + " bytes\n";
                File subdirectorio = new File(archivo.getAbsolutePath());
                File[] subarchivos = subdirectorio.listFiles();
                resul = resul + analizador(subarchivos);
                resul = resul + "----------------------------------------------------------------------------\n";
            }            
        }
        return resul;
    }   
}
