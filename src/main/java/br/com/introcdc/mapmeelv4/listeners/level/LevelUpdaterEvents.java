package br.com.introcdc.mapmeelv4.listeners.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 13:56
 */

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.UpdateType;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LevelUpdaterEvents implements Listener {

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 1) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (Level.getLevel(player.getWorld().getName()) != null) {
                    try {
                        MapUtils.sendActionBar(player, "Moedas: §a" + MapProfile.getProfile(player.getName()).getTempCoins());
                    } catch (Exception ignored) {
                    }
                }
            });
        }
    }

}
