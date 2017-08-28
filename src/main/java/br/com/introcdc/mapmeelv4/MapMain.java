package br.com.introcdc.mapmeelv4;

import br.com.introcdc.mapmeelv4.enums.Warp;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.introcdc.mapmeelv4.bases.MapClassGetter;
import br.com.introcdc.mapmeelv4.events.UpdateEventStarter;
import org.bukkit.scheduler.BukkitRunnable;

public class MapMain extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return MapMain.plugin;
    }

    @Override
    public void onEnable() {
        MapMain.plugin = this;
        MapUtils.loadWorlds();
        for (Warp warp : Warp.values()) {
            warp.setup();
        }
        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("mobGriefing", "false");
        }
        Level.loadLeveis();
        UpdateEventStarter.startAll();
        Bukkit.getPluginManager().registerEvents(new ScoreManager(), MapMain.getPlugin());
        MapClassGetter.loadListeners("br.com.introcdc.mapmeelv4.histories", MapMain.class);
        MapClassGetter.loadListeners("br.com.introcdc.mapmeelv4.listeners", MapMain.class);
        MapClassGetter.loadCommands("br.com.introcdc.mapmeelv4.commands", MapMain.class);
    }

}
