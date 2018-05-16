package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:46
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

public class Level2B extends Level {

    public Level2B() {
        super("Santa Claus Temple", new BlockId(Material.STAINED_GLASS_PANE, 5), Warp.L_2B, MapSound.MUSIC_FIVE, null, new Location(Bukkit.getWorld("world"), -4, 68, -72, -244, 5), new LevelObjective[]{new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("2B", 26, 68, -33, -180, 90))});
    }

}
