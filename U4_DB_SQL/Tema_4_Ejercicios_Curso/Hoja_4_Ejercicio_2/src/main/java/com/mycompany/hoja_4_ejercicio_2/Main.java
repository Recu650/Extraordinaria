/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_4_ejercicio_2;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author usuario
 */
public class Main
{

    public static void main(String[] args)
    {
        GestorDB gestor = new GestorDB();
        List<Grupo> grupos;
        List<Cancion> canciones;
        List<Voto> votos;
        
        Scanner teclado = new Scanner(System.in);
        int select;
        do
        {
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
            select = teclado.nextInt();
            System.out.println("|---------------------------------|");
            switch (select)
            {
                case 1:
                    grupos = gestor.listadoGrupos();
                    for (Grupo grupo : grupos)
                    {
                        System.out.println(grupo.getCodgrupo() + " - "
                                + grupo.getNombre() + " - " + grupo.getLocalidad()
                                + " - " + grupo.getEstilo());
                    }
                    break;
                case 2:
                    grupos = gestor.listadoGruposCanciones();
                    for (Grupo grupo : grupos)
                    {
                        System.out.println(grupo.getNombre());
                        for (Cancion cancion : grupo.getCanciones())
                        {
                            System.out.println("\t" + cancion.getTitulo());
                        }
                    }
                    break;
                case 3:
                    grupos = gestor.listadoGruposCanciones();
                    for (Grupo grupo : grupos)
                    {
                        System.out.println(grupo.getNombre()
                                + " tiene " + grupo.getCanciones().size() + " canciones");
                    }
                    break;
                case 4:
                    String nombreGrupo = Teclado.introString("Grupo buscado: ");
                    canciones = gestor.cancionesUnGrupo(nombreGrupo);
                    System.out.println("Las canciones de " + nombreGrupo + " son:");
                    for(Cancion cancion: canciones){
                        System.out.println("Id: " + cancion.getNumcancion() +
                                "  Titulo: " + cancion.getTitulo() + 
                                "  Duracion: " + cancion.getDuracion());
                    }
                    break;
                case 5:
                    canciones = gestor.cancionesMasVotadas();
                    for(Cancion cancion: canciones){
                        System.out.println("Votos: " + cancion.getTotal_votos() + 
                                " Titulo: " + cancion.getTitulo() + 
                                " Grupo: " + cancion.getGrupoObjeto().getNombre());
                    }
                    break;
                case 6:
                    grupos = gestor.listadoGruposCanciones();
                    for(Grupo grupo: grupos){
                        if(grupo.getCanciones().size() == 0){
                            System.out.println(grupo.getNombre() + " no tiene cancion");
                        }
                    }
                    break;
                case 7:
                    votos = gestor.votosMasRecientes();
                    for(Voto voto: votos){  //Voto[Cancion(Grupo)]
                        System.out.println("Titulo: " + voto.getCancion().getTitulo() +
                                " Grupo: " + voto.getCancion().getGrupoObjeto().getNombre() + 
                                " Fecha: " + voto.getFecha());
                    }
                    break;
                case 8:
                    String nombreGrupoBorrar = Teclado.introString("Nombre del grupo:");
                    int cancionesBorradas = gestor.eliminarCancionesGrupo(nombreGrupoBorrar);
                    System.out.println("Se eliminarion " + cancionesBorradas + " canciones");
                    break;
                case 9:
                    String nombreGrupoModificar = Teclado.introString("Nombre del Grupo:");
                    gestor.modificarGrupo(nombreGrupoModificar);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }
}
