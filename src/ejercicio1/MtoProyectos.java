/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author steve
 */
public class MtoProyectos {

   
    
    private Connection cn;
    private Integer codigo;
    private String nombre;
    private String anio;
    private Double costo;
    private String fecha;
    
    private Integer codigoS;
    private String nombreS;
    //Creamos la conexion a la base de datos
    public MtoProyectos(){
        Conexion con = new Conexion();
        cn= con.conectar();
    }
     /**
     * @return the codigoS
     */
    public Integer getCodigoS() {
        return codigoS;
    }

    /**
     * @param codigoS the codigoS to set
     */
    public void setCodigoS(Integer codigoS) {
        this.codigoS = codigoS;
    }

    /**
     * @return the nombreS
     */
    public String getNombreS() {
        return nombreS;
    }

    /**
     * @param nombreS the nombreS to set
     */
    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }
    
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    public boolean guardarProyecto(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO proyectos (codigo_proyecto, nombre_proyecto, anio_creacion, costo, fecha_creacion) VALUES(?, ?, ?, ?, ?)";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setInt(1, codigo);//codigo es como se definio en la clase aunque en la base se llama codigo_proyecto
            cmd.setString(2, nombre);
            cmd.setString(3, anio);
            cmd.setDouble(4, costo);
            cmd.setString(5, fecha);
            //Si da error devuelve 1, caso contrario 0
            if (!cmd.execute()) {
                resp=true;
                
            }
            //Cerrando conexion
            cmd.close();
            cn.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean modificarProyecto(){
        boolean resp= false;
        try {
            String sql = "UPDATE proyectos SET nombre_proyecto=?, anio_creacion=?, costo=?, fecha_creacion=? WHERE codigo_proyecto=?;";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setString(1, nombre);//codigo es como se definio en la clase aunque en la base se llama codigo_proyecto
            cmd.setString(2, anio);
            cmd.setDouble(3, costo);
            cmd.setString(4, fecha);
            cmd.setInt(5, codigo);
            //Si da error devuelve 1, caso contrario 0
            if (!cmd.execute()) {
                resp=true;
            }
            //Cerrando conexion
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean eliminarProyecto(){
        boolean resp= false ;
        try {
            String sql = "DELETE FROM proyectos WHERE codigo_proyecto=?;";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setInt(1, codigo);
            //Si da error devuelve 1, caso contrario 0
            if (!cmd.execute()) {
                resp=true;
            }
            //Cerrando conexion
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean consultarProyecto(){
        boolean resp= false;
        try {
             String sql = "SELECT codigo_proyecto, nombre_proyecto, anio_creacion, costo, fecha_creacion FROM proyectos WHERE codigo_proyecto=?";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setInt(1, codigo);
            //Importar clase resultset
            ResultSet rs= cmd.executeQuery();
            //Recorrer la lista de registro
            if (rs.next()) {
                resp = true;
                //asignandole a los atributos de la clase 
                codigo = rs.getInt(1);
                nombre = rs.getString(2);
                anio = rs.getString(3);
                costo = rs.getDouble(4);
                fecha = rs.getString(5);
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean guardarSeccion(){
        boolean resp = false;
        try {
            String sql = "INSERT INTO seccion (codigo_seccion, seccion) VALUES(?, ?)";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setInt(1, codigoS);//codigo es como se definio en la clase aunque en la base se llama codigo_proyecto
            cmd.setString(2, nombreS);
            //Si da error devuelve 1, caso contrario 0
            if (!cmd.execute()) {
                resp=true;
                
            }
            //Cerrando conexion
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean modificarSeccion(){
        boolean resp= false;
        try {
            String sql = "UPDATE seccion SET seccion=? WHERE codigo_seccion=?;";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setString(1, nombreS);//codigo es como se definio en la clase aunque en la base se llama codigo_proyecto
            cmd.setInt(2,codigoS);
            //Si da error devuelve 1, caso contrario 0
            if (!cmd.execute()) {
                resp=true;
            }
            //Cerrando conexion
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean eliminarSeccion(){
        boolean resp = false;
        try {
            String sql = "DELETE FROM seccion WHERE codigo_seccion=?;";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setInt(1, codigoS);
            //Si da error devuelve 1, caso contrario 0
            if (!cmd.execute()) {
                resp=true;
            }
            //Cerrando conexion
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
    
    public boolean consultarSeccion(){
        boolean resp= false;
        try {
             String sql = "SELECT codigo_seccion, seccion FROM seccion WHERE codigo_seccion=?";
          
            //Se pasan por referencia por seguridad
            //importar clase PreparedStatement
            PreparedStatement cmd = cn.prepareStatement(sql);
            //Llenar los parametros de la clase, se coloca en el ordne de la tabla
            cmd.setInt(1, codigoS);
            //Importar clase resultset
            ResultSet rs= cmd.executeQuery();
            //Recorrer la lista de registro
            if (rs.next()) {
                resp = true;
                //asignandole a los atributos de la clase 
                codigoS = rs.getInt(1);
                nombreS = rs.getString(2);
                
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }
}
