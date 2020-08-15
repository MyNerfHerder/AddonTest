package me.mynerfherder.addontest;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.meta.PotionMeta;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AddonTest extends JavaPlugin implements SlimefunAddon {


    //sets up meta data for da carts?
    public static void setMetadata (Plugin plugin, Entity entity, String key, Object object){

        entity.setMetadata(key, new FixedMetadataValue(plugin, object));

    }

    //makes the data file object to be used later.
    private File cartData = new File("../AddonTest/cartData.yml");
    //Loads the YAML Configuration.
    public YamlConfiguration cartDataFile = YamlConfiguration.loadConfiguration(cartData);

    @Override
    public void onEnable() {
        //Prints out that the addon is enabled in the console
        getLogger().info("Vehicle Mod has been Enabled and will now load.");

        //Gets if the cart data file exists or not and creates it.
        if(!cartData.exists()) {
            try {
                cartDataFile.save(cartData);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Manages the plugins???
        PluginManager pm = getServer().getPluginManager();

        //He hears all! events...
        CartListener listener = new CartListener(this);
        pm.registerEvents(listener, this);

        // Read something from your config.yml
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }

        // Slimefun4 also already comes with a bundled version of bStats
        // You can use bStats to collect usage data about your plugin
        // More info: https://bstats.org/getting-started
        // Set bStatsId to the id of your plugin
        int bStatsId = -1;
        new Metrics(this, bStatsId);

        // Give your Category a unique id.
        NamespacedKey categoryId = new NamespacedKey(this, "vehicles");

        // Create a new Category
        // This Category will use this ItemStack
        ItemStack categoryItem = new CustomItem(Material.MINECART, "&fVehicles", "", "&bThis is where cars live. Gitchu one! \n&a> Click to open");

        //Where both lines above come together!
        Category category = new Category(categoryId, categoryItem);

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        // This Defines the look and descriptions of the items as well as the name of said items.
        // Create a new Slimefun ItemStack
        // This class has many constructors, it is very important that you give each item a unique id.
        SlimefunItemStack DriedGrassItem = new SlimefunItemStack("DRIED_GRASS", Material.DRIED_KELP, "&aDried grass", "&fAll dried up and crinkly.");
        SlimefunItemStack GrassGelItem = new SlimefunItemStack("GRASS_GEL", Material.SLIME_BALL, "&2Grass Gel", "&cAll Gelled up smelling like fresh cut grass.");
        SlimefunItemStack BiomassBarrelItem = new SlimefunItemStack("BIOMASS_BARREL", SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGUxN2Q5MzRiNDY1ZjUyNmE4Mzc2NmZhZmMwNGIyNzRkMmYxMTFhNDE2MThlMzY3OTcwNmJhODUxY2E4ZiJ9fX0=") , "&2Biomass Barrel","A way to store all of that MASS!");
        SlimefunItemStack RubberBallItem = new SlimefunItemStack("RUBBER_BALL", SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI1MjNlMTVlOTk4NjM1NWExZjg1MWY0M2Y3NTBlZTNmMjNjODlhZTEyMzYzMWRhMjQxZjg3MmJhN2E3ODEifX19"), "&7Rubber Ball", "&eA lump of rubber");

        SlimefunItemStack CarItem = new SlimefunItemStack("CAR", Material.MINECART, "Car", "HE HE car goes brrrrrrrr.");
        SlimefunItemStack SparkPlugItem = new SlimefunItemStack("SPARK_PLUG", Material.BREWING_STAND, "Spark Plug", "This makes the small lightning.");

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        // The Recipe is an ItemStack Array with a length of 9.
        // It represents a Shaped Recipe in a 3x3 crafting grid
        // The machine in which this recipe is crafted in is specified further down
        ItemStack[] DriedGrassRecipe = { new ItemStack(Material.GRASS, 10), null, null, null, null, null, null, null, null };
        ItemStack[] GrassGelRecipe = { new SlimefunItemStack(DriedGrassItem, 10), null, null, null, null, null, null, null, null };
        ItemStack[] BiomassBarrelRecipe = { new SlimefunItemStack(GrassGelItem, 3), null, null, null, null, null, null, null, null };
        ItemStack[] RubberBallRecipe = { new SlimefunItemStack(SlimefunItems.OIL_BUCKET, 1), null, null, null, null, null, null, null, null };

        ItemStack[] CarRecipe = {null, null, null, null, null, null, null, null, null};
        ItemStack[] SparkPlugRecipe = {null, null, null, null, null, null, null, null, null};

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        // Now you just have to register the item
        // RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in which this item is crafted in.
        // Recipe Types from Slimefun itself will automatically add the recipe to that machine
        SlimefunItem DriedGrass = new SlimefunItem(category, DriedGrassItem, RecipeType.SMELTERY, DriedGrassRecipe);
        SlimefunItem GrassGel = new SlimefunItem(category, GrassGelItem, RecipeType.PRESSURE_CHAMBER, GrassGelRecipe);
        SlimefunItem BiomassBarrel = new SlimefunItem(category, BiomassBarrelItem, RecipeType.REFINERY, BiomassBarrelRecipe);
        SlimefunItem RubberBall = new SlimefunItem(category, RubberBallItem, RecipeType.HEATED_PRESSURE_CHAMBER, RubberBallRecipe);

        SlimefunItem Car = new SlimefunItem(category, CarItem, RecipeType.ENHANCED_CRAFTING_TABLE, CarRecipe);
        SlimefunItem SparkPlug = new SlimefunItem(category, SparkPlugItem, RecipeType.ENHANCED_CRAFTING_TABLE, SparkPlugRecipe);

        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        //Registers all the recipes for this addon to slimefun.
        DriedGrass.register(this);
        GrassGel.register(this);
        BiomassBarrel.register(this);
        RubberBall.register(this);

        Car.register(this);
        SparkPlug.register(this);

        //Stuff to customize a potion and its effects
        //PotionMeta potmeta = (PotionMeta) GrassJuiceItem.getItemMeta();
        //potmeta.setColor(Color.LIME);
        //potmeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 100, 0), true);
        //potmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        //GrassJuiceItem.setItemMeta(potmeta);


    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
        getLogger().info("The Vehicle SF Addon Is Disabled.");
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

}
