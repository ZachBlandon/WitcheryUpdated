package Sncwy.WitcheryUpdated.blocks.devices.witchesOven.slots;

import javax.annotation.Nullable;

import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotClayJar extends SlotItemHandler {

	public SlotClayJar(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) { //only allows clay jar item to be placed in this slot
		if (stack.getItem() == ModItems.CLAY_JAR) {
			return true;
		}
		return false;
	}

}
