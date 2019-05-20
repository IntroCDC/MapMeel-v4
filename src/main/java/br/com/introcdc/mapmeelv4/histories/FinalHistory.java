package br.com.introcdc.mapmeelv4.histories;
/*
 * Written by IntroCDC, Bruno Coêlho at 25/04/2019 - 21:36
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.armorpath.ArmorStandPath;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import br.com.introcdc.mapmeelv4.warp.Warp;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class FinalHistory implements Listener {

    public static boolean onceTime = false;

    public static void startFinalHistory() {

        onceTime = false;

        World spawnWorld = Bukkit.getWorld("world");

        World world = Bukkit.getWorld("FINAL-BOSS");
        Location MIDDLE = new Location(world, 5000, 100, 5000);

        ArmorStandPath historyThree = new ArmorStandPath(Arrays.asList(
                new Location(spawnWorld, -40, 49, -21, -92, 11),// Lobby
                new Location(spawnWorld, -47, 58, -2, -137, 36),
                new Location(spawnWorld, -58, 79, 13, -139, 44),
                new Location(spawnWorld, -74, 121, 32, -134, 55),
                new Location(spawnWorld, -76, 121, 85, -172, 21),
                new Location(spawnWorld, -1, 102, 160, -190, 22),
                new Location(spawnWorld, 75, 95, 177, -97, 17),
                new Location(spawnWorld, 199, 111, 169, -1, 34),
                new Location(spawnWorld, 252, 88, 43, -106, 29),
                new Location(spawnWorld, 290, 89, -93, -40, 32),
                new Location(spawnWorld, 273, 192, -140, -213, 6),
                new Location(spawnWorld, 224, 207, -128, -178, 11),
                new Location(spawnWorld, 171, 198, -153, -126, 5),
                new Location(spawnWorld, 128, 65, -184, -199, 22),
                new Location(spawnWorld, -63, 84, -237, -228, 34),
                new Location(spawnWorld, -173, 93, -112, -291, 35),
                new Location(spawnWorld, -149, 79, 30, -333, 29),
                new Location(spawnWorld, -38, 75, 150, -170, 12),
                new Location(spawnWorld, -11, 49, 60, -208, 2),
                new Location(spawnWorld, -27, 50, 33, -303, 6),
                new Location(spawnWorld, -36, 50, -76, -267, 0),
                new Location(spawnWorld, -42, 50, -107, -188, 7),
                new Location(spawnWorld, -28, 129, -87, -74, 12),
                new Location(spawnWorld, -47, 151, -20, -89, 22),
                new Location(spawnWorld, -18, 123, 67, -141, 19),
                new Location(spawnWorld, 27, 55, 54, -266, -2),
                new Location(spawnWorld, 37, 51, 33, -270, 5),
                new Location(spawnWorld, -4, 68, 33, -269, -4),
                new Location(spawnWorld, -49, 89, -21, -116, 17),
                new Location(spawnWorld, -14, 86, -65, -108, 38),
                new Location(spawnWorld, 38, 51, -76, -269, 4),
                new Location(spawnWorld, 0, 49, -76, -269, 1),
                new Location(spawnWorld, -39, 54, -22, -90, -2),
                new Location(spawnWorld, -1, 52, -22, -135, 13),
                new Location(spawnWorld, -2, 52, -21, -42, 7),
                new Location(spawnWorld, 5, 96, -3, -120, -33),
                new Location(spawnWorld, 17, 123, -21, -268, 1),
                new Location(spawnWorld, -3, 123, -21, -270, 4),
                new Location(spawnWorld, -9, 138, -8, -155, -13),
                new Location(spawnWorld, -7, 157, -24, -56, 67),
                new Location(spawnWorld, -31, 169, -34, -64, 33),
                new Location(spawnWorld, -47, 98, -18, -92, -6),
                new Location(spawnWorld, -32, 54, 15, -143, -15),
                new Location(spawnWorld, -16, 48, 44, -184, 2),
                new Location(spawnWorld, -13, 59, 101, -185, 11),
                new Location(spawnWorld, -52, 118, 114, -157, 33),
                new Location(spawnWorld, -83, 144, 61, -119, 39),
                new Location(spawnWorld, -81, 142, -23, -89, 43),
                new Location(spawnWorld, -50, 135, -110, -36, 44),
                new Location(spawnWorld, 0, 134, -152, 2, 24),
                new Location(spawnWorld, 39, 118, -117, 44, 31),
                new Location(spawnWorld, 62, 114, -67, 89, 26),
                new Location(spawnWorld, 57, 112, 35, 104, 22),
                new Location(spawnWorld, 10, 108, 71, 155, 28),
                new Location(spawnWorld, -14, 90, 91, 179, 0),
                new Location(spawnWorld, -14, 59, 110, 180, 0),
                new Location(spawnWorld, -10, 89, 147, 177, 25),
                new Location(spawnWorld, 5, 119, 169, 164, 29),// Start to Up
                new Location(spawnWorld, 36, 159, 198, 158, 34),
                new Location(spawnWorld, 91, 213, 224, 146, 36),
                new Location(spawnWorld, 90, 256, 242, 90, -90),
                new Location(spawnWorld, 90, 400, 242, 90, -90)
        )) {

            @Override
            public void onEnd(Player player) {
                player.teleport(Warp.LOBBY.getLocation());
            }
        };

        List<Location> pathTwo = Arrays.asList(
                new Location(spawnWorld, -45, 51, -31, -45, 3),
                new Location(spawnWorld, -54, 103, -34, -57, 12),
                new Location(spawnWorld, -57, 167, -20, -91, 24),
                new Location(spawnWorld, -35, 147, -28, -31, 1),
                new Location(spawnWorld, -40, 147, -21, -89, 0),
                new Location(spawnWorld, -38, 148, -15, -131, 5),
                new Location(spawnWorld, -41, 148, -22, -87, 4),
                new Location(spawnWorld, -38, 130, -36, -15, -1),
                new Location(spawnWorld, -43, 78, -29, -48, 10),
                new Location(spawnWorld, -43, 50, -12, -138, 5),
                new Location(spawnWorld, -35, 52, -13, -205, 27),
                new Location(spawnWorld, -41, 49, -17, -124, 3),
                new Location(spawnWorld, -39, 49, -15, -145, 3),
                new Location(spawnWorld, -41, 49, -18, -111, 2),
                new Location(spawnWorld, -37, 49, -13, -170, 1),
                new Location(spawnWorld, -41, 49, -18, -105, 2),
                new Location(spawnWorld, -40, 49, -16, -133, 1),
                new Location(spawnWorld, -39, 49, -15, -157, 1),
                new Location(spawnWorld, -41, 49, -18, -120, 0),
                new Location(spawnWorld, -39, 49, -16, -145, 2),
                new Location(spawnWorld, -41, 49, -19, -111, 1),
                new Location(spawnWorld, -39, 49, -18, -136, 1),
                new Location(spawnWorld, -39, 49, -19, -123, 2)
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

                    if (onceTime) {
                        return;
                    }
                    onceTime = true;

                    startFinalCastleDialog();

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

    public static void startFinalCastleDialog() {
        NPC bigZombie = MapUtils.createNPC(EntityType.GIANT, "Estrela Gigante", new Location(Bukkit.getWorld("world"), -37, 49, -26, 0, 0));
        bigZombie.setFlyable(true);

        Giant giant = ((Giant) bigZombie.getEntity());

        giant.getEquipment().setItemInMainHand(MapUtils.itemBuilder(new ItemStack(Material.PLAYER_HEAD)).setOwner("iMeel").toItem());
        giant.setGravity(false);
        giant.setAI(false);
        giant.setCollidable(false);
        giant.setGliding(true);
        giant.setInvulnerable(true);
        giant.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 100));

        new BukkitRunnable() {

            int times = 0;

            @Override
            public void run() {
                times++;

                if (times <= 30) {
                    giant.teleport(giant.getLocation().clone().add(0, 3.5, 0));
                    return;
                }

                giant.getWorld().strikeLightningEffect(giant.getLocation().clone().add(-2.5, 7, 4));
                bigZombie.destroy();
                this.cancel();

            }
        }.runTaskTimer(MapMain.getPlugin(), 0, 5);

        new BukkitRunnable() {

            int times = 0;

            @Override
            public void run() {
                times++;
                if (bigZombie.isSpawned()) {
                    giant.getWorld().playEffect(giant.getLocation().clone().add(-2.5, 7, 4), Effect.SMOKE, 5);

                    giant.getWorld().playEffect(giant.getLocation().clone().add(-2.5, 6.5, 4.5), Effect.SMOKE, 5);
                    giant.getWorld().playEffect(giant.getLocation().clone().add(-2.5, 6.5, 3.5), Effect.SMOKE, 5);
                    giant.getWorld().playEffect(giant.getLocation().clone().add(-3, 6.5, 4), Effect.SMOKE, 5);
                    giant.getWorld().playEffect(giant.getLocation().clone().add(-2, 6.5, 4), Effect.SMOKE, 5);

                    return;
                }
                this.cancel();
            }
        }.runTaskTimer(MapUtils.getPlugin(), 5, 2);

        NPC sombraOne = MapUtils.createNPC(EntityType.PLAYER, "SombraXD", new Location(Bukkit.getWorld("world"), -36.5, 49, -17.5, -229, 0));
        NPC sombraTwo = MapUtils.createNPC(EntityType.PLAYER, "SombraXD", new Location(Bukkit.getWorld("world"), -36.5, 49, -25.5, -313, 0));

        NPC playerViewer = MapUtils.createNPC(EntityType.PLAYER, ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getName(), new Location(Bukkit.getWorld("world"), -40.5, 49, -21.5, 269, 0));

        new BukkitRunnable() {

            NPC meel = null;
            int times = 0;

            @Override
            public void run() {

                times++;

                if (meel == null) {
                    meel = MapUtils.createNPC(EntityType.PLAYER, "iMeel", new Location(Bukkit.getWorld("world"), -24.5, 149, -21.5, 90, 0));

                    Location loc = new Location(Bukkit.getWorld("world"), -30, 53, -22);

                    for (int z = -4; z <= 4; z++) {
                        for (int y = 0; y <= 10; y++) {
                            if (loc.clone().add(0, y, z).getBlock().getType().equals(Material.IRON_BARS)) {
                                loc.clone().add(0, y, z).getBlock().setType(Material.AIR);
                            }
                        }
                    }
                }

                Player meelEntity = (Player) meel.getEntity();

                if (times >= 600) {
                    meel.destroy();
                    sombraOne.destroy();
                    sombraTwo.destroy();
                    playerViewer.destroy();
                    this.cancel();
                }

                if (times < 83) {
                    meel.teleport(meel.getStoredLocation().clone().add(-0.15, 0, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
                    meelEntity.setGravity(false);
                    meelEntity.setCollidable(false);
                    meelEntity.setInvulnerable(true);
                }
                if (times >= 83 && times <= 148) {
                    meel.teleport(meel.getStoredLocation().clone().add(0, -1.5, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
                    meelEntity.setGravity(false);
                    meelEntity.setCollidable(false);
                    meelEntity.setInvulnerable(true);
                }
                if (times == 149) {
                    meel.teleport(meel.getStoredLocation().clone().add(0, -1, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);

                    meelEntity.setGravity(true);

                }

                if (times == 215) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: " + ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getName() + "!");
                    }
                }
                if (times == 230) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: O poder das estrelas me salvaram do castelo!");
                    }
                }
                if (times == 250) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Eu pensava que ia ficar presa no meu próprio castelo para sempre...");
                    }
                }
                if (times == 270) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Mas o rumo dessa história mudou!");
                    }
                }
                if (times == 290) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Graças a você, obrigada " + ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getName() + "...");
                    }
                }
                if (times == 310) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Eu adimiro a sua caminhada até aqui");
                    }
                }
                if (times == 310) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Mesmo estando presa nas paredes do castelo");
                    }
                }
                if (times == 330) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Eu vim te observando...");
                    }
                }
                if (times == 350) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: É lindo saber que mesmo depois de tantos erros");
                    }
                }
                if (times == 370) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Você continuou de pé até chegar ao seu destino...");
                    }
                }
                if (times == 390) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Muito obrigada mesmo com todos os seus erros, você continuou de pé até me salvar...");
                    }
                }
                if (times == 410) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Precisamos fazer alguma coisa para agradecer...");
                    }
                }
                if (times == 420) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Já sei!");
                    }
                    meel.faceLocation(sombraOne.getStoredLocation());
                }
                if (times == 430) {
                    meel.faceLocation(sombraTwo.getStoredLocation());
                }
                if (times == 440) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Vamos fazer um bolo delicioso");
                    }
                    meel.faceLocation(playerViewer.getStoredLocation());
                }
                if (times == 450) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: Para " + ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getName() + "...");
                    }
                }
                if (times == 460) {
                    meel.getNavigator().setTarget(new Location(Bukkit.getWorld("world"), -22, 53, -21, -90, 1));
                    sombraOne.getNavigator().setTarget(new Location(Bukkit.getWorld("world"), -21, 53, -16, -90, 0));
                    sombraTwo.getNavigator().setTarget(new Location(Bukkit.getWorld("world"), -22, 53, -26, -90, 0));

                    playerViewer.faceLocation(((Player) Bukkit.getOnlinePlayers().toArray()[0]).getLocation());
                }
                if (times == 480) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§d§liMeel§f: " + ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getName() + "!");
                    }
                    playerViewer.getNavigator().setTarget(new Location(Bukkit.getWorld("world"), -23, 53, -19, -92, 0));
                }


            }
        }.runTaskTimer(MapMain.getPlugin(), 160, 3);

    }


}
