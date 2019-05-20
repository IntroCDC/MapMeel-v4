package br.com.introcdc.mapmeelv4.mob;
/*
 * Written by IntroCDC, Bruno Coêlho at 28/04/2019 - 01:36
 */

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

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
