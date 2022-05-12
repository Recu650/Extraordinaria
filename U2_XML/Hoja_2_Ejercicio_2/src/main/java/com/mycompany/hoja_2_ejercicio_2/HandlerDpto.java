package com.mycompany.hoja_2_ejercicio_2;

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
    private boolean esEmpleado;
    private Departamento departamento;
    private Empleado empleado;
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.departamentos = new ArrayList();
        System.out.println("Inicio la lectura del documento");
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Fin de la lectura del documento");
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch(qName) {
            case "departamento":
                esEmpleado = false;
                
                String tipo = attributes.getValue("tipo");
                String tfno = attributes.getValue("telefono");
                this.departamento = new Departamento();
                this.departamento.setTipo(tipo);
                this.departamento.setTelefono(tfno);
                break;
            case "empleado":
                esEmpleado = true;
                int salario = Integer.valueOf(attributes.getValue("salario"));
                this.empleado = new Empleado();
                this.empleado.setSalario(salario);
                break;
            default: this.valorElemento = new StringBuilder();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(this.valorElemento == null) {
            this.valorElemento = new StringBuilder();
        }else{
            this.valorElemento.append(ch, start, length);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch(qName){
            case "codigo":
                this.departamento.setCodigo(this.valorElemento.toString());
                break;
            case "puesto":
                this.empleado.setPuesto(this.valorElemento.toString());
                break;
            case "nombre":
                if(esEmpleado == true) this.empleado.setNombre(this.valorElemento.toString());
                else this.departamento.setNombre(this.valorElemento.toString());
                break;
            case "empleado":
                this.departamento.insertarEmpleado(empleado);
                break;
            case "departamento":
                this.departamentos.add(departamento);
        }
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }
    
}
