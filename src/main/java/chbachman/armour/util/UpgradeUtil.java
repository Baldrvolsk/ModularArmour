package chbachman.armour.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import baubles.api.BaublesApi;
import chbachman.api.IModularItem;
import chbachman.api.IUpgrade;
import cpw.mods.fml.common.Loader;

public class UpgradeUtil {

	public static boolean doesPlayerHaveUpgrade(EntityPlayer player, IUpgrade upgrade) {
		ItemStack[] armourArray = player.inventory.armorInventory;

		for (ItemStack armour : armourArray) {
			if(doesItemStackContainUpgrade(armour, upgrade)){
				return true;
			}

		}

		if(Loader.isModLoaded("Baubles")){
			IInventory inventory = BaublesApi.getBaubles(player);

			for(int i = 0; i < inventory.getSizeInventory(); i++){
				ItemStack bauble = inventory.getStackInSlot(i);

				if(doesItemStackContainUpgrade(bauble, upgrade)){
					return true;
				}
			}
		}

		return false;
	}

	public static ItemStack getPlayerUpgrade(EntityPlayer player, IUpgrade upgrade){
		ItemStack[] armourArray = player.inventory.armorInventory;

		for (ItemStack armour : armourArray) {
			if(doesItemStackContainUpgrade(armour, upgrade)){
				return armour;
			}

		}

		if(Loader.isModLoaded("Baubles")){
			IInventory inventory = BaublesApi.getBaubles(player);

			for(int i = 0; i < inventory.getSizeInventory(); i++){
				ItemStack bauble = inventory.getStackInSlot(i);

				if(doesItemStackContainUpgrade(bauble, upgrade)){
					return bauble;
				}
			}
		}

		return null;
	}

	public static void removeUpgrade(ItemStack container, IUpgrade upgrade) {

		ItemStack stack = container.copy();

		if (stack.stackTagCompound == null) {
			NBTHelper.createDefaultStackTag(stack);
			return;
		}

		if (stack.getItem() instanceof IModularItem) {

			NBTUpgradeList list = NBTHelper.getNBTUpgradeList(stack.stackTagCompound);

			for(int i = 0; i < list.size(); i++){
				if(list.get(i).equals(upgrade)){
					list.remove(i);
					break;
				}
			}

			container.stackTagCompound = stack.stackTagCompound;
		}

	}

	public static List<IUpgrade> getDependencyList(IUpgrade upgrade) {
		List<IUpgrade> list = new ArrayList<IUpgrade>();
		list.add(upgrade);
		return list;
	}

	public static boolean doesItemStackContainUpgrade(ItemStack stack, IUpgrade upgrade) {

		if(stack == null || upgrade == null){
			return false;
		}

		NBTHelper.createDefaultStackTag(stack);

		NBTUpgradeList list = NBTHelper.getNBTUpgradeList(stack.stackTagCompound);

		return list.contains(upgrade);
	}

}
