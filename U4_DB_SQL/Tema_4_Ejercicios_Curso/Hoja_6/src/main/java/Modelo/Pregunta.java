package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */

public class Pregunta
{
    //ATRIBUTOS------------------------------------------------------------------------------------
    private int id;
    private String enunciado;
    private String categoria;
    private int nivel;
    private String foto;
    private int veces_formulada;
    private int veces_acertada;
    private List<Respuesta> respuestas;
    //CONSTRUCTOR----------------------------------------------------------------------------------

    public Pregunta()
    {
        this.respuestas = new ArrayList();
    }
    //GETTER_&_SETTER-----------------------------------------------------------------------------

    public List<Respuesta> getRespuestas()
    {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas)
    {
        this.respuestas = respuestas;
    }

    public void addRespuesta(Respuesta respuesta)
    {
        this.respuestas.add(respuesta);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEnunciado()
    {
        return enunciado;
    }

    public void setEnunciado(String enunciado)
    {
        this.enunciado = enunciado;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public int getNivel()
    {
        return nivel;
    }

    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public int getVeces_formulada()
    {
        return veces_formulada;
    }

    public void setVeces_formulada(int veces_formulada)
    {
        this.veces_formulada = veces_formulada;
    }

    public int getVeces_acertada()
    {
        return veces_acertada;
    }

    public void setVeces_acertada(int veces_acertada)
    {
        this.veces_acertada = veces_acertada;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "id=" + id + ", enunciado=" + enunciado + ", categoria=" + categoria + ", nivel=" + nivel + ", foto=" + foto + ", veces_formulada=" + veces_formulada + ", veces_acertada=" + veces_acertada + ", respuestas=" + respuestas + '}';
    }
    
}
