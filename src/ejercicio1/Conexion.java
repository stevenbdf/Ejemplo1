/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author steve
 */
public class Conexion {
    //agregar siempre java.sql.Connection
    public Connection conectar(){
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //importar java.sql.DriverManager
            //Conexion laptop
           //cn= DriverManager.getConnection("jdbc:sqlserver://DESKTOP-CAVE2O6\\SQLEXPRESS;databaseName=control_proyectos_2017;user=sa;password=123;");
            //Conexion PC
             cn= DriverManager.getConnection("jdbc:sqlserver://Internet\\SQLEXPRESS;databaseName=control_proyectos_2017;user=sa;password=123;");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cn;
    }
}
