package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 14/05/2018 - 03:09
 */

import br.com.introcdc.mapmeelv4.coin.CoinType;
import br.com.introcdc.mapmeelv4.coin.MapCoin;
import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.mob.MapMob;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

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
            Map<CoinType, Integer> coinsAmount = new HashMap<>();
            for (MapCoin mapCoin : level.getLoadedCoins()) {
                if (!coinsAmount.containsKey(mapCoin.getType())) {
                    coinsAmount.put(mapCoin.getType(), 0);
                }
                coinsAmount.replace(mapCoin.getType(), coinsAmount.get(mapCoin.getType()) + 1);
                total += mapCoin.getType().getCoins();
            }
            coinsAmount.keySet().forEach(key -> sender.sendMessage(PREFIX + "§fMoeda tipo §a" + key + "§f: " + coinsAmount.get(key)));
            sender.sendMessage(PREFIX + "§fMoedas (Total): §a" + total);

            int amountMobs = 0;
            Map<EntityType, Integer> mobsAmount = new HashMap<>();
            for (MapMob mapMob : level.getLoadedMobs()) {
                if (!mobsAmount.containsKey(mapMob.getEntityType())) {
                    mobsAmount.put(mapMob.getEntityType(), 0);
                }
                mobsAmount.replace(mapMob.getEntityType(), mobsAmount.get(mapMob.getEntityType()) + 1);
                amountMobs++;
            }
            mobsAmount.keySet().forEach(key -> sender.sendMessage(PREFIX + "§fMob tipo §a" + key + "§f: " + mobsAmount.get(key)));
            sender.sendMessage(PREFIX + "§fMobs (Total): §a" + amountMobs);

            return;
        }
        correctUse(sender, "/" + label + " [Level]");
    }
}
