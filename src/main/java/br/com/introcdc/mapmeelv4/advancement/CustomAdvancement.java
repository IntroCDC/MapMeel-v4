package br.com.introcdc.mapmeelv4.advancement;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class CustomAdvancement implements Advancement {

    private static final String BACKGROUND = "minecraft:textures/block/white_terracotta.png";

    public static String getBackground() {
        return BACKGROUND;
    }

    public static CustomAdvancement FIRST_ENTRACE;
    public static CustomAdvancement CASTLE;
    public static CustomAdvancement FIRST_SOMBRA;
    public static CustomAdvancement STILL_HERE;

    public static CustomAdvancement OBJECTIVES;
    public static CustomAdvancement OBJECTIVES_FOLDER;

    public static CustomAdvancement SECOND_DOOR;
    public static CustomAdvancement SECOND_DOOR_FOLDER;

    public static CustomAdvancement THIRD_DOOR;
    public static CustomAdvancement THIRD_DOOR_FOLDER;

    public static CustomAdvancement FOURTH_DOOR;
    public static CustomAdvancement FOURTH_DOOR_FOLDER;

    public static CustomAdvancement SECRET_LEVELS;
    public static CustomAdvancement SECRET_LEVELS_FOLDER;

    public static CustomAdvancement BOSS_DOOR;
    public static CustomAdvancement GET_120_STARS;
    public static CustomAdvancement FIND_THE_GATE;
    public static CustomAdvancement FINISHED_GAME;

    public static void installAdvancements() {
        FIRST_ENTRACE = new CustomAdvancement("root", "O Início", "Entre no mapa pela primeira vez", Material.APPLE, null, "mapmeel", false, true, FrameType.TASK).install();
        CASTLE = new CustomAdvancement("castle", "Um Belo Castelo", "Entre no Castelo da Meel", Material.STONE_BRICKS, "mapmeel/root", "mapmeel", false, true, FrameType.TASK).install();
        FIRST_SOMBRA = new CustomAdvancement("first_sombra", "Socialize com o Sombra", "Converse com o Sombra na entrada do Castelo", Material.BOOK, "mapmeel/castle", "mapmeel", false, true, FrameType.TASK).install();
        STILL_HERE = new CustomAdvancement("still_here", "Ainda está aqui?!", "Espere o Sombra parar de falar...", Material.BOOK, "mapmeel/first_sombra", "mapmeel", true, true, FrameType.TASK).install();

        OBJECTIVES = new CustomAdvancement("objectives", "O Início de Tudo", "Encontre a entrada do primeiro nível!", Material.BOOK, "mapmeel/first_sombra", "mapmeel", false, true, FrameType.TASK).install();
        OBJECTIVES_FOLDER = new CustomAdvancement("root", "O Primeiro Nível", "Encontre a entrada do primeiro nível!", Material.BOOK, null, "level1", false, false, FrameType.TASK).install();

        SECOND_DOOR = new CustomAdvancement("seconddoor", "O Segundo Nível", "Consiga 10 estrelas e abra a porta do segundo nível!", Material.BOOK, "mapmeel/objectives", "mapmeel", false, true, FrameType.CHALLENGE).install();
        SECOND_DOOR_FOLDER = new CustomAdvancement("root", "O Segundo Nível", "Consiga 10 estrelas e abra a porta do segundo nível!", Material.BOOK, null, "level2", false, false, FrameType.CHALLENGE).install();

        THIRD_DOOR = new CustomAdvancement("thirddoor", "O Terceiro Nível", "Consiga 25 estrelas e abra a porta do terceiro nível!", Material.BOOK, "mapmeel/seconddoor", "mapmeel", false, true, FrameType.CHALLENGE).install();
        THIRD_DOOR_FOLDER = new CustomAdvancement("root", "O Terceiro Nível", "Consiga 25 estrelas e abra a porta do terceiro nível!", Material.BOOK, null, "level3", false, false, FrameType.CHALLENGE).install();

        FOURTH_DOOR = new CustomAdvancement("fourthdoor", "O Último Nível", "Consiga 50 estrelas e abra a porta do último nível!", Material.BOOK, "mapmeel/thirddoor", "mapmeel", false, true, FrameType.CHALLENGE).install();
        FOURTH_DOOR_FOLDER = new CustomAdvancement("root", "O Último Nível", "Consiga 50 estrelas e abra a porta do último nível!", Material.BOOK, null, "level4", false, false, FrameType.CHALLENGE).install();

        SECRET_LEVELS = new CustomAdvancement("secret_levels", "Tem ainda mais?!", "Encontre o primeiro nível escondido!", Material.GLASS, "mapmeel/first_sombra", "mapmeel", true, true, FrameType.CHALLENGE).install();
        SECRET_LEVELS_FOLDER = new CustomAdvancement("root", "Níveis Escondidos", "Encontre o primeiro nível escondido!", Material.GLASS, null, "niveisescondidos", true, false, FrameType.CHALLENGE).install();

        BOSS_DOOR = new CustomAdvancement("boss_door", "Não se preocupe Meel, to indo!", "Consiga 70 estrelas e abra a porta do topo do castelo!", Material.IRON_BARS, "mapmeel/fourthdoor", "mapmeel", false, true, FrameType.CHALLENGE).install();
        GET_120_STARS = new CustomAdvancement("get120stars", "Finalize o jogo!", "Consiga todas as 120 estrelas do jogo!", Material.PLAYER_HEAD, "mapmeel/boss_level", "mapmeel", false, true, FrameType.CHALLENGE).install();
        FIND_THE_GATE = new CustomAdvancement("findthegate", "O Portão das 120 Estrelas!", "Procure e encontre o portão das 120 estrelas!", Material.IRON_BARS, "mapmeel/get120stars", "mapmeel", false, true, FrameType.CHALLENGE).install();
        FINISHED_GAME = new CustomAdvancement("finishedgame", "MapMeel v4 Finalizado!", "Converse com o Intro e finalize o jogo!", Material.DIAMOND, "mapmeel/findthegate", "mapmeel", true, true, FrameType.CHALLENGE).install();
    }

    private final String name = "mapmeelv4";
    private String fileName;
    private String title;
    private String description;
    private Material icon;
    private String parent;
    private String folder;
    private boolean hidden;
    private boolean notify;
    private FrameType frameType;

    public CustomAdvancement(String fileName, String title, String description, Material icon, String parent, String folder, boolean hidden, boolean notify, FrameType frameType) {
        this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.parent = parent;
        this.folder = folder;
        this.hidden = hidden;
        this.notify = notify;
        this.frameType = frameType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Material getIcon() {
        return icon;
    }

    public String getParent() {
        return parent;
    }

    public String getFolder() {
        return folder;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isNotify() {
        return notify;
    }

    public FrameType getFrameType() {
        return frameType;
    }

    @Override
    public Collection<String> getCriteria() {
        return new ArrayList<>();
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey("mmv4", getFolder() + "/" + getFileName());
    }

    public String getJson() {
        JsonObject advancementJson = new JsonObject();

        JsonObject infoJson = new JsonObject();

        JsonObject iconJson = new JsonObject();
        iconJson.addProperty("item", getIcon().toString().toLowerCase());
        infoJson.add("icon", iconJson);

        infoJson.addProperty("title", getTitle());
        infoJson.addProperty("description", getDescription());
        infoJson.addProperty("background", BACKGROUND);

        infoJson.addProperty("hidden", isHidden());
        infoJson.addProperty("frame", getFrameType().toString().toLowerCase());
        infoJson.addProperty("show_toast", isNotify());
        infoJson.addProperty("announce_to_chat", false);

        advancementJson.add("display", infoJson);

        JsonObject criteriaJson = new JsonObject();

        JsonObject announceJson = new JsonObject();
        announceJson.addProperty("trigger", "minecraft:impossible");
        criteriaJson.add(getFileName(), announceJson);

        advancementJson.add("criteria", criteriaJson);

        if (getParent() != null) {
            advancementJson.addProperty("parent", "mmv4:" + getParent());
        }

        return advancementJson.toString();
    }

    public CustomAdvancement install() {
        try {
            new File("world/datapacks/mapmeelv4/data/mmv4/advancements/" + folder).mkdirs();
            File jsonFile = new File("world/datapacks/mapmeelv4/data/mmv4/advancements/" + folder + "/" + fileName + ".json");
            PrintWriter writer = new PrintWriter(jsonFile);
            writer.print(getJson());
            writer.flush();
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return this;
    }

    public Advancement getAdvancement() {
        return Bukkit.getAdvancement(getKey());
    }

    public void awardAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            awardPlayer(player);
        }
    }

    public void awardPlayer(Player player) {
        if (!player.getAdvancementProgress(getAdvancement()).isDone()) {
            player.getAdvancementProgress(getAdvancement()).awardCriteria(getFileName());
        }
    }

    public void revokeAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            revokePlayer(player);
        }
    }

    public void revokePlayer(Player player) {
        if (player.getAdvancementProgress(getAdvancement()).isDone()) {
            player.getAdvancementProgress(getAdvancement()).revokeCriteria(getFileName());
        }
    }

    public void sendJsonToPlayer(Player player) {
        TextComponent textComponent = new TextComponent(getJson());
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, getJson()));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para copiar").create()));
        player.sendMessage(textComponent);
    }

    public enum FrameType {
        TASK, CHALLENGE, GOAL
    }

}
