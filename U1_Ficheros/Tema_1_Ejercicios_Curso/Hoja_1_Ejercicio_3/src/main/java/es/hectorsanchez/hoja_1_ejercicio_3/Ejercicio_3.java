/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_1_ejercicio_3;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author usuario
 */
public class Ejercicio_3{
    public static void main(String[] args){
        File directorio = new File("./NUEVODIR");
        File archivo1 = new File("./NUEVODIR/archivo1.txt");
        File archivo2 = new File("./NUEVODIR/archivo2.txt");
        File archivo2rename = new File("./NUEVODIR/archivo2rename.txt");

        creaDirectorio(directorio);
        creaArchivos(archivo1, archivo2);
        cambiaNombre(archivo2, archivo2rename);
    }

    public static void creaDirectorio(File dir){
        if (dir.exists() == false){
            if (dir.mkdirs()){
                System.out.println("Directorio Creado");
            } else System.out.println("Error al crear");
        } else System.out.println("El directorio ya existe");
    }

    public static void creaArchivos(File a1, File a2) {
        try {
            if (a1.createNewFile()) System.out.println("Archivo creado: " + a1.getName());
            else System.out.println("Archivo ya existe");

            if (a2.createNewFile()) System.out.println("Archivo creado: " + a2.getName());
            else System.out.println("Archivo ya existe");
        } catch (IOException e) {
            System.out.println("Error");
            e.getMessage();
        }
    }

    public static void cambiaNombre(File a2, File a2r){
        if (a2.exists()) {
            if (a2r.exists() ==false){
                if (a2.renameTo(a2r) == true)
                    System.out.println("Renombrado");
                else System.out.println("Fallo en el renombre");
            }else System.out.println("Ya ha sido renombrado");
        } else System.out.println("Archivo a renombrar no existe");
    }
}
