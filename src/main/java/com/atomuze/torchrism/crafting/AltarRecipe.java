package com.atomuze.torchrism.crafting;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.altar.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.block.altar.tileEntity.TileEntityPedestal;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class AltarRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	@Nonnull
	protected ItemStack recipeOutput = ItemStack.EMPTY;
	protected NonNullList<Ingredient> RecipeInput = null;
	protected int width = 3;
	protected int height = 3;
	protected boolean mirrored = true;
	protected ResourceLocation group;
	public BlockPos pos = BlockAltarMainPedestal.pos;
	public World world = BlockAltarMainPedestal.world;

	public AltarRecipe(ResourceLocation group, ItemStack result, ShapedPrimer primer) {
		this.group = group;
		this.recipeOutput = result.copy();
		this.width = primer.width;
		this.height = primer.height;
		this.RecipeInput = primer.input;
		this.mirrored = primer.mirrored;
	}
	
	public AltarRecipe(World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
		
	}
	
	private ItemStack getAltarItems(World world, BlockPos pos) {
		TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(pos);
		ItemStack stack = tile.inventory.getStackInSlot(0);
		return !stack.isEmpty() ? stack : ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		System.out.println("AltarCrafting.getCraftingResult");
		return this.getRecipeOutput().copy();
	}
	
	@Override
	@Nonnull
	public ItemStack getRecipeOutput() {
		System.out.println("getRecipeOutput");
		return recipeOutput;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		System.out.println("matches");
		System.out.println(recipeOutput.getUnlocalizedName());
		
		for (int x = 0; x <= inv.getWidth() - width; x++) {
			for (int y = 0; y <= inv.getHeight() - height; ++y) {
				if (checkMatch(inv, x, y, false)) {
					return true;
				}

				if (mirrored && checkMatch(inv, x, y, true)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
		for (int x = 0; x < inv.getWidth(); x++) {
			for (int y = 0; y < inv.getHeight(); y++) {
				int subX = x - startX;
				int subY = y - startY;
				Ingredient target = Ingredient.EMPTY;

				if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
					if (mirror) {
						target = RecipeInput.get(width - subX - 1 + subY * width);
					} else {
						target = RecipeInput.get(subX + subY * width);
					}
				}

				if (!target.apply(inv.getStackInRowAndColumn(x, y))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean canFit(int width, int height) {
		System.out.println("canFit");
		return this.width == width && this.height == height ? true : false;
	}

	public static CraftingHelper.ShapedPrimer parseShaped(JsonContext context, JsonObject json) {
		System.out.println("run ShapedPrimer");
		final Map<Character, Ingredient> ingredientMap = Maps.newHashMap();
		for (final Map.Entry<String, JsonElement> entry : JsonUtils.getJsonObject(json, "key").entrySet()) {
			if (entry.getKey().length() != 1)
				throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			if (" ".equals(entry.getKey()))
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");

			ingredientMap.put(entry.getKey().toCharArray()[0], CraftingHelper.getIngredient(entry.getValue(), context));
		}

		ingredientMap.put(' ', Ingredient.EMPTY);

		final JsonArray patternJ = JsonUtils.getJsonArray(json, "pattern");

		if (patternJ.size() == 0)
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");

		final String[] pattern = new String[patternJ.size()];
		for (int x = 0; x < pattern.length; ++x) {
			final String line = JsonUtils.getString(patternJ.get(x), "pattern[" + x + "]");
			if (x > 0 && pattern[0].length() != line.length())
				throw new JsonSyntaxException("Invalid pattern: each row must  be the same width");
			pattern[x] = line;
		}

		final CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
		primer.width = pattern[0].length();
		primer.height = pattern.length;
		primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
		primer.input = NonNullList.withSize(primer.width * primer.height, Ingredient.EMPTY);

		final Set<Character> keys = Sets.newHashSet(ingredientMap.keySet());
		keys.remove(' ');

		int index = 0;
		for (final String line : pattern) {
			for (final char chr : line.toCharArray()) {
				final Ingredient ing = ingredientMap.get(chr);
				if (ing == null)
					throw new JsonSyntaxException("Pattern references symbol '" + chr + "' but it's not defined in the key");
				primer.input.set(index++, ing);
				keys.remove(chr);
			}
		}

		if (!keys.isEmpty())
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + keys);

		return primer;
	}

}