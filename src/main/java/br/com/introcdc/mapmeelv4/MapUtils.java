package br.com.introcdc.mapmeelv4;

import br.com.introcdc.mapmeelv4.bases.ItemBuilder;
import br.com.introcdc.mapmeelv4.bases.MapCoin;
import br.com.introcdc.mapmeelv4.enums.CoinType;
import br.com.introcdc.mapmeelv4.enums.MapSound;
import br.com.introcdc.mapmeelv4.enums.Warp;
import br.com.introcdc.mapmeelv4.utils.ReflectionManager;
import com.google.gson.JsonParser;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_12_R1.*;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.*;
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

import java.io.*;
import java.net.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static java.time.temporal.ChronoField.*;

public class MapUtils {

    public static Calendar calendar = Calendar.getInstance();

    public static Color[] colors = new Color[]{Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.WHITE, Color.YELLOW};
    public static JsonParser parser = new JsonParser();

    public static String PREFIX = "§5§l§oMap§f§l§oMeel §5§l§o>§f§l§o> §f";
    public static Location FRONTCASTLE = new Location(Bukkit.getWorld("world"), -16, 50, 46, 145, 0);

    public static Random random = new Random();

    private static DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder().appendValue(DAY_OF_MONTH, 2).appendLiteral('/').appendValue(MONTH_OF_YEAR, 2).appendLiteral('/').appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral(" - ").appendValue(HOUR_OF_DAY, 2).appendLiteral(':').appendValue(MINUTE_OF_HOUR, 2).appendLiteral(':').appendValue(SECOND_OF_MINUTE, 2).toFormatter();

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(message);
    }

    public static NPC createNPC(EntityType type, String name, Location location) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(type, name);
        npc.spawn(location);
        return npc;
    }

    public static MapCoin coin(Warp warp, double x, double y, double z, CoinType coinType) {
        return new MapCoin(new Location(warp.getLocation().getWorld(), x, y, z), coinType);
    }

    public static String convertToBarProgress(long max, long use, int size) {
        double currentHealth = Math.max(use, 0);
        double healthPercentage = currentHealth / max * 100.0D;
        String spacer = "|";
        int coloredDisplay = (int) Math.ceil(size * (healthPercentage / 100.0D));
        StringBuilder healthbar = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (coloredDisplay > 0) {
                healthbar.append("§a").append(spacer);
                coloredDisplay--;
            } else {
                healthbar.append("§8").append(spacer);
            }
        }
        healthbar = new StringBuilder("§f[" + healthbar + "§f]");
        return healthbar.toString();
    }

    public static String convertToDate(long number) {
        return TIME_FORMATTER.format(Instant.ofEpochMilli(number).atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public static long convertToMilliSeconds(long tempo, String tipo) {
        tempo = tempo * 1000;
        if (tipo.equalsIgnoreCase("s") || tipo.toLowerCase().contains("segundo") || tipo.toLowerCase().contains("segundos")) {
            return tempo;
        }
        tempo = tempo * 60;
        if (tipo.equalsIgnoreCase("m") || tipo.toLowerCase().contains("minuto") || tipo.toLowerCase().contains("minutos")) {
            return tempo;
        }
        tempo = tempo * 60;
        if (tipo.equalsIgnoreCase("h") || tipo.toLowerCase().contains("hora") || tipo.toLowerCase().contains("horas")) {
            return tempo;
        }
        tempo = tempo * 24;
        if (tipo.equalsIgnoreCase("d") || tipo.toLowerCase().contains("dia") || tipo.toLowerCase().contains("dias")) {
            return tempo;
        } else if (tipo.toLowerCase().contains("semana") || tipo.toLowerCase().contains("semanas")) {
            tempo = tempo * 7;
            return tempo;
        }
        tempo = tempo * 31;
        if (tipo.toLowerCase().contains("mes") || tipo.toLowerCase().contains("meses")) {
            return tempo;
        }
        tempo = tempo * 12;
        if (tipo.equalsIgnoreCase("a") || tipo.toLowerCase().contains("ano") || tipo.toLowerCase().contains("anos")) {
            return tempo;
        } else {
            return -1;
        }
    }

    public static int convertToPercent(int current, int max) {
        return max <= 0 ? 0 : current * 100 / max;
    }

    public static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdirs();
        }
        for (String file : source.list()) {
            copy(new File(source, file), new File(target, file));
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

    public static void createZipFile(HashMap<String, String> files, File destiny) throws IOException {
        destiny.getParentFile().mkdirs();
        if (!destiny.exists()) {
            destiny.createNewFile();
        }
        int BUFFER = 2048;
        BufferedInputStream origin = null;
        FileOutputStream dest = new FileOutputStream(destiny);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
        for (String file : files.keySet()) {
            byte data[] = new byte[BUFFER];
            FileInputStream fileInput = new FileInputStream(file);
            origin = new BufferedInputStream(fileInput, BUFFER);
            ZipEntry entry = new ZipEntry(files.get(file));
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

    public static void deleteFileOrFolder(Path path) {
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                private FileVisitResult handleException(IOException e) {
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                    if (e != null) {
                        return this.handleException(e);
                    }
                    try {
                        Files.delete(dir);
                    } catch (Exception ignored) {
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        Files.delete(file);
                    } catch (Exception ignored) {
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return this.handleException(e);
                }
            });
        } catch (Exception ignored) {
        }
    }

    public static void downloadFile(String URL, File destiny) throws Exception {
        destiny.getParentFile().mkdirs();
        if (!destiny.exists()) {
            destiny.createNewFile();
        }
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            URLConnection connection = new URL(URL).openConnection();
            connection.addRequestProperty("User-Agent", "Kindome/IntroCDC");
            in = new BufferedInputStream(connection.getInputStream());
            fout = new FileOutputStream(destiny);

            byte data[] = new byte[1024];
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

    public static void extractZipFile(File fsrc, File fdest) throws IOException {
        int BUFFER = (int) fsrc.length();
        ZipFile zip = new ZipFile(fsrc);
        Enumeration<?> zipFileEntries = zip.entries();
        while (zipFileEntries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File destFile = new File(fdest, currentEntry);
            File destinationParent = destFile.getParentFile();
            destinationParent.mkdirs();
            if (!entry.isDirectory()) {
                BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
                int currentByte;
                byte data[] = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
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

    public static Entity freezeEntity(Entity entity) {
        net.minecraft.server.v1_12_R1.Entity nmsEn = ((CraftEntity) entity).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsEn.c(compound);
        compound.setByte("NoAI", (byte) 1);
        nmsEn.f(compound);
        return entity;
    }

    public static int getPing(Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }

    public static Plugin getPlugin() {
        return MapMain.getPlugin();
    }

    public static MapProfile getProfile(String name) throws Exception {
        if (MapProfile.getProfiles().containsKey(name)) {
            return MapProfile.getProfiles().get(name).loadSync();
        }
        return new MapProfile(name).loadSync();
    }

    public static double getTPS() {
        return getTPS(0);
    }

    public static double getTPS(int type) {
        return MinecraftServer.getServer().recentTps[type];
    }

    public static synchronized boolean isOriginalNick(String name) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        return connection.getResponseCode() == 200;
    }

    public static ItemBuilder itemBuilder(ItemStack item) {
        return new ItemBuilder(item);
    }

    public static void launchFireworks(Location location) {
        try {
            Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            FireworkMeta fMeta = firework.getFireworkMeta();
            FireworkEffect effect = FireworkEffect.builder().withFade(colors[random.nextInt(colors.length)], colors[random.nextInt(colors.length)]).withColor(colors[random.nextInt(colors.length)], colors[random.nextInt(colors.length)]).flicker(true).trail(true).build();
            fMeta.addEffect(effect);
            firework.setFireworkMeta(fMeta);
            firework.setCustomName("Kindome");
        } catch (Exception ignored) {
        }
    }

    public static void loadSchematic(Location location, File file, boolean noAir) throws MaxChangedBlocksException, DataException, IOException {
        EditSession es = new EditSession(new BukkitWorld(location.getWorld()), 999999999);
        CuboidClipboard.loadSchematic(file).paste(es, new com.sk89q.worldedit.Vector(location.getX(), location.getY(), location.getZ()), noAir);
    }

    public static String getServerIP() throws SocketException {
        String IP = "127.0.0.1 (NF)";
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                if (i.getHostAddress().contains("192.168.0")) {
                    return i.getHostAddress();
                } else if (!i.getHostAddress().contains(":") && !i.getHostAddress().contains("127.0.0.1")) {
                    IP = i.getHostAddress();
                }
            }
        }
        return IP;
    }

    public static String hashSha512(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static String getServerKey() throws UnknownHostException {
        byte[] bytesHost = Base64.getEncoder().encode(InetAddress.getLocalHost().toString().getBytes());
        return new String(bytesHost).replace("=", "");
    }

    public static boolean isNumber(String integer) {
        if (integer.length() >= 18) {
            return false;
        }
        try {
            Integer.parseInt(integer);
            return true;
        } catch (Exception ignored) {
        }
        try {
            Double.parseDouble(integer);
            return true;
        } catch (Exception ignored) {
        }
        try {
            Long.parseLong(integer);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static void loadWorlds() {
        for (Warp warp : Warp.values()) {
            if (!warp.getWorld().equalsIgnoreCase("world")) {
                if (Bukkit.getWorld(warp.getWorld()) == null) {
                    Bukkit.createWorld(new WorldCreator(warp.getWorld()));
                }
            }
        }
    }

    public static HashSet<Block> nearBlocks(Location loc, int radius) {
        HashSet<Block> blocks = new HashSet<>();
        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                for (int y = -radius; y <= radius; ++y) {
                    blocks.add(loc.clone().add(x, y, z).getBlock());
                }
            }
        }
        return blocks;
    }

    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 50000, 1);
    }

    public static void playSound(Player player, MapSound mapSound) {
        playSound(player, mapSound, 1);
    }

    public static void playSound(Player player, MapSound mapSound, float tom) {
        playSound(player, mapSound, -1, tom);
    }

    public static List<UUID> cooldownPlay = new ArrayList<>();

    public static void playSound(Player player, MapSound mapSound, int wait, float tom) {
        if (cooldownPlay.contains(player.getUniqueId()) && wait == -1) {
            return;
        }
        if(mapSound.equals(MapSound.STOP)) {
            new BukkitRunnable() {
                int times = 0;

                @Override
                public void run() {
                    times++;
                    if (times <= 15) {
                        player.playSound(player.getLocation(), mapSound.getFile(), 50000, tom);
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(getPlugin(), 0, 1);
        }
        if (wait > 0) {
            cooldownPlay.add(player.getUniqueId());
            new BukkitRunnable() {
                @Override
                public void run() {
                    cooldownPlay.remove(player.getUniqueId());
                }
            }.runTaskLater(getPlugin(), wait * 20);
        }
        player.playSound(player.getLocation(), mapSound.getFile(), 50000, 1);
    }

    public static Location getLocation(String world, double x, double y, double z, float yaw, float pitch) {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public static Location getLocation(String world, double x, double y, double z) {
        return getLocation(world, x, y, z, 0, 0);
    }

    public static Location getLocation(String world, double x, double y, double z, Location lookingAt) {
        Location loc = getLocation(world, x, y, z, 0, 0);
        loc.setDirection(lookingAt.toVector().subtract(loc.toVector()));
        return loc;
    }

    public static void sendActionBar(Player player, String message) {
        PacketPlayOutChat chat = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message + "\"}"));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat);
    }

    public static void sendPlayer(Player player, String server) {
        sendPluginMessage(player, "Connect", server);
    }

    public static void sendPluginMessage(Player player, String... messages) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(byteArray);
        try {
            for (String message : messages) {
                output.writeUTF(message);
            }
        } catch (IOException ex) {
            player.sendMessage("§cErro ao executar o evento de plugin message...");
            ex.printStackTrace();
        }
        player.sendPluginMessage(getPlugin(), "BungeeCord", byteArray.toByteArray());
    }

    public static void sendTablist(Player player, String header, String footer) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.begin(packet);
        reflectionManager.setObject("a", new ChatComponentText(header));
        reflectionManager.setObject("b", new ChatComponentText(footer));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int duration, int fadeOut) {
        PacketPlayOutTitle timingsPacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn, duration, fadeOut);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"));
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"));
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(timingsPacket);
        playerConnection.sendPacket(titlePacket);
        playerConnection.sendPacket(subtitlePacket);
    }

    public static String placeZero(long number) {
        return number >= 10 ? Long.toString(number) : String.format("0%s", Long.toString(number));
    }

    public static String sToTime(long totalTime) {
        long years = totalTime / 32140800;
        long months = totalTime % 32140800 / 2678400;
        long days = totalTime % 2678400 / 86400;
        long hours = totalTime % 86400 / 3600;
        long minutes = totalTime % 3600 / 60;
        long seconds = totalTime % 60;
        if (totalTime > 0) {
            return (years > 0 ? placeZero(years) + "A " : "") + (months > 0 ? placeZero(months) + "M " : "") + (days > 0 ? placeZero(days) + "d " : "") + (hours > 0 ? placeZero(hours) + "h " : "") + (minutes > 0 ? placeZero(minutes) + "m " : "") + (seconds > 0 ? placeZero(seconds) + "s " : "");
        }
        return "0s";
    }

    public static String sToTimeComplete(long totalTime) {
        long years = totalTime / 32140800;
        long months = totalTime % 32140800 / 2678400;
        long days = totalTime % 2678400 / 86400;
        long hours = totalTime % 86400 / 3600;
        long minutes = totalTime % 3600 / 60;
        long seconds = totalTime % 60;
        if (totalTime > 0) {
            String result = "";
            if (years > 0) {
                result += years + " ano" + (years == 1 ? "" : "s");
                if (months > 0 || days > 0 || hours > 0 || minutes > 0) {
                    result += ", ";
                } else if (seconds > 0) {
                    result += " e ";
                }
            }
            if (months > 0) {
                result += months + " " + (months == 1 ? "mês" : "meses");
                if (days > 0 || hours > 0 || minutes > 0) {
                    result += ", ";
                } else if (seconds > 0) {
                    result += " e ";
                }
            }
            if (days > 0) {
                result += days + " dia" + (days == 1 ? "" : "s");
                if (hours > 0 || minutes > 0) {
                    result += ", ";
                } else if (seconds > 0) {
                    result += " e ";
                }
            }
            if (hours > 0) {
                result += hours + " hora" + (hours == 1 ? "" : "s");
                if (minutes > 0) {
                    result += ", ";
                } else if (seconds > 0) {
                    result += " e ";
                }
            }
            if (minutes > 0) {
                result += minutes + " minuto" + (minutes == 1 ? "" : "s");
                if (seconds > 0) {
                    result += " e ";
                }
            }
            if (seconds > 0) {
                result += seconds + " segundo" + (seconds == 1 ? "" : "s");
            }
            return result;
        }
        return "0 segundos";
    }

}
