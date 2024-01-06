package net.kaupenjoe.mccourse.datagen.util;

import java.util.Optional;

import net.kaupenjoe.mccourse.block.custom.PinkGarnetLampBlock;
import net.kaupenjoe.mccourse.datagen.ModModelProvider;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

/**
 * Used by {@link ModModelProvider} to register complicated models
 */
public final class RegisterModel {

	/**
	 * Register a custom lamp
	 * @param block the lamp block
	 */
	public static void registerCustomLamp(BlockStateModelGenerator generator, Block block) {
		// Submodel ("on")
		Identifier idOn = generator
			.createSubModel(block, "_on", Models.CUBE_ALL, TextureMap::all);
		// Model (default)
		Identifier id = TexturedModel.CUBE_ALL
			.upload(block, generator.modelCollector);
		// Generate
		generator.blockStateCollector
			.accept(VariantsBlockStateSupplier.create(block)
				.coordinate(BlockStateModelGenerator
					.createBooleanModelMap(PinkGarnetLampBlock.CLICKED, idOn, id)));
	}

	/**
	 * Register an item which has two models: a default and an "on"
	 */
	public static void registerItemOnOff(ItemModelGenerator generator, Item item) {
		// Submodel ("on")
		Model m = new Model(Optional.of(ModelIds.getItemModelId(item)), Optional.empty(), TextureKey.LAYER0);
		generator.register(item, "_on", m);
		// Model (default)
		Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(item),
			generator.writer, CreateExtraJson::createOnJson);
	}
}
