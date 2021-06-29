package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 22:52
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Level2C extends Level {

    public Level2C() {
        super(
                "Underwater City",
                Material.PINK_STAINED_GLASS_PANE,
                Material.WATER_BUCKET,
                Warp.L_2C,
                MapSound.MUSIC_THREE,
                new PotionEffect(PotionEffectType.NIGHT_VISION, 100, 100),
                new Location(Bukkit.getWorld("world"), 31, 50, -70, -17, 3),
                new LevelObjective[]{
                        new LevelObjective("Escombros da grande torre ao lado",
                                MapUtils.getLocation("2C", 763, 121, 649, -258, 15), true),
                        new LevelObjective("Desca para o Norte e siga o caminho",
                                MapUtils.getLocation("2C", 708, 133, 622, -12, 25), true),
                        new LevelObjective("A Grande Torre vizinha",
                                MapUtils.getLocation("2C", 699, 76, 666, 155, 53), true),
                        new LevelObjective("Na lingua do grande animal ao Norte",
                                MapUtils.getLocation("2C", 640, 137, 549, -13, 53), true),
                        new LevelObjective("A última torre do Sul",
                                MapUtils.getLocation("2C", 780, 207, 1087, -197, 90), true),
                        new LevelObjective("Pegue 8 Corações",
                                MapUtils.getLocation("2C", 754, 249, 653, 253, 90), false, true)
                });
    }

}
