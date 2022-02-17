package Sncwy.WitcheryUpdated.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class BlockPlankBase extends BlockBase { //base class for planks I've created

	public BlockPlankBase(String name) {
		super(name, Material.WOOD, 2, 15);
		//OreDictionary.registerOre("plankWood", this);
	}

}
