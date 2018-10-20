// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import me.th3pf.plugins.duties.Duties;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.Listener;

public class EntityDeathListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDeath(final EntityDeathEvent event) {
        if (Duties.Config.GetBoolean("Actions.DisableDeathDrops") && event.getEntity() instanceof Player && Duties.Memories.containsKey(((Player)event.getEntity()).getName())) {
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
        if (Duties.Config.GetBoolean("Actions.DisableKillDrops") && event.getEntity().getKiller() instanceof Player && Duties.Memories.containsKey(event.getEntity().getKiller().getName())) {
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }
}
