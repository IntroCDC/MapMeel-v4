package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:55
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

public class Level4A extends Level {

    public Level4A() {
        super(
                "Oriental Village",
                Material.BROWN_STAINED_GLASS_PANE,
                Warp.L_4A,
                MapSound.MUSIC_SIX,
                null,
                new Location(Bukkit.getWorld("world"), -5, 52, -31, 205, 11),
                new LevelObjective[]{
                        new LevelObjective("O Lago Central do Palácio", MapUtils.getLocation("4A", -149, 22, 571, -178, 90), true),
                        new LevelObjective("No Rabo do Dragão", MapUtils.getLocation("4A", -312, 27, 614, -181, 90), true),
                        new LevelObjective("Siga Para a Floresta Do Outro Lado", MapUtils.getLocation("4A", -321, 20, 435, -12, 90), true),
                        new LevelObjective("O Grande Montanha de Gelo", MapUtils.getLocation("4A", -52, 164, 645, -236, 90), true),
                        new LevelObjective("O Fim do Mapa", MapUtils.getLocation("4A", -233, 21, 778, -166, 90), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("4A", -189, 20, 467, -91, 90))
                });
    }

}
