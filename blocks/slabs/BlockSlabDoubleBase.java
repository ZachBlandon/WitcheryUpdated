package Sncwy.WitcheryUpdated.blocks.slabs;

import java.util.Random;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.blocks.BlockBase;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.init.ModItems;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSlabDoubleBase extends BlockBase { //probably none of this is needed except getItemDropped and quantityDropped
	
	public static final AxisAlignedBB BLOCK_SLAB_AABB = new AxisAlignedBB(0, 0, 0, 1D, 1D, 1D);
	private static Item slab_item;

	public BlockSlabDoubleBase(String name, Item slab_item) {
		super(name, Material.WOOD, 2, 15, false);
		this.slab_item = slab_item;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BLOCK_SLAB_AABB;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) { //returns a slab item instead of a double slab item
		return slab_item;
	}
	
	@Override
	public int quantityDropped(Random rand) { //double slabs are two slabs placed on top of each other to make a full block
		return 2;
	}

}