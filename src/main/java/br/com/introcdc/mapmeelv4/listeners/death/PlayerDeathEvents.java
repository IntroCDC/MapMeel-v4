package br.com.introcdc.mapmeelv4.listeners.death;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 15:59
 */

import br.com.introcdc.mapmeelv4.boss.BossBattle;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDeathEvents implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (player.getName().equalsIgnoreCase("Base64")) {
            return;
        }

        if (!player.getGameMode().equals(GameMode.ADVENTURE)) {
            event.setCancelled(true);
            return;
        }
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) || event.isCancelled()) {
            return;
        }

        if (Level.getLevel(player.getWorld().getName()) != null) {
            if (!(player.getHealth() - event.getDamage() <= 0) || event.isCancelled()) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getType().equals(Material.TOTEM_OF_UNDYING) ||
                    player.getInventory().getItemInOffHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
                return;
            }

            event.setCancelled(true);
            player.setHealth(20);

            for (Player player1 : Bukkit.getOnlinePlayers()) {
                MapUtils.sendTitle(player1, "§c§lVocê morreu",
                        "§f§o" + event.getEntity().getName() + " morreu... Voltando para o Lobby...",
                        0, 40, 20);
            }
            if (BossBattle.getInstance().bossBar != null) {
                BossBattle.getInstance().bossBar.removeAll();
            }
            if (BossBattle.getInstance().baseNpc != null && BossBattle.getInstance().baseNpc.isSpawned()) {
                BossBattle.getInstance().baseNpc.destroy();
            }
            if (BossBattle.getInstance().wither != null && !BossBattle.getInstance().wither.isDead()) {
                BossBattle.getInstance().wither.remove();
            }

            Level.getLevel(player.getWorld().getName()).unloadLevel(null, null);
        } else {
            if (player.getHealth() - event.getDamage() <= 0 && !event.isCancelled()) {
                event.setCancelled(true);
                player.setHealth(20);
                player.teleport(Warp.LOBBY.getLocation());
            }
        }
    }

}
