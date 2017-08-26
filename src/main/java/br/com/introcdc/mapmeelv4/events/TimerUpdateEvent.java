package br.com.introcdc.mapmeelv4.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimerUpdateEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return TimerUpdateEvent.handlers;
    }

    private String name;

    private int time;

    public TimerUpdateEvent(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public HandlerList getHandlers() {
        return TimerUpdateEvent.handlers;
    }

    public String getName() {
        return this.name;
    }

    public int getTime() {
        return this.time;
    }
}
