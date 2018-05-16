package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Co�lho at 14/05/2018 - 02:56
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level4C extends Level {

    public Level4C() {
        super("Pirate Island", new BlockId(Material.STAINED_GLASS_PANE, 14), Warp.L_4C, MapSound.MUSIC_ONE, null, new Location(Bukkit.getWorld("world"), 0, 52, -12, 11, 9), new LevelObjective[]{});
    }

}
