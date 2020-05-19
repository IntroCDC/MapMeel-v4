package br.com.introcdc.mapmeelv4.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 08:04
 */

import br.com.introcdc.mapmeelv4.advancement.CustomAdvancement;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class LevelObjective {

    public String stringObjective;
    public Location location;
    public boolean finished;
    public CustomAdvancement customAdvancement;
    private String whoFinished;
    public Level level;
    private Item item;
    private Vector nullVector = new Vector(0, 1, 0);
    private boolean autoSpawn;
    private boolean unloadLevel = true;
    private boolean canGetStar = true;

    private final boolean canGetStarSettings;

    public LevelObjective(String stringObjective, Location location, boolean autoSpawn) {
        this.stringObjective = stringObjective;
        this.location = location;
        this.autoSpawn = autoSpawn;
        this.canGetStarSettings = true;
    }

    public LevelObjective(String stringObjective, Location location, boolean autoSpawn, boolean unloadLevel) {
        this.stringObjective = stringObjective;
        this.location = location;
        this.autoSpawn = autoSpawn;
        this.unloadLevel = unloadLevel;
        this.canGetStarSettings = true;
    }

    public LevelObjective(String stringObjective, Location location, boolean autoSpawn, boolean unloadLevel, boolean canGetStar) {
        this.stringObjective = stringObjective;
        this.location = location;
        this.autoSpawn = autoSpawn;
        this.unloadLevel = unloadLevel;
        this.canGetStar = canGetStar;
        this.canGetStarSettings = canGetStar;
    }

    public String getStringObjective() {
        return stringObjective;
    }

    public String getWhoFinished() {
        return whoFinished;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished, String whoFinished) {
        if (!this.finished && whoFinished != null) {
            this.whoFinished = whoFinished;
        }
        if (this.finished && this.whoFinished != null) {
            this.whoFinished = null;
        }
        this.finished = finished;
        if (customAdvancement != null) {
            if (finished) {
                customAdvancement.awardAllPlayers();
            } else {
                customAdvancement.revokeAllPlayers();
            }
        }
    }

    public boolean isAutoSpawn() {
        return autoSpawn;
    }

    public boolean isUnloadLevel() {
        return unloadLevel;
    }

    public void setUnloadLevel(boolean unloadLevel) {
        this.unloadLevel = unloadLevel;
    }

    public Level getLevel() {
        return level;
    }

    public Item getItem() {
        return item;
    }

    public Vector getNullVector() {
        return nullVector;
    }

    public Location getLocation() {
        return location;
    }

    public void spawnStar(boolean effect, Location location) {
        if (location == null) {
            location = this.location;
        }
        spawn(location);
        if (effect) {
            for (Player player : Bukkit.getWorld(level.getWarp().getWorld()).getPlayers()) {
                Location playerLocation = player.getLocation();
                float fly = player.getFlySpeed();
                player.setFlySpeed(0.0f);
                player.setGameMode(GameMode.SPECTATOR);
                player.teleport(MapUtils.getLocation(level.getWarp().getWorld(), location.getX() + 3, location.getY() + 3, location.getZ() + 3, location));
                MapUtils.playSound(player, MapSound.EFFECT_STAR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setFlySpeed(fly);
                        player.setGameMode(GameMode.ADVENTURE);
                        player.teleport(playerLocation);
                    }
                }.runTaskLater(MapUtils.getPlugin(), 60);
            }
        }
    }

    public boolean canGetStarSettings() {
        return canGetStarSettings;
    }

    public boolean canGetStar() {
        return canGetStar;
    }

    public void setCanGetStar(boolean canGetStar) {
        this.canGetStar = canGetStar;
    }

    public void ifGetStar() {
    }

    public void onLoadLevel() {
    }

    private void spawn(Location location) {
        if (location == null) {
            location = this.location;
        }
        this.despawn();
        location.getChunk().load();

        Item item = location.getWorld().dropItem(location, MapUtils.itemBuilder(new ItemStack(Material.PLAYER_HEAD)).setOwner("iMeel").setName(getStringObjective()).toItem());

        item.setGravity(true);
        item.setInvulnerable(true);
        item.setVelocity(nullVector);
        item.setPickupDelay(5 * 20);

        this.item = item;
    }

    public void despawn() {
        if (this.item != null) {
            this.location.getChunk().load();
            this.item.remove();
            this.item = null;
        }
    }

    public void setup(Level level) {
        this.level = level;
    }

}
