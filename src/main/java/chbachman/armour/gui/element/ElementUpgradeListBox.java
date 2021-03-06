package chbachman.armour.gui.element;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import chbachman.api.nbt.helper.NBTHelper;
import chbachman.api.nbt.helper.NBTList;
import chbachman.api.upgrade.IUpgrade;
import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementListBox;
import cofh.lib.gui.element.listbox.IListBoxElement;
import cofh.lib.gui.element.listbox.ListBoxElementText;

public abstract class ElementUpgradeListBox extends ElementListBox {

    public ElementUpgradeListBox(GuiBase containerScreen, int x, int y, int width, int height) {
        super(containerScreen, x, y, width, height);
    }

    public void loadStack(ItemStack stack) {

        this.removeAll();

        NBTList<IUpgrade> textLines = NBTHelper.getNBTUpgradeList(stack);

        for (IUpgrade upgrade : textLines) {
            this.add(upgrade);
        }
    }

    public void add(IUpgrade upgrade) {
        this.add(new ListBoxElementUpgrade(upgrade));
    }

    @Override
    protected void onSelectionChanged(int newIndex, IListBoxElement newElement) {
        this.onUpgradeSelected(getUpgrade(newElement), newIndex);
    }

    public IUpgrade getSelectedUpgrade() {
        return getUpgrade(this.getSelectedElement());
    }

    private IUpgrade getUpgrade(IListBoxElement element) {
        return ((ListBoxElementUpgrade) element).upgrade;
    }

    private static class ListBoxElementUpgrade extends ListBoxElementText {

        private final IUpgrade upgrade;

        public ListBoxElementUpgrade(IUpgrade upgrade) {
            super(StatCollector.translateToLocal(upgrade.getName()));
            this.upgrade = upgrade;
        }

    }

    public abstract void onUpgradeSelected(IUpgrade upgrade, int index);

}
