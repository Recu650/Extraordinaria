package Modelo;

/**
 *
 * @author usuario
 */

public class Respuesta {
    //ATRIBUTOS------------------------------------------------------------------------------------
    private int id;
    private Pregunta pregunta;
    private String texto;
    private boolean correcta;
    private String foto;
    private int veces_respondida;
    //CONSTRUCTOR----------------------------------------------------------------------------------
    public Respuesta()
    {
    }
    //GETTER_&_SETTER------------------------------------------------------------------------------

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Pregunta getPregunta()
    {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta)
    {
        this.pregunta = pregunta;
    }

    public String getTexto()
    {
        return texto;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    public boolean isCorrecta()
    {
        return correcta;
    }

    public void setCorrecta(boolean correcta)
    {
        this.correcta = correcta;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public int getVeces_respondida()
    {
        return veces_respondida;
    }

    public void setVeces_respondida(int veces_respondida)
    {
        this.veces_respondida = veces_respondida;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "id=" + id + ", pregunta=" + pregunta + ", texto=" + texto + ", correcta=" + correcta + ", foto=" + foto + ", veces_respondida=" + veces_respondida + '}';
    }
    
}
