package com.mycompany.hoja_4_ejercicio_1;

/**
 *
 * @author usuario
 */
public class Futbolista {
    private int id;
    private String alias;
    private String codeEquipo;
    private Puesto puesto;
    private float altura;

    public Futbolista(int id, String alias, String codeEquipo, Puesto puesto, float altura)
    {
        this.id = id;
        this.alias = alias;
        this.codeEquipo = codeEquipo;
        this.puesto = puesto;
        this.altura = altura;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public String getCodeEquipo()
    {
        return codeEquipo;
    }

    public void setCodeEquipo(String codeEquipo)
    {
        this.codeEquipo = codeEquipo;
    }

    public Puesto getPuesto()
    {
        return puesto;
    }

    public void setPuesto(Puesto puesto)
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
    
    @Override
    public String toString()
    {
        return id + " " + alias + " " + codeEquipo + " " + puesto + " " + altura;
    }
}
