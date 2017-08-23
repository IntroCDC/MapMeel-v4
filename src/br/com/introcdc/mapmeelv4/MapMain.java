package br.com.introcdc.mapmeelv4;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.introcdc.mapmeelv4.bases.MapClassGetter;
import br.com.introcdc.mapmeelv4.events.UpdateEventStarter;

public class MapMain extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return MapMain.plugin;
    }

    @Override
    public void onEnable() {
        MapMain.plugin = this;
        Bukkit.getPluginManager().registerEvents(new ScoreManager(), MapMain.getPlugin());
        MapClassGetter.loadListeners("br.com.introcdc.mapmeelv4.histories", MapMain.class);
        MapClassGetter.loadListeners("br.com.introcdc.mapmeelv4.listeners", MapMain.class);
        MapClassGetter.loadCommands("br.com.introcdc.mapmeelv4.commands", MapMain.class);
        MapUtils.loadWorlds();
        UpdateEventStarter.startAll();
    }

}
