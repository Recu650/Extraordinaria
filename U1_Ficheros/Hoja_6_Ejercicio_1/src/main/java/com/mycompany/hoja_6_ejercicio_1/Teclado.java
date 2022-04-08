/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_1;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author DAM126
 */
public class Teclado {
    //Metodo para introducir un entero------------------------------------------

    public static int introInt(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        boolean valido = false;
        int resul = 0;
        do {
            try {
                System.out.println(mensaje);
                resul = teclado.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("No es un entero");
                System.out.println(e.toString());
                teclado.next();
            }
        } while (valido != true);
        return resul;
    }
    
    //Metodo para introducir un double------------------------------------------
    
    public static double introDouble(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        boolean valido = false;
        double resul = 0;
        do {
            try {
                System.out.println(mensaje);
                resul = teclado.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                teclado.next(); //Limpiar el buffer para no caer en un bucle infinito
                System.out.println("No es un double [',' no '.']");
                System.out.println(e.toString());
            }
        } while (valido == false);
        return resul;
    }
    
    //Metodo para introducir un String------------------------------------------
    
    public static String introString(String mensaje) { //Mensaje que suelta
        Scanner teclado = new Scanner(System.in);
        System.out.println(mensaje);
        String resul = teclado.nextLine();
        return resul;
    }

    //Metodo para introducir un Texto-------------------------------------------
    
    public static String introTexto(String mensaje) { //Mensaje que suelta
        Scanner teclado = new Scanner(System.in);
        String resul = "";
        boolean valido = false;
        do {
            System.out.println(mensaje);
            resul = teclado.nextLine();
            if (resul.matches("[A-Za-z ]*")) { //Espacio en blanco para que pueda contener espacios
                valido = true;
            } else {
                System.out.println("Error. Solo Caracteres.");
            }
        } while (!valido);
        return resul;
    }

    //Metodo para introducir una Fecha------------------------------------------
    
    public static LocalDate introFecha(String mensaje) {
        boolean valido = false;
        LocalDate resul = null;
        do {
            try {
                System.out.println(mensaje);
                int dia = Teclado.introInt("DIA:");
                int mes = Teclado.introInt("MES:");
                int anio = Teclado.introInt("AÑO:");
                resul = LocalDate.of(anio, mes, dia);
                valido = true;
            } catch (DateTimeException e) {
                System.out.println("Valor no valido [(1-31)/(1-12)/(1-4000)]");
                System.out.println(e.toString());
            }
        } while (valido != true);
        return resul;
    }

    //Metodo para introducir una Fecha y Hora-----------------------------------
    
    public static LocalDateTime introFechaHora(String mensaje) {
        boolean valido = false;
        LocalDateTime resul = null;
        do {
            try {
                System.out.println(mensaje);
                int dia = Teclado.introInt("DIA:");
                int mes = Teclado.introInt("MES:");
                int anio = Teclado.introInt("AÑO:");
                int hora = Teclado.introInt("HORA:");
                int min = Teclado.introInt("MINUTOS:");
                int seg = Teclado.introInt("SEGUNDOS:");
                resul = LocalDateTime.of(anio, mes, dia, hora, min, seg);
                valido = true;
            } catch (DateTimeException e) {
                System.out.println("Valor no valido [(1-31)/(1-12)/(1-4000)/(0-24)/(0-60)/(0-60)]");
                System.out.println(e.toString());
            }
        } while (valido != true);
        return resul;
    }

    //Metodo para introducir un DNI---------------------------------------------
    
    public static String introDni() {
        String resul = "";
        boolean valido = false;
        do {
            try {
                resul = Teclado.introString("Introduce dni [8 numeros 1 letra]: ");
                if (resul.matches("[0-9]{8}[A-Z]")) {
                    valido = true;
                } else {
                    throw new Exception("Error en el formato [8 numeros y 1 letra].");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } while (!valido);
        return resul;
    }
    
    //Metodo para introducir un numero de la Seguridad Social-------------------
    
    public static String introNumSS() {
        String resul = "";
        boolean valido = false;
        do {
            try {
                resul = Teclado.introString("Introduce Numero SS [39-XXXXX]: ");
                if (resul.matches("39-[0-9]{5}")) {
                    valido = true;
                } else {
                    throw new Exception("Error en el formato [39-XXXXX].");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } while (!valido);
        return resul;
    }
    //Metodo para introducir una pregunta por boolean---------------------------
    
    public static boolean introBoolean(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        boolean campo = false;
        char opcion;
        do {
            System.out.println(mensaje + " [S o N]:");
            opcion = teclado.nextLine().charAt(0);
        } while (opcion != 'S' && opcion != 's' && opcion != 'N' && opcion != 'n');
        if (opcion == 'S' || opcion == 's') {
            campo = true;
        }
        return campo;
    }
    
    //Metodo para introducir una nota-------------------------------------------
    
//    public static int introNota() {
//        
//        boolean valido = false;
//        int num = 0;
//
//        do {
//            try {
//                num = Teclado.introInt("Introduce un nota entre 0 y 10: ");
//                if ((num < 0) || (num > 10)) {
//                    throw new FueraDeRango("Numero incorrecto. Se espera un número entre 0 y 10");
//                }
//                valido = true;
//
//            } catch (FueraDeRango ex) {
//                System.out.println(ex.toString());
//
//            }
//        } while (!valido);
//        return num;
//    }
    
    //EXCEPCION PERSONALIZADA---------------------------------------------------
    
//    public class FueraDeRango extends Exception {
//
//        String mensaje;
//
//        public FueraDeRango(String m) {
//            mensaje = m;
//        }
//
//        @Override
//        public String toString() {
//            return "FueraDeRango{" + "mensaje=" + mensaje + '}';
//        }
//
//    }
    
    //--------------------------------------------------------------------------
    
    //Metodo para introducir un entero positivo---------------------------------
    
//    public static int introIntPos(String mensaje) {
//        boolean valido = false;
//        do {
//            try {
//                int num = Teclado.introInt(mensaje);
//                if (num < 0) {
//                    throw new NumeroNegativoException("Error, es negativo");
//                } else {
//                    valido = true;
//                }
//            } catch (NumeroNegativoException ex) {
//                System.out.println(ex.toString());
//                System.out.println("Introduce un numero POSITIVO");
//            }
//        } while (!valido);
//    }

    //EXCEPCION PERSONALIZADA---------------------------------------------------
    
//    public class NumeroNegativoException extends Exception {
//
//        public NumeroNegativoException() {
//        }
//
//        public NumeroNegativoException(String message) {
//            super(message);
//        }
//
//        @Override
//        public String toString() {
//            return "NumeroNegativoException{" + this.getMessage() + '}';
//        }
//    }
    
    //--------------------------------------------------------------------------
}
