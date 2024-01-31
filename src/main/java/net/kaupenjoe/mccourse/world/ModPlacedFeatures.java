package net.kaupenjoe.mccourse.world;

import java.util.List;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {
	public static final RegistryKey<PlacedFeature> DRIFTWOOD_PLACED_KEY =
		registerKey("driftwood_placed");
	public static final RegistryKey<PlacedFeature> PINK_GARNET_ORE_PLACED_KEY =
		registerKey("pink_garnet_ore_placed");
	public static final RegistryKey<PlacedFeature> NETHER_PINK_GARNET_ORE_PLACED_KEY =
		registerKey("nether_pink_garnet_ore_placed");
	public static final RegistryKey<PlacedFeature> END_PINK_GARNET_ORE_PLACED_KEY =
		registerKey("end_pink_garnet_ore_placed");
	public static final RegistryKey<PlacedFeature> PETUNIA_PLACED_KEY =
		registerKey("petunia_placed");

	public static final RegistryKey<PlacedFeature> PINK_GARNET_GEODE_PLACED_KEY = registerKey("pink_garnet_geode_placed");

	public static void bootstrap(Registerable<PlacedFeature> context) {
		var lookup =
			context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
		bootstrapTree(context, lookup);
		bootstrapOre(context, lookup);
		bootstrapFlower(context, lookup);
		bootstrapGeode(context, lookup);
	}

	protected static void bootstrapTree(
		Registerable<PlacedFeature> context,
		RegistryEntryLookup<ConfiguredFeature<?, ?>> lookup) {
		register(context, DRIFTWOOD_PLACED_KEY,
			lookup
				.getOrThrow(ModConfiguredFeatures.DRIFTWOOD_KEY),
			VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
				PlacedFeatures
					.createCountExtraModifier(1, 0.1f, 2),
				ModBlocks.DRIFTWOOD_SAPLING));
	}

	protected static void bootstrapOre(
		Registerable<PlacedFeature> context,
		RegistryEntryLookup<ConfiguredFeature<?, ?>> lookup) {
		register(context, PINK_GARNET_ORE_PLACED_KEY,
			lookup.getOrThrow(ModConfiguredFeatures.PINK_GARNET_ORE_KEY),
			ModOrePlacement.modifiersWithCount(10,
				HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
		register(context, NETHER_PINK_GARNET_ORE_PLACED_KEY,
			lookup.getOrThrow(ModConfiguredFeatures.NETHER_PINK_GARNET_ORE_KEY),
			ModOrePlacement.modifiersWithCount(8, // Veins per Chunk
				HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
		register(context, END_PINK_GARNET_ORE_PLACED_KEY,
			lookup.getOrThrow(ModConfiguredFeatures.END_PINK_GARNET_ORE_KEY),
			ModOrePlacement.modifiersWithCount(8, // Veins per Chunk
				HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
	}

	protected static void bootstrapFlower(
		Registerable<PlacedFeature> context,
		RegistryEntryLookup<ConfiguredFeature<?, ?>> lookup) {
		register(context, PETUNIA_PLACED_KEY, lookup.getOrThrow(ModConfiguredFeatures.PETUNIA_KEY),
			RarityFilterPlacementModifier.of(4),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BiomePlacementModifier.of());
	}

	protected static void bootstrapGeode(
		Registerable<PlacedFeature> context,
		RegistryEntryLookup<ConfiguredFeature<?, ?>> lookup) {
		register(context, PINK_GARNET_GEODE_PLACED_KEY, lookup.getOrThrow(ModConfiguredFeatures.PINK_GARNET_GEODE_KEY),
			RarityFilterPlacementModifier.of(42),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.fixed(6), YOffset.fixed(40)),
			BiomePlacementModifier.of());
	}

	public static RegistryKey<PlacedFeature> registerKey(String name) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new ModIdentifier(name));
	}

	private static void register(
		Registerable<PlacedFeature> context,
		RegistryKey<PlacedFeature> key,
		RegistryEntry<ConfiguredFeature<?, ?>> configuration,
		List<PlacementModifier> modifiers) {
		context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
	}

	@SuppressWarnings("unused")
	private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
		Registerable<PlacedFeature> context,
		RegistryKey<PlacedFeature> key,
		RegistryEntry<ConfiguredFeature<?, ?>> configuration,
		PlacementModifier... modifiers) {
		register(context, key, configuration, List.of(modifiers));
	}
}
