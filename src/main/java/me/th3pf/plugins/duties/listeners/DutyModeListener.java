// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.listeners;

import me.th3pf.plugins.duties.events.DutyModeDisabledEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.th3pf.plugins.duties.events.DutyModeEnabledEvent;
import org.bukkit.event.Listener;

public class DutyModeListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDutyModeEnabled(final DutyModeEnabledEvent event) {
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDutyModeDisabled(final DutyModeDisabledEvent event) {
    }
}
