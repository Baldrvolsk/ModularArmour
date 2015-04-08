package chbachman.armour.upgrade.upgradeList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import chbachman.api.util.ArmourSlot;
import chbachman.armour.upgrade.UpgradeProtective;

public class UpgradePlayerProtection extends UpgradeProtective{

	public UpgradePlayerProtection() {
		super("playerProtection", 90);
	}

	@Override
	public boolean shouldDefend(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, ArmourSlot slot) {
		Entity entity = source.getSourceOfDamage();

		if(entity == null){
			return false;
		}

		return entity instanceof EntityPlayer;

	}
	
	public int getEnergyPerDamage(ItemStack stack){
		return 100;
	}

}
