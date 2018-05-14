package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 17:37
 */

import br.com.introcdc.mapmeelv4.bases.CommandBase;
import br.com.introcdc.mapmeelv4.enums.CoinType;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLevelSettings extends CommandBase {

    public CommandLevelSettings() {
        super("levelsettings");
        this.onlyStaff = true;
        this.description = "Alterar configurações de um level.";
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "§cApenas jogadores em jogo!");
            return;
        }
        if (args.length > 1) {
            if (args[0].equalsIgnoreCase("addcoin")) {
                if (Level.getLevel(getPlayer(sender.getName()).getWorld().getName()) != null) {
                    if (CoinType.valueOf(args[1].toUpperCase()) != null) {
                        Level.getLevel(getPlayer(sender.getName()).getWorld().getName()).addCoin(getPlayer(sender.getName()).getLocation(), CoinType.valueOf(args[1].toUpperCase()));
                        sender.sendMessage(PREFIX + "§aCoin adicionada!");
                    } else {
                        sender.sendMessage(PREFIX + "§cTipo de coin não reconhecida!");
                    }
                } else {
                    sender.sendMessage(PREFIX + "§cO mundo que você está agora, não é um level!");
                }
                return;
            }
        }
        connectUse(sender, "/" + label + " addcoin [tipo]");
    }
}
