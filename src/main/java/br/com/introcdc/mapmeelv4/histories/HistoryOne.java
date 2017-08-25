package br.com.introcdc.mapmeelv4.histories;

import br.com.introcdc.mapmeelv4.enums.MapSound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.Warp;

public class HistoryOne extends MapUtils implements Listener {

    public static PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 200, 10);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws Exception {
        MapProfile profile = getProfile(event.getPlayer().getName());
        if (profile.getAward("level") == 1) {
            event.getPlayer().teleport(Warp.LOBBY.getLocation());
            playSound(event.getPlayer(), MapSound.STARTING);
            profile.setAward("level", 2);
        }
    }

}
