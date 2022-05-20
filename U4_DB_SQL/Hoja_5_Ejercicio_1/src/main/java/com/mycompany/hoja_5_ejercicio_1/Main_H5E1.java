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
        for (Componente componente : gestor.listadoComponentes(Teclado.introInt("Codigo del grupo: "))) {
            System.out.println(componente.getNombre() + "\t" + componente.getApellido()
                    + "\t" + componente.getAlias() + "\t" + componente.getFuncion());

            if (Teclado.introBoolean("Modificar alias? ") == true) {
                if (gestor.modificarAlias(componente.getIdcomp()))
                    System.out.println("Alias modificado correctamente");
            }
        }
    }

}
