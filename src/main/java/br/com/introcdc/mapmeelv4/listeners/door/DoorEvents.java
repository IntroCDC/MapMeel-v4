package br.com.introcdc.mapmeelv4.listeners.door;
/*
 * Written by IntroCDC, Bruno Coêlho at 20/04/2019 - 17:35
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.door.Door;
import br.com.introcdc.mapmeelv4.door.LittleDoor;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DoorEvents implements Listener {

    public static boolean cooldown = false;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (event.getAction().toString().contains("BLOCK") && event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.IRON_BARS)) {
                for (Door door : Door.allDoors) {
                    if (!(event.getClickedBlock().getLocation().distance(door.getLocation()) < 5)) {
                        continue;
                    }
                    if (cooldown) {
                        return;
                    }

                    cooldown = true;
                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> cooldown = false, 100);

                    if (Level.stars < door.getNeedStars()) {
                        door.openDoor(false);
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            MapUtils.playSound(player, MapSound.EFFECT_BAD_MESSAGE);
                            MapUtils.sendTitle(player, "§4§l§oPortão Trancado!",
                                    "§c§oVocê precisa ter " + door.getNeedStars() +
                                            " estrelas! (falta: " + (door.getNeedStars() - Level.stars) + ")",
                                    10, 40, 20);
                        }
                    } else {
                        door.openDoor(true);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            MapUtils.playSound(player, MapSound.EFFECT_JOINING);
                            MapUtils.sendTitle(player, "§b§l§oAbrindo...",
                                    "§f§oAbrindo portão com o poder das estrelas...",
                                    5, 40, 20);
                        }

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    MapUtils.playSound(player, MapSound.EFFECT_OPEN_DOOR);
                                    MapUtils.sendTitle(player, "§a§lPortão Aberto!",
                                            "§f§oPortão aberto com o poder das estrelas!",
                                            5, 40, 20);
                                }
                            }
                        }.runTaskLater(MapMain.getPlugin(), 100);
                    }
                }
            }
        }

        if (event.getAction().equals(Action.PHYSICAL) && event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            for (LittleDoor littleDoor : LittleDoor.allDoors) {
                if (littleDoor.getLocation().distance(event.getPlayer().getLocation()) < 5) {

                    if (Level.stars < littleDoor.getNeedStars()) {
                        event.setCancelled(true);

                        if (cooldown) {
                            return;
                        }
                        cooldown = true;
                        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> cooldown = false, 100);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            MapUtils.playSound(player, MapSound.EFFECT_BAD_MESSAGE);
                            MapUtils.sendTitle(player, "§4§lPorta Trancada!",
                                    "§c§oVocê precisa ter " + littleDoor.getNeedStars() +
                                            " estrelas! (falta: " + (littleDoor.getNeedStars() - Level.stars) + ")",
                                    10, 40, 20);
                        }
                    } else {
                        littleDoor.onEnterDoor();
                    }

                }
            }
        }

    }

}
