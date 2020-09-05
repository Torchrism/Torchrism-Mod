package com.atomuze.torchrism.block.modblocks;

import java.util.Random;

import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPowerInfusedStone extends BlockBase {
	
	Random rand = new Random();
	
	public BlockPowerInfusedStone(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(4f);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(1f);

	}

	@Override
	public BlockPowerInfusedStone setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.poweredStone);
    }
	
	@Override
	public int quantityDropped(Random random)
    {
        return rand.nextInt(4) + 1;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
}
