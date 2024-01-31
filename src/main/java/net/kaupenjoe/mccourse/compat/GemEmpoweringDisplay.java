package net.kaupenjoe.mccourse.compat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.kaupenjoe.mccourse.recipe.GemEmpoweringRecipe;

import net.minecraft.recipe.RecipeEntry;

public class GemEmpoweringDisplay extends BasicDisplay {

	public GemEmpoweringDisplay(RecipeEntry<GemEmpoweringRecipe> recipeEntry) {
		super(getInputList(recipeEntry.value()),
			List.of(EntryIngredient.of(EntryStacks.of(recipeEntry.value().getResult(null)))));
	}

	private static List<EntryIngredient> getInputList(GemEmpoweringRecipe recipe) {
		if (recipe == null) {
			return Collections.emptyList();
		}
		List<EntryIngredient> list = new ArrayList<>();
		list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(0)));
		return list;
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return GemEmpoweringCategory.GEM_EMPOWERING;
	}
}
