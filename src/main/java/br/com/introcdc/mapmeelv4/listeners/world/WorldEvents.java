package br.com.introcdc.mapmeelv4.listeners.world;
/*
 * Written by IntroCDC, Bruno Coêlho at 26/08/2017 - 22:36
 */

import br.com.introcdc.mapmeelv4.commands.CommandObjectives;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;

public class WorldEvents implements Listener {

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeather(WorldLoadEvent event) {
        World world = event.getWorld();

        if (world.hasStorm()) {
            world.setStorm(false);
        }
        world.setWeatherDuration(999999999);
    }

    @EventHandler
    public void onWeather(WorldSaveEvent event) {
        World world = event.getWorld();

        if (world.hasStorm()) {
            world.setStorm(false);
        }
        world.setWeatherDuration(999999999);
    }

    @EventHandler
    public void onTarget(EntityTargetEvent event) {
        if (!(event.getTarget() instanceof Player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() != null & event.getInventory().getHolder() instanceof CommandObjectives.ObjectivesHolder) {
            event.setCancelled(true);
        }
    }

}
