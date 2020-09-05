package com.atomuze.torchrism.block.modblocks;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.slab.BlockPoweredStoneSlab.Variant;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPoweredStone extends Block {

	public static final PropertyEnum<BlockPoweredStone.EnumType> VARIANT = PropertyEnum.<BlockPoweredStone.EnumType>create("variant", BlockPoweredStone.EnumType.class);
	private String name;

	public BlockPoweredStone(String name) {
		super(Material.ROCK);
		this.name = name;

		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPoweredStone.EnumType.DEFAULT));
		setUnlocalizedName(Torchrism.MODID + "." + name);
		setRegistryName(name);

		setHardness(3f);
		setResistance(4f);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(Torchrism.creativeTab);

	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return meta == BlockPoweredStone.EnumType.CHISELED.getMetadata() ? this.getDefaultState().withProperty(VARIANT, BlockPoweredStone.EnumType.CHISELED) : this.getDefaultState().withProperty(VARIANT, BlockPoweredStone.EnumType.DEFAULT);
	}

	public int damageDropped(IBlockState state) {
		BlockPoweredStone.EnumType BlockPoweredStone$enumtype = (BlockPoweredStone.EnumType) state.getValue(VARIANT);
		return BlockPoweredStone$enumtype.getMetadata();
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, BlockPoweredStone.EnumType.DEFAULT.getMetadata()));
		items.add(new ItemStack(this, 1, BlockPoweredStone.EnumType.CHISELED.getMetadata()));
	}

	/**
	 * Get the MapColor for this Block and the given BlockState
	 */
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.QUARTZ;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, BlockPoweredStone.EnumType.byMetadata(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((BlockPoweredStone.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	public static enum EnumType implements IStringSerializable {
		DEFAULT(0, "default", "default"),
		CHISELED(1, "chiseled", "chiseled");

		private static final BlockPoweredStone.EnumType[] META_LOOKUP = new BlockPoweredStone.EnumType[values().length];
		private final int meta;
		private final String serializedName;
		private final String unlocalizedName;

		private EnumType(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.serializedName = name;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata() {
			return this.meta;
		}

		public String toString() {
			return this.unlocalizedName;
		}

		public static BlockPoweredStone.EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName() {
			return this.serializedName;
		}

		static {
			for (BlockPoweredStone.EnumType BlockPoweredStone$enumtype : values()) {
				META_LOOKUP[BlockPoweredStone$enumtype.getMetadata()] = BlockPoweredStone$enumtype;
			}
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}

	public void registerItemModel(Item itemBlock, int meta) {
		String var;
		switch(meta){
		case 1 :
			var = BlockPoweredStone.EnumType.CHISELED.getName() + "_" + name;
			break;
		default:
			var = name;
		}
		Torchrism.proxy.registerItemRenderer(itemBlock, meta, var);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
}
