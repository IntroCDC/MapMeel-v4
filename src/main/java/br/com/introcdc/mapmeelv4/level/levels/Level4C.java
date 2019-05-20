package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 02:56
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

public class Level4C extends Level {

    public Level4C() {
        super(
                "Pirate Island",
                Material.RED_STAINED_GLASS_PANE,
                Warp.L_4C,
                MapSound.MUSIC_ONE,
                null,
                new Location(Bukkit.getWorld("world"), 0, 52, -12, 11, 9),
                new LevelObjective[]{
                        new LevelObjective("A Cabeça Pirata", MapUtils.getLocation("4C", -28, 138, 94, 185, 13), true),
                        new LevelObjective("Corais Pertos Dos Escombros", MapUtils.getLocation("4C", -104, 75, 30, 221, 11), true),
                        new LevelObjective("A Praia Deserta Esquecida", MapUtils.getLocation("4C", 48, 84, -23, 224, 45), true),
                        new LevelObjective("Montanhas Gêmeas", MapUtils.getLocation("4C", 62, 86, 94, 1, 49), true),
                        new LevelObjective("A Ilha Solitária", MapUtils.getLocation("4C", -86, 108, -68, 315, 90), true),
                        new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("4C", 5, 84, -1, -179, 90), false, true)
                });
    }

}
