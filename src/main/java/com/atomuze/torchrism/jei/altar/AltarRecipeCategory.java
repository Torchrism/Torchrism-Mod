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
//		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
//		stacks.init(input1, true, 0, 0);
//		stacks.set(input1, new ItemStack(Items.WRITABLE_BOOK));
//		stacks.init(input2, true, 10, 2);
//		stacks.set(input2, new ItemStack(Items.ACACIA_DOOR));
//		stacks.init(input3, true, 20, 2);
//		stacks.set(input3, new ItemStack(Items.APPLE));
//		stacks.init(input4, true, 30, 2);
//		stacks.init(input5, true, 40, 2);
//		stacks.init(input6, true, 50, 2);
//		stacks.init(input7, true, 20, 11);
//		stacks.init(input8, true, 30, 11);
//		stacks.init(catalyse1, true, 40, 11);
//		stacks.init(catalyse2, true, 50, 11);
//		stacks.init(catalyse3, true, 60, 11);
//		stacks.init(catalyse4, true, 70, 11);
//		stacks.init(output, false, 30, 45);
//		stacks.set(ingredients);

		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

//		recipeLayout.getItemStacks().init(0, true, 39, 41);
//
//		recipeLayout.getItemStacks().init(1, true, 49, 41);
//		recipeLayout.getItemStacks().set(0, inputs.get(0));

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

//			System.out.println(i.get(5).toString());
//			System.out.println(i.size());

//		System.out.println(inputs.get(0).toString());
//		System.out.println(inputs.size());
//		int index = 1, posX = 60;
//		for(int i = 1; i < inputs.size(); i++) {
//			List<ItemStack> o = inputs.get(i);
//			recipeLayout.getItemStacks().init(index, true, posX, 6);
//			recipeLayout.getItemStacks().set(index, o);
//			index++;
//			posX += 18;
//		}

//		recipeLayout.getItemStacks().init(7, false, 87, 41);
//		recipeLayout.getItemStacks().set(7, getItemMatchingFocus(focus, IFocus.Mode.INPUT, inputs.get(0), outputs.get(0)));
	}

	private void IsetRecipe(IRecipeLayout recipeLayout, int slotIndex, int xPosition, int yPosition, List<ItemStack> stacks) {
		recipeLayout.getItemStacks().init(slotIndex, true, xPosition, yPosition);
		if(stacks.get(slotIndex).equals(new ItemStack(Blocks.BARRIER)))
			stacks.set(slotIndex, new ItemStack(Blocks.AIR));
		recipeLayout.getItemStacks().set(slotIndex, stacks.get(slotIndex));
	}
}
