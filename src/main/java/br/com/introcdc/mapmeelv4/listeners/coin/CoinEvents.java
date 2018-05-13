package br.com.introcdc.mapmeelv4.listeners.coin;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:35
 */

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class CoinEvents implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onPick(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.WOOD) && event.getItem().getItemStack().getDurability() == 4) {
            MapUtils.playSound(event.getPlayer(), MapSound.EFFECT_COIN, -2);
            event.setCancelled(true);
            event.getItem().remove();
        }
    }

}
