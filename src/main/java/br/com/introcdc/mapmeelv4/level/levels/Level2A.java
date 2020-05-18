package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:43
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level2A extends Level {

    public Level2A() {
        super(
                "Snow Temple",
                Material.YELLOW_STAINED_GLASS_PANE,
                Material.SNOW_BLOCK,
                Warp.L_2A,
                MapSound.MUSIC_FIVE,
                null,
                new Location(Bukkit.getWorld("world"), 5, 51, -74, -254, 7),
                new LevelObjective[]{
                        new LevelObjective("Debaixo do navio do lago", MapUtils.getLocation("2A", 26, 64, 296, -357, 3), true),
                        new LevelObjective("A Caverna pra cima da cascata", MapUtils.getLocation("2A", 13, 120, 87, -359, 16), true),
                        new LevelObjective("Submerso nos geleiras dos lados", MapUtils.getLocation("2A", 123, 82, 293, -352, 14), true),
                        new LevelObjective("A Grande Caverna", MapUtils.getLocation("2A", -34, 21, 278, -88, -1), true),
                        new LevelObjective("Trabalhadores da Grande Caverna", MapUtils.getLocation("2A", -23, 18, 349, -32, 12), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("2A", 41, 6, 264, -270, 90), false, true)});
    }

}
