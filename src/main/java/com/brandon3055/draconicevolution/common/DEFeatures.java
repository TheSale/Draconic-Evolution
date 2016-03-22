package com.brandon3055.draconicevolution.common;

import com.brandon3055.brandonscore.common.blocks.BlockBCore;
import com.brandon3055.brandonscore.common.blocks.BlockMobSafe;
import com.brandon3055.brandonscore.common.blocks.ItemBlockBasic;
import com.brandon3055.brandonscore.common.config.Feature;
import com.brandon3055.brandonscore.common.config.FeatureCollection;
import com.brandon3055.brandonscore.common.items.ItemSimpleSubs;
import com.brandon3055.draconicevolution.common.blocks.DraconiumBlock;
import com.brandon3055.draconicevolution.common.blocks.DraconiumOre;
import com.brandon3055.draconicevolution.common.blocks.ItemDraconiumBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

/**
 * Created by brandon3055 on 18/3/2016.
 * This class contains a reference to all blocks and items in Draconic Evolution
 */
public class DEFeatures implements FeatureCollection {

	//Blocks

	@Feature(name = "draconiumOre", variantMap = {"0:type=normal", "1:type=nether", "2:type=end"}, hasCustomItemBlock = true, getItemBlock = ItemBlockBasic.class)
	public static DraconiumOre draconiumOre = (DraconiumOre) new DraconiumOre().setHardness(10f).setResistance(20.0f);

	@Feature(name = "draconiumBlock", variantMap = {"0:charged=false", "1:charged=true"}, hasCustomItemBlock = true, getItemBlock = ItemDraconiumBlock.class)
	public static DraconiumBlock draconiumBlock = (DraconiumBlock) new DraconiumBlock().setHardness(10f).setResistance(20.0f);

	@Feature(name = "draconicBlock")
	public static BlockMobSafe draconicBlock = (BlockMobSafe) ((BlockBCore) new BlockMobSafe(Material.iron).setHardness(20F).setResistance(1000F)).setHarvestTool("pickaxe", 4);

	@Feature(name = "infusedObsidian")
	public static BlockMobSafe infusedObsidian = (BlockMobSafe) ((BlockBCore) new BlockMobSafe(Material.rock).setHardness(100F).setResistance(4000F)).setHarvestTool("pickaxe", 4);


	//items

	@Feature(name = "draconiumDust")
	public static Item draconiumDust = new Item();

	@Feature(name = "draconiumIngot")
	public static Item draconiumIngot = new Item();

	@Feature(name = "nugget", variantMap = {"0:type=draconium", "1:type=awakened"})
	public static ItemSimpleSubs nugget = new ItemSimpleSubs(new String[]{"0:draconium", "1:awakened"});

	@Feature(name = "draconicIngot")
	public static Item draconicIngot = new Item();

	@Feature(name = "draconicCore")
	public static Item draconicCore = new Item();

	@Feature(name = "wyvernCore")
	public static Item wyvernCore = new Item();

	@Feature(name = "awakenedCore")
	public static Item awakenedCore = new Item();

	@Feature(name = "chaoticCore")
	public static Item chaoticCore = new Item();
}