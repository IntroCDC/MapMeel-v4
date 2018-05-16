package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 03:09
 */

import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.coin.MapCoin;
import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.level.Level;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandLevelInfo extends CommandBase {

    public CommandLevelInfo() {
        super("levelinfo");
        this.onlyStaff = true;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        if (args.length > 0) {
            Level level = Level.getLevel(args[0]);
            if (level == null) {
                sender.sendMessage(PREFIX + "§cLevel não encontrado!");
                return;
            }
            sender.sendMessage(PREFIX + "§fNome: §a" + level.getName());
            sender.sendMessage(PREFIX + "§fMúsica de fundo: §a" + level.getBackgroundMapSound().toString());
            sender.sendMessage(PREFIX + "§fWarp: §a" + level.getWarp().getName());
            sender.sendMessage(PREFIX + "§fHorário fixo: §a" + level.getWarp().getTime());
            sender.sendMessage(PREFIX + "§fMoedas (Quantidade): §a" + level.getLoadedCoins().size());
            int total = 0;
            Map<CoinType, Integer> amount = new HashMap<>();
            for (MapCoin mapCoin : level.getLoadedCoins()) {
                if (!amount.containsKey(mapCoin.getType())) {
                    amount.put(mapCoin.getType(), 0);
                }
                amount.replace(mapCoin.getType(), amount.get(mapCoin.getType()) + 1);
                total += mapCoin.getType().getCoins();
            }
            amount.keySet().forEach(key -> sender.sendMessage(PREFIX + "§fMoeda tipo §a" + key + "§f: " + amount.get(key)));
            sender.sendMessage(PREFIX + "§fMoedas (Total): §a" + total);
            return;
        }
        connectUse(sender, "/" + label + " [Level]");
    }
}
