package Sncwy.WitcheryUpdated.init;

import java.util.ArrayList;
import java.util.List;

import Sncwy.WitcheryUpdated.items.ItemAnointingPaste;
import Sncwy.WitcheryUpdated.items.ItemBase;
import Sncwy.WitcheryUpdated.items.ItemMutandis;
import Sncwy.WitcheryUpdated.items.food.FoodBase;
import Sncwy.WitcheryUpdated.items.seeds.ItemBelladonnaSeeds;
import Sncwy.WitcheryUpdated.items.seeds.ItemMandrakeSeeds;
import Sncwy.WitcheryUpdated.items.seeds.ItemSnowbellSeeds;
import Sncwy.WitcheryUpdated.items.seeds.ItemWaterArtichokeSeeds;
import Sncwy.WitcheryUpdated.items.slabs.ItemSlabAlder;
import Sncwy.WitcheryUpdated.items.slabs.ItemSlabBase;
import Sncwy.WitcheryUpdated.items.slabs.ItemSlabHawthorn;
import Sncwy.WitcheryUpdated.items.slabs.ItemSlabRowan;
import net.minecraft.item.Item;


public class ModItems { //declaration of all Items I've created
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Material
	public static final Item ANOINTING_PASTE = new ItemAnointingPaste("anointing_paste");
	public static final Item MUTANDIS = new ItemMutandis("mutandis");
	//public static final Item ALDER_SLAB_ITEM = new ItemSlabBase("alder_slab_item", ModBlocks.ALDER_SLAB, ModBlocks.ALDER_SLAB_UPPER, ModBlocks.ALDER_SLAB_DOUBLE);
	public static final Item ALDER_SLAB_ITEM = new ItemSlabAlder("alder_slab_item");
	public static final Item ROWAN_SLAB_ITEM = new ItemSlabRowan("rowan_slab_item");
	public static final Item HAWTHORN_SLAB_ITEM = new ItemSlabHawthorn("hawthorn_slab_item");
	public static final Item SOFT_CLAY_JAR = new ItemBase("soft_clay_jar");
	public static final Item CLAY_JAR = new ItemBase("clay_jar");
	public static final Item WOOD_ASH = new ItemBase("wood_ash");
	
	//Fumes
	public static final Item EXHALE_OF_THE_HORNED_ONE = new ItemBase("exhale_of_the_horned_one");
	public static final Item BREATH_OF_THE_GODDESS = new ItemBase("breath_of_the_goddess");
	public static final Item HINT_OF_REBIRTH = new ItemBase("hint_of_rebirth");
	public static final Item WHIFF_OF_MAGIC = new ItemBase("whiff_of_magic");
	public static final Item ODOUR_OF_PURITY = new ItemBase("odour_of_purity");
	public static final Item REEK_OF_MISFORTUNE = new ItemBase("reek_of_misfortune");
	public static final Item FOUL_FUME = new ItemBase("foul_fume");
	
	//Seeds
	public static final Item MANDRAKE_SEEDS = new ItemMandrakeSeeds("mandrake_seeds");	
	public static final Item BELLADONNA_SEEDS = new ItemBelladonnaSeeds("belladonna_seeds");
	public static final Item SNOWBELL_SEEDS = new ItemSnowbellSeeds("snowbell_seeds");	
	public static final Item WATER_ARTICHOKE_SEEDS = new ItemWaterArtichokeSeeds("water_artichoke_seeds");
	
	//Crops
	public static final Item MANDRAKE_ROOT = new ItemBase("mandrake_root");
	public static final Item BELLADONNA_FLOWER = new ItemBase("belladonna_flower");
	public static final Item ICY_NEEDLE = new ItemBase("icy_needle");
	public static final Item WATER_ARTICHOKE_GLOBE = new ItemBase("water_artichoke_globe");
	
	//Foodstuff
	public static final Item ROWAN_BERRIES = new FoodBase("rowan_berries", 1, 1.2F, false);
	public static final Item IS_RAW_PORKCHOP = new FoodBase("is_raw_porkchop", 3, 1.8F, true);
	public static final Item IS_COOKED_PORKCHOP = new FoodBase("is_cooked_porkchop", 8, 12.8F, true);
}
