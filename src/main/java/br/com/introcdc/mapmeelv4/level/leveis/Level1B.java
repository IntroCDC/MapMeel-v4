package br.com.introcdc.mapmeelv4.level.leveis;
/*
 * Writter by IntroCDC, Bruno Coêlho at 26/08/2017 - 22:31
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

public class Level1B extends Level {

    public Level1B() {
        super("Mountain Village", new BlockId(Material.STAINED_GLASS_PANE, 1), Warp.L_1A, MapSound.MUSIC_SIX, new Location(Bukkit.getWorld("world"), -33.5, 51.5, 48.5, 27.0f, 10.0f), new LevelObjective[]{});
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
