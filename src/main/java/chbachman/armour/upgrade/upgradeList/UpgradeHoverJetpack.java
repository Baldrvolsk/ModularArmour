package chbachman.armour.upgrade.upgradeList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import chbachman.api.Upgrade;
import chbachman.armour.reference.ArmourSlot;
import chbachman.armour.util.UpgradeUtil;

public class UpgradeHoverJetpack extends Upgrade {
    
    public UpgradeHoverJetpack() {
        super("Jetpack");
    }
    
    @Override
    public boolean isCompatible(ArmourSlot slot) {
        return slot == ArmourSlot.CHESTPLATE;
    }
    
    @Override
    public void onEquip(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        player.capabilities.allowFlying = true;
        player.sendPlayerAbilities();
    }
    
    @Override
    public int onTick(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        
        if (!UpgradeUtil.doesPlayerHaveUpgrade(player, "CalfShields") && player.capabilities.isFlying) {
            player.attackEntityFrom(DamageSource.onFire, 4F);
        }
        
        if (player.capabilities.isFlying) {
            return 500;
        } else {
            return 0;
        }
    }
    
    @Override
    public void onDequip(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        player.capabilities.allowFlying = false;
        player.capabilities.isFlying = false;
        player.sendPlayerAbilities();
    }
    
    @Override
    public void onNoEnergy(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        player.capabilities.allowFlying = false;
        player.capabilities.isFlying = false;
        player.sendPlayerAbilities();
    }
    
}
