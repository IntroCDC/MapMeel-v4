package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:52
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level3D extends Level {

    public Level3D() {
        super(
                "Castle Lands",
                Material.BLUE_STAINED_GLASS_PANE,
                Material.STONE_BRICKS,
                Warp.L_3D,
                MapSound.MUSIC_ONE,
                null,
                new Location(Bukkit.getWorld("world"), 30, 51, 29, 200, 14),
                new LevelObjective[]{
                        new LevelObjective("A Igreja do Alto",
                                MapUtils.getLocation("3D", 624, 173, -816, -179, 90), true),
                        new LevelObjective("O Grande Castelo",
                                MapUtils.getLocation("3D", 474, 200, -700, -89, 90), true),
                        new LevelObjective("A Caverna De Minecarts",
                                MapUtils.getLocation("3D", 622, 79, -763, -270, 32), true),
                        new LevelObjective("Não Entre Aqui",
                                MapUtils.getLocation("3D", 599, 127, -830, -356, 5), true),
                        new LevelObjective("Nos Pés do Dragão",
                                MapUtils.getLocation("3D", 537, 210, -794, 0, 34), true),
                        new LevelObjective("Pegue 8 Corações",
                                MapUtils.getLocation("3D", 531, 148, -691, -273, 90), false, true)
                });
    }

}
