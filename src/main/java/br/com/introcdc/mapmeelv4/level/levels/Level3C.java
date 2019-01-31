package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:51
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

public class Level3C extends Level {

    public Level3C() {
        super(
                "The Big Tower",
                new BlockId(Material.STAINED_GLASS_PANE, 10),
                Warp.L_3C,
                MapSound.MUSIC_SIX,
                null,
                new Location(Bukkit.getWorld("world"), 36, 51, 37, 21, 12),
                new LevelObjective[]{
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("3C", -482, 104, 240, 138, 89))
                });
    }

}
