package net.kaupenjoe.mccourse.item;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.custom.MetalDetectorItem;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import org.jetbrains.annotations.NotNull;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
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
	public static Item METAL_DETECTOR;
	public static Item CAULIFLOWER;
	public static Item PEAT_BRICK;

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new ModIdentifier(name), item);
	}

	private static void itemGroupIngredients(@NotNull FabricItemGroupEntries entries) {
		entries.add(PINK_GARNET);
		entries.add(RAW_PINK_GARNET);
		entries.add(ModBlocks.PINK_GARNET_BLOCK);
		entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
	}

	public static void registerModItems() {
		MCCourseMod.LOGGER.info("Registering Mod Items for " + MCCourseMod.MOD_ID);
		// Create and register items
		PINK_GARNET = registerItem("pink_garnet",
			new Item(new FabricItemSettings()));
		RAW_PINK_GARNET = registerItem("raw_pink_garnet",
			new Item(new FabricItemSettings()));
		METAL_DETECTOR = registerItem("metal_detector",
			new MetalDetectorItem(new FabricItemSettings().maxDamage(256)));
		ModFoodComponents.CAULIFLOWER = new FoodComponent.Builder()
			.hunger(4)
			.saturationModifier(0.5f)
			.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING), 0.25f)
			.build();
		CAULIFLOWER = registerItem("cauliflower",
			new Item(new FabricItemSettings().food(ModFoodComponents.CAULIFLOWER)));
		PEAT_BRICK = registerItem("peat_brick",
			new Item(new FabricItemSettings()));
		// Add them to creative tabs
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
			.register(ModItems::itemGroupIngredients);
	}
}
