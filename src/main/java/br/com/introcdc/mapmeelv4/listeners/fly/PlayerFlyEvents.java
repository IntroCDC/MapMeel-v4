package br.com.introcdc.mapmeelv4.listeners.fly;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 03:46
 */

import br.com.introcdc.mapmeelv4.MapMain;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerFlyEvents implements Listener {

    private static List<UUID> cooldownToFly = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getPlayer().setAllowFlight(true);
                event.getPlayer().setFlying(false);
            }
        }.runTaskLater(MapMain.getPlugin(), 5);
    }

    @EventHandler
    public void onGameMode(PlayerGameModeChangeEvent event) {
        if (event.getNewGameMode().equals(GameMode.SURVIVAL)) {
            event.setCancelled(true);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getNewGameMode().equals(GameMode.SURVIVAL)) {
                    event.getPlayer().setGameMode(GameMode.ADVENTURE);
                }
                event.getPlayer().setAllowFlight(true);
                event.getPlayer().setFlying(false);
            }
        }.runTaskLater(MapMain.getPlugin(), 5);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().isOnGround()) {
            event.getPlayer().setAllowFlight(true);
            cooldownToFly.remove(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onFly(PlayerToggleFlightEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
            if (event.isFlying()) {
                event.setCancelled(true);
                if (!cooldownToFly.contains(event.getPlayer().getUniqueId())) {
                    event.getPlayer().setAllowFlight(false);
                    event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(3));
                    cooldownToFly.add(event.getPlayer().getUniqueId());
                }
            }
        }
    }

}
