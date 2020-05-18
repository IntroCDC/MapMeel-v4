package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 01:57
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level2D extends Level {

    public Level2D() {
        super(
                "Desert City",
                Material.GRAY_STAINED_GLASS_PANE,
                Material.SAND,
                Warp.L_2D,
                MapSound.MUSIC_FOUR,
                null,
                new Location(Bukkit.getWorld("world"), 32, 51, -81, -176, 9),
                new LevelObjective[]{
                        new LevelObjective("O menor reino de todos", MapUtils.getLocation("2D", 19, 107, -5, -182, 24), true),
                        new LevelObjective("Torre principal do reino principal", MapUtils.getLocation("2D", -99, 135, 31, -89, 90), true),
                        new LevelObjective("Castelo de gelo do Deserto", MapUtils.getLocation("2D", -96, 198, -204, -1, -1), true),
                        new LevelObjective("Grande precipicio do fim", MapUtils.getLocation("2D", -298, 125, 473, -244, 48), true),
                        new LevelObjective("O Spa do Deserto", MapUtils.getLocation("2D", -193, 112, 190, -312, 31), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("2D", -117, 109, 35, 0, 90), false, true)
                });
    }

}
