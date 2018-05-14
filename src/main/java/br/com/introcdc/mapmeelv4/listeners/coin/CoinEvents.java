package br.com.introcdc.mapmeelv4.listeners.coin;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:35
 */

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.CoinType;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CoinEvents implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onPick(PlayerPickupItemEvent event) {
        for (CoinType coinType : CoinType.values()) {
            if (event.getItem().getItemStack().getType().equals(coinType.getMaterial()) && event.getItem().getItemStack().getDurability() == coinType.getBytes()) {
                event.setCancelled(true);
                event.getItem().remove();
                MapProfile.getProfile(event.getPlayer().getName()).addTempCoin(coinType);
                new BukkitRunnable() {
                    int times = 0;

                    @Override
                    public void run() {
                        times++;
                        if (times <= coinType.getCoins()) {
                            MapUtils.playSound(event.getPlayer(), MapSound.EFFECT_COIN, -2);
                        } else {
                            cancel();
                        }
                    }
                }.runTaskTimer(MapUtils.getPlugin(), 0, 2);
            }
        }
    }

}
