package br.com.introcdc.mapmeelv4.listeners;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 07:04
 */

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MoveEvent implements Listener {

    public static List<UUID> spongeDamage = new ArrayList<>();
    public static Vector vector = new Vector(0, 2, 0);

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getWorld().getName().equalsIgnoreCase("world")) {
            for (Level level : Level.getLeveis().values()) {
                if (event.getTo().getBlock().getType().equals(level.getBlockId().getMaterial()) && event.getTo().getBlock().getData() == level.getBlockId().getData()) {
                    level.joinPortal(event.getPlayer());
                    Level.currentLevel = level;
                }
            }
        } else if (Level.currentLevel != null) {
            for (LevelObjective levelObjective : Level.currentLevel.getObjectives()) {
                if (event.getTo().getBlock().getType().equals(levelObjective.getBlockId().getMaterial()) && event.getTo().getBlock().getData() == levelObjective.getBlockId().getData()) {
                    Level.currentLevel.unloadLevel();
                }
            }
        }
        if (event.getTo().clone().add(0, -1, -0).getBlock().getType().equals(Material.SPONGE)) {
            if (!spongeDamage.contains(event.getPlayer().getUniqueId())) {
                spongeDamage.add(event.getPlayer().getUniqueId());
            }
            event.getPlayer().setVelocity(vector);
        }
    }

}
