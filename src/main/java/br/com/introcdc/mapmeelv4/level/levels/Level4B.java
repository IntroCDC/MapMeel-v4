package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:56
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level4B extends Level {

    public Level4B() {
        super("Jungle Village", new BlockId(Material.STAINED_GLASS_PANE, 13), Warp.L_4B, MapSound.MUSIC_SEVEN, null, new Location(Bukkit.getWorld("world"), 7, 53, -24, 286, 14), new LevelObjective[]{});
    }

}
