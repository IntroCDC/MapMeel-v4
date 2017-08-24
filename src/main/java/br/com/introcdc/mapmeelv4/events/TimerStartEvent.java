package br.com.introcdc.mapmeelv4.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimerStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return TimerStartEvent.handlers;
    }

    private final String name;

    private final int time;

    public TimerStartEvent(final String name, final int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public HandlerList getHandlers() {
        return TimerStartEvent.handlers;
    }

    public String getName() {
        return this.name;
    }

    public int getTime() {
        return this.time;
    }
}
