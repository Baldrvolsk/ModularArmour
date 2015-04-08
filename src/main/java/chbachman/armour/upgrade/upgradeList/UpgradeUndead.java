package chbachman.armour.upgrade.upgradeList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import chbachman.api.util.ArmourSlot;
import chbachman.armour.upgrade.UpgradeProtective;

public class UpgradeUndead extends UpgradeProtective{

	public UpgradeUndead() {
		super("undead", 75);
	}

	@Override
	public boolean shouldDefend(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, ArmourSlot slot) {
		
		Entity entity = source.getSourceOfDamage();
		
		if(entity == null){
			return false;
		}
		
		if(!(entity instanceof EntityMob)){
			return false;
		}
		
		EntityMob entityMob = (EntityMob) entity;
		
		return entityMob.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
	}
	
	public int getEnergyPerDamage(ItemStack stack){
		return 100;
	}
	
	

}
