package br.com.introcdc.mapmeelv4.listeners.finallevel;
/*
 * Written by IntroCDC, Bruno Coêlho at 25/04/2019 - 16:00
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.listeners.music.MusicUpdaterEvents;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FinalLevelEvents implements Listener {

    public static boolean teleport = false;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (teleport) {
            return;
        }

        if (event.getTo().getWorld().getName().equalsIgnoreCase("FINAL-BOSS")) {
            if (event.getTo().getBlock().getType() == Material.END_PORTAL) {
                teleport = true;

                Level.getLevel(event.getTo().getWorld().getName()).setTempBackgroungMapSound(MapSound.MUSIC_TWELVE);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapUtils.playSound(player, MapSound.STOP);
                    MapUtils.playSound(player, MapSound.EFFECT_JOINING);
                    player.addPotionEffect(Level.blindness);
                }

                new BukkitRunnable() {
                    @Override
                    public void run() {

                        Location location = new Location(Bukkit.getWorld("FINAL-BOSS"), 5000, 102, 5000);

                        location.getWorld().setDifficulty(Difficulty.NORMAL);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.teleport(location);
                            MapUtils.playSound(player, MapSound.MUSIC_TWELVE, SoundCategory.AMBIENT);

                            // Temp Itens
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                            player.getInventory().addItem(new ItemStack(Material.BOW));
                            player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
                            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 64));
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS));

                        }

                        for (Entity entity : location.getWorld().getEntitiesByClass(Wither.class)) {
                            entity.remove();
                        }
                        location.getWorld().spawnEntity(location.clone().add(0, 30, 0), EntityType.WITHER);

                    }
                }.runTaskLater(MapMain.getPlugin(), 30);

            }
        }

    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {

        if (!event.getEntity().getType().equals(EntityType.WITHER)) {
            return;
        }
        if (!teleport) {
            return;
        }

        if (event.getEntity().getWorld().getName().equalsIgnoreCase("FINAL-BOSS")) {

            MusicUpdaterEvents.musicPause = true;

            for (Player player : Bukkit.getOnlinePlayers()) {
                MapUtils.playSound(player, MapSound.STOP);
            }

            Level level = Level.getLevel(event.getEntity().getWorld().getName());
            LevelObjective levelObjective = level.getObjectives().get("Mate o Chefão");

            levelObjective.spawnStar(true, null);

        }

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        event.blockList().clear();
    }

}
