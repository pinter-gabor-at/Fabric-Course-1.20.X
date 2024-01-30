package net.kaupenjoe.mccourse.item;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

public class ModItemGroup {
	public static ItemGroup PINK_GARNET_GROUP;

	public static void registerItemGroups() {
		PINK_GARNET_GROUP = Registry.register(Registries.ITEM_GROUP,
			new ModIdentifier("pink_garnet_group"),
			FabricItemGroup.builder()
				.displayName(Text.translatable("itemgroup.pink_garnet_group"))
				.icon(() -> new ItemStack(ModItems.PINK_GARNET))
				.entries((displayContext, entries) -> {
					entries.add(ModItems.PINK_GARNET);
					entries.add(ModItems.RAW_PINK_GARNET);
					entries.add(ModItems.METAL_DETECTOR);
					entries.add(ModItems.CAULIFLOWER);
					entries.add(ModItems.PEAT_BRICK);
					entries.add(ModItems.PINK_GARNET_SWORD);
					entries.add(ModItems.PINK_GARNET_PICKAXE);
					entries.add(ModItems.PINK_GARNET_SHOVEL);
					entries.add(ModItems.PINK_GARNET_AXE);
					entries.add(ModItems.PINK_GARNET_HOE);
					entries.add(ModItems.PINK_GARNET_PAXEL);
					entries.add(ModItems.PINK_GARNET_HELMET);
					entries.add(ModItems.PINK_GARNET_CHESTPLATE);
					entries.add(ModItems.PINK_GARNET_LEGGINGS);
					entries.add(ModItems.PINK_GARNET_BOOTS);
					entries.add(ModItems.PINK_GARNET_HORSE_ARMOR);
					entries.add(ModItems.DATA_TABLET);
					entries.add(ModItems.CAULIFLOWER_SEEDS);
					entries.add(ModItems.BAR_BRAWL_MUSIC_DISC);
					entries.add(ModItems.RADIATION_STAFF);
					entries.add(ModItems.PINK_GARNET_BOW);
					entries.add(ModItems.PINK_GARNET_SHIELD);
					entries.add(ModItems.DRIFTWOOD_SIGN);
					entries.add(ModItems.DRIFTWOOD_HANGING_SIGN);
					entries.add(ModItems.PORCUPINE_SPAWN_EGG);
					entries.add(ModFluids.SOAP_WATER_BUCKET);
					entries.add(ModBlocks.PINK_GARNET_BLOCK);
					entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
					entries.add(ModBlocks.PINK_GARNET_ORE);
					entries.add(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
					entries.add(ModBlocks.END_STONE_PINK_GARNET_ORE);
					entries.add(ModBlocks.NETHER_PINK_GARNET_ORE);
					entries.add(ModBlocks.SOUND_BLOCK);
					entries.add(ModBlocks.PINK_GARNET_STAIRS);
					entries.add(ModBlocks.PINK_GARNET_SLAB);
					entries.add(ModBlocks.PINK_GARNET_BUTTON);
					entries.add(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
					entries.add(ModBlocks.PINK_GARNET_FENCE);
					entries.add(ModBlocks.PINK_GARNET_FENCE_GATE);
					entries.add(ModBlocks.PINK_GARNET_WALL);
					entries.add(ModBlocks.PINK_GARNET_DOOR);
					entries.add(ModBlocks.PINK_GARNET_TRAPDOOR);
					entries.add(ModBlocks.PINK_GARNET_LAMP_BLOCK);
					entries.add(ModBlocks.PETUNIA);
					entries.add(ModBlocks.GEM_EMPOWERING_STATION);
					entries.add(ModBlocks.DRIFTWOOD_LOG);
					entries.add(ModBlocks.DRIFTWOOD_WOOD);
					entries.add(ModBlocks.STRIPPED_DRIFTWOOD_LOG);
					entries.add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD);
					entries.add(ModBlocks.DRIFTWOOD_PLANKS);
					entries.add(ModBlocks.DRIFTWOOD_SAPLING);
					entries.add(ModBlocks.DRIFTWOOD_LEAVES);
				}).build());
	}
}
