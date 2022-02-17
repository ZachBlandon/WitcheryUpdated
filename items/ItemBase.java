package Sncwy.WitcheryUpdated.items;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModItems;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel { //base class for all Items I've created
	
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(WitcheryMain.witcherytab);
		ModItems.ITEMS.add(this);
	}

	@Override	
	public void registerModels() {
		WitcheryMain.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
