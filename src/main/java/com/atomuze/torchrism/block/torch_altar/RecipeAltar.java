package com.atomuze.torchrism.block.torch_altar;

import javax.annotation.Nullable;

import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeAltar {
	@Nullable
	public static ItemStack ingredientIn(ItemStack ingr[]){
		ItemStack ingrAltar = ingr[0];
		ItemStack ingrInside[] = new ItemStack[4];
		ItemStack ingrOutside[] = new ItemStack[8];
		for(int i = 1; i<=4; i++) {
			ingrInside[i-1] = ingr[i];
		}
		for(int i = 5; i<=12; i++) {
			ingrOutside[i-5] = ingr[i];
		}
		ItemStack ingrResult = craftCheck(ingrAltar,ingrInside,ingrOutside);
		if(ingrResult != ItemStack.EMPTY) {
			return ingrResult;
		}else {
			return null;
		}
	}
	
	@Nullable
	private static ItemStack craftCheck(ItemStack ingrAltar, ItemStack[] ingrInside, ItemStack[] ingrOutside) {
		ItemStack[] craftingRecipeInside = new ItemStack[4];
		ItemStack[] craftingRecipeOutside = new ItemStack[8];
		craftingRecipeInside = recipes(ingrAltar, 1);
		craftingRecipeOutside = recipes(ingrAltar, 2);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!ingrInside[i].getUnlocalizedName().equals(craftingRecipeInside[j].getUnlocalizedName())) {
					return ItemStack.EMPTY;
				}
			}
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!ingrOutside[i].getUnlocalizedName().equals(craftingRecipeOutside[j].getUnlocalizedName())) {
					return ItemStack.EMPTY;
				}
			}
		}
		ItemStack ResultA[] = recipes(ingrAltar, 0);
		ItemStack Result = ResultA[0];
		return Result;
	}
	
	@Nullable
	private static ItemStack[] recipes(ItemStack ingrAltar, int i) {
		int recipeID = 0;
		ItemStack recipes[][] = new ItemStack[2][14];
		ItemStack ingr0 = null;
		ItemStack[] craftingRecipeInside = new ItemStack[4];
		ItemStack[] craftingRecipeOutside = new ItemStack[8];
		ItemStack[] result = new ItemStack[1];
		
		recipes[0][0] = new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.DEFAULT.getMetadata());
		recipes[0][1] = new ItemStack(ModBlocks.compactedTorch, 1, 0);
		recipes[0][2] = new ItemStack(ModBlocks.compactedTorch, 1, 0);
		recipes[0][3] = new ItemStack(ModBlocks.compactedTorch, 1, 0);
		recipes[0][4] = new ItemStack(ModBlocks.compactedTorch, 1, 0);
		recipes[0][5] = ItemStack.EMPTY;
		recipes[0][6] = ItemStack.EMPTY;
		recipes[0][7] = ItemStack.EMPTY;
		recipes[0][8] = ItemStack.EMPTY;
		recipes[0][9] = ItemStack.EMPTY;
		recipes[0][10] = ItemStack.EMPTY;
		recipes[0][11] = ItemStack.EMPTY;
		recipes[0][12] = ItemStack.EMPTY;
		recipes[0][13] = new ItemStack(ModBlocks.greatWallBuilder);
		
		recipes[1][0] = new ItemStack(Blocks.QUARTZ_BLOCK, 1, BlockQuartz.EnumType.DEFAULT.getMetadata());
		recipes[1][1] = new ItemStack(Items.GLOWSTONE_DUST, 1, 0);
		recipes[1][2] = new ItemStack(Items.GLOWSTONE_DUST, 1, 0);
		recipes[1][3] = new ItemStack(Items.GLOWSTONE_DUST, 1, 0);
		recipes[1][4] = new ItemStack(Items.GLOWSTONE_DUST, 1, 0);
		recipes[1][5] = ItemStack.EMPTY;
		recipes[1][6] = ItemStack.EMPTY;
		recipes[1][7] = ItemStack.EMPTY;
		recipes[1][8] = ItemStack.EMPTY;
		recipes[1][9] = ItemStack.EMPTY;
		recipes[1][10] = ItemStack.EMPTY;
		recipes[1][11] = ItemStack.EMPTY;
		recipes[1][12] = ItemStack.EMPTY;
		recipes[1][13] = new ItemStack(ModBlocks.illuminateBlock);
		
		for(int j = 1; j<=4; j++) {
			craftingRecipeInside[j-1] = ItemStack.EMPTY;
		}
		for(int j = 5; j<=12; j++) {
			craftingRecipeOutside[j-5] = ItemStack.EMPTY;
		}
		result[0] = ItemStack.EMPTY;
			
		for(int id = 0; id<2; id++) {
			if(recipes[id][0].getUnlocalizedName().equals(ingrAltar.getUnlocalizedName()) ) {
				for(int j = 1; j<=4; j++) {
					craftingRecipeInside[j-1] = recipes[id][j];
				}
				for(int j = 5; j<=12; j++) {
					craftingRecipeOutside[j-5] = recipes[id][j];
				}
				result[0] = recipes[id][13];
//				System.out.println(recipes[id][0].getUnlocalizedName());
				break;
			}
		}
		
		if (i == 1) {
			return craftingRecipeInside;
		}else if (i == 2) {
			return craftingRecipeOutside;
		}else {
			return result;
		}
		
	}
	
	
}
