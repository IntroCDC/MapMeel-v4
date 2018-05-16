package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 26/08/2017 - 22:31
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Level1B extends Level {

    public Level1B() {
        super("Ghost Village", new BlockId(Material.STAINED_GLASS_PANE, 1), Warp.L_1B, MapSound.MUSIC_SIX, new PotionEffect(PotionEffectType.BLINDNESS, 300 * 20, 3), new Location(Bukkit.getWorld("world"), -35, 52, 2, -247, 13), new LevelObjective[]{new LevelObjective("Pegue 8 Corações", MapUtils.getLocation("1B", 46, 26, -41, 180, 90))});
    }

}
