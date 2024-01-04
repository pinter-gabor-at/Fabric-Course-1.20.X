package net.kaupenjoe.mccourse.datagen;

import java.util.List;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;

import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

public class ModRecipeGenerator extends FabricRecipeProvider {
	public ModRecipeGenerator(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generate(RecipeExporter exporter) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET)
			.pattern("SSS")
			.pattern("SPS")
			.pattern("SSS")
			.input('S', Items.STONE)
			.input('P', ModItems.PINK_GARNET)
			.criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		offerReversibleCompactingRecipes(exporter,
			RecipeCategory.MISC, ModItems.PINK_GARNET,
			RecipeCategory.MISC, ModBlocks.PINK_GARNET_BLOCK);

		offerSmelting(exporter,
			List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE,
				ModBlocks.NETHER_PINK_GARNET_ORE, ModBlocks.END_STONE_PINK_GARNET_ORE),
			RecipeCategory.MISC, ModItems.PINK_GARNET,
			0.25f, 200, "pink_garnet");

		offerBlasting(exporter,
			List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE,
				ModBlocks.NETHER_PINK_GARNET_ORE, ModBlocks.END_STONE_PINK_GARNET_ORE),
			RecipeCategory.MISC, ModItems.PINK_GARNET,
			0.25f, 200, "pink_garnet");

		createSlabRecipe(
			RecipeCategory.BUILDING_BLOCKS,
			ModBlocks.PINK_GARNET_SLAB,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		createStairsRecipe(
			ModBlocks.PINK_GARNET_STAIRS,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_BUTTON)
			.input(ModItems.PINK_GARNET)
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		createPressurePlateRecipe(
			RecipeCategory.REDSTONE,
			ModBlocks.PINK_GARNET_PRESSURE_PLATE,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		createFenceRecipe(
			ModBlocks.PINK_GARNET_FENCE,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		createFenceGateRecipe(
			ModBlocks.PINK_GARNET_FENCE_GATE,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		getWallRecipe(
			RecipeCategory.DECORATIONS,
			ModBlocks.PINK_GARNET_WALL,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		createDoorRecipe(
			ModBlocks.PINK_GARNET_DOOR,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		createTrapdoorRecipe(
			ModBlocks.PINK_GARNET_TRAPDOOR,
			Ingredient.ofItems(ModItems.PINK_GARNET))
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		offerToolset(exporter, ModItems.PINK_GARNET,
			ModItems.PINK_GARNET_SWORD,
			ModItems.PINK_GARNET_PICKAXE,
			ModItems.PINK_GARNET_SHOVEL,
			ModItems.PINK_GARNET_AXE,
			ModItems.PINK_GARNET_HOE);
	}

	/**
	 * Create recipe for on type of tool
	 * @param builder {@link ShapelessRecipeJsonBuilder} in which the pattern has already been set, and contains only
	 * '#' characters for {@link Items#STICK} and 'X' for baseItem
	 */
	private void offerTool(RecipeExporter exporter, ItemConvertible baseItem, ShapedRecipeJsonBuilder builder) {
		builder
			.input('#', Items.STICK)
			.input('X', baseItem)
			.criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
			.criterion(hasItem(baseItem), conditionsFromItem(baseItem))
			.offerTo(exporter);
	}

	/**
	 * Create recipes for common tools
	 */
	private void offerToolset(RecipeExporter exporter,
		ItemConvertible baseItem,
		ItemConvertible sword,
		ItemConvertible pickaxe,
		ItemConvertible shovel,
		ItemConvertible axe,
		ItemConvertible hoe) {
		if (sword != null) {
			offerTool(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, sword)
					.pattern("X")
					.pattern("X")
					.pattern("#"));
		}
		if (pickaxe != null) {
			offerTool(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, pickaxe)
					.pattern("XXX")
					.pattern(" # ")
					.pattern(" # "));
		}
		if (shovel != null) {
			offerTool(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, shovel)
					.pattern("X")
					.pattern("#")
					.pattern("#"));
		}
		if (axe != null) {
			offerTool(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, axe)
					.pattern("XX")
					.pattern("X#")
					.pattern(" #"));
		}
		if (hoe != null) {
			offerTool(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, hoe)
					.pattern("XX")
					.pattern(" #")
					.pattern(" #"));
		}
	}
}
