/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_5_ejercicio_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author usuario
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        int select;
        do
        {
            System.out.println("|----------------------------MENU----------------------------|");
            System.out.println("| 1.Datos de artistas más populares en tu país               |");
            System.out.println("| 2.30 mejores temas de un artista                           |");
            System.out.println("| 3.Datos de 20 artistas más populares de un usuario concreto|");
            System.out.println("| 4.Obtener 10 artistas similares a uno dado                 |");
            System.out.println("| 0.Salir                                                    |");
            System.out.println("|------------------------------------------------------------|");
            System.out.println("| Selecciona opcion:                                         |");
            select = teclado.nextInt();
            System.out.println("|------------------------------------------------------------|");
            switch (select)
            {
                case 1:
                    int n = Teclado.introInt("Cantidad de artistas: ");
                    try
                    {
                        URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=spain&api_key=5565b5de960d7f55d05d01310fd8bec2&limit=" + n);
                        InputStream is = leerURL(url);
                        ArtistasHandler handler = new ArtistasHandler();
                        parsear(is, handler);

                    } catch (MalformedURLException ex)
                    {
                        System.out.println(ex.toString());
                    } catch (IOException ex)
                    {
                        System.out.println(ex.toString());
                    }

                    break;

                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    private static InputStream leerURL(URL url) throws IOException
    { // debuelba InputStream
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        return conexion.getInputStream();
    }

    private static void parsear(InputStream is, DefaultHandler dh)
    {
        SAXParserFactory factoria = SAXParserFactory.newInstance();

        try
        {
            SAXParser parser = factoria.newSAXParser();
            parser.parse(is, dh);
        } catch (ParserConfigurationException | SAXException | IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

}
