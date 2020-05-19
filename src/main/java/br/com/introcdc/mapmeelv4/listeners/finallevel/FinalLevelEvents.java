package br.com.introcdc.mapmeelv4.listeners.finallevel;
/*
 * Written by IntroCDC, Bruno Coêlho at 25/04/2019 - 16:00
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.boss.BossBattle;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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
                Bukkit.broadcastMessage(MapUtils.PREFIX + "§oPrepare-se... você tem 10 segundos para se preparar!");

                new BukkitRunnable() {
                    @Override
                    public void run() {

                        Location location = new Location(Bukkit.getWorld("FINAL-BOSS"), 5000, 102, 5000);

                        location.getWorld().setDifficulty(Difficulty.NORMAL);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.teleport(location);
                            MapUtils.playSound(player, MapSound.MUSIC_TWELVE, SoundCategory.AMBIENT);
                        }

                        for (Entity entity : location.getWorld().getEntitiesByClass(Wither.class)) {
                            entity.remove();
                        }
                        BossBattle.getInstance().start();

                    }
                }.runTaskLater(MapMain.getPlugin(), 10 * 20);

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
            BossBattle.getInstance().spawnBase();
        }

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        event.blockList().clear();
    }

    @EventHandler
    public void onExplodeBlock(BlockExplodeEvent event) {
        event.blockList().clear();
    }

}
