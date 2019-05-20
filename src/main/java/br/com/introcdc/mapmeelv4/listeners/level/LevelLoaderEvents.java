package br.com.introcdc.mapmeelv4.listeners.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:34
 */

import br.com.introcdc.mapmeelv4.level.Level;
import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static org.bukkit.NamespacedKey.minecraft;

public class LevelLoaderEvents implements Listener {

    private static final MaterialSetTag BUTTONS = new MaterialSetTag(minecraft("buttons"))
            .endsWith("_BUTTON");

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock() != null && BUTTONS.isTagged(event.getClickedBlock()) && Level.currentLevel != null && event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
                event.setCancelled(true);
                Level.currentLevel.loadLevel();
            }
        }

        if (event.getClickedBlock() != null && event.getClickedBlock().getType().toString().contains("CHEST") && event.getAction().toString().contains("RIGHT")) {
            event.setCancelled(true);
        }
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.FARMLAND)) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getWorld().getName().equalsIgnoreCase("world")) {
            if (event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
                for (Level level : Level.getLeveis().values()) {
                    if (event.getPlayer().getLocation().distance(level.getPortalSpec()) < 15) {
                        if (event.getTo().getBlock().getType() == level.getMaterial()) {
                            Level.currentLevel = level;
                            level.joinPortal();
                        }
                    }
                }
            }
        }
    }

}
