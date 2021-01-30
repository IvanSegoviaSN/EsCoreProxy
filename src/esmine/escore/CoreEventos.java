
package esmine.escore;

import esmine.escore.whitelist.ControlForo;
import esmine.util.ControlConexion;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class CoreEventos implements Listener {
    public Core plugin;

    public CoreEventos(Core plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void alEntrar(PostLoginEvent eve){
        ProxiedPlayer p = eve.getPlayer();
        // ControlForo cf = new ControlForo();
        // cf.alEntrar(p);
        ControlConexion cm = new ControlConexion();
        cm.insertarDatos(destriparSocket(p.getSocketAddress()), p.getDisplayName().toLowerCase());
    }
    
    @EventHandler 
    public void alSalir(PlayerDisconnectEvent eve){
        ProxiedPlayer p = eve.getPlayer();
        ControlConexion cm = new ControlConexion();
        cm.cambiarEstado(p.getDisplayName().toLowerCase(), false);
    }
    
    private String destriparSocket(SocketAddress s) {
        InetSocketAddress iSa = (InetSocketAddress) s;
        return iSa.getAddress().toString().replace('/', ' ').trim();
    }
}
