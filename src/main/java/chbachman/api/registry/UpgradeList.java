package chbachman.api.registry;

import java.util.HashMap;
import java.util.Iterator;

import chbachman.api.upgrade.IUpgrade;
import chbachman.api.upgrade.Upgrade;
import chbachman.armour.ModularArmour;

@SuppressWarnings("serial")
public class UpgradeList extends HashMap<String, IUpgrade> implements Iterable<IUpgrade>{
    
    public IUpgrade get(Class<? extends Upgrade> clazz) {
        for (IUpgrade upgrade : this.values()) {
            if (upgrade.getClass() == clazz) {
                return upgrade;
            }
        }
        
        return null;
    }
    
    public IUpgrade put(IUpgrade upgrade) {
    	return this.put(upgrade.getBaseName(), upgrade);
    }

	@Override
	public Iterator<IUpgrade> iterator() {
		return this.values().iterator();
	}
    
}
