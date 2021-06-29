package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 00:30
 */

import br.com.introcdc.mapmeelv4.command.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFlySpeed extends CommandBase {

    public CommandFlySpeed() {
        super("flyspeed");
        this.onlyStaff = true;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "§cApenas jogadores em jogo!");
            return;
        }
        Player player = (Player) sender;
        if (args.length > 0) {
            player.setFlySpeed(Float.parseFloat(args[0]) / 100);
            player.sendMessage(PREFIX + "§aVelocidade de voo alterada!");
            return;
        }
        correctUse(sender, "/" + label + " [velocidade]");
    }

}
