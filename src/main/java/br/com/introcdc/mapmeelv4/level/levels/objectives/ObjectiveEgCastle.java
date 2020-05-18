package br.com.introcdc.mapmeelv4.level.levels.objectives;
/*
 * Written by IntroCDC, Bruno Coêlho at 28/04/2019 - 00:11
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class ObjectiveEgCastle extends LevelObjective {

    public ObjectiveEgCastle() {
        super("A Torre do Castelo Secundário", new Location(Bukkit.getWorld("EG-CASTLE"), 449, 114, 505, 180, 90), true, true, false);
    }

    public void ifGetStar() {

        getLevel().setTempPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 100));
        getLevel().setTempBackgroungMapSound(MapSound.MUSIC_CUSTOM_DARK_FOREST);
        spawnStar(true, new Location(Bukkit.getWorld("EG-CASTLE"), 515, 95, 495, -179, 90));

        for (Player player : Bukkit.getOnlinePlayers()) {
            MapUtils.playSound(player, MapSound.STOP);
            MapUtils.playSound(player, MapSound.MUSIC_CUSTOM_DARK_FOREST);
        }

        List<String> messages = Arrays.asList(
                "Why i'm alive?", "Who are you?", "WHY THE FUCK I STILL ALIVE", "pls help pls",
                "Search for a Better Person", "Lies...", "They all lied to me...", "STOP",
                "NOW", "", "self judging...", "the biggest dream", "i'm shit", "i'm joke", "FUCK I HATE THAT", "in a better place", "die", "to die",
                "don't lose time with me", "here forever", "bad ending", "good ending",
                "Nível Darkness: 0.0 (Neutro)", "Nível Darkness: -50.0 (Darkness)", "Nível Darkness: -100.0 (Darkness)", "Nível Darkness: +50.0 (Brightness)", "Nível Darkness: +100.0 (The Golden Day)",
                "The Golden Day", "The Brightness Day", "Darkness", "Brightness", "pls kill me...", "i don't need to be happy", "vida.exe",
                "22-04-2019", "13-03-2019",
                "#MapMeel", "#IntroCDC", "#TheBases", "#IntroBase64", "#TheBasesReturns"
        );

        new BukkitRunnable() {
            @Override
            public void run() {

                if (getLevel().getPotionEffect() == null || Bukkit.getWorld(getLevel().getWarp().getWorld()).getPlayers().isEmpty()) {
                    getLevel().setTempPotionEffect(null);
                    this.cancel();
                    return;
                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapUtils.sendTitle(player, (MapUtils.RANDOM.nextBoolean() ? "§0" : "§f") + MapUtils.getRandom(messages),
                            (MapUtils.RANDOM.nextBoolean() ? "§0" : "§f") + MapUtils.getRandom(messages), 0, 20, 20);
                }

            }
        }.runTaskTimer(MapMain.getPlugin(), 20, 3);

    }
}
