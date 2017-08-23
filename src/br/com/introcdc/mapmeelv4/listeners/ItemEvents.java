package br.com.introcdc.mapmeelv4.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import br.com.introcdc.mapmeelv4.MapUtils;

public class ItemEvents extends MapUtils implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onPick(final PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.WOOD) && event.getItem().getItemStack().getDurability() == 4) {
            playSound(event.getPlayer(), "mapmeelv4.effect.coin");
            event.setCancelled(true);
            event.getItem().remove();
        }
    }

}
