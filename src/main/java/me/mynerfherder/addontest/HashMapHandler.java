package me.mynerfherder.addontest;


import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class HashMapHandler extends JavaPlugin {

    private HashMap<UUID, Integer> fuelLevel = new HashMap<UUID, Integer>();

    public UUID getUUIDFromEvent (PlayerInteractEntityEvent event){

        Entity entity = event.getRightClicked();
        UUID uuid = entity.getUniqueId();
        return uuid;
    }

    public void setFuelLevel (UUID uuid){



    }

    public Integer getFuelLevel (UUID uuid){

    return 20;

    }

}
