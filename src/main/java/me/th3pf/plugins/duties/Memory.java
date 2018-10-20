// 
// Decompiled by Procyon v0.5.30
// 

package me.th3pf.plugins.duties;

import org.bukkit.entity.Damageable;
import org.bukkit.permissions.PermissionAttachment;
import java.util.List;
import org.bukkit.potion.PotionEffect;
import java.util.Collection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.GameMode;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Memory
{
    public Player Player;
    public Location Location;
    public Entity Vehicle;
    public Vector Velocity;
    public GameMode GameMode;
    public ItemStack[] Inventory;
    public ItemStack[] Armor;
    public double Health;
    public int FoodLevel;
    public int Level;
    public float Experience;
    public float Saturation;
    public float Exhaustion;
    public int FireTicks;
    public int RemainingAir;
    public float FallDistance;
    public Collection<PotionEffect> PotionEffects;
    public Location BedSpawnLocation;
    public int TicksLived;
    public int ticksOnDuty;
    public List<PermissionAttachment> TemporaryPermissions;
    
    public Memory(final Player Player, final Location Location, final Entity Vehicle, final Vector Velocity, final GameMode GameMode, final ItemStack[] Inventory, final ItemStack[] Armor, final double Health, final int FoodLevel, final int Level, final float Experience, final float Saturation, final float Exhaustion, final int RemainingAir, final float FallDistance, final int FireTicks, final Collection<PotionEffect> PotionEffects, final Location BedSpawnLocation, final int TicksLived, final int ticksOnDuty) {
        this.Player = Player;
        this.Location = Location;
        this.Vehicle = Vehicle;
        this.Velocity = Velocity;
        this.GameMode = GameMode;
        this.Inventory = Inventory;
        this.Armor = Armor;
        this.Health = Health;
        this.FoodLevel = FoodLevel;
        this.Experience = Experience;
        this.Saturation = Saturation;
        this.Exhaustion = Exhaustion;
        this.RemainingAir = RemainingAir;
        this.FallDistance = FallDistance;
        this.FireTicks = FireTicks;
        this.PotionEffects = PotionEffects;
        this.BedSpawnLocation = BedSpawnLocation;
        this.TicksLived = TicksLived;
        this.ticksOnDuty = ticksOnDuty;
    }
    
    public Memory(final Player player, final int ticksOnDuty) {
        this.Player = player;
        this.Location = player.getLocation();
        this.Vehicle = player.getVehicle();
        this.Velocity = player.getVelocity();
        this.GameMode = player.getGameMode();
        this.Inventory = player.getInventory().getContents();
        this.Armor = player.getInventory().getArmorContents();
        this.Health = ((Damageable)player).getHealth();
        this.FoodLevel = player.getFoodLevel();
        this.Level = player.getLevel();
        this.Experience = player.getExp();
        this.Saturation = player.getSaturation();
        this.Exhaustion = player.getExhaustion();
        this.RemainingAir = player.getRemainingAir();
        this.FallDistance = player.getFallDistance();
        this.FireTicks = player.getFireTicks();
        this.PotionEffects = (Collection<PotionEffect>)player.getActivePotionEffects();
        this.BedSpawnLocation = player.getBedSpawnLocation();
        this.TicksLived = player.getTicksLived();
        this.ticksOnDuty = ticksOnDuty;
    }
}
