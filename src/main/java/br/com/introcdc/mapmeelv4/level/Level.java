package br.com.introcdc.mapmeelv4.level;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:19
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.bases.BlockId;
import br.com.introcdc.mapmeelv4.bases.MapClassGetter;
import br.com.introcdc.mapmeelv4.bases.MapCoin;
import br.com.introcdc.mapmeelv4.enums.Music;
import br.com.introcdc.mapmeelv4.enums.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Level extends MapUtils {

    private static Map<String, Level> leveis = new HashMap<>();
    public static Location LISTOBJECTIVES = new Location(Bukkit.getWorld("world"), 21.5, 41.0, -48.5, 0, 10);

    public static Map<String, Level> getLeveis() {
        return leveis;
    }

    public static Level getLevel(String name) {
        for(Level level : leveis.values()) {
            if(level.getWarp().getName().equalsIgnoreCase(name)) {
                return level;
            }
        }
        return null;
    }

    public static Level currentLevel;

    private String name;
    private boolean finished;
    private BlockId blockId;
    private Warp warp;
    private Music backgroundMusic;
    public List<MapCoin> loadedCoins;
    public List<LevelObjective> objectives;
    public MapCoin[] mapCoins;

    public Level(String name, BlockId blockId, Warp warp, Music backgroundMusic, LevelObjective[] objectives, MapCoin... mapCoins) {
        this.name = name;
        this.blockId = blockId;
        this.warp = warp;
        this.backgroundMusic = backgroundMusic;
        this.objectives = Arrays.asList(objectives);
        this.loadedCoins = Arrays.asList(mapCoins);
        leveis.remove(this.getName());
        leveis.put(this.getName(), this);
    }

    public String getName() {
        return name;
    }

    public boolean isFinished() {
        return finished;
    }

    public BlockId getBlockId() {
        return blockId;
    }

    public Warp getWarp() {
        return warp;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public List<LevelObjective> getObjectives() {
        return objectives;
    }

    public void joinPortal(Player player) {
        player.teleport(LISTOBJECTIVES);
    }

    public abstract void onJoinPortal(Player player);

    public void loadLevel() {
        this.unloadCoins();
        this.loadCoins();
        this.onLoadLevel();
    }

    public void loadCoins() {
        this.loadedCoins.forEach(MapCoin::spawn);
    }

    public void unloadCoins() {
        this.loadedCoins.forEach(MapCoin::despawn);
    }

    public void unloadLevel() {
        this.unloadCoins();
        this.onUnloadLevel();
    }

    public abstract void onLoadLevel();

    public abstract void onUnloadLevel();

    public static void loadLeveis() {
        for (Class<?> clazz : MapClassGetter.getClassesForPackage("br.com.introcdc.mapmeelv4.level.leveis", MapMain.class)) {
            if (Level.class.isAssignableFrom(clazz)) {
                try {
                    clazz.newInstance();
                } catch (Exception ignored) {
                }
            }
        }
    }

}
