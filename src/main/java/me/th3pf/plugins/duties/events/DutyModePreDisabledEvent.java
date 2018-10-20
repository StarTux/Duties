// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class DutyModePreDisabledEvent extends Event
{
    private static final HandlerList handlers;
    private Player player;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public DutyModePreDisabledEvent(final Player player) {
        this.cancelled = false;
        this.player = player;
    }
    
    public HandlerList getHandlers() {
        return DutyModePreDisabledEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return DutyModePreDisabledEvent.handlers;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public boolean getCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
}
