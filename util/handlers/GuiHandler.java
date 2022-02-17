package Sncwy.WitcheryUpdated.util.handlers;

import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.ContainerWitchesOven;
import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.GuiWitchesOven;
import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.TileEntityWitchesOven;
import Sncwy.WitcheryUpdated.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler { //Not entirely sure what this does
												 //I know that servers can't display gui so they need to be split up
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { 
		if (ID == Reference.GUI_WITCHES_OVEN) {
			return new ContainerWitchesOven(player.inventory, (TileEntityWitchesOven)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_WITCHES_OVEN) {
			return new GuiWitchesOven(getServerGuiElement(ID, player, world, x, y, z), player.inventory,
					(TileEntityWitchesOven)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	
}
