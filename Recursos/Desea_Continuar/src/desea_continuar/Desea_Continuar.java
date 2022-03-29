/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desea_continuar;

import java.util.Scanner;

/**
 *
 * @author hsanc
 */
public class Desea_Continuar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        char select = 's';
        do {
            //CODE//
            System.out.println("Repeticion");
            do{
                System.out.println("Continuamos ? [s | n]:");
                select = teclado.next().charAt(0);
            }while(select != 's' && select != 'S' && select != 'n' && select != 'N');
        } while (select == 's' || select == 'S');
    }
    
}
