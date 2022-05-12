package com.mycompany.hoja_5_ejercicio_1;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.pull;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author usuario
 */
public class GestorBaseDatos {
    private final MongoCollection<Document> coleccion;

    public GestorBaseDatos() {
        this.coleccion = Conexion.getInstance().getColeccion();
    }

    public void insertarCCAA(ComunidadAutonoma ccaa) {
        Document doc = new Document("codigo", ccaa.getCodigo())
                .append("nombre", ccaa.getNombre())
                .append("abreviatura", ccaa.getAbreviatura())
                .append("capital", new Document("nombre", ccaa.getNombreCapi())
                        .append("habitantes", ccaa.getHabitantesCapi()))
                .append("habitantes", ccaa.getHabitantes())
                .append("extension", ccaa.getExtension());
        this.coleccion.insertOne(doc);
    }

    public ComunidadAutonoma consultaCCAA(String codigo) {
        ComunidadAutonoma ccaa = new ComunidadAutonoma();
        Document doc = this.coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            ccaa.setCodigo(codigo);
            ccaa.setNombre(doc.getString("nombre"));
            ccaa.setAbreviatura(doc.getString("abreviatura"));
            ccaa.setHabitantes(doc.getDouble("habitantes"));
            ccaa.setExtension(doc.getDouble("extension"));
            ccaa.setNombreCapi(((Document) doc.get("capital")).getString("nombre"));
            ccaa.setHabitantesCapi(((Document) doc.get("capital")).getDouble("habitantes"));

//            System.out.println("//TEXTO EJEMPLO ===============================================");
//            System.out.println("//Comunidad Autonoma: " + doc.getString("nombre"));
//            System.out.println("//Numero de Habitantes: " + doc.getDouble("habitantes"));
//            System.out.println("//Extension: " + doc.getDouble("extension"));
//            System.out.println("//Capital: "+((Document) doc.get("capital")).getString("nombre"));
//            System.out.println("//");
//            System.out.println("//Documento en modo JASON");
//            System.out.println("//");
//            System.out.println("//" + doc.toJson());
//            System.out.println("//=============================================================");
        } else
            ccaa = null;
        return ccaa;
    }

    public void asignarProvincias(String codigo) {
        Document doc = this.coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            System.out.println("Insertando las provincias de " + doc.getString("nombre"));
            List<String> provincias = this.rellenarProvincias();
            this.coleccion.updateOne(eq("codigo", codigo), set("provincias", provincias));
        } else
            System.out.println("Error! Documento no encontrado");
    }

    private List<String> rellenarProvincias() {
        List<String> provincias = new ArrayList();
        boolean continuar = true;

        do {
            provincias.add(Teclado.introString("Provincia: "));
            continuar = Teclado.introBoolean("Insertar otra provincia? ");
        } while (continuar == true);
        return provincias;
    }

    public void addProvincia(String codigo, String provincia) {
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            UpdateResult resul
                    = coleccion.updateOne(eq("codigo", codigo), addToSet("provincias", provincia));

            System.out.println(resul.getMatchedCount() + " Documentos filtrados");
            System.out.println(resul.getModifiedCount() + " Documentos modificados");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void modificarNombre(String codigo, String nombre) {
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            UpdateResult resul = coleccion.updateOne(eq("codigo", codigo), set("nombre", nombre));

            System.out.println(resul.getMatchedCount() + " Documentos filtrados");
            System.out.println(resul.getModifiedCount() + " Documentos modificados");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void eliminarProvincia(String codigo, String provincia) {
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            UpdateResult resul
                    = coleccion.updateOne(eq("codigo", codigo), pull("provincias", provincia));

            System.out.println(resul.getMatchedCount() + " Documentos filtrados");
            System.out.println(resul.getModifiedCount() + " Documentos modificados");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void asignarCapital(String codigo, String nombreCapi, double habitantesCapi) {
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            if ((Document) doc.get("capital") == null) {
                Document capital = new Document("nombre", nombreCapi)
                        .append("habitantes", habitantesCapi);
                UpdateResult resul
                        = coleccion.updateOne(eq("codigo", codigo), set("capital", capital));

                System.out.println(resul.getMatchedCount() + " Documentos filtrados");
                System.out.println(resul.getModifiedCount() + " Documentos modificados");
            } else
                System.out.println("Error! Ya existe capital");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void asignarFechaEstatuto(String codigo, String fecha) {
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            UpdateResult resul
                    = coleccion.updateOne(eq("codigo", codigo), set("fecha_estatuto", fecha));

            System.out.println(resul.getMatchedCount() + " Documentos filtrados");
            System.out.println(resul.getModifiedCount() + " Documentos modificados");
        } else
            System.out.println("Error! Documento no encontrado");
    }

    public void eliminarComunidad(String codigo) {
        Document doc = coleccion.find(eq("codigo", codigo)).first();
        if (doc != null) {
            System.out.println(doc.toJson());
            boolean eliminar = Teclado.introBoolean("Seguro que quieres eliminarlo?");
            if (eliminar == true) {
                DeleteResult resul = coleccion.deleteOne(eq("codigo", codigo));
                System.out.println(resul.getDeletedCount() + " Documentos eliminados");
            } else
                System.out.println("Bien pensado, no eliminamos");
        } else
            System.out.println("Error! Documento no encontrado");
    }
    
    public void comuMasCapi(){
        MongoCursor<Document> cursor =
                this.coleccion.find().projection(fields(include("nombre", "capital.nombre"),
                        exclude("_id"))).iterator();
        while(cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println(doc.toJson());
        }
    }
    
    public void comuPorHabitantes(double min, double max){
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
}
