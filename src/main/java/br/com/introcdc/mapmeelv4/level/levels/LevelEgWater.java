package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 15:49
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelEgWater extends Level {

    public LevelEgWater() {
        super("The Water Temple",
                Material.GLASS_PANE,
                Material.SEA_LANTERN,
                Warp.EG_WATER,
                MapSound.MUSIC_EIGHT,
                null,
                new Location(Bukkit.getWorld("world"), 134, 53, -226, -168, 10),
                new LevelObjective[]{
                        new LevelObjective("No Centro do Templo", new Location(Bukkit.getWorld("EG-WATER"), -91, 91, -14, 200, 90), true)
                });
    }

}
