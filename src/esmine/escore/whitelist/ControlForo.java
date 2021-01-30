
package esmine.escore.whitelist;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;


public class ControlForo {
    
    public enum ESTADO {
        NO_REGISTRADO("&c¡Este usuario no está registrado en el foro! \n\n &fRevisa que tu nombre de usuario sea idéntico al del foro."),
        ERROR_NOMBRE("");
        
        private final String texto;
        
        ESTADO(String t) {
            this.texto = t;
        }

        public String getTexto() {
            return texto;
        }
    }
    
    public void alEntrar(ProxiedPlayer p){
        if (!(p.getDisplayName().equalsIgnoreCase("Player"))) {
            p.disconnect(ChatColor.translateAlternateColorCodes('&', ESTADO.NO_REGISTRADO.getTexto()));
        }
    }
}
