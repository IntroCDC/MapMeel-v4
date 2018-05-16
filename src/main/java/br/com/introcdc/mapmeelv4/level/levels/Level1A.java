package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:26
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

public class Level1A extends Level {

    public Level1A() {
        super("Mountain Village", new BlockId(Material.STAINED_GLASS_PANE), Warp.L_1A, MapSound.MUSIC_ONE, null, new Location(Bukkit.getWorld("world"), -33, 52, 48, -335, 11), new LevelObjective[]{new LevelObjective("A Casa do Final da Vila", MapUtils.getLocation("1A", 43, 74, -24, -181, 7)), new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("1A", -7, 41, -120, -6, 90))});
    }

}
