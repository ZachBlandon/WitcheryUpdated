package Sncwy.WitcheryUpdated.blocks.devices;

import Sncwy.WitcheryUpdated.blocks.BlockBase;
import Sncwy.WitcheryUpdated.blocks.devices.witchesOven.TileEntityWitchesOven;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFumeFunnel extends BlockBase { //in progress will increase probability of secondary output of witches' ovens to the left, right, and bottom of it
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool CONNECTED = PropertyBool.create("connected");
	public static final PropertyDirection CONNECTED_DIRECTION = PropertyDirection.create("connected_direction");
	
	public BlockFumeFunnel(String name) {
		super(name, Material.IRON, 3.5F, 17.5F);
		setSoundType(SoundType.METAL);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(CONNECTED, false));
	}
	
	public static void setState(boolean connected, World world, BlockPos pos, BlockPos connected_pos) {
		
	}
	
	private EnumFacing checkConnection(EnumFacing facing, World world, BlockPos pos) {
		if (world.getTileEntity(pos.down()) instanceof TileEntityWitchesOven) {
			return EnumFacing.DOWN;
		}
		
		switch(facing){
		case NORTH:
		case SOUTH:
			if (world.getTileEntity(pos.east()) instanceof TileEntityWitchesOven) {
				return EnumFacing.EAST;
			}
			else
			if (world.getTileEntity(pos.west()) instanceof TileEntityWitchesOven) {
				return EnumFacing.WEST;
			}
			return null;
			
		case EAST:
		case WEST:
			if (world.getTileEntity(pos.north()) instanceof TileEntityWitchesOven) {
				return EnumFacing.NORTH;
			}
			else
			if (world.getTileEntity(pos.south()) instanceof TileEntityWitchesOven) {
				return EnumFacing.SOUTH;
			}
			return null;
			
		default:
			return null;
		}
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			IBlockState north = world.getBlockState(pos.north());
			IBlockState south = world.getBlockState(pos.south());
			IBlockState east = world.getBlockState(pos.east());
			IBlockState west = world.getBlockState(pos.west());
			EnumFacing face = (EnumFacing)state.getValue(FACING);
			EnumFacing connectedDirection = null;
			
			if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {
				face = EnumFacing.SOUTH;
				connectedDirection = this.checkConnection(EnumFacing.SOUTH, world, pos);
			}
			else
			if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {
				face = EnumFacing.NORTH;
				connectedDirection = this.checkConnection(EnumFacing.NORTH, world, pos);
			}
			else
			if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {
				face = EnumFacing.WEST;
				connectedDirection = this.checkConnection(EnumFacing.WEST, world, pos);
			}
			else
			if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {
				face = EnumFacing.EAST;
				connectedDirection = this.checkConnection(EnumFacing.EAST, world, pos);
			}
			
			if (connectedDirection != null) {
				world.setBlockState(pos, state.withProperty(FACING, face).withProperty(CONNECTED, true)
						.withProperty(CONNECTED_DIRECTION, connectedDirection), 2);
			}
			else {
				world.setBlockState(pos, state.withProperty(FACING, face).withProperty(CONNECTED, false), 2);
			}
		}
	}
}
