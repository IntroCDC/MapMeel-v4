package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 14:57
 */

import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLobby extends CommandBase {

    public CommandLobby() {
        super("Lobby", "Spawn", "LobbyPrincipal", "LobbyMapMeel", "Retornar");
        this.description = "Ir para o Lobby";
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "§cApenas jogadores em jogo!");
            return;
        }
        Player player = getPlayer(sender.getName());
        if (Level.getLevel(player.getWorld().getName()) == null) {
            sender.sendMessage(PREFIX + "§cVocê não está em um level!");
            return;
        }
        Level level = Level.getLevel(player.getWorld().getName());
        level.unloadLevel(null);
    }

}
