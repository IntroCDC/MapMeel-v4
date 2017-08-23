package br.com.introcdc.mapmeelv4.commands;

import org.bukkit.command.CommandSender;

import br.com.introcdc.mapmeelv4.bases.CommandBase;
import br.com.introcdc.mapmeelv4.enums.Cargo;

public class CommandSetCargo extends CommandBase {

    public CommandSetCargo() {
        super("setcargo");
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (args.length >= 2) {
            if (!this.getProfile(args[0]).existsProfile()) {
                sender.sendMessage(this.PREFIX + "§cPerfil não encontrado!");
                return;
            }
            if (args[1].equalsIgnoreCase("ADMIN")) {
                this.getProfile(args[0]).setCargo(Cargo.ADMIN);
                sender.sendMessage(this.PREFIX + "§fCargo alterado para ADMIN com sucesso!");
                return;
            } else if (args[1].equalsIgnoreCase("CONVIDADO")) {
                this.getProfile(args[0]).setCargo(Cargo.CONVIDADO);
                sender.sendMessage(this.PREFIX + "§fCargo alterado para CONVIDADO com sucesso!");
                return;
            }
        }
        this.connectUse(sender, label + " [nick] [convidado/admin]");
    }

}
