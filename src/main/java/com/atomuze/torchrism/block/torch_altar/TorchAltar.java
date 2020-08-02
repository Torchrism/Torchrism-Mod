package com.atomuze.torchrism.block.torch_altar;

import java.util.Random;

import javax.annotation.Nullable;

import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.torch_placer.TorchPlacer;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.item.Item;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TorchAltar extends BlockTileEntity<TileEntityTorchAltar> {
	
	public TorchAltar(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(5f);
		setLightLevel(1f);
	}

	@Override
	public TorchAltar setCreativeTab(CreativeTabs tab) {
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
			if(player.getHeldItem(hand).getItem() == new ItemStack(ModItems.torchwand).getItem()) {
				if(checkingAltar(world,pos, state, player)) {
					craftingProgress(world, pos);
				}
				tile.markDirty();
			}else {
				
				IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
				
				if (player.getHeldItem(hand).isEmpty()) {
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				} else {
					player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItem(hand), false));
				}
				tile.markDirty();
			}
			
		}
		return true;
	}
	
	private boolean checkingAltar(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(
//		check(world, pos, pos.down(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north().west(), Blocks.QUARTZ_BLOCK,player)&&
//		check(world, pos, pos.down().north().east(), Blocks.QUARTZ_BLOCK,player)&&
//		check(world, pos, pos.down().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().east(), Blocks.QUARTZ_BLOCK, player)&&
//		
//		check(world, pos, pos.down().down().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north().north().north().up(), ModBlocks.altarPurePedestal, player)&&
//		
//		check(world, pos, pos.down().down().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().west().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarPurePedestal, player)&&
//		
//		check(world, pos, pos.down().down().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().east().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarPurePedestal, player)&&
//		
//		check(world, pos, pos.down().down().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarPurePedestal, player)&&
//		
//		check(world, pos, pos.down().north().north().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north().north().west().west().up(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarPedestal, player)&&
//		
//		check(world, pos, pos.down().north().north().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north().north().east().east().up(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarPedestal, player)&&
//		
//		check(world, pos, pos.down().south().south().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().south().west().west().up(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarPedestal, player)&&
//		
//		check(world, pos, pos.down().south().south().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().south().east().east().up(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarPedestal, player)&&
//		
//		check(world, pos, pos.down().down().north().north().north().north().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north().north().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
//		check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
//		
//		check(world, pos, pos.down().down().north().north().north().north().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().north().north().north().north().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
//		check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarPurePedestal, player)&&
//		
//		check(world, pos, pos.down().down().south().south().south().south().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south().south().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
//		check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
//		
//		check(world, pos, pos.down().down().south().south().south().south().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().down().south().south().south().south().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//		check(world, pos, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
//		check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarPurePedestal, player)
		
		check(world, pos, pos.down().down().north().north().north().north().north().up(), ModBlocks.altarPurePedestal, player)&&
		check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarPurePedestal, player)&&
		check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarPurePedestal, player)&&
		check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarPurePedestal, player)&&
		check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarPedestal, player)&&
		check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarPedestal, player)&&
		check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarPedestal, player)&&
		check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarPedestal, player)&&
		check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
		check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarPurePedestal, player)&&
		check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
		check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarPurePedestal, player)) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean check(World world, BlockPos initPos, BlockPos checkPos, Block toCheckBlock, EntityPlayer player) {
		if(!world.getBlockState(checkPos).getBlock().equals(toCheckBlock)) { 
			System.out.println(world.getBlockState(checkPos).getBlock().getUnlocalizedName());
			System.out.println(toCheckBlock.getUnlocalizedName());
			player.sendMessage(new TextComponentString("Altar Invalid at x:" + (checkPos.getX()-initPos.getX()) + " y:" + (checkPos.getY()-initPos.getY()) + " z:"+ (checkPos.getZ()-initPos.getZ())));
			return false;
		}

		return true;
	}

	private void craftingProgress(World world, BlockPos pos) {
		int Recipes = 1;
		
		@Nullable
		ItemStack ingredient[] = new ItemStack[13];
		ItemStack craftingRecipe[][] = new ItemStack[Recipes][14];
			
		//Altar complete
		//crafting
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
		
		//ItemStack quartz = new ItemStack(Blocks.QUARTZ_BLOCK, 1, BlockQuartz.EnumType.CHISELED.getMetadata());
		
		ItemStack stoneBrick = new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.DEFAULT.getMetadata());
				
		craftingRecipe[0][0] = stoneBrick;
		craftingRecipe[0][1] = stoneBrick;
		craftingRecipe[0][2] = stoneBrick;
		craftingRecipe[0][3] = stoneBrick;
		craftingRecipe[0][4] = stoneBrick;
		craftingRecipe[0][5] = ItemStack.EMPTY;
		craftingRecipe[0][6] = ItemStack.EMPTY;
		craftingRecipe[0][7] = ItemStack.EMPTY;
		craftingRecipe[0][8] = ItemStack.EMPTY;
		craftingRecipe[0][9] = ItemStack.EMPTY;
		craftingRecipe[0][10] = ItemStack.EMPTY;
		craftingRecipe[0][11] = ItemStack.EMPTY;
		craftingRecipe[0][12] = ItemStack.EMPTY;
		craftingRecipe[0][13] = new ItemStack(ModBlocks.greatWallBuilder);
				
		for(int i =0 ; i<Recipes;i++) {
			boolean craft = true;
			for(int j = 0; j< 13; j++) {
				if(ingredient[j].getItem().getUnlocalizedName().equals(craftingRecipe[i][j].getItem().getUnlocalizedName())) {
					System.out.println("craft check : true");
				}else {
//					System.out.print("craft check : wrong =>	");
//					System.out.print(ingredient[j].getItem().getUnlocalizedName() + "	");
//					System.out.println(craftingRecipe[i][j].getItem().getUnlocalizedName());
					craft = false;
				}
				
			}
			if(craft) {
				ItemStack stackResult = craftingRecipe[i][13];
				EntityItem craftingResult = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stackResult);
				world.spawnEntity(craftingResult);
				break;
			}
		}
	}
	
	
	private ItemStack getAltarItems(World world, BlockPos pos) {
		TileEntityTorchAltar tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			 return stack;
		}else{
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




