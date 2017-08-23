package br.com.introcdc.mapmeelv4.listeners;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:48
 */

import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.WOOD_BUTTON) && Level.currentLevel != null) {
                event.setCancelled(true);
                Level.currentLevel.loadLevel();
            }
        }
    }
}
