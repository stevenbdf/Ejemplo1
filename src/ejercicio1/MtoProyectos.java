/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author steve
 */
public class MtoProyectos {

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
    
    private Connection cn;
    private Integer codigo;
    private String nombre;
    private String anio;
    private Double costo;
    private String fecha;
    
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
            
        } catch (Exception e) {
        }
        return resp;
    }
}
