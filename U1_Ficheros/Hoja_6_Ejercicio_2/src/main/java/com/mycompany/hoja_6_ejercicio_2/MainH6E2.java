/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class MainH6E2 {

    private static final File FICHERO = new File("futbolistas.csv");

    public static void main(String[] args) {
        int select;
        do {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.List Futbolistas     |");
            System.out.println("| 2.Add Futbolista       |");
            System.out.println("| 3.ListFutbolistaPuesto |");
            System.out.println("| 4.Futbolista Mas Alto  |");
            System.out.println("| 5.Modi Puesto Futbolist|");
            System.out.println("| 6.EliminarFutbolista   |");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = Teclado.introInt("");
            System.out.println("|------------------------|");
            switch (select) {
                case 1:
                    for (Futbolista futbolista : leerCSV()) {
                        System.out.println(futbolista.toCSV());
                    }
                    break;
                case 2:
                    aniadirFutbolista();
                    break;
                case 3:
                    System.out.println("[1-PORTERO, 2-DEFENSA, 3-CENTROCAMPISTA, 4-DELANTERO]");
                    int nPuesto = 0;
                    do {
                        nPuesto = Teclado.introInt("Puesto para filtrar:");
                    } while (nPuesto != 1 && nPuesto != 2 && nPuesto != 3 && nPuesto != 4);
                    Puesto puesto = Puesto.values()[nPuesto - 1];

                    for (Futbolista futbolista : leerCSVporPuesto(puesto)) {
                        System.out.println(futbolista.toCSV());
                    }
                    break;
                case 4:
                    Futbolista futbolistaMasAlto = null;
                    float alturaMax = Float.MIN_VALUE;

                    for (Futbolista futbolista : leerCSV()) {
                        if (futbolista.getAltura() > alturaMax) {
                            alturaMax = futbolista.getAltura();
                            futbolistaMasAlto = futbolista;
                        }
                    }

                    System.out.println("Futbolista mas alto: " + futbolistaMasAlto.toCSV());
                    break;
                case 5:
                    int idModificar = Teclado.introInt("ID del jugador a modifcar el puesto:");
                    boolean modificado = modificarPuesto(idModificar);
                    if (modificado == true)
                        System.out.println("Jugador modificado correctamente");
                    else
                        System.out.println("Jugador no encontrado");
                    break;

                case 6:
                    int idEliminar = Teclado.introInt("ID del jugador a eliminar:");
                    boolean eliminado = eliminarFutbolista(idEliminar);
                    if (eliminado == true)
                        System.out.println("Jugador eliminado correctamente");
                    else
                        System.out.println("Jugador no encontrado");
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    public static ArrayList<Futbolista> leerCSV() {
        ArrayList<Futbolista> futbolistas = new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();

            while (linea != null) {
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
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            // Cierro el buffer de lectura
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return futbolistas;
    }

    public static Futbolista futbolistaMaker() {
        int id = Teclado.introInt("ID del Jugador:");
        String nombre = Teclado.introString("Nombre del Jugador:");
        String apellidos = Teclado.introString("Apellidos del Jugador:");
        String alias = Teclado.introString("Alias del Jugador:");

        System.out.println("[1-PORTERO, 2-DEFENSA, 3-CENTROCAMPISTA, 4-DELANTERO]");
        int nPuesto = 0;
        do {
            nPuesto = Teclado.introInt("Puesto del Jugador:");
        } while (nPuesto != 1 && nPuesto != 2 && nPuesto != 3 && nPuesto != 4);
        Puesto puesto = Puesto.values()[nPuesto - 1];
        float altura = (float) Teclado.introDouble("Altura del Jugador:");
        LocalDate fechaNacimiento = Teclado.introFecha("Fecha de Nacimiento del Jugador:");
        String codigo = Teclado.introString("Codigo del Equipo del Jugador:");

        Futbolista futbolista = new Futbolista(id, nombre, apellidos, alias, puesto, altura, fechaNacimiento, codigo);
        return futbolista;
    }

    public static void aniadirFutbolista() {
        BufferedWriter bw = null;
        Futbolista futbolista = futbolistaMaker();
        try {
            bw = new BufferedWriter(new FileWriter(FICHERO, true)); //true para que no machaque 
            bw.write(futbolista.toCSV() + "\n");
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void escribirCSV(ArrayList<Futbolista> futbolistas) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(FICHERO, false)); //false para que machaque 
            for (Futbolista futbolista : futbolistas) {
                bw.write(futbolista.toCSV() + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static ArrayList<Futbolista> leerCSVporPuesto(Puesto puestoBuscado) {
        ArrayList<Futbolista> futbolistas = new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();

            while (linea != null) {
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

                if (futbolista.getPuesto().toString().equalsIgnoreCase(puestoBuscado.toString())) {
                    futbolistas.add(futbolista);
                }

                linea = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            // Cierro el buffer de lectura
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return futbolistas;
    }

    public static boolean eliminarFutbolista(int idEliminar) {
        boolean eliminado = false;
        ArrayList<Futbolista> futbolistas = new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();

            while (linea != null) {
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
                if (futbolista.getIdJugador() == idEliminar) {
                    eliminado = true;
                } else {
                    futbolistas.add(futbolista);
                }

                linea = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            // Cierro el buffer de lectura
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        escribirCSV(futbolistas);
        return eliminado;
    }

    public static boolean modificarPuesto(int idModificar) {
        boolean modificado = false;
        ArrayList<Futbolista> futbolistas = new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();

            while (linea != null) {
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
                if (futbolista.getIdJugador() == idModificar) {
                    modificado = true;
                    System.out.println(futbolista.toCSV());

                    System.out.println("[1-PORTERO, 2-DEFENSA, 3-CENTROCAMPISTA, 4-DELANTERO]");
                    int nPuesto = 0;
                    do {
                        nPuesto = Teclado.introInt("Nuevo Puesto del Jugador:");
                    } while (nPuesto != 1 && nPuesto != 2 && nPuesto != 3 && nPuesto != 4);
                    Puesto puestoNuevo = Puesto.values()[nPuesto - 1];
                    futbolista.setPuesto(puestoNuevo);
                    
                    futbolistas.add(futbolista);
                } else {
                    futbolistas.add(futbolista);
                }

                linea = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            // Cierro el buffer de lectura
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        escribirCSV(futbolistas);
        return modificado;
    }
}
