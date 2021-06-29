package br.com.introcdc.mapmeelv4.listeners.npc;
/*
 * Written by IntroCDC, Bruno Coêlho at 26/04/2019 - 06:07
 */

import br.com.introcdc.mapmeelv4.npctalk.NPCTalk;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCEvents implements Listener {

    @EventHandler
    public void onRightClick(NPCRightClickEvent event) {
        if (!event.getNPC().getEntity().getType().equals(EntityType.PLAYER)) {
            return;
        }

        for (NPCTalk npc : NPCTalk.allNpcs) {
            if (event.getNPC().getName().equalsIgnoreCase(npc.getName()) &&
                    event.getClicker().getLocation().distance(npc.getLocation()) < 10) {
                npc.talk(event.getClicker());
            }
        }
    }

}
