package net.kaupenjoe.mccourse.item;

import net.kaupenjoe.mccourse.block.ModBlocks;
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
				}).build());
	}
}
