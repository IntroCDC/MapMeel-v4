package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:56
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

public class Level4B extends Level {

    public Level4B() {
        super(
                "Jungle Village",
                Material.GREEN_STAINED_GLASS_PANE,
                Warp.L_4B,
                MapSound.MUSIC_SEVEN,
                null,
                new Location(Bukkit.getWorld("world"), 7, 53, -24, 286, 14),
                new LevelObjective[]{
                        new LevelObjective("A Plantação das Plantações", MapUtils.getLocation("4B", 67, 126, 309, -136, 15), true),
                        new LevelObjective("O Templo da Floresta", MapUtils.getLocation("4B", 150, 69, 171, -198, 90), true),
                        new LevelObjective("A Estrela Engarrafada", MapUtils.getLocation("4B", 276, 63, 207, -183, 90), true),
                        new LevelObjective("A Casa Solitária", MapUtils.getLocation("4B", 125, 130, 211, -359, 90), true),
                        new LevelObjective("A Caverna do Extremo Positivo", MapUtils.getLocation("4B", 287, 65, 359, -264, 12), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("4B", 261, 63, 193, -359, 90), false, true)
                });
    }

}
