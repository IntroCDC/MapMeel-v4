package br.com.introcdc.mapmeelv4.profile;

import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapProfile {

    public static List<String> allAwards = Arrays.asList("level", "onlineTime");

    private static Map<String, MapProfile> profiles = new HashMap<>();

    public static Map<String, MapProfile> getProfiles() {
        return profiles;
    }

    private HashMap<String, Long> awards;
    private Cargo cargo;
    private JsonObject config;
    private File configFile;
    private boolean loaded;
    private String name;

    public MapProfile(String name) {
        this.name = name;
        this.loaded = false;
        this.configFile = new File(MapUtils.getPlugin().getDataFolder().getAbsolutePath() + "/profiles/" + this.getName() + ".json");
        if (this.configFile.exists()) {
            try {
                this.config = MapUtils.parser.parse(new FileReader(configFile)).getAsJsonObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.loaded = true;
            this.config = new JsonObject();
            this.config = new JsonObject();
            for (String award : allAwards) {
                getConfig().addProperty(award, 0);
            }
            getConfig().addProperty("cargo", Cargo.CONVIDADO.toString());
        }
        this.cargo = Cargo.CONVIDADO;
        this.awards = new HashMap<>();
        for (String award : allAwards) {
            this.awards.put(award, 0L);
        }
    }

    public void createFile() throws IOException {
        this.config = new JsonObject();
        for (String award : allAwards) {
            getConfig().addProperty(award, 0);
        }
        getConfig().addProperty("cargo", Cargo.CONVIDADO.toString());
        saveConfig();
    }

    public void saveConfig() throws FileNotFoundException {
        getConfigFile().getParentFile().mkdirs();
        PrintWriter writer = new PrintWriter(getConfigFile());
        writer.println(getConfig() != null ? getConfig().toString() : "{}");
        writer.flush();
        writer.close();
    }

    public boolean existsProfile() {
        return getConfigFile().exists();
    }

    public HashMap<String, Long> getAwards() {
        return this.awards;
    }

    public long getAward(String award) {
        if (getAwards().containsKey(award)) {
            return getAwards().get(award);
        }
        return 0;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public JsonObject getConfig() {
        return this.config;
    }

    public File getConfigFile() {
        return this.configFile;
    }

    public String getName() {
        return this.name;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getName());
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public MapProfile loadSync() throws IOException, InvalidConfigurationException {
        if (isLoaded()) {
            return this;
        }
        if (!existsProfile()) {
            return this;
        }
        this.config = MapUtils.parser.parse(new FileReader(configFile)).getAsJsonObject();
        getAwards().clear();
        for (String award : allAwards) {
            getAwards().put(award, getConfig().get(award).getAsLong());
        }
        this.cargo = Cargo.valueOf(getConfig().get("cargo").getAsString());
        this.loaded = true;
        if (!getProfiles().containsKey(getName())) {
            getProfiles().put(getName(), this);
        }
        return this;
    }

    public void reset() {
        for (String award : allAwards) {
            try {
                setAward(award, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAward(String award, long amount) throws IOException {
        getAwards().remove(award);
        getAwards().put(award, amount);
        getConfig().remove(award);
        getConfig().addProperty(award, amount);
        saveConfig();
    }

    public void setCargo(Cargo cargo) throws IOException {
        this.cargo = cargo;
        getConfig().remove("cargo");
        getConfig().addProperty("cargo", cargo.toString());
        saveConfig();
    }

    public void unload() {
        getProfiles().remove(getName());
    }

}
