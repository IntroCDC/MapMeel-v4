package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 15:51
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LevelEgUnderWater extends Level {

    public LevelEgUnderWater() {
        super("The Underwater Temple",
                Material.GLASS_PANE,
                Material.PRISMARINE_BRICKS,
                Warp.EG_UNDERWATER,
                MapSound.MUSIC_THREE,
                new PotionEffect(PotionEffectType.NIGHT_VISION, 100, 100),
                new Location(Bukkit.getWorld("world"), -157, 5, 48, -90, 0),
                new LevelObjective[]{
                        new LevelObjective("No Topo Da Piramide", new Location(Bukkit.getWorld("EG-UNDERWATER"), -183, 52, -200, 88, 42), true)
                });
    }

}
