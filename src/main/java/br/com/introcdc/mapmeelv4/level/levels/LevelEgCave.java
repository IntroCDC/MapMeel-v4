package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 15:28
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelEgCave extends Level {

    public LevelEgCave() {
        super("The Cave",
                Material.GLASS_PANE,
                Warp.EG_CAVE,
                MapSound.MUSIC_SEVEN,
                null,
                new Location(Bukkit.getWorld("world"), 329, 53, -35, 0, 6),
                new LevelObjective[]{
                        new LevelObjective("Perdida na Caverna", new Location(Bukkit.getWorld("EG-CAVE"), -342, 99, -727, 88, 90), true)
                });
    }

}
