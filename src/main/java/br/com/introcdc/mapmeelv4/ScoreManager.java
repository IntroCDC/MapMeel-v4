package br.com.introcdc.mapmeelv4;

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
    private static void scoreboard(final MapProfile profile) {
        if (profile.getPlayer() == null) {
            return;
        }
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("dummy", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§5§l§oMap§f§l§oMeel");

        int column = 15;

        objective.getScore("§a").setScore(column--);
        objective.getScore("§fLevel: §a" + (profile.getPlayer().getWorld().getName().equalsIgnoreCase("world") ? "Lobby" : profile.getPlayer().getWorld().getName())).setScore(column--);
        objective.getScore("§b").setScore(column--);

        profile.getPlayer().setScoreboard(scoreboard);
    }

    @EventHandler
    public void onUpdate(final UpdateEvent event) throws Exception {
        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 1) {
            for (final Player player : Bukkit.getOnlinePlayers()) {
                scoreboard(getProfile(player.getName()));
            }
        }
    }

}
