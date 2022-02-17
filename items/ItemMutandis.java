package Sncwy.WitcheryUpdated.items;

import java.util.Random;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class ItemMutandis extends ItemBase {
	
	public ItemMutandis(String name) {
		super(name);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, //when this item is right-clicked
										EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		if (state.getBlock() instanceof BlockBush && !(state.getBlock() instanceof BlockCrops) ){ //if it's right-clicked on a plant that's not a crop
			Random rand = new Random();
			switch(rand.nextInt(16)) { //spawn in one of the following
			case 0:
				worldIn.setBlockState(pos, ModBlocks.ALDER_SAPLING.getDefaultState());
				break;
			case 1:
				worldIn.setBlockState(pos, ModBlocks.ROWAN_SAPLING.getDefaultState());
				break;
			case 2:
				worldIn.setBlockState(pos, ModBlocks.HAWTHORN_SAPLING.getDefaultState());
				break;
			case 3:
				worldIn.setBlockState(pos, Blocks.WATERLILY.getDefaultState());
				break;
			case 4: 
				worldIn.setBlockState(pos, Blocks.RED_FLOWER.getDefaultState());
				break;
			case 5:
				worldIn.setBlockState(pos, Blocks.YELLOW_FLOWER.getDefaultState());
				break;
			case 6:
				worldIn.setBlockState(pos, Blocks.TALLGRASS.getStateFromMeta(BlockTallGrass.EnumType.GRASS.getMeta()));
				break;
			case 7:
				worldIn.setBlockState(pos, Blocks.TALLGRASS.getStateFromMeta(BlockTallGrass.EnumType.FERN.getMeta()));
				break;
			case 8:
				worldIn.setBlockState(pos, Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK).withProperty(BlockSapling.STAGE, 0));
				break;
			case 9:
				worldIn.setBlockState(pos, Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.BIRCH).withProperty(BlockSapling.STAGE, 0));
				break;
			case 10:
				worldIn.setBlockState(pos, Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.SPRUCE).withProperty(BlockSapling.STAGE, 0));
				break;
			case 11:
				worldIn.setBlockState(pos, Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.JUNGLE).withProperty(BlockSapling.STAGE, 0));
				break;
			case 12:
				worldIn.setBlockState(pos, Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.ACACIA).withProperty(BlockSapling.STAGE, 0));
				break;
			case 13:
				worldIn.setBlockState(pos, Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockSapling.STAGE, 0));
				break;
			case 14:
				worldIn.setBlockState(pos, Blocks.BROWN_MUSHROOM.getDefaultState());
				break;
			case 15:
				worldIn.setBlockState(pos, Blocks.RED_MUSHROOM.getDefaultState());
				break;
			default:
				worldIn.setBlockState(pos, Blocks.TALLGRASS.getDefaultState());
			}
			//spanish moss
			//glint weed
			//ember moss
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
