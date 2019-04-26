package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:48
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

public class Level3B extends Level {

    public Level3B() {
        super(
                "Sky Ships",
                Material.CYAN_STAINED_GLASS_PANE,
                Warp.L_3B,
                MapSound.MUSIC_SEVEN,
                null,
                new Location(Bukkit.getWorld("world"), -5, 69, 36, 113, 9),
                new LevelObjective[]{
                        new LevelObjective("Jardim do Hotel", MapUtils.getLocation("3B", 164, 177, 493, 76, 16), true),
                        new LevelObjective("O Hotel Concorrente", MapUtils.getLocation("3B", 74, 100, 701, 175, 12), true),
                        new LevelObjective("A Tenda mais alta", MapUtils.getLocation("3B", 6, 233, 574, 8, 45), true),
                        new LevelObjective("A Grande Vela Suspensa", MapUtils.getLocation("3B", 33, 115, 459, 304, 90), true),
                        new LevelObjective("O Quarto Presidencial", MapUtils.getLocation("3B", 54, 190, 555, 272, 90), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("3B", 14, 167, 394, 180, 90))
                });
    }

}
