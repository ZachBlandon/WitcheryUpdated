package Sncwy.WitcheryUpdated.init;

import java.util.ArrayList;
import java.util.List;

import Sncwy.WitcheryUpdated.blocks.BlockBase;
import Sncwy.WitcheryUpdated.blocks.BlockPlankBase;
import Sncwy.WitcheryUpdated.blocks.BlockStairBase;
import Sncwy.WitcheryUpdated.blocks.crops.BlockBelladonnaPlant;
import Sncwy.WitcheryUpdated.blocks.crops.BlockMandrakePlant;
import Sncwy.WitcheryUpdated.blocks.crops.BlockSnowbellPlant;
import Sncwy.WitcheryUpdated.blocks.crops.BlockWaterArtichokePlant;
import Sncwy.WitcheryUpdated.blocks.devices.BlockFumeFunnel;
import Sncwy.WitcheryUpdated.blocks.devices.BlockWitchesCauldron;
import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.BlockWitchesOven;
import Sncwy.WitcheryUpdated.blocks.slabs.BlockSlabBase;
import Sncwy.WitcheryUpdated.blocks.slabs.BlockSlabDoubleBase;
import Sncwy.WitcheryUpdated.blocks.slabs.BlockSlabUpperBase;
import Sncwy.WitcheryUpdated.blocks.trees.BlockLeavesAlder;
import Sncwy.WitcheryUpdated.blocks.trees.BlockLeavesHawthorn;
import Sncwy.WitcheryUpdated.blocks.trees.BlockLeavesRowan;
import Sncwy.WitcheryUpdated.blocks.trees.BlockLogBase;
import Sncwy.WitcheryUpdated.blocks.trees.BlockSaplingBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks { //declaration of all Blocks I've created
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
	public static final Block WITCHES_CAULDRON = new BlockWitchesCauldron("witches_cauldron");
	public static final Block ALDER_LOG = new BlockLogBase("alder_log");
	public static final Block ALDER_LEAVES= new BlockLeavesAlder("alder_leaves");
	public static final Block ALDER_PLANKS = new BlockPlankBase("alder_planks");
	public static final Block ALDER_STAIRS = new BlockStairBase("alder_stairs", ALDER_PLANKS.getDefaultState());
	public static final Block ALDER_SLAB = new BlockSlabBase("alder_slab", ModItems.ALDER_SLAB_ITEM);
	public static final Block ALDER_SLAB_UPPER = new BlockSlabUpperBase("alder_slab_upper", ModItems.ALDER_SLAB_ITEM);
	public static final Block ALDER_SLAB_DOUBLE = new BlockSlabDoubleBase("alder_slab_double", ModItems.ALDER_SLAB_ITEM);
	public static final Block ROWAN_LOG = new BlockLogBase("rowan_log");
	public static final Block ROWAN_LEAVES = new BlockLeavesRowan("rowan_leaves");
	public static final Block ROWAN_PLANKS = new BlockPlankBase("rowan_planks");
	public static final Block ROWAN_STAIRS = new BlockStairBase("rowan_stairs", ROWAN_PLANKS.getDefaultState());
	public static final Block ROWAN_SLAB = new BlockSlabBase("rowan_slab", ModItems.ROWAN_SLAB_ITEM);
	public static final Block ROWAN_SLAB_UPPER = new BlockSlabUpperBase("rowan_slab_upper", ModItems.ROWAN_SLAB_ITEM);
	public static final Block ROWAN_SLAB_DOUBLE = new BlockSlabDoubleBase("rowan_slab_double", ModItems.ROWAN_SLAB_ITEM);
	public static final Block HAWTHORN_LOG = new BlockLogBase("hawthorn_log");
	public static final Block HAWTHORN_LEAVES = new BlockLeavesHawthorn("hawthorn_leaves");
	public static final Block HAWTHORN_PLANKS = new BlockPlankBase("hawthorn_planks");
	public static final Block HAWTHORN_STAIRS = new BlockStairBase("hawthorn_stairs", HAWTHORN_PLANKS.getDefaultState());
	public static final Block HAWTHORN_SLAB = new BlockSlabBase("hawthorn_slab", ModItems.HAWTHORN_SLAB_ITEM);
	public static final Block HAWTHORN_SLAB_UPPER = new BlockSlabUpperBase("hawthorn_slab_upper", ModItems.HAWTHORN_SLAB_ITEM);
	public static final Block HAWTHORN_SLAB_DOUBLE = new BlockSlabDoubleBase("hawthorn_slab_double", ModItems.HAWTHORN_SLAB_ITEM);
	public static final Block WITCHES_OVEN = new BlockWitchesOven("witches_oven");
	//public static final Block FUME_FUNNEL = new BlockFumeFunnel("fume_funnel");

	//Plants
	public static final Block MANDRAKE_PLANT = new BlockMandrakePlant("mandrake_plant");
	public static final Block BELLADONNA_PLANT = new BlockBelladonnaPlant("belladonna_plant");
	public static final Block SNOWBELL_PLANT = new BlockSnowbellPlant("snowbell_plant");
	public static final Block WATER_ARTICHOKE_PLANT = new BlockWaterArtichokePlant("water_artichoke_plant");
	
	//Saplings
	public static final Block ALDER_SAPLING = new BlockSaplingBase("alder_sapling", ALDER_LOG, ALDER_LEAVES);
	public static final Block ROWAN_SAPLING = new BlockSaplingBase("rowan_sapling", ROWAN_LOG, ROWAN_LEAVES);
	public static final Block HAWTHORN_SAPLING = new BlockSaplingBase("hawthorn_sapling", HAWTHORN_LOG, HAWTHORN_LEAVES);
}
