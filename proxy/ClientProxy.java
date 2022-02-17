package Sncwy.WitcheryUpdated.proxy;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy{
	
	public void registerItemRenderer(Item item, int meta, String id) { //not entirely sure what this does, I assume it links Items with their JSON
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	public void setGraphicsLevel(BlockLeaves leaf, boolean fancyEnabled) { //allows leaf graphics to be set to fancy or fast in menu
		leaf.setGraphicsLevel(fancyEnabled);
	}
}
