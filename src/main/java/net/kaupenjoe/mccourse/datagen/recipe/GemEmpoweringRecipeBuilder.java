package net.kaupenjoe.mccourse.datagen.recipe;

import net.kaupenjoe.mccourse.recipe.GemEmpoweringRecipe;
import org.jetbrains.annotations.Nullable;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class GemEmpoweringRecipeBuilder implements CraftingRecipeJsonBuilder {
	private final Item result;
	private final Ingredient ingredient;
	private final int count;
	private final Advancement.Builder advancement = Advancement.Builder.create();

	public GemEmpoweringRecipeBuilder(
		ItemConvertible ingredient, ItemConvertible result, int count) {
		this.ingredient = Ingredient.ofItems(ingredient);
		this.result = result.asItem();
		this.count = count;
	}

	@Override
	public CraftingRecipeJsonBuilder criterion(
		String string, AdvancementCriterion<?> criterion) {
		this.advancement.criterion(string, criterion);
		return this;
	}

	@Override
	public CraftingRecipeJsonBuilder group(@Nullable String group) {
		return this;
	}

	@Override
	public Item getOutputItem() {
		return result;
	}

	public ItemStack getOutputItemStack(){
		return new ItemStack(result, count);
	}

	@Override
	public void offerTo(RecipeExporter exporter) {
		offerTo(exporter, CraftingRecipeJsonBuilder
			.getItemId(result).getPath() + "_from_gem_empowering");
	}

	@Override
	public void offerTo(RecipeExporter exporter, Identifier recipeId) {
		exporter.accept(recipeId,
			new GemEmpoweringRecipe(getOutputItemStack(),
				DefaultedList.ofSize(1, this.ingredient)),
			advancement.build(new Identifier(recipeId.getNamespace(),
				"recipes/" + recipeId.getPath())));
	}
}
