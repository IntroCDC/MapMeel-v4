package br.com.introcdc.mapmeelv4.listeners.music;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:39
 */

import br.com.introcdc.mapmeelv4.advancement.CustomAdvancement;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.listeners.join.JoinEvents;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
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
        if (!event.getType().equals(UpdateType.SECONDS) && !event.getType().equals(UpdateType.TICK)) {
            return;
        }
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
                            MapUtils.sendTitle(player, "§f", "§f§oEntrando no castelo...",
                                    10, 30, 10);
                            inside.add(player.getUniqueId());
                            CustomAdvancement.CASTLE.awardPlayer(player);
                        }
                    }

                } else {
                    int tick = MapUtils.RANDOM.nextInt(60);
                    if (inside.contains(player.getUniqueId())) {
                        MapUtils.playSound(player, MapSound.STOP);

                        inside.remove(player.getUniqueId());
                        MapUtils.sendTitle(player, "§f", "§f§oSaindo do castelo...", 10, 30, 10);
                    } else if (event.getTimes() == tick) {
                        if (MapUtils.RANDOM.nextBoolean() && player.getLocation().distance(Warp.LOBBY.getLocation()) > 5) {
                            MapSound sound = (MapUtils.RANDOM.nextBoolean() ?
                                    MapSound.EFFECT_BIRD_ONE : MapSound.EFFECT_BIRD_TWO);
                            float tom = Float.parseFloat((MapUtils.RANDOM.nextBoolean() ? "1" : "0") + "." +
                                    MapUtils.RANDOM.nextInt(10));
                            MapUtils.playSound(player, sound, SoundCategory.NEUTRAL, tom);
                        }
                    }
                }

            } else {

                if (!event.getType().equals(UpdateType.SECONDS)) {
                    continue;
                }
                if (Level.getLevel(player.getWorld().getName()) != null) {
                    MapUtils.playSound(player, Level.getLevel(player.getWorld().getName()).getBackgroundMapSound(), SoundCategory.AMBIENT);
                }

            }
        }
    }

}
