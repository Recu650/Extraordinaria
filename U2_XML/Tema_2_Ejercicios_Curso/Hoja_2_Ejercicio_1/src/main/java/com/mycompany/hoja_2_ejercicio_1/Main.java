/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_2_ejercicio_1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

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
        try
        {
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser parser = factoria.newSAXParser();
            DepartamentosHandler manejador = new DepartamentosHandler();
            File fichero = new File()
        } catch (ParserConfigurationException ex)
        {
            ex.toString();
        } catch (SAXException ex)
        {
            ex.toString();
        }

    }

}
