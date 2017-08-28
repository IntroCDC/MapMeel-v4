package br.com.introcdc.mapmeelv4.listeners;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 17:17
 */

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import br.com.introcdc.mapmeelv4.enums.UpdateType;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MusicEvents extends MapUtils implements Listener {

    public static List<UUID> inside = new ArrayList<>();

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.MINUTES) || event.getType().equals(UpdateType.SECONDS)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getWorld().getName().equalsIgnoreCase("world")) {
                    Location location = player.getLocation().clone();
                    location.setY(20);
                    if (event.getTimes() == MapSound.CASTLE_MUSIC.getMinutes()) {
                        if (location.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
                            if (inside.contains(player.getUniqueId())) {
                                if (event.getType().equals(UpdateType.MINUTES)) {
                                    playSound(player, MapSound.STOP);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            playSound(player, MapSound.CASTLE_MUSIC);
                                        }
                                    }.runTaskLater(getPlugin(), 30);
                                }
                            } else {
                                if (event.getType().equals(UpdateType.SECONDS)) {
                                    inside.add(player.getUniqueId());
                                    playSound(player, MapSound.STOP);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            playSound(player, MapSound.CASTLE_MUSIC);
                                        }
                                    }.runTaskLater(getPlugin(), 30);
                                }
                            }
                        } else {
                            if (inside.contains(player.getUniqueId())) {
                                if (event.getType().equals(UpdateType.SECONDS)) {
                                    inside.remove(player.getUniqueId());
                                    playSound(player, MapSound.STOP);
                                }
                            } else if (event.getType().equals(UpdateType.SECONDS)) {
                                playSound(player, (random.nextBoolean() ? MapSound.EFFECT_BIRD_ONE : MapSound.EFFECT_BIRD_TWO), Double.parseDouble((random.nextBoolean() ? "1" : "0") + "." + random.nextInt(10)));
                            }
                        }
                    }
                } else {
                    if (event.getType().equals(UpdateType.MINUTES)) {
                        if (Level.getLevel(player.getWorld().getName()) != null && event.getTimes() == Level.getLevel(player.getWorld().getName()).getBackgroundMapSound().getMinutes()) {
                            playSound(player, MapSound.STOP);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (Level.getLevel(player.getWorld().getName()) != null && event.getTimes() == Level.getLevel(player.getWorld().getName()).getBackgroundMapSound().getMinutes()) {
                                        playSound(player, Level.getLevel(player.getWorld().getName()).getBackgroundMapSound());
                                    }
                                }
                            }.runTaskLater(getPlugin(), 30);
                        }
                    }
                }
            }
        }
    }

}
