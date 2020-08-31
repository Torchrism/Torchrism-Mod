package com.atomuze.torchrism.crafting;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class AltarCrafting extends AltarRecipe {
	public AltarCrafting(@Nullable ResourceLocation group, ItemStack result, CraftingHelper.ShapedPrimer primer) {	
		super(group, result, primer);
	}
	
	@Override
	public String getGroup() {
		return group == null ? "" : group.toString();
	}

	public static class Factory implements IRecipeFactory {
		@Override
		public IRecipe parse(final JsonContext context, final JsonObject json) {
			final String group = JsonUtils.getString(json, "group", "");
			final CraftingHelper.ShapedPrimer primer = AltarRecipe.parseShaped(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
//			System.out.println("AltarCrafting-----result: " + result.getDisplayName());
			return new AltarCrafting(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
		}
	}
}