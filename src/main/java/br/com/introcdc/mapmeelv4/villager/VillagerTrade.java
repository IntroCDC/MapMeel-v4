package br.com.introcdc.mapmeelv4.villager;

import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.MerchantRecipe;

import java.util.Arrays;
import java.util.List;

public enum VillagerTrade {
    ARMOR(null, Villager.Profession.ARMORER, MapUtils.createRecipe(Material.STONE_SWORD, 10), MapUtils.createRecipe(Material.CHAINMAIL_HELMET, 15), MapUtils.createRecipe(Material.CHAINMAIL_CHESTPLATE, 15), MapUtils.createRecipe(Material.CHAINMAIL_LEGGINGS, 15), MapUtils.createRecipe(Material.CHAINMAIL_BOOTS, 15)),
    ELYTRA(null, Villager.Profession.LEATHERWORKER, MapUtils.createRecipe(Material.ELYTRA, 25), MapUtils.createRecipe(Material.FIREWORK_ROCKET, 1));

    private String name;
    private Villager.Profession profession;
    private List<MerchantRecipe> recipes;

    VillagerTrade(String name, Villager.Profession profession, MerchantRecipe... recipes) {
        this.name = name;
        this.profession = profession;
        this.recipes = Arrays.asList(recipes);
    }

    public String getName() {
        return name;
    }

    public Villager.Profession getProfession() {
        return profession;
    }

    public List<MerchantRecipe> getRecipes() {
        return recipes;
    }

}

