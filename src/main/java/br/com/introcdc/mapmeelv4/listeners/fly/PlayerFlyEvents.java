package br.com.introcdc.mapmeelv4.listeners.fly;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 03:46
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.listeners.events.SpongeEvents;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerFlyEvents implements Listener {

    private final Set<UUID> doubleJumping = new HashSet<>();

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
            }
        }.runTaskLater(MapMain.getPlugin(), 5);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.ADVENTURE) {

            boolean falling = player.getFallDistance() > 1;

            if (player.isOnGround() && !falling) {
                player.setAllowFlight(true);
            } else if (!player.isOnGround() && falling) {
                player.setAllowFlight(false);
            }
        }
    }

    @EventHandler
    public void onFly(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.ADVENTURE) {
            if (event.isFlying()) {
                event.setCancelled(true);

                SpongeEvents.addFallProtection(player);

                player.setAllowFlight(false);
                player.setVelocity(player.getLocation().getDirection().multiply(1.5));
            }
        }
    }

}
