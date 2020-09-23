package com.atomuze.torchrism.jei.altar;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.jei.JeiPlugin;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AltarRecipeCategory implements IRecipeCategory<JeiAltarRecipeWrapper> {

	private IDrawable background, icon;
	private String name;
	protected static final ResourceLocation TEXTURE = new ResourceLocation(Torchrism.MODID, "textures/gui/jei_recipe_bg.png");

	public AltarRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 160, 90);
		this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.altarMainPedestal));
		this.name = I18n.format(Torchrism.MODID + "." + "altar.name");
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
	}

	@Override
	public String getUid() {
		return JeiPlugin.Altar;
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
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	public void setRecipe(IRecipeLayout recipeLayout, JeiAltarRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();

		stacks.init(0, true, 0, 0);
		stacks.init(1, true, 35, 0);
		stacks.init(2, true, 70, 0);
		stacks.init(3, true, 0, 35);
		stacks.init(4, true, 35, 35);
		stacks.init(5, true, 70, 35);
		stacks.init(6, true, 0, 70);
		stacks.init(7, true, 35, 70);
		stacks.init(8, true, 70, 70);
		stacks.init(9, true, 15, 15);
		stacks.init(10, true, 55, 15);
		stacks.init(11, true, 15, 55);
		stacks.init(12, true, 55, 55);
		stacks.init(13, false, 130, 35);
		stacks.set(ingredients);
	}
}
