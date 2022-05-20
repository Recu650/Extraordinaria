/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_2_ejercicio_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

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
        Scanner teclado = new Scanner(System.in);
        int c;
        FileInputStream f = null;
        
        System.out.println("Ruta del fichero:");
        String ruta = teclado.nextLine();
        
        try{
            f = new FileInputStream(ruta);
            while((c = f.read()) != -1){
                if(c != 32){
                    System.out.print((char) c);
                }
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
    
}
