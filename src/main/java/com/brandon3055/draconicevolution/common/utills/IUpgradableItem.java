package com.brandon3055.draconicevolution.common.utills;

import cofh.api.energy.IEnergyContainerItem;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.draconicevolution.common.lib.References;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by brandon3055 on 23/12/2015.
 */
public interface IUpgradableItem {

	/**@return a list of upgrades for this item.*/
	public List<EnumUpgrade> getUpgrades(ItemStack itemstack);

	/**@return the maximum number of upgrades for this item*/
	public int getUpgradeCap(ItemStack itemstack);

	/**@return the max upgrade tier allowed for this item
	 * 0 = Draconic Core
	 * 1 = Wyvern Core
	 * 2 = Awakened Core
	 * 3 = Chaotic Core*/
	public int getMaxTier(ItemStack itemstack);

	public int getMaxUpgradePoints(int upgradeIndex);

	public int getBaseUpgradePoints(int upgradeIndex);

	public List<String> getUpgradeStats(ItemStack itemstack);

	public static enum EnumUpgrade {
		RF_CAPACITY		(0, 1, "RFCapacity"){
			@Override
			public void onRemovedFromItem(ItemStack itemStack) {
				if (itemStack != null && itemStack.getItem() instanceof IEnergyContainerItem){
					IEnergyContainerItem item = (IEnergyContainerItem)itemStack.getItem();
					for (int i = 0; i < 500 && item.getEnergyStored(itemStack) > item.getMaxEnergyStored(itemStack); i++){
						item.extractEnergy(itemStack, item.getEnergyStored(itemStack)-item.getMaxEnergyStored(itemStack), false);
					}
				}
			}
		},
		DIG_SPEED		(1,  1, "DigSpeed"),
		DIG_AOE			(2,  4, "DigAOE"){
			@Override
			public void onRemovedFromItem(ItemStack itemStack) {
				if (ItemNBTHelper.getInteger(itemStack, References.DIG_AOE, 0) > getUpgradePoints(itemStack)) ItemNBTHelper.setInteger(itemStack, References.DIG_AOE, getUpgradePoints(itemStack));
			}
		},
		DIG_DEPTH		(3,  2, "DigDepth"){
			@Override
			public void onRemovedFromItem(ItemStack itemStack) {
				if (ItemNBTHelper.getInteger(itemStack, References.DIG_DEPTH, 0) > getUpgradePoints(itemStack)) ItemNBTHelper.setInteger(itemStack, References.DIG_DEPTH, getUpgradePoints(itemStack));
			}
		},
		ATTACK_DAMAGE	(4,  1, "AttackDamage"),
		ATTACK_AOE		(5,  2, "AttackAOE"){
			@Override
			public void onRemovedFromItem(ItemStack itemStack) {
				if (ItemNBTHelper.getInteger(itemStack, References.ATTACK_AOE, 0) > getUpgradePoints(itemStack)) ItemNBTHelper.setInteger(itemStack, References.ATTACK_AOE, getUpgradePoints(itemStack));
			}
		},
		ARROW_DAMAGE	(6,  1, "ArrowDamage"),
		DRAW_SPEED		(7,  1, "DrawSpeed"),
		ARROW_SPEED		(8,  1, "ArrowSpeed"),
		SHIELD_CAPACITY	(9,  1, "ShieldCapacity"),
		SHIELD_RECOVERY	(10, 1, "ShieldRecovery"),
		MOVE_SPEED		(11, 1, "MoveSpeed"),
		JUMP_BOOST		(12, 1, "JumpBoost");

		public int index;
		/**How many core points dose it take to add 1 upgrade point*/
		public int pointConversion;
		public String name;
		private final String COMPOUND_NAME =  "Upgrades";

		private EnumUpgrade(int index, int pointConversion, String name) {
			this.index = index;
			this.pointConversion = pointConversion;
			this.name = name;
		}

		/**Get the number of cores applied to this upgrade
		 * @return an int[4] containing the number of cores that have been applied
		 * in order from index 0 which is the number od draconic cores to index 3 which is the number of chaotic cores
		 * with wyvern and awakened in between*/
		public int[] getCoresApplied(ItemStack stack){
			if (stack == null) return new int[] {0, 0, 0, 0};
			NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
			if (!compound.hasKey(COMPOUND_NAME)) return new int[] {0, 0, 0, 0};
			NBTTagCompound upgrades = compound.getCompoundTag(COMPOUND_NAME);
			if (upgrades.hasKey(name) && upgrades.getIntArray(name).length == 4) return upgrades.getIntArray(name);
			return new int[] {0, 0, 0, 0};
		}

		/**Sets the number of each core type applied
		 * takes an ItemStack and an int[4]*/
		public void setCoresApplied(ItemStack stack, int[] cores){
			if (cores.length != 4){
				LogHelper.error("[EnumUpgrade] Error applying upgrades to stack.");
				return;
			}

			NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
			NBTTagCompound upgrades;
			if (compound.hasKey(COMPOUND_NAME)) upgrades = compound.getCompoundTag(COMPOUND_NAME);
			else upgrades = new NBTTagCompound();
			upgrades.setIntArray(name, cores);
			compound.setTag(COMPOUND_NAME, upgrades);
		}

		public String getLocalizedName() { return StatCollector.translateToLocal("gui.de."+name+".txt"); }

		public static EnumUpgrade getUpgradeByIndex(int index) {
			for (EnumUpgrade upgrade : EnumUpgrade.values()) {
				if (upgrade.index == index) return upgrade;
			}
			return null;
		}

		/**Returns the combined upgrade points from all cores applied to this upgrade */
		public int getUpgradePoints(ItemStack itemStack){
			int[] applied = getCoresApplied(itemStack);
			int totalPoints = applied[0] + (applied[1] * 2) + (applied[2] * 4) + (applied[3] * 8);
			if (itemStack != null && itemStack.getItem() instanceof IUpgradableItem){
				int points = ((IUpgradableItem)itemStack.getItem()).getBaseUpgradePoints(index) + (totalPoints / pointConversion);
				return Math.min(points, ((IUpgradableItem)itemStack.getItem()).getMaxUpgradePoints(index));
			}
			return 0;
		}

		/**Called when this upgrade is applied to an item*/
		public void onAppliedToItem(ItemStack itemStack){

		}

		/**Called when this upgrade is removed from an item*/
		public void onRemovedFromItem(ItemStack itemStack){

		}

	}
}
