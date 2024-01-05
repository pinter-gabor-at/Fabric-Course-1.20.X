package net.kaupenjoe.mccourse.datagen;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.custom.CauliflowerCropBlock;
import net.kaupenjoe.mccourse.block.custom.PinkGarnetLampBlock;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		BlockStateModelGenerator.BlockTexturePool pinkGarnetTexturePool =
			blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PINK_GARNET_BLOCK);
		blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PINK_GARNET_BLOCK);
		blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_ORE);
		blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
		blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_STONE_PINK_GARNET_ORE);
		blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_PINK_GARNET_ORE);
		blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SOUND_BLOCK);
		pinkGarnetTexturePool.stairs(ModBlocks.PINK_GARNET_STAIRS);
		pinkGarnetTexturePool.slab(ModBlocks.PINK_GARNET_SLAB);
		pinkGarnetTexturePool.button(ModBlocks.PINK_GARNET_BUTTON);
		pinkGarnetTexturePool.pressurePlate(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
		pinkGarnetTexturePool.fence(ModBlocks.PINK_GARNET_FENCE);
		pinkGarnetTexturePool.fenceGate(ModBlocks.PINK_GARNET_FENCE_GATE);
		pinkGarnetTexturePool.wall(ModBlocks.PINK_GARNET_WALL);
		blockStateModelGenerator.registerDoor(ModBlocks.PINK_GARNET_DOOR);
		blockStateModelGenerator.registerTrapdoor(ModBlocks.PINK_GARNET_TRAPDOOR);
		registerCustomLamp(blockStateModelGenerator);
		blockStateModelGenerator.registerCrop(ModBlocks.CAULIFLOWER_CROP,
			CauliflowerCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
		blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.PETUNIA,
			ModBlocks.POTTED_PETUNIA, BlockStateModelGenerator.TintType.NOT_TINTED);
	}

	private void registerCustomLamp(BlockStateModelGenerator blockStateModelGenerator) {
		// Submodel ("on")
		Identifier idOn = blockStateModelGenerator
			.createSubModel(ModBlocks.PINK_GARNET_LAMP_BLOCK, "_on", Models.CUBE_ALL, TextureMap::all);
		// Model (default)
		Identifier id = TexturedModel.CUBE_ALL
			.upload(ModBlocks.PINK_GARNET_LAMP_BLOCK, blockStateModelGenerator.modelCollector);
		// Generate
		blockStateModelGenerator.blockStateCollector
			.accept(VariantsBlockStateSupplier.create(ModBlocks.PINK_GARNET_LAMP_BLOCK)
				.coordinate(BlockStateModelGenerator
					.createBooleanModelMap(PinkGarnetLampBlock.CLICKED, idOn, id)));
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
		itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);
		itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
		itemModelGenerator.register(ModItems.PEAT_BRICK, Models.GENERATED);
		itemModelGenerator.register(ModItems.METAL_DETECTOR, Models.GENERATED);
		itemModelGenerator.register(ModItems.PINK_GARNET_SWORD, Models.HANDHELD);
		itemModelGenerator.register(ModItems.PINK_GARNET_PICKAXE, Models.HANDHELD);
		itemModelGenerator.register(ModItems.PINK_GARNET_SHOVEL, Models.HANDHELD);
		itemModelGenerator.register(ModItems.PINK_GARNET_AXE, Models.HANDHELD);
		itemModelGenerator.register(ModItems.PINK_GARNET_HOE, Models.HANDHELD);
		itemModelGenerator.register(ModItems.PINK_GARNET_PAXEL, Models.HANDHELD);
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_HELMET));
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_CHESTPLATE));
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_LEGGINGS));
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_BOOTS));
		itemModelGenerator.register(ModItems.PINK_GARNET_HORSE_ARMOR, Models.GENERATED);
		itemModelGenerator.register(ModItems.BAR_BRAWL_MUSIC_DISC, Models.GENERATED);
		registerItemOnOff(itemModelGenerator, ModItems.DATA_TABLET);
	}

	/**
	 * Register an item which has two models: a default and an "on"
	 */
	private void registerItemOnOff(ItemModelGenerator itemModelGenerator, Item item) {
		// Submodel ("on")
		itemModelGenerator.register(item, "_on", Models.GENERATED);
		// Model (default)
		Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(item),
			itemModelGenerator.writer, this::createOnJson);
	}

	/**
	 * Used by {@link #registerItemOnOff(ItemModelGenerator, Item)}
	 */
	private JsonObject createOnJson(Identifier id, Map<TextureKey, Identifier> textures) {
		// "predicate": {}
		JsonObject jsonPredicate = new JsonObject();
		jsonPredicate.addProperty(new ModIdentifier("on").toString(), 1);
		// { "model", "predicate" }
		JsonObject jsonOverride = new JsonObject();
		jsonOverride.add("predicate", jsonPredicate);
		jsonOverride.addProperty("model", id.toString() + "_on");
		// "overrides": []
		JsonArray jsonOverrides = new JsonArray();
		jsonOverrides.add(jsonOverride);
		// { parent, overrides, textures }
		JsonObject jsonObject = Models.GENERATED.createJson(id, textures);
		jsonObject.add("overrides", jsonOverrides);
		return jsonObject;
	}
}
