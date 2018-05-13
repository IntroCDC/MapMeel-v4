package br.com.introcdc.mapmeelv4.bases;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.CoinType;
import org.bukkit.util.Vector;

public class MapCoin extends MapUtils {

    private static Vector nullVector = new Vector(0, 0, 0);

    private boolean geted;
    private Item item;
    private Location location;
    private CoinType type;

    public MapCoin(Location location, CoinType type) {
        this.location = location;
        this.type = type;
    }

    public void despawn() {
        if (this.item != null) {
            this.item.remove();
            this.item = null;
        }
    }

    public Item getItem() {
        return this.item;
    }

    public Location getLocation() {
        return this.location;
    }

    public CoinType getType() {
        return this.type;
    }

    public boolean isGeted() {
        return this.geted;
    }

    public void spawn() {
        this.despawn();
        this.location.getChunk().load();
        Item item = this.location.getWorld().dropItem(this.location, itemBuilder(new ItemStack(this.type.getMaterial(), 1, this.type.getBytes())).setName("§fMoeda").toItem());
        item.setGravity(false);
        item.setInvulnerable(true);
        item.setVelocity(nullVector);
        this.item = item;
    }

}
