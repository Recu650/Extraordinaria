package com.mycompany.hoja_7;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Indexes.ascending;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author usuario
 */
public class GestorBaseDatos
{
    private final MongoCollection<Document> coleccionAlumnos;
    private final MongoCollection<Document> coleccionCursos;

    public GestorBaseDatos()
    {
        this.coleccionAlumnos = Conexion.getInstance().getColeccionAlumnos();
        this.coleccionCursos = Conexion.getInstance().getColeccionCursos();
    }

    /*Se pide por teclado el id de un curso y, si existe, se va mostrando el nombre y apellidos de cada uno
    de sus alumnos y se pide que se dé a cada uno una nota. La nota introducida para cada alumno se
    añade al array de notas.*/
    public void aniadirNotas()
    {
        String id = Teclado.introString("Introduce id del curso");
        Document docCurso = this.coleccionCursos.find(eq("_id", id)).first();

        if (docCurso != null)
        {
            MongoCursor<Document> cursor = this.coleccionAlumnos.find(eq("curso", id)).iterator();

            while (cursor.hasNext())
            {
                Document docAlumno = cursor.next();

                System.out.println(docAlumno.getString("nombre") + " " + docAlumno.getString("apellidos"));
                int nota = Teclado.introInt("Introduce Nota:");
                String idAlumno = docAlumno.getString("_id");
                this.coleccionAlumnos.updateOne(eq("_id", idAlumno), push("notas", nota));
            }
        } else
            System.out.println("Curso no encontrado.");
    }

    /*Se pide por teclado el id de un curso y, si existe, se piden los datos de un tema por teclado y se
    añade un documento con los datos del tema (título y horas) al array temas*/
    public void aniadirTema()
    {
        String id = Teclado.introString("Introduce id del curso");
        Document docCurso = this.coleccionCursos.find(eq("_id", id)).first();

        if (docCurso != null)
        {
            String titulo = Teclado.introString("Titulo del Tema");
            int horas = Teclado.introInt("Numero de Horas del Tema");

            this.coleccionCursos.updateOne(eq("id", id), addToSet("temas",
                    new Document("titulo", titulo).append("horas", horas)));
        } else
            System.out.println("Curso no encontrado");
    }

    /*Se pide por teclado el id de un curso y, si existe, se escribe el título del curso y las horas de
    duración. A continuación, se escribe un listado ordenado por apellidos, nombre de los alumnos del
    curso con valor en nota_media mayor o igual que 5 (apellidos, nombre, nota_media).*/
    public void obtenerAlumnosCurso()
    {
        String id = Teclado.introString("Introducir id del curso");
        Document docCurso = this.coleccionCursos.find(eq("_id", id)).first();
        if (docCurso != null)
        {
            System.out.println(docCurso.getString("titulo") + " " + docCurso.getInteger("horas"));

            MongoCursor<Document> cursor = this.coleccionAlumnos.find(
                    and(eq("curso", id), gte("nota_media", 5))).sort(ascending("apellidos", "nombre")).iterator();

            while (cursor.hasNext())
            {
                Document docAlumno = cursor.next();
                System.out.printf("%-30s-16s%4.2f\n",
                        docAlumno.getString("apellidos"),
                        docAlumno.getString("nombre"),
                        docCurso.getDouble("nota_media"));
            }
        } else
            System.out.println("Curso no encontrado");
    }

    /*Se escriben en pantalla los títulos de los cursos y cuántos alumnos hay en cada uno de esos cursos.
    
    Debes realizarlo consultando primero los cursos existentes y, para cada curso, obteniendo cuántos
    alumnos hay. Aplicando el método countDocuments a consultar los alumnos de un curso.
    
    Hay otras alternativas como usar el método aggregate que podría obtener cuantos alumnos hay para
    cada código de curso. Por cada resultado habría que obtener el título del curso. De esta forma no
    saldrían los cursos en los que no hay alumnos matriculados. Esto es un ejemplo de cómo se
    obtendría cada id de curso el número de alumnos matriculados. 
    
    cursor = alumnos.aggregate(
        Arrays.asList(
            Aggregates.group("$curso", Accumulators.sum("num", 1))
        )).iterator(); */
    
    //OPCION_1_FASIL
    public void numeroAlumnosCurso()
    {
        MongoCursor<Document> cursor = this.coleccionCursos.find().iterator();

        while (cursor.hasNext())
        {
            Document docCurso = cursor.next();
            int alumnos = (int) this.coleccionAlumnos.countDocuments(eq("curso", docCurso.getString("id")));
            System.out.println("Curso: " + docCurso.getString("nombre") + ", alumnos: " + alumnos);
        }
    }

    //OPCION_2_AGGREGATE
    //Ni puta idea de lo que acaba de pasar...
    public void numeroAlumnosCursoAggregates()
    {
        MongoCursor<Document> cursor = this.coleccionAlumnos.aggregate(
                Arrays.asList(Aggregates.group("$curso", Accumulators.sum("num", 1)))
        ).iterator();

        while (cursor.hasNext())
        {
            Document doc = (Document) cursor.next();
            String idCurso = doc.getString("_id");
            Document curso = this.coleccionCursos.find(eq("_id", idCurso)).first();
            if (curso != null)
            {
                System.out.println(curso.getString("titulo") + " --- " + doc.getInteger("num"));
            }
        }
    }

    /*Se pide por teclado el id de un alumno y, si existe, se calculala nota media del array
    de notas y se modifica con esa nota calculada el valor del atributo nota_media.
    
    Debes extraer el contenido del atributo notas en un ArrayList.*/

    public void modificarNotaMedia()
    {
        String id = Teclado.introString("Introduce id del alumno");
        double media = 0.0;

        Document docAlumno = this.coleccionAlumnos.find(eq("_id", id)).first();
        if (docAlumno != null)
        {
            List<Double> notas = (List<Double>) docAlumno.get("notas");
            Double suma = 0.0;
            for (Double n : notas)
            {
                suma = suma = n;
            }
            if (notas.size() > 0)
            {
                media = suma / notas.size();
            }
            DecimalFormat formato = new DecimalFormat("##.00");
            String mediaString = formato.format(media);
            this.coleccionAlumnos.updateOne(eq("_id", id), set("nota_media", mediaString));
        } else
            System.out.println("Alumno no encontrado");
    }

    /*Se pide por teclado el id de un curso y, si existe, se calculan las horas del curso 
    como suma de las horas de los temas. Si no hay temas cargados, las horas serán cero. 
    Con el valor calculado de horas, se modificará el valor del atributo horas del curso.*/
    public void modificarHorasCurso()
    {
        String id = Teclado.introString("Introduce id del curso");
        Document docCurso = this.coleccionCursos.find(eq("_id", id)).first();

        if (docCurso != null)
        {
            int horas = 0;
            if (docCurso.containsKey("temas"))
            {
                List<Document> listaTemas = (List<Document>) docCurso.get("temas");

                for (Document tema : listaTemas)
                {
                    horas = horas + tema.getInteger("horas");
                }

                coleccionCursos.updateOne(eq("_id", docCurso.getString("_id")), set("horas", horas));
            } else
                coleccionCursos.updateOne(eq("_id", docCurso.getString("_id")), set("horas", horas));
        } else
            System.out.println("Curso no encontrado");
    }

    /*Se pide por teclado el id de un alumno y, si existe, se escribe su nombre y apellidos
    y el título delcurso en el que está matriculado.*/
    public void datosAlumno()
    {
        String id = Teclado.introString("Introduce id del alumno");
        Document docAlumno = this.coleccionAlumnos.find(eq("_id", id)).first();

        if (docAlumno != null)
        {
            String curso = this.coleccionCursos.find(eq("_id", docAlumno.getString("curso")))
                    .first().getString("titulo");
            System.out.println(docAlumno.getString("nombre") + " " + docAlumno.getString("apellidos")
                    + " " + curso);
        } else
            System.out.println("Alumno no encontrado");
    }

    /*Se pide por teclado el id de un curso y, si existe, se obtiene y escribe la nota media
    en el curso, es decir, la nota media de las notas medias de los alumnos del curso.*/
    public void notaMedia()
    {
        String id = Teclado.introString("Introduce id del curso");
        Document docCurso = this.coleccionCursos.find(eq("_id", id)).first();

        if (docCurso != null)
        {
            MongoCursor<Document> cursor = this.coleccionAlumnos.find(eq("curso", docCurso.getString("_id"))).iterator();
            double contador = 0;
            double suma = 0;

            while (cursor.hasNext())
            {
                Document docAlumno = cursor.next();
                Double nota = (Double) docAlumno.get("nota_media");
                suma = suma + nota;
                contador++;
            }
            double media = suma / contador;
            this.coleccionCursos.updateOne(eq("_id", docCurso.getString("_id")), set("nota_media", media));
        } else
            System.out.println("Curso no encontrado");
    }
}
