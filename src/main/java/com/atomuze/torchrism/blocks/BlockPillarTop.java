package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockPillarTop extends Block {
    public BlockPillarTop() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.STONE)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                .notSolid());

    }
//
//	@Override
//	public BlockAltarOutsidePedestal setCreativeTab(CreativeTabs tab) {
//		super.setCreativeTab(tab);
//		return this;
//	}
//
//	@Override
//	public Class<TileEntityOtherPedestal> getTileEntityClass() {
//		return TileEntityOtherPedestal.class;
//	}
//
//	@Nullable
//	@Override
//	public TileEntityOtherPedestal createTileEntity(World world, IBlockState state) {
//		return new TileEntityOtherPedestal();
//	}
//
//	@Override
//	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
//		if (!world.isRemote && !BlockAltarMainPedestal.crafting) {
//			TileEntityOtherPedestal tile = getTileEntity(world, pos);
//			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
//
//			if (player.getHeldItem(hand).isEmpty() || player.getHeldItem(hand).getItem() == itemHandler.getStackInSlot(0).getItem()) {
//				player.addItemStackToInventory(itemHandler.extractItem(0, 1, false));
//			} else if (itemHandler.getStackInSlot(0).isEmpty()){
//				ItemStack giveBackItem = player.getHeldItem(hand);
//				player.setHeldItem(hand, itemHandler.insertItem(0, new ItemStack(player.getHeldItem(hand).getItem(),  1, player.getHeldItem(hand).getMetadata()), false));
//				player.setHeldItem(hand, new ItemStack(giveBackItem.getItem(),  giveBackItem.getCount() - 1, giveBackItem.getMetadata()));
//			}
//			tile.markDirty();
//		}
//		return true;
//	}
//
//
//
//	@Override
//	public void breakBlock(World world, BlockPos pos, IBlockState state) {
//		TileEntityOtherPedestal tile = getTileEntity(world, pos);
//		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
//		ItemStack stack = itemHandler.getStackInSlot(0);
//		if (!stack.isEmpty()) {
//			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
//			world.spawnEntity(item);
//		}
//		super.breakBlock(world, pos, state);
//	}
//
//	@Override
//	public boolean isOpaqueCube(IBlockState state) {
//		return false;
//	}
//
//	@Override
//	public boolean isFullCube(IBlockState state) {
//		return false;
//	}
}
