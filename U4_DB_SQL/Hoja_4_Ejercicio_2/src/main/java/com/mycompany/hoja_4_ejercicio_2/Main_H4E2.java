/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_4_ejercicio_2;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Main_H4E2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorDB gestor = new GestorDB();
        List<Grupo> grupos;
        List<Cancion> canciones;
        List<Voto> votos;

        int select;
        do {
            System.out.println("|--------------MENU---------------|");
            System.out.println("| 1.Listado de grupos             |");
            System.out.println("| 2.Listado de canciones          |");
            System.out.println("| 3.Número de canciones por grupo |");
            System.out.println("| 4.Canciones de un grupo         |");
            System.out.println("| 5.Las 5 canciones más votadas   |");
            System.out.println("| 6.Grupos sin canciones          |");
            System.out.println("| 7.Los últimos 5 votos           |");
            System.out.println("| 8.Eliminar canciones de un grupo|");
            System.out.println("| 9.Modificar datos de grupo      |");
            System.out.println("| 0.Salir                         |");
            System.out.println("|---------------------------------|");
            System.out.println("| Selecciona opcion:              |");
            select = Teclado.introInt("");
            System.out.println("|---------------------------------|");
            switch (select) {
                case 1:
                    grupos = gestor.listadoGrupos();
                    for (Grupo grupo : grupos) {
                        System.out.println(grupo.getCodgrupo() + "\t" + grupo.getNombre()
                                + "\t" + grupo.getLocalidad() + "\t" + grupo.getEstilo());
                    }
                    break;
                case 2:
                    grupos = gestor.listadoGruposCanciones();
                    for (Grupo grupo : grupos) {
                        System.out.println(grupo.getNombre());
                        for (Cancion cancion : grupo.getCanciones()) {
                            System.out.println("\t" + cancion.getTitulo());
                        }
                    }
                    break;
                case 3:
                    grupos = gestor.listadoGruposCanciones();
                    for (Grupo grupo : grupos) {
                        System.out.println(grupo.getNombre() + " tiene "
                                + grupo.getCanciones().size() + " canciones");
                    }
                    break;
                case 4:
                    String nombreGrupo = Teclado.introString("Grupo que Buscas: ");
                    canciones = gestor.cancionesUnGrupo(nombreGrupo);
                    System.out.println("Las canciones de " + nombreGrupo + " son:");
                    for (Cancion cancion : canciones) {
                        System.out.println("Id: " + cancion.getNumcancion()
                                + "\tTitulo: " + cancion.getTitulo()
                                + "\tDuracion: " + cancion.getDuracion());
                    }
                    break;
                case 5:
                    canciones = gestor.cancionesMasVotadas();
                    for (Cancion cancion : canciones) {
                        System.out.println("Votos: " + cancion.getTotal_votos()
                                + "\tTitulo: " + cancion.getTitulo()
                                + "\tGrupo: " + cancion.getGrupo().getNombre());
                    }
                    break;
                case 6:
                    grupos = gestor.listadoGruposCanciones();
                    for(Grupo grupo: grupos){
                        if(grupo.getCanciones().size() == 0){
                            System.out.println(grupo.getNombre() + " no tiene canciones");
                        }
                    }
                    break;
                case 7:
                    votos = gestor.votosMasRecientes();
                    for(Voto voto: votos){
                        System.out.println("Titulo: " + voto.getCancion().getTitulo() +
                                "\tGrupo: " + voto.getCancion().getGrupo().getNombre() + 
                                "\tFecha: " + voto.getFecha());
                    }
                    break;
                case 8:
                    String nombreGrupoBorrar = Teclado.introString("Nombre del Grupo: ");
                    int cancionesBorradas = gestor.eliminarCancionesGrupo(nombreGrupoBorrar);
                    System.out.println("Se eliminaron " + cancionesBorradas + " canciones");
                    break;
                case 9:
                    gestor.modificarGrupo(Teclado.introString("Nombre del grupo que modificar: "));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

}
