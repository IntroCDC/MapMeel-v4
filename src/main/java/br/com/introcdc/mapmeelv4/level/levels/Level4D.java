package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:58
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level4D extends Level {

    public Level4D() {
        super(
                "Desert Village",
                Material.BLACK_STAINED_GLASS_PANE,
                Material.SANDSTONE_WALL,
                Warp.L_4D,
                MapSound.MUSIC_FOUR,
                null,
                new Location(Bukkit.getWorld("world"), -3, 124, -24, -293, 9),
                new LevelObjective[]{
                        new LevelObjective("A Vizinhança", MapUtils.getLocation("4D", -229, 26, -762, 96, 19), true),
                        new LevelObjective("A Liderança do Cavalo", MapUtils.getLocation("4D", -457, 27, -783, 179, 15), true),
                        new LevelObjective("Piramide Vizinha", MapUtils.getLocation("4D", -484, 17, -915, 87, 90), true),
                        new LevelObjective("A Piramide Torre", MapUtils.getLocation("4D", -213, 65, -981, 0, 90), true),
                        new LevelObjective("O Templo Da Piramide Distante", MapUtils.getLocation("4D", -742, 5, -567, 271, 90), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("4D", -467, 15, -822, -5, 90), false, true)
                });
    }

}
