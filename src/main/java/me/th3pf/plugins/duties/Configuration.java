// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties;

import java.util.List;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.HashMap;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration
{
    public class Main
    {
        private YamlConfiguration config;
        private HashMap<String, Object> configDefaults;
        
        public LinkedHashMap<String, Object> initializeConfigDefaults() {
            final LinkedHashMap<String, Object> output = new LinkedHashMap<String, Object>();
            output.put("Enabled", true);
            output.put("KeepStateOffline", false);
            output.put("Actions.onEnable.Order", Arrays.asList("MemoryImport", "TemporaryGroups", "TemporaryPermissions", "Cleanups", "CommandsByConsole", "Commands", "Messages", "Broadcast"));
            output.put("Actions.TemporaryPermissions", Arrays.asList(new String[0]));
            output.put("Actions.TemporaryGroups", Arrays.asList(new String[0]));
            output.put("Actions.onEnable.Cleanups", Arrays.asList("Vehicle", "Velocity", "Inventory", "Armor", "Exhaustion", "Saturation", "FoodLevel", "Health", "Experience", "RemaingAir", "FallDistance", "FireTicks", "PotionEffects", "TicksLived"));
            output.put("Actions.onEnable.Messages", new ArrayList());
            output.put("Actions.onEnable.Commands", new ArrayList());
            output.put("Actions.onEnable.CommandsByConsole", Arrays.asList("gamemode 1 %PLAYER_NAME%"));
            output.put("Actions.onDisable.Order", Arrays.asList("MemoryExport", "CommandsByConsole", "Commands", "TemporaryPermissions", "TemporaryGroups", "DataRemoval", "Messages", "Broadcast"));
            output.put("Actions.onDisable.Messages", new ArrayList());
            output.put("Actions.onDisable.Commands", new ArrayList());
            output.put("Actions.onDisable.CommandsByConsole", new ArrayList());
            output.put("Actions.DisableDeathDrops", true);
            output.put("Actions.DisableKillDrops", false);
            output.put("Actions.DenyDesiredDrops", true);
            output.put("Actions.DenyChestInteracts", true);
            output.put("Actions.RemindPlayers", true);
            output.put("Actions.Requirements.Dependencies", Arrays.asList("Vault"));
            output.put("Actions.NameTagPrefix", "&4!&f");
            output.put("Actions.NameTagSuffix", "&4!&f");
            output.put("Vault.Permissions", true);
            output.put("Vault.NameFormatting", false);
            output.put("Vault.Economy", false);
            output.put("PreventTeleportCollision", false);
            output.put("Broadcast-duty-changes", true);
            output.put("ReminderCooldown", 2400);
            return output;
        }
        
        public Main(final File configFile) {
            this.configDefaults = new LinkedHashMap<String, Object>();
            this.config = new YamlConfiguration();
            this.configDefaults = this.initializeConfigDefaults();
            if (!configFile.exists()) {
                for (final String key : this.configDefaults.keySet()) {
                    this.config.set(key, this.configDefaults.get(key));
                }
                try {
                    this.config.save(configFile);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    this.config.load(configFile);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        
        public void Reload() {
            try {
                this.config.load(new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "config.yml"));
            }
            catch (Exception e) {}
        }
        
        public boolean GetBoolean(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return false;
            }
            try {
                return this.config.getBoolean(key, (boolean)this.configDefaults.get(key));
            }
            catch (Exception exception) {
                return false;
            }
        }
        
        public void SetBoolean(final String key, final Boolean value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public String GetString(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return "";
            }
            try {
                return this.config.getString(key).replaceAll("&", String.valueOf('§'));
            }
            catch (Exception exception) {
                return "";
            }
        }
        
        public void SetString(final String key, final String value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public Integer GetInteger(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return 0;
            }
            try {
                return this.config.getInt(key);
            }
            catch (Exception exception) {
                return 0;
            }
        }
        
        public void SetInteger(final String key, final Integer value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public double GetDouble(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return 0.0;
            }
            try {
                return this.config.getDouble(key);
            }
            catch (Exception exception) {
                return 0.0;
            }
        }
        
        public void SetDouble(final String key, final double value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public List<String> GetStringList(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return null;
            }
            try {
                final List<String> output = new ArrayList<String>();
                for (final String object : this.config.getStringList(key)) {
                    output.add(object.replaceAll("&", String.valueOf('§')));
                }
                return output;
            }
            catch (Exception exception) {
                return null;
            }
        }
        
        public void SetStringList(final String key, final List<String> value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public YamlConfiguration GetHandle() {
            try {
                return this.config;
            }
            catch (Exception exception) {
                return null;
            }
        }
    }
    
    public class Messages
    {
        private YamlConfiguration config;
        private HashMap<String, Object> configDefaults;
        
        public LinkedHashMap<String, Object> initializeConfigDefaults() {
            final LinkedHashMap<String, Object> output = new LinkedHashMap<String, Object>();
            output.put("Client.Tag", "&6[&e&oDuties&6]&f ");
            output.put("Client.Enabled", "&aDutymode enabled! Use /dutymode to disable.");
            output.put("Client.Disabled", "&cDutymode disabled! Use /dutymode to enable.");
            output.put("Client.Fail.Enable", "&4Dutymode activation failed or interrupted!");
            output.put("Client.Fail.Disable", "&4Dutymode deactivation failed or interrupted!");
            output.put("Client.EnabledForOtherPlayer", "&aDutymode enabled for the requested player!");
            output.put("Client.DisabledForOtherPlayer", "&cDutymode disabled for the requested player!");
            output.put("Client.Fail.EnableForOtherPlayer", "&4Dutymode activation failed or interrupted for the requested player!");
            output.put("Client.Fail.DisableForOtherPlayer", "&4Dutymode deactivation failed or interrupted for the requested player!");
            output.put("Client.PlayerNotOnline", "&4The requested player is not online!");
            output.put("Client.Purged", "&cDutymode statuses purged!");
            output.put("Client.MissingPermission", "&4You don't have permissions to do that!");
            output.put("Client.CommandExtensionNotFound", "&4Couldn't find command extension.");
            output.put("Client.Broadcast.Enabled", "%PLAYER_NAME%&a went on duty.");
            output.put("Client.Broadcast.Disabled", "%PLAYER_NAME%&c went off duty.");
            output.put("Client.Reminder.Login", "&9Notice that you joined on duty!");
            output.put("Client.Reminder.ChestOpen", "&9Remember that you have dutymode on!");
            output.put("Client.Reminder.ChestOpenCancelled", "&4You are not allowed to open chests in dutymode!");
            output.put("Client.Reminder.ItemDrop", "&9Remember that you have dutymode on!");
            output.put("Client.Reminder.ItemDropCancelled", "&4You are not allowed to drop items in dutymode!");
            output.put("Client.AlreadyOn", "&4Dutymode is already on.");
            output.put("Client.AlreadyOff", "&4Dutymode is already off.");
            output.put("Client.ErrorOccured", "&4An error occured while enabling dutymode.");
            output.put("Client.List", "&aStaff on duty: &f");
            output.put("Client.ListAll", "&aPlayers on duty: &f");
            output.put("Client.NoStaffOnDuty", "&cThere is currently no staff on duty.");
            output.put("Client.NoPlayersOnDuty", "&cThere is currently no players on duty.");
            output.put("Client.BroadcastsShown", "&eNow broadcasting status updates!");
            output.put("Client.BroadcastsHidden", "&9No longer broadcasting status updates!.");
            output.put("Client.BroadcastsShownForPlayer", "&eThe requested player is now broadcasting status updates.");
            output.put("Client.BroadcastsHiddenForPlayer", "&9The requsted player is no longer broadcasting status updates.");
            output.put("Client.BroadcastsAlreadyShown", "&4You are already broadcasting status updates.");
            output.put("Client.BroadcastsAlreadyHidden", "&4You are already not broadcasting status updates.");
            output.put("Client.BroadcastsAlreadyShownForPlayer", "&4The requested player is already broadcasting status updates.");
            output.put("Client.BroadcastsAlreadyHiddenForPlayer", "&4The requested player is already not broadcasting status updates.");
            output.put("Server.Enabled", "Dutymode enabled for player %PLAYER_NAME%.");
            output.put("Server.Disabled", "Dutymode disabled for player %PLAYER_NAME%.");
            output.put("Server.Fail.Enable", "Failed to enable dutymode for player %PLAYER_NAME%.");
            output.put("Server.Fail.Disable", "Failed to disable dutymode for player %PLAYER_NAME%.");
            output.put("Server.IngamePlayersOnly", "This command is only available for in-game player.");
            return output;
        }
        
        public Messages(final File configFile) {
            this.configDefaults = new LinkedHashMap<String, Object>();
            this.config = new YamlConfiguration();
            this.configDefaults = this.initializeConfigDefaults();
            if (!configFile.exists()) {
                for (final String key : this.configDefaults.keySet()) {
                    this.config.set(key, this.configDefaults.get(key));
                }
                try {
                    this.config.save(configFile);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    this.config.load(configFile);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        
        public void Reload() {
            try {
                this.config.load(new File(String.valueOf(Duties.GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "messages.yml"));
            }
            catch (Exception e) {}
        }
        
        public boolean GetBoolean(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return false;
            }
            try {
                return this.config.getBoolean(key, (boolean)this.configDefaults.get(key));
            }
            catch (Exception exception) {
                return false;
            }
        }
        
        public void SetBoolean(final String key, final Boolean value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public String GetString(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return "";
            }
            try {
                if (this.config.getString(key).equals("")) {
                    Duties.GetInstance().LogMessage("Couldn't find the '" + key + "' message. Removing the 'messages.yml' file will fix this.");
                }
                return this.config.getString(key).replaceAll("&", String.valueOf('§'));
            }
            catch (Exception exception) {
                return "";
            }
        }
        
        public void SetBoolean(final String key, final String value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public List<String> GetStringList(final String key) {
            if (!this.configDefaults.containsKey(key)) {
                return null;
            }
            try {
                return (List<String>)this.config.getStringList(key);
            }
            catch (Exception exception) {
                return null;
            }
        }
        
        public void SetStringList(final String key, final List<String> value) {
            if (!this.configDefaults.containsKey(key)) {
                return;
            }
            try {
                this.config.set(key, (Object)value);
            }
            catch (Exception exception) {}
        }
        
        public YamlConfiguration GetHandle() {
            try {
                return this.config;
            }
            catch (Exception exception) {
                return null;
            }
        }
    }
}
