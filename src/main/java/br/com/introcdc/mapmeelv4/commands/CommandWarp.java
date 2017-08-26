package br.com.introcdc.mapmeelv4.commands;

import org.bukkit.command.CommandSender;

import br.com.introcdc.mapmeelv4.MapUtils;
import br.com.introcdc.mapmeelv4.bases.CommandBase;
import br.com.introcdc.mapmeelv4.enums.Warp;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandWarp extends CommandBase {

    public CommandWarp() {
        super("warp");
        this.onlyStaff = true;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (!this.isPlayer(sender)) {
            return;
        }
        if (args.length > 0) {
            Warp selected = null;
            for (Warp warp : Warp.values()) {
                if (warp.getName().equalsIgnoreCase(args[0])) {
                    selected = warp;
                    break;
                }
            }
            if (selected != null) {
                if (args.length > 1) {
                    if (!this.isOnlinePlayer(sender, args[1])) {
                        return;
                    }
                    this.getPlayer(args[1]).teleport(selected.getLocation());
                    sender.sendMessage(MapUtils.PREFIX + "§fVocê teleportou §a" + this.getPlayer(args[1]).getName() + "§f para a warp §a" + selected.getName() + "§f com sucesso!");
                } else {
                    this.getPlayerSender(sender).teleport(selected.getLocation());
                    sender.sendMessage(MapUtils.PREFIX + "§fVocê teleportou-se para a warp §a" + selected.getName() + "§f com sucesso!");
                }
                return;
            } else {
                sender.sendMessage(MapUtils.PREFIX + "§cWarp não encontrada!");
            }
        }
        this.connectUse(sender, label + " [warp]");
        TextComponent warps = new TextComponent(MapUtils.PREFIX + "§fWarps: §a");
        for (Warp warp : Warp.values()) {
            TextComponent warpp = new TextComponent("§a" + warp.getName() + "§f, ");
            warpp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fClique para teleportar-se para a warp!").create()));
            warpp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp " + warp.getName()));
            warps.addExtra(warpp);
        }
        this.getPlayerSender(sender).spigot().sendMessage(warps);
    }

}
