package net.kaupenjoe.mccourse;

import net.kaupenjoe.mccourse.datagen.ModBlockLootTableGenerator;
import net.kaupenjoe.mccourse.datagen.ModBlockTagProvider;
import net.kaupenjoe.mccourse.datagen.ModItemTagProvider;
import net.kaupenjoe.mccourse.datagen.ModModelProvider;
import net.kaupenjoe.mccourse.datagen.ModPaintingVariantTagProvider;
import net.kaupenjoe.mccourse.datagen.ModRecipeGenerator;

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
	}
}
