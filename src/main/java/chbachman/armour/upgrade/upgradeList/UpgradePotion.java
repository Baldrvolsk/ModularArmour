package chbachman.armour.upgrade.upgradeList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import chbachman.api.IUpgrade;
import chbachman.api.Upgrade;
import chbachman.armour.register.Vanilla;
import chbachman.armour.util.ArmourSlot;

public class UpgradePotion extends Upgrade {

	public final PotionEffect effect;
	public final int energyCost;
	
	public UpgradePotion(String name, Potion effect, int level, int duration, int energyCost) {
		super(name);
		this.effect = new PotionEffect(effect.id, duration, level, false);
		this.energyCost = energyCost;
	}
	
	public int onTick(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
		player.addPotionEffect(effect);
		return energyCost;
    }
	
	public IUpgrade[] getDependencies() {
		
        return new IUpgrade[]{Vanilla.basePotion};
    }

}
