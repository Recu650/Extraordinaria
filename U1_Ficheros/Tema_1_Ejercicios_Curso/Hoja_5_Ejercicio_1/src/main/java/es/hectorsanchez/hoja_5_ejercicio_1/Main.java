/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_5_ejercicio_1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        File f = new File("profesores.dat");

        Scanner teclado = new Scanner(System.in);
        int select;
        do
        {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Add Profesor         |");
            System.out.println("| 2.List Profe Localidad |");
            System.out.println("| 3.List Profe Inst Antig|");
            System.out.println("| 4.Traslado Profe       |");
            System.out.println("| 5.Destruir Insti       |");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select)
            {
                case 1:
                    Escribir(f);
                    Leer(f);
                    break;
                case 2:
                    LeerLocalidad(f);
                    break;
                case 3:
                    Instituto instiAntiguo = InstiMasAntiguo(f);
                    LeerInstiMasAntiguo(f, instiAntiguo);
                    break;
                case 4:
                    Traslado(f);
                    break;
                case 5:
                    BorrarInstituto(f);
                    break;
                case 6:
                    Leer(f);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    public static void Escribir(File f)
    {
        ObjectOutputStream oos = null;

        int numReg;
        String nombre = "";
        LocalDate fechaIng;
        Instituto insti;

        try
        {
            if (f.exists())
            {
                oos = new MiObjectOutputStream(new FileOutputStream(f, true));
            } else
            {
                oos = new ObjectOutputStream(new FileOutputStream(f, true));
            }

            numReg = Teclado.introInt("Numero de Registro: ");
            nombre = Teclado.introString("Nombre Profe: ");
            fechaIng = Teclado.introFecha("Fecha de ingreso: ");
            String nInsti = Teclado.introString("Nombre Instituto: ");
            LocalDate fInsti = Teclado.introFecha("Fecha de Construccion: ");
            String local = Teclado.introString("Localidad: ");
            insti = new Instituto(nInsti, fInsti, local);

            Profesor p = new Profesor(numReg, nombre, fechaIng, insti);
            oos.writeObject(p);
        } catch (IOException ex)
        {
            System.out.println("Error al escribir " + ex.toString());
        } finally
        {
            if (oos != null)
            {
                try
                {
                    oos.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar " + ex.toString());
                }
            }
        }
    }

    public static void Leer(File f)
    {
        ObjectInputStream ois = null;
        try
        {
            Profesor p;
            ois = new ObjectInputStream(new FileInputStream(f));
            while (true)
            {
                p = (Profesor) ois.readObject();
                System.out.println(p.toString());
            }
        } catch (EOFException e)
        {

        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error, el tipo de obj no es compatible");
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.toString());
        } finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar: " + ex.toString());
                }
            }
        }
    }

    public static void LeerLocalidad(File f)
    {
        ObjectInputStream ois = null;
        String locBus = Teclado.introString("Localidad para Filtrar: ");
        try
        {
            Profesor p;
            ois = new ObjectInputStream(new FileInputStream(f));
            while (true)
            {
                p = (Profesor) ois.readObject();
                if (p.getInsti().getLocalidad().equalsIgnoreCase(locBus))
                {
                    System.out.println(p.toString());
                }
            }

        } catch (EOFException e)
        {

        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error, el tipo de obj no es compatible");
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.toString());
        } finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar: " + ex.toString());
                }
            }
        }
    }

    public static Instituto InstiMasAntiguo(File f)
    {
        ObjectInputStream ois = null;
        Instituto instiAntiguo = null;
        try
        {
            Profesor p;
            ois = new ObjectInputStream(new FileInputStream(f));
            LocalDate f_antigua = LocalDate.now();
            while (true)
            {
                p = (Profesor) ois.readObject();
                if (p.getInsti().getFechaConstruccion().isBefore(f_antigua))
                {
                    f_antigua = p.getInsti().getFechaConstruccion();
                    instiAntiguo = p.getInsti();
                }

            }
        } catch (EOFException e)
        {

        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error, el tipo de obj no es compatible");
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.toString());
        } finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar: " + ex.toString());
                }
            }
        }
        return instiAntiguo;
    }

    public static void LeerInstiMasAntiguo(File f, Instituto instiAntiguo)
    {
        ObjectInputStream ois = null;

        try
        {
            Profesor p;
            ois = new ObjectInputStream(new FileInputStream(f));
            while (true)
            {
                p = (Profesor) ois.readObject();
                if (p.getInsti().equals(instiAntiguo))
                {
                    System.out.println(p.toString());
                }
            }

        } catch (EOFException e)
        {
            System.out.println("Se alcanzo el final");
        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error, el tipo de obj no es compatible");
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.toString());
        } finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar: " + ex.toString());
                }
            }
        }
    }

    public static void Traslado(File f)
    {
        ObjectInputStream ois = null;
        ArrayList<Profesor> profesores = new ArrayList();
        int numProfe = Teclado.introInt("Numero de Profe a Trasladar: ");

        try
        {
            Profesor p;
            ois = new ObjectInputStream(new FileInputStream(f));

            while (true)
            {
                p = (Profesor) ois.readObject();
                if (p.getNumRegistro() == numProfe)
                {
                    String nuevoInsti = Teclado.introString("Nombre Nuevo Insti: ");
                    p.getInsti().setNombre(nuevoInsti);
                }
                profesores.add(p);
            }

        } catch (EOFException e)

        {

        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error, el tipo de obj no es compatible");
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.toString());
        } finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar: " + ex.toString());
                }
            }

        }

        //Escribir array
        ObjectOutputStream oos = null;

        try
        {
            oos = new ObjectOutputStream(new FileOutputStream(f));

            for (Profesor profe : profesores)
            {
                System.out.println(profe);
                oos.writeObject(profe);
            }
        } catch (IOException ex)
        {
            System.out.println("Error al escribir " + ex.toString());
        } finally
        {
            if (oos != null)
            {
                try
                {
                    oos.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar " + ex.toString());
                }
            }
        }
    }

    public static ArrayList BorrarInstituto(File f)
    {
        ObjectInputStream ois = null;
        ArrayList<Profesor> profesores = new ArrayList();
        ArrayList<Profesor> borrados = new ArrayList();
        String instiBorrar = Teclado.introString("Instituto a borrar: ");
        try
        {
            Profesor p;
            ois = new ObjectInputStream(new FileInputStream(f));
            while (true)
            {
                p = (Profesor) ois.readObject();
                if (p.getInsti().getNombre().equalsIgnoreCase(instiBorrar))
                {
                    borrados.add(p);
                }else{
                    profesores.add(p);
                }
            }

        } catch (EOFException e)
        {
            
        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error, el tipo de obj no es compatible");
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.toString());
        } finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar: " + ex.toString());
                }
            }
        }
        
        ObjectOutputStream oos = null;

        try
        {
            oos = new ObjectOutputStream(new FileOutputStream(f));

            for (Profesor profe : profesores)
            {
                oos.writeObject(profe);
            }
        } catch (IOException ex)
        {
            System.out.println("Error al escribir " + ex.toString());
        } finally
        {
            if (oos != null)
            {
                try
                {
                    oos.close();
                } catch (IOException ex)
                {
                    System.out.println("Error al cerrar " + ex.toString());
                }
            }
        }
        
        return borrados;
    }

}
