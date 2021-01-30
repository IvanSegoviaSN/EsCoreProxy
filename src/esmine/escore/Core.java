
package esmine.escore;

import java.io.File;
import java.io.IOException;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


public class Core extends Plugin {
    
    @Override
    public void onEnable() {
        Core.plugin = this;
        cargarListeners();
    }

    @Override
    public void onDisable() {
    }
    
    public Configuration obtenerConfig() {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            ControlExcepciones.mostrarException(this, new ControlExcepciones("No se ha podido cargar el fichero config.yml"));
            return null;
        }
    }
    
    private void cargarListeners() {
        getProxy().getPluginManager().registerListener(this, (Listener) new CoreEventos(this));
        getProxy().getPluginManager().registerListener(this, (Listener) new MoverJugador(this));
    }
    
    public static Core plugin;
    public Core getPlugin() {
        return plugin;
    }
}
