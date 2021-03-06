package com.atomuze.torchrism.block;

import javax.annotation.Nullable;

import com.atomuze.torchrism.block.altar.CheckAltar;
import com.atomuze.torchrism.crafting.ContainerAltar;
import com.atomuze.torchrism.item.ModItems;
import com.atomuze.torchrism.tileentity.TileEntityMainPedestal;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockAltarMainPedestal extends BlockTileEntity<TileEntityMainPedestal> {

	public static boolean crafting;
	public static EntityItem craftingResult;
	public static EntityPlayer player;
	public static ItemStack stackResult;
	public static World world;
	public static BlockPos pos;
	private static boolean keepInventory;

	public BlockAltarMainPedestal(String name) {
		super(Material.ROCK, name);
		setHardness(4f);
		setResistance(5f);
		setLightLevel(1f);
		crafting = false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityMainPedestal tile = getTileEntity(world, pos);
		this.world = world;
		this.pos = pos;
		this.player = player;
		if (!world.isRemote && !crafting) {
			if (player.getHeldItem(hand).getItem() == new ItemStack(ModItems.torchStaff).getItem()) {
				CheckAltar check = new CheckAltar(world, pos);
				if (!check.checkingAltar(world, pos)) {
					TileEntityMainPedestal.altarBuilder = 1;
				} else {
					craftingProgress(world, pos, player);
				}
				tile.markDirty();
			} else {
				IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
				if (player.getHeldItem(hand).isEmpty() || player.getHeldItem(hand).getItem() == itemHandler.getStackInSlot(0).getItem()) {
					player.addItemStackToInventory(itemHandler.extractItem(0, 1, false));
				} else if (itemHandler.getStackInSlot(0).isEmpty()){
					itemHandler.insertItem(0, player.getHeldItem(hand).splitStack(1), false);
				}
				tile.markDirty();
			}
		}
		return true;
	}

	private void craftingProgress(World world, BlockPos pos, EntityPlayer player) {
		this.world = world;
		this.pos = pos;
		InventoryPlayer playerInventory = player.inventory;
		ContainerAltar container = new ContainerAltar(playerInventory, world, pos);
		InventoryCrafting inv = new InventoryCrafting(container, 3, 3);

		if (stackResult != null) {
			craftingResult = new EntityItem(world, pos.getX(), pos.getY() + 2, pos.getZ(), stackResult);
			this.crafting = true;
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;

		if (active) {
			worldIn.setBlockState(pos, ModBlocks.altarMainPedestal.getDefaultState(), 3);
			worldIn.setBlockState(pos, ModBlocks.altarMainPedestal.getDefaultState(), 3);
		} else {
			worldIn.setBlockState(pos, ModBlocks.altarMainPedestal_night.getDefaultState(), 3);
			worldIn.setBlockState(pos, ModBlocks.altarMainPedestal_night.getDefaultState(), 3);
		}

		keepInventory = false;

		if (tileentity != null) {
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
	public BlockAltarMainPedestal setCreativeTab(CreativeTabs tab) {
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
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(ModBlocks.altarMainPedestal));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(ModBlocks.altarMainPedestal);
	}
}
