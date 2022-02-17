package Sncwy.WitcheryUpdated.items.seeds;

import java.awt.Event;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.items.ItemBase;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemWaterArtichokeSeeds extends ItemBase implements IHasModel, IPlantable {

	public ItemWaterArtichokeSeeds(String name) {
		super(name);
	}

	@Override
	public void registerModels() {
		WitcheryMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) { //onItemUse uses the position of the block clicked on
																										 //but you can't right-click on water because it's not a 
		RayTraceResult blockTrace = this.rayTrace(worldIn, player, true);								 //solid so onItemRightClick is used and ray tracing is 
		ItemStack stack = player.getHeldItem(hand);														 //used to find BlockPos
		if (blockTrace.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos pos = blockTrace.getBlockPos();
			
			if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getBlockState(pos).getBlock() != Blocks.FLOWING_WATER 
				&& !stack.isEmpty() && worldIn.isAirBlock(pos.up()) && worldIn.isBlockModifiable(player, pos))
			{
				worldIn.setBlockState(pos.up(), ModBlocks.WATER_ARTICHOKE_PLANT.getDefaultState());
				stack.shrink(1);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
		}	
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return ModBlocks.WATER_ARTICHOKE_PLANT.getDefaultState();
	}
}
