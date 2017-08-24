package br.com.introcdc.mapmeelv4.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import br.com.introcdc.mapmeelv4.enums.UpdateType;

public class UpdateEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return UpdateEvent.handlers;
    }

    private final int times;
    private final UpdateType type;

    public UpdateEvent(final UpdateType type, final int times) {
        this.type = type;
        this.times = times;
    }

    @Override
    public HandlerList getHandlers() {
        return UpdateEvent.handlers;
    }

    public int getTimes() {
        return this.times;
    }

    public UpdateType getType() {
        return this.type;
    }
}
