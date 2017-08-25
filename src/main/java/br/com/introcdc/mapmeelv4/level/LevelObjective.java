package br.com.introcdc.mapmeelv4.level;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 08:04
 */

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.bases.BlockId;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

public class LevelObjective extends MapUtils {

    public static Location OBJ1 = new Location(Bukkit.getWorld("world"), 19, 42, -45);
    public static Location OBJ2 = new Location(Bukkit.getWorld("world"), 19, 42, -47);
    public static Location OBJ3 = new Location(Bukkit.getWorld("world"), 19, 42, -49);
    public static Location OBJ4 = new Location(Bukkit.getWorld("world"), 19, 42, -51);
    public static Location OBJ5 = new Location(Bukkit.getWorld("world"), 19, 42, -53);
    public static Location OBJ6 = new Location(Bukkit.getWorld("world"), 23, 42, -49);

    public static void reload() {
        ((Sign)LevelObjective.OBJ1.getBlock().getState()).setLine(0, "");
        ((Sign)LevelObjective.OBJ1.getBlock().getState()).setLine(1, "§l1º Objetivo");
        ((Sign)LevelObjective.OBJ1.getBlock().getState()).setLine(2, "");
        ((Sign)LevelObjective.OBJ1.getBlock().getState()).setLine(3, "");
        LevelObjective.OBJ1.getBlock().getState().update();

        ((Sign)LevelObjective.OBJ2.getBlock().getState()).setLine(0, "");
        ((Sign)LevelObjective.OBJ2.getBlock().getState()).setLine(1, "§l2º Objetivo");
        ((Sign)LevelObjective.OBJ2.getBlock().getState()).setLine(2, "");
        ((Sign)LevelObjective.OBJ2.getBlock().getState()).setLine(3, "");
        LevelObjective.OBJ2.getBlock().getState().update();

        ((Sign)LevelObjective.OBJ3.getBlock().getState()).setLine(0, "");
        ((Sign)LevelObjective.OBJ3.getBlock().getState()).setLine(1, "§l3º Objetivo");
        ((Sign)LevelObjective.OBJ3.getBlock().getState()).setLine(2, "");
        ((Sign)LevelObjective.OBJ3.getBlock().getState()).setLine(3, "");
        LevelObjective.OBJ3.getBlock().getState().update();

        ((Sign)LevelObjective.OBJ4.getBlock().getState()).setLine(0, "");
        ((Sign)LevelObjective.OBJ4.getBlock().getState()).setLine(1, "§l4º Objetivo");
        ((Sign)LevelObjective.OBJ4.getBlock().getState()).setLine(2, "");
        ((Sign)LevelObjective.OBJ4.getBlock().getState()).setLine(3, "");
        LevelObjective.OBJ4.getBlock().getState().update();

        ((Sign)LevelObjective.OBJ5.getBlock().getState()).setLine(0, "");
        ((Sign)LevelObjective.OBJ5.getBlock().getState()).setLine(1, "§l5º Objetivo");
        ((Sign)LevelObjective.OBJ5.getBlock().getState()).setLine(2, "");
        ((Sign)LevelObjective.OBJ5.getBlock().getState()).setLine(3, "");
        LevelObjective.OBJ5.getBlock().getState().update();

        ((Sign)LevelObjective.OBJ6.getBlock().getState()).setLine(0, "");
        ((Sign)LevelObjective.OBJ6.getBlock().getState()).setLine(1, "§l6º Objetivo");
        ((Sign)LevelObjective.OBJ6.getBlock().getState()).setLine(2, "§oColetar 100");
        ((Sign)LevelObjective.OBJ6.getBlock().getState()).setLine(3, "§omoedas");
        LevelObjective.OBJ6.getBlock().getState().update();
    }

    public String stringObjective;
    public BlockId blockId;
    public boolean finished;

    public LevelObjective(String stringObjective, BlockId blockId) {
        this.stringObjective = stringObjective;
        this.blockId = blockId;
    }

    public String getStringObjective() {
        return stringObjective;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public BlockId getBlockId() {
        return blockId;
    }
}
