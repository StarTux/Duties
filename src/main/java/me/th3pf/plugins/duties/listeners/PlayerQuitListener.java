// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.th3pf.plugins.duties.ModeSwitcher;
import me.th3pf.plugins.duties.Duties;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.Listener;

public class PlayerQuitListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        if (!Duties.Memories.containsKey(event.getPlayer().getName())) {
            return;
        }
        new ModeSwitcher(event.getPlayer()).DisableDutyMode();
    }
}
