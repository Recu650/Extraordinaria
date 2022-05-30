/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_1;

import java.time.ZoneId;
import java.util.Date;

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
            System.out.println("| 2.5 Ultimos Votos      |");
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
                        System.out.println("Usuario: " + voto.getUsuario().getNombre()
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
                                    System.out.println("|-----NUEVO USUARIO------|");
                                    Usuario usuarioNuevo = new Usuario();
                                    usuarioNuevo.setUsuario(Teclado.introString("Nuevo Usuario: "));
                                    usuarioNuevo.setPassword(Teclado.introString("Nueva contrasenia: "));
                                    usuarioNuevo.setNombre(Teclado.introString("Nuevo Nombre: "));
                                    usuarioNuevo.setApellidos(Teclado.introString("Nuevos Apellidos: "));
                                    usuarioNuevo.setFechanacimiento(
                                            Date.from(Teclado.introFecha("Nueva fecha de nacimiento: ")
                                                    .atStartOfDay(ZoneId.systemDefault())
                                                    .toInstant()));
                                    
                                    gestor.ModificarUsuario(usuarioNuevo, voto.getUsuario().getUsuario());
                                    break;
                                case 2:
                                    if(Teclado.introBoolean("Seguro que quieres eliminar el voto? ")){
                                        if(gestor.EliminarVoto(voto)){
                                            System.out.println("Voto eliminado");
                                        }
                                    }else System.out.println("No eliminamos.");
                                    
                                    break;
                            }
                        } while (select2 != 1 || select2 != 2);
                    }
                    break;
                case 3:
                    for(Voto voto: gestor.votosUsuario(Teclado.introString("Nombre de Usuario: "))){
                        System.out.println(voto.getCancion().getTitulo() + "\t" + voto.getFecha().toString());
                        if(Teclado.introBoolean("Rectificar? ")){
                            gestor.rectificarVoto(voto);
                        }else System.out.println("No rectificamos");
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
