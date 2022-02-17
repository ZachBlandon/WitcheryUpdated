package Sncwy.WitcheryUpdated.items.food;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModItems;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel { //base class for food items I've created

	public FoodBase(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
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
