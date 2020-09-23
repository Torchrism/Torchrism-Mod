package com.atomuze.torchrism.jei.altar;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.atomuze.torchrism.crafting.RecipeAltar;
import com.google.common.collect.Lists;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class AltarRecipeMaker {
	public static List<JeiAltarRecipeWrapper> getRecipes(){
		List<JeiAltarRecipeWrapper> jeiRecipe = Lists.newArrayList();
		for(int i=0; i< RecipeAltar.inputList.size() / 13; i++) {
			List<ItemStack> inputs =  Lists.newArrayListWithExpectedSize(13);
//			System.out.println(RecipeAltar.inputList.size());
			for(int j=0;j < 13; j++){
//				System.out.println(13*i + j);
				inputs.add(RecipeAltar.inputList.get(13*i + j));
//				System.out.println(inputs.size());
				
			}
//			System.out.println("add");
			JeiAltarRecipeWrapper recipe = new JeiAltarRecipeWrapper(inputs, RecipeAltar.resultList.get(i));
			jeiRecipe.add(recipe);
		}
		return jeiRecipe;
	}
}
