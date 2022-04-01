/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_4_ejercicio_1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class MainH4E1
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Crear fichero si no existe
        File fichero = new File("Futbolistas.dat");
        try
        {
            if (fichero.exists() == false)
            {
                fichero.createNewFile();
                System.out.println("Fichero creado correctamente");
            }
        } catch (IOException ex)
        {
            System.out.println("Error al crear el archivo");
        }
        //Menu de opciones
        Scanner teclado = new Scanner(System.in);
        int select;
        do
        {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Add Futbolista       |");
            System.out.println("| 2.List Futbolistas     |");
            System.out.println("| 3.List Futbolistas equi|");
            System.out.println("| 4.Buscar Futbolista    |");
            System.out.println("| 5.Modificar equipo futb|");
            System.out.println("| 6.Modificar datos futbo|");
            System.out.println("| 7.Eliminar futbolista  |");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select)
            {
                case 1:
                    int id = Teclado.introInt("ID Jugador: ");
                    if (buscarID(id, fichero) == true)
                        System.out.println("El jugador ya existe");
                    else
                    {
                        Futbolista futbolista = crearFutbolista(id);
                        insertarFutbolista(fichero, futbolista);
                    }
                    break;
                case 2:
                    System.out.println(leerFichero(fichero));
                    break;
                case 3:
                    String codigoEquipo = Teclado.introString("Codigo Equipo Buscar: ");
                    String textoEquipo = leerFicheroEquipo(fichero, codigoEquipo);
                    if (textoEquipo.equalsIgnoreCase(""))
                        System.out.println("Este equipo no tiene jugadores");
                    else
                        System.out.println(textoEquipo);

                    break;
                case 4:
                    int idFutbolista = Teclado.introInt("ID Futbolista Buscar");
                    String textoFutbolista = leerFicheroFutbolista(fichero, idFutbolista);
                    if(textoFutbolista.equalsIgnoreCase("")){
                        System.out.println("No hay futbolistas con ese ID");
                    }else{
                        System.out.println(textoFutbolista);
                    }
                    break;
                case 5:
                    
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    public static boolean buscarID(int id, File fichero)
    {
        int buscaId;
        boolean resul = false;
        FileInputStream fis = null;
        DataInputStream dis = null;

        try
        {
            fis = new FileInputStream(fichero);
            dis = new DataInputStream(new BufferedInputStream(fis));

            do
            {
                buscaId = dis.readInt();
                if (buscaId == id)
                    resul = true;
                //Si lo encuentra sale por aqui y cambia a true
            } while (resul == false);
        } catch (EOFException eof)
        {
            //Si no lo encuentra sale por aqui y lo deja en false
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }

        return resul;
    }

    public static Futbolista crearFutbolista(int id)
    {
        //Pido datos
        String alias = Teclado.introString("Alias:");
        String codigo = Teclado.introString("Codigo Equipo:");
        System.out.println("[1-PORTERO, 2-DEFENSA, 3-CENTROCAMPISTA, 4-DELANTERO]");
        int nPuesto = 0;
        do
        {
            nPuesto = Teclado.introInt("Puesto: ");
        } while (nPuesto != 1 && nPuesto != 2 && nPuesto != 3 && nPuesto != 4);
        Puesto puesto = Puesto.values()[nPuesto - 1];
        float altura = (float) Teclado.introDouble("Altura: ");
        //Creo objeto futbolista
        Futbolista futbolista = new Futbolista(id, alias, codigo, puesto, altura);
        return futbolista;
    }

    public static void insertarFutbolista(File fichero, Futbolista futbolista)
    {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try
        {
            fos = new FileOutputStream(fichero, true); //true para añadir
            dos = new DataOutputStream(fos);

            dos.writeInt(futbolista.getId());
            dos.writeUTF(futbolista.getAlias());
            dos.writeUTF(futbolista.getCodeEquipo());
            dos.writeInt(futbolista.getPuesto().ordinal());
            dos.writeFloat(futbolista.getAltura());

        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dos != null)
                    dos.close();
            } catch (IOException ex)
            {
                System.out.println("Error al cerrar " + ex.toString());
            }
        }
    }

    public static String leerFichero(File fichero)
    {
        FileInputStream fis = null;
        DataInputStream dis = null;
        String textoFichero = "";
        try
        {
            fis = new FileInputStream(fichero);
            dis = new DataInputStream(new BufferedInputStream(fis));
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String codigo = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                Futbolista futbolista = new Futbolista(id, alias, codigo, puesto, altura);
                textoFichero = textoFichero + futbolista.toString() + "\n";
            } while (true);
        } catch (EOFException eof)
        {

        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
            } catch (IOException ex)
            {
                System.out.println("Error al cerrar" + ex.toString());
            }
        }
        return textoFichero;
    }

    public static String leerFicheroEquipo(File fichero, String codigoEquipo)
    {
        FileInputStream fis = null;
        DataInputStream dis = null;
        String textoFichero = "";
        try
        {
            fis = new FileInputStream(fichero);
            dis = new DataInputStream(new BufferedInputStream(fis));
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String codigo = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                if (codigo.equalsIgnoreCase(codigoEquipo))
                {
                    Futbolista futbolista = new Futbolista(id, alias, codigo, puesto, altura);
                    textoFichero = textoFichero + futbolista.toString() + "\n";
                }
            } while (true);
        } catch (EOFException eof)
        {

        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
            } catch (IOException ex)
            {
                System.out.println("Error al cerrar" + ex.toString());
            }
        }
        return textoFichero;
    }
    
    public static String leerFicheroFutbolista(File fichero, int idFutbolista)
    {
        FileInputStream fis = null;
        DataInputStream dis = null;
        String textoFichero = "";
        try
        {
            fis = new FileInputStream(fichero);
            dis = new DataInputStream(new BufferedInputStream(fis));
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String codigo = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();
                
                if(id == idFutbolista){
                    Futbolista futbolista = new Futbolista(id, alias, codigo, puesto, altura);
                    textoFichero = textoFichero + futbolista.toString() + "\n";
                }              
            } while (true);
        } catch (EOFException eof)
        {

        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
            } catch (IOException ex)
            {
                System.out.println("Error al cerrar" + ex.toString());
            }
        }
        return textoFichero;
    }
}
