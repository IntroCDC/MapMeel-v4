package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:26
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level1A extends Level {

    public Level1A() {
        super(
                "Mountain Village",
                Material.WHITE_STAINED_GLASS_PANE,
                Warp.L_1A,
                MapSound.MUSIC_ONE,
                null,
                new Location(Bukkit.getWorld("world"), -41, 53, 81, -336, 19),
                new LevelObjective[]{
                        new LevelObjective("A Casa do Final da Vila", MapUtils.getLocation("1A", 43.5, 74, -24.5, -181, 7), true),
                        new LevelObjective("Na Caverna da Ravina de Lava", MapUtils.getLocation("1A", -96, 9, -19, 89, 89), true),
                        new LevelObjective("Presa na Jaula Suspensa", MapUtils.getLocation("1A", -20.5, 42, -88.5, -181, 89), true),
                        new LevelObjective("A Sala Suspeita da Casa", MapUtils.getLocation("1A", 24, 41, -154, -180, 90), true),
                        new LevelObjective("A Estranha Construção de Madeira dos Ares", MapUtils.getLocation("1A", 38, 183, -60, -5, 89), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("1A", -7, 41, -120, -6, 90))});
    }

}
