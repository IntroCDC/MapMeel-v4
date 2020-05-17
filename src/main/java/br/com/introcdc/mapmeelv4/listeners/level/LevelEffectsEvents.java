package br.com.introcdc.mapmeelv4.listeners.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:43
 */

import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

public class LevelEffectsEvents implements Listener {

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 3) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                World world = player.getWorld();

                if (Level.getLevel(world.getName()) != null && Level.getLevel(world.getName()).getPotionEffect() != null && player.getGameMode().equals(GameMode.ADVENTURE)) {
                    PotionEffect potionEffect = new PotionEffect(Level.getLevel(world.getName()).getPotionEffect().getType(), 1000, Level.getLevel(world.getName()).getPotionEffect().getAmplifier());
                    player.removePotionEffect(potionEffect.getType());
                    player.addPotionEffect(potionEffect);
                }
            });
        }
    }

}
