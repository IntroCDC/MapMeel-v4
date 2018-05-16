package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 28/08/2017 - 00:33
 */

import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CommandSetTempBlock extends CommandBase {

    public CommandSetTempBlock() {
        super("settempblock");
    }

    public static List<Block> cooldown = new ArrayList<>();

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (args.length == 7) {
            if (Bukkit.getWorld(args[0]) != null && MapUtils.isNumber(args[1]) && MapUtils.isNumber(args[2]) && MapUtils.isNumber(args[3]) && Material.valueOf(args[4]) != null && MapUtils.isNumber(args[5]) && MapUtils.isNumber(args[6])) {
                if (cooldown.contains(Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])))) {
                    return;
                }
                cooldown.add(Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                Material type = Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).getType();
                byte data = Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).getData();
                Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).setType(Material.valueOf(args[4]));
                Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).setData((byte) Integer.parseInt(args[5]));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).setType(type);
                        Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])).setData(data);
                        cooldown.remove(Bukkit.getWorld(args[0]).getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                    }
                }.runTaskLater(MapUtils.getPlugin(), Integer.parseInt(args[6]) * 20);
                return;
            }
        }
        sender.sendMessage(PREFIX + "§fUso correto: /" + label + " [Mundo] [X] [Y] [Z] [Material] [Data] [Tempo]");
    }

}
