/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_2_ejercicio1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author usuario
 */
public class DepartamentoHandler extends DefaultHandler{
    private List<Departamento> departamentos;
    private StringBuilder valorElemento;
    private boolean esEmpleado;
    private Departamento departamento;
    private Empleado empleado;
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.departamentos = new ArrayList();
        System.out.println("Inicio de la lectura del documento");
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Fin de la lectura del documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); 
        switch(qName){
            case "departamento":
            {
                esEmpleado = false;
                
                String tipo = attributes.getValue("tipo");
                String tfno = attributes.getValue("telefono");
                departamento = new Departamento();
                departamento.setTipo(tipo);
                departamento.setTelefono(tfno);
            }
            case "empleado":
            {
                esEmpleado = true;
                float salario = Float.parseFloat(attributes.getValue("salario"));
                empleado = new Empleado();
                empleado.setSalario(salario);   
            }
            default: this.valorElemento = new StringBuilder();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); 
        if(this.valorElemento == null)
            this.valorElemento = new StringBuilder();
        else
            this.valorElemento.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); 
        switch(qName){
            case "codigo":
            {
                departamento.setCodigo(valorElemento.toString());
                departamentos.add(departamento); //No copia el objet, apunta a el
            }
            case "puesto":
            {
                empleado.setPuesto(valorElemento.toString());
            }
            case "nombre":
            {
                if (esEmpleado)
                    empleado.setNombre(valorElemento.toString());
                if(!esEmpleado)
                    departamento.setNombre(valorElemento.toString());
            }
            case "empleado": 
            {
                departamento.insertarEmpleado(empleado);
            }
            case "departamento":
            {
                departamentos.add(departamento); 
            }
        }
    }
    
    
}
