// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.th3pf.plugins.duties.Duties;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class PlayerJoinListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        if (!Duties.Memories.containsKey(event.getPlayer())) {
            return;
        }
        if (!Duties.Config.GetBoolean("Actions.RemindPlayers")) {
            return;
        }
        if (!event.getPlayer().hasPermission("duties.getreminder.onlogin") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(event.getPlayer(), "duties.getreminder.onlogin"))) {
            return;
        }
        event.getPlayer().sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Reminder.Login"));
    }
}
