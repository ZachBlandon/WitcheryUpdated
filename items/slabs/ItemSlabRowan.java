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

public class ItemSlabRowan extends ItemBase { //see ItemSlabAlder

	public ItemSlabRowan(String name) {
		super(name);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
										EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		switch(facing) {
		case DOWN:		
			if (state.getBlock() == ModBlocks.ROWAN_SLAB_UPPER) {
				worldIn.setBlockState(pos, ModBlocks.ROWAN_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else 
			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
				worldIn.setBlockState(pos.down(), ModBlocks.ROWAN_SLAB_UPPER.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case EAST:
			if (worldIn.getBlockState(pos.east()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.east(), ModBlocks.ROWAN_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.east(), ModBlocks.ROWAN_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.east()).getBlock() == ModBlocks.ROWAN_SLAB || 
				worldIn.getBlockState(pos.east()).getBlock() == ModBlocks.ROWAN_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.east(), ModBlocks.ROWAN_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case NORTH:
			if (worldIn.getBlockState(pos.north()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.north(), ModBlocks.ROWAN_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.north(), ModBlocks.ROWAN_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.north()).getBlock() == ModBlocks.ROWAN_SLAB || 
				worldIn.getBlockState(pos.north()).getBlock() == ModBlocks.ROWAN_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.north(), ModBlocks.ROWAN_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case SOUTH:
			if (worldIn.getBlockState(pos.south()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.south(), ModBlocks.ROWAN_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.south(), ModBlocks.ROWAN_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.south()).getBlock() == ModBlocks.ROWAN_SLAB || 
				worldIn.getBlockState(pos.south()).getBlock() == ModBlocks.ROWAN_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.south(), ModBlocks.ROWAN_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case UP:
				if (state.getBlock() == ModBlocks.ROWAN_SLAB) {
					worldIn.setBlockState(pos, ModBlocks.ROWAN_SLAB_DOUBLE.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else
				if (worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.ROWAN_SLAB_UPPER) {
					worldIn.setBlockState(pos.up(), ModBlocks.ROWAN_SLAB_DOUBLE.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else
				if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.up(), ModBlocks.ROWAN_SLAB.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
			return EnumActionResult.FAIL;
		case WEST:
			if (worldIn.getBlockState(pos.west()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.west(), ModBlocks.ROWAN_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.west(), ModBlocks.ROWAN_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.west()).getBlock() == ModBlocks.ROWAN_SLAB || 
				worldIn.getBlockState(pos.west()).getBlock() == ModBlocks.ROWAN_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.west(), ModBlocks.ROWAN_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		default:
			return EnumActionResult.FAIL;
		}
	}
}
