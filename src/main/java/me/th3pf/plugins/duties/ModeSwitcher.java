// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import me.th3pf.plugins.duties.events.DutyModeDisabledEvent;
import me.th3pf.plugins.duties.events.DutyModeEnabledEvent;
import me.th3pf.plugins.duties.events.DutyModePreDisabledEvent;
import me.th3pf.plugins.duties.events.DutyModePreEnabledEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

public class ModeSwitcher
{
    private Player player;
    
    public ModeSwitcher(final Player player) {
        this.player = player;
    }
    
    public boolean EnableDutyMode() {
        try {
            final DutyModePreEnabledEvent event = new DutyModePreEnabledEvent(this.player);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.getCancelled()) {
                return false;
            }
            for (final String plugin : Duties.Config.GetStringList("Actions.Requirements.Dependencies")) {
                try {
                    if (Duties.GetInstance().pluginManager.getPlugin(plugin).isEnabled()) {
                        continue;
                    }
                    Duties.GetInstance().pluginManager.enablePlugin(Duties.GetInstance().pluginManager.getPlugin(plugin));
                }
                catch (Exception ex) {}
            }
            boolean fail = false;
            final Modules modules2 = new Modules();
            modules2.getClass();
            final Modules.onEnable modules = modules2.new onEnable();
            for (final String module : Duties.Config.GetStringList("Actions.onEnable.Order")) {
                if (module.equalsIgnoreCase("MemoryImport")) {
                    if (modules.MemoryImport()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("TemporaryPermissions")) {
                    if (modules.TemporaryPermissions()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("TemporaryGroups")) {
                    if (modules.TemporaryGroups()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("Cleanups")) {
                    if (modules.Cleanups()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("CommandsByConsole")) {
                    if (modules.CommandsByConsole()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("Commands")) {
                    if (modules.Commands()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("Messages")) {
                    if (modules.Messages()) {
                        continue;
                    }
                    fail = true;
                }
                else {
                    if (!module.equalsIgnoreCase("Broadcast") || modules.Broadcast()) {
                        continue;
                    }
                    fail = true;
                }
            }
            if (fail) {
                return false;
            }
            Duties.GetInstance().getLogger().info(Duties.Messages.GetString("Server.Enabled").replaceAll("%PLAYER_NAME%", this.player.getName()));
            Bukkit.getServer().getPluginManager().callEvent((Event)new DutyModeEnabledEvent(this.player));
            return true;
        }
        catch (Exception exception) {
            Duties.GetInstance().getLogger().info(Duties.Messages.GetString("Server.Fail.Enable").replaceAll("%PLAYER_NAME%", this.player.getName().replaceAll("%REASON%", exception.getMessage())));
            return false;
        }
    }
    
    public boolean DisableDutyMode() {
        try {
            final DutyModePreDisabledEvent event = new DutyModePreDisabledEvent(this.player);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.getCancelled()) {
                return false;
            }
            for (final String plugin : Duties.Config.GetStringList("Actions.Requirements.Dependencies")) {
                try {
                    if (Duties.GetInstance().pluginManager.getPlugin(plugin).isEnabled()) {
                        continue;
                    }
                    Duties.GetInstance().pluginManager.enablePlugin(Duties.GetInstance().pluginManager.getPlugin(plugin));
                }
                catch (Exception ex) {}
            }
            boolean fail = false;
            final Modules modules2 = new Modules();
            modules2.getClass();
            final Modules.onDisable modules = modules2.new onDisable();
            for (final String module : Duties.Config.GetStringList("Actions.onDisable.Order")) {
                if (module.equalsIgnoreCase("MemoryExport")) {
                    if (modules.MemoryExport()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("CommandsByConsole")) {
                    if (modules.CommandsByConsole()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("Commands")) {
                    if (modules.Commands()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("TemporaryGroups")) {
                    if (modules.TemporaryGroups()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("TemporaryPermissions")) {
                    if (modules.TemporaryPermissions()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("DataRemoval")) {
                    if (modules.DataRemoval()) {
                        continue;
                    }
                    fail = true;
                }
                else if (module.equalsIgnoreCase("Messages")) {
                    if (modules.Messages()) {
                        continue;
                    }
                    fail = true;
                }
                else {
                    if (!module.equalsIgnoreCase("Broadcast") || modules.Broadcast()) {
                        continue;
                    }
                    fail = true;
                }
            }
            if (fail) {
                return false;
            }
            Duties.GetInstance().getLogger().info(Duties.Messages.GetString("Server.Disabled").replaceAll("%PLAYER_NAME%", this.player.getName()));
            Bukkit.getServer().getPluginManager().callEvent((Event)new DutyModeDisabledEvent(this.player));
            return true;
        }
        catch (Exception exception) {
            Duties.GetInstance().getLogger().info(Duties.Messages.GetString("Server.Fail.Disable").replaceAll("%PLAYER_NAME%", this.player.getName().replaceAll("%REASON%", exception.getMessage())));
            return false;
        }
    }
    
    public class Modules
    {
        public class onEnable
        {
            public boolean Broadcast() {
                try {
                    if (Duties.Config.GetBoolean("Broadcast-duty-changes") && ModeSwitcher.this.player.hasPermission("duties.broadcast") && !Duties.Hidden.contains(ModeSwitcher.this.player)) {
                        String FormattedName = ModeSwitcher.this.player.getName();
                        if (Duties.Config.GetBoolean("Vault.NameFormatting") && Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                            FormattedName = String.valueOf(Duties.VaultAdapter.chat.getPlayerPrefix(ModeSwitcher.this.player)) + FormattedName + Duties.VaultAdapter.chat.getPlayerSuffix(ModeSwitcher.this.player);
                        }
                        List<Player> onlinePlayers;
                        for (int length = (onlinePlayers = new ArrayList<>(Duties.GetInstance().getServer().getOnlinePlayers())).size(), i = 0; i < length; ++i) {
                            final Player player = onlinePlayers.get(i);
                            if (player != ModeSwitcher.this.player && player.hasPermission("duties.getbroadcasts")) {
                                player.sendMessage(Duties.Messages.GetString("Client.Broadcast.Enabled").replaceAll("%PLAYER_NAME%", FormattedName));
                            }
                        }
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while broadcasting duty mode change: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean Messages() {
                if (Duties.Config.GetStringList("Actions.onEnable.Messages") == null) {
                    return true;
                }
                try {
                    for (final String message : Duties.Config.GetStringList("Actions.onEnable.Messages")) {
                        final String parsedMessage = ("/".equals(message.charAt(0)) ? message.substring(1) : message).replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()).replaceAll("%PLAYER_GAMEMODE%", ModeSwitcher.this.player.getGameMode().toString());
                        ModeSwitcher.this.player.sendMessage(parsedMessage);
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while chatting onEnable messages: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean Commands() {
                if (Duties.Config.GetStringList("Actions.onEnable.Commands") == null) {
                    return true;
                }
                try {
                    for (final String command : Duties.Config.GetStringList("Actions.onEnable.Commands")) {
                        final String parsedCommand = ("/".equals(command.charAt(0)) ? command.substring(1) : command).replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()).replaceAll("%PLAYER_GAMEMODE%", ModeSwitcher.this.player.getGameMode().toString());
                        ModeSwitcher.this.player.performCommand(parsedCommand);
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while performing onEnable commands: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean CommandsByConsole() {
                if (Duties.Config.GetStringList("Actions.onEnable.CommandsByConsole") == null) {
                    return true;
                }
                try {
                    for (final String command : Duties.Config.GetStringList("Actions.onEnable.CommandsByConsole")) {
                        final String parsedCommand = ("/".equals(command.charAt(0)) ? command.substring(1) : command).replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()).replaceAll("%PLAYER_GAMEMODE%", ModeSwitcher.this.player.getGameMode().toString());
                        Duties.GetInstance().getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), parsedCommand);
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while performing onEnable console commands: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean Cleanups() {
                if (Duties.Config.GetStringList("Actions.onEnable.Cleanups") == null) {
                    return true;
                }
                try {
                    for (final String task : Duties.Config.GetStringList("Actions.onEnable.Cleanups")) {
                        if (task.equalsIgnoreCase("Location")) {
                            ModeSwitcher.this.player.teleport(ModeSwitcher.this.player.getWorld().getSpawnLocation());
                        }
                        else if (task.equalsIgnoreCase("Vehicle")) {
                            if (!ModeSwitcher.this.player.isInsideVehicle()) {
                                continue;
                            }
                            ModeSwitcher.this.player.getVehicle().eject();
                        }
                        else if (task.equalsIgnoreCase("Velocity")) {
                            ModeSwitcher.this.player.setVelocity(new Vector(0, 0, 0));
                        }
                        else if (task.equalsIgnoreCase("Inventory")) {
                            ModeSwitcher.this.player.getInventory().clear();
                        }
                        else if (task.equalsIgnoreCase("Armor")) {
                            ModeSwitcher.this.player.getInventory().setHelmet((ItemStack)null);
                            ModeSwitcher.this.player.getInventory().setChestplate((ItemStack)null);
                            ModeSwitcher.this.player.getInventory().setLeggings((ItemStack)null);
                            ModeSwitcher.this.player.getInventory().setBoots((ItemStack)null);
                        }
                        else if (task.equalsIgnoreCase("Saturation")) {
                            ModeSwitcher.this.player.setSaturation(0.0f);
                        }
                        else if (task.equalsIgnoreCase("Exhaustion")) {
                            ModeSwitcher.this.player.setExhaustion(0.0f);
                        }
                        else if (task.equalsIgnoreCase("FoodLevel")) {
                            ModeSwitcher.this.player.setFoodLevel(20);
                        }
                        else if (task.equalsIgnoreCase("Health")) {
                            ModeSwitcher.this.player.setHealth(20.0);
                        }
                        else if (task.equalsIgnoreCase("Experience")) {
                            ModeSwitcher.this.player.setLevel(0);
                            ModeSwitcher.this.player.setExp(0.0f);
                        }
                        else if (task.equalsIgnoreCase("RemainingAir")) {
                            ModeSwitcher.this.player.setRemainingAir(20);
                        }
                        else if (task.equalsIgnoreCase("FallDistance")) {
                            ModeSwitcher.this.player.setFallDistance(0.0f);
                        }
                        else if (task.equalsIgnoreCase("FireTicks")) {
                            ModeSwitcher.this.player.setFireTicks(0);
                        }
                        else if (task.equalsIgnoreCase("PotionEffects")) {
                            for (final PotionEffect potionEffect : ModeSwitcher.this.player.getActivePotionEffects()) {
                                ModeSwitcher.this.player.removePotionEffect(potionEffect.getType());
                            }
                        }
                        else if (task.equalsIgnoreCase("BedSpawnLocation")) {
                            ModeSwitcher.this.player.setBedSpawnLocation(ModeSwitcher.this.player.getWorld().getSpawnLocation());
                        }
                        else {
                            if (!task.equalsIgnoreCase("TicksLived")) {
                                continue;
                            }
                            ModeSwitcher.this.player.setTicksLived(1);
                        }
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while reading cleanup tasks: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean TemporaryGroups() {
                if (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                    return true;
                }
                try {
                    if (Duties.Config.GetStringList("Actions.TemporaryGroups") == null) {
                        return true;
                    }
                    for (final String group : Duties.Config.GetStringList("Actions.TemporaryGroups")) {
                        try {
                            if (Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("bPermissions")) {
                                for (final World world : Duties.GetInstance().getServer().getWorlds()) {
                                    Duties.VaultAdapter.permission.playerAddGroup(world, ModeSwitcher.this.player.getName(), group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                }
                            }
                            else {
                                if (Duties.VaultAdapter.permission.playerInGroup((String)null, ModeSwitcher.this.player.getName(), group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()))) {
                                    continue;
                                }
                                Duties.VaultAdapter.permission.playerAddGroup((String)null, ModeSwitcher.this.player.getName(), group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                            }
                        }
                        catch (Exception exception) {
                            Duties.GetInstance().getLogger().info("Failed while enabling temporary groups: Not a valid group: " + group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                            Duties.GetInstance().getLogger().info("Error occured: " + exception.getMessage() + ". Ignoring it!");
                        }
                    }
                    return true;
                }
                catch (Exception exception2) {
                    Duties.GetInstance().getLogger().info("Failed while enabling temporary groups: " + exception2.getMessage());
                    return false;
                }
            }
            
            public boolean TemporaryPermissions() {
                if (Duties.Config.GetStringList("Actions.TemporaryPermissions") == null) {
                    return true;
                }
                try {
                    Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).TemporaryPermissions = new ArrayList<PermissionAttachment>();
                    for (final String node : Duties.Config.GetStringList("Actions.TemporaryPermissions")) {
                        try {
                            if (Duties.Config.GetBoolean("Vault.Permissions") && Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                                if (node.contains(": ")) {
                                    if (node.split(": ")[1].equalsIgnoreCase("true")) {
                                        Duties.VaultAdapter.permission.playerAddTransient(ModeSwitcher.this.player, node.split(": ")[0].replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                    }
                                    else if (node.split(": ")[1].equalsIgnoreCase("false")) {
                                        Duties.VaultAdapter.permission.playerRemoveTransient(ModeSwitcher.this.player, node.split(": ")[0].replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                    }
                                    else {
                                        Duties.GetInstance().getLogger().info("Failed while enabling temporary permissions: '" + node.split(": ")[1] + "' is not a valid value for node: " + node.split(": ")[0] + ". Ignoring it!");
                                    }
                                }
                                else {
                                    Duties.VaultAdapter.permission.playerAddTransient(ModeSwitcher.this.player, node.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                }
                            }
                            else {
                                if (!ModeSwitcher.this.player.isOnline()) {
                                    return true;
                                }
                                Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).TemporaryPermissions.add(ModeSwitcher.this.player.addAttachment((Plugin)Duties.GetInstance(), node.split(": ")[0].replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()), Boolean.parseBoolean(node.split(": ")[1])));
                            }
                        }
                        catch (Exception exception) {
                            Duties.GetInstance().getLogger().info("Failed while enabling temporary permissions: Not a valid permission node: '" + node.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                            Duties.GetInstance().getLogger().info("Error occured: " + exception.getMessage() + ". Ignoring it!");
                        }
                    }
                    return true;
                }
                catch (Exception exception2) {
                    Duties.GetInstance().getLogger().info("Failed while enabling temporary permissions: " + exception2.getMessage());
                    return false;
                }
            }
            
            public boolean MemoryImport() {
                try {
                    Duties.Memories.put(ModeSwitcher.this.player.getUniqueId(), new Memory(ModeSwitcher.this.player, 0));
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while importing player data in to memory: " + exception.getMessage());
                    return false;
                }
            }
        }
        
        public class onDisable
        {
            public boolean Broadcast() {
                try {
                    if (Duties.Config.GetBoolean("Broadcast-duty-changes") && ModeSwitcher.this.player.hasPermission("duties.broadcast") && !Duties.Hidden.contains(ModeSwitcher.this.player)) {
                        String FormattedName = ModeSwitcher.this.player.getName();
                        if (Duties.Config.GetBoolean("Vault.NameFormatting") && Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                            FormattedName = String.valueOf(Duties.VaultAdapter.chat.getPlayerPrefix(ModeSwitcher.this.player)) + FormattedName + Duties.VaultAdapter.chat.getPlayerSuffix(ModeSwitcher.this.player);
                        }
                        List<Player> onlinePlayers;
                        for (int length = (onlinePlayers = new ArrayList<>(Duties.GetInstance().getServer().getOnlinePlayers())).size(), i = 0; i < length; ++i) {
                            final Player player = onlinePlayers.get(i);
                            if (player != ModeSwitcher.this.player && player.hasPermission("duties.getbroadcasts")) {
                                player.sendMessage(Duties.Messages.GetString("Client.Broadcast.Disabled").replaceAll("%PLAYER_NAME%", FormattedName));
                            }
                        }
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while broadcasting duty mode change: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean Messages() {
                if (Duties.Config.GetStringList("Actions.onDisable.Messages") == null) {
                    return true;
                }
                try {
                    for (final String message : Duties.Config.GetStringList("Actions.onDisable.Messages")) {
                        final String parsedMessage = ("/".equals(message.charAt(0)) ? message.substring(1) : message).replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()).replaceAll("%PLAYER_GAMEMODE%", ModeSwitcher.this.player.getGameMode().toString());
                        ModeSwitcher.this.player.sendMessage(parsedMessage);
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while chatting onDisable messages: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean DataRemoval() {
                try {
                    Duties.Memories.remove(ModeSwitcher.this.player.getUniqueId());
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while removing player data from memory: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean TemporaryGroups() {
                if (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                    return true;
                }
                try {
                    if (Duties.Config.GetStringList("Actions.TemporaryGroups") == null) {
                        return true;
                    }
                    for (final String group : Duties.Config.GetStringList("Actions.TemporaryGroups")) {
                        try {
                            if (Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("bPermissions")) {
                                for (final World world : Duties.GetInstance().getServer().getWorlds()) {
                                    Duties.VaultAdapter.permission.playerRemoveGroup(world, ModeSwitcher.this.player.getName(), group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                }
                            }
                            else {
                                if (!Duties.VaultAdapter.permission.playerInGroup((String)null, ModeSwitcher.this.player.getName(), group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()))) {
                                    continue;
                                }
                                Duties.VaultAdapter.permission.playerRemoveGroup((String)null, ModeSwitcher.this.player.getName(), group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                            }
                        }
                        catch (Exception exception) {
                            Duties.GetInstance().getLogger().info("Failed while disabling temporary groups: Not a valid group: " + group.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                            Duties.GetInstance().getLogger().info("Error occured: " + exception.getMessage() + ". Ignoring it!");
                        }
                    }
                    return true;
                }
                catch (Exception exception2) {
                    Duties.GetInstance().getLogger().info("Failed while disabling temporary groups: " + exception2.getMessage());
                    return false;
                }
            }
            
            public boolean TemporaryPermissions() {
                if (Duties.Config.GetStringList("Actions.TemporaryPermissions") == null) {
                    return true;
                }
                try {
                    int count = 0;
                    for (final String node : Duties.Config.GetStringList("Actions.TemporaryPermissions")) {
                        try {
                            if (Duties.Config.GetBoolean("Vault.Permissions") && Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                                if (node.contains(": ")) {
                                    if (node.split(": ")[1].equalsIgnoreCase("true")) {
                                        Duties.VaultAdapter.permission.playerRemoveTransient(ModeSwitcher.this.player, node.split(": ")[0].replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                    }
                                    else {
                                        if (!node.split(": ")[1].equalsIgnoreCase("false")) {
                                            Duties.GetInstance().getLogger().info("Failed while disabling temporary permissions: '" + node.split(": ")[1] + "' is not a valid value for node: " + node.split(": ")[0] + ". Ignoring it!");
                                            continue;
                                        }
                                        Duties.VaultAdapter.permission.playerAddTransient(ModeSwitcher.this.player, node.split(": ")[0].replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                    }
                                }
                                else {
                                    Duties.VaultAdapter.permission.playerRemoveTransient(ModeSwitcher.this.player, node.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                                }
                            }
                            else {
                                if (!ModeSwitcher.this.player.isOnline()) {
                                    return true;
                                }
                                ModeSwitcher.this.player.removeAttachment((PermissionAttachment)Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).TemporaryPermissions.get(count));
                            }
                        }
                        catch (Exception exception) {
                            Duties.GetInstance().getLogger().info("Failed while disabling temporary permissions: Not a valid permission node: '" + node.replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()));
                            Duties.GetInstance().getLogger().info("Error occured: " + exception.getMessage() + ". Ignoring it!");
                        }
                        ++count;
                    }
                    return true;
                }
                catch (Exception exception2) {
                    Duties.GetInstance().getLogger().info("Failed while removing temporary permissions: " + exception2.getMessage());
                    return false;
                }
            }
            
            public boolean Commands() {
                if (Duties.Config.GetStringList("Actions.onDisable.Commands") == null) {
                    return true;
                }
                try {
                    for (final String command : Duties.Config.GetStringList("Actions.onDisable.Commands")) {
                        final String parsedCommand = ("/".equals(command.charAt(0)) ? command.substring(1) : command).replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()).replaceAll("%PLAYER_GAMEMODE%", ModeSwitcher.this.player.getGameMode().toString());
                        ModeSwitcher.this.player.performCommand(parsedCommand);
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while performing onDisable commands: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean CommandsByConsole() {
                if (Duties.Config.GetStringList("Actions.onDisable.CommandsByConsole") == null) {
                    return true;
                }
                try {
                    for (final String command : Duties.Config.GetStringList("Actions.onDisable.CommandsByConsole")) {
                        final String parsedCommand = ("/".equals(command.charAt(0)) ? command.substring(1) : command).replaceAll("%PLAYER_NAME%", ModeSwitcher.this.player.getName()).replaceAll("%PLAYER_GAMEMODE%", ModeSwitcher.this.player.getGameMode().toString());
                        Duties.GetInstance().getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), parsedCommand);
                    }
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while performing onDisable console commands: " + exception.getMessage());
                    return false;
                }
            }
            
            public boolean MemoryExport() {
                try {
                    if (ModeSwitcher.this.player.isInsideVehicle()) {
                        ModeSwitcher.this.player.getVehicle().eject();
                    }
                    if (Duties.Config.GetBoolean("PreventTeleportCollision")) {
                        Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Location.setY(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Location.getY() + 1.0);
                    }
                    ModeSwitcher.this.player.teleport(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Location);
                    if (Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Vehicle != null) {
                        Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Vehicle.setPassenger((Entity)ModeSwitcher.this.player);
                    }
                    ModeSwitcher.this.player.setVelocity(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Velocity);
                    ModeSwitcher.this.player.setGameMode(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).GameMode);
                    ModeSwitcher.this.player.getInventory().clear();
                    ModeSwitcher.this.player.getInventory().setContents(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Inventory);
                    ModeSwitcher.this.player.getInventory().setArmorContents(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Armor);
                    ModeSwitcher.this.player.setExhaustion(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Exhaustion);
                    ModeSwitcher.this.player.setSaturation(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Saturation);
                    ModeSwitcher.this.player.setFoodLevel(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).FoodLevel);
                    ModeSwitcher.this.player.setHealth(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Health);
                    ModeSwitcher.this.player.setLevel(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Level);
                    ModeSwitcher.this.player.setExp(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).Experience);
                    ModeSwitcher.this.player.setRemainingAir(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).RemainingAir);
                    ModeSwitcher.this.player.setFallDistance(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).FallDistance);
                    ModeSwitcher.this.player.setFireTicks(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).FireTicks);
                    for (final PotionEffect potionEffect : ModeSwitcher.this.player.getActivePotionEffects()) {
                        ModeSwitcher.this.player.removePotionEffect(potionEffect.getType());
                    }
                    ModeSwitcher.this.player.addPotionEffects((Collection)Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).PotionEffects);
                    if (Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).BedSpawnLocation != null && ModeSwitcher.this.player.getBedSpawnLocation() != null && !Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).BedSpawnLocation.equals((Object)ModeSwitcher.this.player.getBedSpawnLocation())) {
                        ModeSwitcher.this.player.setBedSpawnLocation(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).BedSpawnLocation);
                    }
                    ModeSwitcher.this.player.setTicksLived(Duties.Memories.get(ModeSwitcher.this.player.getUniqueId()).TicksLived);
                    return true;
                }
                catch (Exception exception) {
                    Duties.GetInstance().getLogger().info("Failed while reseting player data from memory: " + exception.getMessage());
                    return false;
                }
            }
        }
    }
}
