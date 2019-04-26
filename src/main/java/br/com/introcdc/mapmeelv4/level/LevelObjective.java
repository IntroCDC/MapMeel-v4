package br.com.introcdc.mapmeelv4.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 08:04
 */

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
    public Level level;
    private Item item;
    private Vector nullVector = null;
    private boolean autoSpawn;

    public LevelObjective(String stringObjective, Location location) {
        this.stringObjective = stringObjective;
        this.location = location;
        this.autoSpawn = false;
    }

    public LevelObjective(String stringObjective, Location location, boolean autoSpawn) {
        this.stringObjective = stringObjective;
        this.location = location;
        this.autoSpawn = autoSpawn;
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

    public boolean isAutoSpawn() {
        return autoSpawn;
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

    private void spawn(Location location) {
        if (location == null) {
            location = this.location;
        }
        if (nullVector == null) {
            nullVector = new Vector(0, 1, 0);
        }
        this.despawn();
        location.getChunk().load();
        Item item = location.getWorld().dropItem(location, MapUtils.itemBuilder(new ItemStack(Material.PLAYER_HEAD)).setOwner("iMeel").setName(getStringObjective()).toItem());
        item.setGravity(true);
        item.setInvulnerable(true);
        item.setVelocity(nullVector);
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
