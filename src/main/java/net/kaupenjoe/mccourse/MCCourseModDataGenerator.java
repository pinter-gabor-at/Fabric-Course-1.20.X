package net.kaupenjoe.mccourse;

import net.kaupenjoe.mccourse.datagen.ModAdvancementProvider;
import net.kaupenjoe.mccourse.datagen.ModBlockLootTableGenerator;
import net.kaupenjoe.mccourse.datagen.ModBlockTagProvider;
import net.kaupenjoe.mccourse.datagen.ModFluidTagProvider;
import net.kaupenjoe.mccourse.datagen.ModItemTagProvider;
import net.kaupenjoe.mccourse.datagen.ModModelProvider;
import net.kaupenjoe.mccourse.datagen.ModPOITagProvider;
import net.kaupenjoe.mccourse.datagen.ModPaintingVariantTagProvider;
import net.kaupenjoe.mccourse.datagen.ModRecipeGenerator;
import net.kaupenjoe.mccourse.datagen.ModWorldGenerator;
import net.kaupenjoe.mccourse.world.ModConfiguredFeatures;
import net.kaupenjoe.mccourse.world.ModPlacedFeatures;
import net.kaupenjoe.mccourse.world.biome.ModBiomes;

import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MCCourseModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockLootTableGenerator::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeGenerator::new);
		pack.addProvider(ModPaintingVariantTagProvider::new);
		pack.addProvider(ModAdvancementProvider::new);
		pack.addProvider(ModPOITagProvider::new);
		pack.addProvider(ModFluidTagProvider::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
	}
}
