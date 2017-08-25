package br.com.introcdc.mapmeelv4.listeners;
/*
 * Writter by IntroCDC, Bruno Coêlho at 25/08/2017 - 07:35
 */

import br.com.introcdc.mapmeelv4.MapUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvents extends MapUtils implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (MoveEvent.spongeDamage.contains(event.getEntity().getUniqueId())) {
                    event.setCancelled(true);
                    MoveEvent.spongeDamage.remove(event.getEntity().getUniqueId());
                }
                if (event.getEntity().getLocation().clone().add(0, -1, 0).getBlock().getType().equals(Material.SPONGE)) {
                    event.setCancelled(true);
                }
            }
        }

    }

}
