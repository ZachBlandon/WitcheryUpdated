package Sncwy.WitcheryUpdated.blocks.devices.witchesOven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Sncwy.WitcheryUpdated.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WitchesOvenRecipes {

	private static final WitchesOvenRecipes INSTANCE = new WitchesOvenRecipes();
	private final HashMap<String, ArrayList<ItemStack>> smeltingList = new HashMap<String, ArrayList<ItemStack>>();
	
	public static WitchesOvenRecipes getInstance() {
		return INSTANCE;
	}
	
	private WitchesOvenRecipes() { //uses unlocalized name of items to make finding the correct recipe very quick
								   //stores an arraylist of the primary output and secondary output if it has one
		smeltingList.put("tile.sapling.oak", 
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), new ItemStack(ModItems.EXHALE_OF_THE_HORNED_ONE, 1))));
		
		smeltingList.put("tile.sapling.birch",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), new ItemStack(ModItems.BREATH_OF_THE_GODDESS, 1))));
		
		smeltingList.put("tile.sapling.spruce",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), new ItemStack(ModItems.HINT_OF_REBIRTH, 1))));
		
		smeltingList.put("tile.sapling.jungle",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.rowan_sapling",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), new ItemStack(ModItems.WHIFF_OF_MAGIC, 1))));
		
		smeltingList.put("tile.hawthorn_sapling",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), new ItemStack(ModItems.ODOUR_OF_PURITY, 1))));
		
		smeltingList.put("tile.alder_sapling",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), new ItemStack(ModItems.REEK_OF_MISFORTUNE, 1))));
		
		smeltingList.put("tile.sapling.acacia",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), ItemStack.EMPTY)));
		
		smeltingList.put("tile.sapling.big_oak",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.WOOD_ASH, 1), ItemStack.EMPTY)));
		
		smeltingList.put("tile.log.oak",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.log.birch",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.log.spruce",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.log.jungle",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.log.acacia",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.log.big_oak",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.rowan_log",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.hawthorn_log",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("tile.alder_log",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.beefRaw",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COOKED_BEEF, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.porkchopRaw",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COOKED_PORKCHOP, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.chickenRaw",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COOKED_CHICKEN, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.muttonRaw",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COOKED_MUTTON, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.rabbitRaw",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COOKED_RABBIT, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.fish.cod.raw",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COOKED_FISH, 1, 0), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.fish.salmon.raw",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Items.COOKED_FISH, 1, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
		
		smeltingList.put("item.is_raw_porkchop",
				new ArrayList<ItemStack>(Arrays.asList(new ItemStack(ModItems.IS_COOKED_PORKCHOP, 1), new ItemStack(ModItems.FOUL_FUME, 1))));
	}
	
	public ArrayList<ItemStack> getWitchesOvenResult(ItemStack stack) { //returns the arraylist with smelting results
		if (stack != null) {
			return smeltingList.get(stack.getItem().getUnlocalizedName(stack));
		}
		return null;
	}
}
