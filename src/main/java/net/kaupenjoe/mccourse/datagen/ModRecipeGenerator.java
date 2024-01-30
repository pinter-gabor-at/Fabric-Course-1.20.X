package net.kaupenjoe.mccourse.datagen;

import java.util.List;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.datagen.recipe.GemEmpoweringRecipeBuilder;
import net.kaupenjoe.mccourse.item.ModItems;

import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

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

		offerTool(exporter, ModItems.PINK_GARNET,
			ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_PAXEL)
				.pattern("XXX")
				.pattern("X# ")
				.pattern(" # "));

		offerArmorset(exporter, ModItems.PINK_GARNET,
			ModItems.PINK_GARNET_HELMET,
			ModItems.PINK_GARNET_CHESTPLATE,
			ModItems.PINK_GARNET_LEGGINGS,
			ModItems.PINK_GARNET_BOOTS);

		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.PINK_GARNET_HORSE_ARMOR)
			.pattern("  H")
			.pattern("IWI")
			.pattern("L L")
			.input('W', ItemTags.WOOL)
			.input('I', ModItems.PINK_GARNET)
			.input('H', ModItems.PINK_GARNET_HELMET)
			.input('L', ModItems.PINK_GARNET_LEGGINGS)
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_LAMP_BLOCK)
			.pattern(" X ")
			.pattern("X#X")
			.pattern(" X ")
			.input('#', Items.REDSTONE_LAMP)
			.input('X', ModItems.PINK_GARNET)
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR)
			.pattern("  /")
			.pattern(" /S")
			.pattern("XS ")
			.input('/', Items.STICK)
			.input('S', Items.STRING)
			.input('X', Items.IRON_INGOT)
			.criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
			.criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
			.criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
			.offerTo(exporter);

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DATA_TABLET)
			.pattern("///")
			.pattern("/B/")
			.pattern("///")
			.input('/', Items.STICK)
			.input('B', Items.BOOK)
			.criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
			.criterion(hasItem(Items.BOOK), conditionsFromItem(Items.BOOK))
			.criterion(hasItem(ModItems.METAL_DETECTOR), conditionsFromItem(ModItems.METAL_DETECTOR))
			.offerTo(exporter);

		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.PINK_GARNET_BOW)
			.pattern(" IS")
			.pattern("X S")
			.pattern(" IS")
			.input('I', Items.STICK)
			.input('S', Items.STRING)
			.input('X', ModItems.PINK_GARNET)
			.criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
			.offerTo(exporter);

		new GemEmpoweringRecipeBuilder(ModItems.RAW_PINK_GARNET, ModItems.PINK_GARNET, 3)
			.criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
			.offerTo(exporter);

		new GemEmpoweringRecipeBuilder(Items.STICK, Items.END_ROD, 1)
			.criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
			.offerTo(exporter);

		new GemEmpoweringRecipeBuilder(Items.COAL, Items.DIAMOND, 7)
			.criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
			.offerTo(exporter);

		new GemEmpoweringRecipeBuilder(Blocks.PRISMARINE, Items.COOKED_CHICKEN, 12)
			.criterion(hasItem(Blocks.PRISMARINE), conditionsFromItem(Blocks.PRISMARINE))
			.offerTo(exporter);
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
				ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, sword)
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

	/**
	 * Create recipe for on type of armor
	 * @param builder {@link ShapelessRecipeJsonBuilder} in which the pattern has already been set, and contains only
	 * 'X' characters for baseItem
	 */
	private void offerArmor(RecipeExporter exporter, ItemConvertible baseItem, ShapedRecipeJsonBuilder builder) {
		builder
			.input('X', baseItem)
			.criterion(hasItem(baseItem), conditionsFromItem(baseItem))
			.offerTo(exporter);
	}

	/**
	 * Create recipes for common armor items
	 */
	private void offerArmorset(RecipeExporter exporter,
		ItemConvertible baseItem,
		ItemConvertible helmet,
		ItemConvertible chestplate,
		ItemConvertible leggings,
		ItemConvertible boots) {
		if (helmet != null) {
			offerArmor(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, helmet)
					.pattern("XXX")
					.pattern("X X")
					.pattern("   "));
		}
		if (chestplate != null) {
			offerArmor(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, chestplate)
					.pattern("X X")
					.pattern("XXX")
					.pattern("XXX"));
		}
		if (leggings != null) {
			offerArmor(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, leggings)
					.pattern("XXX")
					.pattern("X X")
					.pattern("X X"));
		}
		if (boots != null) {
			offerArmor(exporter, baseItem,
				ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, boots)
					.pattern("   ")
					.pattern("X X")
					.pattern("X X"));
		}
	}

}
