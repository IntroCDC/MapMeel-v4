package br.com.introcdc.mapmeelv4.listeners.coin;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:35
 */

import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class CoinEvents implements Listener {

    public static long coins = 0;
    public static long redCoins = 0;
    public static long blueCoins = 0;

    @EventHandler(priority = EventPriority.LOW)
    public void onPick(PlayerPickupItemEvent event) {
        for (CoinType coinType : CoinType.values()) {
            if (event.getItem().getItemStack().getType().equals(coinType.getMaterial())) {
                event.setCancelled(true);
                if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                    return;
                }

                event.getItem().remove();

                long old = coins;
                coins += coinType.getCoins();

                int air = coinType.getCoins() * 100;

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setRemainingAir(Math.min(player.getRemainingAir() + air, 300));
                    player.setHealth(Math.min(player.getHealth() + (coinType.getCoins() * 2), 20));
                }

                Level level = Level.getLevel(event.getPlayer().getWorld().getName());

                if (old < 100 && coins >= 100 && level != null) {
                    if (level.getObjectives().get("Colete 100 Moedas") != null) {
                        level.getObjectives().get("Colete 100 Moedas").spawnStar(true, event.getPlayer().getLocation().clone());
                    }
                }

                if (coinType.equals(CoinType.X1)) {
                    for (Player player : Bukkit.getOnlinePlayers()) {

                        MapUtils.playSound(player, MapSound.EFFECT_COIN, SoundCategory.BLOCKS);

                    }
                }

                if (coinType.equals(CoinType.X2)) {
                    redCoins++;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        MapUtils.sendTitle(player, "§f", "§c§l" + redCoins, 0, 20, 10);

                        MapUtils.playSound(player, MapSound.EFFECT_COIN, SoundCategory.BLOCKS, 1);
                    }

                    if (level != null) {
                        if (redCoins == 8) {
                            if (level.getObjectives().containsKey("Pegue 8 Corações")) {
                                level.getObjectives().get("Pegue 8 Corações").spawnStar(true, null);
                            }
                        }
                    }

                }
                if (coinType.equals(CoinType.X5)) {
                    blueCoins++;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        MapUtils.sendTitle(player, "§f", "§9§l" + blueCoins, 0, 20, 10);

                        MapUtils.playSound(player, MapSound.EFFECT_COIN, SoundCategory.BLOCKS);
                    }
                }
            }
        }
    }

}
