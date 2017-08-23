package br.com.introcdc.mapmeelv4.histories;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.Warp;

public class LevelOne extends MapUtils implements Listener {

    public static PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 200, 10);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws Exception {
        MapProfile profile = getProfile(event.getPlayer().getName());
        if (profile.getAwards().get("level") == 1) {
            event.getPlayer().teleport(Warp.LOBBY.getLocation());
            sendTitle(event.getPlayer(), "§8", "§7§oPor que esta história teve um fim?", 20, 60, 20);
            event.getPlayer().getWorld().strikeLightningEffect(event.getPlayer().getLocation());
            event.getPlayer().addPotionEffect(blindness);
            for (int i = 0; i < 3; i++) {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GHAST_SCREAM, 50000, 0.5f);
            }
            profile.setAward("level", 2);
        }
    }

}
