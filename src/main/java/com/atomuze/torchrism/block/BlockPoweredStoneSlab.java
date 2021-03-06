package com.atomuze.torchrism.block;

import java.util.Random;

import com.atomuze.torchrism.Torchrism;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BlockPoweredStoneSlab extends BlockSlab {
	protected String name;
	public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);

	public BlockPoweredStoneSlab(String name) {
		super(Material.ROCK);
		setUnlocalizedName(Torchrism.MODID + "." + name);
		setRegistryName(name);
		setHardness(3f);
		setResistance(4f);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(Torchrism.creativeTab);
		this.useNeighborBrightness = !this.isDouble();
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
		if (!this.isDouble())
			state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
		this.name = name;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModBlocks.poweredStoneSlab;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		 return new ItemStack(ModBlocks.poweredStoneSlab, 1, this.damageDropped(state));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
		if (!this.isDouble())
			state = state.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP)
			meta |= 8;
		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (!this.isDouble())
			return new BlockStateContainer(this, new IProperty[] { VARIANT, HALF });
		else
			return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return Variant.DEFAULT;
	}

	public static enum Variant implements IStringSerializable {
		DEFAULT;

		@Override
		public String getName() {
			return "default";
		}
	}

	public void registerItemModel(Item itemBlock) {
		Torchrism.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	public void registerModel(Item itemBlock) {
		Torchrism.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
}