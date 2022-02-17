package Sncwy.WitcheryUpdated.items.seeds;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.items.ItemBase;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemBelladonnaSeeds extends ItemBase implements IHasModel, IPlantable {

	public ItemBelladonnaSeeds(String name) {
		super(name);
	}

	@Override
	public void registerModels() {
		WitcheryMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, //when item is right-clicked spawn a crop block of this
										EnumFacing facing, float hitX, float hitY, float hitZ)		   //type on the location if the block clicked is able to 
	{																								   //be planted on
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, stack) 
			&& state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && 
			worldIn.isAirBlock(pos.up())) 
		{
			worldIn.setBlockState(pos.up(), ModBlocks.BELLADONNA_PLANT.getDefaultState());
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
		else
			return EnumActionResult.FAIL;
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return ModBlocks.BELLADONNA_PLANT.getDefaultState();
	}
}
