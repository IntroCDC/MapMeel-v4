package br.com.introcdc.mapmeelv4.listeners.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 13:56
 */

import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.listeners.coin.CoinEvents;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
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
                        MapUtils.sendActionBar(player, "Total de Moedas: §b§l" + CoinEvents.coins + " §f- Moedas: §e" + (CoinEvents.coins - ((CoinEvents.redCoins * 2) + (CoinEvents.blueCoins * 5))) + " §f- Corações: §c" + CoinEvents.redCoins + " §f- Cubos: §9" + CoinEvents.blueCoins);
                    } catch (Exception ignored) {
                    }
                }
            });
        }
    }

}
