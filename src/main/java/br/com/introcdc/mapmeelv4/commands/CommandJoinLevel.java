package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 05/04/2020 - 20:26
 */

import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public class CommandJoinLevel extends CommandBase {

    public CommandJoinLevel() {
        super("joinlevel", "level");
        this.onlyStaff = true;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (!isPlayer(sender)) {
            return;
        }
        if (args.length > 0) {
            Level level = null;
            for (Level otherLevel : Level.getLeveis().values()) {
                if (otherLevel.getWarp().getName().equalsIgnoreCase(args[0])) {
                    level = otherLevel;
                }
            }
            if (level == null) {
                sender.sendMessage(MapUtils.PREFIX + "§cLevel não encontrado...");
                return;
            }
            level.joinPortal();
            return;
        }
        correctUse(sender, label + " [level]");
        TextComponent textLevel = new TextComponent(MapUtils.PREFIX + "§fLeveis: §a");
        for (Level leveel : Level.getLeveis().values()) {
            TextComponent levell = new TextComponent("§a" + leveel.getWarp().getName() + "§f, ");
            levell.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder("§fClique para entrar no level!").create()));
            levell.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                    "/joinlevel " + leveel.getWarp().getName()));
            textLevel.addExtra(levell);
        }
        getPlayerSender(sender).spigot().sendMessage(textLevel);
    }

}
