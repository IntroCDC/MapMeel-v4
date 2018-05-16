package br.com.introcdc.mapmeelv4.hologram;

import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class MapHologram implements Listener {

    private NPC armorStand;
    private Location location;
    private String text;
    private boolean visible;

    public MapHologram(String text, Location location) {
        this.text = text;
        this.location = location;
        this.visible = true;
        NPC armorStand = MapUtils.createNPC(EntityType.ARMOR_STAND, text, location);
        ArmorStand dasdsadsadassda = (ArmorStand) armorStand.getEntity();
        dasdsadsadassda.setVisible(false);
        dasdsadsadassda.setGravity(false);
        dasdsadsadassda.setMaxHealth(20);
        dasdsadsadassda.setHealth(20);
        dasdsadsadassda.setRemoveWhenFarAway(false);
        this.armorStand = armorStand;
        this.reloadCustomName();
        Bukkit.getPluginManager().registerEvents(this, MapUtils.getPlugin());
    }

    public void customUnload() {
        this.isVisible();
    }

    public NPC getArmorStand() {
        return this.armorStand;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getText() {
        return this.text;
    }

    public boolean isVisible() {
        return this.visible;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!event.getEntity().equals(getArmorStand())) {
            return;
        }
        event.setCancelled(true);
        ((ArmorStand) event.getEntity()).setHealth(20);
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (!event.getRightClicked().equals(getArmorStand())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        if (!event.getRightClicked().equals(getArmorStand())) {
            return;
        }
        event.setCancelled(true);
    }

    private void reloadCustomName() {
        this.getArmorStand().teleport(this.getLocation(), TeleportCause.PLUGIN);
        if (this.isVisible()) {
            this.getArmorStand().setName(this.getText());
        } else {
            this.getArmorStand().setName("§f");
        }
    }

    public void setLocation(Location location) {
        this.location = location;
        this.reloadCustomName();
    }

    public void setText(String text) {
        this.text = text;
        this.reloadCustomName();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        this.reloadCustomName();
    }

    public void unload() {
        HandlerList.unregisterAll(this);
        this.customUnload();
        this.setVisible(false);
        if (this.getArmorStand().isSpawned()) {
            this.getArmorStand().despawn();
        }
        CitizensAPI.getNPCRegistry().deregister(this.getArmorStand());
    }

}
