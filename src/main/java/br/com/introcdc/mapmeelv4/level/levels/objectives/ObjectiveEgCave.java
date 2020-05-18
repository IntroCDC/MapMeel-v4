package br.com.introcdc.mapmeelv4.level.levels.objectives;
/*
 * Written by IntroCDC, Bruno Coêlho at 02/08/2019 - 07:18
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectiveEgCave extends LevelObjective {

    private List<NPC> npcs = new ArrayList<>();

    public ObjectiveEgCave() {
        super("Perdida na Caverna", new Location(Bukkit.getWorld("EG-CAVE"), -342, 99, -727, 88, 90), true, true, false);
    }

    @Override
    public void ifGetStar() {

        getLevel().setTempPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 100));
        getLevel().setTempBackgroungMapSound(MapSound.MUSIC_CUSTOM_DARK_FOREST);
        spawnStar(true, new Location(Bukkit.getWorld("EG-CAVE"), -434.5, 92, -847.5));

        List<String> nicks = Arrays.asList("Medo", "Ansiedade", "Incapacidade", "Vontade de viver", "IntroBase64", "Introo", "Mel");
        for (String nick : nicks) {
            npcs.add(MapUtils.createNPC(EntityType.PLAYER, nick, getLocation()));
        }

        List<String> messages = Arrays.asList(
                "Medos", "Socorro", "Alguém está aí?", "Is anyone there?", "é, parece que to sozinho",
                "eu sou uma piada", "não perca tempo comigo", "não posso alcançar meu sonho...", "por que está aqui?", "por que ta jogando isso?",
                "Intro is dead", "ele está morto"
        );

        new BukkitRunnable() {
            @Override
            public void run() {

                if (getLevel().getPotionEffect() == null || Bukkit.getWorld(getLevel().getWarp().getWorld()).getPlayers().isEmpty()) {
                    getLevel().setTempPotionEffect(null);

                    if (!npcs.isEmpty()) {
                        for (NPC npc : npcs) {
                            npc.destroy();
                        }
                    }
                    Bukkit.getScheduler().runTask(MapMain.getPlugin(), () -> npcs.clear());

                    this.cancel();
                    return;
                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapUtils.sendTitle(player, (MapUtils.RANDOM.nextBoolean() ? "§0" : "§f") + MapUtils.getRandom(messages),
                            (MapUtils.RANDOM.nextBoolean() ? "§0" : "§f") + MapUtils.getRandom(messages), 0, 20, 20);

                    for (NPC npc : npcs) {
                        if (npc.getNavigator().getTargetAsLocation() == null || npc.getNavigator().getEntityTarget() == null) {
                            npc.getNavigator().setTarget(player, true);
                        }
                    }
                }

            }
        }.runTaskTimer(MapMain.getPlugin(), 20, 3);

    }

    @Override
    public void onLoadLevel() {
        if (!npcs.isEmpty()) {
            for (NPC npc : npcs) {
                npc.destroy();
            }
        }
        Bukkit.getScheduler().runTask(MapMain.getPlugin(), () -> npcs.clear());
    }

}
