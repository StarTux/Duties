// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class DutyModeEnabledEvent extends Event
{
    private static final HandlerList handlers;
    private Player player;
    
    static {
        handlers = new HandlerList();
    }
    
    public DutyModeEnabledEvent(final Player player) {
        this.player = player;
    }
    
    public HandlerList getHandlers() {
        return DutyModeEnabledEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return DutyModeEnabledEvent.handlers;
    }
    
    public Player getPlayer() {
        return this.player;
    }
}
