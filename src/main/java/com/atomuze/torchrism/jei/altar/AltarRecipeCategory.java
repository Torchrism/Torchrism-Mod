package com.atomuze.torchrism.jei.altar;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.jei.TorchrismJeiPlugin;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class AltarRecipeCategory extends AbstractAltarRecipeCategoty<JeiAltarRecipeWrapper> {

	private IDrawable background;
	private String name;

	public AltarRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURE, 176, 14, 24, 17);
		name = "Torchrism Altar";
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
		System.out.println("setRecipe--------------------------------------------------------------------");
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 21, 2);
		stacks.init(input2, true, 30, 2);
		stacks.init(input3, true, 39, 2);
		stacks.init(input4, true, 50, 2);
		stacks.init(input5, true, 60, 2);
		stacks.init(input6, true, 70, 2);
		stacks.init(input7, true, 20, 11);
		stacks.init(input8, true, 30, 11);
		stacks.init(catalyse1, true, 40, 11);
		stacks.init(catalyse2, true, 50, 11);
		stacks.init(catalyse3, true, 60, 11);
		stacks.init(catalyse4, true, 70, 11);
		stacks.init(output, true, 50, 21);

		stacks.set(ingredients);
	}
}
