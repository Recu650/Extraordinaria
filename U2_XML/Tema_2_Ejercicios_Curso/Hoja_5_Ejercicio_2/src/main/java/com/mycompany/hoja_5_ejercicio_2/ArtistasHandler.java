package com.mycompany.hoja_5_ejercicio_2;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author usuario
 */
public class ArtistasHandler extends DefaultHandler
{
    private List<Artista> artistas;
    private StringBuilder valorElemento;
    
    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
        this.artistas = new ArrayList();
        System.out.println("Inicio Lectura XML");
    }
    
    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
        System.out.println("Fin Lectura XML");
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        super.startElement(uri, localName, qName, attributes);
        switch (qName)
        {
            case "artist":
            {
                this.artistas.add(new Artista());
            }
            case "name", "image":
            {
                this.valorElemento = new StringBuilder();
            }
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        super.characters(ch, start, length);
        if (this.valorElemento == null)
        {
            this.valorElemento = new StringBuilder();
        } else
        {
            this.valorElemento.append(ch, start, length);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        super.endElement(uri, localName, qName);
        switch (qName)
        {
            case "name":
            {
                this.artistas.get(this.artistas.size() - 1).setNombre(this.valorElemento.toString());
            }
            case "image":
            {
                this.artistas.get(this.artistas.size() - 1).setFoto(this.valorElemento.toString());
            }
        }
    }
    
    public List<Artista> getArtistas()
    {
        return artistas;
    }
    
}
