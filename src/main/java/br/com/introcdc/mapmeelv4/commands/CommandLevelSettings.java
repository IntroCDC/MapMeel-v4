package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 13/05/2018 - 17:37
 */

import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
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
        if (Level.getLevel(getPlayer(sender.getName()).getWorld().getName()) == null) {
            sender.sendMessage(PREFIX + "§cO mundo que você está agora, não é um level!");
            return;
        }
        Level level = Level.getLevel(getPlayer(sender.getName()).getWorld().getName());
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("addcoin")) {
                if (CoinType.valueOf(args[1].toUpperCase()) != null) {
                    level.addCoin(getPlayer(sender.getName()).getLocation(), CoinType.valueOf(args[1].toUpperCase()));
                    sender.sendMessage(PREFIX + "§aCoin adicionada!");
                } else {
                    sender.sendMessage(PREFIX + "§cTipo de coin não reconhecida!");
                }
                return;
            } else if (args[0].equalsIgnoreCase("loadcoins")) {
                level.loadCoins();
                sender.sendMessage(PREFIX + "§aMoedas carregadas!");
                return;
            } else if (args[0].equalsIgnoreCase("unloadcoins")) {
                level.unloadCoins();
                sender.sendMessage(PREFIX + "§aMoedas descarregadas!");
                return;
            } else if (args[0].equalsIgnoreCase("addmob")) {
                if (EntityType.valueOf(args[1].toUpperCase()) != null) {
                    level.addMob(getPlayer(sender.getName()).getLocation(), EntityType.valueOf(args[1].toUpperCase()));
                    sender.sendMessage(PREFIX + "§aMob adicionado!");
                } else {
                    sender.sendMessage(PREFIX + "§cTipo de mod não reconhecido!");
                }
                return;
            } else if (args[0].equalsIgnoreCase("loadmobs")) {
                level.loadMobs();
                sender.sendMessage(PREFIX + "§aMobs carregados!");
                return;
            } else if (args[0].equalsIgnoreCase("unloadmobs")) {
                level.unloadMobs();
                sender.sendMessage(PREFIX + "§aMobs descarregados!");
                return;
            }
        }
        correctUse(sender, "/" + label + " addcoin [tipo]");
        correctUse(sender, "/" + label + " loadcoins/unloadcoins");
    }

}
