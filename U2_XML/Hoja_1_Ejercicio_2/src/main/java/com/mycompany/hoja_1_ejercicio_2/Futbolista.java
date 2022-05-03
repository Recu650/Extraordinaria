package com.mycompany.hoja_1_ejercicio_2;

/**
 *
 * @author usuario
 */
public class Futbolista {
    private short id;
    private String alias;
    private byte puesto;
    private float altura;
    private String codeEquipo;

    public Futbolista()
    {
    }

    public Futbolista(short id, String alias, byte puesto, float altura, String codeEquipo)
    {
        this.id = id;
        this.alias = alias;
        this.puesto = puesto;
        this.altura = altura;
        this.codeEquipo = codeEquipo;
    }

    public short getId()
    {
        return id;
    }

    public void setId(short id)
    {
        this.id = id;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public byte getPuesto()
    {
        return puesto;
    }

    public void setPuesto(byte puesto)
    {
        this.puesto = puesto;
    }

    public float getAltura()
    {
        return altura;
    }

    public void setAltura(float altura)
    {
        this.altura = altura;
    }

    public String getCodeEquipo()
    {
        return codeEquipo;
    }

    public void setCodeEquipo(String codeEquipo)
    {
        this.codeEquipo = codeEquipo;
    }

    @Override
    public String toString()
    {
        return id + " " + alias + " " + codeEquipo + " " + puesto + " " + altura;
    }
    
    public String obtenerPuesto() {
        String puestoString = "";
        switch(this.puesto) {
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
    
}
