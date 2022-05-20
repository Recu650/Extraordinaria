/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author usuario
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException
    {
        System.out.println("--------------------------JASON--------------------------");
        URL url = new URL("https://aplicaciones.ivanlorenzo.es/ad/dam2.json");
        System.out.println(leerURL(url));
        
        System.out.println("--------------------------XML---------------------------");
        URL url2 = new URL("https://aplicaciones.ivanlorenzo.es/ad/dam2.xml");
        System.out.println(leerURL(url2));
    }

    
    private static String leerURL (URL url) throws IOException{
        String texto = "";
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea = "";
        while((linea = lector.readLine()) != null){
            texto = texto + linea + "\n";
        }
        return texto;
    }
}
