package Sncwy.WitcheryUpdated.blocks;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.init.ModItems;
import Sncwy.WitcheryUpdated.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel{ //base class that most of the blocks that I created extend

	public BlockBase(String name, Material material, float hardness, float resistance) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(WitcheryMain.witcherytab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	public BlockBase(String name, Material material, float hardness, float resistance, boolean tab) { //same as default but doesn't add 
		super(material);																			  //block to inventory. Used in BlockSlabBase,
		setUnlocalizedName(name);																	  //BlockSlabDoubleBase, BlockSlabUpperBase
		setRegistryName(name);																		  //as only slab_item is added to inventory
		setHardness(hardness);																		  //see items.slabs.ItemSlabAlder
		setResistance(resistance);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		WitcheryMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
