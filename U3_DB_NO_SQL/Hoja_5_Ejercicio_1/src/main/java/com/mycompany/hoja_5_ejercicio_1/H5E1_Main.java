/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_1;

import org.bson.Document;

/**
 *
 * @author usuario
 */
public class H5E1_Main {

    private static GestorBaseDatos gestor;

    public static void main(String[] args) {

        gestor = new GestorBaseDatos();
        ComunidadAutonoma ccaa;

        int select;
        do {
            System.out.println("|---------------------MENU-------------------|");
            System.out.println("| 1.Añadir Comunidad Autónoma sin provincias |");
            System.out.println("| 2.Consultar Comunidad Autónoma             |");
            System.out.println("| 3.Asignar provincias a Comunidad Autónoma  |");
            System.out.println("| 4.Añadir provincia a Comunidad Autónoma    |");
            System.out.println("| 5.Modificar nombre de Comunidad Autónoma   |");
            System.out.println("| 6.Eliminar provincia en Comunidad Autónoma |");
            System.out.println("| 7.Asignar capital a Comunidad Autónoma     |");
            System.out.println("| 8.Asignar fecha Estatuto Autonomía         |");
            System.out.println("| 9.Eliminar Comunidad Autónoma              |");
            System.out.println("|10.Muestra ccaa y capitales                 |");
            System.out.println("|11.Obtener ccaa con habitantes entre valores|");
            System.out.println("|12.Obtener ccaa uniprovinciales             |");
            System.out.println("|13.Obtener capitales con más habitantes que |");
            System.out.println("|14.Obtener ccaa sin fecha de estatuto       |");
            System.out.println("|15.Obtener provincias de ccaa               |");
            System.out.println("|16.Crear fichero JSON                       |");
            System.out.println("| 0.Salir                                    |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Selecciona opcion:                         |");
            select = Teclado.introInt("");
            System.out.println("|--------------------------------------------|");
            switch (select) {

                case 1:
                    ccaa = new ComunidadAutonoma();
                    ccaa.setCodigo(Teclado.introString("Codigo de la ccaa:"));
                    ccaa.setNombre(Teclado.introString("Nombre de la ccaa:"));
                    ccaa.setAbreviatura(Teclado.introString("Abreviatura de la ccaa:"));
                    ccaa.setNombreCapi(Teclado.introString("Nombre de la capital:"));
                    ccaa.setHabitantesCapi(Teclado.introInt("Habitantes de la capital"));
                    ccaa.setHabitantes(Teclado.introInt("Habitantes de la ccaa:"));
                    ccaa.setExtension(Teclado.introInt("Extension de la ccaa:"));

                    gestor.insertarCCAA(ccaa);
                    break;

                case 2:
                    ccaa = gestor.consultaCCAA(Teclado.introString("Codigo de la ccaa que buscas:"));

                    if (ccaa != null) {
                        System.out.println("Comunidad Autonoma: " + ccaa.getNombre());
                        System.out.println("Abreviatura: " + ccaa.getAbreviatura());
                        System.out.println("Numero de Habitantes: " + ccaa.getHabitantes());
                        System.out.println("Extension: " + ccaa.getExtension());
                        System.out.println("Capital: " + ccaa.getNombreCapi());
                        System.out.println("Habitantes de la Capital: " + ccaa.getHabitantesCapi());
                    } else
                        System.out.println("Error! CCAA No encontrada.");
                    break;

                case 3:
                    gestor.asignarProvincias(Teclado.introString("Codigo de la ccaa: "));
                    break;

                case 4:
                    gestor.addProvincia(Teclado.introString("Codigo de la ccaa: "),
                            Teclado.introString("Provincia de la ccaa: "));
                    break;

                case 5:
                    gestor.modificarNombre(Teclado.introString("Codigo de la ccaa: "),
                            Teclado.introString("Nuevo nombre: "));
                    break;

                case 6:
                    gestor.eliminarProvincia(Teclado.introString("Codigo de la ccaa: "),
                            Teclado.introString("Provincia a eliminar: "));
                    break;

                case 7:
                    gestor.asignarCapital(Teclado.introString("Codigo de la ccaa: "),
                            Teclado.introString("Capital de la ccaa: "),
                            Teclado.introDouble("Habitantes de la capi: "));
                    break;

                case 8:
                    String codigo = Teclado.introString("Codigo de la ccaa:");
                    System.out.println("Fecha de entrada en vigor del estatuto autonomico:");
                    int dia = Teclado.introInt("Dia: ");
                    int mes = Teclado.introInt("Mes: ");
                    int anio = Teclado.introInt("Anio: ");
                    String fecha = dia + "/" + mes + "/" + anio;
                    
                    gestor.asignarFechaEstatuto(codigo, fecha);
                    break;
                    
                case 9:
                    gestor.eliminarComunidad(Teclado.introString("Codigo de la ccaa:"));
                    break;
                    
                case 10:
                    gestor.comuMasCapi();
                    break;
                
                case 11:
                    
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

}
