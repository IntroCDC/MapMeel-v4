package br.com.introcdc.mapmeelv4.histories;
/*
 * Written by IntroCDC, Bruno Coêlho at 25/04/2019 - 21:36
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.armorpath.ArmorStandPath;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class FinalHistory implements Listener {

    public static void startFinalHistory() {

        World spawnWorld = Bukkit.getWorld("world");

        World world = Bukkit.getWorld("FINAL-BOSS");
        Location MIDDLE = new Location(world, 5000, 100, 5000);

        ArmorStandPath historyThree = new ArmorStandPath(Arrays.asList(
                new Location(spawnWorld, -40, 49, -21, -92, 11),// Lobby
                new Location(spawnWorld, -40, 48, -9, -146, 7),
                new Location(spawnWorld, -50, 63, 2, -140, 48),
                new Location(spawnWorld, -62, 108, 19, -139, 56),
                new Location(spawnWorld, -74, 165, 33, -131, 54),
                new Location(spawnWorld, -48, 181, 101, -159, 45),
                new Location(spawnWorld, 31, 149, 102, -209, 39),
                new Location(spawnWorld, 57, 124, 79, -233, 33),
                new Location(spawnWorld, 67, 120, 13, -267, 25),
                new Location(spawnWorld, 64, 122, -103, -296, 20),
                new Location(spawnWorld, 28, 145, -194, -332, 42),
                new Location(spawnWorld, -11, 144, -198, -356, 42),
                new Location(spawnWorld, -13, 82, -160, -357, 45),
                new Location(spawnWorld, -13, 51, -119, -356, 3),
                new Location(spawnWorld, -24, 52, -74, -335, 1),
                new Location(spawnWorld, -38, 52, -22, -359, 0),
                new Location(spawnWorld, -16, 51, 49, -359, 0),
                new Location(spawnWorld, -13, 52, 101, -303, 3),
                new Location(spawnWorld, -68, 73, 138, -222, 11),
                new Location(spawnWorld, -153, 62, 101, -181, 20),
                new Location(spawnWorld, -172, 62, 39, -155, 24),
                new Location(spawnWorld, -189, 79, -35, -129, 36),
                new Location(spawnWorld, -172, 80, -148, -108, 30),
                new Location(spawnWorld, -89, 84, -227, -189, 34),
                new Location(spawnWorld, -30, 78, -257, -141, 24),
                new Location(spawnWorld, 62, 74, -218, -130, 27),
                new Location(spawnWorld, 128, 60, -187, -161, 19),
                new Location(spawnWorld, 147, 136, -159, -125, -4),
                new Location(spawnWorld, 186, 208, -146, -142, 11),
                new Location(spawnWorld, 232, 208, -134, -184, 1),
                new Location(spawnWorld, 279, 208, -152, -221, 6),
                new Location(spawnWorld, 303, 100, -127, -22, 38),
                new Location(spawnWorld, 318, 59, -91, -11, 7),
                new Location(spawnWorld, 252, 85, -35, -43, 27),
                new Location(spawnWorld, 220, 85, 38, -34, 34),
                new Location(spawnWorld, 177, 88, 124, -64, 42),
                new Location(spawnWorld, 181, 118, 230, -125, 45),
                new Location(spawnWorld, 177, 121, 261, 97, 7),
                new Location(spawnWorld, 106, 125, 272, 147, 19),
                new Location(spawnWorld, 3, 90, 220, 176, 8),
                new Location(spawnWorld, -24, 97, 161, 188, 14),
                new Location(spawnWorld, -15, 55, 94, 181, 5),
                new Location(spawnWorld, -19, 51, 44, 125, 2),
                new Location(spawnWorld, -41, 51, 61, 17, 8),
                new Location(spawnWorld, -44, 51, 80, 0, 8),
                new Location(spawnWorld, -36, 52, 57, 112, 13),
                new Location(spawnWorld, -50, 52, 33, 90, 2),
                new Location(spawnWorld, -51, 51, -76, 89, 5),
                new Location(spawnWorld, -44, 52, -123, 181, 1),
                new Location(spawnWorld, -20, 93, -93, 294, 27),
                new Location(spawnWorld, 15, 158, -100, 359, 43),
                new Location(spawnWorld, -42, 141, 0, 245, 24),
                new Location(spawnWorld, -16, 117, 59, 223, 25),
                new Location(spawnWorld, 37, 55, 34, 91, 25),
                new Location(spawnWorld, 5, 53, 33, 90, 5),
                new Location(spawnWorld, -27, 51, 19, -64, -23),
                new Location(spawnWorld, -13, 51, 57, -182, 1),
                new Location(spawnWorld, -14, 57, 108, -180, 4),
                new Location(spawnWorld, -15, 105, 154, -179, 41),
                new Location(spawnWorld, 0, 161, 183, -188, 49),
                new Location(spawnWorld, 52, 259, 249, -205, 55),
                new Location(spawnWorld, 50, 350, 270, -282, -90)
        )) {

            @Override
            public void onEnd(Player player) {
                player.teleport(Warp.LOBBY.getLocation());
            }
        };

        List<Location> pathTwo = Arrays.asList(
                new Location(spawnWorld, -44, 49, -33, -53, -13),
                new Location(spawnWorld, -41, 111, -31, -58, -36),
                new Location(spawnWorld, -34, 158, -29, -63, 16),
                new Location(spawnWorld, -34, 158, -9, -137, 20),
                new Location(spawnWorld, -41, 158, -21, -92, 23),
                new Location(spawnWorld, -36, 151, -35, -40, 6),
                new Location(spawnWorld, -43, 151, -21, -96, 10),
                new Location(spawnWorld, -44, 130, -21, -91, 4),
                new Location(spawnWorld, -44, 90, -21, -91, 4),
                new Location(spawnWorld, -40, 49, -21, -92, 11),
                new Location(spawnWorld, -39, 49, -30, -20, 11),
                new Location(spawnWorld, -39, 49, -26, -34, 13),
                new Location(spawnWorld, -40, 49, -21, -92, 11),
                new Location(spawnWorld, -37, 49, -17, -176, 20),
                new Location(spawnWorld, -40, 49, -21, -92, 11),
                new Location(spawnWorld, -39, 49, -26, -34, 13),
                new Location(spawnWorld, -40, 49, -21, -92, 11),
                new Location(spawnWorld, -37, 49, -17, -176, 20),
                new Location(spawnWorld, -40, 49, -21, -92, 11),
                new Location(spawnWorld, -39, 49, -26, -34, 13),
                new Location(spawnWorld, -40, 49, -21, -92, 11),
                new Location(spawnWorld, -37, 49, -17, -176, 20),
                new Location(spawnWorld, -40, 49, -21, -92, 11)
        );

        ArmorStandPath historyTwo = new ArmorStandPath(pathTwo) {

            @Override
            public void onEnd(Player player) {

                player.setGameMode(GameMode.SPECTATOR);

                MapUtils.playSound(player, MapSound.MUSIC_END_THREE);
                historyThree.start();
                historyThree.watch(player);

            }
        };

        List<Location> pathOne = Arrays.asList(
                new Location(world, 5000, 100, 4996, 3, 37),
                new Location(world, 5006, 102, 5000, 89, 38),
                new Location(world, 5000, 106, 5007, 171, 50),
                new Location(world, 4993, 117, 5001, 265, 63),
                new Location(world, 5002, 144, 4992, 340, 76),
                new Location(world, 5006, 224, 4991, 358, 84),
                new Location(world, 5006, 254, 4991, 358, 90),
                new Location(world, 5006, 350, 4991, 358, -90)
        );

        ArmorStandPath historyOne = new ArmorStandPath(pathOne) {

            @Override
            public void onEnd(Player player) {

                player.setGameMode(GameMode.SPECTATOR);
                player.teleport(new Location(spawnWorld, -48, 49, -35, -55, -6));

                Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                    MapUtils.playSound(player, MapSound.MUSIC_END_TWO);
                    historyTwo.start();
                    historyTwo.watch(player);
                }, 100);

            }
        };

        for (Player player : Bukkit.getOnlinePlayers()) {
            MapUtils.playSound(player, MapSound.STOP);
            MapUtils.playSound(player, MapSound.MUSIC_END_ONE);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                historyOne.start();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    historyOne.watch(player);
                }
            }
        }.runTaskLater(MapMain.getPlugin(), 12);

    }

}
