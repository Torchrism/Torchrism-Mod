package com.atomuze.torchrism.jei.altar;

import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class JeiAltarRecipeWrapper implements IRecipeWrapper {

	private final List<ItemStack> inputs;
	private final ItemStack outputs;

	public JeiAltarRecipeWrapper(List<ItemStack> inputs, ItemStack outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, outputs);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

	}
}
