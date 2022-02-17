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

public class BlockSlabBase extends BlockBase {
	
	public static final AxisAlignedBB BLOCK_SLAB_AABB = new AxisAlignedBB(0, 0, 0, 1D, 0.5D, 1D); //a slab is minecraft is a half block
	private static Item slab_item;
	
	public BlockSlabBase(String name, Item slab_item) {
		super(name, Material.WOOD, 2, 15, false);
		this.slab_item = slab_item;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) { //probably not necessary here honestly since a slab covers the entire
		return false;								 //block beneath it
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BLOCK_SLAB_AABB;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return slab_item;
	}

}
