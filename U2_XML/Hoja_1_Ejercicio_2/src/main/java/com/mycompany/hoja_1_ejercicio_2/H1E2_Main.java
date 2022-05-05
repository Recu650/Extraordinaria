/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_1_ejercicio_2;

import java.io.File;
import java.util.List;

/**
 *
 * @author usuario
 */
public class H1E2_Main {

    private static GestorXML gestor = new GestorXML();
    private static File fichero = new File("futbolistas.xml");
    private static Futbolista futbolista;

    public static void main(String[] args) {

        int select;
        do {
            System.out.println("|----------------MENU---------------|");
            System.out.println("| 1.Listar futbolistas              |");
            System.out.println("| 2.Añadir futbolista               |");
            System.out.println("| 3.Consultar futbolista por número |");
            System.out.println("| 4.Modificar equipo de futbolista  |");
            System.out.println("| 5.Modificar altura de futbolista  |");
            System.out.println("| 6.Eliminar futbolista             |");
            System.out.println("| 7.Grabar en fichero               |");
            System.out.println("| 0.Salir                           |");
            System.out.println("|-----------------------------------|");
            System.out.println("| Selecciona opcion:                |");
            select = Teclado.introInt("");
            System.out.println("|-----------------------------------|");
            switch (select) {
                case 1:
                    gestor.abrirXML(fichero);
                    System.out.println("LISTADO DE FUTBOLISTAS\n");
                    for (Futbolista f : gestor.mostrarDOM()) {
                        System.out.println("Num: " + f.getId()
                                + "\tAlias: " + f.getAlias()
                                + "\tPosicion: " + f.obtenerPuesto()
                                + "\tEquipo: " + f.getCodeEquipo());

                    }
                    break;
                case 2:
                    gestor.abrirXML(fichero);
                    gestor.insertarFutbolista(crearFutbolista());
                    break;
                case 3:
                    gestor.abrirXML(fichero);
                    buscarFutbolista();
                    break;
                case 4:
                    gestor.abrirXML(fichero);
                    //Si el futbolista buscado existe se le cambia el codigo del equipo
                    futbolista = buscarFutbolista();
                    if (futbolista != null) {
                        futbolista.setCodeEquipo(Teclado.introString("Nuevo Codigo de Equipo: "));
                        gestor.ModificarFutbolista(futbolista);
                    }
                    break;
                case 5:

                    break;
                case 6:
                    gestor.abrirXML(fichero);
                    futbolista = buscarFutbolista();
                    boolean eliminar;
                    if (futbolista != null) {
                        eliminar = Teclado.introBoolean("Seguro que quieres eliminar a " + futbolista.getAlias() + " ?");
                        if(eliminar == true){
                            gestor.EliminarFutbolista(futbolista);
                        }else System.out.println("Operacion anulada");
                    }
                    break;
                case 7:
                    gestor.abrirXML(fichero);
                    String ruta = Teclado.introString("Nombre del nuevo fichero: ");
                    gestor.guardarDOMcomoFILE(ruta + ".xml");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    private static Futbolista crearFutbolista() {
        Futbolista f = new Futbolista();
        short num = (short) Teclado.introInt("Numero del Futbolista: ");
        f.setId(num);
        String alias = Teclado.introString("Alias de Futbolista: ");
        f.setAlias(alias);
        byte puesto;
        do {
            puesto = (byte) Teclado.introInt("Puesto del Futbolista [1-Portero 2-Defensa 3-Centro 4-Delantero]:");
        } while (puesto != 1 && puesto != 2 && puesto != 3 && puesto != 4);
        f.setPuesto(puesto);
        String equipo = Teclado.introString("Codigo del Equipo del Futbolista: ");
        f.setCodeEquipo(equipo);
        return f;
    }

    public static Futbolista buscarFutbolista() {

        Futbolista f = null;

        short num = (short) Teclado.introInt("Numero del jugador que buscas: ");
        f = gestor.mostrarDOMfiltroNumero(num);
        if (f != null) {
            System.out.println("Num: " + f.getId()
                    + "\tAlias: " + f.getAlias()
                    + "\tPosicion: " + f.obtenerPuesto()
                    + "\tEquipo: " + f.getCodeEquipo());

        } else
            System.out.println("Jugador no encontrado :(");
        return f;
    }

}
