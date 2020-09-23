package com.atomuze.torchrism.jei.altar;

import java.util.List;

import com.atomuze.torchrism.crafting.RecipeAltar;
import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;

public class AltarRecipeMaker {
	public static List<JeiAltarRecipeWrapper> getRecipes() {
		List<JeiAltarRecipeWrapper> jeiRecipe = Lists.newArrayList();
		for (int i = 0; i < RecipeAltar.inputList.size() / 15; i++) {
			List<ItemStack> inputs = Lists.newArrayListWithExpectedSize(15);
			for (int j = 0; j < 15; j++) {
				inputs.add(RecipeAltar.inputList.get(15 * i + j));
			}
			JeiAltarRecipeWrapper recipe = new JeiAltarRecipeWrapper(inputs, RecipeAltar.resultList.get(i));
			jeiRecipe.add(recipe);
		}
		return jeiRecipe;
	}
}
