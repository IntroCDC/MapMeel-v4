package br.com.introcdc.mapmeelv4.bases;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.CoinType;

public class MapCoin extends MapUtils {

    private boolean geted;
    private Item item;
    private final Location location;
    private final CoinType type;

    public MapCoin(final Location location, final CoinType type) {
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
        final Item item = this.location.getWorld().dropItem(this.location, itemBuilder(new ItemStack(this.type.getMaterial(), this.type.getBytes())).setName("§fMoeda").toItem());
        item.setGravity(false);
        item.setInvulnerable(true);
        this.item = item;
    }

}
