// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.events;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class ReloadedEvent extends Event
{
    private static final HandlerList handlers;
    
    static {
        handlers = new HandlerList();
    }
    
    public HandlerList getHandlers() {
        return ReloadedEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return ReloadedEvent.handlers;
    }
}
