// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.commandexecutors;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.io.IOException;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import me.th3pf.plugins.duties.events.ReloadedEvent;
import org.bukkit.Bukkit;
import me.th3pf.plugins.duties.adapters.VaultAdapter;
import me.th3pf.plugins.duties.Configuration;
import java.io.File;
import org.bukkit.ChatColor;
import me.th3pf.plugins.duties.Duties;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class DutiesCommandExecutor implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("duties.help") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.help"))) {
                return true;
            }
            sender.sendMessage(ChatColor.BLUE + "----------------------" + ChatColor.GOLD + "[" + ChatColor.YELLOW + "Duties" + ChatColor.GOLD + "]" + ChatColor.BLUE + "-----------" + ChatColor.YELLOW + "[Page: " + "1" + "]" + ChatColor.BLUE + "------");
            sender.sendMessage(ChatColor.GREEN + "/dutymode");
            sender.sendMessage("    Toggles the duty mode for yourself");
            sender.sendMessage(ChatColor.GREEN + "/dutymode toggle [Player]");
            sender.sendMessage("    Toggles the duty mode for yourself [or other player]");
            sender.sendMessage(ChatColor.GREEN + "/dutymode enable [Player]");
            sender.sendMessage("    Enables the duty mode for yourself [or other player]");
            sender.sendMessage(ChatColor.GREEN + "/dutymode disable [Player]");
            sender.sendMessage("    Disables the duty mode for yourself [or other player]");
            sender.sendMessage(ChatColor.GREEN + "/dutymode list");
            sender.sendMessage("    Shows a list of the staff players that have duty mode on");
            sender.sendMessage(ChatColor.GREEN + "/dutymode listall");
            sender.sendMessage("    Shows a list of all the players that have duty mode on");
            sender.sendMessage(ChatColor.YELLOW + "/duties help");
            sender.sendMessage("    Shows the help for the plugin");
            sender.sendMessage(ChatColor.RED + "/duties reload");
            sender.sendMessage("    Reloads the plugin");
            sender.sendMessage(ChatColor.RED + "/duties disable");
            sender.sendMessage("    Disables the plugin");
            sender.sendMessage(ChatColor.BLUE + "-----------------------------------------------------");
            return true;
        }
        else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
            if (!sender.hasPermission("duties.help") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.help"))) {
                return true;
            }
            if (args.length == 1) {
                sender.sendMessage(ChatColor.BLUE + "----------------------" + ChatColor.GOLD + "[" + ChatColor.YELLOW + "Duties" + ChatColor.GOLD + "]" + ChatColor.BLUE + "-----------" + ChatColor.YELLOW + "[Page: " + "1" + "]" + ChatColor.BLUE + "------");
            }
            else {
                sender.sendMessage(ChatColor.BLUE + "----------------------" + ChatColor.GOLD + "[" + ChatColor.YELLOW + "Duties" + ChatColor.GOLD + "]" + ChatColor.BLUE + "-----------" + ChatColor.YELLOW + "[Page: " + args[1] + "]" + ChatColor.BLUE + "------");
            }
            if (args.length == 1 || args[1].equalsIgnoreCase("0") || args[1].equalsIgnoreCase("1")) {
                sender.sendMessage(ChatColor.GREEN + "/dutymode");
                sender.sendMessage("    Toggles the duty mode for yourself");
                sender.sendMessage(ChatColor.GREEN + "/dutymode toggle [Player]");
                sender.sendMessage("    Toggles the duty mode for yourself [or other player]");
                sender.sendMessage(ChatColor.GREEN + "/dutymode enable [Player]");
                sender.sendMessage("    Enables the duty mode for yourself [or other player]");
                sender.sendMessage(ChatColor.GREEN + "/dutymode disable [Player]");
                sender.sendMessage("    Disables the duty mode for yourself [or other player]");
                sender.sendMessage(ChatColor.GREEN + "/dutymode list");
                sender.sendMessage("    Shows a list of the staff players that have duty mode on");
                sender.sendMessage(ChatColor.GREEN + "/dutymode listall");
                sender.sendMessage("    Shows a list of all the players that have duty mode on");
                sender.sendMessage(ChatColor.YELLOW + "/duties help");
                sender.sendMessage("    Shows the help for the plugin");
                sender.sendMessage(ChatColor.RED + "/duties reload");
                sender.sendMessage("    Reloads the plugin");
                sender.sendMessage(ChatColor.RED + "/duties disable");
                sender.sendMessage("    Disables the plugin");
            }
            else if (args[1].equalsIgnoreCase("2")) {
                sender.sendMessage(ChatColor.RED + "/duties purge");
                sender.sendMessage("    Forces every player off duty mode");
                sender.sendMessage(ChatColor.GREEN + "/hidebroadcast [Player]");
                sender.sendMessage("    Disables duty mode changes broadcasting");
                sender.sendMessage(ChatColor.GREEN + "/hidebroadcast [Player]");
                sender.sendMessage("    Disables duty mode changes broadcasting");
                sender.sendMessage(ChatColor.YELLOW + "/duties updateconfig");
                sender.sendMessage("    Updates the config file to include all config options");
            }
            sender.sendMessage(ChatColor.BLUE + "-----------------------------------------------------");
            return true;
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("duties.reload") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.reload"))) {
                this.TellSender(sender, updates.MissingPermission, false);
                return true;
            }
            Duties.GetInstance().getLogger().info("The 'KeepStateOffline' setting requires server restart to be changed.");
            if (!new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "config.yml").exists()) {
                final Configuration configuration = new Configuration();
                configuration.getClass();
                final Configuration.Main config = configuration.new Main(new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "config.yml"));
                config.Reload();
            }
            if (!new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "messages.yml").exists()) {
                final Configuration configuration2 = new Configuration();
                configuration2.getClass();
                final Configuration.Messages messages = configuration2.new Messages(new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "messages.yml"));
                messages.Reload();
            }
            Duties.GetInstance().reloadConfig();
            Duties.Config.Reload();
            Duties.Messages.Reload();
            Duties.VaultAdapter = new VaultAdapter();
            Bukkit.getServer().getPluginManager().callEvent((Event)new ReloadedEvent());
            if (sender instanceof Player) {
                this.TellSender(sender, "Configuration reloaded!");
            }
            Duties.GetInstance().getLogger().info("Configuration reloaded!");
            return true;
        }
        else if (args[0].equalsIgnoreCase("disable")) {
            if (!sender.hasPermission("duties.disable") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.disable"))) {
                this.TellSender(sender, updates.MissingPermission, false);
                return true;
            }
            Duties.GetInstance().pluginManager.disablePlugin((Plugin)Duties.GetInstance());
            return true;
        }
        else {
            if (!args[0].equalsIgnoreCase("updateconfig")) {
                this.TellSender(sender, updates.CommandExtensionNotFound, false);
                return true;
            }
            if (!sender.hasPermission("duties.updateconfig") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.updateconfig"))) {
                this.TellSender(sender, updates.MissingPermission, false);
                return true;
            }
            Duties.Config.Reload();
            Duties.Messages.Reload();
            LinkedHashMap<String, Object> configDefaults = Duties.Config.initializeConfigDefaults();
            for (final String key : configDefaults.keySet()) {
                if (!Duties.Config.GetHandle().contains(key)) {
                    Duties.GetInstance().getLogger().info("Adding: '" + key + "' to 'config.yml'");
                    Duties.Config.GetHandle().set(key, configDefaults.get(key));
                }
            }
            configDefaults.clear();
            configDefaults = Duties.Messages.initializeConfigDefaults();
            for (final String key : configDefaults.keySet()) {
                if (!Duties.Messages.GetHandle().contains(key)) {
                    Duties.GetInstance().getLogger().info("Adding: '" + key + "' to 'messages.yml'");
                    Duties.Messages.GetHandle().set(key, configDefaults.get(key));
                }
            }
            try {
                Duties.Config.GetHandle().save(new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "config.yml"));
                Duties.Messages.GetHandle().save(new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "messages.yml"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Duties.GetInstance().getLogger().info("Configuration reloaded & updated!");
            return true;
        }
    }
    
    private void TellSender(final CommandSender sender, final updates update, final boolean success) {
        if (update == updates.MissingPermission) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.MissingPermission"));
        }
        if (update == updates.CommandExtensionNotFound) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.CommandExtensionNotFound"));
        }
    }
    
    private void TellSender(final CommandSender sender, final String message) {
        sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + message);
    }
    
    public enum updates
    {
        MissingPermission("MissingPermission", 0), 
        CommandExtensionNotFound("CommandExtensionNotFound", 1);
        
        private updates(final String s, final int n) {
        }
    }
}
