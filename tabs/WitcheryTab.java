package Sncwy.WitcheryUpdated.tabs;

import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class WitcheryTab extends CreativeTabs { //creates a custom tab in creative mode inventory
	
	public WitcheryTab() { 
		super ("witcherytab");
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.ANOINTING_PASTE);
	}
}
