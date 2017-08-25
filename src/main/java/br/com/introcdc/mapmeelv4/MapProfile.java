package br.com.introcdc.mapmeelv4;

import br.com.introcdc.mapmeelv4.enums.Cargo;
import br.com.introcdc.mapmeelv4.enums.CoinType;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapProfile extends MapUtils {

    public static final List<String> allAwards = Arrays.asList("stars", "level");

    private static Map<String, MapProfile> profiles = new HashMap<>();

    public static Map<String, MapProfile> getProfiles() {
        return profiles;
    }

    private HashMap<String, Long> awards;
    private Cargo cargo;
    private JsonObject config;
    private File configFile;
    private boolean loaded;
    private final String name;
    private long tempCoins;

    public MapProfile(final String name) {
        this.name = name;
        this.loaded = false;
        this.configFile = new File(getPlugin().getDataFolder().getAbsolutePath() + "/profiles/" + this.getName() + ".json");
        if (this.configFile.exists()) {
            try {
                this.config = parser.parse(new FileReader(configFile)).getAsJsonObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.tempCoins = 0;
        this.cargo = Cargo.CONVIDADO;
        this.awards = new HashMap<>();
        for (String award : allAwards) {
            this.awards.put(award, 0L);
        }
    }

    public void addTempCoin(final CoinType type) {
        this.addTempCoins(type.getCoins());
    }

    public void addTempCoins(final int amount) {
        this.tempCoins += amount;
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
        this.getConfigFile().getParentFile().mkdirs();
        PrintWriter writer = new PrintWriter(this.getConfigFile());
        writer.println(this.getConfig() != null ? this.getConfig().toString() : "{}");
        writer.flush();
        writer.close();
    }

    public boolean existsProfile() {
        return this.getConfigFile().exists();
    }

    public HashMap<String, Long> getAwards() {
        return this.awards;
    }

    public long getAward(String award) {
        if (this.getAwards().containsKey(award)) {
            return this.getAwards().get(award);
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
        return Bukkit.getPlayer(this.getName());
    }

    public long getTempCoins() {
        return this.tempCoins;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public MapProfile loadSync() throws IOException, InvalidConfigurationException {
        if (this.isLoaded()) {
            return this;
        }
        if (!this.existsProfile()) {
            return this;
        }
        this.config = parser.parse(new FileReader(configFile)).getAsJsonObject();
        this.getAwards().clear();
        for (String award : allAwards) {
            this.getAwards().put(award, this.getConfig().get(award).getAsLong());
        }
        this.cargo = Cargo.valueOf(this.getConfig().get("cargo").getAsString());
        this.loaded = true;
        if (!getProfiles().containsKey(this.getName())) {
            getProfiles().put(this.getName(), this);
        }
        return this;
    }

    public void reset() {
        this.tempCoins = 0;
        for (String award : allAwards) {
            try {
                this.setAward(award, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAward(final String award, final long amount) throws IOException {
        this.getAwards().remove(award);
        this.getAwards().put(award, amount);
        this.getConfig().remove(award);
        this.getConfig().addProperty(award, amount);
        saveConfig();
    }

    public void setCargo(Cargo cargo) throws IOException {
        this.cargo = cargo;
        this.getConfig().remove("cargo");
        this.getConfig().addProperty("cargo", cargo.toString());
        saveConfig();
    }

    public void unload() {
        getProfiles().remove(this.getName());
    }

}
