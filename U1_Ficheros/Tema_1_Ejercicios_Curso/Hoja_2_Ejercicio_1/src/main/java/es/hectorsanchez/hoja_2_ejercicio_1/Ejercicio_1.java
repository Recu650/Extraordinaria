/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_2_ejercicio_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
        int c_entrada;
        FileInputStream f_entrada = null;
        FileOutputStream f_salida = null;
        
        try{
            f_entrada = new FileInputStream("entrada.txt");
            f_salida = new FileOutputStream("salida.txt", true);
            while((c_entrada = f_entrada.read()) != -1){
                //System.out.print((char) c_entrada);
                f_salida.write(c_entrada);
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }finally{
            if(f_entrada != null || f_salida != null){
                try{
                    f_entrada.close();
                    f_salida.close();
                }catch (IOException ex){
                    System.out.println("Error al cerrar fichero de entrada");
                }
            }
        }
    }
    
}
