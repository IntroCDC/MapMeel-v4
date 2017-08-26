package br.com.introcdc.mapmeelv4.bases;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.enums.UpdateType;
import br.com.introcdc.mapmeelv4.events.TimerStartEvent;
import br.com.introcdc.mapmeelv4.events.TimerStopEvent;
import br.com.introcdc.mapmeelv4.events.TimerUpdateEvent;
import br.com.introcdc.mapmeelv4.events.UpdateEvent;

public class TimerManager implements Listener {

    private boolean actived;
    private String name;
    private int time;

    public TimerManager(String name, int time) {
        this.name = name;
        this.time = time;
        this.actived = false;
        Bukkit.getPluginManager().registerEvents(this, MapMain.getPlugin());
    }

    public String getName() {
        return this.name;
    }

    public int getTime() {
        return this.time;
    }

    public boolean isActived() {
        return this.actived;
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType().equals(UpdateType.SECONDS) && event.getTimes() == 1) {
            if (this.isActived()) {
                if (this.time > 0) {
                    this.time--;
                    Bukkit.getPluginManager().callEvent(new TimerUpdateEvent(this.getName(), this.getTime()));
                } else {
                    this.setActived(false);
                }
            }
        }
    }

    public void setActived(boolean actived) {
        if (actived) {
            if (!this.isActived()) {
                Bukkit.getPluginManager().callEvent(new TimerStartEvent(this.getName(), this.getTime()));
            }
        } else {
            if (this.isActived()) {
                Bukkit.getPluginManager().callEvent(new TimerStopEvent(this.getName(), this.getTime()));
            }
        }
        this.actived = actived;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
