package Sncwy.WitcheryUpdated.blocks.trees;

import java.util.Random;
import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.init.ModItems;
import Sncwy.WitcheryUpdated.util.IHasModel;
import Sncwy.WitcheryUpdated.worldGen.WorldGenTreesBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.oredict.OreDictionary;

public class BlockSaplingBase extends BlockBush implements IGrowable, IHasModel {

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, //this was taken from vanilla minecraft saplings
					0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	protected Block log;
	protected Block leaves;
	
	public BlockSaplingBase(String name, Block log, Block leaves) {
		setCreativeTab(WitcheryMain.witcherytab);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		
		this.log = log;
		this.leaves = leaves;
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }
	
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                grow(worldIn, rand, pos, state);
            }
        }
    }    

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) { //bonemeal has a chance to grow a tree from a sapling
		return (double)worldIn.rand.nextFloat() < 0.45D;
	}
	
    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) //places a tree where the sapling is
    {																					  //see worldGen.WorldGenTreesBase
        if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        WorldGenerator worldgenerator = new WorldGenTreesBase(true, log, leaves);

        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

        worldgenerator.generate(worldIn, rand, pos);
    }

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) { //if not last stage increases STAGE else generates tree
		if (((Integer)state.getValue(STAGE)).intValue() == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
		else {
			generateTree(worldIn, pos, state, rand);
		}
	}
	
	@Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(STAGE).intValue() << 3;
        return i;
    }    

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {STAGE});
    }
    
    @Override
	public void registerModels() {
		WitcheryMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
