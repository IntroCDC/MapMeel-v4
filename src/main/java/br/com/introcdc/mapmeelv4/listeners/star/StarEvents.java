package br.com.introcdc.mapmeelv4.listeners.star;
/*
 * Written by IntroCDC, Bruno Coêlho at 16/05/2018 - 02:52
 */

import br.com.introcdc.mapmeelv4.histories.FinalHistory;
import br.com.introcdc.mapmeelv4.item.InventoryBase;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.listeners.coin.CoinEvents;
import br.com.introcdc.mapmeelv4.listeners.music.MusicUpdaterEvents;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class StarEvents implements Listener {

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        if (event.getItem() != null && event.getItem().getItemStack() != null && event.getItem().getItemStack().getItemMeta() != null && event.getItem().getItemStack().getItemMeta().getDisplayName() != null) {
            if (event.getItem().getItemStack().getType().equals(Material.PLAYER_HEAD)) {
                if (Level.getLevel(event.getPlayer().getWorld().getName()) != null) {
                    if (Level.getLevel(event.getPlayer().getWorld().getName()).getObjectives().containsKey(event.getItem().getItemStack().getItemMeta().getDisplayName())) {
                        event.setCancelled(true);

                        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            return;
                        }

                        event.getItem().remove();

                        MusicUpdaterEvents.musicPause = false;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.setGameMode(GameMode.SPECTATOR);
                        }

                        if (event.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Mate o Chefão")) {

                            LevelObjective objective = Level.getLevel(event.getPlayer().getWorld().getName()).getObjectives().get("Mate o Chefão");

                            if (!objective.isFinished()) {
                                objective.setFinished(true);
                                Level.stars++;
                            }

                            Level.getLevel(event.getPlayer().getWorld().getName()).save();
                            CoinEvents.coins = 0;
                            CoinEvents.redCoins = 0;
                            CoinEvents.blueCoins = 0;
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
                                InventoryBase.clearInventory(player);
                            }
                            Level.getLevel(event.getPlayer().getWorld().getName()).unloadCoins();
                            Level.getLevel(event.getPlayer().getWorld().getName()).unloadMobs();

                            FinalHistory.startFinalHistory();

                        } else {
                            Level.getLevel(event.getPlayer().getWorld().getName())
                                    .unloadLevel(
                                            Level.getLevel(event.getPlayer().getWorld().getName()).getObjectives().get(event.getItem().getItemStack().getItemMeta().getDisplayName())
                                    );
                        }

                    }
                }
            }
        }
    }

}
