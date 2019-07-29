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
        this.onlyStaff = true;
        this.description = "Listar objetivos pendentes do MapMeel v4!";
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        Inventory inventory = Bukkit.createInventory(null, 54);

        for (String levelName : Level.getLeveis().keySet()) {
            Level level = Level.getLevel(levelName);
            if (level == null) {
                continue;
            }

            ItemBuilder itemBuilder = new ItemBuilder(new ItemStack(Material.GRASS));
            itemBuilder.setName("§a" + levelName);

            ArrayList<String> lore = new ArrayList<>();
            for (LevelObjective levelObjective : level.getObjectives().values()) {
                lore.add("§f§o" + levelObjective.getStringObjective() + ": " + (levelObjective.isFinished() ? "§aFinalizado" : "§cNão Finalizado!"));
            }

            inventory.addItem(itemBuilder.setLore(lore).toItem());
        }

        getPlayerSender(sender).openInventory(inventory);
    }

}
