package br.com.introcdc.mapmeelv4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonParser;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;

import br.com.introcdc.mapmeelv4.bases.ItemBuilder;
import br.com.introcdc.mapmeelv4.enums.Warp;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class MapUtils {

    public static final Calendar calendar = Calendar.getInstance();

    public static final Color[] colors = new Color[] { Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.WHITE, Color.YELLOW };
    public static final JsonParser parser = new JsonParser();

    public static final String PREFIX = "§5§l§oMap§f§l§oMeel §5§l§o>§f§l§o> §f";

    public static final Random random = new Random();

    public static void broadcastMessage(final String message) {
        Bukkit.broadcastMessage(message);
    }

    public static String convertToDate(final long number) {
        calendar.setTimeInMillis(number);
        return placeZero(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + placeZero(calendar.get(Calendar.MONTH) + 1) + "/" + placeZero(calendar.get(Calendar.YEAR)) + " - " + placeZero(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + placeZero(calendar.get(Calendar.MINUTE)) + ":" + placeZero(calendar.get(Calendar.SECOND));
    }

    public static long convertToMilliSeconds(long tempo, final String tipo) {
        tempo = tempo * 1000;
        if (tipo.equalsIgnoreCase("s")) {
            return tempo;
        }
        tempo = tempo * 60;
        if (tipo.equalsIgnoreCase("m")) {
            return tempo;
        }
        tempo = tempo * 60;
        if (tipo.equalsIgnoreCase("h")) {
            return tempo;
        }
        tempo = tempo * 24;
        if (tipo.equalsIgnoreCase("d")) {
            return tempo;
        } else if (tipo.equalsIgnoreCase("semana")) {
            tempo = tempo * 7;
            return tempo;
        }
        tempo = tempo * 31;
        if (tipo.equalsIgnoreCase("mes")) {
            return tempo;
        }
        tempo = tempo * 12;
        if (tipo.equalsIgnoreCase("a")) {
            return tempo;
        } else {
            return Long.parseLong("ERROR");
        }
    }

    public static NPC createNPC(final EntityType type, final String name, final Location location, final String skin) {
        final NPC npc = CitizensAPI.getNPCRegistry().createNPC(type, name);
        npc.spawn(location);
        return npc;
    }

    public static void createZipFile(final HashMap<String, String> files, final File destiny) throws IOException {
        final int BUFFER = 2048;
        BufferedInputStream origin = null;
        final FileOutputStream dest = new FileOutputStream(destiny);
        final ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
        for (final String file : files.keySet()) {
            final byte data[] = new byte[BUFFER];
            final FileInputStream fileInput = new FileInputStream(file);
            origin = new BufferedInputStream(fileInput, BUFFER);
            final ZipEntry entry = new ZipEntry(files.get(file));
            out.putNextEntry(entry);
            int count;
            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
                out.flush();
            }
        }
        origin.close();
        out.flush();
        out.close();
    }

    public static void downloadFile(final String URL, final File destiny) throws Exception {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            final URL url = new URL(URL);
            in = new BufferedInputStream(url.openStream());
            fout = new FileOutputStream(destiny);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
            fout.flush();
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }

    public static void extractZipFile(final File fsrc, final File fdest) throws IOException {
        final int BUFFER = (int) fsrc.length();
        final ZipFile zip = new ZipFile(fsrc);
        final Enumeration<?> zipFileEntries = zip.entries();
        while (zipFileEntries.hasMoreElements()) {
            final ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            final String currentEntry = entry.getName();
            final File destFile = new File(fdest, currentEntry);
            final File destinationParent = destFile.getParentFile();
            destinationParent.mkdirs();
            if (!entry.isDirectory()) {
                final BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
                int currentByte;
                final byte data[] = new byte[BUFFER];
                final FileOutputStream fos = new FileOutputStream(destFile);
                final BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }
        }
        zip.close();
    }

    public static Entity freezeEntity(final Entity entity) {
        final net.minecraft.server.v1_12_R1.Entity nmsEn = ((CraftEntity) entity).getHandle();
        final NBTTagCompound compound = new NBTTagCompound();
        nmsEn.c(compound);
        compound.setByte("NoAI", (byte) 1);
        nmsEn.f(compound);
        return entity;
    }

    public static int getPing(final Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }

    public static Plugin getPlugin() {
        return MapMain.getPlugin();
    }

    public static MapProfile getProfile(final String name) throws Exception {
        if (MapProfile.getProfiles().containsKey(name)) {
            return MapProfile.getProfiles().get(name).loadSync();
        }
        return new MapProfile(name).loadSync();
    }

    public static double getTPS() {
        return getTPS(0);
    }

    public static double getTPS(final int type) {
        return MinecraftServer.getServer().recentTps[type];
    }

    public static synchronized boolean isOriginalNick(final String name) throws IOException {
        final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        return connection.getResponseCode() == 200;
    }

    public static ItemBuilder itemBuilder(final ItemStack item) {
        return new ItemBuilder(item);
    }

    public static void launchFireworks(final Location location) {
        try {
            final Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            final FireworkMeta fMeta = firework.getFireworkMeta();
            final FireworkEffect effect = FireworkEffect.builder().withFade(colors[random.nextInt(colors.length)], colors[random.nextInt(colors.length)]).withColor(colors[random.nextInt(colors.length)], colors[random.nextInt(colors.length)]).flicker(true).trail(true).build();
            fMeta.addEffect(effect);
            firework.setFireworkMeta(fMeta);
            firework.setCustomName("Kindome");
        } catch (final Exception ignored) {
        }
    }

    public static void loadSchematic(final Location location, final File file, final boolean noAir) throws MaxChangedBlocksException, DataException, IOException {
        final EditSession es = new EditSession(new BukkitWorld(location.getWorld()), 999999999);
        CuboidClipboard.loadSchematic(file).paste(es, new com.sk89q.worldedit.Vector(location.getX(), location.getY(), location.getZ()), noAir);
    }

    public static void loadWorlds() {
        for (final Warp warp : Warp.values()) {
            if (!warp.getWorld().equalsIgnoreCase("world")) {
                if (Bukkit.getWorld(warp.getWorld()) == null) {
                    Bukkit.createWorld(new WorldCreator(warp.getWorld()));
                }
            }
        }
    }

    public static HashSet<Block> nearBlocks(final Location loc, final int radius) {
        final HashSet<Block> blocks = new HashSet<>();
        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                for (int y = -radius; y <= radius; ++y) {
                    blocks.add(loc.clone().add(x, y, z).getBlock());
                }
            }
        }
        return blocks;
    }

    public static String placeZero(final long number) {
        return number >= 10 ? Long.toString(number) : String.format("0%s", Long.toString(number));
    }

    public static void playSound(final Player player, final Sound sound) {
        player.playSound(player.getLocation(), sound, 50000, 1);
    }

    public static void playSound(final Player player, final String sound) {
        player.playSound(player.getLocation(), sound, 50000, 1);
    }

    public static void sendActionBar(final Player player, final String message) {
        final PacketPlayOutChat chat = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message + "\"}"));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat);
    }

    public static void sendLobbyAleatorio(final Player player) {
        new BukkitRunnable() {

            @Override
            public void run() {
                sendPluginMessage(player, "SendLobby");
            }
        }.runTaskAsynchronously(getPlugin());
    }

    public static void sendPlayer(final Player player, final String server) {
        sendPluginMessage(player, "Connect", server);
    }

    public static void sendPluginMessage(final Player player, final String... messages) {
        final ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        final DataOutputStream output = new DataOutputStream(byteArray);
        try {
            for (final String message : messages) {
                output.writeUTF(message);
            }
        } catch (final IOException ex) {
            player.sendMessage("§cErro ao executar o evento de plugin message...");
            ex.printStackTrace();
        }
        player.sendPluginMessage(getPlugin(), "BungeeCord", byteArray.toByteArray());
    }

    public static void sendTablist(final Player player, final String Header, final String Footer) {
        // try {
        // final IChatBaseComponent header =
        // IChatBaseComponent.ChatSerializer.a("{'text': '" + Header + "'}");
        // final IChatBaseComponent footer =
        // IChatBaseComponent.ChatSerializer.a("{'text': '" + Footer + "'}");
        // final PacketPlayOutPlayerListHeaderFooter packetPlayOut = new
        // PacketPlayOutPlayerListHeaderFooter(header);
        // final Field field = packetPlayOut.getClass().getDeclaredField("b");
        // field.setAccessible(true);
        // field.set(packetPlayOut, footer);
        // ((CraftPlayer)
        // player).getHandle().playerConnection.sendPacket(packetPlayOut);
        // } catch (final Exception e) {
        // e.printStackTrace();
        // }
    }

    public static void sendTitle(final Player player, final String title, final String subtitle, final int fadeIn, final int duration, final int fadeOut) {
        final PacketPlayOutTitle timingsPacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn, duration, fadeOut);
        final PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"));
        final PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"));
        final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(timingsPacket);
        playerConnection.sendPacket(titlePacket);
        playerConnection.sendPacket(subtitlePacket);
    }

    public static String sToTime(long seconds) {
        final long years = seconds / 32140800;
        final long months = seconds % 32140800 / 2678400;
        final long days = seconds % 2678400 / 86400;
        final long hours = seconds % 86400 / 3600;
        final long minutes = seconds % 3600 / 60;
        seconds = seconds % 60;
        return (years > 0 ? placeZero(years) + "A " : "") + (months > 0 ? placeZero(months) + "M " : "") + (days > 0 ? placeZero(days) + "d " : "") + (hours > 0 ? placeZero(hours) + "h " : "") + (minutes > 0 ? placeZero(minutes) + "m " : "") + placeZero(seconds) + "s";
    }

    public static String sToTimeComplete(long seconds) {
        final long years = seconds / 32140800;
        final long months = seconds % 32140800 / 2678400;
        final long days = seconds % 2678400 / 86400;
        final long hours = seconds % 86400 / 3600;
        final long minutes = seconds % 3600 / 60;
        seconds = seconds % 60;
        return (years > 0 ? years + (years == 1 ? " ano, " : " anos, ") : "") + (months > 0 ? months + (months == 1 ? " mês, " : " meses, ") : "") + (days > 0 ? days + (days == 1 ? " dia, " : " dias, ") : "") + (hours > 0 ? hours + (hours == 1 ? " hora, " : " horas, ") : "") + (minutes > 0 ? minutes + (minutes == 1 ? " minuto e " : " minutos e ") : "") + seconds + (seconds == 1 ? " segundo" : " segundos");
    }

}
