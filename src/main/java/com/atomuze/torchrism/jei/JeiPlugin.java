package com.atomuze.torchrism.jei;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.crafting.ContainerAltar;
import com.atomuze.torchrism.crafting.RecipeAltar;
import com.atomuze.torchrism.jei.altar.AltarRecipeCategory;
import com.atomuze.torchrism.jei.altar.AltarRecipeMaker;
import com.google.common.base.Preconditions;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;

@JEIPlugin
public class JeiPlugin implements IModPlugin{
	
	public static final String Altar = Torchrism.MODID + ".altar";
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new AltarRecipeCategory(gui));
	}

	@Override
	public void register(IModRegistry registry) {
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers helpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		registry.addRecipes(AltarRecipeMaker.getRecipes(), Altar);
	}

	public static String translateToLocal(String key) {
		 if(I18n.canTranslate(key))
			 return I18n.translateToLocal(key);
		 else
			 return I18n.translateToFallback(key);	 
	}
	
	public static String translateToLocalFormatted(String key, Object... format) {
		String s = translateToLocal(key);
		try {
			return String.format(s, format);
		}catch (IllegalFormatException e) {
			return "Formate error: " + s;
		}
	}
}