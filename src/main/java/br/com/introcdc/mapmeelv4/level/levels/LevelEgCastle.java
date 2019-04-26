package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 14:24
 */

import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelEgCastle extends Level {

    public LevelEgCastle() {
        super("The Castle",
                Material.GLASS_PANE,
                Warp.EG_CASTLE,
                MapSound.CASTLE_MUSIC,
                null,
                new Location(Bukkit.getWorld("world"), 2, 103, 36, -248, 9),
                new LevelObjective[]{
                        new LevelObjective("A Torre do Castelo Secundário", new Location(Bukkit.getWorld("EG-CASTLE"), 449, 114, 505, 180, 90), true)
                });
    }

}
