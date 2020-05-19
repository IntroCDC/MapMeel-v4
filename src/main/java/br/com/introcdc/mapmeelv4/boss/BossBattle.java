package br.com.introcdc.mapmeelv4.boss;

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import br.com.introcdc.mapmeelv4.listeners.music.MusicUpdaterEvents;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.timer.UpdateType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.nms.v1_15_R1.entity.EntityHumanNPC;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BossBattle implements Listener {

    private static BossBattle instance;
    private static MapSound[] FNAF = new MapSound[]{MapSound.FNAF_1, MapSound.FNAF_2, MapSound.FNAF_3, MapSound.FNAF_4, MapSound.FNAF_5, MapSound.FNAF_6};

    public static BossBattle getInstance() {
        return instance;
    }

    public BossBattle() {
        instance = this;
        baseActived = false;
        Bukkit.getPluginManager().registerEvents(this, MapMain.getPlugin());
    }

    public boolean started = false;
    public Location center;
    public Wither wither;
    public NPC baseNpc;
    public boolean baseActived;
    public static int damage = 5;
    public BossBar bossBar;

    public void start() {
        if (started) {
            return;
        }
        started = true;

        if (wither != null && !wither.isDead()) {
            wither.remove();
        }
        if (baseNpc != null && baseNpc.isSpawned()) {
            baseNpc.destroy();
        }

        center = new Location(Bukkit.getWorld("FINAL-BOSS"), 5000, 100, 5000);


        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                broadcast("O que?!?! Você chegou até aqui?!?!", true), 4 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                broadcast("Você nunca irá libertar ela!", true), 8 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                broadcast("Mas de qualquer forma, preciso me livrar de você...", true), 12 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                broadcast("Você irá conhecer o meu pet...", true), 16 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () ->
                broadcast("§lWITHER!", true), 20 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            center.getWorld().strikeLightning(center);
            wither = center.getWorld().spawn(center.clone().add(0, 10, 0), Wither.class);
            wither.setCustomName("§lPet do Boss");
            wither.setCustomNameVisible(true);
            started = false;
        }, 24 * 20);
    }

    public void spawnBase() {
        if (baseNpc != null && baseNpc.isSpawned()) {
            baseNpc.destroy();
        }

        baseNpc = MapUtils.createNPC(EntityType.PLAYER, "Base64", center.clone().add(0, -50, 0), "Intro_GamerHD");
        baseNpc.setProtected(true);

        List<EnderCrystal> crystals = new ArrayList<>();

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            broadcast("Como é que é? Você matou meu Wither?", true);
        }, 4 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            broadcast("Vou ter que resolver esse problema por mim mesmo!", true);
        }, 8 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            MapUtils.playSoundToAll(Sound.BLOCK_BEACON_ACTIVATE);
            EnderCrystal enderCrystal = center.getWorld().spawn(center.clone().add(3, 0, 0), EnderCrystal.class);
            enderCrystal.setBeamTarget(center.clone().add(0, 25, 0));
            crystals.add(enderCrystal);
        }, 12 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            MapUtils.playSoundToAll(Sound.BLOCK_BEACON_ACTIVATE);
            EnderCrystal enderCrystal = center.getWorld().spawn(center.clone().add(-3, 0, 0), EnderCrystal.class);
            enderCrystal.setBeamTarget(center.clone().add(0, 25, 0));
            crystals.add(enderCrystal);
            MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            broadcast("Te prepara...", true);
        }, 16 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            MapUtils.playSoundToAll(Sound.BLOCK_BEACON_ACTIVATE);
            EnderCrystal enderCrystal = center.getWorld().spawn(center.clone().add(0, 0, 3), EnderCrystal.class);
            enderCrystal.setBeamTarget(center.clone().add(0, 25, 0));
            crystals.add(enderCrystal);
        }, 20 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            MapUtils.playSoundToAll(Sound.BLOCK_BEACON_ACTIVATE);
            EnderCrystal enderCrystal = center.getWorld().spawn(center.clone().add(0, 0, -3), EnderCrystal.class);
            enderCrystal.setBeamTarget(center.clone().add(0, 25, 0));
            crystals.add(enderCrystal);
        }, 24 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> MapUtils.playSoundToAll(MapUtils.getRandom(FNAF)), 26 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            center.getWorld().createExplosion(center.clone().add(0, 25, 0), 5);
            MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            for (EnderCrystal enderCrystal : crystals) {
                enderCrystal.remove();
            }
            baseNpc.teleport(center.clone().add(0, 25, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }, 30 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            broadcast("Olá!");
        }, 34 * 20);

        Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
            baseActived = true;
            MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            EntityHumanNPC.PlayerNPC playerNPC = (EntityHumanNPC.PlayerNPC) baseNpc.getEntity();
            playerNPC.setMaxHealth(1000);
            playerNPC.setHealth(1000);
            playerNPC.setSprinting(true);
            baseNpc.setProtected(false);
            bossBar = Bukkit.createBossBar("Base64", BarColor.RED, BarStyle.SOLID);
            bossBar.setVisible(true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                bossBar.addPlayer(player);
            }
            bossBar.setProgress(1);
        }, 40 * 20);

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && CitizensAPI.getNPCRegistry().isNPC(event.getEntity())) {
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) && !event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) && !event.getCause().equals(EntityDamageEvent.DamageCause.POISON) && !event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player && CitizensAPI.getNPCRegistry().isNPC(event.getEntity()) && event.getEntity().getName().equalsIgnoreCase("Base64")) {
            Player player = (Player) event.getEntity();
            CitizensAPI.getNPCRegistry().getNPC(player).destroy();
            center.getWorld().setDifficulty(Difficulty.PEACEFUL);
            for (int x = -5; x <= 5; x++) {
                for (int z = -5; z <= 5; z++) {
                    center.clone().add(x, 0, z).getBlock().setType(Material.AIR);
                }
            }
            MusicUpdaterEvents.musicPause = true;
            MapUtils.playSoundToAll(MapSound.STOP);
            bossBar.removeAll();
            if (baseActived) {
                baseActived = false;
                MapUtils.playSoundToAll(Sound.ENTITY_ENDER_DRAGON_DEATH);
                NPC npc = MapUtils.createNPC(EntityType.PLAYER, "Base64", player.getLocation(), "Intro_GamerHD");
                npc.setProtected(false);
                Player playerNpc = (Player) npc.getEntity();
                ArmorStand armorStand = npc.getStoredLocation().getWorld().spawn(MapUtils.getLocation("FINAL-BOSS", npc.getStoredLocation().getX() + 3, npc.getStoredLocation().getY() + 3, npc.getStoredLocation().getZ() + 3, npc.getStoredLocation()), ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                    otherPlayer.setGameMode(GameMode.SPECTATOR);
                    otherPlayer.setSpectatorTarget(armorStand);
                }
                broadcast("Você me paga...");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        npc.faceLocation(armorStand.getLocation());
                        npc.getStoredLocation().getWorld().strikeLightningEffect(npc.getStoredLocation());
                        playerNpc.setHealth(playerNpc.getHealth() - 2);
                        if (playerNpc.getHealth() <= 1) {
                            cancel();
                            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                                armorStand.remove();
                                center.getWorld().setDifficulty(Difficulty.NORMAL);
                                for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                                    otherPlayer.setGameMode(GameMode.ADVENTURE);
                                }
                            }, 40);
                        }
                    }
                }.runTaskTimer(MapMain.getPlugin(), 1, 5);
            } else {
                Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                    Level level = Level.getLevel(event.getEntity().getWorld().getName());
                    LevelObjective levelObjective = level.getObjectives().get("Mate o Chefão");
                    levelObjective.spawnStar(true, null);
                }, 10 * 20);
            }
        }
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType() == UpdateType.SECONDS && event.getTimes() == 2 && baseActived && baseNpc.isSpawned()) {
            if (MapUtils.RANDOM.nextBoolean()) {
                MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            }
            BaseAttack attack = MapUtils.getRandom(BaseAttack.values());
            attack.getRunnableAttack().accept(baseNpc, MapUtils.getRandom(Bukkit.getOnlinePlayers()));
            if (baseNpc.getStoredLocation().distance(center) > 50) {
                baseNpc.teleport(center, PlayerTeleportEvent.TeleportCause.PLUGIN);
                center.getWorld().strikeLightning(center);
                MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            }
            bossBar.setProgress(((LivingEntity) baseNpc.getEntity()).getHealth() / 1000);
        }
    }

    public void broadcast(String message) {
        broadcast(message, false);
    }

    public void broadcast(String message, boolean hide) {
        Bukkit.broadcastMessage("§0§l§o" + (hide ? "§k" : "") + "Base64: §f§o" + message);
    }

    public enum BaseAttack {
        HIT((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Vem cá, não foge " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player, true);
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                if (npc.getStoredLocation().distance(player.getLocation()) <= 2) {
                    player.damage(damage, npc.getEntity());
                    MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
                }
            }, 20);

        }),
        JUMP_EXPLODE((npc, player) -> {

            npc.faceLocation(player.getLocation());
            Vector vector = npc.getStoredLocation().getDirection();
            vector.multiply(1.5f);
            npc.getEntity().setVelocity(vector);
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                if (MapUtils.RANDOM.nextBoolean()) {
                    BossBattle.getInstance().broadcast("MORRA " + player.getName().toUpperCase() + "!");
                }
                npc.getStoredLocation().getWorld().createExplosion(npc.getStoredLocation(), 3);
                MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            }, 5);

        }),
        DRAGON_BALL((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Eu tenho a alma do dragão, " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());
            EntityHumanNPC.PlayerNPC playerNPC = (EntityHumanNPC.PlayerNPC) npc.getEntity();
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                playerNPC.launchProjectile(DragonFireball.class);
                MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            }, 5);

        }),
        FIREBALL((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Você está com frio, deixa eu te esquentar " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());
            EntityHumanNPC.PlayerNPC playerNPC = (EntityHumanNPC.PlayerNPC) npc.getEntity();
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                playerNPC.launchProjectile(Fireball.class);
                MapUtils.playSoundToAll(MapUtils.getRandom(FNAF));
            }, 5);

        }),
        TP(((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Vem pra cá, " + player.getName() + "!");
            }
            npc.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

        })),
        BLINDNESS((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Você está vendo coisas demais " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10 * 20, 0));

        }),
        CROCODILES((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Você será devorado " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());

            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                int distance = 0;
                Location location = npc.getStoredLocation().clone();
                location.setPitch(0);
                location.add(npc.getStoredLocation().getDirection().getX() + 0.1, 0, npc.getStoredLocation().getDirection().getZ() + 0.1);
                while (!location.getBlock().getType().isSolid() && distance < 15) {
                    distance++;
                    Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                        location.add(npc.getStoredLocation().getDirection().getX(), 0, npc.getStoredLocation().getDirection().getZ());
                        location.getWorld().spawnEntity(location, EntityType.EVOKER_FANGS);
                    }, distance);
                }
            }, 2);

        }),
        CUSPIR((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Você perto de mim não é nada " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());
            EntityHumanNPC.PlayerNPC playerNPC = (EntityHumanNPC.PlayerNPC) npc.getEntity();
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                MapUtils.playSoundToAll(Sound.ENTITY_LLAMA_SPIT);
                playerNPC.launchProjectile(LlamaSpit.class);
            }, 2);

        }),
        TOO_HIGH((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("VAI EMBORA " + player.getName().toUpperCase() + "!");
            }
            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> player.setVelocity(new Vector(0, 10, 0)), 2);

        }),
        ARROWS((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Fica no meio da minha mira " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());

            new BukkitRunnable() {

                int times = 0;

                @Override
                public void run() {
                    npc.faceLocation(player.getLocation());
                    npc.getNavigator().setTarget(player.getLocation());
                    times++;
                    if (times >= 20) {
                        cancel();
                    }
                    ((LivingEntity) npc.getEntity()).launchProjectile(Arrow.class);
                }
            }.runTaskTimer(MapMain.getPlugin(), 2, 1);

        }),
        ENDER_PEARL((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Vá para longe " + player.getName() + "!");
            }
            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> player.launchProjectile(EnderPearl.class), 2);

        }),
        TNT(((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Venha cá, me dê um abraço " + player.getName() + "!");
            }
            TNTPrimed tntPrimed = npc.getStoredLocation().getWorld().spawn(npc.getStoredLocation().clone().add(0, 1, 0), TNTPrimed.class);
            tntPrimed.setFuseTicks(3 * 20);
            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());

        })),
        FIRE(((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("É só eu, ou as coisas estão quentes por aqui?");
            }
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    npc.getStoredLocation().clone().add(x, 0, z).getBlock().setType(Material.FIRE);
                }
            }

            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());
        })),
        STRIKE(((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Eu sou o verdadeiro filho de odin!");
            }
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    npc.getStoredLocation().getWorld().strikeLightning(npc.getStoredLocation().clone().add(x, 0, z));
                }
            }

            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());
        })),
        ENTITY(((npc, player) -> {

            if (MapUtils.RANDOM.nextBoolean()) {
                BossBattle.getInstance().broadcast("Ataquem fantasmas!");
            }

            EntityType[] entityType = new EntityType[]{EntityType.ZOMBIE, EntityType.VEX, EntityType.SKELETON, EntityType.PILLAGER, EntityType.EVOKER, EntityType.VINDICATOR, EntityType.CREEPER, EntityType.WITHER_SKELETON, EntityType.GHAST};
            EntityType selected = MapUtils.getRandom(entityType);

            npc.getStoredLocation().getWorld().spawnEntity(npc.getStoredLocation().clone().add(1, 2, 0), selected);
            npc.getStoredLocation().getWorld().spawnEntity(npc.getStoredLocation().clone().add(-1, 2, 0), selected);
            npc.getStoredLocation().getWorld().spawnEntity(npc.getStoredLocation().clone().add(0, 2, 1), selected);
            npc.getStoredLocation().getWorld().spawnEntity(npc.getStoredLocation().clone().add(0, 2, -1), selected);

            npc.faceLocation(player.getLocation());
            npc.getNavigator().setTarget(player.getLocation());
        })),
        ;

        private BiConsumer<NPC, Player> runnableAttack;

        BaseAttack(BiConsumer<NPC, Player> runnableAttack) {
            this.runnableAttack = runnableAttack;
        }

        public BiConsumer<NPC, Player> getRunnableAttack() {
            return runnableAttack;
        }

    }

}
