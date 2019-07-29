package br.com.introcdc.mapmeelv4.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:19
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.classes.MapClassGetter;
import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.coin.MapCoin;
import br.com.introcdc.mapmeelv4.door.Door;
import br.com.introcdc.mapmeelv4.door.LittleDoor;
import br.com.introcdc.mapmeelv4.item.InventoryBase;
import br.com.introcdc.mapmeelv4.listeners.coin.CoinEvents;
import br.com.introcdc.mapmeelv4.listeners.finallevel.FinalLevelEvents;
import br.com.introcdc.mapmeelv4.listeners.music.MusicUpdaterEvents;
import br.com.introcdc.mapmeelv4.mob.MapMob;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("EmptyMethod")
public class Level {

    public static long stars = 0;

    public static boolean update = false;

    private static Map<String, Level> leveis = new HashMap<>();
    public static Location LISTOBJECTIVES = new Location(Bukkit.getWorld("world"), 21.5, 40.5, -55.5, 0, -7.5f);

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
    private Material material;
    private Warp warp;
    private MapSound backgroundMapSound;
    private MapSound tempBackgroungMapSound;
    private List<MapCoin> loadedCoins;
    private List<MapMob> loadedMobs;
    public Map<String, LevelObjective> objectives;
    private Location portalSpec;
    private PotionEffect potionEffect;
    private PotionEffect tempPotionEffect = null;
    private File levelFile;
    private JsonElement jsonElement;

    public Level(String name, Material material, Warp warp, MapSound backgroundMapSound, PotionEffect potionEffect, Location portalSpec, LevelObjective[] objectives) {
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fRegistrando level §a" + name + "§f...");
        this.name = name;
        this.material = material;
        this.warp = warp;
        this.backgroundMapSound = backgroundMapSound;
        this.potionEffect = potionEffect;
        this.portalSpec = portalSpec;
        this.objectives = new HashMap<>();
        for (LevelObjective objective : objectives) {
            this.objectives.put(objective.getStringObjective(), objective);
        }

        if (!warp.toString().contains("EG_") && !warp.toString().contains("FINAL")) {
            LevelObjective coins = new LevelObjective("Colete 100 Moedas", warp.getLocation(), false, false);
            this.objectives.put(coins.getStringObjective(), coins);
        }

        this.loadedCoins = new ArrayList<>();
        this.loadedMobs = new ArrayList<>();
        this.levelFile = new File("plugins/MapMeel/levels/" + warp.getName() + ".json");
        try {
            if (!levelFile.exists()) {
                levelFile.getParentFile().mkdirs();
                levelFile.createNewFile();
                PrintWriter writer = new PrintWriter(levelFile);
                writer.println("{\"coins\":[],\"mobs\":[]}");
                writer.close();
            }
            try (FileReader reader = new FileReader(levelFile)) {
                jsonElement = MapUtils.parser.parse(reader);
            }
            jsonElement.getAsJsonObject().get("coins").getAsJsonArray().forEach(jsonElement1 -> getLoadedCoins().add(new MapCoin(new Location(Bukkit.getWorld(getWarp().getName()), jsonElement1.getAsJsonObject().get("x").getAsDouble(), jsonElement1.getAsJsonObject().get("y").getAsDouble(), jsonElement1.getAsJsonObject().get("z").getAsDouble()), CoinType.valueOf(jsonElement1.getAsJsonObject().get("type").getAsString()))));
            jsonElement.getAsJsonObject().get("mobs").getAsJsonArray().forEach(jsonElement1 -> getLoadedMobs().add(new MapMob(EntityType.valueOf(jsonElement1.getAsJsonObject().get("type").getAsString()), new Location(Bukkit.getWorld(getWarp().getName()), jsonElement1.getAsJsonObject().get("x").getAsDouble(), jsonElement1.getAsJsonObject().get("y").getAsDouble(), jsonElement1.getAsJsonObject().get("z").getAsDouble()))));
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        leveis.put(getName(), this);
        Bukkit.getConsoleSender().sendMessage(MapUtils.PREFIX + "§fLevel §a" + name + "§f registrado!");
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public List<MapCoin> getLoadedCoins() {
        return loadedCoins;
    }

    public List<MapMob> getLoadedMobs() {
        return loadedMobs;
    }

    public Warp getWarp() {
        return warp;
    }

    public Location getPortalSpec() {
        return portalSpec;
    }

    public MapSound getBackgroundMapSound() {
        return tempBackgroungMapSound != null ? tempBackgroungMapSound : backgroundMapSound;
    }

    public void setTempBackgroungMapSound(MapSound tempBackgroungMapSound) {
        this.tempBackgroungMapSound = tempBackgroungMapSound;
    }

    public PotionEffect getPotionEffect() {
        return tempPotionEffect != null ? tempPotionEffect : potionEffect;
    }

    public void setTempPotionEffect(PotionEffect tempPotionEffect) {
        this.tempPotionEffect = tempPotionEffect;
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
            int times = 0;

            @Override
            public void run() {
                times++;
                if (times <= 55) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.teleport(getPortalSpec());
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(MapUtils.getPlugin(), 1, 1);
        new BukkitRunnable() {
            @Override
            public void run() {
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

        tempPotionEffect = null;
        tempBackgroungMapSound = null;
        onLoad();

        FinalLevelEvents.teleport = false;
        MusicUpdaterEvents.musicPause = false;

        for (Player player : Bukkit.getOnlinePlayers()) {
            InventoryBase.clearInventory(player);
            MapUtils.sendTitle(player, "§l§oCarregando...", "§f§oAguarde", 0, 20, 20);
            MapUtils.playSound(player, MapSound.EFFECT_LETSGO);
            player.addPotionEffect(blindness);
        }
        loadCoins();
        loadMobs();
        new BukkitRunnable() {
            @Override
            public void run() {
                Level.currentLevel = null;
                CoinEvents.coins = 0;
                CoinEvents.redCoins = 0;
                CoinEvents.blueCoins = 0;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.teleport(getWarp().getLocation());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            MapUtils.playSound(player, getBackgroundMapSound(), SoundCategory.AMBIENT);
                            loadCooldown = false;
                            MapUtils.sendTitle(player, "§a§l" + getName(), "§f§oSeja bem-vindo ao level §a§o" + getWarp().toString().replace("L_", ""), 20, 40, 20);
                        }
                    }.runTaskLater(MapUtils.getPlugin(), 10);
                }
                getObjectives().values().forEach(levelObjective -> {
                    levelObjective.setCanGetStar(levelObjective.canGetStarSettings());
                    if (levelObjective.isAutoSpawn()) {
                        levelObjective.spawnStar(false, null);
                    }
                });
            }
        }.runTaskLater(MapUtils.getPlugin(), 20);
    }

    public void onLoad() {
    }

    public void loadCoins() {
        getLoadedCoins().forEach(MapCoin::spawn);
    }

    public void loadMobs() {
        getLoadedMobs().forEach(MapMob::spawn);
    }

    public void unloadCoins() {
        getLoadedCoins().forEach(MapCoin::despawn);
        for (Entity entity : getWarp().getLocation().getWorld().getEntities()) {
            if (entity instanceof Item) {
                entity.remove();
            }
        }
    }

    public void unloadMobs() {
        getLoadedMobs().forEach(MapMob::despawn);

        List<EntityType> types = new ArrayList<>();
        for (MapMob mapMob : getLoadedMobs()) {
            if (!types.contains(mapMob.getEntityType())) {
                types.add(mapMob.getEntityType());
            }
        }

        for (Entity entity : getWarp().getLocation().getWorld().getEntities()) {
            if (types.contains(entity.getType())) {
                entity.remove();
            }
        }
    }

    public void unloadLevel(LevelObjective objective) {
        boolean already = false;

        boolean unload = objective == null || objective.isUnloadLevel();

        if (objective != null) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                MapUtils.playSound(player, MapSound.STOP);
                MapUtils.playSound(player, MapSound.EFFECT_JOINING);
            }

            if (objective.canGetStar()) {

                if (!objective.isFinished()) {
                    objective.setFinished(true);
                    Level.stars++;
                } else {
                    already = true;
                }
            } else {

                objective.setCanGetStar(true);
                objective.ifGetStar();
                return;
            }
        }
        save();
        if (unload) {

            tempPotionEffect = null;
            tempBackgroungMapSound = null;

            CoinEvents.coins = 0;
            CoinEvents.redCoins = 0;
            CoinEvents.blueCoins = 0;

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
                player.setGameMode(GameMode.SPECTATOR);
            }

            for (LevelObjective levelObjective : getObjectives().values()) {
                levelObjective.setCanGetStar(levelObjective.canGetStarSettings());
            }

            unloadCoins();
            unloadMobs();
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
                } else {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.setGameMode(GameMode.ADVENTURE);
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
                                }
                            }
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.setGameMode(GameMode.ADVENTURE);
                            }
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (objective != null) {
                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                            MapUtils.sendTitle(player, (Already ? "§7§lOBJETIVO JÁ CONCLUÍDO!" : "§a§lOBJETIVO CONCLUÍDO!"), "§f§o" + objective.getStringObjective(), 10, 40, 20);
                                            MapUtils.playSound(player, MapSound.EFFECT_COMPLETE);
                                        }

                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                for (LittleDoor littleDoor : LittleDoor.allDoors) {
                                                    if (Level.stars == littleDoor.getNeedStars()) {
                                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                                            MapUtils.sendTitle(player, "§b§l§oPorta Destravada!", "§f§oA porta para o level §b§o" + littleDoor.getName() + "§f abriu!", 10, 40, 20);
                                                            MapUtils.playSound(player, MapSound.EFFECT_OPEN_DOOR);
                                                        }
                                                    }
                                                }

                                                for (Door door : Door.allDoors) {
                                                    if (Level.stars == door.getNeedStars()) {
                                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                                            MapUtils.sendTitle(player, "§a§l§oPortão Destravado!", "§f§l§oO portão para o nível §b§o" + door.getName() + "§f abriu!", 10, 40, 20);
                                                            MapUtils.playSound(player, MapSound.EFFECT_OPEN_DOOR);
                                                        }
                                                    }
                                                }
                                            }
                                        }.runTaskLater(MapMain.getPlugin(), 100);
                                    }
                                }
                            }.runTaskLater(MapUtils.getPlugin(), 5);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (unload) {
                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                            MapUtils.playSound(player, MapSound.CASTLE_MUSIC, SoundCategory.AMBIENT);
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

    public void addMob(Location location, EntityType entityType) {
        getLoadedMobs().add(new MapMob(entityType, location));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", location.getX());
        jsonObject.addProperty("y", location.getY());
        jsonObject.addProperty("z", location.getZ());
        jsonObject.addProperty("type", entityType.toString());
        jsonElement.getAsJsonObject().get("mobs").getAsJsonArray().add(jsonObject);
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
