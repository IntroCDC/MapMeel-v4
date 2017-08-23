package br.com.introcdc.mapmeelv4.commands;

import org.bukkit.command.CommandSender;

import br.com.introcdc.mapmeelv4.bases.CommandBase;

public class CommandRegisterAccount extends CommandBase {

    public CommandRegisterAccount() {
        super("registeraccount");
        this.onlyStaff = true;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (args.length > 0) {
            if (this.getProfile(args[0]).existsProfile()) {
                sender.sendMessage(this.PREFIX + "§cEsta conta já está registrada no servidor!");
            } else {
                this.getProfile(args[0]).createFile();
                sender.sendMessage(this.PREFIX + "Conta registrada!");
            }
            return;
        }
        this.connectUse(sender, label + " [Nick]");
    }

}
