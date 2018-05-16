package br.com.introcdc.mapmeelv4.listeners.block;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 08:34
 */

import br.com.introcdc.mapmeelv4.utils.MapUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignColor implements Listener {

    @EventHandler
    public void onSign(SignChangeEvent event) throws Exception {
        if (MapUtils.getProfile(event.getPlayer().getName()).getCargo().isStaff()) {
            for (int i = 0; i < 4; i++) {
                event.setLine(i, ChatColor.translateAlternateColorCodes('&', event.getLine(i)));
            }
        }
    }

}
