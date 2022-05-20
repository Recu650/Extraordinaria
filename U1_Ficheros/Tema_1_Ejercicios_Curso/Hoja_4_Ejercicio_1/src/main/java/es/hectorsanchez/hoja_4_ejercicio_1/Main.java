/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.hectorsanchez.hoja_4_ejercicio_1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        File f = new File("Futbolistas.dat");
        try
        {
            if (f.exists() == false)
            {
                f.createNewFile();
                System.out.println("Fichero creado correctamente");
            }
        } catch (IOException ex)
        {
            System.out.println("Error al crear el fichero");
        }

        Scanner teclado = new Scanner(System.in);
        int select;
        do
        {
            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Add Futbolista       |");
            System.out.println("| 2.List Futbolistas     |");
            System.out.println("| 3.List Futbolistas equi|");
            System.out.println("| 4.Buscar Futbolista    |");
            System.out.println("| 5.Modificar equipo futb|");
            System.out.println("| 6.Modificar datos futbo|");
            System.out.println("| 7.Eliminar futbolista  |");
            System.out.println("| 0.Salir                |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select)
            {
                case 1:
                    //COMPARO EL ID CON LOS DEL FICHERO
                    System.out.println("Id Futbolista: ");
                    int id = teclado.nextInt();
                    if (buscaID(id, f) == true)
                        System.out.println("Jugador ya existe");
                    else
                    {
                        //PIDO DATOS
                        teclado.nextLine();
                        System.out.println("Alias Futbolista: ");
                        String alias = teclado.nextLine();

                        System.out.println("Codigo Equipo: ");
                        String code = teclado.nextLine();

                        System.out.println("Puesto [1-PORTERO, 2-DEFENSA, 3-CENTROCAMPISTA, 4-DELANTERO]");
                        int npuesto = 0;
                        do
                        {
                            npuesto = teclado.nextInt();
                        } while (npuesto != 1 && npuesto != 2 && npuesto != 3 && npuesto != 4);
                        Puesto puesto = Puesto.values()[npuesto - 1];

                        System.out.println("Altura futbolista: ");
                        float altura = teclado.nextFloat();
                        //CREO OBJETO FUTBOLISTA
                        Futbolista f1 = new Futbolista(id, alias, code, puesto, altura);
                        //INSERTO FUTBOLISTA
                        insertFutbolista(f1, f);
                    }
                    break;
                case 2:
                    System.out.println(leerFichero(f));
                    break;
                case 3:
                    teclado.nextLine();
                    System.out.println("Equipo a Buscar");
                    String code = teclado.nextLine();
                    System.out.println(leerFicheroEquipos(f, code));
                    break;
                case 4:
                    teclado.nextLine();
                    System.out.println("ID Jugador a Buscar");
                    int idJugador = teclado.nextInt();
                    System.out.println(leerFicheroJugador(f, idJugador));
                    break;
                case 5:
                    teclado.nextLine();
                    System.out.println("ID Jugador a Cambiar Equiupo");
                    int idJugador2 = teclado.nextInt();

                    modificarEquipo(f, idJugador2);
                    break;
                case 6:
                    teclado.nextLine();
                    System.out.println("ID Jugador a Cambiar Datos");
                    int idJugador3 = teclado.nextInt();

                    modificarJugador(f, idJugador3);
                    break;
                case 7:
                    teclado.nextLine();
                    System.out.println("ID Jugador a Eleminar");
                    int idJugador4 = teclado.nextInt();
                    borrarJugador(f, idJugador4);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (select != 0);
    }

    public static boolean buscaID(int id, File f)
    {
        int buscaId;
        FileInputStream fis = null;
        DataInputStream dis = null;
        boolean fin = false;

        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(new BufferedInputStream(fis));

            do
            {
                buscaId = dis.readInt();
                if (buscaId == id)
                {

                    fin = true;
                }
            } while (!fin);
        } catch (EOFException eof)
        {

        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }
        return fin;
    }

    public static void insertFutbolista(Futbolista f1, File f)
    {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try
        {
            fos = new FileOutputStream(f, true);
            dos = new DataOutputStream(fos);

            dos.writeInt(f1.getId());
            dos.writeUTF(f1.getAlias());
            dos.writeUTF(f1.getCodeEquipo());
            dos.writeInt(f1.getPuesto().ordinal());
            dos.writeFloat(f1.getAltura());

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.toString());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dos != null)
                    dos.close();
            } catch (IOException ex)
            {
                System.out.println("Error al cerrar");
            }

        }
    }

    public static String leerFichero(File f)
    {
        FileInputStream fis = null;
        DataInputStream dis = null;
        boolean fin = false;
        String textoFichero = "";
        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(new BufferedInputStream(fis));

            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();
                textoFichero = textoFichero + new Futbolista(id, alias, code, puesto, altura) + "\n";
            } while (!fin);
        } catch (EOFException eof)
        {
            fin = true;
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }
        return textoFichero;
    }

    public static String leerFicheroEquipos(File f, String codeEq)
    {
        FileInputStream fis = null;
        DataInputStream dis = null;
        boolean fin = false;
        String textoFichero = "";
        int cont = 0;
        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(new BufferedInputStream(fis));

            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                if (code.equalsIgnoreCase(codeEq))
                {
                    textoFichero = textoFichero + new Futbolista(id, alias, code, puesto, altura).toString() + "\n";
                    cont++;
                }

            } while (!fin);
        } catch (EOFException eof)
        {
            fin = true;
            if (cont == 0)
            {
                textoFichero = "Ningun jugador en ese equipo";
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }
        return textoFichero;
    }

    public static String leerFicheroJugador(File f, int idJugador)
    {
        FileInputStream fis = null;
        DataInputStream dis = null;
        boolean fin = false;
        String textoFichero = "";
        int cont = 0;
        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(new BufferedInputStream(fis));

            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                if (id == idJugador)
                {
                    textoFichero = textoFichero + new Futbolista(id, alias, code, puesto, altura).toString() + "\n";
                    cont++;
                }

            } while (!fin);
        } catch (EOFException eof)
        {
            fin = true;
            if (cont == 0)
            {
                textoFichero = "Ningun jugador encontrado";
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }
        return textoFichero;
    }

    public static void modificarEquipo(File f, int idJugador)
    {
        Scanner teclado = new Scanner(System.in);
        FileInputStream fis = null;
        DataInputStream dis = null;
        boolean fin = false;
        boolean encontrado = false;

        File f_aux = new File("aux.dat");

        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(new BufferedInputStream(fis));

            fos = new FileOutputStream(f_aux);
            dos = new DataOutputStream(fos);
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                if (id == idJugador)
                {
                    encontrado = true;
                    System.out.println(id + " " + alias + " " + code + " " + puesto + " " + altura);
                    System.out.println("Nuevo Equipo (Siglas)");
                    code = teclado.nextLine();
                }
                dos.writeInt(id);
                dos.writeUTF(alias);
                dos.writeUTF(code);
                dos.writeInt(puesto.ordinal());
                dos.writeFloat(altura);

            } while (!fin);
        } catch (EOFException eof)
        {
            if (encontrado == false)
                System.out.println("Jugador no encontrado");
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
                if (dos != null)
                    dos.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }

        try
        {
            fis = new FileInputStream(f_aux);
            dis = new DataInputStream(new BufferedInputStream(fis));

            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                dos.writeInt(id);
                dos.writeUTF(alias);
                dos.writeUTF(code);
                dos.writeInt(puesto.ordinal());
                dos.writeFloat(altura);

            } while (!fin);
        } catch (EOFException eof)
        {
            f_aux.delete();
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
                if (dos != null)
                    dos.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }

    }

    public static void modificarJugador(File f, int idJugador)
    {
        Scanner teclado = new Scanner(System.in);
        FileInputStream fis = null;
        DataInputStream dis = null;
        boolean fin = false;
        boolean encontrado = false;

        File f_aux = new File("aux.dat");

        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(new BufferedInputStream(fis));

            fos = new FileOutputStream(f_aux);
            dos = new DataOutputStream(fos);
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                if (id == idJugador)
                {
                    encontrado = true;
                    
                    System.out.println("Nuevo alias");
                    alias = teclado.nextLine();
                    System.out.println("Nuevo codigo de equipo");
                    code = teclado.nextLine();
                    System.out.println("Nuevo Puesto [1-PORTERO, 2-DEFENSA, 3-CENTROCAMPISTA, 4-DELANTERO]");
                    int npuesto = 0;
                    do
                    {
                        npuesto = teclado.nextInt();
                    } while (npuesto != 1 && npuesto != 2 && npuesto != 3 && npuesto != 4);
                    puesto = Puesto.values()[npuesto - 1];
                    System.out.println("Nueva altura");
                    altura = teclado.nextFloat();
                }

                dos.writeInt(id);
                dos.writeUTF(alias);
                dos.writeUTF(code);
                dos.writeInt(puesto.ordinal());
                dos.writeFloat(altura);

            } while (!fin);
        } catch (EOFException eof)
        {
            if (encontrado == false)
                System.out.println("Jugador no encontrado");
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
                if (dos != null)
                    dos.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }

        try
        {
            fis = new FileInputStream(f_aux);
            dis = new DataInputStream(new BufferedInputStream(fis));

            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                dos.writeInt(id);
                dos.writeUTF(alias);
                dos.writeUTF(code);
                dos.writeInt(puesto.ordinal());
                dos.writeFloat(altura);

            } while (!fin);
        } catch (EOFException eof)
        {
            f_aux.delete();
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
                if (dos != null)
                    dos.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }

    }
    
    public static void borrarJugador(File f, int idJugador)
    {
        Scanner teclado = new Scanner(System.in);
        FileInputStream fis = null;
        DataInputStream dis = null;
        boolean fin = false;
        boolean encontrado = false;

        File f_aux = new File("aux.dat");

        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try
        {
            fis = new FileInputStream(f);
            dis = new DataInputStream(new BufferedInputStream(fis));

            fos = new FileOutputStream(f_aux);
            dos = new DataOutputStream(fos);
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                if (id != idJugador)
                {
                    dos.writeInt(id);
                    dos.writeUTF(alias);
                    dos.writeUTF(code);
                    dos.writeInt(puesto.ordinal());
                    dos.writeFloat(altura);
                }else encontrado = true;
                

            } while (!fin);
        } catch (EOFException eof)
        {
            if (encontrado == false)
                System.out.println("Jugador no encontrado");
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
                if (dos != null)
                    dos.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }

        try
        {
            fis = new FileInputStream(f_aux);
            dis = new DataInputStream(new BufferedInputStream(fis));

            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            do
            {
                int id = dis.readInt();
                String alias = dis.readUTF();
                String code = dis.readUTF();
                Puesto puesto = Puesto.values()[dis.readInt()];
                float altura = dis.readFloat();

                dos.writeInt(id);
                dos.writeUTF(alias);
                dos.writeUTF(code);
                dos.writeInt(puesto.ordinal());
                dos.writeFloat(altura);

            } while (!fin);
        } catch (EOFException eof)
        {
            f_aux.delete();
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                if (dis != null)
                    dis.close();
                if (dos != null)
                    dos.close();
            } catch (IOException exc)
            {
                System.out.println("Error al cerrar");
            }
        }

    }
}
