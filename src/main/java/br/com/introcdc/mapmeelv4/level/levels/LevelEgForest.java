package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 15:36
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelEgForest extends Level {

    public LevelEgForest() {
        super("The Forest",
                Material.GLASS_PANE,
                Warp.EG_FOREST,
                MapSound.MUSIC_SIX,
                null,
                new Location(Bukkit.getWorld("world"), -96, 57, -276, 88, 4),
                new LevelObjective[]{
                        new LevelObjective("A Sala Escondida", new Location(Bukkit.getWorld("EG-FOREST"), 341, 85, 379, -172, 90), true)
                });
    }

}
