/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectolibreria;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Aaron Martinez
 */
public class ProyectoLibreria {

    String sql ="";
    String columna="";
   com.mysql.jdbc.Connection cn=null;
   
    /**
     * Conectando la base de datos con el programa
     * @param servidor 
     * @param base 
     * @param usuario 
     * @param contraseña 
     * @return 
     */
    
    
     public Connection Conectar(String servidor,String base,String usuario,String contraseña){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=(com.mysql.jdbc.Connection) DriverManager.getConnection(servidor+"/"+base,usuario,contraseña);
            System.out.println("Exito en la conexión");
        }catch(Exception conectar){
            System.out.println(conectar.getMessage());
        }
        return cn; 
    }
    /**
     * Para buscar mediante consultas
     * @param parametro 
     * @param nombreTabla 
     * @param ID 
     * @param primaryKey 
     */
    public void buscar(String parametro,String nombreTabla,String ID,String primaryKey){
        
        
            sql="Select "+parametro+" from "+nombreTabla+" where "+ID+"='"+primaryKey+"'";
            mostrardatos(6);
        
    }
    /**
     * Muestra la información de la base de datos
     * @param columna 
     */
    public void mostrardatos(int columna){
        String[] datos = new String[columna];
        try {
            Statement stm = (Statement) cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
             for(int x=0;x<+datos.length;x++) {
                 datos[x]=rs.getString(x+1);
                     System.out.println(datos[x]);
}
            }
            
    }
           
         catch (SQLException show) {
            System.out.println(show.getMessage());
        }
}
    /**
     * Para insertar los datos en la tabla.
     * @param tabla 
     * @param nomColumna 
     * @param valores 
     */
      public void Insertar(String tabla,String nomColumna,String valores){
      
    java.sql.PreparedStatement ps;
        try {
            ps = cn.prepareStatement("Insert into "+tabla+"("+nomColumna+") values ("+valores+")");
            ps.execute();
            System.out.println("Exito en la insercción.");
            
        } catch (Exception insertar) {
            System.out.println(insertar.getMessage());
        }
    }
      /**
       * Borra un registro de la tabla de la base de datos
       * @param nombreTabla
       * @param primary_Key 
       * @param id 
       */
      public void Borrar(String nombreTabla,String primary_Key,int id){
      
        try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("Delete from "+nombreTabla+" where "+primary_Key+"='"+id+"'");
            ps.executeUpdate();
            mostrardatos(6);
            System.out.println("Borrado confirmado");
        } catch (SQLException delete) {
            System.out.println(delete.getMessage());
        }
      }
      /**
       * Actualiza los valores de un registro
       * @param tabla 
       * @param parametrosActualizar 
       * @param datosNuevos 
       * @param primary_Key 
       * @param id 
       */
      public void Actualizar(String tabla,String parametrosActualizar,String datosNuevos,String primary_Key,int id){
           try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("Update "+tabla+" set "+parametrosActualizar+" = '"+datosNuevos+"' where "+primary_Key+"='"+id+"'");
            ps.executeUpdate();
            mostrardatos(6);
               System.out.println("Actualizado");
        } catch (SQLException actualize) {
               System.out.println(actualize.getMessage());
        }
      }
     /**
      * Cierra la conexion con la base de datos
      */
      public void CloseConnection(){
            try {
            cn.close();
        } catch (SQLException closeConnection) {
            System.out.println(closeConnection.getMessage());
        }
      }
      
}




