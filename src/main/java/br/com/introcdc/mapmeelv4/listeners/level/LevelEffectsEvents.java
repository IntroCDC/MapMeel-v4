package br.com.introcdc.mapmeelv4.listeners.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:43
 */

import br.com.introcdc.mapmeelv4.enums.UpdateType;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LevelEffectsEvents implements Listener {

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 3) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (Level.getLevel(player.getWorld().getName()) != null && Level.getLevel(player.getWorld().getName()).getPotionEffect() != null && player.getGameMode().equals(GameMode.ADVENTURE)) {
                    player.addPotionEffect(Level.getLevel(player.getWorld().getName()).getPotionEffect());
                }
            });
        }
    }

}
