package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 28/08/2017 - 00:33
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandSetTempBlock extends CommandBase {

    public CommandSetTempBlock() {
        super("settempblock");
    }

    public static List<Block> cooldown = new ArrayList<>();

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (args.length == 6) {
            if (Bukkit.getWorld(args[0]) != null && MapUtils.isNumber(args[1]) && MapUtils.isNumber(args[2]) && MapUtils.isNumber(args[3]) && Material.valueOf(args[4]) != null) {
                int x = Integer.parseInt(args[1]), y = Integer.parseInt(args[2]), z = Integer.parseInt(args[3]);
                Block block = Bukkit.getWorld(args[0]).getBlockAt(x, y, z);
                if (cooldown.contains(block)) {
                    return;
                }
                cooldown.add(block);
                Material type = block.getType();
                block.setType(Material.valueOf(args[4]));
                Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {
                    block.setType(type);
                    cooldown.remove(block);
                }, Integer.parseInt(args[5]) * 20);
                return;
            }
        }
        sender.sendMessage(PREFIX + "§fUso correto: /" + label + " [Mundo] [X] [Y] [Z] [Material] [Tempo]");
    }

}
