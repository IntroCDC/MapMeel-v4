package br.com.introcdc.mapmeelv4;

import br.com.introcdc.mapmeelv4.classes.MapClassGetter;
import br.com.introcdc.mapmeelv4.events.UpdateEventStarter;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.scoreboard.ScoreManager;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Parrot;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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
        Location DEFAULT = new Location(Bukkit.getWorld("world"), 0, 80, 0);
        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("mobGriefing", "false");
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doEntityDrops", "false");
            world.setGameRuleValue("doFireTick", "false");
            world.setGameRuleValue("doMobLoot", "false");
            world.setGameRuleValue("doTileDrops", "false");
            world.setGameRuleValue("doWeatherCycle", "false");
            world.setGameRuleValue("keepInventory", "true");
            world.setGameRuleValue("doWeatherCycle", "false");
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Parrot || entity instanceof Item) {
                    entity.remove();
                }
            }
            if (world.getName().equalsIgnoreCase("world")) {
                for (int i = 0; i < 1000; i++) {
                    world.spawnEntity(DEFAULT.clone().add((MapUtils.random.nextInt(500) - 250), 0, (MapUtils.random.nextInt(500) - 250)), EntityType.PARROT);
                }
            }
        }
        MapClassGetter.loadListeners("br.com.introcdc.mapmeelv4.histories", MapMain.class);
        MapClassGetter.loadListeners("br.com.introcdc.mapmeelv4.listeners", MapMain.class);
        MapClassGetter.loadCommands("br.com.introcdc.mapmeelv4.commands", MapMain.class);
        Bukkit.getPluginManager().registerEvents(new ScoreManager(), MapMain.getPlugin());
        UpdateEventStarter.startAll();
        Level.loadLeveis();
    }

}
