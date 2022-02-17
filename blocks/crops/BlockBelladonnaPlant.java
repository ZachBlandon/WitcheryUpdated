package Sncwy.WitcheryUpdated.blocks.crops;

import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBelladonnaPlant extends BlockCrops {
	
	private static final AxisAlignedBB[] belladonnaBB = new AxisAlignedBB[] { //Bounding box for each of the 8 plant growth stages
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D),
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D),
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D),
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D),
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D),
														new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	
	public BlockBelladonnaPlant(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, //when block is right clicked by a player
									EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote) { //worldIn.isRemote() returns true if code it being ran server side, so this checks if this is being run client side
			if (this.isMaxAge(state)) {
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.BELLADONNA_FLOWER, 1))); //drops the crop
				worldIn.setBlockState(pos, this.withAge(0)); //sets at new crop with growth 0 after crop is harvested																					//item if the crop 
				return true;																													//is max growth
			}
		}
		return false;
	}
	
	@Override
	protected Item getSeed() { //just returns the seed item this crop is grown from
		return ModItems.BELLADONNA_SEEDS; 
	}
	
	@Override
	protected Item getCrop() { //returns what this crop grows
		return ModItems.BELLADONNA_FLOWER;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { //returns the bounding box of the crop block itself
		return belladonnaBB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
	}
}