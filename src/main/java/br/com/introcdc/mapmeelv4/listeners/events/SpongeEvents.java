package br.com.introcdc.mapmeelv4.listeners.events;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:33
 */

import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SpongeEvents implements Listener {

    private static final Set<UUID> fallProtection = new HashSet<>();

    @EventHandler
    public void eachSecond(UpdateEvent event) {
        if (event.getType() == UpdateType.SECONDS && event.getTimes() == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.isOnGround() && hasFallProtection(player)) {
                    removeFallProtection(player);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (hasFallProtection(player)) {
                    event.setCancelled(true);
                    removeFallProtection(player);
                }
                if (hasSpongeUnderneath(player.getLocation())) {
                    event.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (hasSpongeUnderneath(event.getTo())) {
            addFallProtection(player);
            player.setVelocity(new Vector(0, 2, 0));
        }
    }

    private static boolean hasSpongeUnderneath(final Location location) {
        Block groundBlock = location.getBlock().getRelative(BlockFace.DOWN);
        Block undergroundBlock = groundBlock.getRelative(BlockFace.DOWN);
        return groundBlock.getType() == Material.SPONGE || undergroundBlock.getType() == Material.SPONGE;
    }


    public static boolean hasFallProtection(final Player player) {
        return fallProtection.contains(player.getUniqueId());
    }

    public static void addFallProtection(final Player player) {
        fallProtection.add(player.getUniqueId());
    }

    public static void removeFallProtection(final Player player) {
        fallProtection.remove(player.getUniqueId());
    }

}
