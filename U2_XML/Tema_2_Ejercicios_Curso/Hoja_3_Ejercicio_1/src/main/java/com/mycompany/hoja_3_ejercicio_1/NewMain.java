/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_3_ejercicio_1;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
        deserializar(new File("universidad.xml")) //Como sacar los empleados???
                .getDepartamento()
                .stream()
                .forEach(d -> System.out.println(d.getCodigo() + " " + d.getNombre() + " " + d.getTelefono() + " " + d.getTipo() + "\n"));
    }
    private static Universidad deserializar(File fXML)
    {
        //Objeto de la clase binding en el que se deserializará el XML
        Universidad universidad = null;
        try
        {
            //Contexto para class Clientes
            JAXBContext contextUniversidad = JAXBContext.newInstance(Universidad.class);
            //Obtener un deserializador para contexto
            Unmarshaller deserializador = contextUniversidad.createUnmarshaller();
            //Asignar la clase gestora de eventos al deserializador
            //No es obligatorio usarlo, aunque si recomendable
            deserializador.setEventHandler(new GestorEventos());
            //Si quisiéramos usar un fichero esquema XSD que hubiéramos recogido por teclado, 
            //después de obtener el deserializador, le tendríamos que haber asignado el esquema.
//            deserializador.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
//                .newSchema(new File("clientes.xsd")));
            //Deserializar en objeto clientes 
            universidad = (Universidad) deserializador.unmarshal(fXML);
        }
        catch (JAXBException /*| SAXException */ex)
        {
            System.err.println(ex.getMessage());
        }
        return universidad;
    }
    
}
