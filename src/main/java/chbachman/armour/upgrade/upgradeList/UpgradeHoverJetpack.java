package chbachman.armour.upgrade.upgradeList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import chbachman.api.item.IModularItem;
import chbachman.api.upgrade.Upgrade;
import chbachman.api.util.ArmourSlot;
import chbachman.armour.register.Vanilla;
import chbachman.armour.util.ConfigHelper;
import chbachman.armour.util.EnergyUtil;
import chbachman.armour.util.UpgradeUtil;

public class UpgradeHoverJetpack extends Upgrade {
    
    public UpgradeHoverJetpack() {
    	super("jetpack");
    }

    private int cost;
    
    @Override
    public void registerConfigOptions(){
    	cost = ConfigHelper.get(ConfigHelper.SPEED, this, "cost to fly each tick", 500);
    }

    @Override
    public boolean isCompatible(IModularItem item, ItemStack stack, int armourType) {
        return armourType == ArmourSlot.CHESTPLATE.id;
    }
    
    @Override
    public int onTick(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        
    	if(EnergyUtil.getEnergyStored(stack) != 0){
    		setFlying(player, true, world);
    	}else if(EnergyUtil.getEnergyStored(stack) == 0){
    		setFlying(player, false, world);
    	}
    	
        if (!UpgradeUtil.doesPlayerHaveUpgrade(player, Vanilla.calfShields) && player.capabilities.isFlying) {
            player.attackEntityFrom(DamageSource.onFire, 4F);
        }
        
        if (player.capabilities.isFlying) {
            return cost;
        } else {
            return 0;
        }
    }
    
    @Override
    public void onDequip(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        setFlying(player, false, world);
    }
    
    private void setFlying(EntityPlayer player, boolean bool, World world){
    	//if(!world.isRemote){
    	//	return;
    	//}
    	
    	if(bool){
    		if(player.capabilities.allowFlying == true){
    			return;
    		}
    		
    		player.capabilities.allowFlying = true;
            player.sendPlayerAbilities();
    	}else{
    		
    		if(player.capabilities.allowFlying == false){
    			return;
    		}
    		
    		player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
            player.sendPlayerAbilities();
    	}
    }
    
}
