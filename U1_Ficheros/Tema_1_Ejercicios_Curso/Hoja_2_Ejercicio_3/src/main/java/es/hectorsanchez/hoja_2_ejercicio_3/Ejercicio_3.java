/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_2_ejercicio_3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
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
        Random r = new Random();
        int select;
        File f = null;
        FileOutputStream fo = null;
        do
        {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Crear Fichero Numeros|");
            System.out.println("| 2.Aniadir Numero       |");
            System.out.println("| 3.Listar Numeros       |");
            System.out.println("| 4.Listar Numeros Hexadc|");
            System.out.println("| 5.Buscar Numero        |");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select)
            {
                case 1:


                        try
                        {
                            f = new File("numeros.txt");
                            //f.createNewFile(); Si no existe lo crea automaticamente
                            fo = new FileOutputStream(f);
                            for (int i = 0; i < 10; i++)
                            {
                                int n = r.nextInt(256);
                                fo.write(n);
                            }
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
                                    System.out.println("Error al cerrar");
                                }
                            }
                        }
                    
                    break;
                case 2:
                    int n2 = 0;
                    try
                    {
                        if (f.exists() == true)
                        {
                            fo = new FileOutputStream(f, true);
                            
                            do{
                                System.out.println("Numero [0 - 255]: ");
                                n2 = teclado.nextInt();
                                fo.write(n2);
                            }while(n2 < 0 || n2 > 255);
                        }
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
                                System.out.println("Error al cerrar");
                            }
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
