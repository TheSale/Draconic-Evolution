package com.brandon3055.draconicevolution;


import com.brandon3055.brandonscore.handlers.FileHandler;
import com.brandon3055.brandonscore.registry.IModConfigHelper;
import com.brandon3055.brandonscore.registry.ModConfigContainer;
import com.brandon3055.brandonscore.registry.ModConfigProperty;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon3055 on 24/3/2016.
 * This class holds all of the config values for Draconic Evolution
 */
@ModConfigContainer(modid = DraconicEvolution.MODID)
public class DEConfig implements IModConfigHelper {

    public static Map<String, String> comments = new HashMap<String, String>();

    static {
        comments.put("items", "Allows you to disable any item in the mod. Note that disabling an item will automatically\ndisable its recipe and all recipes that use it. (Requires game restart)\nTo disable an item set its value to false");
        comments.put("blocks", "Allows you to disable any block in the mod. Note that disabling an block will automatically\ndisable its recipe and all recipes that use it. (Requires game restart)\nTo disable a block set its value to false");
        comments.put("World", "This category contains config properties related to world gen.");
        comments.put("Tweaks", "Just what the name says. Tweaks. Allows you to tweak stuff.");
        comments.put("Client Settings", "These are client side properties that have no effect server side.");
        comments.put("Stat Tweaks", "These allow you to tweak the stats of the tools, weapons and armor.");
        comments.put("Misc", "Just some misc settings.");
    }

    @Override
    public Configuration createConfiguration(FMLPreInitializationEvent event) {
        return new Configuration(new File(FileHandler.brandon3055Folder, "DraconicEvolution.cfg"), true);
    }

    @Override
    public String getCategoryComment(String category) {
        return comments.getOrDefault(category, "");
    }

    @Override
    public void onConfigChanged(String propertyName, String propertyCategory) {

    }

    //Category World

    @ModConfigProperty(category = "World", name = "worldGenEnabled", comment = "Setting this to false will just completely disable ALL DE world gen!")
    public static boolean worldGenEnabled = true;

    @ModConfigProperty(category = "World", name = "enableRetroGen", comment = "Set this to false if you do not want ore added to chunks that have not previously been generated by DE (this can almost always be left true)")
    public static boolean enableRetroGen = true;

    @ModConfigProperty(category = "World", name = "disableOreSpawnOverworld", comment = "Disables draconium ore generation in the overworld")
    public static boolean disableOreSpawnOverworld = false;

    @ModConfigProperty(category = "World", name = "disableOreSpawnEnd", comment = "Disables draconium ore generation in the end")
    public static boolean disableOreSpawnEnd = false;

    @ModConfigProperty(category = "World", name = "disableOreSpawnNether", comment = "Disables draconium ore generation in the nether")
    public static boolean disableOreSpawnNether = false;

    @ModConfigProperty(category = "World", name = "generateEnderComets", comment = "Set to false to disable the generation of Ender Comets")
    public static boolean generateEnderComets = true;

    @ModConfigProperty(category = "World", name = "generateChaosIslands", comment = "Set to false to disable the generation of Chaos Islands")
    public static boolean generateChaosIslands = true;

    @ModConfigProperty(category = "World", name = "cometRarity", comment = "Ender Comets have a 1 in {this number} chance to spawn in each chunk")
    public static int cometRarity = 10000;

    @ModConfigProperty(category = "World", name = "chaosIslandSeparation", comment = "This is the distance between chaos islands")
    public static int chaosIslandSeparation = 10000;

    @ModConfigProperty(category = "World", name = "oreGenDimentionBlacklist", comment = "Add the dimension id of any mod dimension's you dont want draconium ore generated in")
    public static int[] oreGenDimentionBlacklist = new int[0];

    //Category Tweak

    @ModConfigProperty(category = "Tweaks", name = "rapidDespawnAOEMinedItems", comment = "If set to true items dropped by tools in AOE mode will despawn after 5 seconds if not picked up.")
    public static boolean rapidDespawnAOEMinedItems = false;

    @ModConfigProperty(category = "Tweaks", name = "disableGuardianCrystalRespawn", comment = "(Wuss mode) Setting this to true will disable the chaos guardians ability to respawn healing crystals.")
    public static boolean disableGuardianCrystalRespawn = false;

    @ModConfigProperty(category = "Tweaks", name = "enableFlight", comment = "Set this to false if you would like to disable the draconic armors flight.")
    public static boolean enableFlight = true;

    @ModConfigProperty(category = "Tweaks", name = "dislocatorUsesPerPearl", comment = "Sets the number of teleports you get per ender pearl with the Advanced Dislocator")
    public static int dislocatorUsesPerPearl = 1;

    @ModConfigProperty(category = "Tweaks", name = "hardMode", comment = "When true everything is just a little harder. (Currently only effects recipes but that will probably change in the future)")
    public static boolean hardMode = false;

    @ModConfigProperty(category = "Tweaks", name = "bowBlockDamage", comment = "Set to false to prevent the bows explosion effect breaking blocks")
    public static boolean bowBlockDamage = true;

    @ModConfigProperty(category = "Tweaks", name = "grinderEnergyPerHeart", comment = "Sets the energy per use per heart of damage for the grinder.")
    public static int grinderEnergyPerHeart = 80;

    @ModConfigProperty(category = "Tweaks", name = "dragonEggSpawnOverride", comment = "By default the dragon egg only ever spawns once. This forces it to spawn every time the dragon is killed.")
    public static boolean dragonEggSpawnOverride = true;

    @ModConfigProperty(category = "Tweaks", name = "expensiveDragonRitual", comment = "Lets face it. The biggest issue with the new dragon ritual is it is too darn cheap! This modifies the recipe to make it a bit more expensive.")
    public static boolean expensiveDragonRitual = true;

    @ModConfigProperty(category = "Tweaks", name = "itemDislocatorBlacklist", comment = "A list of items of items that should be ignored by the item dislocator. Use the items registry name e.g. minecraft:apple you can also add a meta value like so minecraft:wool|4")
    public static String[] itemDislocatorBlacklist = new String[]{"appliedenergistics2:item.ItemCrystalSeed"};

    @ModConfigProperty(category = "Tweaks", name = "reactorOutputMultiplier", comment = "Adjusts the energy output multiplier of the reactor")
    public static double reactorOutputMultiplier = 1;

    @ModConfigProperty(category = "Tweaks", name = "reactorFuelUsageMultiplier", comment = "Adjusts the fuel usage multiplier of the reactor")
    public static double reactorFuelUsageMultiplier = 1;

    @ModConfigProperty(category = "Tweaks", name = "dragonDustLootModifier", comment = "This can be used to adjust the amount of draconium dust the dragon drops when killed.\nThe amount dropped will be this number +/- 10%")
    public static int dragonDustLootModifier = 1;

    @ModConfigProperty(category = "Tweaks", name = "disableLargeReactorBoom", comment = "If true will disable the massive reactor explosion and replace it with a much smaller one.")
    public static boolean disableLargeReactorBoom = false;

    @ModConfigProperty(category = "Tweaks", name = "disableLootCores", comment = "This will disable loot cores (The \"Blobs\" of items dropped by the tools.).")
    public static boolean disableLootCores = false;

    @ModConfigProperty(category = "Tweaks", name = "reactorExplosionScale", comment = "Allows you to adjust the overall scale of the reactor explosion. use \"disableLargeReactorBoom\" to diaable explosion completely.")
    public static double reactorExplosionScale = 1;

    @ModConfigProperty(category = "Tweaks", name = "soulDropChance", comment = "Mobs have a 1 in this number chance to drop a soul")
    public static int soulDropChance = 1000;

    @ModConfigProperty(category = "Tweaks", name = "passiveSoulDropChance", comment = "Passive (Animals) Mobs have a 1 in this number chance to drop a soul")
    public static int passiveSoulDropChance = 800;

    @ModConfigProperty(category = "Tweaks", name = "spawnerList", comment = "By default any entities added to this list will not drop their souls and will not be spawnable by the stabilized spawner.")
    public static String[] spawnerList = {};

    @ModConfigProperty(category = "Tweaks", name = "spawnerListWhiteList", comment = "Changes the spawner list to a whitelist as opposed to the default blacklist.")
    public static boolean spawnerListWhiteList = false;

    @ModConfigProperty(category = "Tweaks", name = "spawnerDelays", comment = "Sets the min and max spawn delay in ticks for each spawner tier. Order is as follows.\nBasic MIN, MAX, Wyvern MIN, MAX, Draconic MIN, MAX, Chaotic MIN MAX")
    public static int[] spawnerDelays = new int[]{200, 800, 100, 400, 50, 200, 25, 100};

    //Category Client

    @ModConfigProperty(category = "Client Settings", name = "hudSettings", comment = "This is where the settings for the in game hud are stored. You should not need to adjust these unless something breaks.")
    public static int[] hudSettings = new int[]{996, 825, 69, 907, 90, 100, 3, 0, 1, 1, 1, 1}; //x, y, x, y, scale, scale, fademode, fademode, rotateArmor, armorText, hudEnabled, shieldEnabled

    @ModConfigProperty(category = "Client Settings", name = "disable3DModels", comment = "Disables the 3D tool and armor models. This is required if you want to use a 2D resource pack.)")
    public static boolean disable3DModels = false;

    @ModConfigProperty(category = "Client Settings", name = "invertDPDSB", comment = "Invert Dislocator Pedestal display name shift behavior.")
    public static boolean invertDPDSB = false;

    @ModConfigProperty(category = "Client Settings", name = "useShaders", comment = "Set this to false if your system can not handle the awesomeness that is shaders! (Warning will make cool things look like crap)")
    public static boolean useShaders = true;

    @ModConfigProperty(category = "Client Settings", name = "useCrystalShaders", comment = "This allowes you to just disable the energy crystal shader which can be a lot lagier than the reactor since there are usually a lot more of them (The fallback crystal texture is not soooo bad...)")
    public static boolean useCrystalShaders = true;

    @ModConfigProperty(category = "Client Settings", name = "useReactorBeamShaders", comment = "Set this to false if you prefer the original look of the reactor beams")
    public static boolean useReactorBeamShaders = true;

    @ModConfigProperty(category = "Client Settings", name = "disableLoudCelestialManipulator", comment = "If true the range of the celestial manipulator sound effect will be significantly reduced.")
    public static boolean disableLoudCelestialManipulator = false;

    //Category Misc

    @ModConfigProperty(category = "Misc", name = "devLog", comment = "This enables dev log output. I primarily use this for development purposes so it wont be very useful to regular users.")
    public static boolean devLog = false;

    @ModConfigProperty(category = "Misc", name = "chaosGuardianLoading", comment = "Set this to false to disable chaos guardian chunkloading.\nNote. The chaos guardian is ONLY loaded when a player is within a couple hundred blocks.\nThis is to avoid issues where the guardian would flu out of the loaded chunks and freeze\nwhich is especial an issue on servers with reduced render distance.")
    public static boolean chaosGuardianLoading = true;

    public static Map<String, Integer> itemDislocatorBlacklistMap = new HashMap<String, Integer>();

    @Override
    public void onConfigLoaded() {
        itemDislocatorBlacklistMap.clear();
        for (String s : itemDislocatorBlacklist) {
            if (s.contains("|")) {
                itemDislocatorBlacklistMap.put(s.substring(0, s.indexOf("|")), Integer.parseInt(s.substring(s.indexOf("|") + 1)));
            }
            else {
                itemDislocatorBlacklistMap.put(s, -1);
            }
        }
    }
}


//todo energy core storage values and ability to disable tier 8
//todo tool and armor stats and energy
//todo
//todo
