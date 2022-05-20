/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author usuario
 */
public class Main
{
    private static GestorBaseDatos gestor;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        gestor = new GestorBaseDatos();
        
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
                    gestor.insertaComAut();
                    break;
                case 2:    
                    gestor.consultaComunidad();
                    break;
                case 3:  
                    gestor.asignaProvinciasComunidad();
                    break;
                case 4:  
                    gestor.addProvinciaComunidad();
                    break;
                case 5:      
                    gestor.modificaNombre();
                    break;
                case 6:
                    gestor.eliminaProvinciaComunidad(); //Incompleto
                    break;
                case 7:
                    gestor.asignaCapitalComunidad();
                    break;
                case 8:   
                    gestor.asignaFechaEstatuto();
                    break;
                case 9:    
                    gestor.eliminaComunidad();
                    break;
                case 10:
                    gestor.comuMasCapi();
                    break;
                case 11:
                    gestor.comuHabitantesEntre();
                    break;
                case 12:
                    gestor.comuUniprovinciales();
                case 13:
                    gestor.capiConMasHabiQue();
                    break;
                case 14:
                    gestor.comuSinFechaEstatutos();
                    break;
                case 15:
                    gestor.provinciasDeUnaComu();
                    break;
                case 16:
                    gestor.crearFicheroJASON();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }
    

}
