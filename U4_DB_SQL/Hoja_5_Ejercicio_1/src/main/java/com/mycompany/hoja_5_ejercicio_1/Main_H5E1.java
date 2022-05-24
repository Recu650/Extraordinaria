/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_1;

/**
 *
 * @author usuario
 */
public class Main_H5E1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorDB gestor = new GestorDB();

        int select;
        do {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Modificar Alias      |");
            System.out.println("| 2.|");
            System.out.println("| 3.|");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = Teclado.introInt("");
            System.out.println("|------------------------|");
            switch (select) {
                case 1:
                    for (Componente componente : gestor.listadoComponentes(Teclado.introInt("Codigo del grupo: "))) {
                        System.out.println(componente.getNombre() + "\t" + componente.getApellido()
                                + "\t" + componente.getAlias() + "\t" + componente.getFuncion());

                        if (Teclado.introBoolean("Modificar alias? ") == true) {
                            if (gestor.modificarAlias(componente.getIdcomp()))
                                System.out.println("Alias modificado correctamente");
                        }
                    }
                    break;
                case 2:
                    for (Voto voto : gestor.cincoUltimosVotos()) {
                        System.out.println("Usuario: " + voto.getUsuario()
                                + "\tFecha: " + voto.getFecha()
                                + "\tCancion: " + voto.getCancion().getTitulo() + " - "
                                + voto.getCancion().getDuracion());

                        int select2;
                        do {
                            System.out.println("|----------MENU----------|");
                            System.out.println("| 1.Modificar Ususario   |");
                            System.out.println("| 2.Eliminar             |");
                            System.out.println("|------------------------|");
                            System.out.println("| Selecciona opcion:     |");
                            select2 = Teclado.introInt("");
                            System.out.println("|------------------------|");
                            
                            switch(select2) {
                                case 1:
                                    break;
                                case 2:
                                    break;
                            }
                        } while (select2 != 1 || select2 != 2);

                    }
                    break;
                case 3:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);

    }

}
