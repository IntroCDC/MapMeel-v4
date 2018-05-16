package br.com.introcdc.mapmeelv4.commands;

import br.com.introcdc.mapmeelv4.profile.Cargo;
import org.bukkit.command.CommandSender;

import br.com.introcdc.mapmeelv4.command.CommandBase;

public class CommandSetCargo extends CommandBase {

    public CommandSetCargo() {
        super("setcargo");
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (args.length >= 2) {
            if (!getProfile(args[0]).existsProfile()) {
                sender.sendMessage(PREFIX + "§cPerfil não encontrado!");
                return;
            }
            if (args[1].equalsIgnoreCase("ADMIN")) {
                getProfile(args[0]).setCargo(Cargo.ADMIN);
                sender.sendMessage(PREFIX + "§fCargo alterado para ADMIN com sucesso!");
                return;
            } else if (args[1].equalsIgnoreCase("CONVIDADO")) {
                getProfile(args[0]).setCargo(Cargo.CONVIDADO);
                sender.sendMessage(PREFIX + "§fCargo alterado para CONVIDADO com sucesso!");
                return;
            }
        }
        connectUse(sender, label + " [nick] [convidado/admin]");
    }

}
