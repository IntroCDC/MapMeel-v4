package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:17
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

public class Level1C extends Level {

    public Level1C() {
        super("The Villager Village", new BlockId(Material.STAINED_GLASS_PANE, 2), Warp.L_1C, MapSound.MUSIC_TWO, null, new Location(Bukkit.getWorld("world"), -35, 52, -45, -294, 12), new LevelObjective[]{new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("1C", 553, 66, 362, 354, 89))});
    }

}
