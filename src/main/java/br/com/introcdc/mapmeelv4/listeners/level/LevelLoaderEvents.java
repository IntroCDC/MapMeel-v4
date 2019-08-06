package br.com.introcdc.mapmeelv4.listeners.level;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:34
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.histories.HistoryOne;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.listeners.join.JoinEvents;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SoundCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.NamespacedKey.minecraft;

public class LevelLoaderEvents implements Listener {

    private static final MaterialSetTag BUTTONS = new MaterialSetTag(minecraft("buttons"))
            .endsWith("_BUTTON");

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (event.getClickedBlock() != null) {
                if (event.getClickedBlock().getType().equals(Material.FARMLAND)) {
                    event.setCancelled(true);
                }
            }
        }

        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock() != null && BUTTONS.isTagged(event.getClickedBlock()) && event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
                event.setCancelled(true);
                if (Level.currentLevel != null) {
                    Level.currentLevel.loadLevel();
                } else {
                    if (JoinEvents.buttonPlay.contains(event.getPlayer().getUniqueId())) {
                        JoinEvents.buttonPlay.remove(event.getPlayer().getUniqueId());
                        MapUtils.playSound(event.getPlayer(), MapSound.STOP, SoundCategory.AMBIENT);
                        MapUtils.playSound(event.getPlayer(), MapSound.EFFECT_JOINING);
                        MapUtils.sendTitle(event.getPlayer(), "§5§l§oMap§f§l§oMeel v4", "§f§oSeja bem-vindo ao MapMeel v4!", 10, 30, 20);
                        event.getPlayer().teleport(Warp.LOBBY.getLocation());

                        if (Level.currentLevel != null) {
                            event.getPlayer().teleport(Level.currentLevel.getWarp().getLocation());
                            Level.currentLevel.welcome(event.getPlayer());
                        } else {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    HistoryOne.start(event.getPlayer());
                                }
                            }.runTaskLater(MapMain.getPlugin(), 20);
                        }
                    }
                }
            }
        }

        if (event.getClickedBlock() != null && event.getClickedBlock().getType().toString().contains("CHEST") && event.getAction().toString().contains("RIGHT")) {
            event.setCancelled(true);
        }

        if (event.getClickedBlock() != null && event.getAction().toString().contains("LEFT")) {
            if (event.getClickedBlock().getType().equals(Material.PAINTING) || event.getClickedBlock().getType().equals(Material.ITEM_FRAME)) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getWorld().getName().equalsIgnoreCase("world")) {
            if (event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
                for (Level level : Level.getLeveis().values()) {
                    if (event.getPlayer().getLocation().distance(level.getPortalSpec()) < 15) {
                        if (event.getTo().getBlock().getType() == level.getMaterial()) {
                            Level.currentLevel = level;
                            level.joinPortal();
                        }
                    }
                }
            }
        }
    }

}
