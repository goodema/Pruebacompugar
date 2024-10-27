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
   }
       
    return conex;
   }
public void Disconnect() throws SQLException
{
    conex.close();
}
     
}
