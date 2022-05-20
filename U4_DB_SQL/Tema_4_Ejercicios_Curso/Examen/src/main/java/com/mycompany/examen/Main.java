/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.examen;

import BaseDatos.GestorDB;
import Util.Teclado;

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
        GestorDB gestor = new GestorDB();
        int select;
        do {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Modificar urgengia articulo|");
            System.out.println("| 2.Eliminar pedido antiguo|");
            System.out.println("| 3.Propietario en cuyas fabricas hay menos unidades no servidas|");
            System.out.println("| 4.Lista de articulos en los que no se ha producido ningun pedido|");
            System.out.println("| 5.Lista de fabricas pro pais del propietario|");
            System.out.println("| 6.Los 5 articulos con mas cantidad pedida|");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = Teclado.introInt("");
            System.out.println("|------------------------|");
            switch (select) {
                case 1: 
                    String articuloModificar = Teclado.introString("Articulo a Modificar Urgencia: ");
                    int nPedidosModificados = gestor.modificarUrgenciaArticulo(articuloModificar);
                    System.out.println(articuloModificar + " se modificó " + nPedidosModificados + " veces");
                    break;
                case 2:   
                    boolean correcto = gestor.eliminarPedidoAntiguo();
                    if(correcto == true) System.out.println("Pedido antiguo eliminado");
                    else System.out.println("Error, el pedido no ha sido eliminado correctamente");
                    break;
                case 3:                   
                    break;
                case 4:                  
                    break;
                case 5:                   
                    break;
                case 6:                  
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }
    
}
