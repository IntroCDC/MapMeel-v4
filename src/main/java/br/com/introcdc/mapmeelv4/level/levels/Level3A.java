package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:47
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level3A extends Level {

    public Level3A() {
        super(
                "Sky Lands",
                Material.LIGHT_GRAY_STAINED_GLASS_PANE,
                Material.WHITE_WOOL,
                Warp.L_3A,
                MapSound.MUSIC_TWO,
                null,
                new Location(Bukkit.getWorld("world"), 4, 51, 36, 113, 8),
                new LevelObjective[]{
                        new LevelObjective("O Grande Navio",
                                MapUtils.getLocation("3A", -56, 118, 3, -359, 13), true),
                        new LevelObjective("O Final do Alto Parkour",
                                MapUtils.getLocation("3A", 104, 150, -27, -240, 46), true),
                        new LevelObjective("O Moinho de Vento",
                                MapUtils.getLocation("3A", -37, 133, -85, 0, 38), true),
                        new LevelObjective("A Nuvem mais baixa",
                                MapUtils.getLocation("3A", -40, 75, 24, -353, 90), true),
                        new LevelObjective("O Balão casa",
                                MapUtils.getLocation("3A", 93, 139, 59, 92, 65), true),
                        new LevelObjective("Pegue 8 Corações",
                                MapUtils.getLocation("3A", -27, 111, -11, -31, 90), false, true)
                });
    }

}
