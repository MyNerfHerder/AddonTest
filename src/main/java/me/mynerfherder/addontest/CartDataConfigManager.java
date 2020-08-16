package me.mynerfherder.addontest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getLogger;

public class CartDataConfigManager {

    private AddonTest plugin = AddonTest.getPlugin(AddonTest.class);

    //File and the File config objects.
    public FileConfiguration cartDataConfig;
    public File cartDataFile;

    public void setupCartData() {
        if (!plugin.getDataFolder().exists()) {
            try {
                plugin.getDataFolder().mkdir();
                getLogger().info("Plugin config folder for AddonTest made");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        cartDataFile = new File(plugin.getDataFolder(), "cartData.yml");

        if (!cartDataFile.exists()){
            try {
                cartDataFile.createNewFile();
            }
            catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the cartData.yml file!!!");
            }

        }
        cartDataConfig = YamlConfiguration.loadConfiguration(cartDataFile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Created the cartData.yml file!");
    }
}
