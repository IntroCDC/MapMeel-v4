package br.com.introcdc.mapmeelv4.commands;

import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.histories.FinalHistory;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class CommandTest extends CommandBase {

    public CommandTest() {
        super("test");
        this.onlyStaff = true;
        this.description = "Apenas um comando de teste";
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        Player player = getPlayerSender(sender);

        if (args.length == 2) {
            sender.sendMessage("iniciando");
            FinalHistory.startFinalHistory();
            return;
        }

        Villager.Profession profession = Villager.Profession.NITWIT;
        List<MerchantRecipe> recipes = new ArrayList<>();

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("1")) {
                profession = Villager.Profession.ARMORER;
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.LEATHER_HELMET), 10));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.LEATHER_CHESTPLATE), 10));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.LEATHER_LEGGINGS), 10));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.LEATHER_BOOTS), 10));

                recipes.add(MapUtils.createRecipe(new ItemStack(Material.IRON_HELMET), 25));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.IRON_CHESTPLATE), 25));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.IRON_LEGGINGS), 25));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.IRON_BOOTS), 25));

                recipes.add(MapUtils.createRecipe(new ItemStack(Material.DIAMOND_HELMET), 50));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.DIAMOND_CHESTPLATE), 50));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.DIAMOND_LEGGINGS), 50));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.DIAMOND_BOOTS), 50));
            }

            if (args[0].equalsIgnoreCase("2")) {
                profession = Villager.Profession.WEAPONSMITH;
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.WOODEN_SWORD), 5));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.STONE_SWORD), 10));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.IRON_SWORD), 25));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.DIAMOND_SWORD), 50));

                recipes.add(MapUtils.createRecipe(new ItemStack(Material.WOODEN_AXE), 10));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.STONE_AXE), 20));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.IRON_AXE), 50));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.DIAMOND_AXE), 75));
            }

            if (args[0].equalsIgnoreCase("3")) {
                profession = Villager.Profession.FLETCHER;
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.ARROW, 16), 15));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.BOW), 25));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.CROSSBOW), 50));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.SHIELD), 30));
            }

            if (args[0].equalsIgnoreCase("4")) {
                profession = Villager.Profession.CLERIC;
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60 * 20, 0)).toItem(), 50));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60 * 20, 0)).toItem(), 50));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30 * 20, 0)).toItem(), 55));

                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.SPEED, 120 * 20, 0)).toItem(), 40));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.JUMP, 120 * 20, 0)).toItem(), 40));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 120 * 20, 0)).toItem(), 40));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 120 * 20, 0)).toItem(), 40));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120 * 20, 0)).toItem(), 40));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.LEVITATION, 15 * 20, 0)).toItem(), 40));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.GLOWING, 120 * 20, 0)).toItem(), 40));

                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 0)).toItem(), 55));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.SATURATION, 10 * 20, 0)).toItem(), 55));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 10 * 20, 0)).toItem(), 55));

                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.POTION)).addCustonEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 300 * 20, 0)).toItem(), 60));
            }

            if (args[0].equalsIgnoreCase("5")) {
                profession = Villager.Profession.SHEPHERD;
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.ELYTRA), 25));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.FIREWORK_ROCKET, 10), 20));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.TRIDENT), 50));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.TOTEM_OF_UNDYING), 75));
            }

            if (args[0].equalsIgnoreCase("6")) {
                profession = Villager.Profession.LIBRARIAN;

                recipes.add(MapUtils.createRecipe(new ItemStack(Material.LAPIS_LAZULI, 16), 5));
                recipes.add(MapUtils.createRecipe(new ItemStack(Material.EXPERIENCE_BOTTLE, 16), 30));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.DURABILITY, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.MENDING, 1).toItem(), 25));

                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.THORNS, 1).toItem(), 30));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.OXYGEN, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.FROST_WALKER, 1).toItem(), 25));

                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.DAMAGE_ALL, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.FIRE_ASPECT, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.KNOCKBACK, 1).toItem(), 25));

                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.ARROW_DAMAGE, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.ARROW_KNOCKBACK, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.ARROW_INFINITE, 1).toItem(), 75));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.QUICK_CHARGE, 1).toItem(), 25));

                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.CHANNELING, 1).toItem(), 25));
                recipes.add(MapUtils.createRecipe(MapUtils.itemBuilder(new ItemStack(Material.ENCHANTED_BOOK)).addEnchantment(Enchantment.LOYALTY, 1).toItem(), 25));
            }
        }

        Villager villager = player.getWorld().spawn(player.getLocation(), Villager.class);
        villager.setProfession(profession);
        villager.setVillagerType(Villager.Type.PLAINS);
        villager.setVillagerLevel(5);
        villager.setRecipes(recipes);
    }

}
