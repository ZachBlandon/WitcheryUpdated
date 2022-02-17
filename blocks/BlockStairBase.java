package Sncwy.WitcheryUpdated.blocks;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.init.ModItems;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockStairBase extends BlockStairs implements IHasModel { //base class for stairs I've created

	public BlockStairBase(String name, IBlockState modelState) {
		super(modelState);
		setCreativeTab(WitcheryMain.witcherytab);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setHardness(2.0F);
		this.setResistance(15.0F);
		this.setHarvestLevel("axe", 1);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		WitcheryMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
