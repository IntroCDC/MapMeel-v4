package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:17
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level1C extends Level {

    public Level1C() {
        super(
                "The Villager Village",
                Material.MAGENTA_STAINED_GLASS_PANE,
                Material.OAK_LOG,
                Warp.L_1C,
                MapSound.MUSIC_TWO,
                null,
                new Location(Bukkit.getWorld("world"), -53, 52, -77, -278, 10),
                new LevelObjective[]{
                        new LevelObjective("Em cima da criação de bois", MapUtils.getLocation("1C", 574, 90, 410, -255, 90), true),
                        new LevelObjective("O Guarda para a Montanha", MapUtils.getLocation("1C", 541, 79, 420, 80, 45), true),
                        new LevelObjective("Na maior torre de todas", MapUtils.getLocation("1C", 554, 83, 349, -71, 89), true),
                        new LevelObjective("Na saida do lago de fora", MapUtils.getLocation("1C", 614, 64, 283, -130, 30), true),
                        new LevelObjective("Avante floresta, no lago contra montanha", MapUtils.getLocation("1C", 484, 47, 208, -351, 46), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("1C", 553, 66, 362, 354, 89), false, true)});
    }

}
