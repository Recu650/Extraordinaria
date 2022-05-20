/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_1;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args)
    {
        GestorDB gestor = new GestorDB();

        List<Componente> componentes;

        int codigo = Teclado.introInt("Codigo del grupo: ");

        componentes = gestor.listadoComponentes(codigo);

        for (Componente componente : componentes)
        {
            System.out.println(componente.getIdcomp() + " - "
                    + componente.getNombre() + " - "
                    + componente.getApellido() + " - "
                    + componente.getAlias() + " - "
                    + componente.getGrupo().getCodgrupo());
        }

        System.out.println("---------------------------------------------------------------------");

        gestor.ModificarAliasComponentes(codigo);

        System.out.println("---------------------------------------------------------------------");

        componentes = gestor.listadoComponentes(codigo);

        for (Componente componente : componentes)
        {
            System.out.println(componente.getIdcomp() + " - "
                    + componente.getNombre() + " - "
                    + componente.getApellido() + " - "
                    + componente.getAlias() + " - "
                    + componente.getGrupo().getCodgrupo());
        }
    }
}
