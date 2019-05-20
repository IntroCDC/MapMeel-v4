package br.com.introcdc.mapmeelv4.listeners.chat;
/*
 * Written by IntroCDC, Bruno Coêlho at 27/04/2019 - 04:20
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        event.setCancelled(true);
        Bukkit.broadcastMessage("§f§l" + (event.getPlayer().isOp() ? "§o" : "") + event.getPlayer().getName() + "§f: " + ChatColor.translateAlternateColorCodes('&', event.getMessage()));

    }

}
