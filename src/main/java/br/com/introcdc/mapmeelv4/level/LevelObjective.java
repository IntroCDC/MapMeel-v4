package br.com.introcdc.mapmeelv4.level;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 08:04
 */

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.bases.BlockId;
import org.bukkit.Location;
import org.bukkit.Material;

public class LevelObjective extends MapUtils {

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
