package br.com.introcdc.mapmeelv4.listeners.star;
/*
 * Written by IntroCDC, Bruno Coêlho at 16/05/2018 - 02:52
 */

import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class StarEvents implements Listener {

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        if (event.getItem() != null && event.getItem().getItemStack() != null && event.getItem().getItemStack().getItemMeta() != null && event.getItem().getItemStack().getItemMeta().getDisplayName() != null) {
            if (event.getItem().getItemStack().getType().equals(Material.GOLD_BLOCK)) {
                if (Level.getLevel(event.getPlayer().getWorld().getName()) != null) {
                    if (Level.getLevel(event.getPlayer().getWorld().getName()).getObjectives().containsKey(event.getItem().getItemStack().getItemMeta().getDisplayName())) {
                        event.setCancelled(true);
                        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            return;
                        }
                        event.getItem().remove();
                        Level.getLevel(event.getPlayer().getWorld().getName()).unloadLevel(Level.getLevel(event.getPlayer().getWorld().getName()).getObjectives().get(event.getItem().getItemStack().getItemMeta().getDisplayName()), !event.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Colete 100 Moedas"));
                    }
                }
            }
        }
    }

}
