package br.com.introcdc.mapmeelv4.level.levels;
/*
 * Written by IntroCDC, Bruno Coêlho at 21/04/2019 - 18:31
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class FinalLevel extends Level {

    public FinalLevel() {
        super(
                "Final Level",
                Material.END_PORTAL,
                Warp.FINAL_BOSS,
                MapSound.MUSIC_ELEVEN,
                null,
                new Location(Bukkit.getWorld("world"), -4, 150, -18, -140, 61),
                new LevelObjective[]{
                        new LevelObjective("Mate o Chefão", new Location(Bukkit.getWorld("FINAL-BOSS"), 5000, 102, 5000), false, true)
                });
    }

}
