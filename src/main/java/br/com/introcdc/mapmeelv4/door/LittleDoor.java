package br.com.introcdc.mapmeelv4.door;
/*
 * Written by IntroCDC, Bruno Coêlho at 20/04/2019 - 19:20
 */

import br.com.introcdc.mapmeelv4.advancement.CustomAdvancement;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class LittleDoor {

    public static List<LittleDoor> allDoors = new ArrayList<>();

    private String name;
    private int needStars;
    private Location location;

    public LittleDoor(String name, int needStars, Location location) {
        this.name = name;
        this.needStars = needStars;
        this.location = location;
        allDoors.add(this);
    }

    public String getName() {
        return name;
    }

    public int getNeedStars() {
        return needStars;
    }

    public Location getLocation() {
        return location;
    }

    public void onEnterDoor() {
    }

    public static void loadDoors() {
        new LittleDoor("1A", 0, new Location(Bukkit.getWorlds().get(0), -44, 50, 72)) {
            public void onEnterDoor() {
                CustomAdvancement.OBJECTIVES.awardAllPlayers();
                CustomAdvancement.OBJECTIVES_FOLDER.awardAllPlayers();
            }
        };
        new LittleDoor("1B", 1, new Location(Bukkit.getWorlds().get(0), -43, 50, 33));
        new LittleDoor("1C", 3, new Location(Bukkit.getWorlds().get(0), -43, 50, -76));
        new LittleDoor("1D", 5, new Location(Bukkit.getWorlds().get(0), -44, 50, -115));
    }

}
