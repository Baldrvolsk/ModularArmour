package chbachman.api;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import chbachman.armour.crafting.Recipe;
import chbachman.armour.reference.ArmourSlot;
import chbachman.armour.upgrade.UpgradeList;
import cofh.lib.util.helpers.StringHelper;
/**
 * Default implementation of IUpgrade. Use this or make your own. 
 * @author chbachman
 *
 */
public abstract class Upgrade implements IArmourUpgrade , Comparable<Upgrade>{
    
    protected final int id;
    protected String name;
    
    public int amount = 1;
    
    public Upgrade(String name) {
        this.id = this.getNextAvailableId();
        
        this.name = name;
        
        UpgradeList.list.add(this);
    }
    
    private int getNextAvailableId() {
        return UpgradeList.list.size();
    }
    
    public String getName() {
        
        if(this.amount > 1){
            return StringHelper.localize(this.getUnlocalizedName()) + "(" + this.amount + ")";
        }
        
        return StringHelper.localize(this.getUnlocalizedName());
    }
    
    public String getInformation(){
    	return this.getLocalizationString(this.name) + ".information";
    }
    
    public String getUnlocalizedName() {
        return this.getLocalizationString(this.name) + ".name";
    }
    
    protected String getLocalizationString(String name) {
        return "upgrade.chbachman." + StringHelper.camelCase(name).replace(" ", "");
    }
    
    public int getId() {
        return this.id;
    }
    
    @Override
    public int compareTo(Upgrade upgrade) {
        return this.getName().compareTo(upgrade.getName());
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    // Api for Upgrades here
    public boolean isCompatible(int slot) {
        return this.isCompatible(ArmourSlot.getArmourSlot(slot));
    }
    
    public boolean isCompatible(ArmourSlot slot){
    	return true;
    }
    
    public int getArmourDisplay() {
        return 0;
    }
    
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, ArmourSlot slot) {
        return null;
    }
    
    public List<String> getDependencies() {
        return null;
    }
    
    public void onUpgradeAddition(IModularItem item, ItemStack stack) {
        
    }
    
    public void onEquip(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        
    }
    
    public int onTick(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        return 0;
    }
    
    public void onDequip(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        
    }
    
    public void onNoEnergy(World world, EntityPlayer player, ItemStack stack, ArmourSlot slot) {
        
    }
    
    public boolean isRepeatable() {
        return false;
    }
    
}
