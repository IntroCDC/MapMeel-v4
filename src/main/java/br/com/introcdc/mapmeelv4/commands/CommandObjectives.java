package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 29/07/2019 - 01:25
 */

import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.item.ItemBuilder;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CommandObjectives extends CommandBase {

    public CommandObjectives() {
        super("objetivos");
        this.description = "Listar objetivos pendentes do MapMeel v4!";
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        Inventory inventory = Bukkit.createInventory(null, 54, "Objetivos");

        for (String[] levelName : new String[][]{
                new String[]{"1A", "2"}, new String[]{"1B", "3"}, new String[]{"1C", "5"}, new String[]{"1D", "6"},
                new String[]{"2A", "11"}, new String[]{"2B", "12"}, new String[]{"2C", "14"}, new String[]{"2D", "15"},
                new String[]{"3A", "20"}, new String[]{"3B", "21"}, new String[]{"3C", "23"}, new String[]{"3D", "24"},
                new String[]{"4A", "29"}, new String[]{"4B", "30"}, new String[]{"4C", "32"}, new String[]{"4D", "33"},
                new String[]{"EG-SKY", "37"}, new String[]{"EG-CASTLE", "38"}, new String[]{"EG-CAVE", "39"}, new String[]{"EG-FOREST", "40"}, new String[]{"EG-HELL", "41"}, new String[]{"EG-UNDERWATER", "42"}, new String[]{"EG-WATER", "43"},
                new String[]{"FINAL-BOSS", "49"}}) {

            Level level = Level.getLevel(levelName[0]);
            if (level == null) {
                return;
            }

            ItemBuilder itemBuilder = new ItemBuilder(new ItemStack(Material.ACACIA_PLANKS));
            itemBuilder.setName("§a" + level.getWarp().toString());

            ArrayList<String> lore = new ArrayList<>();
            lore.add("§fNome: §a" + level.getName());
            lore.add("§fWarp: §a" + level.getWarp().toString());
            lore.add("§f");
            for (LevelObjective levelObjective : level.getObjectives().values()) {
                lore.add("§f§o" + levelObjective.getStringObjective() + ": " + (levelObjective.isFinished() ? "§aFinalizado" : "§cNão Finalizado!"));
            }

            inventory.setItem(Integer.valueOf(levelName[1]), itemBuilder.setLore(lore).toItem());
        }

        getPlayerSender(sender).openInventory(inventory);
    }

}
