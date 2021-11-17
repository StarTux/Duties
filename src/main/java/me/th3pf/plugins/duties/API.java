// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties;

import me.th3pf.plugins.duties.adapters.VaultAdapter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class API
{
    private Plugin registredPlugin;
    
    public API() {
        this.registredPlugin = null;
    }
    
    public void RegisterAddon(final Plugin plugin, final String name, final boolean hidden) {
        if (Duties.Addons.containsKey(plugin)) {
            return;
        }
        if (plugin == null || name == null) {
            return;
        }
        try {
            Duties.Addons.put(plugin, name);
            this.registredPlugin = plugin;
            if (!hidden) {
                Duties.GetInstance().getLogger().info("Registered addon: " + name);
            }
        }
        catch (Exception exception) {
            if (!hidden) {
                Duties.GetInstance().getLogger().info("Addon '" + name + "'" + " failed to register addon " + "'" + name + "'" + "due to an unknown cause.");
            }
        }
    }
    
    public void UnregisterAddon(final Plugin plugin, final String name, final boolean hidden) {
        if (!Duties.Addons.containsKey(this.registredPlugin)) {
            return;
        }
        if (plugin == null || name == null) {
            return;
        }
        try {
            Duties.Addons.remove(plugin);
            this.registredPlugin = null;
            if (!hidden) {
                Duties.GetInstance().getLogger().info("Unregistered addon: " + name);
            }
        }
        catch (Exception exception) {
            if (!hidden) {
                Duties.GetInstance().getLogger().info("Addon '" + name + "'" + " failed to register addon " + "'" + name + "'" + "due to an unknown cause.");
            }
        }
    }
    
    public void EnableDutyModeForPlayer(final Player player) {
        if (!Duties.Addons.containsKey(this.registredPlugin)) {
            return;
        }
        new ModeSwitcher(player).EnableDutyMode();
    }
    
    public void DisableDutyModeForPlayer(final Player player) {
        if (!Duties.Addons.containsKey(this.registredPlugin)) {
            return;
        }
        new ModeSwitcher(player).DisableDutyMode();
    }
    
    public boolean IsPlayerInDutyMode(final String player) {
        return Duties.Addons.containsKey(this.registredPlugin) && Duties.Memories.keySet().contains(player);
    }
    
    public boolean HasPlayerCompleteDutyRights(final Player player) {
        return Duties.Addons.containsKey(this.registredPlugin) && player.hasPermission("duties.staff");
    }
    
    public Configuration.Main GetMainConfiguration() {
        return Duties.Config;
    }
    
    public Configuration.Messages GetMessagesConfiguration() {
        return Duties.Messages;
    }
    
    public String GetDataFolderPath() {
        return Duties.GetInstance().getDataFolder().getAbsolutePath();
    }
    
    public VaultAdapter GetVaultAdapter() {
        return Duties.VaultAdapter;
    }
    
    public Duties GetInstance() {
        return Duties.GetInstance();
    }
}
