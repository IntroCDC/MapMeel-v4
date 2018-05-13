package br.com.introcdc.mapmeelv4.listeners.world;
/*
 * Written by IntroCDC, Bruno Coêlho at 26/08/2017 - 22:36
 */

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        if (event.getWorld().hasStorm()) {
            event.getWorld().setStorm(false);
        }
        event.getWorld().setWeatherDuration(999999999);
    }

    @EventHandler
    public void onWeather(WorldSaveEvent event) {
        if (event.getWorld().hasStorm()) {
            event.getWorld().setStorm(false);
        }
        event.getWorld().setWeatherDuration(999999999);
    }

}
