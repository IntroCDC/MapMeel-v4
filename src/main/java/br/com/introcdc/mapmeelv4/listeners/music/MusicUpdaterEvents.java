package br.com.introcdc.mapmeelv4.listeners.music;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:39
 */

import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MusicUpdaterEvents implements Listener {

    public static List<UUID> inside = new ArrayList<>();

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.MINUTES) || event.getType().equals(UpdateType.SECONDS) || event.getType().equals(UpdateType.TICK)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getWorld().getName().equalsIgnoreCase("world")) {
                    Location location = player.getLocation().clone();
                    location.setY(20);
                    if (location.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
                        if (event.getTimes() == MapSound.CASTLE_MUSIC.getMinutes()) {
                            if (inside.contains(player.getUniqueId())) {
                                if (event.getType().equals(UpdateType.MINUTES)) {
                                    MapUtils.playSound(player, MapSound.STOP);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            MapUtils.playSound(player, MapSound.CASTLE_MUSIC);
                                        }
                                    }.runTaskLater(MapUtils.getPlugin(), 30);
                                }
                            } else {
                                if (event.getType().equals(UpdateType.SECONDS)) {
                                    MapUtils.sendTitle(player, "§f", "§f§oEntrando no castelo...", 10, 30, 10);
                                    inside.add(player.getUniqueId());
                                    MapUtils.playSound(player, MapSound.STOP);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            MapUtils.playSound(player, MapSound.CASTLE_MUSIC);
                                        }
                                    }.runTaskLater(MapUtils.getPlugin(), 30);
                                }
                            }
                        }
                    } else {
                        int tick = MapUtils.random.nextInt(50);
                        if (inside.contains(player.getUniqueId())) {
                            if (event.getType().equals(UpdateType.SECONDS)) {
                                MapUtils.sendTitle(player, "§f", "§f§oSaindo do castelo...", 10, 30, 10);
                                inside.remove(player.getUniqueId());
                                MapUtils.playSound(player, MapSound.STOP);
                            }
                        } else if (event.getType().equals(UpdateType.TICK) && event.getTimes() == tick) {
                            if (MapUtils.random.nextBoolean()) {
                                MapUtils.playSound(player, (MapUtils.random.nextBoolean() ? MapSound.EFFECT_BIRD_ONE : MapSound.EFFECT_BIRD_TWO), Float.parseFloat((MapUtils.random.nextBoolean() ? "1" : "0") + "." + MapUtils.random.nextInt(10)));
                            }
                        }
                    }
                } else {
                    if (event.getType().equals(UpdateType.MINUTES)) {
                        if (Level.getLevel(player.getWorld().getName()) != null && event.getTimes() == Level.getLevel(player.getWorld().getName()).getBackgroundMapSound().getMinutes()) {
                            MapUtils.playSound(player, MapSound.STOP);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (Level.getLevel(player.getWorld().getName()) != null && event.getTimes() == Level.getLevel(player.getWorld().getName()).getBackgroundMapSound().getMinutes()) {
                                        MapUtils.playSound(player, Level.getLevel(player.getWorld().getName()).getBackgroundMapSound());
                                    }
                                }
                            }.runTaskLater(MapUtils.getPlugin(), 30);
                        }
                    }
                }
            }
        }
    }

}
