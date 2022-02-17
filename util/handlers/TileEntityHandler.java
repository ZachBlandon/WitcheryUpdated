package Sncwy.WitcheryUpdated.util.handlers;

import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.TileEntityWitchesOven;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.util.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void rergisterTileEntites() { //registers tileEntities. Just noticed there's a typo oops
		GameRegistry.registerTileEntity(TileEntityWitchesOven.class, ModBlocks.WITCHES_OVEN.getRegistryName());
	}
}
