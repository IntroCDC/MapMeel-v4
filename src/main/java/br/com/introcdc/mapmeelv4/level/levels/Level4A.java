package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:55
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level4A extends Level {

    public Level4A() {
        super("Oriental Village", new BlockId(Material.STAINED_GLASS_PANE, 12), Warp.L_4A, MapSound.MUSIC_SIX, null, new Location(Bukkit.getWorld("world"), -5, 52, -31, 205, 11), new LevelObjective[]{});
    }

}
