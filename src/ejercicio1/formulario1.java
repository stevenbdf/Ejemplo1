/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiante
 */
public class formulario1 extends javax.swing.JFrame {

    /**
     * Creates new form formulario1
     */
    DefaultTableModel modeloTabla;
    DefaultTableModel modeloTabla2;
    DefaultTableModel modeloTabla3;
    DefaultComboBoxModel modeloCombo;
    DefaultComboBoxModel modeloCombo2;
    
    Conexion con= new Conexion();
    public formulario1() {
        
        modeloTabla= new DefaultTableModel(null, getColumnas());
        setFilas();
        modeloTabla2= new DefaultTableModel(null, getColumnas2());
        setFilas2();
        modeloTabla3= new DefaultTableModel(null, getColumnas3());
        setFilas3();
        
        modeloCombo = new DefaultComboBoxModel(new String [] {});
        modeloCombo2 = new DefaultComboBoxModel(new String [] {});
        
        initComponents();
        llenaComboBox();
        llenaComboBox2();
        setLocationRelativeTo(null);
        
        ImageIcon foto = new ImageIcon (getClass().getResource("/imagenes/agregar.png"));
        ImageIcon icono = new ImageIcon(foto.getImage().getScaledInstance(agregar.getWidth(),agregar.getHeight(),Image.SCALE_DEFAULT));
        agregar.setIcon(icono);
        
        ImageIcon foto1 = new ImageIcon (getClass().getResource("/imagenes/modificar.png"));
        ImageIcon icono1 = new ImageIcon(foto1.getImage().getScaledInstance(modificar.getWidth(),modificar.getHeight(),Image.SCALE_DEFAULT));
        modificar.setIcon(icono1);
        
        ImageIcon foto2 = new ImageIcon (getClass().getResource("/imagenes/eliminar.png"));
        ImageIcon icono2 = new ImageIcon(foto2.getImage().getScaledInstance(eliminar.getWidth(),eliminar.getHeight(),Image.SCALE_DEFAULT));
        eliminar.setIcon(icono2);
        
        ImageIcon foto3 = new ImageIcon (getClass().getResource("/imagenes/guardar.png"));
        ImageIcon icono3 = new ImageIcon(foto3.getImage().getScaledInstance(buscar.getWidth(),buscar.getHeight(),Image.SCALE_DEFAULT));
        buscar.setIcon(icono3);
        
        ImageIcon foto4 = new ImageIcon (getClass().getResource("/imagenes/actualizar.png"));
        ImageIcon icono4 = new ImageIcon(foto4.getImage().getScaledInstance(actualizar.getWidth(),actualizar.getHeight(),Image.SCALE_DEFAULT));
        actualizar.setIcon(icono4);
        
      
        agregar1.setIcon(icono); 
        modificar1.setIcon(icono1);
        eliminar1.setIcon(icono2);
        buscar1.setIcon(icono3);
        actualizar1.setIcon(icono4);
        
        agregar2.setIcon(icono); 
        modificar2.setIcon(icono1);
        eliminar2.setIcon(icono2);
        buscar2.setIcon(icono3);
        actualizar2.setIcon(icono4);
    }
    
    private void llenaComboBox() {
        modeloCombo.removeAllElements();
        try {
            
            /* Realizamos la consulta a la base de datos*/
            String sql = "SELECT seccion FROM seccion";  
            PreparedStatement verDatos = con.conectar().prepareStatement(sql);
            ResultSet ver = verDatos.executeQuery(); 
            while (ver.next()) {
              
                modeloCombo.addElement(ver.getObject("seccion"));
            }

        } catch (SQLException ex) {
            System.out.println("Error: "+ex);

        }
    }
    
    private void llenaComboBox2() {
        modeloCombo2.removeAllElements();
        try {
            
            /* Realizamos la consulta a la base de datos*/
            String sql = "SELECT nombre_proyecto FROM proyectos";  
            PreparedStatement verDatos = con.conectar().prepareStatement(sql);
            ResultSet ver = verDatos.executeQuery(); 
            while (ver.next()) {
              
                modeloCombo2.addElement(ver.getObject("nombre_proyecto"));
            }

        } catch (SQLException ex) {
            System.out.println("Error: "+ex);

        }
    }
    
    private String[] getColumnas(){
        String columnas[] = new String[]{"CODIGO","NOMBRE","AÑO","COSTO","FECHA REGISTRO"};
        return columnas;
    }
    
    private void setFilas(){
        try{
            String consulta = "SELECT codigo_proyecto, nombre_proyecto, anio_creacion, costo, fecha_creacion FROM proyectos";
            
                PreparedStatement us = con.conectar().prepareStatement(consulta);
                ResultSet resultado = us.executeQuery();
                
                Object datos[]= new Object[5];
                
                while(resultado.next()){
                    for (int i = 0; i <datos.length; i++) {
                        datos[i]= resultado.getObject(i+1);
                    }
                    modeloTabla.addRow(datos);
                }
        }
        catch(Exception e){
            
                
            }
            
        }
    private String[] getColumnas2(){
        String columnas[] = new String[]{"CODIGO","NOMBRE"};
        return columnas;
    }
    private void setFilas2(){
        try{
            String consulta = "SELECT codigo_seccion, seccion FROM seccion";
            
                PreparedStatement us = con.conectar().prepareStatement(consulta);
                ResultSet resultado = us.executeQuery();
                
                Object datos[]= new Object[2];
                
                while(resultado.next()){
                    for (int i = 0; i <datos.length; i++) {
                        datos[i]= resultado.getObject(i+1);
                    }
                    modeloTabla2.addRow(datos);
                }
        }
        catch(Exception e){
            
                
            }
            
        }
    private String[] getColumnas3(){
        String columnas[] = new String[]{"CARNET","NOMBRE","APELLIDO","GENERO","CORREO","SECCION","PROYECTO"};
        return columnas;
    }
    private void setFilas3(){
        try{
            String consulta = "SELECT carnet, nombres, apellidos, genero, correo, seccion.seccion,proyectos.nombre_proyecto "
                    + " FROM proyectos, seccion, integrantes "
                    + "WHERE integrantes.codigo_proyecto=proyectos.codigo_proyecto AND integrantes.codigo_seccion=seccion.codigo_seccion ";
            
            
                PreparedStatement us = con.conectar().prepareStatement(consulta);
                ResultSet resultado = us.executeQuery();
                
                Object datos[]= new Object[7];
                
                while(resultado.next()){
                    for (int i = 0; i <datos.length; i++) {
                        datos[i]= resultado.getObject(i+1);
                    }
                    modeloTabla3.addRow(datos);
                }
        }
        catch(Exception e){
            
                
            }
            
        }
   
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        año = new javax.swing.JTextField();
        costo = new javax.swing.JTextField();
        fecha = new javax.swing.JTextField();
        codigo = new javax.swing.JTextField();
        buscar = new javax.swing.JLabel();
        agregar = new javax.swing.JLabel();
        modificar = new javax.swing.JLabel();
        eliminar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        actualizar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        codigo1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nombre1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        actualizar1 = new javax.swing.JLabel();
        agregar1 = new javax.swing.JLabel();
        modificar1 = new javax.swing.JLabel();
        eliminar1 = new javax.swing.JLabel();
        buscar1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        codigo2 = new javax.swing.JTextField();
        nombre2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        foto = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        comboProyecto = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        comboSeccion = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        actualizar2 = new javax.swing.JLabel();
        agregar2 = new javax.swing.JLabel();
        modificar2 = new javax.swing.JLabel();
        eliminar2 = new javax.swing.JLabel();
        buscar2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de Proyectos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(34, 166, 170));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(34, 166, 170));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel2.setText("Fecha Registro:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, 30));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel3.setText("Codigo:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 30));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel5.setText("Año:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 30));

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel6.setText("Costo ($):");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 30));
        jPanel3.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 150, 30));
        jPanel3.add(año, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 100, 30));
        jPanel3.add(costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 100, 30));
        jPanel3.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 100, 30));
        jPanel3.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, 30));

        buscar.setText("Button1");
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarMouseClicked(evt);
            }
        });
        jPanel3.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 50, 50));

        agregar.setText("Button1");
        agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarMouseClicked(evt);
            }
        });
        jPanel3.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 50, 50));

        modificar.setText("Button1");
        modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarMouseClicked(evt);
            }
        });
        jPanel3.add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 50, 50));

        eliminar.setText("Button1");
        eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarMouseClicked(evt);
            }
        });
        jPanel3.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 50, 50));

        jTable2.setBackground(new java.awt.Color(204, 255, 255));
        jTable2.setModel(modeloTabla );
        jTable2.setGridColor(new java.awt.Color(102, 102, 102));
        jTable2.setSelectionForeground(new java.awt.Color(13, 51, 62));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 530, 190));

        jButton1.setText("Limpiar Campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 130, 50));

        actualizar.setText("Button1");
        actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualizarMouseClicked(evt);
            }
        });
        jPanel3.add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 50, 50));

        jTabbedPane1.addTab("Proyectos", jPanel3);

        jPanel4.setBackground(new java.awt.Color(34, 166, 170));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, 30));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel7.setText("Codigo:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 30));
        jPanel4.add(nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 150, 30));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel8.setText("Nombre:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, 30));

        actualizar1.setText("Button1");
        actualizar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualizar1MouseClicked(evt);
            }
        });
        jPanel4.add(actualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 50, 50));

        agregar1.setText("Button1");
        agregar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregar1MouseClicked(evt);
            }
        });
        jPanel4.add(agregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 50, 50));

        modificar1.setText("Button1");
        modificar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificar1MouseClicked(evt);
            }
        });
        jPanel4.add(modificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 50, 50));

        eliminar1.setText("Button1");
        eliminar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminar1MouseClicked(evt);
            }
        });
        jPanel4.add(eliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 50, 50));

        buscar1.setText("Button1");
        buscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscar1MouseClicked(evt);
            }
        });
        jPanel4.add(buscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 50, 50));

        jButton2.setText("Limpiar Campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 130, 50));

        jTable3.setBackground(new java.awt.Color(204, 255, 255));
        jTable3.setModel(modeloTabla2);
        jTable3.setGridColor(new java.awt.Color(102, 102, 102));
        jTable3.setSelectionForeground(new java.awt.Color(13, 51, 62));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 300, 190));

        jTabbedPane1.addTab("Secciones", jPanel4);

        jPanel5.setBackground(new java.awt.Color(34, 166, 170));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel9.setText("Carnet:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 30));
        jPanel5.add(codigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, 30));
        jPanel5.add(nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 150, 30));

        jLabel10.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel10.setText("Nombre:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        jLabel11.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel11.setText("Genero:");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 30));
        jPanel5.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 150, 30));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel12.setText("Apellido:");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 100, 30));

        jLabel13.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel13.setText("Foto:");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, 30));
        jPanel5.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 150, 30));

        foto.setText("Foto");
        jPanel5.add(foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 100, 100));

        jLabel15.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel15.setText("Proyecto:");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, 30));

        jButton3.setText("Examinar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, -1, -1));

        jLabel16.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel16.setText("Correo:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, 30));

        comboProyecto.setModel(modeloCombo2);
        comboProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProyectoActionPerformed(evt);
            }
        });
        jPanel5.add(comboProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 100, 30));

        jLabel17.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jLabel17.setText("Seccion:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 30));

        comboSeccion.setModel(modeloCombo);
        comboSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSeccionActionPerformed(evt);
            }
        });
        jPanel5.add(comboSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 100, 30));

        jTable4.setBackground(new java.awt.Color(204, 255, 255));
        jTable4.setModel(modeloTabla3);
        jTable4.setGridColor(new java.awt.Color(102, 102, 102));
        jTable4.setSelectionForeground(new java.awt.Color(13, 51, 62));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 500, 190));

        actualizar2.setText("Button1");
        actualizar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualizar2MouseClicked(evt);
            }
        });
        jPanel5.add(actualizar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 50, 50));

        agregar2.setText("Button1");
        agregar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregar2MouseClicked(evt);
            }
        });
        jPanel5.add(agregar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 50, 50));

        modificar2.setText("Button1");
        modificar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificar2MouseClicked(evt);
            }
        });
        jPanel5.add(modificar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 50, 50));

        eliminar2.setText("Button1");
        eliminar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminar2MouseClicked(evt);
            }
        });
        jPanel5.add(eliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 50, 50));

        buscar2.setText("Button1");
        buscar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscar2MouseClicked(evt);
            }
        });
        jPanel5.add(buscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 50, 50));

        jTabbedPane1.addTab("Integrantes", jPanel5);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 400));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 670, 396));

        jPanel2.setBackground(new java.awt.Color(13, 51, 62));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(182, 189, 11));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(182, 189, 11));
        jLabel1.setText("Gestion de Proyectos");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 70));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 90));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarMouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigo(Integer.parseInt(codigo.getText()));
        objeto.setNombre(nombre.getText());
        objeto.setAnio(año.getText());
        objeto.setCosto(Double.parseDouble(costo.getText()));
        objeto.setFecha(fecha.getText());
        
        if (objeto.guardarProyecto()) {
            JOptionPane.showMessageDialog(this, "Datos Guardados");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }  
        int filas=modeloTabla.getRowCount();
                for (int i = 0; filas>i; i++) {
                    modeloTabla.removeRow(0);
                }
                    setFilas();
    }//GEN-LAST:event_agregarMouseClicked

    private void modificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarMouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigo(Integer.parseInt(codigo.getText()));
        objeto.setNombre(nombre.getText());
        objeto.setAnio(año.getText());
        objeto.setCosto(Double.parseDouble(costo.getText()));
        objeto.setFecha(fecha.getText());
        
        if (objeto.modificarProyecto()) {
            JOptionPane.showMessageDialog(this, "Datos Modificados");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        } 
        int filas=modeloTabla.getRowCount();
        for (int i = 0; filas>i; i++) {
            modeloTabla.removeRow(0);
        }
            setFilas();
    }//GEN-LAST:event_modificarMouseClicked

    private void eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarMouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigo(Integer.valueOf(codigo.getText()));
        int eliminar= JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar este proyecto", "Atencion", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (eliminar==0) {
           if (objeto.eliminarProyecto()) {
            JOptionPane.showMessageDialog(this, "Datos Eliminados");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }  
        }
        int filas=modeloTabla.getRowCount();
        for (int i = 0; filas>i; i++) {
            modeloTabla.removeRow(0);
        }
            setFilas();
    }//GEN-LAST:event_eliminarMouseClicked

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigo(Integer.valueOf(codigo.getText()));
        
        if (objeto.consultarProyecto()) {
            nombre.setText(""+objeto.getNombre());
            año.setText(""+objeto.getAnio());
            costo.setText(""+objeto.getCosto());
            fecha.setText(""+objeto.getFecha());
        } else {
            JOptionPane.showMessageDialog(this, "Datos No Encontrados");
        }  
        int filas=modeloTabla.getRowCount();
        for (int i = 0; filas>i; i++) {
            modeloTabla.removeRow(0);
        }
            setFilas();
    }//GEN-LAST:event_buscarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        codigo.setText(null);
        nombre.setText(null);
        año.setText(null);
        costo.setText(null);
        fecha.setText(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void actualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseClicked
        // TODO add your handling code here:
        int filas=modeloTabla.getRowCount();
        for (int i = 0; filas>i; i++) {
            modeloTabla.removeRow(0);
        }
            setFilas();
        
    }//GEN-LAST:event_actualizarMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        codigo.setText(String.valueOf(modeloTabla.getValueAt(jTable2.getSelectedRow(),0)));
        nombre.setText(String.valueOf(modeloTabla.getValueAt(jTable2.getSelectedRow(),1)));
        año.setText(String.valueOf(modeloTabla.getValueAt(jTable2.getSelectedRow(),2)));
        costo.setText(String.valueOf(modeloTabla.getValueAt(jTable2.getSelectedRow(),3)));
        fecha.setText(String.valueOf(modeloTabla.getValueAt(jTable2.getSelectedRow(),4)));
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void actualizar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizar1MouseClicked
        // TODO add your handling code here:
        int filas=modeloTabla2.getRowCount();
                for (int i = 0; filas>i; i++) {
                    modeloTabla2.removeRow(0);
                }
                    setFilas2();
    }//GEN-LAST:event_actualizar1MouseClicked

    private void agregar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregar1MouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigoS(Integer.parseInt(codigo1.getText()));
        objeto.setNombreS(nombre1.getText());
        
        if (objeto.guardarSeccion()) {
            JOptionPane.showMessageDialog(this, "Datos Guardados");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }  
        int filas=modeloTabla2.getRowCount();
                for (int i = 0; filas>i; i++) {
                    modeloTabla2.removeRow(0);
                }
                    setFilas2();
    }//GEN-LAST:event_agregar1MouseClicked

    private void modificar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificar1MouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigoS(Integer.parseInt(codigo1.getText()));
        objeto.setNombreS(nombre1.getText());
        
        if (objeto.modificarSeccion()) {
            JOptionPane.showMessageDialog(this, "Datos Modificados");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }  
        int filas=modeloTabla2.getRowCount();
                for (int i = 0; filas>i; i++) {
                    modeloTabla2.removeRow(0);
                }
                    setFilas2();
    }//GEN-LAST:event_modificar1MouseClicked

    private void eliminar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminar1MouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigoS(Integer.valueOf(codigo1.getText()));
        int eliminar= JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar esta seccion", "Atencion", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (eliminar==0) {
           if (objeto.eliminarSeccion()) {
            JOptionPane.showMessageDialog(this, "Datos Eliminados");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }  
        }
        int filas=modeloTabla2.getRowCount();
                for (int i = 0; filas>i; i++) {
                    modeloTabla2.removeRow(0);
                }
                    setFilas2();
    }//GEN-LAST:event_eliminar1MouseClicked

    private void buscar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscar1MouseClicked
        // TODO add your handling code here:
        MtoProyectos objeto = new MtoProyectos();
        objeto.setCodigoS(Integer.valueOf(codigo1.getText()));
        
        if (objeto.consultarSeccion()) {
            nombre1.setText(""+objeto.getNombreS());
            
        } else {
            JOptionPane.showMessageDialog(this, "Datos No Encontrados");
        }  
        int filas=modeloTabla2.getRowCount();
        for (int i = 0; filas>i; i++) {
            modeloTabla2.removeRow(0);
        }
            setFilas2();
    }//GEN-LAST:event_buscar1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        codigo1.setText(null);
        nombre1.setText(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        codigo1.setText(String.valueOf(modeloTabla2.getValueAt(jTable3.getSelectedRow(),0)));
        nombre1.setText(String.valueOf(modeloTabla2.getValueAt(jTable3.getSelectedRow(),1)));
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void actualizar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizar2MouseClicked
        // TODO add your handling code here:
        llenaComboBox();
    }//GEN-LAST:event_actualizar2MouseClicked

    private void agregar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_agregar2MouseClicked

    private void modificar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_modificar2MouseClicked

    private void eliminar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminar2MouseClicked

    private void buscar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar2MouseClicked
    //Variable de tipo "file"
    File fichero;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int resultado;

        CargarFoto ventana = new CargarFoto();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG", "jpg", "png");
        ventana.jfchCargarfoto.setFileFilter(filtro);
        resultado = ventana.jfchCargarfoto.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == resultado) {
            fichero = ventana.jfchCargarfoto.getSelectedFile();

            try {
                ImageIcon icon = new ImageIcon(fichero.toString());
                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
                foto.setIcon(icono);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error abriendo la imagen " + ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSeccionActionPerformed

    private void comboProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboProyectoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formulario1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formulario1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actualizar;
    private javax.swing.JLabel actualizar1;
    private javax.swing.JLabel actualizar2;
    private javax.swing.JLabel agregar;
    private javax.swing.JLabel agregar1;
    private javax.swing.JLabel agregar2;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField año;
    private javax.swing.JLabel buscar;
    private javax.swing.JLabel buscar1;
    private javax.swing.JLabel buscar2;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField codigo1;
    private javax.swing.JTextField codigo2;
    private javax.swing.JComboBox<String> comboProyecto;
    private javax.swing.JComboBox<String> comboSeccion;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField costo;
    private javax.swing.JLabel eliminar;
    private javax.swing.JLabel eliminar1;
    private javax.swing.JLabel eliminar2;
    private javax.swing.JTextField fecha;
    private javax.swing.JLabel foto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JLabel modificar;
    private javax.swing.JLabel modificar1;
    private javax.swing.JLabel modificar2;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField nombre1;
    private javax.swing.JTextField nombre2;
    // End of variables declaration//GEN-END:variables
}
