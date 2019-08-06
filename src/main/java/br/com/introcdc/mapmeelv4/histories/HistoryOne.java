package br.com.introcdc.mapmeelv4.histories;

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.armorpath.ArmorStandPath;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.profile.MapProfile;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class HistoryOne implements Listener {

    public static void start(Player player) {
        MapProfile profile = MapUtils.getProfile(player.getName());
        if (profile.getAward("level") <= 1) {
            profile.setAward("level", 2);

            Location DEFAULT = new Location(Bukkit.getWorld("world"), 0, 80, 0);
            for (int x = -5; x <= 5; x++) {
                for (int z = -5; z <= 5; z++) {
                    DEFAULT.clone().add(x * 16, 0, z * 16).getChunk().load(true);
                }
            }

            new BukkitRunnable() {
                @Override
                public void run() {

                    MapUtils.playSound(player, MapSound.STARTING);
                    World world = player.getWorld();
                    ArmorStandPath path = new ArmorStandPath(Arrays.asList(
                            new Location(world, -108, 47, 264, -135, -2),
                            new Location(world, -83, 51, 251, -134, -8),
                            new Location(world, -58, 55, 243, -152, -9),
                            new Location(world, -17, 71, 231, -177, 1),
                            new Location(world, 25, 74, 205, -192, 1),
                            new Location(world, 37, 76, 165, -199, 4),
                            new Location(world, 2, 62, 131, -160, 5),
                            new Location(world, -50, 60, 122, -166, -8),
                            new Location(world, -115, 122, 192, -140, 20),
                            new Location(world, -106, 47, 262, -134, -5)
                    )) {

                        @Override
                        public void onEnd(Player player) {
                        }
                    };

                    path.start();
                    path.watch(player);

                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                            MapUtils.sendTitle(player, "§f§l§oIntroCDC", "", 3, 100, 20), 50);

                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                            MapUtils.sendTitle(player, "§f§l§oIntroCDC", "§f§oApresenta...", 0, 100, 20), 92);

                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                            MapUtils.sendTitle(player, "§f§l§oIntroCDC", "§f§oMais uma versão...", 0, 100, 20), 136);

                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                            MapUtils.sendTitle(player, "§f§l§oIntroCDC", "§f§oDe um projeto tão grande...", 0, 100, 20), 180);

                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                            MapUtils.sendTitle(player, "§5§l§oMapMeel §f§ov4", "§f§oO Fim da História", 0, 263, 130), 224);

                }
            }.runTaskLater(MapMain.getPlugin(), 100);

        }
    }

}
