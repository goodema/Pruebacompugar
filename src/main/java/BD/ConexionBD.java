package BD;

import java.sql.*;
import javax.swing.JOptionPane;
public class ConexionBD {
  
Connection conex=null;
String url,u,p,e;
   public void setConnectionValues(String db_url, String user, String password, String ErrorMessage)
   {
       url=db_url;
       u=user;
       p=password;
       e=ErrorMessage;
   }
   public Connection getConection()
   {
       try{
           Class.forName("com.mysql.jdbc.Driver");
           conex=DriverManager.getConnection("jdbc:mysql://"+url,u,p);
           if(conex != null)
       {
           System.out.print("Conexion exitosa");
       }
       else
       {
           System.out.print("Falló la Conexion");
           if(e!=null)
           {
           JOptionPane.showMessageDialog(null,"");
           }else
           {
           JOptionPane.showMessageDialog(null,"Error de Conexion");
           }
       }
   }catch (ClassNotFoundException | SQLException e)
   {
       System.out.print(e.getMessage()+" Error al conectar");
       System.out.print(e);
   }
       
    return conex;
   }
public void Disconnect() throws SQLException
{
    conex.close();
}

public static void main(String [] args)
{
    ConexionBD c= new ConexionBD();
    c.setConnectionValues("localhost/pruebacompugar", "root", "","");
    c.getConection();
}

     
   public boolean insertarDatos(String[] data) {
    String sql = "INSERT INTO clientesinternet (Nombre, Telefono,Direccion, IP, Id_antena) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conex.prepareStatement(sql)) {
        // Usar los datos del arreglo getData en las posiciones correctas
        stmt.setString(1, data[0]); // Dirección
        stmt.setString(2, data[1]); // Nombre
        stmt.setString(3, data[2]); // Teléfono
        stmt.setString(4, data[3]); // IP
        stmt.setString(5, data[4]); // Antena
        
        // Ejecutar la actualización en la base de datos
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"Registro Existoso");
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar datos: " + e.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        return false;
    }
   }
}


