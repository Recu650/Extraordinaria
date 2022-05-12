package com.mycompany.hoja_2_ejercicio_1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author usuario
 */
public class HandlerDpto extends DefaultHandler {
    private List<Departamento> departamentos;
    private StringBuilder valorElemento;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.departamentos = new ArrayList();
        System.out.println("//Se ha iniciado la lectura del documento");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("//finaliza la lectura del documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //Evaluamos todas las posibles etiquetas de inicio
        switch (qName) {
            case "departamentos":
                System.out.println("//Nodo departamentos");
                break;
            case "departamento":
                System.out.println("//Nodo departamento");
                System.out.println("//Aqui estan los datos del departamento " + (departamentos.size() + 1));
                //Creo un objeto dpto, donde almacenaré los datos.
                departamentos.add(new Departamento());
                break;
            default: this.valorElemento = new StringBuilder();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        super.characters(ch, start, length);
        //Esto puede ser un texto de un valor de una marca
        //o incluso un espacio o un ENTER que haya entre dos marcas.
        //Solo se obtiene el texto. No puede saberse a que corresponde
        
        //Usamos StringBuffer para extraerlos como String del array chars
        if(this.valorElemento == null) this.valorElemento = new StringBuilder();
        else this.valorElemento.append(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException { 
        super.endElement(uri, localName, qName);
        //Evaluamos las marcas de fin que necesitamos.
        switch(qName){
            case "numero":
                System.out.println("\t//Numero: " + this.valorElemento.toString());
                this.departamentos.get(this.departamentos.size() - 1).setNumero(Integer.valueOf(this.valorElemento.toString()));
                break;
            case "nombre":
                System.out.println("\t//Nombre: " + this.valorElemento.toString());
                this.departamentos.get(this.departamentos.size() - 1).setNombre(this.valorElemento.toString());
                break;
            case "localidad":
                System.out.println("\t//Localidad: " + this.valorElemento.toString());
                this.departamentos.get(this.departamentos.size() - 1).setLocalidad(this.valorElemento.toString());
                break;
            case "empleados":
                System.out.println("\t//Empleados: " + this.valorElemento.toString());
                this.departamentos.get(this.departamentos.size() - 1).setEmpleados(Integer.valueOf(this.valorElemento.toString()));
                break;
        }
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }
    
    
}
