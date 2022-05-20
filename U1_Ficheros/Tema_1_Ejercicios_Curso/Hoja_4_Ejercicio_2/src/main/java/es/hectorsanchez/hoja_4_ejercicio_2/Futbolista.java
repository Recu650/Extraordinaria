package es.hectorsanchez.hoja_4_ejercicio_2;

import java.util.Collections;

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
    
    

//    @Override
//    public String toString()
//    {
//        String linea = String.format("%3d %-25s %3s %-16s %4,2s",
//                this.id, this.alias, this.codeEquipo, this.puesto, this.altura);
//        return linea;
//    }

    @Override
    public String toString()
    {
        return id + " " + alias + " " + codeEquipo + " " + puesto + " " + altura;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Futbolista other = (Futbolista) obj;
        if (this.id != other.id)
            return false;
        return true;
    }

//    @Override
//    public int compareTo(Object o)
//    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
  
}
