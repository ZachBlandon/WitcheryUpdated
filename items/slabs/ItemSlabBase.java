package Sncwy.WitcheryUpdated.items.slabs;

import Sncwy.WitcheryUpdated.blocks.slabs.BlockSlabBase;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSlabBase extends ItemBase { //failed attempt to create a single generic class

	private Block slab;
	private Block slab_upper;
	private Block slab_double;
	
	public ItemSlabBase(String name, Block slab, Block slab_upper, Block slab_double) {
		super(name);
		this.slab = slab;
		this.slab_upper = slab_upper;
		this.slab_double = slab_double;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
										EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		switch(facing) {
		case DOWN:		
			if (state.getBlock() == slab_upper) {
				worldIn.setBlockState(pos, slab_double.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else 
			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
				worldIn.setBlockState(pos.down(), slab_upper.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case EAST:
			if (worldIn.getBlockState(pos.east()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.east(), slab_upper.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.east(), slab.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.east()).getBlock() == slab || 
				worldIn.getBlockState(pos.east()).getBlock() == slab_upper)
			{
				worldIn.setBlockState(pos.east(), slab_double.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case NORTH:
			if (worldIn.getBlockState(pos.north()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.north(), slab_upper.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.north(), slab.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.north()).getBlock() == slab || 
				worldIn.getBlockState(pos.north()).getBlock() == slab_upper)
			{
				worldIn.setBlockState(pos.north(), slab_double.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case SOUTH:
			if (worldIn.getBlockState(pos.south()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.south(), slab_upper.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.south(), slab.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.south()).getBlock() == slab || 
				worldIn.getBlockState(pos.south()).getBlock() == slab_upper)
			{
				worldIn.setBlockState(pos.south(), slab_double.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case UP:
				if (state.getBlock() == slab) {
					worldIn.setBlockState(pos, slab_double.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else
				if (worldIn.getBlockState(pos.up()).getBlock() == slab_upper) {
					worldIn.setBlockState(pos.up(), slab_double.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else
				if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.up(), slab.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
			return EnumActionResult.FAIL;
		case WEST:
			if (worldIn.getBlockState(pos.west()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.west(), slab_upper.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.west(), slab.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.west()).getBlock() == slab || 
				worldIn.getBlockState(pos.west()).getBlock() == slab_upper)
			{
				worldIn.setBlockState(pos.west(), slab_double.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		default:
			return EnumActionResult.FAIL;
		}
	}
}
