package Sncwy.WitcheryUpdated.blocks.devices.witchesOven.slots;

import javax.annotation.Nullable;

import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.TileEntityWitchesOven;
import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFuel extends SlotItemHandler {

	public SlotFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack) { //only allows items that are able to burn to be placed in this slot
		if (TileEntityWitchesOven.getItemBurnTime(stack)  > 0) {
			return true;
		}
		return false;
	}
}
