package Sncwy.WitcheryUpdated.blocks.devices.witchesOven;

import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.slots.SlotClayJar;
import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.slots.SlotFuel;
import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.slots.SlotOutput;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerWitchesOven extends Container { //handles updating data on both server and client

	private final TileEntityWitchesOven tileEntity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	
	private static final int INPUT1 = 0;
	private static final int INPUT2 = 1;
	private static final int FUEL = 2;
	private static final int OUTPUT1 = 3;
	private static final int OUTPUT2 = 4;
	
	public ContainerWitchesOven(InventoryPlayer playerIn, TileEntityWitchesOven tileEntity) { //adds slots from slots package
		this.tileEntity = tileEntity;
		IItemHandler inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.SOUTH);
		
		this.addSlotToContainer(new SlotItemHandler(inventory, INPUT1, 56, 17) {
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty(); //markDirty() tells server to update its data from client
			}
		});
		this.addSlotToContainer(new SlotClayJar(inventory, INPUT2, 83, 53) {
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		this.addSlotToContainer(new SlotFuel(inventory, FUEL, 56, 53) {			
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		this.addSlotToContainer(new SlotOutput(inventory, OUTPUT1, 116, 21) {
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		this.addSlotToContainer(new SlotOutput(inventory, OUTPUT2, 116, 53) {
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		
		for (int y = 0; y < 3; y++) { //above five slots are the oven itself
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(playerIn, x + y * 9 + 9, 8 + x * 18, 84 + y * 18)); //these slots are the players inventory
			}
		}
		
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(playerIn, x, 8 + x * 18, 142)); //these slots are the players hotbar
		}
	}
	
	private boolean isPrimaryFuel(Item item) { //see transferStackInSlot line 177
		if (item ==  Item.getItemFromBlock(Blocks.LOG)) {
			return false;
		}
		if (item == Item.getItemFromBlock(ModBlocks.ALDER_LOG)) {
			return false;
		}
		if (item == Item.getItemFromBlock(ModBlocks.HAWTHORN_LOG)) {
			return false;
		}
		if (item == Item.getItemFromBlock(ModBlocks.ROWAN_LOG)) {
			return false;
		}
		
		if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
			Block block = Block.getBlockFromItem(item);
		  
			if (block == Blocks.WOODEN_SLAB) {
				return true;
			}
			if (block.getDefaultState().getMaterial() == Material.WOOD) {
				return true;
			}
			if (block == Blocks.COAL_BLOCK) {
				return true;
			}
		}

		if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) {
			return true;
		}
		if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) {
			return true;
		}
		if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) {
			return true;
		}
		if (item == Items.STICK) {
			return true;
		}
		if (item == Items.COAL) {
			return true;
		}
		if (item == Items.LAVA_BUCKET) {
			return true;
		}
		if (item == Items.BLAZE_ROD) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public void detectAndSendChanges() { 
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != tileEntity.getCookTime()) {
				listener.sendWindowProperty(this, 2, tileEntity.getCookTime());
			}
			if(this.burnTime != tileEntity.getBurnTime()) {
				listener.sendWindowProperty(this, 0, tileEntity.getBurnTime());
			}
			if(this.totalCookTime != tileEntity.getTotalCookTime(tileEntity.getSmelting())) {
				listener.sendWindowProperty(this, 3, tileEntity.getTotalCookTime(tileEntity.getSmelting()));
			}
		}
		
		this.cookTime = tileEntity.getCookTime();
		this.burnTime = tileEntity.getBurnTime();
		this.totalCookTime = tileEntity.getTotalCookTime(tileEntity.getSmelting());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) { //used for the gui progress bar
		if (id == 2) {
			tileEntity.setCookTime(data);
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) { //when a player shift clicks on an item its transfered to most appropriate slot.
		ItemStack stack = ItemStack.EMPTY;								   //thats a default functionality on minecraft. This method implements that for this
		Slot slot = this.inventorySlots.get(index);						   //container. For example clay jars can only be shift clicked into the clay jar slot
																		   //and items that I've marked as primary fuel in isPrimaryFuel() get placed in the fuel
		if (slot != null && slot.getHasStack()) {						   //slot before checking if they can be placed in the input slot
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
			
			if (index < containerSlots) {
				if (!this.mergeItemStack(stack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			else
			if (stack1.getItem() == ModItems.CLAY_JAR) {
				if (!this.mergeItemStack(stack1, INPUT2, INPUT2 + 1, false)) {
					if (index >= containerSlots && index < containerSlots + 27) {
						if (!this.mergeItemStack(stack1, containerSlots + 27, containerSlots + 27 + 9, false)) {
							return ItemStack.EMPTY;
						}
					}
					else
					if (index >= containerSlots + 27 && index < containerSlots + 27 + 9) {
						if (!this.mergeItemStack(stack1, containerSlots, containerSlots + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
				}
			}
			else
			if (this.isPrimaryFuel(stack1.getItem())) {
				if (!this.mergeItemStack(stack1, FUEL, FUEL + 1, false)) {
					if (index >= containerSlots && index < containerSlots + 27) {
						if (!this.mergeItemStack(stack1, containerSlots + 27, containerSlots + 27 + 9, false)) {
							return ItemStack.EMPTY;
						}
					}
					else
					if (index >= containerSlots + 27 && index < containerSlots + 27 + 9) {
						if (!this.mergeItemStack(stack1, containerSlots, containerSlots + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
				}
			}
			else {
				if (!this.mergeItemStack(stack1, INPUT1, INPUT1 + 1, false)) {
					if (index >= containerSlots && index < containerSlots + 27) {
						if (!this.mergeItemStack(stack1, containerSlots + 27, containerSlots + 27 + 9, false)) {
							return ItemStack.EMPTY;
						}
					}
					else
					if (index >= containerSlots + 27 && index < containerSlots + 27 + 9) {
						if (!this.mergeItemStack(stack1, containerSlots, containerSlots + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
				}
			}
			
			if (stack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}
		
			if (stack1.getCount() == stack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(player, stack1);
		}
		return stack;
	}
}
