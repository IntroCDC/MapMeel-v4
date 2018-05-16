package br.com.introcdc.mapmeelv4.commands;

import org.bukkit.command.CommandSender;

import br.com.introcdc.mapmeelv4.command.CommandBase;

public class CommandRegisterAccount extends CommandBase {

    public CommandRegisterAccount() {
        super("registeraccount");
        this.onlyStaff = true;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (args.length > 0) {
            if (getProfile(args[0]).existsProfile()) {
                sender.sendMessage(PREFIX + "§cEsta conta já está registrada no servidor!");
            } else {
                getProfile(args[0]).createFile();
                sender.sendMessage(PREFIX + "Conta registrada!");
            }
            return;
        }
        connectUse(sender, label + " [Nick]");
    }

}
