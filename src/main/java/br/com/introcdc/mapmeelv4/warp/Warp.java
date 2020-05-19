package br.com.introcdc.mapmeelv4.warp;

import br.com.introcdc.mapmeelv4.MapMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public enum Warp {
    L_1A("1A",
            "1A",
            -94.5, 44.0, -170.5, -40, 0,
            6000),
    L_1B("1B",
            "1B",
            46.5, 26.5, -40.5, 0, 0,
            18000),
    L_1C("1C",
            "1C",
            605.5, 67.0, 361.5, 90, 0,
            6000),
    L_1D("1D",
            "1D",
            260.5, 67.0, 788.5, 0, 0,
            18000),
    L_2A("2A",
            "2A",
            -18.5, 71.0, 240.5, 90, 0,
            6000),
    L_2B("2B",
            "2B",
            25.5, 69.0, -26.5, -90, 0,
            18000),
    L_2C("2C",
            "2C",
            771.4, 232.5, 671.5, 0, 0,
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
            -2.5, 114.5, 0,
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
            -457,
            109,
            249,
            148,
            5,
            6000),
    L_3D("3D",
            "3D",
            641.5,
            64.0,
            -626.5,
            150,
            -15,
            6000),
    L_4A("4A",
            "4A",
            -202.5,
            21.0,
            311.5,
            0,
            0,
            18000),
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

    EG_SKY("EG-SKY",
            "EG-SKY",
            -364.5, 91.0, -850.5, 90, 0,
            6000),
    EG_CASTLE("EG-CASTLE",
            "EG-CASTLE",
            508.5,
            102.5,
            470.5,
            0,
            0,
            6000),
    EG_CAVE("EG-CAVE",
            "EG-CAVE",
            -435.5,
            92.0,
            -850.5,
            0,
            0,
            18000),
    EG_FOREST("EG-FOREST",
            "EG-FOREST",
            321.5, 66.0, 418.5, 180, -7,
            6000),
    EG_HELL("EG-HELL",
            "EG-HELL",
            -104.5, 3.0, -814.5,
            0,
            0,
            6000),
    EG_UNDERWATER("EG-UNDERWATER",
            "EG-UNDERWATER",
            -229.5, 72.0, -198.5, -90, 30,
            6000),
    EG_WATER("EG-WATER",
            "EG-WATER",
            25.5, 27.0, -14.5, 120, -10,
            6000),

    FINAL_BOSS("FINAL-BOSS",
            "FINAL-BOSS",
            3018, 10, 3018, 0, -5,
            18000),

    LOBBY("Lobby",
            "world",
            -108,
            48.0,
            264.5,
            -140,
            0,
            6000),
    ;

    private Location location;
    private String name;
    private long time;
    private String world;
    private double X;
    private double Y;
    private double Z;
    private float Yaw;
    private float Pitch;

    Warp(String name, String world, double X, double Y, double Z, float Yaw, float Pitch, long time) {
        this.name = name;
        this.world = world;
        this.time = time;
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.Yaw = Yaw;
        this.Pitch = Pitch;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setup() {
        Warp.this.location = new Location(Bukkit.getWorld(world), X, Y, Z, Yaw, Pitch);
        Warp.this.updater();
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
                if (Warp.this.getLocation().getWorld() != null) {
                    if (Warp.this.getLocation().getWorld().getTime() != Warp.this.getTime()) {
                        // Warp.this.getLocation().getWorld().setTime(Warp.this.getTime());
                        Warp.this.getLocation().getWorld().setGameRuleValue("doDayCycle", "false");
                    }
                }
            }
        }.runTaskTimer(MapMain.getPlugin(), 0, 20);
    }

}
