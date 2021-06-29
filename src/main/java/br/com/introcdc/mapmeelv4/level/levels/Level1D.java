package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:40
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level1D extends Level {

    public Level1D() {
        super(
                "Watch Big City",
                Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                Material.CLOCK,
                Warp.L_1D,
                MapSound.MUSIC_SIX,
                null,
                new Location(Bukkit.getWorld("world"), -41, 52, -125, -202, 4),
                new LevelObjective[]{
                        new LevelObjective("No meio do relógio central",
                                MapUtils.getLocation("1D", 94, 37, 801, -5, 90), true),
                        new LevelObjective("Topo da cidade futuristica X",
                                MapUtils.getLocation("1D", 2, 204, 1005, -124, 19), true),
                        new LevelObjective("O Raio do Zeus III",
                                MapUtils.getLocation("1D", 122, 118, 597, -118, 90), true),
                        new LevelObjective("Alpinista do II Deserto",
                                MapUtils.getLocation("1D", -46, 85, 533, -34, 32), true),
                        new LevelObjective("Segundo Arranha Céu da Cobertura VIII",
                                MapUtils.getLocation("1D", 203, 148, 982, -220, 79), true),
                        new LevelObjective("Pegue 8 Corações",
                                MapUtils.getLocation("1D", 85, 31, 811, -71, 90), false, true)});
    }

}
