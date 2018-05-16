package br.com.introcdc.mapmeelv4.listeners.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:34
 */

import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class LevelLoaderEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.WOOD_BUTTON) && Level.currentLevel != null && event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
                event.setCancelled(true);
                Level.currentLevel.loadLevel();
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getWorld().getName().equalsIgnoreCase("world")) {
            if (event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
                for (Level level : Level.getLeveis().values()) {
                    if (event.getTo().getBlock().getType().equals(level.getBlockId().getMaterial()) && event.getTo().getBlock().getData() == level.getBlockId().getData()) {
                        level.joinPortal();
                        Level.currentLevel = level;
                    }
                }
            }
        }
    }

}
