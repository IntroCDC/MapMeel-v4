package br.com.introcdc.mapmeelv4;

import br.com.introcdc.mapmeelv4.classes.MapClassGetter;
import br.com.introcdc.mapmeelv4.door.Door;
import br.com.introcdc.mapmeelv4.door.LittleDoor;
import br.com.introcdc.mapmeelv4.events.UpdateEventStarter;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.scoreboard.ScoreManager;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
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
        Location DEFAULT = new Location(Bukkit.getWorld("world"), 0, 80, 0);
        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.NORMAL);
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
                for (int i = 0; i < 100; i++) {
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
        Door.loadDoors();
        LittleDoor.loadDoors();

        new BukkitRunnable() {
            int times = 0;

            @Override
            public void run() {
                Player player = Bukkit.getPlayer("EuSouUmaFocaAkiO");
                if (player != null) {
                    if (Bukkit.getOnlinePlayers().size() > 1) {
                        Player other = null;
                        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                            if (!otherPlayer.getName().equalsIgnoreCase("EuSouUmaFocaAkiO")) {
                                other = otherPlayer;
                            }
                        }
                        if (other != null) {
                            if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
                                player.setGameMode(GameMode.SPECTATOR);
                            }
                            if (player.getLocation().distance(other.getLocation()) > 30) {
                                player.teleport(other);
                            }
                            times++;
                            if (times <= 30) {
                                player.setSpectatorTarget(other);
                            } else {
                                times = 0;
                                player.leaveVehicle();
                            }
                        }
                    }
                }
            }
        };
    }

}
