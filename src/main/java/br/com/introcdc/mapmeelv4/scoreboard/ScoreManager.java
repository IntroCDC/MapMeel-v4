package br.com.introcdc.mapmeelv4.scoreboard;

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.enums.UpdateType;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ScoreManager extends MapUtils implements Listener {

    @SuppressWarnings("UnusedAssignment")
    private static void scoreboard(MapProfile profile) {
        if (profile.getPlayer() == null) {
            return;
        }
        String header = "\n§5§l§oMap§f§l§oMeel\n\n§a" + (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world") ? "Mundo Normal\n§b§bLobby" : profile.getPlayer().getWorld().getName() + "\n§b§l" + (Level.getLevel(profile.getPlayer().getWorld().getName()) != null ? Level.getLevel(profile.getPlayer().getWorld().getName()).getName() : "Não Localizado"))
                + "\n\n§e§l" + profile.getAward("stars") + "§f estrelas\n";
        StringBuilder footer = new StringBuilder();

        if (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            footer = new StringBuilder("\n§f§l§oVocê está no Lobby!\n");
        } else {
            if (Level.getLevel(profile.getPlayer().getWorld().getName()) != null) {
                footer = new StringBuilder("\n§l§oObjetivos:\n\n");
                for (LevelObjective levelObjective : Level.getLevel(profile.getPlayer().getWorld().getName()).getObjectives()) {
                    footer.append("§f§l").append(levelObjective.getStringObjective()).append(": ").append(levelObjective.isFinished() ? "§a§oFinalizado" : "§cNão Finalizado").append("\n");
                }
            } else {
                footer = new StringBuilder("\n§cLevel não localizado!\n");
            }
        }

        sendTablist(profile.getPlayer(), header, footer.toString());
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) throws Exception {
        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                scoreboard(getProfile(player.getName()));
            }
        }
    }

}
