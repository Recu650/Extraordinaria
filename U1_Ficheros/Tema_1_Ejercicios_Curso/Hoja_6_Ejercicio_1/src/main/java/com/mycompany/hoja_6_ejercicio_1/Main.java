/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author usuario
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    
    private static final Path FICHERO = Paths.get("futbolistas.csv");
    
    public static void main(String[] args)
    {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in); 
        int select;
        do {
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
            switch (select) {
                case 1:
                    getFutbolistas(FICHERO).forEach(System.out::println);  
                    break;
                case 2:
                    listarFutbolistasEquipo();
                    break;
                case 3: 
                    buscarFutbolista();
                    break;
                case 4:                
                    crearFicheroEquipo();
                    break;
                case 5:    
                    String fichero = Teclado.introString("Nombre del Fichero");
                    getFutbolistas(Paths.get(fichero)).forEach(System.out::println);
                    break;
                case 6:   
                    mostrarTodosFicherosEquipo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
        
    }
    
    private static List<Futbolista> getFutbolistas(Path fichero){
        List<Futbolista> futbolistas= null;
        
        try{
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
    
    private static void listarFutbolistasEquipo(){
        String codigoEqipo = Teclado.introString("Codigo del equipo a buscas: ");
        getFutbolistas(FICHERO)
                .stream()
                .filter(f -> f.getCodeEquipo().equalsIgnoreCase(codigoEqipo))
                .forEach(System.out::println);
    }
    private static void buscarFutbolista(){
        String aka = Teclado.introString("Alias de Futbolista: ");
        getFutbolistas(FICHERO)
                .stream()
                .filter(f -> f.getAlias().equalsIgnoreCase(aka))
                .forEach(System.out::println);        
    }
    private static void crearFicheroEquipo() {
        String codigoEqipo = Teclado.introString("Codigo del equipo a buscas: ");
        Path ficheroEquipo = Paths.get(codigoEqipo.toUpperCase() + ".csv");
        
        try{
            List<String> cadenaFutbolistas = getFutbolistas(FICHERO)
                    .stream()
                    .filter(f -> f.getCodeEquipo().equalsIgnoreCase(codigoEqipo))
                    .map(f -> f.toCSV())
                    .toList();
            Path write = Files.write(ficheroEquipo, cadenaFutbolistas);
        } catch (IOException ex)
        {
            System.out.println("Error al Escribir: " + ex.toString());
        }
    }
    
    private static void mostrarTodosFicherosEquipo(){
        try{
        Stream<Path> stream = Files.list(FICHERO.toAbsolutePath().getParent());
        stream
                .map(s -> s.getFileName().toString())
                .filter(f -> f.endsWith(".csv"))
                .forEach(System.out::println); // f -> System.out.println(f)
        } catch (IOException ex)
        {
            System.out.println("Error " + ex.toString());
        }
    }
    
}
