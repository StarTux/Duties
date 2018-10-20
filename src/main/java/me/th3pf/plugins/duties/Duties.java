// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties;

import java.util.Iterator;
import java.util.Collection;
import me.th3pf.plugins.duties.listeners.PlayerQuitListener;
import me.th3pf.plugins.duties.listeners.PlayerJoinListener;
import me.th3pf.plugins.duties.listeners.RemindListener;
import me.th3pf.plugins.duties.listeners.EntityDeathListener;
import me.th3pf.plugins.duties.listeners.PlayerInteractListener;
import org.bukkit.event.Listener;
import me.th3pf.plugins.duties.listeners.PlayerDropItemListener;
import me.th3pf.plugins.duties.commandexecutors.DutymodeCommandExecutor;
import org.bukkit.command.CommandExecutor;
import me.th3pf.plugins.duties.commandexecutors.DutiesCommandExecutor;
import java.io.File;
import java.util.ArrayList;
import me.th3pf.plugins.duties.adapters.VaultAdapter;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Duties extends JavaPlugin
{
    private static Duties Instance;
    public PluginManager pluginManager;
    public PluginDescriptionFile PDFile;
    public static Configuration.Main Config;
    public static Configuration.Messages Messages;
    public static HashMap<UUID, Memory> Memories;
    public static List<Player> Hidden;
    public static HashMap<String, Long> LastChestReminderTime;
    public static HashMap<String, Long> LastDropReminderTime;
    public static HashMap<Plugin, String> Addons;
    public static VaultAdapter VaultAdapter;
    public static boolean latestEventCancelled;
    
    static {
        Duties.Memories = new HashMap<UUID, Memory>();
        Duties.Hidden = new ArrayList<Player>();
        Duties.LastChestReminderTime = new HashMap<String, Long>();
        Duties.LastDropReminderTime = new HashMap<String, Long>();
        Duties.Addons = new HashMap<Plugin, String>();
        Duties.latestEventCancelled = false;
    }
    
    public Duties() {
        Duties.Instance = this;
    }
    
    public void onEnable() {
        this.pluginManager = this.getServer().getPluginManager();
        this.PDFile = this.getDescription();
        final Configuration configuration = new Configuration();
        configuration.getClass();
        Duties.Config = configuration.new Main(new File(String.valueOf(GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "config.yml"));
        final Configuration configuration2 = new Configuration();
        configuration2.getClass();
        Duties.Messages = configuration2.new Messages(new File(String.valueOf(GetInstance().getDataFolder().getAbsolutePath()) + File.separator + "messages.yml"));
        if (!Duties.Config.GetBoolean("Enabled")) {
            this.pluginManager.disablePlugin((Plugin)this);
        }
        if (this.pluginManager.isPluginEnabled("Vault")) {
            Duties.VaultAdapter = new VaultAdapter();
        }
        this.getCommand("duties").setExecutor((CommandExecutor)new DutiesCommandExecutor());
        this.getCommand("dutymode").setExecutor((CommandExecutor)new DutymodeCommandExecutor());
        this.pluginManager.registerEvents((Listener)new PlayerDropItemListener(), (Plugin)this);
        this.pluginManager.registerEvents((Listener)new PlayerInteractListener(), (Plugin)this);
        this.pluginManager.registerEvents((Listener)new EntityDeathListener(), (Plugin)this);
        this.pluginManager.registerEvents((Listener)new RemindListener(), (Plugin)this);
        if (Duties.Config.GetBoolean("KeepStateOffline")) {
            this.pluginManager.registerEvents((Listener)new PlayerJoinListener(), (Plugin)this);
        }
        else {
            this.pluginManager.registerEvents((Listener)new PlayerQuitListener(), (Plugin)this);
        }
        this.LogMessage("by " + this.PDFile.getAuthors().get(0) + " was successfully enabled!");
    }
    
    public void onDisable() {
        this.getServer().savePlayers();
        final ArrayList<UUID> keySet = new ArrayList<UUID>();
        keySet.addAll(Duties.Memories.keySet());
        if (Duties.Config.GetBoolean("KeepStateOffline")) {
            for (final UUID playerID : keySet) {
                if (GetInstance().getServer().getOfflinePlayer(playerID).isOnline()) {
                    if (new ModeSwitcher(GetInstance().getServer().getPlayer(playerID)).DisableDutyMode()) {
                        continue;
                    }
                    this.LogMessage("Couldn't disable duty mode for " + GetInstance().getServer().getPlayer(playerID).getName() + ".");
                }
                else {
                    final Player player = Duties.Memories.get(GetInstance().getServer().getPlayer(playerID).getName()).Player;
                    player.loadData();
                    if (!new ModeSwitcher(player).DisableDutyMode()) {
                        this.LogMessage("Dutymode inactivation for " + GetInstance().getServer().getPlayer(playerID).getName() + " couldn't complete. Sorry for the inconvience.");
                    }
                    player.saveData();
                }
            }
        }
        else {
            for (final UUID playerID : keySet) {
                if (!new ModeSwitcher(GetInstance().getServer().getPlayer(playerID)).DisableDutyMode()) {
                    this.LogMessage("Dutymode inactivation for " + GetInstance().getServer().getPlayer(playerID).getName() + " couldn't complete. Sorry for the inconvience.");
                }
            }
        }
        this.LogMessage("by " + this.PDFile.getAuthors().get(0) + " was successfully disabled!");
    }
    
    public static Duties GetInstance() {
        return Duties.Instance;
    }
    
    public static API GetAPI() {
        return new API();
    }
    
    public void LogMessage(final String Message) {
        System.out.println("[" + this.PDFile.getName() + " " + this.PDFile.getVersion() + "] " + Message);
    }
}
