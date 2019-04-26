package br.com.introcdc.mapmeelv4.armorpath;
/*
 * Written by IntroCDC, Bruno Coêlho at 25/04/2019 - 16:59
 */

import br.com.introcdc.mapmeelv4.MapMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class ArmorStandPath {

    private static final int TICKS = 50;

    private List<Location> locations;
    private Queue<Location> queue;

    private ArmorStand entity;

    private Location from;
    private Location to;
    private float progress;

    private boolean ended;
    private boolean started;

    public ArmorStandPath(List<Location> locations) {
        this.locations = locations;
        this.queue = new ArrayDeque<>(locations);
    }

    public void start() {
        if (started) {
            return;
        }
        this.started = true;

        Location pos = this.takeLocation();
        this.entity = pos.getWorld().spawn(pos, ArmorStand.class);
        this.entity.setGravity(false);

        this.from = pos;
        this.to = this.takeLocation();

        this.updater();
    }

    public void updater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasEnded()) {
                    this.cancel();

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.setGameMode(GameMode.ADVENTURE);
                        onEnd(player);
                    }

                    entity.remove();
                    return;
                }
                update();
            }
        }.runTaskTimer(MapMain.getPlugin(), 1, 1);
    }

    public void onEnd(Player player) {
    }

    public void update() {
        ArmorStand entity = this.entity;

        Location from = this.from;
        Location to = this.to;

        Location pos = entity.getLocation();

        this.progress = Math.min(this.progress + 0.015F, 1.0F);

        pos.setX(interpolateAxis(from.getX(), to.getX()));
        pos.setY(interpolateAxis(from.getY(), to.getY()));
        pos.setZ(interpolateAxis(from.getZ(), to.getZ()));
        pos.setYaw(interpolateAngle(from.getYaw(), to.getYaw()));
        pos.setPitch(interpolateAngle(from.getPitch(), to.getPitch()));

        entity.teleport(pos);

        if (Math.abs(pos.distance(to)) == 0) {
            this.from = to;
            this.to = this.takeLocation();
            this.progress = 0.0F;

            if (this.to == null) {
                this.ended = true;
            } else {

                if (this.from.distance(this.to) > 250) {
                    entity.teleport(this.to);
                }

            }
        }
    }

    private double interpolateAxis(double from, double to) {
        return from + (to - from) * this.progress;
    }

    private float interpolateAngle(float from, float to) {
        return from + (to - from) * this.progress;
    }

    public void watch(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(this.entity);
    }

    public boolean hasEnded() {
        return this.ended;
    }

    private Location takeLocation() {
        return this.queue.poll();
    }

}
