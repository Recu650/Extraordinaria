/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_3_ejercicio_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Ejercicio_3
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in); 
        File f = new File("personas.txt");
        FileOutputStream fo = null;
        FileInputStream fi = null;
        int select;
        do {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.ADD Persona          |");
            System.out.println("| 2.BUSCAR Persona       |");
            System.out.println("| 3.BUSCAR Nombre        |");
            System.out.println("| 4.COMIENZO Apellidos   |");
            System.out.println("| 5.ELIMINAR Persona     |");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select) {
                case 1:                  
                    teclado.nextLine();
                    System.out.println("Nombre: ");
                    String nombre1 = teclado.nextLine();
                    
                    System.out.println("Apellido: ");
                    String apellido1 = teclado.nextLine();
                    
                    String dato1 = apellido1.toUpperCase() + "." + nombre1.toUpperCase() + "\n";
                    try{
                        fo = new FileOutputStream(f, true);
                        for (int i = 0; i < dato1.length(); i++)
                        {
                            fo.write(dato1.charAt(i));
                        }
                    }catch(IOException e){
                        System.out.println(e.toString());
                    }finally{
                        try{
                            fo.close();
                        }catch(IOException ex){
                            System.out.println(ex.toString());
                        }
                    }
                    break;
                case 2:  
                    boolean encontrado = false;
                    
                    teclado.nextLine();
                    System.out.println("Nombre: ");
                    String nombre2 = teclado.nextLine();
                    
                    System.out.println("Apellido: ");
                    String apellido2 = teclado.nextLine();
                    
                    String dato2 = apellido2.toUpperCase() + "." + nombre2.toUpperCase();
                    
                    try{
                        fi = new FileInputStream(f);
                        String datoLeido = "";
                        int c;
                        
                        while (fi.available() > 0){
                            c = fi.read();
                            if(c != 10) datoLeido = datoLeido + (char) c;
                            else datoLeido = "";  
          
                            if(datoLeido.equalsIgnoreCase(dato2)) encontrado = true;
                        }
                        
                        if(encontrado == true) System.out.println(dato2 + " Encontrado");
                        else System.out.println(dato2 + " No Encontrado");
                    }catch (IOException e){
                        System.out.println(e.toString());
                    }finally{
                        try{
                            fi.close();
                        }catch(IOException ex){
                            System.out.println(ex.toString());
                        }
                    }
                    break;
                case 3:                   
                    break;
                case 4:                  
                    break;
                case 5:                   
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }
    
}
