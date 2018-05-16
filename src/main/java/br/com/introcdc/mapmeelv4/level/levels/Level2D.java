package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 01:57
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level2D extends Level {

    public Level2D() {
        super("Desert City", new BlockId(Material.STAINED_GLASS_PANE, 7), Warp.L_2D, MapSound.MUSIC_FOUR, null, new Location(Bukkit.getWorld("world"), 32, 51, -81, -176, 9), new LevelObjective[]{});
    }

}
