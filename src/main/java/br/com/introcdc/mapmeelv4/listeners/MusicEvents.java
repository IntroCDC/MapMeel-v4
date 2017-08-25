package br.com.introcdc.mapmeelv4.listeners;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 17:17
 */

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import br.com.introcdc.mapmeelv4.enums.UpdateType;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class MusicEvents extends MapUtils implements Listener {

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.MINUTES)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Level.getLevel(player.getWorld().getName()) != null && event.getTimes() == Level.getLevel(player.getWorld().getName()).getBackgroundMapSound().getMinutes()) {
                    new BukkitRunnable() {
                        int times = 0;

                        @Override
                        public void run() {
                            times++;
                            if (times <= 20) {
                                playSound(player, MapSound.STOP);
                            } else {
                                cancel();
                            }
                        }
                    }.runTaskTimer(getPlugin(), 0, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            playSound(player, Level.getLevel(player.getWorld().getName()).getBackgroundMapSound());
                        }
                    }.runTaskLater(getPlugin(), 30);
                }
            }
        }
    }

}
