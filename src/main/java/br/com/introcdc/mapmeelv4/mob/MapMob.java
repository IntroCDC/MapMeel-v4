package br.com.introcdc.mapmeelv4.mob;
/*
 * Written by IntroCDC, Bruno Coêlho at 28/04/2019 - 01:36
 */

import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.villager.VillagerTrade;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

public class MapMob {

    private EntityType entityType;
    private Location location;

    private LivingEntity entity;

    public MapMob(EntityType entityType, Location location) {
        this.entityType = entityType;
        this.location = location;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Entity getEntity() {
        return entity;
    }

    public Location getLocation() {
        return location;
    }

    public void spawn() {
        despawn();

        if (!getLocation().getChunk().isLoaded()) {
            getLocation().getChunk().load();
        }

        this.entity = (LivingEntity) getLocation().getWorld().spawnEntity(getLocation(), getEntityType());
        this.entity.setRemoveWhenFarAway(false);

        if (this.entity.getType().equals(EntityType.VILLAGER)) {
            Villager villager = (Villager) this.entity;
            VillagerTrade trade = MapUtils.getRandom(VillagerTrade.values());
            villager.setVillagerType(Villager.Type.PLAINS);
            villager.setProfession(trade.getProfession());
            villager.setVillagerLevel(5);
            villager.setRecipes(trade.getRecipes());
        }

    }

    public void despawn() {
        if (!getLocation().getChunk().isLoaded()) {
            getLocation().getChunk().load();
        }

        if (getEntity() != null && !getEntity().isDead()) {
            getEntity().remove();
        }

        this.entity = null;
    }

}
