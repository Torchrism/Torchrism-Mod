package com.atomuze.torchrism.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.atomuze.torchrism.block.altar.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.block.altar.tileEntity.TileEntityPedestal;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RecipeAltar extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	@Nonnull
	protected ItemStack recipeOutput = ItemStack.EMPTY;
	public static NonNullList<Ingredient> RecipeInput;
	public static final List<ItemStack> inputList = new ArrayList<>();
	public static final List<ItemStack> resultList = new ArrayList<>();
	protected final int width = 3;
	protected final int height = 5;
	public BlockPos pos;
	public World world;

	public RecipeAltar(ItemStack result, ShapedPrimer primer) {
//		System.out.println("AltarRecipe*--------------------------------------------------" + primer.input.get(1).getMatchingStacks().toString());
//		System.out.println("AltarRecipe*--------------------------------------------------" + result.getUnlocalizedName());
		this.recipeOutput = result.copy();
		this.RecipeInput = primer.input;
		int count = 0;
		for (int i = 0; i < primer.input.size(); i++) {
//			System.out.println(primer.input.get(i).getMatchingStacks());
			for (ItemStack itemstack : primer.input.get(i).getMatchingStacks()) {
				inputList.add(itemstack);
				count++;
			}
		}
		
		for (int j = 0; j < 13 - count; j++) {
			inputList.add(new ItemStack(Blocks.BARRIER));
		}
		
		count = 0;
	//	System.out.println(result.getUnlocalizedName());
		resultList.add(result);
		
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		BlockAltarMainPedestal.stackResult = this.getRecipeOutput().copy();
		return this.getRecipeOutput().copy();
	}

	@Override
	@Nonnull
	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		if (inv.getHeight() != 0)
			return false;
		pos = BlockAltarMainPedestal.pos;
		world = BlockAltarMainPedestal.world;
		for (int x = 0; x < 15; x++) {
			if (!checkMatch(x))
				return false;
		}
		return true;
	}

	private boolean checkMatch(int slot) {
		Ingredient target = Ingredient.EMPTY;
		target = RecipeInput.get(slot);
		switch (slot) {
		case 0:
			if (!target.apply(getAltarItems(world, pos.north().north().north().north().west().west().west().west())))
				return false;
			break;
		case 1:
			if (!target.apply(getAltarItems(world, pos.down().north().north().north().north().north())))
				return false;
			break;
		case 2:
			if (!target.apply(getAltarItems(world, pos.north().north().north().north().east().east().east().east())))
				return false;
			break;
		case 3:
			if (!target.apply(getAltarItems(world, pos.down().west().west().west().west().west())))
				return false;
			break;
		case 4:
			if (!target.apply(getAltarItems(world, pos)))
				return false;
			break;
		case 5:
			if (!target.apply(getAltarItems(world, pos.down().east().east().east().east().east())))
				return false;
			break;
		case 6:
			if (!target.apply(getAltarItems(world, pos.south().south().south().south().west().west().west().west())))
				return false;
			break;
		case 7:
			if (!target.apply(getAltarItems(world, pos.down().south().south().south().south().south())))
				return false;
			break;
		case 8:
			if (!target.apply(getAltarItems(world, pos.south().south().south().south().east().east().east().east())))
				return false;
			break;
		case 9:
			if (target.apply(new ItemStack(Blocks.AIR))) 
				break;
			if (!(target.apply(getAltarItems(world, pos.north().north().east().east().up())) || target.apply(getAltarItems(world, pos.north().north().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().east().east().up())))) {
				return false;
			}
			break;
		case 10:
			if (target.apply(new ItemStack(Blocks.AIR))) 
				break;
			if (!(target.apply(getAltarItems(world, pos.north().north().east().east().up())) || target.apply(getAltarItems(world, pos.north().north().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().east().east().up())))) {
				return false;
			}
			break;
		case 11:
			if (target.apply(new ItemStack(Blocks.AIR))) 
				break;
			if (!(target.apply(getAltarItems(world, pos.north().north().east().east().up())) || target.apply(getAltarItems(world, pos.north().north().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().east().east().up())))) {
				return false;
			}
			break;
		case 12:
			if (target.apply(new ItemStack(Blocks.AIR))) 
				break;
			if (!(target.apply(getAltarItems(world, pos.north().north().east().east().up())) || target.apply(getAltarItems(world, pos.north().north().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().west().west().up())) || target.apply(getAltarItems(world, pos.south().south().east().east().up())))) {
				return false;
			}
			break;
		case 13:
			if (!target.apply(new ItemStack(Blocks.AIR))) {
				throw new IllegalArgumentException("Invalid input");
			}
			break;
		case 14:
			if (!target.apply(new ItemStack(Blocks.AIR))) {
				throw new IllegalArgumentException("Invalid input");
			}
		}
		return true;

	}

	@Override
	public boolean canFit(int width, int height) {
		return this.width == width && this.height == height ? true : false;
	}

	private ItemStack getAltarItems(World world, BlockPos pos) {
		TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(pos);
		ItemStack stack = tile.inventory.getStackInSlot(0);
		return !stack.isEmpty() ? stack : ItemStack.EMPTY;
	}

	public static CraftingHelper.ShapedPrimer parseShaped(JsonContext context, JsonObject json) {
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
	
	public static class Factory implements IRecipeFactory {
		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			String group = JsonUtils.getString(json, "group", "");
			CraftingHelper.ShapedPrimer primer = RecipeAltar.parseShaped(context, json);
			ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			return new RecipeAltar(result, primer);
		}
	}

}