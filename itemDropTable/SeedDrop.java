package Sncwy.WitcheryUpdated.itemDropTable;

import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber
public class SeedDrop { //adds a chance for vanilla minecraft tall grass block to drop my custom seeds
	
	public static void init() {
		MinecraftForge.addGrassSeed(new ItemStack(ModItems.MANDRAKE_SEEDS), 4);
		MinecraftForge.addGrassSeed(new ItemStack(ModItems.BELLADONNA_SEEDS), 4);
		MinecraftForge.addGrassSeed(new ItemStack(ModItems.SNOWBELL_SEEDS), 4);
		MinecraftForge.addGrassSeed(new ItemStack(ModItems.WATER_ARTICHOKE_SEEDS), 4);
	}
}
