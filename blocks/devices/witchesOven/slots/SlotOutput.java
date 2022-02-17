package Sncwy.WitcheryUpdated.blocks.devices.witchesOven.slots;

import javax.annotation.Nullable;

import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotOutput extends SlotItemHandler {

	public SlotOutput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) { //doesn't allow players to place items in this slot
		return false;										//items can only be spawned in this slot. Example in ContainerWitchesOven 
	}
}
