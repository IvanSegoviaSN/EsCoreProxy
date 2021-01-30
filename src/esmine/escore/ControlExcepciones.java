
package esmine.escore;

import net.md_5.bungee.api.ChatColor;


public class ControlExcepciones extends Exception {

    public ControlExcepciones(String string) {
        super(string);
    }
    
    public static void mostrarException(Core p, ControlExcepciones c) {
        p.getProxy().getConsole().sendMessage(ChatColor.RED + "ERROR EN EL CORE: " + c.getMessage());
    }
}
