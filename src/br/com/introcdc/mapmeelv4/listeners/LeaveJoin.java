package br.com.introcdc.mapmeelv4.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;

public class LeaveJoin extends MapUtils implements Listener {

    public static boolean onlyStaff = true;

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(final PlayerJoinEvent event) throws Exception {
        event.setJoinMessage(null);
        MapProfile profile = getProfile(event.getPlayer().getName());
        if (!profile.existsProfile()) {
            profile.createFile();
        }
        if (!profile.getAwards().containsKey("Level")) {
            profile.setAward("level", 1);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLogin(final AsyncPlayerPreLoginEvent event) throws Exception {
        if (!getProfile(event.getName()).existsProfile()) {
            event.disallow(Result.KICK_OTHER, PREFIX + "�cO projeto MapMeel v4 � um projeto privado e n�o liberado para pessoas de fora!\n\n�c�o(Sua conta n�o foi encontrada!)");
            return;
        }
        if (!getProfile(event.getName()).getCargo().isStaff() && onlyStaff) {
            event.disallow(Result.KICK_OTHER, PREFIX + "�cO servidor MapMeel v4 atualmente est� liberado apenas para Staffers!");
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onQuit(final PlayerQuitEvent event) throws Exception {
        event.setQuitMessage(null);
        getProfile(event.getPlayer().getName()).unload();
    }

}
