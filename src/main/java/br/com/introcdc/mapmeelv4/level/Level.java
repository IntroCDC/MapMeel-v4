package br.com.introcdc.mapmeelv4.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:19
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.block.BlockId;
import br.com.introcdc.mapmeelv4.classes.MapClassGetter;
import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.coin.MapCoin;
import br.com.introcdc.mapmeelv4.item.InventoryBase;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("EmptyMethod")
public class Level {

    public static long stars = 0;

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
    private BlockId blockId;
    private Warp warp;
    private MapSound backgroundMapSound;
    public List<MapCoin> loadedCoins;
    public Map<String, LevelObjective> objectives;
    private Location portalSpec;
    private PotionEffect potionEffect;
    private File levelFile;
    private JsonElement jsonElement;

    public Level(String name, BlockId blockId, Warp warp, MapSound backgroundMapSound, PotionEffect potionEffect, Location portalSpec, LevelObjective[] objectives) {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fRegistrando level §a" + name + "§f...");
        this.name = name;
        this.blockId = blockId;
        this.warp = warp;
        this.backgroundMapSound = backgroundMapSound;
        this.potionEffect = potionEffect;
        this.portalSpec = portalSpec;
        this.objectives = new HashMap<>();
        for (LevelObjective objective : objectives) {
            this.objectives.put(objective.getStringObjective(), objective);
        }
        LevelObjective coins = new LevelObjective("Colete 100 Moedas", warp.getLocation());
        this.objectives.put(coins.getStringObjective(), coins);
        this.loadedCoins = new ArrayList<>();
        this.levelFile = new File("plugins/MapMeel/levels/" + warp.getName() + ".json");
        try {
            if (!levelFile.exists()) {
                levelFile.getParentFile().mkdirs();
                levelFile.createNewFile();
                PrintWriter writer = new PrintWriter(levelFile);
                writer.println("{\"coins\":[]}");
                writer.close();
            }
            Scanner scanner = new Scanner(levelFile);
            jsonElement = MapUtils.parser.parse(scanner.nextLine());
            scanner.close();
            jsonElement.getAsJsonObject().get("coins").getAsJsonArray().forEach(jsonElement1 -> getLoadedCoins().add(new MapCoin(new Location(Bukkit.getWorld(getWarp().getName()), jsonElement1.getAsJsonObject().get("x").getAsDouble(), jsonElement1.getAsJsonObject().get("y").getAsDouble(), jsonElement1.getAsJsonObject().get("z").getAsDouble()), CoinType.valueOf(jsonElement1.getAsJsonObject().get("type").getAsString()))));
            for (LevelObjective levelObjective : this.objectives.values()) {
                if (!jsonElement.getAsJsonObject().has(levelObjective.getStringObjective())) {
                    jsonElement.getAsJsonObject().addProperty(levelObjective.stringObjective, false);
                }
                levelObjective.finished = jsonElement.getAsJsonObject().get(levelObjective.getStringObjective()).getAsBoolean();
                levelObjective.setup(this);
                if (levelObjective.finished) {
                    stars++;
                }
            }
            save();
        } catch (Exception ignored) {
        }

        leveis.put(getName(), this);
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fLevel §a" + name + "§f registrado!");
    }

    public String getName() {
        return name;
    }

    public BlockId getBlockId() {
        return blockId;
    }

    public List<MapCoin> getLoadedCoins() {
        return loadedCoins;
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

    public Map<String, LevelObjective> getObjectives() {
        return objectives;
    }

    public static PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 50, 100);

    public void joinPortal() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setFlySpeed(0.0f);
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(getPortalSpec());
            MapUtils.playSound(player, MapSound.EFFECT_JOINING);
            InventoryBase.clearInventory(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    MapUtils.playSound(player, MapSound.STOP);
                }
            }.runTaskLater(MapUtils.getPlugin(), 40);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                LevelObjective.reload();
                int current = 0;
                for (String key : getObjectives().keySet()) {
                    LevelObjective objective = getObjectives().get(key);
                    current++;
                    if (current == 1) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ1.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ1.getBlock().getState()).setLine(3, objective.getStringObjective().substring(14, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ1.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ1.getBlock().getState().update();
                    }
                    if (current == 2) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ2.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ2.getBlock().getState()).setLine(3, objective.getStringObjective().substring(14, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ2.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ2.getBlock().getState().update();
                    }
                    if (current == 3) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ3.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ3.getBlock().getState()).setLine(3, objective.getStringObjective().substring(14, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ3.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ3.getBlock().getState().update();
                    }
                    if (current == 4) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ4.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ4.getBlock().getState()).setLine(3, objective.getStringObjective().substring(14, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ4.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ4.getBlock().getState().update();
                    }
                    if (current == 5) {
                        if (objective.getStringObjective().length() > 15) {
                            ((Sign) LevelObjective.OBJ5.getBlock().getState()).setLine(2, objective.getStringObjective().substring(0, 15));
                            ((Sign) LevelObjective.OBJ5.getBlock().getState()).setLine(3, objective.getStringObjective().substring(14, objective.getStringObjective().length()));
                        } else {
                            ((Sign) LevelObjective.OBJ5.getBlock().getState()).setLine(2, objective.getStringObjective());
                        }
                        LevelObjective.OBJ5.getBlock().getState().update();
                    }
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.addPotionEffect(blindness);
                    player.teleport(LISTOBJECTIVES);
                    player.setFlySpeed(0.1f);
                    MapUtils.playSound(player, MapSound.EFFECT_STARTING);
                }
            }
        }.runTaskLater(MapUtils.getPlugin(), 60);
    }

    public static boolean loadCooldown = false;

    public void loadLevel() {
        if (loadCooldown) {
            return;
        }
        loadCooldown = true;
        for (Player player : Bukkit.getOnlinePlayers()) {
            InventoryBase.clearInventory(player);
            MapUtils.sendTitle(player, "§l§oCarregando...", "§f§oAguarde", 0, 20, 20);
            MapUtils.playSound(player, MapSound.EFFECT_LETSGO);
            player.addPotionEffect(blindness);
        }
        unloadCoins();
        loadCoins();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapUtils.getProfile(player.getName()).resetTempCoins();
                    player.teleport(getWarp().getLocation());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            MapUtils.playSound(player, getBackgroundMapSound());
                            loadCooldown = false;
                            MapUtils.sendTitle(player, "§a§l" + getName(), "§f§oSeja bem-vindo ao level §a§o" + getWarp().toString().replace("L_", ""), 20, 40, 20);
                        }
                    }.runTaskLater(MapUtils.getPlugin(), 10);
                }
            }
        }.runTaskLater(MapUtils.getPlugin(), 20);
    }

    public void loadCoins() {
        getLoadedCoins().forEach(MapCoin::spawn);
    }

    public void unloadCoins() {
        getLoadedCoins().forEach(MapCoin::despawn);
        for (Entity entity : getWarp().getLocation().getWorld().getEntities()) {
            if (entity instanceof Item) {
                entity.remove();
            }
        }
    }

    public void unloadLevel(LevelObjective objective, boolean unload) {
        boolean already = false;
        if (objective != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                MapUtils.playSound(player, MapSound.EFFECT_JOINING);
            }
            if (!objective.isFinished()) {
                objective.setFinished(true);
                Level.stars++;
            } else {
                already = true;
            }
        }
        save();
        if (unload) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
                MapUtils.getProfile(player.getName()).resetTempCoins();
                player.setGameMode(GameMode.SPECTATOR);
            }
            unloadCoins();
        }
        boolean Already = already;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (objective != null) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        MapUtils.playSound(player, MapSound.EFFECT_GETSTAR);
                    }
                }
                if (unload) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.setFlySpeed(0.0f);
                        InventoryBase.clearInventory(player);
                        player.teleport(MapUtils.getLocation(player.getLocation().getWorld().getName(), player.getLocation().getX() + 5, player.getLocation().getY() + 5, player.getLocation().getZ() + 5, player.getLocation()));
                    }
                }
                new BukkitRunnable() {
                    int times = 0;

                    @Override
                    public void run() {
                        times++;
                        if (times <= 35) {
                            if (unload) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.setVelocity(player.getLocation().getDirection().multiply(-1));
                                }
                            }
                        } else if (times == 36) {
                            if (unload) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.addPotionEffect(blindness);
                                }
                            }
                        } else if (times == 40) {
                            cancel();
                            if (unload) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.setFlySpeed(0.1f);
                                    player.teleport(getPortalSpec());
                                    player.setGameMode(GameMode.ADVENTURE);
                                }
                            }
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (objective != null) {
                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                            MapUtils.sendTitle(player, (Already ? "§7§lOBJETIVO JÁ CONCLUÍDO!" : "§a§lOBJETIVO CONCLUÍDO!"), "§f§o" + objective.getStringObjective(), 10, 40, 20);
                                            MapUtils.playSound(player, MapSound.EFFECT_COMPLETE);
                                        }
                                    }
                                }
                            }.runTaskLater(MapUtils.getPlugin(), 5);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (unload) {
                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                            MapUtils.playSound(player, MapSound.CASTLE_MUSIC);
                                        }
                                    }
                                }
                            }.runTaskLater(MapUtils.getPlugin(), 65);
                        }
                    }
                }.runTaskTimer(MapUtils.getPlugin(), 0, 2);
            }
        }.runTaskLater(MapUtils.getPlugin(), 20);
    }

    public void addCoin(Location location, CoinType coinType) {
        getLoadedCoins().add(new MapCoin(location, coinType));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", location.getX());
        jsonObject.addProperty("y", location.getY());
        jsonObject.addProperty("z", location.getZ());
        jsonObject.addProperty("type", coinType.toString());
        jsonElement.getAsJsonObject().get("coins").getAsJsonArray().add(jsonObject);
        save();
    }

    public void save() {
        try {
            for (LevelObjective levelObjective : this.objectives.values()) {
                if (!jsonElement.getAsJsonObject().has(levelObjective.getStringObjective())) {
                    jsonElement.getAsJsonObject().addProperty(levelObjective.stringObjective, false);
                }
                jsonElement.getAsJsonObject().addProperty(levelObjective.stringObjective, levelObjective.isFinished());
            }
            PrintWriter writer = new PrintWriter(levelFile);
            writer.println(jsonElement.toString());
            writer.close();
        } catch (Exception ignored) {
        }
    }

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
