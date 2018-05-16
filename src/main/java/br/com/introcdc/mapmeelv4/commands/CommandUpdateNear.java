package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 23:55
 */

import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class CommandUpdateNear extends CommandBase {

    public CommandUpdateNear() {
        super("updatenearwater");
        this.onlyStaff = true;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "§cApenas jogadores em jogo!");
            return;
        }
        Player player = getPlayer(sender.getName());
        if (args.length > 0) {
            int blocks = 0;
            HashSet<Block> blocksList = MapUtils.nearBlocks(player.getLocation(), Integer.parseInt(args[0]));
            for (Block block : blocksList) {
                if (block.getType().toString().contains("WATER") && block.getLocation().clone().add(0, -1, 0).getBlock().getType().toString().contains("AIR")) {
                    blocks++;
                    block.getLocation().clone().add(0, -1, 0).getBlock().setType(Material.WATER);
                }
            }
            player.sendMessage(PREFIX + "§aAtualizando... (" + blocks + ")");
            return;
        }
        connectUse(sender, "/" + label + " [número]");
    }
}
