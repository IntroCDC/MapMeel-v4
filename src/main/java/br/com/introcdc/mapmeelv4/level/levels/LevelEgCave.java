package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Co�lho at 21/04/2019 - 15:28
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.level.levels.objectives.ObjectiveEgCave;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelEgCave extends Level {

    public LevelEgCave() {
        super("The Cave",
                Material.GLASS_PANE,
                Material.COBBLESTONE,
                Warp.EG_CAVE,
                MapSound.MUSIC_SEVEN,
                null,
                new Location(Bukkit.getWorld("world"), 329, 53, -35, 0, 6),
                new LevelObjective[]{
                        new ObjectiveEgCave()
                });
    }

}
