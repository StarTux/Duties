// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.adapters;

import org.bukkit.plugin.RegisteredServiceProvider;
import me.th3pf.plugins.duties.Duties;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultAdapter
{
    public Permission permission;
    public Economy economy;
    public Chat chat;
    
    public VaultAdapter() {
        this.permission = null;
        this.economy = null;
        this.chat = null;
        if (Duties.Config.GetBoolean("Vault.Permissions") && !this.setupPermissions()) {
            Duties.Config.SetBoolean("Vault.Permissions", false);
            Duties.GetInstance().saveConfig();
            Duties.GetInstance().LogMessage("Vault didn't hook any permissions plugin, disabled the setting!");
        }
        if (Duties.Config.GetBoolean("Vault.NameFormatting") && !this.setupChat()) {
            Duties.Config.SetBoolean("Vault.NameFormatting", false);
            Duties.GetInstance().saveConfig();
            Duties.GetInstance().LogMessage("Vault didn't hook any chat plugin, disabled the setting!");
        }
        if (Duties.Config.GetBoolean("Vault.Economy") && !this.setupEconomy()) {
            Duties.Config.SetBoolean("Vault.Economy", false);
            Duties.GetInstance().saveConfig();
            Duties.GetInstance().LogMessage("Vault didn't hook any economy plugin, disabled the setting!");
        }
    }
    
    private Boolean setupPermissions() {
        final RegisteredServiceProvider<Permission> permissionProvider = (RegisteredServiceProvider<Permission>)Duties.GetInstance().getServer().getServicesManager().getRegistration((Class)Permission.class);
        if (permissionProvider != null) {
            this.permission = (Permission)permissionProvider.getProvider();
            Duties.GetInstance().LogMessage("Vault permissions hooked.");
        }
        if (this.permission != null) {
            return true;
        }
        return false;
    }
    
    private Boolean setupChat() {
        final RegisteredServiceProvider<Chat> chatProvider = (RegisteredServiceProvider<Chat>)Duties.GetInstance().getServer().getServicesManager().getRegistration((Class)Chat.class);
        if (chatProvider != null) {
            this.chat = (Chat)chatProvider.getProvider();
            Duties.GetInstance().LogMessage("Vault chat hooked.");
        }
        if (this.chat != null) {
            return true;
        }
        return false;
    }
    
    private Boolean setupEconomy() {
        final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>)Duties.GetInstance().getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (economyProvider != null) {
            this.economy = (Economy)economyProvider.getProvider();
            Duties.GetInstance().LogMessage("Vault economy hooked.");
        }
        if (this.economy != null) {
            return true;
        }
        return false;
    }
}
