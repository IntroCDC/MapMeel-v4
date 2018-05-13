package br.com.introcdc.mapmeelv4.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:19
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.bases.BlockId;
import br.com.introcdc.mapmeelv4.bases.InventoryBase;
import br.com.introcdc.mapmeelv4.bases.MapClassGetter;
import br.com.introcdc.mapmeelv4.bases.MapCoin;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import br.com.introcdc.mapmeelv4.enums.Warp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
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
    private Location portalSpec;
    private PotionEffect potionEffect;

    public Level(String name, BlockId blockId, Warp warp, MapSound backgroundMapSound, PotionEffect potionEffect, Location portalSpec, LevelObjective[] objectives, MapCoin... mapCoins) {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fRegistrando level §a" + name + "§f...");
        this.name = name;
        this.blockId = blockId;
        this.warp = warp;
        this.backgroundMapSound = backgroundMapSound;
        this.potionEffect = potionEffect;
        this.portalSpec = portalSpec;
        this.objectives = Arrays.asList(objectives);
        this.loadedCoins = Arrays.asList(mapCoins);
        leveis.put(this.getName(), this);
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fLevel §a" + name + "§f registrado!");
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

    public Location getPortalSpec() {
        return portalSpec;
    }

    public MapSound getBackgroundMapSound() {
        return backgroundMapSound;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public List<LevelObjective> getObjectives() {
        return objectives;
    }

    public static PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 50, 100);

    public void joinPortal(Player player) {
        float fly = player.getFlySpeed();
        player.setFlySpeed(0.0f);
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(getPortalSpec());
        playSound(player, MapSound.EFFECT_JOINING);
        InventoryBase.clearInventory(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                playSound(player, MapSound.STOP);
            }
        }.runTaskLater(getPlugin(), 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                LevelObjective.reload();
                int current = 0;
                for (LevelObjective objective : getObjectives()) {
                    current++;
                    if (current == 1) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ1.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ1.getBlock().getState()).setLine(3, objective.getStringObjective().substring(15, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ1.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ1.getBlock().getState().update();
                    }
                    if (current == 2) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ2.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ2.getBlock().getState()).setLine(3, objective.getStringObjective().substring(15, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ2.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ2.getBlock().getState().update();
                    }
                    if (current == 3) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ3.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ3.getBlock().getState()).setLine(3, objective.getStringObjective().substring(15, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ3.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ3.getBlock().getState().update();
                    }
                    if (current == 4) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ4.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ4.getBlock().getState()).setLine(3, objective.getStringObjective().substring(15, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ4.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ4.getBlock().getState().update();
                    }
                    if (current == 5) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ5.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ5.getBlock().getState()).setLine(3, objective.getStringObjective().substring(15, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ5.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ5.getBlock().getState().update();
                    }
                }
                player.setGameMode(GameMode.ADVENTURE);
                player.addPotionEffect(blindness);
                player.teleport(LISTOBJECTIVES);
                player.setFlySpeed(fly);
                playSound(player, MapSound.EFFECT_STARTING);
            }
        }.runTaskLater(getPlugin(), 40);
    }

    public abstract void onJoinPortal(Player player);

    public static List<UUID> loadCooldown = new ArrayList<>();

    public void loadLevel(Player player) {
        if (loadCooldown.contains(player.getUniqueId())) {
            return;
        }
        InventoryBase.clearInventory(player);
        loadCooldown.add(player.getUniqueId());
        this.unloadCoins();
        this.loadCoins();
        this.onLoadLevel(player);
        player.addPotionEffect(blindness);
        playSound(player, MapSound.EFFECT_LETSGO);
        sendTitle(player, "§l§oCarregando...", "§f§oAguarde", 0, 20, 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(getWarp().getLocation());
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        playSound(player, getBackgroundMapSound());
                        loadCooldown.remove(player.getUniqueId());
                        sendTitle(player, "§a§l" + getName(), "§f§oSeja bem-vindo ao level §a" + getWarp().toString().replace("L_", ""), 20, 40, 20);
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
        for (Entity entity : this.getWarp().getLocation().getWorld().getEntities()) {
            if (entity instanceof Item) {
                entity.remove();
            }
        }
    }

    public void unloadLevel(Player player, LevelObjective objective) {
        this.unloadCoins();
        this.onUnloadLevel();
        playSound(player, MapSound.EFFECT_STARTING);
        playSound(player, MapSound.STOP);
        objective.setFinished(true);
        player.setGameMode(GameMode.SPECTATOR);
        new BukkitRunnable() {
            @Override
            public void run() {
                playSound(player, MapSound.EFFECT_GETSTAR);
                float fly = player.getFlySpeed();
                player.setFlySpeed(0.0f);
                InventoryBase.clearInventory(player);
                player.teleport(getLocation(player.getLocation().getWorld().getName(), player.getLocation().getX() + 5, player.getLocation().getY() + 5, player.getLocation().getZ() + 5, player.getLocation()));
                new BukkitRunnable() {
                    int times = 0;

                    @Override
                    public void run() {
                        times++;
                        if (times <= 35) {
                            player.setVelocity(player.getLocation().getDirection().multiply(-1));
                        } else if (times == 36) {
                            player.addPotionEffect(blindness);
                        } else if (times == 40) {
                            cancel();
                            player.setFlySpeed(fly);
                            player.teleport(FRONTCASTLE);
                            player.setGameMode(GameMode.ADVENTURE);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    playSound(player, MapSound.CASTLE_MUSIC);
                                }
                            }.runTaskLater(getPlugin(), 10);
                        }
                    }
                }.runTaskTimer(getPlugin(), 0, 2);
            }
        }.runTaskLater(getPlugin(), 20);
    }

    public abstract void onLoadLevel(Player player);

    public abstract void onUnloadLevel();

    public static void loadLeveis() {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fRegistrando leveis...");
        for (Class<?> clazz : MapClassGetter.getClassesForPackage("br.com.introcdc.mapmeelv4.level.levels", MapMain.class)) {
            Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fLevel encontrado: §a" + clazz.getName());
            try {
                clazz.newInstance();
            } catch (Exception ignored) {
            }
        }
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fLeveis registrados!");
    }

}
