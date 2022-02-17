package Sncwy.WitcheryUpdated.items;

import Sncwy.WitcheryUpdated.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAnointingPaste extends ItemBase {
	
	public ItemAnointingPaste(String name) {
		super(name);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, //when right-clicking with this item in hand
			EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		if (state.getBlock() == Blocks.CAULDRON) { //if a vanilla minecraft cauldron was right-clicked on 
			worldIn.setBlockState(pos, ModBlocks.WITCHES_CAULDRON.getDefaultState()); //spawn a witches' cauldron in that location
			stack.shrink(1);
			double x = pos.getX() + 0.5;					//play some sounds and spawn some particles
			double y = pos.getY() + 1;
			double z = pos.getZ() + 0.5;
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 0, 0.01D, 0, 0);
			worldIn.playSound(x, y, z, new SoundEvent(new ResourceLocation("block.lava.extinguish")), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x, y, z, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x + 0.5, y, z, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x, y, z + 0.5, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x - 0.5, y, z, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x, y, z - 0.5, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x, y + 0.5, z, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x + 0.5, y + 0.5, z, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x, y + 0.5, z + 0.5, 0, 0.01D, 0, 0);
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, x + 0.5, y + 0.5, z + 0.5, 0, 0.01D, 0, 0);
			worldIn.playSound(x, y, z, new SoundEvent(new ResourceLocation("entity.player.levelup")), SoundCategory.MASTER, 1.0F, 0.4F, false);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
