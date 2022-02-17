package Sncwy.WitcheryUpdated.util.handlers;

import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {

	public static void registerSmelting() { //adds below recipe to vanilla minecraft furnace smelting recipes
		GameRegistry.addSmelting(ModItems.IS_RAW_PORKCHOP, new ItemStack(ModItems.IS_COOKED_PORKCHOP), 0);
	}
}
