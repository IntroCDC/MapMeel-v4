package br.com.introcdc.mapmeelv4.scoreboard;

import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.profile.MapProfile;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ScoreManager implements Listener {

    @SuppressWarnings("UnusedAssignment")
    private static void scoreboard(MapProfile profile) {
        if (profile.getPlayer() == null) {
            return;
        }
        Player player = profile.getPlayer();
        World world = player.getWorld();

        String header = "\n§5§l§oMap§f§l§oMeel\n" +
                "\n" +
                "§a§l" + (world.getName().equalsIgnoreCase("world") ? "Mundo Normal\n" + "§b§l§oLobby" :

                (Level.getLevel(world.getName()) != null ?
                        Level.getLevel(world.getName()).getWarp().getName() :
                        world.getName()) + "\n" + "§b§l§o" + (Level.getLevel(world.getName()) != null ? Level.getLevel(world.getName()).getName() : "Não Localizado"))

                + "\n" +
                "\n" +
                "§e§l" + Level.stars + "§f estrelas\n ";
        StringBuilder footer = new StringBuilder();

        if (world.getName().equalsIgnoreCase("world")) {
            footer = new StringBuilder("\n§f§l§oVocê está no Lobby!\n ");
        } else {
            if (Level.getLevel(world.getName()) != null) {
                footer = new StringBuilder("\n§l§oObjetivos finalizados:\n\n");
                for (String key : Level.getLevel(world.getName()).getObjectives().keySet()) {
                    LevelObjective levelObjective = Level.getLevel(world.getName()).getObjectives().get(key);
                    if (levelObjective.isFinished() || Level.getLevel(world.getName()).getObjectives().size() == 1) {
                        footer.append("§f§l").append(levelObjective.getStringObjective()).append(": §b").append(levelObjective.getWhoFinished()).append("\n");
                    }
                }
                footer.append(" ");
            } else {
                footer = new StringBuilder("\n§cLevel não localizado!\n ");
            }
        }

        MapUtils.sendTablist(player, header, footer.toString());
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
