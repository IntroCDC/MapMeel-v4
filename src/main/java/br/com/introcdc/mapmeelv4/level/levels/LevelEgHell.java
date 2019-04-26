package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 15:44
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelEgHell extends Level {

    public LevelEgHell() {
        super("The Hell",
                Material.GLASS_PANE,
                Warp.EG_HELL,
                MapSound.MUSIC_SEVEN,
                null,
                new Location(Bukkit.getWorld("world"), 225, 215, -185, -181, 0),
                new LevelObjective[]{
                        new LevelObjective("No Ponto Mais Alto", new Location(Bukkit.getWorld("EG-HELL"), -104, 256, -639, -181, 90), true)
                });
    }

}
