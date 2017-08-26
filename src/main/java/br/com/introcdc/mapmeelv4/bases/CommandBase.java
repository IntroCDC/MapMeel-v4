package br.com.introcdc.mapmeelv4.bases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv4.MapProfile;
import br.com.introcdc.mapmeelv4.MapUtils;

public abstract class CommandBase extends Command {

    private static List<String> toList(String[] array) {
        ArrayList<String> aliases = new ArrayList<>();
        Collections.addAll(aliases, array);
        return aliases;
    }

    protected boolean onlyStaff = false;

    public String PREFIX = MapUtils.PREFIX;

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase(String name, String... aliases) {
        super(name, "", "", CommandBase.toList(aliases));
    }

    public void connectUse(CommandSender sender, String command) {
        sender.sendMessage(MapUtils.PREFIX + "§fUso correto: §a/" + command);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (this.onlyStaff) {
            if (!this.isStaff(sender)) {
                return false;
            }
        }
        try {
            this.onExecute(sender, label, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Player getPlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    public Player getPlayerSender(CommandSender sender) {
        return (Player) sender;
    }

    public MapProfile getProfile(String name) throws Exception {
        return MapUtils.getProfile(name);
    }

    public boolean isOnlinePlayer(CommandSender sender, String name) {
        return this.isOnlinePlayer(sender, name, true);
    }

    public boolean isOnlinePlayer(CommandSender sender, String name, boolean sendMessage) {
        if (sendMessage && sender != null) {
            if (this.getPlayer(name) == null) {
                sender.sendMessage(MapUtils.PREFIX + "§cEste jogador não está online!");
            }
        }
        return this.getPlayer(name) != null;
    }

    public boolean isOnlinePlayer(String name) {
        return this.isOnlinePlayer(null, name, false);
    }

    public boolean isPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return true;
        }
        sender.sendMessage(MapUtils.PREFIX + "§cEste comando pode ser executado apenas por jogadores no servidor!");
        return false;
    }

    public boolean isStaff(CommandSender sender) {
        if (sender instanceof Player) {
            try {
                return this.getProfile(sender.getName()).getCargo().isStaff();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public abstract void onExecute(CommandSender sender, String label, String[] args) throws Exception;

}
