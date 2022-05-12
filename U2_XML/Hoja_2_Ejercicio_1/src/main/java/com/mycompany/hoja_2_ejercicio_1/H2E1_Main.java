/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_2_ejercicio_1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author usuario
 */
public class H2E1_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //Declaramos e instanciamos un objeto factoría para poder crear un objeto
            //parseador. SAXParserFactory es una clase abstracta y no tiene constructor
            SAXParserFactory factoria = SAXParserFactory.newDefaultInstance();
            
            //Declaramos un parseador para SAX y lo construimos con factoria
            //Genera la excepcion ParserConfigurationException
            SAXParser parser = factoria.newSAXParser();
            
            //Ahora es cuando se procede a parsear el fichero XML. Al método que parsea
            //hay que pasarle un File del fichero a parsear y un handler o manejador
            //(este debe ser un objeto de una clase creada a partir de DefaultHandler)
            //Define como es el documento y como se tiene que interpretar
            HandlerDpto handler = new HandlerDpto();
            File fichero = new File("departamentos.xml");
            parser.parse(fichero, handler);
            
            //Obtenemos el resultado del xml en forma de List<Departamento>
            List<Departamento> departamentos = handler.getDepartamentos();
            System.out.println("\n\n\nResultado:\n\n\n");
            System.out.println("DEPARTAMENTOS\n");
            for(Departamento dept: departamentos){
                System.out.println("Departamento Numero: " + dept.getNumero() + " de " + dept.getNombre());                 
                System.out.println("\tLocalidad: " + dept.getLocalidad() );
                System.out.println("\tEmpleados: " + dept.getEmpleados());                  
            }
            
        }catch (ParserConfigurationException | SAXException | IOException ex)
        {
            System.out.println("Error al parsear el documento");
        }
    }

}
