package net.kaupenjoe.mccourse.world;

import java.util.List;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import net.kaupenjoe.mccourse.world.tree.custom.DriftwoodFoliagePlacer;
import net.kaupenjoe.mccourse.world.tree.custom.DriftwoodTrunkPlacer;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.GeodeCrackConfig;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.GeodeLayerConfig;
import net.minecraft.world.gen.feature.GeodeLayerThicknessConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {
	public static final RegistryKey<ConfiguredFeature<?, ?>> DRIFTWOOD_KEY =
		registerKey("driftwood");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_GARNET_ORE_KEY =
		registerKey("pink_garnet_ore");
	public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_PINK_GARNET_ORE_KEY =
		registerKey("nether_pink_garnet_ore");
	public static final RegistryKey<ConfiguredFeature<?, ?>> END_PINK_GARNET_ORE_KEY =
		registerKey("end_pink_garnet_ore");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PETUNIA_KEY =
		registerKey("petunia");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_GARNET_GEODE_KEY =
		registerKey("pink_garnet_geode");

	public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
		bootstrapTree(context);
		bootstrapOre(context);
		bootstrapFlower(context);
		bootstrapGeode(context);
	}

	protected static void bootstrapTree(Registerable<ConfiguredFeature<?, ?>> context) {
		register(context, DRIFTWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
			BlockStateProvider.of(ModBlocks.DRIFTWOOD_LOG),
			new DriftwoodTrunkPlacer(5, 6, 3),
			BlockStateProvider.of(ModBlocks.DRIFTWOOD_LEAVES),
			new DriftwoodFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
			new TwoLayersFeatureSize(1, 0, 2))
			.dirtProvider(BlockStateProvider.of(Blocks.END_STONE))
			.build());
	}

	protected static void bootstrapOre(Registerable<ConfiguredFeature<?, ?>> context) {
		RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
		RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
		RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);
		List<OreFeatureConfig.Target> overworldPinkGarnetOres =
			List.of(OreFeatureConfig
					.createTarget(stoneReplaceables, ModBlocks.PINK_GARNET_ORE.getDefaultState()),
				OreFeatureConfig
					.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_PINK_GARNET_ORE.getDefaultState()));
		List<OreFeatureConfig.Target> netherPinkGarnetOres =
			List.of(OreFeatureConfig
				.createTarget(netherReplaceables, ModBlocks.NETHER_PINK_GARNET_ORE.getDefaultState()));
		List<OreFeatureConfig.Target> endPinkGarnetOres =
			List.of(OreFeatureConfig
				.createTarget(endReplaceables, ModBlocks.END_STONE_PINK_GARNET_ORE.getDefaultState()));
		register(context, PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPinkGarnetOres, 12));
		register(context, NETHER_PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherPinkGarnetOres, 9));
		register(context, END_PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(endPinkGarnetOres, 8));
	}

	protected static void bootstrapFlower(Registerable<ConfiguredFeature<?, ?>> context) {
		register(context, PETUNIA_KEY, Feature.FLOWER,
			new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PETUNIA)))));
	}

	protected static void bootstrapGeode(Registerable<ConfiguredFeature<?, ?>> context) {
		register(context, PINK_GARNET_GEODE_KEY, Feature.GEODE,
			new GeodeFeatureConfig(new GeodeLayerConfig(BlockStateProvider.of(Blocks.AIR),
				BlockStateProvider.of(Blocks.DEEPSLATE),
				BlockStateProvider.of(ModBlocks.PINK_GARNET_ORE),
				BlockStateProvider.of(Blocks.DIRT),
				BlockStateProvider.of(Blocks.EMERALD_BLOCK),
				List.of(ModBlocks.PINK_GARNET_BLOCK.getDefaultState()),
				BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
				new GeodeLayerThicknessConfig(1.7D, 1.2D, 2.5D, 3.5D),
				new GeodeCrackConfig(0.25D, 1.5D, 1),
				0.5D, 0.1D,
				true, UniformIntProvider.create(3, 8),
				UniformIntProvider.create(2, 6), UniformIntProvider.create(1, 2),
				-18, 18, 0.075D, 1));
	}


	public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new ModIdentifier(name));
	}

	@SuppressWarnings("SameParameterValue")
	private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
		Registerable<ConfiguredFeature<?, ?>> context,
		RegistryKey<ConfiguredFeature<?, ?>> key,
		F feature,
		FC configuration) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}
}
