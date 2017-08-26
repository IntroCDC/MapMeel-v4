package br.com.introcdc.mapmeelv4.events;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.UpdateType;

public class UpdateEventStarter extends MapUtils {

    private static void days() {
        for (int i = 1; i < 60; i++) {
            UpdateEvent event = new UpdateEvent(UpdateType.DAYS, i);
            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(event);
                }
            }.runTaskTimer(getPlugin(), 20, 24 * 60 * 60 * 20 * i);
        }
    }

    private static void hours() {
        for (int i = 1; i < 60; i++) {
            UpdateEvent event = new UpdateEvent(UpdateType.HOURS, i);
            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(event);
                }
            }.runTaskTimer(getPlugin(), 20, 60 * 60 * 20 * i);
        }
    }

    private static void minutes() {
        for (int i = 1; i < 60; i++) {
            UpdateEvent event = new UpdateEvent(UpdateType.MINUTES, i);
            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(event);
                }
            }.runTaskTimer(getPlugin(), 20, 60 * 20 * i);
        }
    }

    private static void seconds() {
        for (int i = 1; i < 60; i++) {
            UpdateEvent event = new UpdateEvent(UpdateType.SECONDS, i);
            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(event);
                }
            }.runTaskTimer(getPlugin(), 20, 20 * i);
        }
    }

    public static void startAll() {
        days();
        hours();
        minutes();
        seconds();
        tick();
    }

    private static void tick() {
        for (int i = 1; i < 60; i++) {
            UpdateEvent event = new UpdateEvent(UpdateType.TICK, i);
            new BukkitRunnable() {

                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(event);
                }
            }.runTaskTimer(getPlugin(), 20, i);
        }
    }

}
