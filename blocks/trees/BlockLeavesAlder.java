package Sncwy.WitcheryUpdated.blocks.trees;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.init.ModItems;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLeavesAlder extends BlockLeaves implements IHasModel {
	
    public BlockLeavesAlder(String name) {
		setCreativeTab(WitcheryMain.witcherytab);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
		WitcheryMain.proxy.setGraphicsLevel(this, true);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) { //leaves randomly drop apples when they decay
        if (worldIn.rand.nextInt(chance) == 0) {
            spawnAsEntity(worldIn, pos, new ItemStack(Items.APPLE));
        }
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { //leaves drop saplings for their tree not leaf blocks
        return Item.getItemFromBlock(ModBlocks.ALDER_SAPLING);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this));
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) { //silk touch is an enchantment players can get for their pickaxe that
        return new ItemStack(Item.getItemFromBlock(this));	  //causes items to drop themselves instead of a different item
    }														  //for example causes leaves to drop leaf blocks instead of saplings

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (!state.getValue(DECAYABLE).booleanValue()) {
            i |= 4;
        }

        if (state.getValue(CHECK_DECAY).booleanValue()) {
            i |= 8;
        }
        
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) { //leaves will also drop leaf blocks if harvested with shears
            player.addStat(StatList.getBlockStats(this));
        }
        else {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public EnumType getWoodType(int meta) {
        return null;
    }

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) { //harvested with shears
		return NonNullList.withSize(1, new ItemStack(this));
	}

	@Override
	public void registerModels() { //from IHasModel interface. Marks blocks for rendering
		WitcheryMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
