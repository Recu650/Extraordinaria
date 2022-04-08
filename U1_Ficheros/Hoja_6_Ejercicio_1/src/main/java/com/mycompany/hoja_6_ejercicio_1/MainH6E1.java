/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class MainH6E1
{

    private static final File FICHERO = new File("futbolistas.csv");
    public static final char SEPARADOR = ';';

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        int select;
        do
        {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.List Futbolistas     |");
            System.out.println("| 2.List Futbolistas Equi|");
            System.out.println("| 3.Buscar Futbolista    |");
            System.out.println("| 4.Crear Fichero Equipo |");
            System.out.println("| 5.List Fichero Equipo  |");
            System.out.println("| 6.Todos Ficheros Equipo|");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select)
            {
                case 1:
                    ArrayList<Futbolista> futbolistas = leerCSV();
                    for(Futbolista futbolista: futbolistas){
                        System.out.println(futbolista.toCSV());
                    }
                    break;
                case 2:
                    String equipoBuscar = Teclado.introString("Equipo que buscas:");
                    ArrayList<Futbolista> equipoFiltro = leerCSVFiltroEquipo(equipoBuscar);
                    for(Futbolista futbolista: equipoFiltro){
                        System.out.println(futbolista.toCSV());
                    }
                    break;
                case 3:
                    String aliasBuscar = Teclado.introString("Alias del futbolista que buscas:");
                    ArrayList<Futbolista> futbolistasFiltro = leerCSVFiltroFutbolista(aliasBuscar);
                    for(Futbolista futbolista: futbolistasFiltro){
                        System.out.println(futbolista.toCSV());
                    }
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    public static ArrayList<Futbolista> leerCSV()
    {
        ArrayList<Futbolista> futbolistas = new ArrayList();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();

            while (linea != null)
            {
                String[] campos = linea.split(",");
                
                int id = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String apellidos = campos[2];
                String alias = campos[3];
                Puesto puesto = Puesto.valueOf(campos[4]);
                float altura = Float.parseFloat(campos[5]);
                LocalDate fNacimiento = LocalDate.parse(campos[6]);
                String code = campos[7];
                
                Futbolista futbolista = new Futbolista(id, nombre, apellidos, alias, puesto, altura, fNacimiento, code);
                futbolistas.add(futbolista);
                
                linea = br.readLine();
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            // Cierro el buffer de lectura
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return futbolistas;
    }
    public static ArrayList<Futbolista> leerCSVFiltroEquipo(String equipoBuscar)
    {
        ArrayList<Futbolista> futbolistas = new ArrayList();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();

            while (linea != null)
            {
                String[] campos = linea.split(",");
                
                int id = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String apellidos = campos[2];
                String alias = campos[3];
                Puesto puesto = Puesto.valueOf(campos[4]);
                float altura = Float.parseFloat(campos[5]);
                LocalDate fNacimiento = LocalDate.parse(campos[6]);
                String code = campos[7];
                
                if(equipoBuscar.equalsIgnoreCase(code)){
                    Futbolista futbolista = new Futbolista(id, nombre, apellidos, alias, puesto, altura, fNacimiento, code);
                    futbolistas.add(futbolista);
                }
  
                linea = br.readLine();
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            // Cierro el buffer de lectura
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return futbolistas;
    }
    
    public static ArrayList<Futbolista> leerCSVFiltroFutbolista(String aliasBuscar)
    {
        ArrayList<Futbolista> futbolistas = new ArrayList();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();

            while (linea != null)
            {
                String[] campos = linea.split(",");
                
                int id = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String apellidos = campos[2];
                String alias = campos[3];
                Puesto puesto = Puesto.valueOf(campos[4]);
                float altura = Float.parseFloat(campos[5]);
                LocalDate fNacimiento = LocalDate.parse(campos[6]);
                String code = campos[7];
                
                if(aliasBuscar.equalsIgnoreCase(alias)){
                    Futbolista futbolista = new Futbolista(id, nombre, apellidos, alias, puesto, altura, fNacimiento, code);
                    futbolistas.add(futbolista);
                }
  
                linea = br.readLine();
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            // Cierro el buffer de lectura
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return futbolistas;
    }
    
    public static void escribirCSV(ArrayList<Futbolista> futbolistas){
        
    }
}
