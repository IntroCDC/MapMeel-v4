package br.com.introcdc.mapmeelv4.events;

import br.com.introcdc.mapmeelv4.timer.UpdateType;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return UpdateEvent.handlers;
    }

    private int times;
    private UpdateType type;

    public UpdateEvent(UpdateType type, int times) {
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
