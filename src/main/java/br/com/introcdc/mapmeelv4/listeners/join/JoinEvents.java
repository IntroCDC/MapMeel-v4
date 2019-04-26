package br.com.introcdc.mapmeelv4.listeners.join;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:35
 */

import br.com.introcdc.mapmeelv4.profile.Cargo;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinEvents implements Listener {

    public static boolean onlyStaff = false;
    public static boolean avaibledForAll = true;

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent event) throws Exception {
        event.setJoinMessage(null);
        MapUtils.getProfile(event.getPlayer().getName());
        event.getPlayer().setOp(MapUtils.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.ADMIN));
        event.getPlayer().teleport(Warp.LOBBY.getLocation());
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        event.getPlayer().getActivePotionEffects().forEach(effect -> event.getPlayer().removePotionEffect(effect.getType()));
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLogin(AsyncPlayerPreLoginEvent event) throws Exception {
        if (!MapUtils.getProfile(event.getName()).existsProfile() && !avaibledForAll) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, MapUtils.PREFIX + "§cO projeto MapMeel v4 é um projeto privado e não liberado para pessoas de fora!\n\n§c§o(Sua conta não foi encontrada!)");
            return;
        }
        if (!MapUtils.getProfile(event.getName()).getCargo().isStaff() && onlyStaff) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, MapUtils.PREFIX + "§cO servidor MapMeel v4 atualmente está liberado apenas para Staffers!");
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onQuit(PlayerQuitEvent event) throws Exception {
        event.setQuitMessage(null);
        event.getPlayer().setOp(false);
        MapUtils.getProfile(event.getPlayer().getName()).unload();
    }

}
