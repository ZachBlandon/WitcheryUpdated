package Sncwy.WitcheryUpdated.blocks.devices;

import Sncwy.WitcheryUpdated.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockWitchesCauldron extends BlockBase {
	
	public static final AxisAlignedBB WITCHES_CAULDRON_AABB = new AxisAlignedBB(0, 0, 0, 1D, 0.8125D, 1D); //shorter than a normal block so it
																										   //needs its own bounding box
	public BlockWitchesCauldron(String name) {
		super(name, Material.IRON, 2, 10);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) { //block does not cover entire 1x1x1 space so this tells the renderer
		return false;								 //to render the top of the block under this one to prevent clipping
	}
	
	@Override
	public boolean isFullCube(IBlockState state) { //marks that block is not the size of a full block
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return WITCHES_CAULDRON_AABB;
	}
}
