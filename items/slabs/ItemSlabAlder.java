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

public class ItemSlabAlder extends ItemBase {

	public ItemSlabAlder(String name) {
		super(name);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, //just determines what happens when a slab
										EnumFacing facing, float hitX, float hitY, float hitZ)		   //is placed in every case. All slab blocks are
	{																								   //placed using a slab_item which spawns in the 
		ItemStack stack = player.getHeldItem(hand);													   //blocks using the following code
		IBlockState state = worldIn.getBlockState(pos);
		switch(facing) {
		case DOWN:		
			if (state.getBlock() == ModBlocks.ALDER_SLAB_UPPER) {
				worldIn.setBlockState(pos, ModBlocks.ALDER_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else 
			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
				worldIn.setBlockState(pos.down(), ModBlocks.ALDER_SLAB_UPPER.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case EAST:
			if (worldIn.getBlockState(pos.east()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.east(), ModBlocks.ALDER_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.east(), ModBlocks.ALDER_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.east()).getBlock() == ModBlocks.ALDER_SLAB || 
				worldIn.getBlockState(pos.east()).getBlock() == ModBlocks.ALDER_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.east(), ModBlocks.ALDER_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case NORTH:
			if (worldIn.getBlockState(pos.north()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.north(), ModBlocks.ALDER_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.north(), ModBlocks.ALDER_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.north()).getBlock() == ModBlocks.ALDER_SLAB || 
				worldIn.getBlockState(pos.north()).getBlock() == ModBlocks.ALDER_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.north(), ModBlocks.ALDER_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case SOUTH:
			if (worldIn.getBlockState(pos.south()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.south(), ModBlocks.ALDER_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.south(), ModBlocks.ALDER_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.south()).getBlock() == ModBlocks.ALDER_SLAB || 
				worldIn.getBlockState(pos.south()).getBlock() == ModBlocks.ALDER_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.south(), ModBlocks.ALDER_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		case UP:
				if (state.getBlock() == ModBlocks.ALDER_SLAB) {
					worldIn.setBlockState(pos, ModBlocks.ALDER_SLAB_DOUBLE.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else
				if (worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.ALDER_SLAB_UPPER) {
					worldIn.setBlockState(pos.up(), ModBlocks.ALDER_SLAB_DOUBLE.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else
				if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.up(), ModBlocks.ALDER_SLAB.getDefaultState());
					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
			return EnumActionResult.FAIL;
		case WEST:
			if (worldIn.getBlockState(pos.west()).getBlock() == Blocks.AIR) {
				if (hitY > 0.5F) {
					worldIn.setBlockState(pos.west(), ModBlocks.ALDER_SLAB_UPPER.getDefaultState());
				}
				else {
					worldIn.setBlockState(pos.west(), ModBlocks.ALDER_SLAB.getDefaultState());
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			if (worldIn.getBlockState(pos.west()).getBlock() == ModBlocks.ALDER_SLAB || 
				worldIn.getBlockState(pos.west()).getBlock() == ModBlocks.ALDER_SLAB_UPPER)
			{
				worldIn.setBlockState(pos.west(), ModBlocks.ALDER_SLAB_DOUBLE.getDefaultState());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		default:
			return EnumActionResult.FAIL;
		}
	}
}
