package br.com.introcdc.mapmeelv4.door;
/*
 * Written by IntroCDC, Bruno Coêlho at 20/04/2019 - 17:14
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.advancement.CustomAdvancement;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Door {

    public static List<Door> allDoors = new ArrayList<>();

    private String name;
    private int needStars;
    private boolean opened;
    private Location location;

    public Door(String name, int needStars, Location location) {
        this.name = name;
        this.needStars = needStars;
        this.location = location;
        allDoors.add(this);

        openDoor(Level.stars >= needStars);
    }

    public String getName() {
        return name;
    }

    public int getNeedStars() {
        return needStars;
    }

    public boolean isOpened() {
        return opened;
    }

    public Location getLocation() {
        return location;
    }

    public void openDoor(boolean open) {
        this.opened = open;

        for (int z = -4; z <= 4; z++) {
            for (int y = 0; y <= 10; y++) {
                Location loc = this.location.clone().add(0, y, z);

                if (open) {

                    int to = z + 5;
                    int Y = y;

                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {

                        loc.getWorld().playEffect(loc, Effect.WITHER_BREAK_BLOCK, 5);

                        if (loc.getBlock().getType().equals(Material.IRON_BARS)) {
                            loc.getBlock().setType(Material.AIR);
                        }

                        if (to * Y == 90) {
                            onOpenDoor();
                        }

                    }, to * y);

                } else {
                    if (loc.getBlock().getType().equals(Material.AIR)) {
                        loc.getBlock().setType(Material.IRON_BARS);
                    }
                }

            }
        }
    }

    public void onOpenDoor() {

    }

    public static void loadDoors() {
        new Door("2", 10, new Location(Bukkit.getWorlds().get(0), 41, 53, -77)) {
            public void onOpenDoor() {
                CustomAdvancement.SECOND_DOOR.awardAllPlayers();
                CustomAdvancement.SECOND_DOOR_FOLDER.awardAllPlayers();
            }
        };
        new Door("3", 25, new Location(Bukkit.getWorlds().get(0), 41, 53, 33)) {
            public void onOpenDoor() {
                CustomAdvancement.THIRD_DOOR.awardAllPlayers();
                CustomAdvancement.THIRD_DOOR_FOLDER.awardAllPlayers();
            }
        };
        new Door("4", 50, new Location(Bukkit.getWorlds().get(0), -30, 53, -22)) {
            public void onOpenDoor() {
                CustomAdvancement.FOURTH_DOOR.awardAllPlayers();
                CustomAdvancement.FOURTH_DOOR_FOLDER.awardAllPlayers();
            }
        };
        new Door("Final", 70, new Location(Bukkit.getWorlds().get(0), 8, 122, -22)) {
            public void onOpenDoor() {
                CustomAdvancement.BOSS_DOOR.awardAllPlayers();
            }
        };
        new Door("120 Estrelas", 120, new Location(Bukkit.getWorlds().get(0), -321, 76, -149)) {
            public void onOpenDoor() {
                CustomAdvancement.FIND_THE_GATE.awardAllPlayers();
            }
        };
    }

}
