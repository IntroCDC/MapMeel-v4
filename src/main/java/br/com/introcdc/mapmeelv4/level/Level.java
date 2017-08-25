package br.com.introcdc.mapmeelv4.level;
/*
 * Writter by IntroCDC, Bruno Co�lho at 23/08/2017 - 07:19
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.bases.BlockId;
import br.com.introcdc.mapmeelv4.bases.MapClassGetter;
import br.com.introcdc.mapmeelv4.bases.MapCoin;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import br.com.introcdc.mapmeelv4.enums.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

@SuppressWarnings("EmptyMethod")
public abstract class Level extends MapUtils {

    private static Map<String, Level> leveis = new HashMap<>();
    public static Location LISTOBJECTIVES = new Location(Bukkit.getWorld("world"), 21.5, 41.0, -48.5, 0, 10);

    public static Map<String, Level> getLeveis() {
        return leveis;
    }

    public static Level getLevel(String name) {
        for (Level level : leveis.values()) {
            if (level.getWarp().getName().equalsIgnoreCase(name)) {
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
    private MapSound backgroundMapSound;
    public List<MapCoin> loadedCoins;
    public List<LevelObjective> objectives;
    public MapCoin[] mapCoins;

    public Level(String name, BlockId blockId, Warp warp, MapSound backgroundMapSound, LevelObjective[] objectives, MapCoin... mapCoins) {
        this.name = name;
        this.blockId = blockId;
        this.warp = warp;
        this.backgroundMapSound = backgroundMapSound;
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

    public MapSound getBackgroundMapSound() {
        return backgroundMapSound;
    }

    public List<LevelObjective> getObjectives() {
        return objectives;
    }

    public static PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 100, 100);

    public void joinPortal(Player player) {
        LevelObjective.reload();
        int current = 0;
        for (LevelObjective objective : getObjectives()) {
            current++;
            if (current == 1) {
                ((Sign) LevelObjective.OBJ1.getBlock().getState()).setLine(2, objective.getStringObjective());
                LevelObjective.OBJ1.getBlock().getState().update();
            }
            if (current == 2) {
                ((Sign) LevelObjective.OBJ2.getBlock().getState()).setLine(2, objective.getStringObjective());
                LevelObjective.OBJ2.getBlock().getState().update();
            }
            if (current == 3) {
                ((Sign) LevelObjective.OBJ3.getBlock().getState()).setLine(2, objective.getStringObjective());
                LevelObjective.OBJ3.getBlock().getState().update();
            }
            if (current == 4) {
                ((Sign) LevelObjective.OBJ4.getBlock().getState()).setLine(2, objective.getStringObjective());
                LevelObjective.OBJ4.getBlock().getState().update();
            }
            if (current == 5) {
                ((Sign) LevelObjective.OBJ5.getBlock().getState()).setLine(2, objective.getStringObjective());
                LevelObjective.OBJ5.getBlock().getState().update();
            }
        }
        player.addPotionEffect(blindness);
        player.teleport(LISTOBJECTIVES);
        playSound(player, MapSound.EFFECT_STARTING);
    }

    public abstract void onJoinPortal(Player player);

    public static List<UUID> loadCooldown = new ArrayList<>();

    public void loadLevel(Player player) {
        if (loadCooldown.contains(player.getUniqueId())) {
            return;
        }
        loadCooldown.add(player.getUniqueId());
        this.unloadCoins();
        this.loadCoins();
        this.onLoadLevel(player);
        player.addPotionEffect(blindness);
        sendTitle(player, "�l�oCarregando...", "�f�oAguarde", 0, 40, 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(getWarp().getLocation());
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        playSound(player, getBackgroundMapSound());
                        loadCooldown.remove(player.getUniqueId());
                    }
                }.runTaskLater(getPlugin(), 10);
            }
        }.runTaskLater(getPlugin(), 20);
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

    public abstract void onLoadLevel(Player player);

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
