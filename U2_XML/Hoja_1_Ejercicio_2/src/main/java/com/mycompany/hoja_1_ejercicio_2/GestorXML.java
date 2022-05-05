package com.mycompany.hoja_1_ejercicio_2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
public class GestorXML {
    private Document documento;

    public Document crearXML() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            documento = builder.newDocument();
            documento.setXmlVersion("1.0");
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.toString());
        }

        Element elementFutbolistas = documento.createElement("Futbolistas");
        documento.appendChild(elementFutbolistas);

        return documento;
    }

    public boolean abrirXML(File fichero) {
        documento = null;
        try {
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
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.out.println("Error al abrir el XML " + ex.toString());
            return false;
        }
    }

    private byte obtenerPuestoByte(String puesto) {
        byte puestoByte = 0;
        switch (puesto) {
            case "PORTERO":
                puestoByte = 1;
                break;
            case "DEFENSA":
                puestoByte = 2;
                break;
            case "CENTROCAMP":
                puestoByte = 3;
                break;
            case "DELANTERO":
                puestoByte = 4;
                break;
        }
        return puestoByte;
    }

    private String obtenerPuestoString(Byte puesto) {
        String puestoString = "";
        switch (puesto) {
            case 1:
                puestoString = "PORTERO";
                break;
            case 2:
                puestoString = "DEFENSA";
                break;
            case 3:
                puestoString = "CENTROCAMP";
                break;
            case 4:
                puestoString = "DELANTERO";
                break;
        }
        return puestoString;
    }

    public boolean insertarFutbolista(Futbolista f) {
        try {
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
            nFutbolista.appendChild(elementNum);
            nFutbolista.appendChild(elementAlias);
            nFutbolista.appendChild(elementPosicion);

            //Finalmente, se obtiene el primer nodo del documento y a él se le añade como hijo el 
            //nodo alumno que ya tiene colgando todos sus hijos y atributos creados antes.
            Node raiz = documento.getChildNodes().item(0);
            raiz.appendChild(nFutbolista);
            
            this.guardarDOMcomoFILE("futbolistas.xml");

            return true;
        } catch (DOMException ex) {
            System.out.println("Error al añadir al DOM");
            return false;
        }
    }

    private void borrarEspaciosBlanco(Node node) {
        NodeList hijos = node.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node hijo = hijos.item(i);
            if (hijo.getNodeType() == Node.TEXT_NODE) {
                hijo.setTextContent(hijo.getTextContent().trim());
            }
            borrarEspaciosBlanco(hijo);
        }
    }

    public boolean guardarDOMcomoFILE(String fichero) {
        try {
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
        } catch (IOException | TransformerException ex) {
            System.out.println("Error al guardar el fichero");
            return false;
        }
    }

    public List<Futbolista> mostrarDOM() {
        //En principio returneaba String, pero es mas util si returnea una List<Futbolista>
        //Así que imprimo la salida String para ver como se hace pero en verdad la utilidad
        //Es obtener la List, se puede comentar la linea del sout.

        List<Futbolista> futbolistas = new ArrayList();

        String cadena = "";
        //Obtener el primer nodo del DOM
        Node raiz = documento.getFirstChild();

        //Obtener una lista de nodos hijos de la raiz
        NodeList nodosFutbolista = raiz.getChildNodes();

        cadena = cadena + "\n LISTADO DE FUTBOLISTAS \n";

        //Procesar los nodos hijo
        for (int i = 0; i < nodosFutbolista.getLength(); i++) {
            Node nodoFutbolista = nodosFutbolista.item(i);
            //Al ejecutar paso a paso este código, se observa 
            //como los nodos que encuentra son
            //de tipo 1 (ELEMENT_NODE) y tipo 3 (TEXT_NODE).
            if (nodoFutbolista.getNodeType() == Node.ELEMENT_NODE) {
                //Es un nodo Futbolista que hay que procesar si es de tipo Elemento

                //HIJOS DE FUTBOLISTA
                String[] datos_nodo = procesarFutbolista(nodoFutbolista);
                cadena = cadena + "Num: " + datos_nodo[0] + "\tAlias: " + datos_nodo[1]
                        + "\tPosicion: " + datos_nodo[2];

                Futbolista futbolista = new Futbolista();
                futbolista.setId(Short.valueOf(datos_nodo[0]));
                futbolista.setAlias(datos_nodo[1]);
                futbolista.setPuesto(obtenerPuestoByte(datos_nodo[2]));

                //ATRIBUTO DE FUTBOLISTA
                List<String> atributos = procesarAtributos(nodoFutbolista);
                cadena = cadena + "\t" + atributos.get(0) + ": " + atributos.get(1) + "\n";

                futbolista.setCodeEquipo(atributos.get(1));
                futbolistas.add(futbolista);
            }
        }
        //System.out.println(cadena);
        return futbolistas;
    }

    public Futbolista mostrarDOMfiltroNumero(Short num) {
        Futbolista futbolista = null;
        //Obtener el primer nodo del DOM
        Node raiz = documento.getFirstChild();

        //Obtener una lista de nodos hijos de la raiz
        NodeList nodosFutbolista = raiz.getChildNodes();

        //Procesar los nodos hijo
        for (int i = 0; i < nodosFutbolista.getLength(); i++) {
            Node nodoFutbolista = nodosFutbolista.item(i);
            //Al ejecutar paso a paso este código, se observa 
            //como los nodos que encuentra son
            //de tipo 1 (ELEMENT_NODE) y tipo 3 (TEXT_NODE).
            if (nodoFutbolista.getNodeType() == Node.ELEMENT_NODE) {
                //Es un nodo Futbolista que hay que procesar si es de tipo Elemento

                //HIJOS DE FUTBOLISTA
                String[] datos_nodo = procesarFutbolista(nodoFutbolista);

                if (Short.valueOf(datos_nodo[0]) == num) {
                    futbolista = new Futbolista();
                    futbolista.setId(Short.valueOf(datos_nodo[0]));
                    futbolista.setAlias(datos_nodo[1]);
                    futbolista.setPuesto(obtenerPuestoByte(datos_nodo[2]));

                    //ATRIBUTO DE FUTBOLISTA
                    List<String> atributos = procesarAtributos(nodoFutbolista);
                    futbolista.setCodeEquipo(atributos.get(1));
                }
            }
        }
        return futbolista;
    }

    private List<String> procesarAtributos(Node nodo) {
        List<String> atributos = new ArrayList();

        //Para saber cuantos atributos tiene el nodo
        int numAtributos = nodo.getAttributes().getLength();
        for (int i = 0; i < numAtributos; i++) {
            //De la lista de atributos añade al ArrayList 
            //el nombre de cada atributo y su valor
            //Cada vez se añaden dos valores al ArrayList
            //en las posiciones pares habrá nombres de atributos 
            //y en las impares valores de atributos
            atributos.add(nodo.getAttributes().item(i).getNodeName());
            atributos.add(nodo.getAttributes().item(i).getNodeValue());
        }
        return atributos;
    }

    private String[] procesarFutbolista(Node nodo) {
        String[] datos = new String[3]; //Numero de hijos que tiene futbolista.
        int contador = 0;

        //Obtener los hijos del Futbolista.
        NodeList nodosDeFutbolista = nodo.getChildNodes();
        for (int i = 0; i < nodosDeFutbolista.getLength(); i++) {
            Node nodoHijo = nodosDeFutbolista.item(i);
            /*Se debe comprobar el tipo de nodo que se quiere tratar por que es posible que haya
            nodos tipo TEXT que contengan retornos de carro al cambiar de línea en el XML.
            En este ejemplo, cuando detecta un nodo que no es tipo ELEMENT_NODE es porque es tipo 
            TEXT y contiene los saltos de línea o espacios en blanco que se incluyen en el 
            fichero de texto al crear el XML.*/
            if (nodoHijo.getNodeType() == Node.ELEMENT_NODE) {
                //IMPORTANTE: para obtener el texto con el título accede al nodo TEXT 
                //hijo de nodoHijo y saca su valor.
                datos[contador] = nodoHijo.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }
        return datos;
    }

    public void ModificarFutbolista(Futbolista nuevoFutbolista) {
        //Saco los futbolistas a un List y sustitullo el viejo por el nuevo
        List<Futbolista> futbolistas = this.mostrarDOM();
        int index = -1;
        int contador = 0;
        for (Futbolista futbolista : futbolistas) {
            if (futbolista.getId() == nuevoFutbolista.getId()) {
                index = contador;
            }
            contador++;
        }
        futbolistas.set(index, nuevoFutbolista);

        //Hago un nuevo XML, lo relleno y lo guardo con 
        //el mismo nombre para sobrescribirlo
        this.crearXML();
        for (Futbolista futbolista : futbolistas) {
            this.insertarFutbolista(futbolista);
        }
        this.guardarDOMcomoFILE("futbolistas.xml");
    }
    
    public void EliminarFutbolista(Futbolista eliminarFutbolista){
        //Saco los futbolistas a un List y elimino 
        List<Futbolista> futbolistas = this.mostrarDOM();
        int index = -1;
        int contador = 0;
        for (Futbolista futbolista : futbolistas) {
            if (futbolista.getId() == eliminarFutbolista.getId()) {
                index = contador;
            }
            contador++;
        }
        futbolistas.remove(index);

        //Hago un nuevo XML, lo relleno y lo guardo con 
        //el mismo nombre para sobrescribirlo
        this.crearXML();
        for (Futbolista futbolista : futbolistas) {
            this.insertarFutbolista(futbolista);
        }
        this.guardarDOMcomoFILE("futbolistas.xml");
    }
}
