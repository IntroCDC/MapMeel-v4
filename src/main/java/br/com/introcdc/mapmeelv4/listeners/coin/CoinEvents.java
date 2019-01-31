package br.com.introcdc.mapmeelv4.listeners.coin;
/*
 * Written by IntroCDC, Bruno Co�lho at 13/05/2018 - 08:35
 */

import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CoinEvents implements Listener {

    public static long coins = 0;
    public static long redCoins = 0;

    @EventHandler(priority = EventPriority.LOW)
    public void onPick(PlayerPickupItemEvent event) {
        for (CoinType coinType : CoinType.values()) {
            if (event.getItem().getItemStack().getType().equals(coinType.getMaterial()) && event.getItem().getItemStack().getDurability() == coinType.getBytes()) {
                event.setCancelled(true);
                if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                    return;
                }
                event.getItem().remove();
                long old = coins;
                coins += coinType.getCoins();
                Level level = Level.getLevel(event.getPlayer().getWorld().getName());
                if (old < 100 && coins >= 100 && level != null) {
                    level.getObjectives().get("Colete 100 Moedas").spawnStar(true, event.getPlayer().getLocation().clone().add(3, 3, 3));
                }
                if (coinType.equals(CoinType.X2)) {
                    redCoins++;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        MapUtils.sendTitle(player, "�f", "�c�l" + redCoins, 0, 20, 10);
                    }
                }
                if (level != null) {
                    if (redCoins == 8) {
                        if (level.getObjectives().containsKey("Pegue 8 Cora��es")) {
                            level.getObjectives().get("Pegue 8 Cora��es").spawnStar(true, null);
                        }
                    }
                }
                new BukkitRunnable() {
                    int times = 0;

                    @Override
                    public void run() {
                        times++;
                        if (times <= coinType.getCoins()) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                MapUtils.playSound(player, MapSound.EFFECT_COIN, -2);
                            }
                        } else {
                            cancel();
                        }
                    }
                }.runTaskTimer(MapUtils.getPlugin(), 0, 2);
            }
        }
    }

}
