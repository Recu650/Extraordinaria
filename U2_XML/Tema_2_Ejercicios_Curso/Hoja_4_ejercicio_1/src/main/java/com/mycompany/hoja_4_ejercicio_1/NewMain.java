/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_4_ejercicio_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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
        try
        {
            // TODO code application logic here
            Source estilo = new StreamSource("departamentos.xsl");
            Source datos = new StreamSource("departamentos.xml");
            
            FileOutputStream fos = null;
            try
            {
                fos = new FileOutputStream("salida.html");
            } catch (FileNotFoundException ex)
            {
                System.out.println(ex.toString());
            }
            
            Result result = new StreamResult(fos);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilo);
            
            transformer.transform(datos, result);
        } catch (TransformerException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
}
