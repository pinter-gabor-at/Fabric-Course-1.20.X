package net.kaupenjoe.mccourse.world;

import java.util.List;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

public class ModPlacedFeatures {
	public static final RegistryKey<PlacedFeature> DRIFTWOOD_PLACED_KEY =
		registerKey("driftwood_placed");

	public static void bootstrap(Registerable<PlacedFeature> context) {
		var configuredFeatureRegistryEntryLookup =
			context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
		register(context, DRIFTWOOD_PLACED_KEY,
			configuredFeatureRegistryEntryLookup
				.getOrThrow(ModConfiguredFeatures.DRIFTWOOD_KEY),
			VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
				PlacedFeatures
					.createCountExtraModifier(1, 0.1f, 2),
				ModBlocks.DRIFTWOOD_SAPLING));
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
