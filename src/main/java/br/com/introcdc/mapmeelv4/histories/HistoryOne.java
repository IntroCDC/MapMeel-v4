package br.com.introcdc.mapmeelv4.histories;

import br.com.introcdc.mapmeelv4.enums.MapSound;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.Warp;
import org.bukkit.util.Vector;

public class HistoryOne extends MapUtils implements Listener {

    public static PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 200, 10);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws Exception {
        MapProfile profile = getProfile(event.getPlayer().getName());
        if (profile.getAward("level") == 1) {
            playSound(event.getPlayer(), MapSound.STARTING, 32);
            profile.setAward("level", 2);
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            float fly = event.getPlayer().getFlySpeed();
            event.getPlayer().setFlySpeed(0.0f);
            event.getPlayer().getLocation().setDirection(new Vector(0.7136908887404638, 0.14349272082621858, -0.6856056843395743));
            event.getPlayer().setVelocity(new Vector(0.7136908887404638, 0.14349272082621858, -0.6856056843395743).multiply(1.5f));
        }
    }

}
