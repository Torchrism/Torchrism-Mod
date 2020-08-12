package com.atomuze.torchrism.block.torch_altar;

import javax.annotation.Nullable;

import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockTorchAltar extends BlockTileEntity<TileEntityTorchAltar> {

	public boolean crafting = false;
	public long initTime = 0;
	EntityItem craftingResult;
	public static double offset = 0;
	World world;
	BlockPos pos;
	EnumFacing side;
	
	public BlockTorchAltar(Material material, String name) {
		super(material, name);
	}

	public BlockTorchAltar(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(5f);
		setLightLevel(1f);
		
	}

	@Override
	public BlockTorchAltar setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public Class<TileEntityTorchAltar> getTileEntityClass() {
		return TileEntityTorchAltar.class;
	}

	@Nullable
	@Override
	public TileEntityTorchAltar createTileEntity(World world, IBlockState state) {
		return new TileEntityTorchAltar();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntityTorchAltar tile = getTileEntity(world, pos);
		if (!world.isRemote) {
			if (player.getHeldItem(hand).getItem() == new ItemStack(ModItems.torchwand).getItem()) {
				if (checkingAltar(world, pos, state, player)) {
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

	private boolean checkingAltar(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (check(world, pos, pos.down().down().north().north().north().north().north().up(),ModBlocks.altarPurePedestal, player) 
				&& check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarPurePedestal, player) 
				&& check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarPurePedestal, player) 
				&& check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarPurePedestal, player) 
				&& check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarPedestal, player) 
				&& check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarPedestal, player) 
				&& check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarPedestal, player) 
				&& check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarPedestal, player)
				&& check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarPurePedestal, player) 
				&& check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarPurePedestal, player) 
				&& check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarPurePedestal, player) 
				&& check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarPurePedestal, player)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean check(World world, BlockPos initPos, BlockPos checkPos, Block toCheckBlock, EntityPlayer player) {
		if (!world.getBlockState(checkPos).getBlock().equals(toCheckBlock)) {
			System.out.println(world.getBlockState(checkPos).getBlock().getUnlocalizedName());
			System.out.println(toCheckBlock.getUnlocalizedName());
			player.sendMessage(new TextComponentString("Altar Invalid at x:" + (checkPos.getX() - initPos.getX()) + " y:" + (checkPos.getY() - initPos.getY()) + " z:" + (checkPos.getZ() - initPos.getZ())));
			return false;
		}

		return true;
	}

	private void craftingProgress(World world, BlockPos pos, EnumFacing side) {
		int Recipes = 1;

		@Nullable
		ItemStack ingredient[] = new ItemStack[13];
		ItemStack craftingRecipe[][] = new ItemStack[Recipes][14];
		
		// Altar complete
		// crafting
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
		if(stackResult != null) {
			craftingResult = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stackResult);
			world.spawnEntity(craftingResult);
			craftingEffect(world,pos,side);
		}
		
		initTime = world.getTotalWorldTime();
		this.world = world;
		this.pos = pos;
		this.side = side;
	}
	
	
	
	private void craftingEffect(World world, BlockPos pos, EnumFacing side) {
		extractItem(world, pos, side);
		extractItem(world, pos.down().north().north().west().west().up().up(), side);
		extractItem(world, pos.down().north().north().east().east().up().up(), side);
		extractItem(world, pos.down().south().south().west().west().up().up(), side);
		extractItem(world, pos.down().south().south().east().east().up().up(), side);
		extractItem(world, pos.down().down().north().north().north().north().north().up(), side);
		extractItem(world, pos.down().down().west().west().west().west().west().up(), side);
		extractItem(world, pos.down().down().east().east().east().east().east().up(), side);
		extractItem(world, pos.down().down().south().south().south().south().south().up(), side);
		extractItem(world, pos.north().north().north().north().east().east().east().east(), side);
		extractItem(world, pos.north().north().north().north().west().west().west().west(), side);
		extractItem(world, pos.south().south().south().south().east().east().east().east(), side);
		extractItem(world, pos.south().south().south().south().west().west().west().west(), side);
		world.spawnEntity(craftingResult);
	}

	public void extractItem(World world, BlockPos pos, EnumFacing side) {
		TileEntityTorchAltar tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
		itemHandler.extractItem(0, 1, false);
		tile.markDirty();
	}

	private ItemStack getAltarItems(World world, BlockPos pos) {
		TileEntityTorchAltar tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			return stack;
		} else {
			return ItemStack.EMPTY;
		}

	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityTorchAltar tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
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

//check(world, pos, pos.down(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().west(), Blocks.QUARTZ_BLOCK,player)&&
//check(world, pos, pos.down().north().east(), Blocks.QUARTZ_BLOCK,player)&&
//check(world, pos, pos.down().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().east(), Blocks.QUARTZ_BLOCK, player)&&
//
//check(world, pos, pos.down().down().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().north().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().north().north().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().west().west().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().north().north().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().east().east().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().south().south().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().west().west().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().south().south().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().east().east().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().down().north().north().north().north().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().north().north().north().north().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().south().south().south().south().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().south().south().south().south().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarPurePedestal, player)
