/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author usuario
 */
public class H2E5_Main {

    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("\"--------------------------JSON--------------------------\"");
        URL url = new URL("https://aplicaciones.ivanlorenzo.es/ad/dam2.json");
        System.out.println(leerURL(url));
        
        System.out.println("\"--------------------------XML--------------------------\"");
        URL url2 = new URL("https://aplicaciones.ivanlorenzo.es/ad/dam2.xml");
        System.out.println(leerURL(url2));
    }
    
    private static String leerURL(URL url) throws IOException {
        String contenido = "";
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea = "";
        while((linea = br.readLine()) != null){
            contenido = contenido + linea + "\n";
        }
        return contenido;
    }
    
}
