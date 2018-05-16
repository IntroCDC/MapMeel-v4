package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:47
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level3A extends Level {

    public Level3A() {
        super("Sky Lands", new BlockId(Material.STAINED_GLASS_PANE, 8), Warp.L_3A, MapSound.MUSIC_TWO, null, new Location(Bukkit.getWorld("world"), 4, 51, 36, 113, 8), new LevelObjective[]{});
    }

}
