package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:58
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level4D extends Level {

    public Level4D() {
        super("Desert Village", new BlockId(Material.STAINED_GLASS_PANE, 15), Warp.L_4D, MapSound.MUSIC_FOUR, null, new Location(Bukkit.getWorld("world"), 1, 124, -18, 17, 10), new LevelObjective[]{});
    }

}
