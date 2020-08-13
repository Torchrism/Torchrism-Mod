package com.atomuze.torchrism.block.torch_altar.block;

import javax.annotation.Nullable;

import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.torch_altar.CheckAltar;
import com.atomuze.torchrism.block.torch_altar.RecipeAltar;
import com.atomuze.torchrism.block.torch_altar.tileEntity.TileEntityMainPedestal;
import com.atomuze.torchrism.block.torch_altar.tileEntity.TileEntityPedestal;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockMainPedestal extends BlockTileEntity<TileEntityMainPedestal> {

	public static boolean crafting = false;
	public static EntityItem craftingResult;
	private static boolean keepInventory;
	World world;
	BlockPos pos;
	EnumFacing side;

	public BlockMainPedestal(Material material, String name) {
		super(material, name);
	}

	public BlockMainPedestal(String name) {

		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(5f);
		setLightLevel(1f);

	}

	@Override
	public BlockMainPedestal setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public Class<TileEntityMainPedestal> getTileEntityClass() {
		return TileEntityMainPedestal.class;
	}

	@Nullable
	@Override
	public TileEntityMainPedestal createTileEntity(World world, IBlockState state) {
		return new TileEntityMainPedestal();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntityMainPedestal tile = getTileEntity(world, pos);

		if (!world.isRemote) {
			if (player.getHeldItem(hand).getItem() == new ItemStack(ModItems.torchwand).getItem()) {
				if (CheckAltar.checkingAltar(world, pos, player)) {
					craftingProgress(world, pos, side);
				}
				tile.markDirty();
			} else {
				IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
				if (player.getHeldItem(hand).isEmpty() || player.getHeldItem(hand).getItem() == itemHandler.getStackInSlot(0).getItem()) {
					player.addItemStackToInventory(itemHandler.extractItem(0, 1, false));
				} else if (itemHandler.getStackInSlot(0).isEmpty()) {
					ItemStack giveBackItem = player.getHeldItem(hand);
					player.setHeldItem(hand, itemHandler.insertItem(0, new ItemStack(player.getHeldItem(hand).getItem(), 1, player.getHeldItem(hand).getMetadata()), false));
					player.setHeldItem(hand, new ItemStack(giveBackItem.getItem(), giveBackItem.getCount() - 1, giveBackItem.getMetadata()));
				}
				tile.markDirty();
			}

		}
		return true;
	}

	private void craftingProgress(World world, BlockPos pos, EnumFacing side) {

		int Recipes = 1;
		@Nullable
		ItemStack ingredient[] = new ItemStack[13];
		ItemStack craftingRecipe[][] = new ItemStack[Recipes][14];

		ingredient[0] = getAltarItems(world, pos);
		ingredient[1] = getAltarItems(world, pos.down().north().north().west().west().up().up());
		ingredient[2] = getAltarItems(world, pos.down().north().north().east().east().up().up());
		ingredient[3] = getAltarItems(world, pos.down().south().south().west().west().up().up());
		ingredient[4] = getAltarItems(world, pos.down().south().south().east().east().up().up());
		ingredient[5] = getAltarItems(world, pos.down().down().north().north().north().north().north().up());
		ingredient[6] = getAltarItems(world, pos.down().down().west().west().west().west().west().up());
		ingredient[7] = getAltarItems(world, pos.down().down().east().east().east().east().east().up());
		ingredient[8] = getAltarItems(world, pos.down().down().south().south().south().south().south().up());
		ingredient[9] = getAltarItems(world, pos.north().north().north().north().east().east().east().east());
		ingredient[10] = getAltarItems(world, pos.north().north().north().north().west().west().west().west());
		ingredient[11] = getAltarItems(world, pos.south().south().south().south().east().east().east().east());
		ingredient[12] = getAltarItems(world, pos.south().south().south().south().west().west().west().west());

		ItemStack stackResult = RecipeAltar.ingredientIn(ingredient);
		if (stackResult != null) {
			craftingResult = new EntityItem(world, pos.getX(), pos.getY() + 4, pos.getZ(), stackResult);
			crafting = true;
		}
		this.world = world;
		this.pos = pos;
		this.side = side;
	}

	private ItemStack getAltarItems(World world, BlockPos pos) {
		TileEntityPedestal tile = getTileEntity(world, pos);
		ItemStack stack = tile.inventory.getStackInSlot(0);
		if (!stack.isEmpty()) {
			return stack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active)
        {
            worldIn.setBlockState(pos, ModBlocks.torchAltar.getDefaultState(), 3);
            worldIn.setBlockState(pos, ModBlocks.torchAltar.getDefaultState(), 3);
        }
        else
        {
            worldIn.setBlockState(pos, ModBlocks.torchAltar_night.getDefaultState(), 3);
            worldIn.setBlockState(pos, ModBlocks.torchAltar_night.getDefaultState(), 3);
        }

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntityMainPedestal tile = getTileEntity(world, pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
			ItemStack stack = itemHandler.getStackInSlot(0);
			if (!stack.isEmpty()) {
				EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				world.spawnEntity(item);
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
