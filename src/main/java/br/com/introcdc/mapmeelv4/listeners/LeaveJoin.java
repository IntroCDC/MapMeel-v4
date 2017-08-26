package br.com.introcdc.mapmeelv4.listeners;

import br.com.introcdc.mapmeelv4.enums.Warp;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.introcdc.mapmeelv4.MapUtils;

public class LeaveJoin extends MapUtils implements Listener {

    public static boolean onlyStaff = true;

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent event) throws Exception {
        event.setJoinMessage(null);
        getProfile(event.getPlayer().getName());
        event.getPlayer().teleport(Warp.LOBBY.getLocation());
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLogin(AsyncPlayerPreLoginEvent event) throws Exception {
        if (!getProfile(event.getName()).existsProfile()) {
            event.disallow(Result.KICK_OTHER, PREFIX + "§cO projeto MapMeel v4 é um projeto privado e não liberado para pessoas de fora!\n\n§c§o(Sua conta não foi encontrada!)");
            return;
        }
        if (!getProfile(event.getName()).getCargo().isStaff() && onlyStaff) {
            event.disallow(Result.KICK_OTHER, PREFIX + "§cO servidor MapMeel v4 atualmente está liberado apenas para Staffers!");
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onQuit(PlayerQuitEvent event) throws Exception {
        event.setQuitMessage(null);
        getProfile(event.getPlayer().getName()).unload();
    }

}
