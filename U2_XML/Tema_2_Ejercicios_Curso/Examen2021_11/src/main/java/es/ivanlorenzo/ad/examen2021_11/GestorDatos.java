package es.ivanlorenzo.ad.examen2021_11;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ivan
 */
public class GestorDatos
{
    private Set<Inmobiliaria> inmobiliarias;

    public GestorDatos()
    {
        this.inmobiliarias = new LinkedHashSet();
    }

    public void modificarPrecios(int identificadorInmobiliaria)
    {
        try{
            Path ruta = Paths.get("inmuebles.csv");
            Path rutaAuxiliar = Paths.get("inmuebles-aux.csv");
            BufferedWriter escritor = Files.newBufferedWriter(rutaAuxiliar);
            
            BufferedReader lector = Files.newBufferedReader(ruta);
            
            String linea;
            while((linea = lector.readLine()) != null){
                String[] dato = linea.split(",");
                if(Integer.parseInt(dato[3]) == identificadorInmobiliaria){
                    double nuevoPrecio = Double.parseDouble(dato[1]*1.1);
                    dato[1] = Double.toString(nuevoPrecio);
                }
                String aEscribir = dato[0] + "," + dato[1] + "," + dato[2] + "," + dato[3];
                escritor.write(aEscribir);
                escritor.newLine();
            }
            Files.move(rutaAuxiliar, ruta, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally{
            //cerrar flujos
        }

    }

    public void leerInmobiliarias(Path fichero)
    {
        DataInputStream lector = null;
        try{
            lector =  new DataInputStream(new FileInputStream(fichero.toFile()));
            
            while(true){
                int identificador = lector.readInt();
                String nombre = lector.readUTF();
                int numeroEmpleados = lector.readInt();
                Inmobiliaria inmobiliaria = new Inmobiliaria(identificador,nombre,numeroEmpleados);
                this.inmobiliarias.add(inmobiliaria);
            }
        } catch (FileNotFoundException ex)
        {
            
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }finally{
            //cerrar flujos
        }
    }

    public void leerInmuebles(Path fichero)
    {
        try{
            BufferedReader lector = Files.newBufferedReader(fichero);
            String linea;
            while(linea = lector.readLine() != null){
                String[] dato =linea.split(",");
                Inmueble inmueble = new Inmueble(dato[0],
                        Double.parseDouble(dato[1]),dato[2]);
                Inmobiliaria inmobiliaria = buscarPorId(Integer.parseInt(dato[4]));
                inmobiliaria.añadirInmueble(inmueble);
            }
            List<String[]> datos =Files.lines(fichero)
                    .map(l -> l.split(",")).toList();
            datos.forEach(dato ->
            {
                Inmueble inmueble = new Inmueble(dato[0],
                        Double.parseDouble(dato[1]), dato[2]);
                Inmobiliaria inmobiliaria =  buscarPorId(Integer.parseInt(dato[3]));
                inmobiliaria.añadirInmueble(inmueble);
            });
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }

    }

    private Inmobiliaria buscarPorId(int id)
    {
//        for(Inmobiliaria inmobiliaria : this.inmobiliarias){
//            if(inmobiliaria.getIdentificador() == id)
//                return inmobiliaria;
//        }
//        return null;
        
        Optional<Inmobiliaria> inmo = this.inmobiliarias.stream()
                .filter(i -> i.getIdentificador() == id)
                .findFirst();
        return (inmo.isPresent()) ? inmo.get() : null; //Operador terciario (if else)
    }

    public void procesarDatosXML(Path fichero)
    {
        
    }

    public void añadirEtiquetaValoracion(Path fichero)
    {
        
    }

    public void guardarJSON(Path fichero)
    {
        try ( BufferedWriter escritor = Files.newBufferedWriter(fichero))
        {
            //Creamos un objeto Gson a partir de  GsonBuilder
            //Llamando al método setPrettyPrinting lo pinta bien formateado
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(getInmobiliarias(), escritor);
        }
        catch (IOException ex)
        {
            System.err.println("Error al guardar el JSON " + ex.getMessage());
        }
    }
    
    public String mostrarDatosEncolumnados()
    {
//        String cadena = "";
//        for(Inmobiliaria inmobiliaria: this.inmobiliarias){
//            cadena += inmobiliaria + "\n";
//        }
//        return cadena;
        
        return inmobiliarias.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining("\n"));
    }

    public String mostrarDatosOrdenadosEmpleados()
    {
        return inmobiliarias.stream()
                .sorted((i1, i2) -> i2.getNumeroEmpleados() - i1.getNumeroEmpleados())
                .map(i -> i.toString())
                .collect(Collectors.joining("\n"));
    }
    
    
    public String mostrarInmobiliariasOrdenadasPorMediaPrecios()
    {
        return inmobiliarias.stream()
                .sorted((i1, i2) -> (int)(i2.getMediaPrecios() - i1.getMediaPrecios()))
                .map(i -> i.getNombre() + " (" + i.getMediaPrecios()/1000 + "K €")
                .collect(Collectors.joining("\n"));
    }

    public Set<Inmobiliaria> getInmobiliarias()
    {
        return inmobiliarias;
    }

    private void borrarEspaciosBlanco(Node node)
    {
        NodeList hijos = node.getChildNodes();
        for (int i = 0; i < hijos.getLength(); ++i)
        {
            Node hijo = hijos.item(i);
            if (hijo.getNodeType() == Node.TEXT_NODE)
            {
                hijo.setTextContent(hijo.getTextContent().trim());
            }
            borrarEspaciosBlanco(hijo);
        }
    }

}
