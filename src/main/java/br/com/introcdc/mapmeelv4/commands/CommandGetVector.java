package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Co�lho at 26/08/2017 - 06:37
 */

import br.com.introcdc.mapmeelv4.bases.CommandBase;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public class CommandGetVector extends CommandBase {

    public CommandGetVector() {
        super("getvector");

    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        TextComponent message = new TextComponent(PREFIX + "�fVector: �a" + getPlayerSender(sender).getLocation().getDirection().getX() + "�f, �a" + getPlayerSender(sender).getLocation().getDirection().getY() + "�f, �a" + getPlayerSender(sender).getLocation().getDirection().getZ() + "�f (Clique para copiar)");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, getPlayerSender(sender).getLocation().getDirection().getX() + ", " + getPlayerSender(sender).getLocation().getDirection().getY() + ", " + getPlayerSender(sender).getLocation().getDirection().getZ()));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para copiar!").create()));
        getPlayerSender(sender).spigot().sendMessage(message);
    }

}
