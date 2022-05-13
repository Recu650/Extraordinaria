/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_7;

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
        GestorBaseDatos gestor = new GestorBaseDatos();
        
        int select;
        do {
            System.out.println("|---------------------MENU-------------------|");
            System.out.println("| 1.Añadir notas a alumnos de curso          |");
            System.out.println("| 2.Añadir tema a curso                      |");
            System.out.println("| 3.Obtener alumnos de curso                 |");
            System.out.println("| 4.Número de alumnos por curso              |");
            System.out.println("| 5.Modificar nota media de alumno           |");
            System.out.println("| 6.Modificar horas de curso                 |");
            System.out.println("| 7.Datos de alumno                          |");
            System.out.println("| 8.Nota media en curso                      |");
            System.out.println("| 0.Salir                                    |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Selecciona opcion:                         |");
            select = Teclado.introInt("");
            System.out.println("|--------------------------------------------|");
            switch (select) {
                case 1:    
                    gestor.aniadirNotas();
                    break;
                case 2:    
                    gestor.aniadirTema();
                    break;
                case 3:  
                   gestor.obtenerAlumnosCurso();
                    break;
                case 4:  
                    gestor.numeroAlumnosCurso();
                    break;
                case 5:      
                    gestor.modificarNotaMedia();
                    break;
                case 6:
                    gestor.modificarHorasCurso();
                    break;
                case 7:
                    gestor.datosAlumno();
                    break;
                case 8:   
                    gestor.notaMedia();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }
    
}
