package br.com.introcdc.mapmeelv4.commands;
/*
 * Written by IntroCDC, Bruno Coêlho at 25/04/2019 - 21:32
 */

import br.com.introcdc.mapmeelv4.command.CommandBase;
import br.com.introcdc.mapmeelv4.door.Door;
import br.com.introcdc.mapmeelv4.level.Level;
import br.com.introcdc.mapmeelv4.level.LevelObjective;
import org.bukkit.command.CommandSender;

public class CommandResetAll extends CommandBase {

    public CommandResetAll() {
        super("resetall");
        this.onlyStaff = true;
        this.description = "Definir todas as fases do mapa como não concluidas!";
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) throws Exception {
        sender.sendMessage(PREFIX + "Resetando objetivos...");
        for (Level level : Level.getLeveis().values()) {
            for (LevelObjective levelObjective : level.getObjectives().values()) {
                levelObjective.setFinished(false, null);
            }
            level.save();
        }
        Level.stars = 0;

        for (Door door : Door.allDoors) {
            door.openDoor(false);
        }

        if (args.length > 0) {
            Level.stars = Integer.parseInt(args[0]);
        }

        sender.sendMessage(PREFIX + "Objetivos resetados!");
    }

}
