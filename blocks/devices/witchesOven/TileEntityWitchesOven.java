package Sncwy.WitcheryUpdated.blocks.devices.witchesOven;

import java.util.ArrayList;
import java.util.Random;

import Sncwy.WitcheryUpdated.blocks.trees.BlockSaplingBase;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityWitchesOven extends TileEntity implements ITickable { //BUGS: light doesnt update neighboring blocks
																			 //BUGS: shift click transfers remainder of item stack to and
	private ItemStackHandler inventory = new ItemStackHandler(5);			 //      from hotbar if entire stack doesnt fit in furnace
	private ItemStack smelting = ItemStack.EMPTY;
	private ItemStack fuel = ItemStack.EMPTY;

	private final int primaryInput = 0;
	private final int secondaryInput = 1;
	private final int fuelInput = 2;
	private final int primaryOutput = 3;
	private final int secondaryOutput = 4;

	private int burnTime = 0;
	private int cookTime = 0;
	private int totalCookTime;
	
	private int fumeChance = 8;

	public int getBurnTime() {
		return burnTime;
	}

	public int getCookTime() {
		return cookTime;
	}
	
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}
	
	public int getTotalCookTime(ItemStack input) { //sapling and logs smelt faster than default in the oven
		if (input.getItem() == Item.getItemFromBlock(Blocks.SAPLING) || input.getItem() == Item.getItemFromBlock(Blocks.LOG)) {
			return 180; 
		}
		return 200;
	}
	
	public ItemStack getSmelting() {
		return smelting;
	}
	
	public ItemStack getFuel() {
		return fuel;
	}
	
	private boolean canSmelt() {
		if (inventory.getStackInSlot(primaryInput).getItem().equals(Item.getItemFromBlock(Blocks.AIR)) //if there's nothing in the input 
				|| inventory.getStackInSlot(primaryInput).isEmpty()) {
			return false;
		} 
		else {
			ArrayList<ItemStack> result = WitchesOvenRecipes.getInstance().getWitchesOvenResult(inventory.getStackInSlot(primaryInput));
			if (result == null) { //if there's not a recipe associated with the item in WitchesOvenRecipes
				return false;
			} 
			else {
				ItemStack output1 = inventory.getStackInSlot(primaryOutput);
				ItemStack output2 = inventory.getStackInSlot(secondaryOutput);
				ItemStack input2 = inventory.getStackInSlot(secondaryInput);

				int res1 = output1.getCount() + result.get(0).getCount();
				int res2 = output2.getCount() + result.get(1).getCount();
				
				//the following checks if the output is empty or the item is the same as the result of smelting as well as
				//whether theres a clay jar in the oven and whether if the clay jar output is the empty or the same as smelting result
				if (input2.isEmpty()) {
					if (output1.isEmpty() || output1.isItemEqual(result.get(0))) {
						return res1 <= output1.getMaxStackSize() && res2 <= output2.getMaxStackSize();
					}
				} 
				else {
					if (output1.isEmpty()) {
						if (output2.isEmpty() || output2.isItemEqual(result.get(1)) || result.get(1).isEmpty()) {
							return res1 <= output1.getMaxStackSize() && res2 <= output2.getMaxStackSize();
						}
					} 
					else {
						if (output1.isItemEqual(result.get(0))) {
							if (output2.isEmpty() || output2.isItemEqual(result.get(1)) || result.get(1).isEmpty()) {
								return res1 <= output1.getMaxStackSize() && res2 <= output2.getMaxStackSize();
							}
						}
					}
				}
				return false;
			}
		}
	}

	public void smeltItem() { //adds the result(s) of smelting to the output and removes the item(s) used
		if (this.canSmelt()) {
			ItemStack input1 = inventory.getStackInSlot(primaryInput);
			ItemStack input2 = inventory.getStackInSlot(secondaryInput);
			ItemStack output1 = inventory.getStackInSlot(primaryOutput);
			ItemStack output2 = inventory.getStackInSlot(secondaryOutput);

			ArrayList<ItemStack> result = WitchesOvenRecipes.getInstance().getWitchesOvenResult(input1);

			if (!input2.isEmpty() && result.get(1) != ItemStack.EMPTY) { 
				Random rand = new Random();

				if ((rand.nextInt(fumeChance) + 1) % fumeChance == 0) { //theres only a chance that a second ouput is produced
					if (output2.isEmpty()) {
						inventory.setStackInSlot(secondaryOutput, result.get(1).copy());
					} 
					else {
						output2.grow(result.get(1).getCount());
					}
					input2.shrink(1);
				}
			}
			
			if (output1.isEmpty()) { //creates a new ItemStack of the result if output slot is empty
				inventory.setStackInSlot(primaryOutput, result.get(0).copy());
			} 
			else { //else increases existing ItemStack by 1
				output1.grow(result.get(0).getCount());
			}
			input1.shrink(1);
		}
	}
	
	public static int getItemBurnTime(ItemStack fuel) { //returns how many ticks a specific fuel will burn for
		if (fuel.isEmpty()) {
			return 0;
		}
		else {
			Item item = fuel.getItem();
  
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);
			  
				if (block == Blocks.WOODEN_SLAB) {
					return 150;
				}
				if (block.getDefaultState().getMaterial() == Material.WOOD) {
					return 300;
				}
				if (block == Blocks.COAL_BLOCK) {
					return 16000;
				}
			}
  
			if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) {
				return 200;
			}
			if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) {
				return 200;
			}
			if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) {
			  return 200;
			}
			if (item == Items.STICK) {
				return 100;
			}
			if (item == Items.COAL) {
				return 1600;
			}
			if (item == Items.LAVA_BUCKET) {
				return 20000;
			}
			if (item == Item.getItemFromBlock(Blocks.SAPLING)) {
				return 1000;
			}
			if (item == Item.getItemFromBlock(ModBlocks.ALDER_SAPLING)) {
				return 1000;
			}
			if (item == Item.getItemFromBlock(ModBlocks.ROWAN_SAPLING)) {
				return 1000;
			}
			if (item == Item.getItemFromBlock(ModBlocks.HAWTHORN_SAPLING)) {
				return 1000;
			}
			if (item == Items.BLAZE_ROD) {
				return 2400;
			}
  
			return ForgeEventFactory.getItemBurnTime(fuel);
		}
	}	
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityWitchesOven tileEntity) {
		return tileEntity.getBurnTime() > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) { //restores inventory from server data
		super.readFromNBT(compound);
		inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) { //stores inventory to server
		super.writeToNBT(compound);
		compound.setTag("Inventory", inventory.serializeNBT());
		
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag() { //happens when chuck containing tileEntity is loaded
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
		public void handleUpdateTag(NBTTagCompound tag) {
			this.readFromNBT(tag);
		}

	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) { 
		return oldState != newSate;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T)this.inventory;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void update() { //runs every tick
		boolean flag = false;
		//smelting = inventory.getStackInSlot(primaryInput); System.out.println(smelting.getItem().getUnlocalizedName(smelting));
		if (this.canSmelt())
		{	
			if (smelting != inventory.getStackInSlot(primaryInput)) { //if what was smelting last tick is not the same as whats smelting this tick
				cookTime = 0;										  //so if a player replaces whats smelting then set cookTime back to 0
				smelting = inventory.getStackInSlot(primaryInput);
				return;
			}
			
			ItemStack input = inventory.getStackInSlot(primaryInput);
			fuel = inventory.getStackInSlot(fuelInput);
			
			if (this.isBurning()) {
				if (++cookTime >= this.getTotalCookTime(input)) {
					this.smeltItem();
					cookTime = 0;
					flag = true;
				}
				
				if (--burnTime <= 0) { //if the fuel runs out
					if (!fuel.isEmpty()) { //if theres more fuel 
						burnTime = this.getItemBurnTime(fuel); //reset burnTime with new fuel
						fuel.shrink(1);
					}
					else { //else theres no more fuel
						inventory.setStackInSlot(fuelInput, ItemStack.EMPTY);
						BlockWitchesOven.setState(false, world, pos); //set state of oven to no longer burning
					}
					flag = true;
				}
			}
			else { //oven is not burning
				if (cookTime > 0) { //but something was in the middle of smelting
					cookTime -= 2; //start reducing cook progress
				}
				else {
					if (!fuel.isEmpty()) { //can smelt but not burning but has fuel
						burnTime = this.getItemBurnTime(fuel);
						fuel.shrink(1);
						BlockWitchesOven.setState(true, world, pos); //start smelting
					}
				}
				flag = true;
			}
		}
		else { //item can't smelt
			if (cookTime > 0) { //if something was smelting but was replaced with something that can't smelt
				cookTime = 0; //no chance to save their cook progress
			}
			if (burnTime > 0) { //for example the last item in input was smelted so now it's empty so it can't smelt
				if  (--burnTime <= 0) { //continues burning until fuel is used up
					BlockWitchesOven.setState(false, world, pos); //then set to not burning
					flag = true;
				}
			}
		}
		
		if (flag) { //if an item was changed
			this.markDirty(); //update server with new items / new item locations / etc...
		}
	} 
}
