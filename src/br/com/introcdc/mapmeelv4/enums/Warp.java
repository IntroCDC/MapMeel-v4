package br.com.introcdc.mapmeelv4.enums;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv4.MapMain;

public enum Warp {
    L_1A("1A",
         "1A",
         -94.5,
         44.0,
         -170.5,
         -40,
         0,
         6000),
    L_1B("1B",
         "1B",
         46.5,
         26.5,
         -40.5,
         0,
         0,
         18000),
    L_1C("1C",
         "1C",
         605.5,
         67.0,
         361.5,
         90,
         0,
         6000),
    L_1D("1D",
         "1D",
         260.5,
         67.0,
         788.5,
         0,
         0,
         18000),
    L_2A("2A",
         "2A",
         -18.5,
         71.0,
         240.5,
         90,
         0,
         6000),
    L_2B("2B",
         "2B",
         25.5,
         69.0,
         -26.5,
         -90,
         0,
         18000),
    L_2C("2C",
         "2C",
         -202.5,
         21.0,
         311.5,
         0,
         0,
         18000),
    L_2D("2D",
         "2D",
         43.5,
         70.0,
         28.5,
         -90,
         0,
         6000),
    L_3A("3A",
         "3A",
         -97.5,
         64.0,
         83.5,
         180,
         0,
         6000),
    L_3B("3B",
         "3B",
         14.5,
         168.0,
         393.5,
         -90,
         0,
         6000),
    L_3C("3C",
         "3C",
         -330.5,
         26.0,
         293.5,
         90,
         0,
         6000),
    L_3D("3D",
         "3D",
         135.5,
         5.0,
         8.5,
         90,
         0,
         6000),
    L_4A("4A",
         "4A",
         -1048.5,
         55.0,
         558.5,
         0,
         0,
         6000),
    L_4B("4B",
         "4B",
         273.5,
         64.0,
         199.5,
         0,
         0,
         6000),
    L_4C("4C",
         "4C",
         41.5,
         86.0,
         38.5,
         180,
         0,
         6000),
    L_4D("4D",
         "4D",
         -435.5,
         16.0,
         -850.5,
         0,
         0,
         6000),
    LOBBY("Lobby",
          "world",
          -108,
          48.0,
          264.5,
          -140,
          0,
          6000),;

    private Location location;
    private final String name;
    private final long time;
    private final String world;

    Warp(final String name, final String world, final double X, final double Y, final double Z, final float Yaw, final float Pitch, final long time) {
        this.name = name;
        this.world = world;
        this.time = time;
        new BukkitRunnable() {
            @Override
            public void run() {
                Warp.this.location = new Location(Bukkit.getWorld(world), X, Y, Z, Yaw, Pitch);
                Warp.this.updater();
            }
        }.runTaskLater(MapMain.getPlugin(), 20);
    }

    public Location getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public long getTime() {
        return this.time;
    }

    public String getWorld() {
        return this.world;
    }

    public void updater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Warp.this.getLocation().getWorld().setTime(Warp.this.getTime());
            }
        }.runTaskTimer(MapMain.getPlugin(), 0, 20);
    }

}
