package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Co�lho at 26/08/2017 - 22:31
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level1B extends Level {

    public Level1B() {
        super("Ghost Village",
                Material.ORANGE_STAINED_GLASS_PANE,
                Material.WHITE_STAINED_GLASS,
                Warp.L_1B,
                MapSound.MUSIC_SIX,
                null,
                new Location(Bukkit.getWorld("world"), -54, 53, 29, -301, 10),
                new LevelObjective[]{
                        new LevelObjective("O Capit�o do Navio",
                                MapUtils.getLocation("1B", 90.5, 30, 19.5, 0, 86), true),
                        new LevelObjective("A Sacada da Maior de Todas",
                                MapUtils.getLocation("1B", -35.5, 70, 4.5, -87, 89), true),
                        new LevelObjective("O P�ssaro",
                                MapUtils.getLocation("1B", -19, 88, 55, -134, 88), true),
                        new LevelObjective("Lago no centro da cidade",
                                MapUtils.getLocation("1B", 29, 37, 8, -63, 42), true),
                        new LevelObjective("O Alpinista",
                                MapUtils.getLocation("1B", 129, 88, 95, -225, 27), true),
                        new LevelObjective("Pegue 8 Cora��es",
                                MapUtils.getLocation("1B", 46, 26, -41, 180, 90), false, true)});
    }

}
