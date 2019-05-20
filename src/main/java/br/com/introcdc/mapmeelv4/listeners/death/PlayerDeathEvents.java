package br.com.introcdc.mapmeelv4.listeners.death;
/*
 * Written by IntroCDC, Bruno Co�lho at 14/05/2018 - 15:59
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDeathEvents implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (!player.getGameMode().equals(GameMode.ADVENTURE)) {
                event.setCancelled(true);
                return;
            }

            if (Level.getLevel(player.getWorld().getName()) != null) {
                if (player.getHealth() - event.getDamage() <= 0 && !event.isCancelled()) {
                    event.setCancelled(true);
                    player.setHealth(20);

                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        MapUtils.sendTitle(player1, "�c�lVoc� morreu", "�f�oIndo para o Lobby...", 0, 40, 20);
                    }

                    Level.getLevel(player.getWorld().getName()).unloadLevel(null);
                }
            } else {
                if (player.getHealth() - event.getDamage() <= 0 && !event.isCancelled()) {
                    event.setCancelled(true);
                    player.setHealth(20);
                    player.teleport(Warp.LOBBY.getLocation());
                }
            }
        }
    }

}
