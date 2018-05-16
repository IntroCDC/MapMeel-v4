package br.com.introcdc.mapmeelv4.listeners.events;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:33
 */

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpongeEvents implements Listener {

    private static List<UUID> spongeDamage = new ArrayList<>();
    public static Vector vector = new Vector(0, 2, 0);

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (spongeDamage.contains(event.getEntity().getUniqueId())) {
                    event.setCancelled(true);
                    spongeDamage.remove(event.getEntity().getUniqueId());
                }
                if (event.getEntity().getLocation().clone().add(0, -1, 0).getBlock().getType().equals(Material.SPONGE)) {
                    event.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().clone().add(0, -1, -0).getBlock().getType().equals(Material.SPONGE) && event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
            if (!spongeDamage.contains(event.getPlayer().getUniqueId())) {
                spongeDamage.add(event.getPlayer().getUniqueId());
            }
            event.getPlayer().setVelocity(vector);
        }
    }

}
