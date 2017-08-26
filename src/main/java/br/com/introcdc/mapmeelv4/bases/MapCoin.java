package br.com.introcdc.mapmeelv4.bases;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.CoinType;

public class MapCoin extends MapUtils {

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
        Item item = this.location.getWorld().dropItem(this.location, itemBuilder(new ItemStack(this.type.getMaterial(), this.type.getBytes())).setName("§fMoeda").toItem());
        item.setGravity(false);
        item.setInvulnerable(true);
        this.item = item;
    }

}
