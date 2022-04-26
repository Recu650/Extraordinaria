package com.mycompany.hoja_1_ejercicio_1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author usuario
 */
public class GestorXML
{
    private Document documento;

    public Document crearXML()
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            documento = builder.newDocument();
            documento.setXmlVersion("1.0");
        } catch (ParserConfigurationException ex)
        {
            System.out.println(ex.toString());
        }

        Element elementFutbolistas = documento.createElement("Futbolistas");
        documento.appendChild(elementFutbolistas);

        return documento;
    }

    public boolean abrirXML(File fichero)
    {
        documento = null;
        try
        {
            //Se crea un objeto DocumentBuiderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            /*Indica que el modelo DOM no debe contemplar los comentarios que tenga el XML. 
            Es decir, cuando se convierte el XML al modelo DOM los comentarios serán ignorados.*/
            factory.setIgnoringComments(true);
            /*Ignora los espacios en blanco. Si no se hace esto entonces los espacios en blanco 
            aparecen como nodos.*/
            factory.setIgnoringElementContentWhitespace(true);

            /*Se crea un objeto DocumentBuilder para cargar en él la estructura de árbol DOM 
            a partir del XML seleccionado.*/
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Interpreta (parsea) el documento XML (file) y genera el DOM equivalente.
            documento = builder.parse(fichero);
            //Ahora documento apunta al árbol DOM listo para ser recorrido.
            return true;
        } catch (IOException | ParserConfigurationException | SAXException ex)
        {
            System.out.println("Error al abrir el XML");
            return false;
        }
    }
    
    private byte obtenerPuestoByte(String puesto){
        byte puestoByte = 0;
        switch(puesto){
            case "PORTERO":
                puestoByte = 1;
                break;
            case "DEFENSA":
                puestoByte = 2;
                break;
            case "CENTROCAMPISTA":
                puestoByte = 3;
                break;
            case "DELANTERO":
                puestoByte = 4;
                break;
        }
        return puestoByte;
    }
    
    private String obtenerPuestoString (Byte puesto){
        String puestoString = "";
        switch(puesto) {
            case 1:
                puestoString = "PORTERO";
                break;
            case 2:
                puestoString = "DEFENSA";
                break;
            case 3:
                puestoString = "CENTROCAMPISTA";
                break;
            case 4:
                puestoString = "DELANTERO";
                break;
        }
        return puestoString;
    }
    
    public boolean insertarFutbolista(Futbolista f){
        try{
            //Elemento Id
            Element elementNum = documento.createElement("num");
            Text nIdText = documento.createTextNode(String.valueOf(f.getId()));
            elementNum.appendChild(nIdText);
            
            //Elemeto Alias
            Element elementAlias = documento.createElement("alias");
            Text nAliasText = documento.createTextNode(f.getAlias());
            elementAlias.appendChild(nAliasText);
            
            //Elemeto Posicion
            Element elementPosicion = documento.createElement("posicion");
            Text nPosicionText = documento.createTextNode(obtenerPuestoString(f.getPuesto()));
            elementPosicion.appendChild(nPosicionText);
            
            //Elemento Futbolista
            Element nFutbolista = documento.createElement("Futbolista");
            nFutbolista.setAttribute("equipo", f.getCodeEquipo());
            
            //Añadir a Futbolista los elementos hijo
            elementoNum.appendChild(nIdText);
            nFutbolista.appendChild(nAliasText);
            nFutbolista.appendChild(nPosicionText);
            
            //Finalmente, se obtiene el primer nodo del documento y a él se le añade como hijo el 
            //nodo alumno que ya tiene colgando todos sus hijos y atributos creados antes.
            Node raiz = documento.getChildNodes().item(0);
            raiz.appendChild(nFutbolista);
            
            return true;
        }catch (DOMException ex){
            System.out.println("Error al añadir al DOM");
            return false;
        }
    }
    
    private void borrarEspaciosBlanco(Node node){
        NodeList hijos = node.getChildNodes();
        for(int i = 0; i < hijos.getLength(); i++){
            Node hijo = hijos.item(i);
            if(hijo.getNodeType() == Node.TEXT_NODE){
                hijo.setTextContent(hijo.getTextContent().trim());
            }
            borrarEspaciosBlanco(hijo);
        }
    }
    
    public boolean guardarDOMcomoFILE(String fichero){
        try{
            borrarEspaciosBlanco(documento);
            Source source = new DOMSource(documento);
            // A partir del fuente se crea el resultado en el archivo
            Result result = new StreamResult(Files.newBufferedWriter(Paths.get(fichero)));
            
            // Ahora hay que crear un objeto TransformerFactory para hacer la transformación
            // y convertir el documento en fichero
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            
            return true;
        } catch (IOException | TransformerException ex)
        {
            System.out.println("Error al guardar el fichero");
            return false;
        }
    }
}
