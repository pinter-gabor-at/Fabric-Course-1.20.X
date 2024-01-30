package net.kaupenjoe.mccourse.recipe;

import net.minecraft.recipe.Ingredient;

import java.util.Collection;
import java.util.List;

/**
 * Utilities for recipe generation
 */
public final class RecipeUtils {

	/**
	 * Filter empty ingredients from a {@link Collection}, or a {@link List} or an Array
	 * @param ingredients input
	 * @return output
	 */
	public static Ingredient[] filterEmptyIngredients(Collection<Ingredient> ingredients) {
		return ingredients.stream()
			.filter((ingredient) ->
				!ingredient.isEmpty()).toArray(Ingredient[]::new);
	}
}
