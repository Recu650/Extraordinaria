package com.mycompany.hoja_5;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.size;
import static com.mongodb.client.model.Indexes.ascending;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author ivan
 */
public class GestorBaseDatos
{
    private final MongoCollection<Document> coleccion;

    public GestorBaseDatos()
    {
        coleccion = Conexion.getInstance().getColeccion();
    }

    public void insertaComAut()
    {
        String codigo = Teclado.introString("Codigo de la ccaa:");
        String nombre = Teclado.introString("Nombre de la ccaa:");
        String abreviatura = Teclado.introString("Abreviatura de la ccaa:");
        String nombreCapi = Teclado.introString("Nombre de la capital:");
        double habitantesCapi = Teclado.introInt("Habitantes de la capital");
        double habitantes = Teclado.introInt("Habitantes de la ccaa:");
        double extension = Teclado.introInt("Extension de la ccaa:");

        Document doc = new Document("codigo", codigo)
                .append("nombre", nombre)
                .append("abreviatura", abreviatura)
                .append("capital", new Document("nombre", nombreCapi)
                        .append("habitantes", habitantesCapi))
                .append("habitantes", habitantes)
                .append("extension", extension);

        coleccion.insertOne(doc);
    }

    public void consultaComunidad()
    {
        String codigo = Teclado.introString("Codigo de la ccaa a buscar:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            System.out.println("Comunidad Autonoma: " + doc.getString("nombre"));
            System.out.println("Numero de Habitantes: " + doc.getDouble("habitantes"));
            System.out.println("Extension: " + doc.getDouble("extension"));
            System.out.println("Capital: " + ((Document) doc.get("capital")).getString("nombre"));
            System.out.println("");
            System.out.println("Documento en modo JASON");
            System.out.println("");
            System.out.println(doc.toJson());
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void asignaProvinciasComunidad()
    {
        String codigo = Teclado.introString("Codigo de la ccaa para meter provincias:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            boolean continuar = true;
            ArrayList<String> provincias = new ArrayList();

            System.out.println("Inserta las provincias de " + doc.getString("nombre"));

            while (continuar == true)
            {
                String provincia = Teclado.introString("Provincia: ");
                provincias.add(provincia);
                continuar = Teclado.introBoolean("Continuar: ");
            }

            coleccion.updateOne(eq("codigo", codigo), set("provincias", provincias));
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void addProvinciaComunidad()
    {
        String codigo = Teclado.introString("Codigo de la ccaa para agregar una provincia:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            System.out.println("Inserta las provincias de " + doc.getString("nombre"));
            String provincia = Teclado.introString("Nueva Provincia");
            UpdateResult resul = coleccion.updateOne(eq("codigo", codigo), addToSet("provincias", provincia));

            System.out.println(resul.getMatchedCount() + " Documentos filtrados");
            System.out.println(resul.getModifiedCount() + " Documentos modificados");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void modificaNombre()
    {
        String codigo = Teclado.introString("Codigo de la ccaa para cambiar nombre:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            String nombre = Teclado.introString("Nuevo nombre: ");
            UpdateResult resul = coleccion.updateOne(eq("codigo", codigo), set("nombre", nombre));

            System.out.println(resul.getMatchedCount() + " Documentos filtrados");
            System.out.println(resul.getModifiedCount() + " Documentos modificados");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void eliminaProvinciaComunidad()
    {
        String codigo = Teclado.introString("Codigo de la ccaa para eliminar provincia:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            String provincia = Teclado.introString("Provincia a eliminar: ");
            //UpdateResult resul = coleccion.updateOne(eq("codigo", codigo), push("provincia", provincia));

            //System.out.println(resul.getMatchedCount() + " Documentos filtrados");
            //System.out.println(resul.getModifiedCount() + " Documentos modificados");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void asignaCapitalComunidad()
    {
        String codigo = Teclado.introString("Codigo de la ccaa para agregar capital:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            if ((Document) doc.get("capital") == null)
            {
                String nombre = Teclado.introString("Nombre de la capital: ");
                double habitantes = Teclado.introInt("Habitantes de la capital: ");

                Document capital = new Document("nombre", nombre).append("habitantes", habitantes);
                coleccion.updateOne(eq("codigo", codigo), set("capital", capital));
            } else
                System.out.println("Error! Ya existe capital");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void asignaFechaEstatuto()
    {
        String codigo = Teclado.introString("Codigo de la ccaa para agregar fecha de estatuto:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            System.out.println("Fecha de entrada en vigor del estatuto autonomico:");
            int dia = Teclado.introInt("Dia: ");
            int mes = Teclado.introInt("Mes: ");
            int anio = Teclado.introInt("Anio: ");
            String fecha = dia + "/" + mes + "/" + anio;
            
            coleccion.updateOne(eq("codigo", codigo), set("fecha_estatuto", fecha));
        } else System.out.println("Error! Documento no encontrado");
    }

    public void eliminaComunidad()
    {
        String codigo = Teclado.introString("Codigo de la ccaa para borrar:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            System.out.println(doc.toJson());
            boolean eliminar = Teclado.introBoolean("Seguro que quieres eliminarlo?");
            if(eliminar == true){
                coleccion.deleteOne(eq("codigo", codigo));
                System.out.println("Comunidad autonoma eliminada");
            }else System.out.println("Bien pensado, no eliminamos");
        } else System.out.println("Error! Documento no encontrado");
    }
    
    public void comuMasCapi() {
        MongoCursor<Document> cursor = coleccion.find().projection(fields(include("nombre", "capital.nombre"),exclude("_id"))).iterator();
        while(cursor.hasNext()){
            Document doc = cursor.next();
            System.out.println(doc.toJson());
        }
    }
    
    public void comuHabitantesEntre() {
        double min = Teclado.introInt("Valor minimo de habitantes: ");
        double max = Teclado.introInt("Valor maximo de habitantes: ");
        
        MongoCursor<Document> cursor = coleccion.find(
//                and(gt("habitantes", min), lt("habitantes", max)) 
        )
                .sort(descending("habitantes")).iterator();
        
        while(cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println(doc.getString("nombre").toUpperCase() + " - " 
                    + doc.getDouble("habitantes") + " HABITANTES");
        }       
    }
    
    public void comuUniprovinciales () {
        MongoCursor<Document> cursor = coleccion.find(size("provincias", 1))
                .sort(fields(ascending("nombre"), descending("habitantes")))
                .iterator();
        
        while(cursor.hasNext()){
            Document doc = cursor.next();
            System.out.println(doc.getString("nombre"));
        }
    }
    
    public void capiConMasHabiQue() {
        double n = Teclado.introInt("Habitantes minimos de capital");
        MongoCursor<Document> cursor = coleccion.find(gt("capital.habitantes",n))
                .sort(ascending("capital.nombre"))
                .iterator();
        
        while(cursor.hasNext()){
           Document doc = cursor.next();
           System.out.println(((Document) doc.get("capital")).getString("nombre"));
        }
    }
    
    public void comuSinFechaEstatutos() {
        MongoCursor<Document> cursor = coleccion.find(
                or(exists("fecha_estatuto", false), eq("fecha_estatuto", null)))
                .iterator();
        
        while(cursor.hasNext()){
            Document doc = cursor.next();
            System.out.println(doc.getString("nombre"));
        }
    }
    
    public void provinciasDeUnaComu() {
        String codigo = Teclado.introString("Codigo de la ccaa para ver provincias:");
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null)
        {
            //Nu se seguir
        } else System.out.println("Error! Documento no encontrado");
    }
    
    public void crearFicheroJASON() {
        MongoCursor<Document> cursor = coleccion.find()
                .projection(fields(
                        include("nombre","capital","provincias","habitantes","extension"),
                        exclude("_id")))
                .iterator();
        
        while(cursor.hasNext()){
            Document doc = cursor.next();
            System.out.println(doc.toJson());
            //Falta terminar este metodo
        }
    }
}
