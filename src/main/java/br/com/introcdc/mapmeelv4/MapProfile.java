package br.com.introcdc.mapmeelv4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv4.enums.Cargo;
import br.com.introcdc.mapmeelv4.enums.CoinType;

public class MapProfile extends MapUtils {

    public static final List<String> allAwards = Arrays.asList("stars", "level");

    private static Map<String, MapProfile> profiles = new HashMap<>();

    public static Map<String, MapProfile> getProfiles() {
        return profiles;
    }

    private HashMap<String, Long> awards;
    private Cargo cargo;
    private final YamlConfiguration config;
    private final File configFile;
    private boolean loaded;
    private final String name;
    private long tempCoins;

    public MapProfile(final String name) {
        this.name = name;
        this.loaded = false;
        this.configFile = new File(getPlugin().getDataFolder().getAbsolutePath() + "/profiles/" + this.getName() + ".yml");
        this.config = new YamlConfiguration();
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
        for (String award : allAwards) {
            this.getConfig().set(award, 0);
        }
        this.getConfig().set("cargo", Cargo.CONVIDADO.toString());
        this.getConfig().save(this.getConfigFile());
    }

    public boolean existsProfile() {
        return this.getConfigFile().exists();
    }

    public HashMap<String, Long> getAwards() {
        return this.awards;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public YamlConfiguration getConfig() {
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
        this.config.load(this.getConfigFile());
        for (String award : allAwards) {
            this.getAwards().replace(award, this.getConfig().getLong(award));
        }
        this.cargo = Cargo.valueOf(this.getConfig().getString("cargo"));
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
        if (!this.getAwards().containsKey(award)) {
            this.getAwards().put(award, amount);
        }
        this.getAwards().replace(award, amount);
        this.getConfig().set(award, amount);
        this.getConfig().save(this.getConfigFile());
    }

    public void setCargo(Cargo cargo) throws IOException {
        this.cargo = cargo;
        this.getConfig().set("cargo", cargo.toString());
        this.getConfig().save(this.getConfigFile());
    }

    public void unload() {
        getProfiles().remove(this.getName());
    }

}
