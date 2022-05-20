/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        String fichero = Teclado.introString("Fichero para trabajar: ");
        Path FICHERO = Paths.get("../Hoja_6_Ejercicio_1/" + fichero);
        if (FICHERO.toFile().exists() == false)
            System.out.println("El Fichero no existe");
        else
        {
            int select;
            do
            {
                System.out.println("|----------MENU----------|");
                System.out.println("| 1.List Futbolistas     |");
                System.out.println("| 2.Add Futbolista       |");
                System.out.println("| 3.ListFutbolistaPuesto |");
                System.out.println("| 4.Futbolista Mas Alto  |");
                System.out.println("| 5.Modi Puesto Futbolist|");
                System.out.println("| 6.EliminarFutbolista   |");
                System.out.println("| 0.Salir                |");
                System.out.println("|------------------------|");
                select = Teclado.introInt(" Selecciona opcion:     |");
                System.out.println("|------------------------|");
                switch (select)
                {
                    case 1:
                        getFutbolistas(FICHERO).forEach(System.out::println);
                        break;
                    case 2:
                        addFutbolista(FICHERO);
                        break;
                    case 3:

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

    }

    private static List<Futbolista> getFutbolistas(Path fichero)
    {
        List<Futbolista> futbolistas = null;

        try
        {
            futbolistas = Files.lines(fichero)
                    .map(l -> l.split(","))
                    .map(d -> new Futbolista(Integer.parseInt(d[0]),
                    d[1], d[2], d[3],
                    Puesto.valueOf(d[4]),
                    Float.parseFloat(d[5]),
                    LocalDate.parse(d[6]), d[7]))
                    .toList();
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.toString());
        }
        return futbolistas;
    }

    private static void addFutbolista(Path fichero)
    {
        int id = Teclado.introInt("idJugador: ");
        String nombre = Teclado.introTexto("Nombre: ");
        String apellido = Teclado.introTexto("Apellido: ");
        String alias = Teclado.introString("Alias: ");

        int npuesto = 0;
        do
        {
            npuesto = Teclado.introInt("Puesto [1-PORTERO, 2-DEFENSA, 3-CENTROCAMPISTA, 4-DELANTERO]");
        } while (npuesto != 1 && npuesto != 2 && npuesto != 3 && npuesto != 4);
        Puesto puesto = Puesto.values()[npuesto - 1];

        float altura = (float) Teclado.introDouble("Altura: ");
        LocalDate fNacimiento = Teclado.introFecha("Fecha Nacimiento: ");
        String code = Teclado.introString("Code Equipo: ");

        Futbolista f = new Futbolista(id, nombre, apellido, alias, puesto, altura, fNacimiento, code);

        try
        {
            List<String> cadenaFutbolistas = getFutbolistas(fichero)
                    .stream()
                    .map(f1 -> f1.toCSV())
                    .toList();

            Path write = Files.write(fichero, cadenaFutbolistas);
        }catch(IOException ex){
            System.out.println("Error " + ex.toString());
        }

    }
}
