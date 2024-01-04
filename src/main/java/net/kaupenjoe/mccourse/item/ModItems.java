package net.kaupenjoe.mccourse.item;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import org.jetbrains.annotations.NotNull;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class ModItems {
	public static Item PINK_GARNET;
	public static Item RAW_PINK_GARNET;

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new ModIdentifier(name), item);
	}

	private static void itemGroupIngredients(@NotNull FabricItemGroupEntries entries) {
		entries.add(PINK_GARNET);
		entries.add(RAW_PINK_GARNET);
	}

	public static void registerModItems() {
		MCCourseMod.LOGGER.info("Registering Mod Items for " + MCCourseMod.MOD_ID);
		// Create and register items
		PINK_GARNET = registerItem("pink_garnet",
			new Item(new FabricItemSettings()));
		RAW_PINK_GARNET = registerItem("raw_pink_garnet",
			new Item(new FabricItemSettings()));
		// Add them to creative tabs
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
			.register(ModItems::itemGroupIngredients);
	}
}
