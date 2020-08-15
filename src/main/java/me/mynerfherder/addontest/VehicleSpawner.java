package me.mynerfherder.addontest;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class VehicleSpawner {

    private AddonTest plugin;
    public VehicleSpawner (AddonTest addonTest){
        this.plugin = addonTest;
    }

    public boolean holdingCarItem (PlayerInteractEvent holdingCar){

        //get the player then the target block and the world they are inside.
        Player player = holdingCar.getPlayer();
        Block block = player.getTargetBlock(null, 3);
        Location location = block.getLocation();
        //Get and add 1 to the y cord.
        location.setY(location.getY()+1);
        World w = holdingCar.getPlayer().getWorld();

        ItemStack item = holdingCar.getPlayer().getInventory().getItemInMainHand();
        if(item.getItemMeta().toString().contains("slimefun:slimefun_item=CAR")) {


            Entity car = w.spawnEntity(location, EntityType.MINECART);
            AddonTest.setMetadata(plugin, car, "car", 42069);
            holdingCar.getPlayer().sendMessage("He Be Livin.");
            return true;
        }
        else {
            return false;
        }
    }
}
