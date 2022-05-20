/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_4_ejercicio_2;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        File f = new File("Futbolistas.dat");
        FileInputStream fis = null;
        DataInputStream dis = null;
        ArrayList<Futbolista> futbolistas = new ArrayList();

        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);

            while (true)
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                int npuesto = dis.readInt();
                Puesto puesto = Puesto.values()[npuesto];
                float altura = dis.readFloat();

                futbolistas.add(new Futbolista(id, alias, code, puesto, altura));
            }
        } catch (EOFException ex){ 
            
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
        finally
        {
            try
            {
                dis.close();
            } catch (IOException ex)
            {
                System.out.println("Error al cerrar fichero");
            }
        }
        Collections.sort(futbolistas, (f1, f2) -> f1.getId() - f2.getId());
        for(Futbolista fut: futbolistas){
            System.out.println(fut.toString());
        }
    }

}
