package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:52
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level2C extends Level {

    public Level2C() {
        super("Underwater City", new BlockId(Material.STAINED_GLASS_PANE, 6), Warp.L_2C, MapSound.MUSIC_THREE, null, new Location(Bukkit.getWorld("world"), 31, 50, -70, -17, 3), new LevelObjective[]{});
    }

}
