package chbachman.armour.gui.element;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementBase;

public class ElementText extends ElementBase {

    String s;

    public ElementText(GuiBase gui, int posX, int posY) {
        super(gui, posX, posY);
    }

    public ElementText(GuiBase gui, int posX, int posY, int width, int height) {
        super(gui, posX, posY, width, height);
    }

    public ElementText setText(String toDisplay) {
        s = toDisplay;
        return this;
    }

    public String getText() {
        return s;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks) {

    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        this.getFontRenderer().drawString(s, this.posX, this.posY, 0xFFFFFFFF);
    }

}
