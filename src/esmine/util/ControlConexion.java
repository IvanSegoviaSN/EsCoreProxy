
package esmine.util;

import esmine.escore.ControlExcepciones;
import esmine.escore.Core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.md_5.bungee.config.Configuration;


public class ControlConexion {
    private Connection con; ResultSet rs;
    
    public void insertarDatos(String ip, String nombre) {
        this.con = crearConexion();
        try {
            if (comprobarDatos(nombre)) {
                cambiarEstado(nombre, true);
            } else {
                PreparedStatement ps = con.prepareStatement(insertar);
                ps.setString(1, ip);
                ps.setString(2, nombre);
                ps.setBoolean(3, true);

                ps.executeUpdate();
            }
            
        } catch (SQLException e) {
            ControlExcepciones.mostrarException(Core.plugin, new ControlExcepciones("Error en insertarDatos()"));
        }
    }
    
    public boolean comprobarDatos(String nombre) {
        this.con = crearConexion();
        try {
            PreparedStatement ps = con.prepareStatement(consultar);
            ps.setString(1, nombre);
            
            rs = ps.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            ControlExcepciones.mostrarException(Core.plugin, new ControlExcepciones("Error en comprobarDatos()"));
            return true;
        }
    }
    
    public void cambiarEstado(String nombre, Boolean enLinea) {
        this.con = crearConexion();
        try {
            PreparedStatement ps = con.prepareStatement(cambiarEstado);
            ps.setBoolean(1, enLinea);
            ps.setString(2, nombre);

            ps.executeUpdate();

        } catch (SQLException e) {
            ControlExcepciones.mostrarException(Core.plugin, new ControlExcepciones("Error en cambiarEstado()"));
        }
    }
    

    private final String insertar = "INSERT INTO cp_usuarios (ip, nombre, enlinea) VALUES (?, ?, ?)";
    private final String cambiarEstado = "UPDATE cp_usuarios SET enlinea=? WHERE nombre=?";
    private final String consultar = "SELECT nombre FROM cp_usuarios WHERE nombre=?";
    
    private Connection crearConexion() {
        Configuration conf = Core.plugin.obtenerConfig();
        Conexion c = new Conexion(conf.getString("esmine.usuario"), conf.getString("esmine.pass"));
        
        return c.getCon();
    }

}
