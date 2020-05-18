package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 15:16
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelEgSky extends Level {

    public LevelEgSky() {
        super("The Sky",
                Material.GLASS_PANE,
                Material.QUARTZ_PILLAR,
                Warp.EG_SKY,
                MapSound.MUSIC_TWO,
                null,
                new Location(Bukkit.getWorld("world"), 162, 120, 259, 100, 10),
                new LevelObjective[]{
                        new LevelObjective("No Centro Da Ilha", new Location(Bukkit.getWorld("EG-SKY"), -423, 93, -850, 89, 90), true)
                });
    }

}
