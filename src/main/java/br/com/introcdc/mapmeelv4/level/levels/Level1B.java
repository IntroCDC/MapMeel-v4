package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 26/08/2017 - 22:31
 */

import br.com.introcdc.mapmeelv4.bases.BlockId;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import br.com.introcdc.mapmeelv4.enums.Warp;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Level1B extends Level {

    public Level1B() {
        super("Ghost Village", new BlockId(Material.STAINED_GLASS_PANE, 1), Warp.L_1B, MapSound.MUSIC_SIX, new PotionEffect(PotionEffectType.BLINDNESS, 300 * 20, 3), new Location(Bukkit.getWorld("world"), -36.5, 52.5, 3.5, 125, 14), new LevelObjective[]{});
    }

    @Override
    public void onJoinPortal(Player player) {
    }

    @Override
    public void onLoadLevel(Player player) {
    }

    @Override
    public void onUnloadLevel() {
    }

}
