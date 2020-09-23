package com.atomuze.torchrism.jei.altar;

import java.util.Collections;
import java.util.List;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.jei.TorchrismJeiPlugin;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AltarRecipeCategory implements IRecipeCategory<JeiAltarRecipeWrapper> {

	private IDrawable background;
	private String name;
	protected static final ResourceLocation TEXTURE = new ResourceLocation(Torchrism.MODID, "textures/gui/jei_recipe_bg.png");
	
	public AltarRecipeCategory(IGuiHelper helper) {
		background = helper.createDrawable(TEXTURE, 0, 0, 160, 90);
		name = I18n.format(Torchrism.MODID + "." + "altar.name");
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
	}

	@Override
	public String getUid() {
		return TorchrismJeiPlugin.Altar;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public String getModName() {
		return Torchrism.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	public void setRecipe(IRecipeLayout recipeLayout, JeiAltarRecipeWrapper recipeWrapper, IIngredients ingredients) {
		System.out.println("setRecipe");
		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		List<List<ItemStack>> outputs = ingredients.getOutputs(

		for (List<ItemStack> i : inputs) {
			IsetRecipe(recipeLayout, 0, 0, 0, i);
			IsetRecipe(recipeLayout, 1, 35, 0, i);
			IsetRecipe(recipeLayout, 2, 70, 0, i);
			IsetRecipe(recipeLayout, 3, 0, 35, i);
			IsetRecipe(recipeLayout, 4, 35, 35, i);
			IsetRecipe(recipeLayout, 5, 70, 35, i);
			IsetRecipe(recipeLayout, 6, 0, 70, i);
			IsetRecipe(recipeLayout, 7, 35, 70, i);
			IsetRecipe(recipeLayout, 8, 70, 70, i);
			IsetRecipe(recipeLayout, 9, 15, 15, i);
			IsetRecipe(recipeLayout, 10, 55, 15, i);
			IsetRecipe(recipeLayout, 11, 15, 55, i);
			IsetRecipe(recipeLayout, 12, 55, 55, i);
			
			recipeLayout.getItemStacks().init(13, false, 130, 35);
			recipeLayout.getItemStacks().set(13, outputs.get(0));
			for (int j = 0; j < i.size(); j++) {
				System.out.println(i.get(j).toString());
				System.out.println(i.size());
			}
		}
	}

	private void IsetRecipe(IRecipeLayout recipeLayout, int slotIndex, int xPosition, int yPosition, List<ItemStack> stacks) {
		recipeLayout.getItemStacks().init(slotIndex, true, xPosition, yPosition);
		if(stacks.get(slotIndex).equals(new ItemStack(Blocks.BARRIER)))
			stacks.set(slotIndex, new ItemStack(Blocks.AIR));
		recipeLayout.getItemStacks().set(slotIndex, stacks.get(slotIndex));
	}
}
