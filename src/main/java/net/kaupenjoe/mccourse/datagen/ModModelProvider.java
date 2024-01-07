package net.kaupenjoe.mccourse.datagen;

import static net.kaupenjoe.mccourse.datagen.util.RegisterModel.*;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.custom.CauliflowerCropBlock;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.item.ModItems;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

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
		registerCustomLamp(blockStateModelGenerator, ModBlocks.PINK_GARNET_LAMP_BLOCK);
		blockStateModelGenerator.registerCrop(ModBlocks.CAULIFLOWER_CROP,
			CauliflowerCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
		blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.PETUNIA,
			ModBlocks.POTTED_PETUNIA, BlockStateModelGenerator.TintType.NOT_TINTED);
		blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GEM_EMPOWERING_STATION);
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
		registerBow(itemModelGenerator, ModItems.PINK_GARNET_BOW);
		itemModelGenerator.register(ModFluids.SOAP_WATER_BUCKET, Models.GENERATED);
	}
}
