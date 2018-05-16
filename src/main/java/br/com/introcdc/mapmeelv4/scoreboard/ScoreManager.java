package br.com.introcdc.mapmeelv4.scoreboard;

import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.profile.MapProfile;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ScoreManager implements Listener {

    @SuppressWarnings("UnusedAssignment")
    private static void scoreboard(MapProfile profile) {
        if (profile.getPlayer() == null) {
            return;
        }
        String header = "\n§5§l§oMap§f§l§oMeel\n\n§a§l" + (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world") ? "Mundo Normal\n§b§l§oLobby" : profile.getPlayer().getWorld().getName() + "\n§b§l§o" + (Level.getLevel(profile.getPlayer().getWorld().getName()) != null ? Level.getLevel(profile.getPlayer().getWorld().getName()).getName() : "Não Localizado"))
                + "\n\n§e§l" + Level.stars + "§f estrelas\n";
        StringBuilder footer = new StringBuilder();

        if (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            footer = new StringBuilder("\n§f§l§oVocê está no Lobby!\n");
        } else {
            if (Level.getLevel(profile.getPlayer().getWorld().getName()) != null) {
                footer = new StringBuilder("\n§l§oObjetivos:\n\n");
                for (String key : Level.getLevel(profile.getPlayer().getWorld().getName()).getObjectives().keySet()) {
                    LevelObjective levelObjective = Level.getLevel(profile.getPlayer().getWorld().getName()).getObjectives().get(key);
                    footer.append("§f§l").append(levelObjective.getStringObjective()).append(": ").append(levelObjective.isFinished() ? "§a§oFinalizado" : "§cNão Finalizado").append("\n");
                }
            } else {
                footer = new StringBuilder("\n§cLevel não localizado!\n");
            }
        }

        MapUtils.sendTablist(profile.getPlayer(), header, footer.toString());
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) throws Exception {
        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                scoreboard(MapUtils.getProfile(player.getName()));
            }
        }
    }

}
