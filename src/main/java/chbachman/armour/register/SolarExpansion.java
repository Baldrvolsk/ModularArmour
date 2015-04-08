package chbachman.armour.register;

import net.minecraft.block.Block;
import chbachman.api.upgrade.IUpgrade;
import chbachman.armour.crafting.Recipe;
import chbachman.armour.upgrade.upgradeList.UpgradeSolar;

public class SolarExpansion implements Module{

	public static IUpgrade solarLeadstone;
	public static IUpgrade solarHardened;
	public static IUpgrade solarRedstone;
	public static IUpgrade solarResonant;
	public static IUpgrade solarAdvanced;
	public static IUpgrade solarUltimate;

	@Override
	public void preInit() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

	@Override
	public void registerUpgrades() {
		
		solarLeadstone = new UpgradeSolar("solarLeadstone", 1);
		solarHardened = new UpgradeSolar("solarHardened", 8);
		solarRedstone = new UpgradeSolar("solarRedstone", 64);
		solarResonant = new UpgradeSolar("solarResonant", 512);
		solarAdvanced = new UpgradeSolar("solarAdvanced", 4096);
		solarUltimate = new UpgradeSolar("solarUltimate", 32768);
	}

	@Override
	public void registerUpgradeRecipes() {
		
		Recipe.recipeList.remove(Vanilla.solar);
		
		Block solarT1 = (Block) Block.blockRegistry.getObject("SolarExpansion:solarPanelLeadstone");
		Block solarT2 = (Block) Block.blockRegistry.getObject("SolarExpansion:solarPanelHardened");
		Block solarT3 = (Block) Block.blockRegistry.getObject("SolarExpansion:solarPanelRedstone");
		Block solarT4 = (Block) Block.blockRegistry.getObject("SolarExpansion:solarPanelResonant");
		Block solarT5 = (Block) Block.blockRegistry.getObject("SolarExpansion:solarPanelAdvanced");
		Block solarT6 = (Block) Block.blockRegistry.getObject("SolarExpansion:solarPanelUltimate");

		Recipe.recipeList.add(new Recipe(solarLeadstone, "A  ", "   ", "   ", 'A', solarT1));
		Recipe.recipeList.add(new Recipe(solarHardened, "A  ", "   ", "   ", 'A', solarT2));
		Recipe.recipeList.add(new Recipe(solarRedstone, "A  ", "   ", "   ", 'A', solarT3));
		Recipe.recipeList.add(new Recipe(solarResonant, "A  ", "   ", "   ", 'A', solarT4));
		Recipe.recipeList.add(new Recipe(solarAdvanced, "A  ", "   ", "   ", 'A', solarT5));
		Recipe.recipeList.add(new Recipe(solarUltimate, "A  ", "   ", "   ", 'A', solarT6));
	}

}
