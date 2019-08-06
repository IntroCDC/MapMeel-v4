package br.com.introcdc.mapmeelv4.listeners.music;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:39
 */

import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.listeners.join.JoinEvents;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MusicUpdaterEvents implements Listener {

    public static List<UUID> inside = new ArrayList<>();

    public static boolean musicPause = false;

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.SECONDS) || event.getType().equals(UpdateType.TICK)) {
            for (Player player : Bukkit.getOnlinePlayers()) {

                if (!player.getGameMode().equals(GameMode.ADVENTURE)) {
                    continue;
                }
                if (musicPause) {
                    continue;
                }

                if (player.getWorld().getName().equalsIgnoreCase("world")) {

                    if (Level.currentLevel != null || JoinEvents.buttonPlay.contains(player.getUniqueId())) {
                        continue;
                    }

                    Location location = player.getLocation().clone();
                    location.setY(20);
                    if (location.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {

                        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 1) {
                            MapUtils.playSound(player, MapSound.CASTLE_MUSIC, SoundCategory.AMBIENT);

                            if (!inside.contains(player.getUniqueId())) {
                                MapUtils.sendTitle(player, "§f", "§f§oEntrando no castelo...", 10, 30, 10);
                                inside.add(player.getUniqueId());
                            }
                        }

                    } else {
                        int tick = MapUtils.random.nextInt(60);
                        if (inside.contains(player.getUniqueId())) {
                            MapUtils.playSound(player, MapSound.STOP);

                            inside.remove(player.getUniqueId());
                            MapUtils.sendTitle(player, "§f", "§f§oSaindo do castelo...", 10, 30, 10);
                        } else if (event.getTimes() == tick) {
                            if (MapUtils.random.nextBoolean()) {
                                MapSound sound = (MapUtils.random.nextBoolean() ? MapSound.EFFECT_BIRD_ONE : MapSound.EFFECT_BIRD_TWO);
                                float tom = Float.parseFloat((MapUtils.random.nextBoolean() ? "1" : "0") + "." + MapUtils.random.nextInt(10));
                                MapUtils.playSound(player, sound, SoundCategory.NEUTRAL, tom);
                            }
                        }
                    }

                } else {

                    if (event.getType().equals(UpdateType.SECONDS)) {
                        if (Level.getLevel(player.getWorld().getName()) != null) {
                            MapUtils.playSound(player, Level.getLevel(player.getWorld().getName()).getBackgroundMapSound(), SoundCategory.AMBIENT);
                        }
                    }

                }
            }
        }
    }

}
