package net.kaupenjoe.mccourse.util;

import net.kaupenjoe.mccourse.item.ModItems;

import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;

public class ModLootTableModifiers {
	private static final Identifier IGLOO_STRUCTURE_CHEST_ID
		= new Identifier("minecraft", "chests/igloo_chest");
	private static final Identifier CREEPER_ID
		= new Identifier("minecraft", "entities/creeper");

	public static void modifyLootTables() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (Blocks.SHORT_GRASS.getLootTableId().equals(id)) {
				// Adds Cauliflower Seeds to the grass loot table.
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.35f)) // Drops 35% of the time
					.with(ItemEntry.builder(ModItems.CAULIFLOWER_SEEDS))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)).build());
				tableBuilder.pool(poolBuilder.build());
			}
			if (IGLOO_STRUCTURE_CHEST_ID.equals(id)) {
				// Adds Metal Detector to the Igloo loot table.
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time
					.with(ItemEntry.builder(ModItems.METAL_DETECTOR))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());
				tableBuilder.pool(poolBuilder.build());
			}
			if (CREEPER_ID.equals(id)) {
				// Adds Peat Brick to the Creeper Loot table.
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.85f)) // Drops 85% of the time
					.with(ItemEntry.builder(ModItems.PEAT_BRICK))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)).build());
				tableBuilder.pool(poolBuilder.build());
			}
		});
	}
}
