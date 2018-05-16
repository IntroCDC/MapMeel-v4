package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:43
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

public class Level2A extends Level {

    public Level2A() {
        super("Snow Temple", new BlockId(Material.STAINED_GLASS_PANE, 4), Warp.L_2A, MapSound.MUSIC_FIVE, null, new Location(Bukkit.getWorld("world"), 5, 51, -74, -254, 7), new LevelObjective[]{new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("2A", 41, 6, 264, -270, 90))});
    }

}
