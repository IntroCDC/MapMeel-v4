package br.com.introcdc.mapmeelv4;

import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import br.com.introcdc.mapmeelv4.enums.UpdateType;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;

public class ScoreManager extends MapUtils implements Listener {

    @SuppressWarnings("UnusedAssignment")
    private static void scoreboard(MapProfile profile) {
        if (profile.getPlayer() == null) {
            return;
        }
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("dummy", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§5§l§oMap§f§l§oMeel");

        int column = 15;

        String header = "\n§5§l§oMap§f§l§oMeel\n\n§fLevel Atual: §a" + (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world") ? "Lobby" : profile.getPlayer().getWorld().getName()) + "\n\n§fEstrelas: §e" + profile.getAward("stars") + "\n";
        StringBuilder footer = new StringBuilder();

        if (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            footer = new StringBuilder("\n§fVocê está no Lobby!\n");
        } else {

            if (Level.getLevel(profile.getPlayer().getWorld().getName()) != null) {
                footer = new StringBuilder("\n§fObjetivos:\n\n");
                for (LevelObjective levelObjective : Level.getLevel(profile.getPlayer().getWorld().getName()).getObjectives()) {
                    footer.append("§f").append(levelObjective.getStringObjective()).append(": ").append(levelObjective.isFinished() ? "§aFinalizado" : "§cNão Finalizado").append("\n");
                }
            } else {
                footer = new StringBuilder("\n§cLevel desconhecido!\n");
            }
        }

        sendTablist(profile.getPlayer(), header, footer.toString());

        objective.getScore("§a").setScore(column--);
        objective.getScore("§fLevel: §a" + (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world") ? "Lobby" : profile.getPlayer().getWorld().getName())).setScore(column--);
        objective.getScore("§b").setScore(column--);

        profile.getPlayer().setScoreboard(scoreboard);
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
