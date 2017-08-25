package br.com.introcdc.mapmeelv4.level.leveis;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:26
 */

import br.com.introcdc.mapmeelv4.bases.BlockId;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import br.com.introcdc.mapmeelv4.enums.Warp;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Level1A extends Level {

    public Level1A() {
        super("Mountain Village", new BlockId(Material.STAINED_GLASS_PANE), Warp.L_1A, MapSound.MUSIC_ONE, new LevelObjective[]{});
    }

    @Override
    public void onJoinPortal(Player player) {
    }

    @Override
    public void onLoadLevel() {
    }

    @Override
    public void onUnloadLevel() {
    }
}
