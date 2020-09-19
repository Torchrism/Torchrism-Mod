package com.atomuze.torchrism.jei.altar;

import com.atomuze.torchrism.Torchrism;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractAltarRecipeCategoty<T extends IRecipeWrapper> implements IRecipeCategory<T> {
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(Torchrism.MODID, "textures/gui/torchonomicon_bg.png");
	
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int input3 = 2;
	protected static final int input4 = 3;
	protected static final int input5 = 4;
	protected static final int input6 = 5;
	protected static final int input7 = 6;
	protected static final int input8 = 7;
	protected static final int input9 = 8;
	protected static final int catalyse1 = 9;
	protected static final int catalyse2 = 10;
	protected static final int catalyse3 = 11;
	protected static final int catalyse4 = 12;
	protected static final int output = 13;

	protected final IDrawableStatic staticArrow;
	
	public AbstractAltarRecipeCategoty(IGuiHelper helper) {
		staticArrow = helper.createDrawable(TEXTURE, 30, 14, 24, 17);
	}
}
