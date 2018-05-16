package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:40
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level1D extends Level {

    public Level1D() {
        super("Watch Big City", new BlockId(Material.STAINED_GLASS_PANE, 3), Warp.L_1D, MapSound.MUSIC_SIX, null, new Location(Bukkit.getWorld("world"), -41, 52, -91, -153, 9), new LevelObjective[]{new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("1D", 85, 31, 811, -71, 90))});
    }

}
