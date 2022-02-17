package Sncwy.WitcheryUpdated.worldGen;

import java.util.Random;

import Sncwy.WitcheryUpdated.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

public class WorldGenTreesBase extends WorldGenAbstractTree {
	
	private IBlockState blockStateWood;// = ModBlocks.ALDER_LOG.getDefaultState();
    private IBlockState blockStateLeaves;// = ModBlocks.ALDER_LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private final int minTreeHeight = 4;

	public WorldGenTreesBase(boolean notify, Block log, Block leaves) {
		super(notify);
		blockStateWood = log.getDefaultState();
		blockStateLeaves = leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) { //generates the tree. Trees are made of multiple logs and leaves
		int minHeight = rand.nextInt(4) + minTreeHeight;	
		
		if (pos.getY() >= 1 && pos.getY() + minHeight + 1 <= worldIn.getHeight()) {
			if (!isSuitableLocation(worldIn, pos, minHeight)) {
				return false;
			}
			else {
				IBlockState state = worldIn.getBlockState(pos.down());
				
				if (state.getBlock().canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, (IPlantable)Blocks.SAPLING) &&
					pos.getY() < worldIn.getHeight() - minHeight - 1)
				{
					state.getBlock().onPlantGrow(state, worldIn, pos.down(), pos);
					generateLeaves(worldIn, pos, minHeight, rand);
					generateTrunk(worldIn, pos, minHeight);
					return true;
				}
				else
					return false;
			}
		}
		else
			return false;
	}
	
	private void generateLeaves(World worldIn, BlockPos pos, int height, Random rand) { //spawns in leaf blocks with a bit of randomness
		for (int leafY = pos.getY() + height - 3; leafY <= pos.getY() + height; leafY++) {	
			int leafLayer = leafY - (pos.getY() + height);
			int leafLayerRadius = 1 - leafLayer / 2;
			
			for (int leafX = pos.getX() - leafLayerRadius; leafX <= pos.getX() + leafLayerRadius; leafX++) {
				int leafRelativeX = leafX - pos.getX();
				
				for (int leafZ = pos.getZ() - leafLayerRadius; leafZ <=pos.getZ() + leafLayerRadius; leafZ++) {
					int leafRelativeZ = leafZ - pos.getZ();
					
					if (Math.abs(leafRelativeX) != leafLayerRadius || Math.abs(leafRelativeZ) != leafLayerRadius ||
							rand.nextInt(2) != 0 && leafLayer != 0)
					{
						BlockPos blockPos = new BlockPos(leafX, leafY, leafZ);
						IBlockState state = worldIn.getBlockState(blockPos);
						
						if (state.getBlock().isAir(state, worldIn, blockPos) || state.getBlock().isLeaves(state, worldIn, blockPos)) {
							this.setBlockAndNotifyAdequately(worldIn, blockPos, blockStateLeaves);
						}
					}
				}
			}
		}
	}
	
	private void generateTrunk(World worldIn, BlockPos pos, int minHeight) { //spawns in logs on top of each other to make a trunk with a random height
		for (int height = 0; height < minHeight; height++) {
			IBlockState state = worldIn.getBlockState(pos.up(height));
			
			if (state.getBlock().isAir(state, worldIn, pos.up(height)) || state.getBlock().isLeaves(state, worldIn, pos.up(height))) {
				this.setBlockAndNotifyAdequately(worldIn, pos.up(height), blockStateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
			}
		}
	}
	
	private boolean isSuitableLocation(World worldIn, BlockPos pos, int minHeight) { //checks if sapling has enough space around it to generate a tree
		boolean isSuitableLocation = true;
		int extraSpaceNeeded = 1;
		
		for (int checkY = pos.getY(); checkY <= pos.getY() + minHeight + 1; checkY++) {
			if (checkY == pos.getY()) {
				extraSpaceNeeded = 0;
			}
			
			if (checkY >= pos.getY() + minHeight + 1) {
				extraSpaceNeeded = 2;
			}
			
			BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
			
			for (int checkX = pos.getX() - extraSpaceNeeded; checkX <= pos.getX() + extraSpaceNeeded && isSuitableLocation; checkX++) {
				for (int checkZ = pos.getZ() - extraSpaceNeeded; checkZ <= pos.getZ() + extraSpaceNeeded && isSuitableLocation; checkZ++) {
					isSuitableLocation = isReplaceable(worldIn, blockPos.setPos(checkX, checkY, checkZ));
				}
			}
		}
		return isSuitableLocation;
	}
}
