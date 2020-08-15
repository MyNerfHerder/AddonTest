package me.mynerfherder.addontest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.mynerfherder.addontest.VehicleSpawner;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;



public class CartListener implements Listener{


    private AddonTest plugin;
    //Constructor
    public CartListener(AddonTest plugin) {

        this.plugin = plugin;

    }


    //EventHandler
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {

        event.getPlayer().sendMessage("Beunos Tardes. The Listener Class is working?");

    }

    //Interact checker that can see right click and block interaction then returns the held Items MetaData
    @EventHandler
    public void playerItemRightClick (PlayerInteractEvent interactEvent) {

        //ItemStack item = interactEvent.getPlayer().getInventory().getItemInMainHand();
        //if(item.getItemMeta().toString().contains("slimefun:slimefun_item=GRASS_GEL")) {

        //    interactEvent.getPlayer().sendMessage("true");

        //}
        //Right click while holding an item to receive metadata info.
        //interactEvent.getPlayer().sendMessage(item.getItemMeta().toString());
        Block p = interactEvent.getPlayer().getTargetBlockExact(4);
        interactEvent.getPlayer().sendMessage(p.toString());

        //get the player then the target block and the world they are inside.
        Player player = interactEvent.getPlayer();
        Block block = player.getTargetBlock(null, 3);
        Location location = block.getLocation();
        //Get and add 1 to the y cord.
        location.setY(location.getY()+1);
        World w = interactEvent.getPlayer().getWorld();

        ItemStack item = interactEvent.getPlayer().getInventory().getItemInMainHand();
        if(item.getItemMeta().toString().contains("slimefun:slimefun_item=CAR")) {


            Entity car = w.spawnEntity(location, EntityType.MINECART);
            //AddonTest.setMetadata(plugin, car, "car", "Yep that's a car. not a minecart");
            interactEvent.getPlayer().sendMessage("He Be Livin.");
        }


    }



    //Returns the entity name that was right clicked or the block right clicked.
    @EventHandler
    public void inMineCart (PlayerInteractEntityEvent returnEntity) {

        Entity clickedEntity = returnEntity.getRightClicked();
        returnEntity.getPlayer().sendMessage(clickedEntity.toString());
        //List<MetadataValue> carData = returnEntity.getRightClicked().getMetadata("car");
        //returnEntity.getPlayer().sendMessage(carData.get(0).toString());



    }
}
