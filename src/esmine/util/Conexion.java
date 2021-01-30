
package esmine.util;

import esmine.escore.ControlExcepciones;
import esmine.escore.Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private final String db = "esmine", url = "jdbc:mysql://localhost/" + db;
    private final String user, pw;
    private Connection con;

    public Conexion(String user, String pw) {
        this.user = user;
        this.pw = pw;
        abrirConex();
    }
    
    private Connection abrirConex() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, pw);
            
        } catch (ClassNotFoundException | SQLException e) {
            ControlExcepciones.mostrarException(Core.plugin, new ControlExcepciones("No se ha podido establecer la conexión con la base de datos."));
        }
        
        return con;
    }
    
    public void cerrarConex() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            ControlExcepciones.mostrarException(Core.plugin, new ControlExcepciones("No se ha podido cerrar la conexión"));
        }
    }
    
    public Connection getCon()  {
        return con;
    }
    
}
