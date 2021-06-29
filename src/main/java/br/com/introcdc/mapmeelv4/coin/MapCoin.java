package br.com.introcdc.mapmeelv4.coin;

import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class MapCoin {

    private final Vector VECTOR = new Vector(0, 0, 0);

    private Item item;
    private Location location;
    private CoinType type;

    public MapCoin(Location location, CoinType type) {
        this.location = location;
        this.type = type;
    }

    public void despawn() {
        if (this.item != null) {
            if (!this.location.getChunk().isLoaded()) {
                this.location.getChunk().load();
            }
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

    public void spawn() {
        this.despawn();
        if (!this.location.getChunk().isLoaded()) {
            this.location.getChunk().load();
        }
        Item item = this.location.getWorld().dropItem(this.location,
                MapUtils.itemBuilder(new ItemStack(this.type.getMaterial())).setName("§fMoeda").toItem());
        item.setGravity(false);
        item.setInvulnerable(true);
        item.setVelocity(VECTOR);
        this.item = item;
    }

}
