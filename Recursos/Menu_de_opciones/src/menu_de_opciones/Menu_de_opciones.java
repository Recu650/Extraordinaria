/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_de_opciones;

import java.util.Scanner;

/**
 *
 * @author hsanc
 */
public class Menu_de_opciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in); 
        int select;
        do {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.|");
            System.out.println("| 2.|");
            System.out.println("| 3.|");
            System.out.println("| 4.|");
            System.out.println("| 5.|");
            System.out.println("| 6.|");
            System.out.println("| 7.|");
            System.out.println("| 8.|");
            System.out.println("| 9.|");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select) {
                case 1:                   
                    break;
                case 2:                   
                    break;
                case 3:                   
                    break;
                case 4:                  
                    break;
                case 5:                   
                    break;
                case 6:                  
                    break;
                case 7:                  
                    break;
                case 8:                   
                    break;
                case 9:                   
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }
    
}
