/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6;

import BaseDatos.GestorDB;
import Modelo.Pregunta;
import Modelo.Respuesta;
import Modelo.Usuario;
import Util.GestorFicheros;
import Util.Teclado;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) {
        GestorDB gestor = new GestorDB();
        GestorFicheros gestorFicheros = new GestorFicheros();
        int opcion = 0;

        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("");
            System.out.println("[======================================]");
            System.out.println("[         QUE COMIENCE EL JUEGO        ]");
            System.out.println("[======================================]");
            System.out.println("[ ¿Que quieres hacer                   ]");
            System.out.println("[                                      ]");
            System.out.println("[ 1 - Añadir un nuevo usuario          ]");
            System.out.println("[ 2 - Cargar fichero                   ]");
            System.out.println("[ 3 - Ver preguntas por categoría      ]");
            System.out.println("[ 4 - Jugar                            ]");
            System.out.println("[ 5 - Salir                            ]");
            System.out.println("[======================================]");
            opcion = teclado.nextInt();
            teclado.nextLine();
            System.out.println("");
            switch (opcion) {
                case 1: //Ejercicio_1: Registrar un usuario nuevo en la base de datos
                    boolean respuesta;
                    Usuario usuario = pedirDatos();
                    respuesta = gestor.registrarUsusario(usuario);

                    System.out.println("");
                    if (respuesta == true)
                        System.out.println("Usuario registrado correctamente");
                    else
                        System.out.println("Error al registrar usuario");
                    System.out.println("");
                    break;
                case 2://Ejercicio_2: Add una lista de preguntas leida de un CSV a la base de datos
                    File fichero = new File("fichero.csv");
                    List<Pregunta> preguntas = gestorFicheros.leerPreguntasCSV(fichero);

                    int preguntasAniadidas = gestor.aniadirPreguntas(preguntas);
                    System.out.println(preguntasAniadidas + " pretguntas añadidas correctamente");
                    break;
                case 3:
                    String categoria = Teclado.introString("Categoria para mostrar preguntas: ");
//                  gestor.preguntasPorCategorias(categoria);

                    List<Pregunta> preguntasCategoria = new ArrayList();
                    preguntasCategoria = gestor.preguntasPorCategorias2(categoria);
                    
                    for(Pregunta p: preguntasCategoria){
                        System.out.println(p.getEnunciado());
                        System.out.println("");
                        
                        for(Respuesta r: p.getRespuestas()){
                            System.out.println(r.getTexto());
                        }
                        System.out.println("");
                    }
                    break;
                case 4:
                    String introUsuario = Teclado.introString("USUARIO: ");
                    String introPassword = Teclado.introString("PASSWORD: ");
                    gestor.jugar(introUsuario, introPassword);
                    break;
                case 5:

                    break;
                default:
                    System.out.println("No es una opción válida");
            }
        } while (opcion != 5);
    }

    private static Usuario pedirDatos() {
        String nombre = Teclado.introString("NOMBRE:");
        String apellidos = Teclado.introString("APELLIDOS:");
        Date fecha_nacimiento = Date.valueOf(Teclado.introFecha("FECHA DE NACIMIENTO:"));
        String usuario = Teclado.introString("USUARIO:");
        String password = Teclado.introString("PASSWORD:");

        Usuario user = new Usuario();

        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setFecha_nacimiento(fecha_nacimiento);
        user.setUsuario(usuario);
        user.setPassword(password);

        return user;
    }
}
