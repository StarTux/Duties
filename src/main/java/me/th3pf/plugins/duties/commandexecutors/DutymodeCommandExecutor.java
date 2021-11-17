// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.commandexecutors;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import java.util.UUID;
import me.th3pf.plugins.duties.ModeSwitcher;
import org.bukkit.entity.Player;
import me.th3pf.plugins.duties.Duties;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class DutymodeCommandExecutor implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 0) {
            try {
                if (!sender.hasPermission("duties.self.toggle") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.self.toggle"))) {
                    this.TellSender(sender, updates.MissingPermission, true);
                    return true;
                }
                if (sender instanceof Player) {
                    final ModeSwitcher actions = new ModeSwitcher((Player)sender);
                    if (!Duties.Memories.containsKey(((Player)sender).getUniqueId())) {
                        this.TellSender(sender, updates.Enable, actions.EnableDutyMode());
                    }
                    else {
                        this.TellSender(sender, updates.Disable, actions.DisableDutyMode());
                    }
                    return true;
                }
                Duties.GetInstance().getLogger().info("This command is only avaible for in-game player.");
                return true;
            }
            catch (Exception ex) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + "An error occured while enabling duty mode.");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("toggle")) {
            try {
                if (args.length >= 2) {
                    if (!sender.hasPermission("duties.others.toggle") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.others.toggle"))) {
                        this.TellSender(sender, updates.MissingPermission, true);
                        return true;
                    }
                    try {
                        if (!Duties.GetInstance().getServer().getPlayer(args[1]).isOnline()) {
                            this.TellSender(sender, updates.PlayerIsNotOnline, false);
                            return true;
                        }
                    }
                    catch (Exception exception) {
                        this.TellSender(sender, updates.PlayerIsNotOnline, false);
                        return true;
                    }
                    final ModeSwitcher actions = new ModeSwitcher(Duties.GetInstance().getServer().getPlayer(args[1]).getPlayer());
                    if (!Duties.Memories.containsKey(Duties.GetInstance().getServer().getPlayer(args[1]).getUniqueId())) {
                        this.TellSender(sender, updates.EnableOfOther, actions.EnableDutyMode());
                    }
                    else {
                        this.TellSender(sender, updates.DisableOfOther, actions.DisableDutyMode());
                    }
                    return true;
                }
                else {
                    if (!(sender instanceof Player)) {
                        Duties.GetInstance().getLogger().info("This command is only avaible for in-game player.");
                        return true;
                    }
                    if (!sender.hasPermission("duties.self.toggle") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.self.toggle"))) {
                        this.TellSender(sender, updates.MissingPermission, true);
                        return true;
                    }
                    final ModeSwitcher actions = new ModeSwitcher((Player)sender);
                    if (!Duties.Memories.containsKey(((Player)sender).getUniqueId())) {
                        this.TellSender(sender, updates.Enable, actions.EnableDutyMode());
                    }
                    else {
                        this.TellSender(sender, updates.Disable, actions.DisableDutyMode());
                    }
                    return true;
                }
            }
            catch (Exception ex) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + "An error occured while enabling duty mode.");
                return true;
            }
        }
        Label_0904: {
            if (!args[0].equalsIgnoreCase("enable")) {
                if (!args[0].equalsIgnoreCase("on")) {
                    break Label_0904;
                }
            }
            try {
                if (args.length >= 2) {
                    if (!sender.hasPermission("duties.others.enable") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.others.enable"))) {
                        this.TellSender(sender, updates.MissingPermission, true);
                        return true;
                    }
                    try {
                        if (!Duties.GetInstance().getServer().getPlayer(args[1]).isOnline()) {
                            this.TellSender(sender, updates.PlayerIsNotOnline, false);
                            return true;
                        }
                    }
                    catch (Exception exception) {
                        this.TellSender(sender, updates.PlayerIsNotOnline, false);
                        return true;
                    }
                    if (Duties.Memories.containsKey(Duties.GetInstance().getServer().getPlayer(args[1]).getUniqueId())) {
                        this.TellSender(sender, updates.AlreadyOn, false);
                        return true;
                    }
                    final ModeSwitcher actions = new ModeSwitcher(Duties.GetInstance().getServer().getPlayer(args[1]).getPlayer());
                    this.TellSender(sender, updates.EnableOfOther, actions.EnableDutyMode());
                    return true;
                }
                else {
                    if (!sender.hasPermission("duties.self.enable") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.self.enable"))) {
                        this.TellSender(sender, updates.MissingPermission, true);
                        return true;
                    }
                    if (!Duties.Memories.containsKey(((Player)sender).getUniqueId())) {
                        this.TellSender(sender, updates.AlreadyOn, false);
                        return true;
                    }
                    if (sender instanceof Player) {
                        final ModeSwitcher actions = new ModeSwitcher((Player)sender);
                        this.TellSender(sender, updates.Enable, actions.EnableDutyMode());
                        return true;
                    }
                    this.TellSender(sender, updates.IngamePlayersOnly, false);
                    return true;
                }
            }
            catch (Exception ex) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + "An error occured while enabling duty mode.");
                return true;
            }
        }
        Label_1272: {
            if (!args[0].equalsIgnoreCase("disable")) {
                if (!args[0].equalsIgnoreCase("off")) {
                    break Label_1272;
                }
            }
            try {
                if (args.length >= 2) {
                    if (!sender.hasPermission("duties.others.disable") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.others.disable"))) {
                        this.TellSender(sender, updates.MissingPermission, true);
                        return true;
                    }
                    try {
                        if (!Duties.GetInstance().getServer().getPlayer(args[1]).isOnline()) {
                            this.TellSender(sender, updates.PlayerIsNotOnline, false);
                            return true;
                        }
                    }
                    catch (Exception exception) {
                        this.TellSender(sender, updates.PlayerIsNotOnline, false);
                        return true;
                    }
                    if (!Duties.Memories.containsKey(Duties.GetInstance().getServer().getPlayer(args[1]).getUniqueId())) {
                        this.TellSender(sender, updates.AlreadyOff, false);
                        return true;
                    }
                    final ModeSwitcher actions = new ModeSwitcher(Duties.GetInstance().getServer().getPlayer(args[1]).getPlayer());
                    this.TellSender(sender, updates.DisableOfOther, actions.DisableDutyMode());
                    return true;
                }
                else {
                    if (!sender.hasPermission("duties.self.disable") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.self.disable"))) {
                        this.TellSender(sender, updates.MissingPermission, true);
                        return true;
                    }
                    if (!Duties.Memories.containsKey(((Player)sender).getUniqueId())) {
                        this.TellSender(sender, updates.AlreadyOff, false);
                        return true;
                    }
                    if (sender instanceof Player) {
                        final ModeSwitcher actions = new ModeSwitcher((Player)sender);
                        this.TellSender(sender, updates.Disable, actions.DisableDutyMode());
                        return true;
                    }
                    Duties.GetInstance().getLogger().info("This command is only avaible for in-game player.");
                    return true;
                }
            }
            catch (Exception ex) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + "An error occured while disabling duty mode.");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("who")) {
            if (!sender.hasPermission("duties.list") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.list"))) {
                this.TellSender(sender, updates.MissingPermission, true);
                return true;
            }
            String players = "";
            for (final UUID playerID : Duties.Memories.keySet()) {
                if (!Duties.GetInstance().getServer().getPlayer(playerID).hasPermission("duties.belisted")) {
                    if (!Duties.Config.GetBoolean("Vault.Permissions")) {
                        continue;
                    }
                    if (!Duties.VaultAdapter.permission.has(Duties.GetInstance().getServer().getPlayer(playerID), "duties.belisted")) {
                        continue;
                    }
                }
                String formattedName = Duties.GetInstance().getServer().getPlayer(playerID).getName();
                if (Duties.Config.GetBoolean("Vault.NameFormatting") && Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                    formattedName = String.valueOf(Duties.VaultAdapter.chat.getPlayerPrefix(Duties.GetInstance().getServer().getPlayer(playerID))) + formattedName + Duties.VaultAdapter.chat.getPlayerSuffix(Duties.GetInstance().getServer().getPlayer(playerID));
                }
                players = String.valueOf(players) + formattedName + ChatColor.WHITE + ", ";
            }
            if (players.length() <= 0) {
                this.TellSender(sender, Duties.Messages.GetString("Client.NoStaffOnDuty"));
            }
            else {
                players = players.substring(0, players.length() - 2);
                this.TellSender(sender, String.valueOf(Duties.Messages.GetString("Client.List")) + players);
            }
            return true;
        }
        else if (args[0].equalsIgnoreCase("listall") || args[0].equalsIgnoreCase("whoall")) {
            if (!sender.hasPermission("duties.listall") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.listall"))) {
                this.TellSender(sender, updates.MissingPermission, true);
                return true;
            }
            String players = "";
            for (final UUID playerID : Duties.Memories.keySet()) {
                String formattedName = Duties.GetInstance().getServer().getPlayer(playerID).getName();
                if (Duties.Config.GetBoolean("Vault.NameFormatting") && Duties.GetInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                    formattedName = String.valueOf(Duties.VaultAdapter.chat.getPlayerPrefix(Duties.GetInstance().getServer().getPlayer(playerID))) + formattedName + Duties.VaultAdapter.chat.getPlayerSuffix(Duties.GetInstance().getServer().getPlayer(playerID));
                }
                players = String.valueOf(players) + formattedName + ChatColor.WHITE + ", ";
            }
            if (players.length() <= 0) {
                this.TellSender(sender, Duties.Messages.GetString("Client.NoPlayersOnDuty"));
            }
            else {
                players = players.substring(0, players.length() - 2);
                this.TellSender(sender, String.valueOf(Duties.Messages.GetString("Client.ListAll")) + players);
            }
            return true;
        }
        else if (args[0].equalsIgnoreCase("showbroadcast") || args[0].equalsIgnoreCase("showb")) {
            if (args.length >= 2) {
                if (!sender.hasPermission("duties.others.setbroadcasts.shown") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.others.setbroadcasts.shown"))) {
                    this.TellSender(sender, updates.MissingPermission, true);
                    return true;
                }
                try {
                    if (!Duties.GetInstance().getServer().getPlayer(args[1]).isOnline()) {
                        this.TellSender(sender, updates.PlayerIsNotOnline, false);
                        return true;
                    }
                }
                catch (Exception exception) {
                    this.TellSender(sender, updates.PlayerIsNotOnline, false);
                    return true;
                }
                if (!Duties.Hidden.contains(Duties.GetInstance().getServer().getPlayer(args[1]).getPlayer())) {
                    this.TellSender(sender, updates.BroadcastsAlreadyShownForPlayer, false);
                    return true;
                }
                Duties.Hidden.remove(Duties.GetInstance().getServer().getPlayer(args[1]).getPlayer());
                this.TellSender(sender, updates.BroadcastsShownForPlayer, true);
                return true;
            }
            else {
                if (!sender.hasPermission("duties.self.setbroadcasts.shown") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.self.setbroadcasts.shown"))) {
                    this.TellSender(sender, updates.MissingPermission, true);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    Duties.GetInstance().getLogger().info("This command is only avaible for in-game player.");
                    return true;
                }
                if (!Duties.Hidden.contains(sender)) {
                    this.TellSender(sender, updates.BroadcastsAlreadyShown, false);
                    return true;
                }
                Duties.Hidden.remove(sender);
                this.TellSender(sender, updates.BroadcastsShown, true);
                return true;
            }
        }
        else if (args[0].equalsIgnoreCase("hidebroadcast") || args[0].equalsIgnoreCase("hideb")) {
            if (args.length >= 2) {
                if (!sender.hasPermission("duties.others.setbroadcasts.hidden") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.others.setbroadcasts.hidden"))) {
                    this.TellSender(sender, updates.MissingPermission, true);
                    return true;
                }
                try {
                    if (!Duties.GetInstance().getServer().getPlayer(args[1]).isOnline()) {
                        this.TellSender(sender, updates.PlayerIsNotOnline, false);
                        return true;
                    }
                }
                catch (Exception exception) {
                    this.TellSender(sender, updates.PlayerIsNotOnline, false);
                    return true;
                }
                if (Duties.Hidden.contains(Duties.GetInstance().getServer().getPlayer(args[1]).getPlayer())) {
                    this.TellSender(sender, updates.BroadcastsAlreadyHiddenForPlayer, false);
                    return true;
                }
                Duties.Hidden.add(Duties.GetInstance().getServer().getPlayer(args[1]).getPlayer());
                this.TellSender(sender, updates.BroadcastsHiddenForPlayer, true);
                return true;
            }
            else {
                if (!sender.hasPermission("duties.self.setbroadcasts.hidden") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.self.setbroadcasts.hidden"))) {
                    this.TellSender(sender, updates.MissingPermission, true);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    Duties.GetInstance().getLogger().info("This command is only avaible for in-game player.");
                    return true;
                }
                if (Duties.Hidden.contains(sender)) {
                    this.TellSender(sender, updates.BroadcastsAlreadyHidden, false);
                    return true;
                }
                Duties.Hidden.add((Player)sender);
                this.TellSender(sender, updates.BroadcastsHidden, true);
                return true;
            }
        }
        else {
            if (!args[0].equalsIgnoreCase("purge")) {
                this.TellSender(sender, updates.CommandExtensionNotFound, false);
                return true;
            }
            if (!sender.hasPermission("duties.purge") && (!Duties.Config.GetBoolean("Vault.Permissions") || !Duties.VaultAdapter.permission.has(sender, "duties.purge"))) {
                this.TellSender(sender, updates.MissingPermission, true);
                return true;
            }
            final ArrayList<UUID> keySet = new ArrayList<UUID>();
            keySet.addAll(Duties.Memories.keySet());
            for (final UUID playerID : keySet) {
                if (!Duties.GetInstance().getServer().getPlayer(playerID).isOnline()) {
                    Duties.GetInstance().getLogger().info("Player " + Duties.GetInstance().getServer().getPlayer(playerID).getName() + " is offline and can therefore not be put off duty mode.");
                }
                else {
                    if (new ModeSwitcher(Duties.GetInstance().getServer().getPlayer(playerID)).DisableDutyMode()) {
                        continue;
                    }
                    Duties.GetInstance().getLogger().info("Couldn't disable duty mode for " + Duties.GetInstance().getServer().getPlayer(playerID).getName() + ".");
                }
            }
            this.TellSender(sender, updates.Purged, true);
            return true;
        }
    }
    
    private void TellSender(final CommandSender sender, final updates update, final boolean success) {
        if (update == updates.Enable) {
            if (!(sender instanceof Player)) {
                return;
            }
            if (success) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Enabled").replaceAll("$", "").replaceAll("&", ""));
            }
            else {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Fail.Enable").replaceAll("$", "").replaceAll("&", ""));
            }
        }
        else if (update == updates.Disable) {
            if (!(sender instanceof Player)) {
                return;
            }
            if (success) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Disabled").replaceAll("$", "").replaceAll("&", ""));
            }
            else {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Fail.Disable").replaceAll("$", "").replaceAll("&", ""));
            }
        }
        if (update == updates.EnableOfOther) {
            if (!(sender instanceof Player)) {
                return;
            }
            if (success) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.EnabledForOtherPlayer").replaceAll("$", "").replaceAll("&", ""));
            }
            else {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Fail.EnableForOtherPlayer").replaceAll("$", "").replaceAll("&", ""));
            }
        }
        if (update == updates.DisableOfOther) {
            if (!(sender instanceof Player)) {
                return;
            }
            if (success) {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.DisabledForOtherPlayer").replaceAll("$", "").replaceAll("&", ""));
            }
            else {
                sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Fail.DisableForOtherPlayer").replaceAll("$", "").replaceAll("&", ""));
            }
        }
        else if (update == updates.PlayerIsNotOnline) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.PlayerNotOnline").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.Purged) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.Purged").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.MissingPermission) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.MissingPermission").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.CommandExtensionNotFound) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.CommandExtensionNotFound").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.AlreadyOn) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.AlreadyOn").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.AlreadyOff) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.AlreadyOff").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.IngamePlayersOnly) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.IngamePlayersOnly").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsShown) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsShown").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsHidden) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsHidden").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsShownForPlayer) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsShownForPlayer").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsHiddenForPlayer) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsHiddenForPlayer").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsAlreadyShown) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsAlreadyShown").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsAlreadyHidden) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsAlreadyHidden").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsAlreadyShownForPlayer) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsAlreadyShownForPlayer").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.BroadcastsAlreadyHiddenForPlayer) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.BroadcastsAlreadyHiddenForPlayer").replaceAll("$", "").replaceAll("&", ""));
        }
        else if (update == updates.Exception) {
            sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + Duties.Messages.GetString("Client.ErrorOccured").replaceAll("$", "").replaceAll("&", ""));
        }
    }
    
    public void TellSender(final CommandSender sender, final String message) {
        sender.sendMessage(String.valueOf(Duties.Messages.GetString("Client.Tag")) + message.replaceAll("$", "").replaceAll("&", ""));
    }
    
    private enum updates
    {
        Enable("Enable", 0), 
        Disable("Disable", 1), 
        EnableOfOther("EnableOfOther", 2), 
        DisableOfOther("DisableOfOther", 3), 
        PlayerIsNotOnline("PlayerIsNotOnline", 4), 
        Purged("Purged", 5), 
        MissingPermission("MissingPermission", 6), 
        CommandExtensionNotFound("CommandExtensionNotFound", 7), 
        AlreadyOn("AlreadyOn", 8), 
        AlreadyOff("AlreadyOff", 9), 
        IngamePlayersOnly("IngamePlayersOnly", 10), 
        BroadcastsShown("BroadcastsShown", 11), 
        BroadcastsHidden("BroadcastsHidden", 12), 
        BroadcastsShownForPlayer("BroadcastsShownForPlayer", 13), 
        BroadcastsHiddenForPlayer("BroadcastsHiddenForPlayer", 14), 
        BroadcastsAlreadyShown("BroadcastsAlreadyShown", 15), 
        BroadcastsAlreadyHidden("BroadcastsAlreadyHidden", 16), 
        BroadcastsAlreadyShownForPlayer("BroadcastsAlreadyShownForPlayer", 17), 
        BroadcastsAlreadyHiddenForPlayer("BroadcastsAlreadyHiddenForPlayer", 18), 
        Exception("Exception", 19);
        
        private updates(final String s, final int n) {
        }
    }
}
