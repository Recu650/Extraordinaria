/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_4;

import dao.UsuarioDAO;
import dao.UsuarioDAOJDBC;
import java.time.LocalDate;
import modelo.Usuario;

/**
 *
 * @author usuario
 */
public class NewMain
{

    public static void main(String[] args)
    {
        // TODO code application logic here
        UsuarioDAO dao = new UsuarioDAOJDBC();
        Usuario usuario = recogerDatos();
        dao.insertar(usuario);
    }
    
    public static Usuario recogerDatos(){
        String user = Teclado.introString("User:");
        String pasw = Teclado.introString("Password:");
        String name = Teclado.introString("Nombre:");
        String surn = Teclado.introString("Apellidos:");
        LocalDate date = Teclado.introFecha("Fecha de nacimiento");
        
        return new Usuario(user, pasw, name, surn, date);
    }
}
