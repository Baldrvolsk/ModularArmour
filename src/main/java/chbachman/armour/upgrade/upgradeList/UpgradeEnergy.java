package chbachman.armour.upgrade.upgradeList;

import net.minecraft.item.ItemStack;
import chbachman.api.item.IModularItem;
import chbachman.api.upgrade.IUpgrade;
import chbachman.api.upgrade.Upgrade;
import chbachman.armour.items.armour.logic.RFUpgradeLogic;

public class UpgradeEnergy extends Upgrade {

    public final int maxTransfer;
    public final int capacity;

    private IUpgrade dependency = null;

    public UpgradeEnergy(String name, int maxTransfer, int capacity) {
        super(name);
        this.maxTransfer = maxTransfer;
        this.capacity = capacity;
    }

    @Override
    public void onUpgradeAddition(IModularItem armour, ItemStack stack) {

        RFUpgradeLogic holder = (RFUpgradeLogic) armour.getLogic();

        holder.setCapacity(stack, capacity);
        holder.setMaxTransfer(stack, maxTransfer);
    }

    @Override
    public boolean isCompatible(IModularItem item, ItemStack stack, int armorType) {
        return item.getLogic() instanceof RFUpgradeLogic;
    }

    @Override
    public IUpgrade[] getDependencies() {
        if (dependency == null) {
            return null;
        }

        return new IUpgrade[] { this.dependency };
    }

    public UpgradeEnergy setDependencies(IUpgrade upgrade) {
        this.dependency = upgrade;
        return this;
    }

}
