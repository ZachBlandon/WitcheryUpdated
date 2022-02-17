package Sncwy.WitcheryUpdated.proxy;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;

public class CommonProxy { //honestly I'd be lying if I knew why the Proxy classes are set up like this
	
	public void registerItemRenderer(Item item, int meta, String id) {} //does nothing serverside

	public void setGraphicsLevel(BlockLeaves leaf, boolean fancyEnabled) {} //does nothing serverside
}
