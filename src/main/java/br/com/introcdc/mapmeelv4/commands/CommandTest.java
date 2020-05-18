package br.com.introcdc.mapmeelv4.commands;

import br.com.introcdc.mapmeelv4.advancement.CustomAdvancement;
import br.com.introcdc.mapmeelv4.command.CommandBase;
import org.bukkit.command.CommandSender;

public class CommandTest extends CommandBase {

    public CommandTest() {
        super("test");
        this.onlyStaff = true;
        this.description = "Apenas um comando de teste";
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
    }

}
