package Sncwy.WitcheryUpdated.blocks.devices.witchesOven;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import Sncwy.WitcheryUpdated.WitcheryMain;
import Sncwy.WitcheryUpdated.blocks.BlockBase;
import Sncwy.WitcheryUpdated.blocks.BlockTileEntityBase;
import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockWitchesOven extends BlockTileEntityBase<TileEntityWitchesOven> {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING; //allows the block to be placed with a specific direction
	public static final PropertyBool BURNING = PropertyBool.create("burning"); //allows the block to change textures. I haven't included any 
																			   //textures of JSONs since that's not Java
	public BlockWitchesOven(String name) {
		super(name, Material.IRON, 3.5F, 17.5F);
		setSoundType(SoundType.METAL);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(BURNING, false));
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) { //changes the block state to burning or not burning but keeps 
		IBlockState state = worldIn.getBlockState(pos);						   //the same facing and inventory
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		
		if (active) {
			worldIn.setBlockState(pos, ModBlocks.WITCHES_OVEN.getDefaultState()
					.withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
		}
		else {
			worldIn.setBlockState(pos, ModBlocks.WITCHES_OVEN.getDefaultState()
					.withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);
		}
		
		if (tileEntity != null) {
			tileEntity.validate();
			worldIn.setTileEntity(pos, tileEntity);
		}
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) { //returns extended block state that includes properties
		if (worldIn.getTileEntity(pos) instanceof TileEntityWitchesOven) {
			return this.getDefaultState().withProperty(FACING, state.getValue(FACING))
					.withProperty(BURNING, ((TileEntityWitchesOven)worldIn.getTileEntity(pos)).isBurning());
		}
		return state;
	} 
	
	/*
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { //changes the light level of this and surrounding blocks if it's
		TileEntity tileEntity = world.getTileEntity(pos);							//burning but currently won't update nearby blocks so only this
		if (tileEntity instanceof TileEntityWitchesOven) {							//block's light level will change
			TileEntityWitchesOven te = (TileEntityWitchesOven)world.getTileEntity(pos);
			if (te.isBurning()) {
				return 9;
			}
		}
		return 0;
	}
	*/
	
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) { //spawns flame particles on random ticks if oven is burning
		TileEntityWitchesOven tileEntity = (TileEntityWitchesOven)worldIn.getTileEntity(pos);	   //tileEntity.isBurning() is not the same as BURNING field
		if(tileEntity.isBurning()) {															   //BURNING is ultimately to use a different JSON while isBurning()
			if (rand.nextInt(3) != 0) {															   //depends on TileEntityWitchesOven
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				
				double offsetY = rand.nextDouble() % 0.25;
				double offsetZ = rand.nextDouble() % 0.5;
				
				switch (stateIn.getValue(FACING)) {
				case EAST:
					worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.9, y + 0.225 + offsetY, z + 0.25 + offsetZ, 0, 0, 0, 0);
					break;
				case NORTH:
					worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.25 + offsetZ, y + 0.225 + offsetY, z + 0.1, 0, 0, 0, 0);
					break;
				case SOUTH:
					worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.2 + offsetZ, y + 0.225 + offsetY, z + 0.9, 0, 0, 0, 0);
					break;
				case WEST:
					worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.1, y + 0.225 + offsetY, z + 0.2 + offsetZ, 0, 0, 0, 0);
					break;
				default:		
				}	
			}
		}
	}
	
	@Override
	public Class<TileEntityWitchesOven> getTileEntityClass() {
		return TileEntityWitchesOven.class;
	}
	
	@Nullable
	@Override
	public TileEntityWitchesOven createTileEntity(World world, IBlockState state) {
		return new TileEntityWitchesOven();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, //opens gui when players right clicks on oven block
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote) {
			playerIn.openGui(WitcheryMain.instance, Reference.GUI_WITCHES_OVEN, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) { //gets the Item that the oven drops
		return Item.getItemFromBlock(ModBlocks.WITCHES_OVEN);				  //only Item and ItemStack can be in player inventory, so all Blocks have
	}																		  //Items and when that Item is right-clicked a Block is placed at the location
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) { //gets ItemStack of oven
		return new ItemStack(ModBlocks.WITCHES_OVEN);						   //ItemStack allows multiple of the same Item to be in the same slot
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) { //checks the direction the player is facing and changes FACING
		if (!worldIn.isRemote) {											   //to opposite direction player is facing so that oven is always
			IBlockState north = worldIn.getBlockState(pos.north());			   //placed facing player, then spawns the block in the world
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState east = worldIn.getBlockState(pos.east());
			IBlockState west = worldIn.getBlockState(pos.west());
			EnumFacing face = (EnumFacing)state.getValue(FACING);
			
			if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {
				face = EnumFacing.SOUTH;
			}
			else
			if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {
				face = EnumFacing.NORTH;
			}
			else
			if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {
				face = EnumFacing.WEST;
			}
			else
			if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {
				face = EnumFacing.EAST;
			}
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		}
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, //similar to onBlockAdded, but
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand)										  //is used when something besides
	{																											  //player places the oven and doesn't
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());			  //actually place the block
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) { //places the block if its placed by
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);	//something besides player
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) { //handles what happens when block is destroyed
		IItemHandler itemHandler = getTileEntity(worldIn, pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.SOUTH);
		List<ItemStack> drops = new ArrayList<ItemStack>();
		
		for (int slot = 0; slot < itemHandler.getSlots(); slot++) { //drops all items that were in tileEntity inventory 
			if (!itemHandler.getStackInSlot(slot).isEmpty()) {
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemHandler.getStackInSlot(slot)));
			}
		}
		super.breakBlock(worldIn, pos, state); //destroys both block and tileEntity tied to block
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) { 
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {BURNING, FACING});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) { //gets block state (BURNING, FACING) from metadata stored in block
		EnumFacing facing = EnumFacing.getFront(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y) {
			facing = EnumFacing.SOUTH;
		}
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) { //gets metadata from block state
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) { //block does not cover entire 1x1x1 space so this tells the renderer
		return false;								 //to render the top of the block under this one to prevent clipping
	}
}
