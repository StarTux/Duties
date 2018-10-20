// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.listeners;

import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import me.th3pf.plugins.duties.Duties;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class RemindListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
            return;
        }
        if (!Duties.Memories.containsKey(event.getPlayer().getName())) {
            return;
        }
        if (!Duties.Config.GetBoolean("Actions.RemindPlayers")) {
            return;
        }
        if (event.isCancelled() && Duties.latestEventCancelled) {
            event.getPlayer().sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Reminder.ChestOpenCancelled"));
            Duties.latestEventCancelled = false;
            return;
        }
        if (!event.getPlayer().hasPermission("duties.getreminder.onchestopen") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(event.getPlayer(), "duties.getreminder.onchestopen"))) {
            return;
        }
        if (event.getClickedBlock().getType() != Material.CHEST && event.getClickedBlock().getType() != Material.TRAPPED_CHEST && event.getClickedBlock().getType() != Material.ENDER_CHEST) {
            return;
        }
        if (!Duties.LastChestReminderTime.containsKey(event.getPlayer().getName())) {
            Duties.LastChestReminderTime.put(event.getPlayer().getName(), (long)new Integer(0));
        }
        if (Duties.Config.GetInteger("ReminderCooldown") != 0L && event.getPlayer().getWorld().getTime() - Duties.LastChestReminderTime.get(event.getPlayer().getName()) <= Duties.Config.GetInteger("ReminderCooldown")) {
            return;
        }
        event.getPlayer().sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Reminder.ChestOpen"));
        Duties.LastChestReminderTime.put(event.getPlayer().getName(), event.getPlayer().getWorld().getTime());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        if (!Duties.Memories.containsKey(event.getPlayer().getName())) {
            return;
        }
        if (!Duties.Config.GetBoolean("Actions.RemindPlayers")) {
            return;
        }
        if (event.isCancelled()) {
            event.getPlayer().sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Reminder.ItemDropCancelled"));
            return;
        }
        if (!event.getPlayer().hasPermission("duties.getreminder.onitemdrop") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(event.getPlayer(), "duties.getreminder.onitemdrop"))) {
            return;
        }
        if (!Duties.LastDropReminderTime.containsKey(event.getPlayer().getName())) {
            Duties.LastDropReminderTime.put(event.getPlayer().getName(), (long)new Integer(0));
        }
        if (Duties.Config.GetInteger("ReminderCooldown") != 0L && event.getPlayer().getWorld().getTime() - Duties.LastDropReminderTime.get(event.getPlayer().getName()) <= Duties.Config.GetInteger("ReminderCooldown")) {
            return;
        }
        event.getPlayer().sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Reminder.ItemDrop"));
        Duties.LastDropReminderTime.put(event.getPlayer().getName(), event.getPlayer().getWorld().getTime());
    }
}
