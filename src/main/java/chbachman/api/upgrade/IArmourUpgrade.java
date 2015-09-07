package chbachman.api.upgrade;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import chbachman.api.util.ArmourSlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IArmourUpgrade extends IUpgrade {

    /**
     * Gets the Armour Display piece. All of these are added together to get
     * this piece of armour's display value.
     * 
     * @return Armour display, where 1 = half of a armour
     */
    int getArmourDisplay(EntityPlayer player, ItemStack stack, ArmourSlot slot);

    /**
     * Called to check what the armour should protect against.
     * 
     * @param attacker
     * @param armour
     * @param source
     * @param damage
     * @param armourSlot
     * @return ArmorProperties describing what the Armour should protect against
     *         when this upgrade is equiped.
     */
    ArmorProperties getProperties(EntityLivingBase attacker, ItemStack armour, DamageSource source, double damage, ArmourSlot armourSlot);

    /**
     * Gets the name of the texture to load onto the armour.
     * 
     * @param stack
     * @param slot
     * @return null if no change, otherwise name of texture.
     */
    String getArmourTexture(ItemStack stack, ArmourSlot slot);

    /**
     * Gets the Model for the Armour.
     * 
     * @return null if no change, otherwise the model to be used.
     */
    @SideOnly(Side.CLIENT)
    ModelBiped getArmourModel(EntityLivingBase entityLiving, ItemStack itemStack, int armourSlot);

    /**
     * Gets the name of the color to load onto the armour.
     * 
     * @param stack
     * @param slot
     * @return null if no change, otherwise name of the color.
     */
    String getArmourColor(ItemStack stack, ArmourSlot slot);

}
