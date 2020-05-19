package br.com.introcdc.mapmeelv4.listeners.entity;
/*
 * Written by IntroCDC, Bruno Coêlho at 28/04/2019 - 02:22
 */

import br.com.introcdc.mapmeelv4.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityEvents implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            return;
        }

        event.getDrops().clear();
        event.getDrops().add(new ItemBuilder(new ItemStack(Material.ACACIA_PLANKS)).setName("§oMoeda").toItem());
    }

}
