package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:46
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level2B extends Level {

    public Level2B() {
        super(
                "Santa Claus Temple",
                Material.LIME_STAINED_GLASS_PANE,
                Warp.L_2B,
                MapSound.MUSIC_NINE,
                null,
                new Location(Bukkit.getWorld("world"), -4, 68, -72, -244, 5),
                new LevelObjective[]{
                        new LevelObjective("O Fim da linha do trem", MapUtils.getLocation("2B", 404, 77, 129, -263, 90), true),
                        new LevelObjective("A Baleia na entrada do Natal", MapUtils.getLocation("2B", -27, 52, 281, -355, 90), true),
                        new LevelObjective("O Papai Noel", MapUtils.getLocation("2B", -37, 111, -349, 0, 0), true),
                        new LevelObjective("A Praça no meio do Nada", MapUtils.getLocation("2B", 167, 72, -147, -171, 90), true),
                        new LevelObjective("A Árvore de Natal", MapUtils.getLocation("2B", 25, 105, -46, -276, 90), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("2B", 26, 68, -33, -180, 90), false, true)});
    }

}
