package com.mycompany.hoja_2_ejercicio_1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author usuario
 */
public class DepartamentosHandler extends DefaultHandler{
    private List<Departamento> departamentos;
    private StringBuilder valorElemento;

    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument(); 
        this.departamentos = new ArrayList();
        System.out.println("Se ha iniciado la lectura del doc");
    }

    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
        System.out.println("Finalizada la lectura del doc");
    }
    
    
}
