package me.mynerfherder.addontest;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.entity.LivingEntity;

public class CartListener implements Listener{

    //Constructor
    public CartListener(AddonTest plugin) {



    }


    //EventHandler
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {

        event.getPlayer().sendMessage("Beunos Tardes. The Listener Class is working?");

    }

    //Interact checker that can see right click and block interaction then returns the held Items MetaData
    @EventHandler
    public void playerItemRightClick (PlayerInteractEvent interactEvent) {

        ItemStack item = interactEvent.getPlayer().getInventory().getItemInMainHand();
        if(item.getItemMeta().toString().contains("slimefun:slimefun_item=GRASS_GEL")) {

            interactEvent.getPlayer().sendMessage("true");

        }
        //Right click while holding an item to receive metadata info.
        interactEvent.getPlayer().sendMessage(item.getItemMeta().toString());
        Block p = interactEvent.getPlayer().getTargetBlockExact(4);
        interactEvent.getPlayer().sendMessage(p.toString());
    }

    //Returns the entity name that was right clicked or the block right clicked.
    @EventHandler
    public void inMineCart (PlayerInteractEntityEvent inMineCart) {

        Entity clickedEntity = inMineCart.getRightClicked();
        inMineCart.getPlayer().sendMessage(clickedEntity.toString());


    }
}
