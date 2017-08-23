package br.com.introcdc.mapmeelv4.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimerStopEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return TimerStopEvent.handlers;
    }

    private final String name;

    private final int time;

    public TimerStopEvent(final String name, final int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public HandlerList getHandlers() {
        return TimerStopEvent.handlers;
    }

    public String getName() {
        return this.name;
    }

    public int getTime() {
        return this.time;
    }
}
