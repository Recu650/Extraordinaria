/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_8_ejercicio_1;

/**
 *
 * @author usuario
 */
public class Main_H8E1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorDB gestor = new GestorDB();
        int select;
        do {
            System.out.println("|----------------MENU---------------|");
            System.out.println("| 1. Tutor de un curso              |");
            System.out.println("| 2. Cursos sin tutor               |");
            System.out.println("| 3. Cursos sin alumnos             |");
            System.out.println("| 4. Profesores no tutores          |");
            System.out.println("| 5. Número de alumnos en cada curso|");
            System.out.println("| 6. Cursos con menos alumnos que   |");
            System.out.println("| 7. Alumnos del mismo curso que    |");
            System.out.println("| 8. Curso que tiene más alumnos    |");
            System.out.println("| 9. Alumnos con mayor nota media   |");
            System.out.println("| 0.Salir                           |");
            System.out.println("|-----------------------------------|");
            System.out.println("| Selecciona opcion:                |");
            select = Teclado.introInt("");
            System.out.println("|-----------------------------------|");
            switch (select) {
                case 1:
                    String cursoId = Teclado.introString("Curso: ");
                    System.out.println("El tutor del curso " + cursoId + " es "
                            + gestor.tutorCurso(cursoId));
                    break;
                case 2:
                    for (Curso cursoSinTutor : gestor.cursosSinTutor()) {
                        System.out.println(cursoSinTutor.getId() + "\t" + cursoSinTutor.getNombre());
                    }
                    break;
                case 3:
                    for (Curso cursoSinAlumnos : gestor.cursosSinTutor()) {
                        System.out.println(cursoSinAlumnos.getId() + "\t" + cursoSinAlumnos.getNombre());
                    }
                    break;
                case 4:
                    for (Profesor profe : gestor.profeNoTutor()) {
                        System.out.println(profe.getId() + "\t" + profe.getNombre() + "\t" + profe.getFecha_nacimiento());
                    }
                    break;
                case 5:
                    System.out.println(gestor.alumnosEnCadaCurso());
                    break;
                case 6:
                    break;
                case 7:

                    break;
                case 8:
                    for (Alumno alumnoCurso : gestor.alumnosDelCursoDe(Teclado.introString("id alumno:"))) {
                        System.out.println(alumnoCurso.getId() + "\t" + alumnoCurso.getNombre() + "\t"
                                + alumnoCurso.getNota_media());
                    }
                    break;
                case 9:
                    for (Alumno alumnoNota : gestor.alumnosConMasNota(Teclado.introInt("Numero de notas:"))) {
                        System.out.println(alumnoNota.getId() + "\t" + alumnoNota.getNombre() + "\t"
                                + alumnoNota.getNota_media());
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

}
