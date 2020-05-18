package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:51
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Level3C extends Level {

    public Level3C() {
        super(
                "The Big Tower",
                Material.PURPLE_STAINED_GLASS_PANE,
                Material.SPRUCE_LOG,
                Warp.L_3C,
                MapSound.MUSIC_SEVEN,
                null,
                new Location(Bukkit.getWorld("world"), 36, 51, 37, 21, 12),
                new LevelObjective[]{
                        new LevelObjective("No Inferior Da Grande Central", MapUtils.getLocation("3C", -464.5, 7.0, 201.5, 0, 86), true),
                        new LevelObjective("Na Torre da Estação Principal", MapUtils.getLocation("3C", -474, 95, 100, -357, 90), true),
                        new LevelObjective("A Principal Construção do Criatório Superior", MapUtils.getLocation("3C", -441, 187, 144, -8, 90), true),
                        new LevelObjective("Viagem para o Fim Da Linha", MapUtils.getLocation("3C", -604, 55, 1019, -359, 90), true),
                        new LevelObjective("Da Estação para Um Novo Mundo", MapUtils.getLocation("3C", 402, 42, -786, -2, 90), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("3C", -464.5, 51.0, 204.5, 138, 89), false, true)
                });
    }

}
