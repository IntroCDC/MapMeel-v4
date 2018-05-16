package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:48
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level3B extends Level {

    public Level3B() {
        super("Sky Ships", new BlockId(Material.STAINED_GLASS_PANE, 9), Warp.L_3B, MapSound.MUSIC_SEVEN, null, new Location(Bukkit.getWorld("world"), -5, 69, 36, 113, 9), new LevelObjective[]{});
    }

}
