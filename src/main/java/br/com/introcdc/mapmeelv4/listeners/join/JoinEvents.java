package br.com.introcdc.mapmeelv4.listeners.join;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 08:35
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.profile.Cargo;
import br.com.introcdc.mapmeelv4.profile.MapProfile;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JoinEvents implements Listener {

    public static boolean onlyStaff = false;
    public static boolean avaibledForAll = true;

    public static List<UUID> buttonPlay = new ArrayList<>();

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent event) throws Exception {
        event.setJoinMessage(null);
        MapUtils.getProfile(event.getPlayer().getName());
        event.getPlayer().setOp(MapUtils.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.ADMIN));
        event.getPlayer().teleport(Warp.LOBBY.getLocation());
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        event.getPlayer().getActivePotionEffects().forEach(effect -> event.getPlayer().removePotionEffect(effect.getType()));

        MapProfile mapProfile = new MapProfile(event.getPlayer().getName());
        if (!mapProfile.existsProfile()) {
            mapProfile.createFile();
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!buttonPlay.contains(event.getPlayer().getUniqueId())) {
                    buttonPlay.add(event.getPlayer().getUniqueId());
                    event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -109.0, 47, 235.5, -90, 0));
                    event.getPlayer().setResourcePack("http://local.introbase64.com.br:8080/MapMeelv4Texture.zip");
                }
            }
        }.runTaskLater(MapMain.getPlugin(), 20);
    }

    @EventHandler
    public void onJoin(PlayerResourcePackStatusEvent event) throws Exception {
        if (event.getStatus().equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)) {
            event.getPlayer().sendMessage(MapUtils.PREFIX + "§f§oTextura carregada! Clique para iniciar!");
            MapUtils.playSound(event.getPlayer(), MapSound.MUSIC_THEME, SoundCategory.AMBIENT);
        } else if (event.getStatus().equals(PlayerResourcePackStatusEvent.Status.ACCEPTED)) {
            event.getPlayer().sendMessage(MapUtils.PREFIX + "§f§oBaixando...");
        } else if (event.getStatus().equals(PlayerResourcePackStatusEvent.Status.DECLINED)) {
            event.getPlayer().kickPlayer(MapUtils.PREFIX + "§4§o§lO uso de textura do MapMeel v4 é obrigatório!");
        } else {
            event.getPlayer().kickPlayer(MapUtils.PREFIX + "§4§o§lO uso de textura do MapMeel v4 é obrigatório! (Ocorreu um erro)");
        }
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

        buttonPlay.remove(event.getPlayer().getUniqueId());

        if (Bukkit.getOnlinePlayers().size() == 1) {
            if (Level.currentLevel != null) {
                Level.currentLevel.unloadLevel(null);
            }
        }

    }

}
