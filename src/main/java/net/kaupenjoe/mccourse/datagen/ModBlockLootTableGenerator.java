package net.kaupenjoe.mccourse.datagen;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.custom.CauliflowerCropBlock;
import net.kaupenjoe.mccourse.item.ModItems;

import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
	public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		addDrop(ModBlocks.PINK_GARNET_BLOCK);
		addDrop(ModBlocks.RAW_PINK_GARNET_BLOCK);
		addDrop(ModBlocks.SOUND_BLOCK);
		addDrop(ModBlocks.PINK_GARNET_ORE,
			oreDrops(ModBlocks.PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
		addDrop(ModBlocks.DEEPSLATE_PINK_GARNET_ORE,
			oreDrops(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
		addDrop(ModBlocks.END_STONE_PINK_GARNET_ORE,
			oreDrops(ModBlocks.END_STONE_PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
		addDrop(ModBlocks.NETHER_PINK_GARNET_ORE,
			oreDrops(ModBlocks.NETHER_PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
		addDrop(ModBlocks.PINK_GARNET_STAIRS);
		addDrop(ModBlocks.PINK_GARNET_SLAB,
			slabDrops(ModBlocks.PINK_GARNET_SLAB));
		addDrop(ModBlocks.PINK_GARNET_BUTTON);
		addDrop(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
		addDrop(ModBlocks.PINK_GARNET_FENCE);
		addDrop(ModBlocks.PINK_GARNET_FENCE_GATE);
		addDrop(ModBlocks.PINK_GARNET_WALL);
		addDrop(ModBlocks.PINK_GARNET_TRAPDOOR);
		addDrop(ModBlocks.PINK_GARNET_DOOR, doorDrops(ModBlocks.PINK_GARNET_DOOR));

		BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition
			.builder(ModBlocks.CAULIFLOWER_CROP)
			.properties(StatePredicate.Builder.create()
				.exactMatch(CauliflowerCropBlock.AGE, 6));
		addDrop(ModBlocks.CAULIFLOWER_CROP,
			cropDrops(ModBlocks.CAULIFLOWER_CROP, ModItems.CAULIFLOWER, ModItems.CAULIFLOWER_SEEDS, builder2));
		// IF YOU ONLY WANT THE ITEM TO DROP FROM THE TOP BLOCK
		// BlockStatePropertyLootCondition.Builder builder3 = BlockStatePropertyLootCondition.builder(ModBlocks.CATTAIL_CROP)
		//         .properties(StatePredicate.Builder.create().exactMatch(CauliflowerCropBlock.AGE, 8));
		// this.addDrop(ModBlocks.CATTAIL_CROP, this.cropDrops(ModBlocks.CATTAIL_CROP, ModItems.CATTAIL, ModItems.CATTAIL_SEEDS, builder3));
		AnyOfLootCondition.Builder builder =
			BlockStatePropertyLootCondition.builder(ModBlocks.CATTAIL_CROP)
				.properties(StatePredicate.Builder.create()
					.exactMatch(CauliflowerCropBlock.AGE, 7))
				.or(BlockStatePropertyLootCondition.builder(ModBlocks.CATTAIL_CROP)
					.properties(StatePredicate.Builder.create()
						.exactMatch(CauliflowerCropBlock.AGE, 8)));
		addDrop(ModBlocks.CATTAIL_CROP,
			cropDrops(ModBlocks.CATTAIL_CROP, ModItems.CATTAIL, ModItems.CATTAIL_SEEDS, builder));
	}
}
