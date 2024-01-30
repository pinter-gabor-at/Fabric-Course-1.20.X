package net.kaupenjoe.mccourse.item;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.item.custom.DataTabletItem;
import net.kaupenjoe.mccourse.item.custom.MetalDetectorItem;
import net.kaupenjoe.mccourse.item.custom.ModArmorItem;
import net.kaupenjoe.mccourse.item.custom.ModPoisonSwordItem;
import net.kaupenjoe.mccourse.item.custom.PaxelItem;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import org.jetbrains.annotations.NotNull;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SignItem;
import net.minecraft.item.SpawnEggItem;
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
	public static Item PINK_GARNET_SWORD;
	public static Item PINK_GARNET_PICKAXE;
	public static Item PINK_GARNET_SHOVEL;
	public static Item PINK_GARNET_AXE;
	public static Item PINK_GARNET_HOE;
	public static Item PINK_GARNET_PAXEL;
	public static Item PINK_GARNET_HELMET;
	public static Item PINK_GARNET_CHESTPLATE;
	public static Item PINK_GARNET_LEGGINGS;
	public static Item PINK_GARNET_BOOTS;
	public static Item PINK_GARNET_HORSE_ARMOR;
	public static Item DATA_TABLET;
	public static Item CAULIFLOWER_SEEDS;
	public static Item BAR_BRAWL_MUSIC_DISC;
	public static Item RADIATION_STAFF;
	public static Item PINK_GARNET_BOW;
	public static Item PINK_GARNET_SHIELD;
	public static Item DRIFTWOOD_SIGN;
	public static Item DRIFTWOOD_HANGING_SIGN;
	public static Item PORCUPINE_SPAWN_EGG;

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
		PINK_GARNET_SWORD = registerItem("pink_garnet_sword",
			new ModPoisonSwordItem(ModToolMaterial.PINK_GARNET, 2, 2f, new FabricItemSettings()));
		PINK_GARNET_PICKAXE = registerItem("pink_garnet_pickaxe",
			new PickaxeItem(ModToolMaterial.PINK_GARNET, 1, 1f, new FabricItemSettings()));
		PINK_GARNET_SHOVEL = registerItem("pink_garnet_shovel",
			new ShovelItem(ModToolMaterial.PINK_GARNET, 0, 0f, new FabricItemSettings()));
		PINK_GARNET_AXE = registerItem("pink_garnet_axe",
			new AxeItem(ModToolMaterial.PINK_GARNET, 6, -2f, new FabricItemSettings()));
		PINK_GARNET_HOE = registerItem("pink_garnet_hoe",
			new HoeItem(ModToolMaterial.PINK_GARNET, 0, 0f, new FabricItemSettings()));
		PINK_GARNET_PAXEL = registerItem("pink_garnet_paxel",
			new PaxelItem(ModToolMaterial.PINK_GARNET, 0, 0f, new FabricItemSettings()));
		PINK_GARNET_HELMET = registerItem("pink_garnet_helmet",
			new ModArmorItem(ModArmorMaterials.PINK_GARNET, ArmorItem.Type.HELMET, new FabricItemSettings()));
		PINK_GARNET_CHESTPLATE = registerItem("pink_garnet_chestplate",
			new ModArmorItem(ModArmorMaterials.PINK_GARNET, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
		PINK_GARNET_LEGGINGS = registerItem("pink_garnet_leggings",
			new ModArmorItem(ModArmorMaterials.PINK_GARNET, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
		PINK_GARNET_BOOTS = registerItem("pink_garnet_boots",
			new ModArmorItem(ModArmorMaterials.PINK_GARNET, ArmorItem.Type.BOOTS, new FabricItemSettings()));
		PINK_GARNET_HORSE_ARMOR = registerItem("pink_garnet_horse_armor",
			new HorseArmorItem(14, "pink_garnet", new FabricItemSettings()));
		DATA_TABLET = registerItem("data_tablet",
			new DataTabletItem(new FabricItemSettings().maxCount(1)));
		CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
			new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new FabricItemSettings()));
		BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
			new MusicDiscItem(9, ModSounds.BAR_BRAWL, new FabricItemSettings().maxCount(1), 122));
		RADIATION_STAFF = registerItem("radiation_staff",
			new Item(new FabricItemSettings()));
		PINK_GARNET_BOW = registerItem("pink_garnet_bow",
			new BowItem(new FabricItemSettings().maxDamage(500)));
		PINK_GARNET_SHIELD = registerItem("pink_garnet_shield",
			new ShieldItem(new FabricItemSettings().maxDamage(500)));
		DRIFTWOOD_SIGN = registerItem("driftwood_sign",
			new SignItem(
				new FabricItemSettings().maxCount(16),
				ModBlocks.DRIFTWOOD_SIGN,
				ModBlocks.DRIFTWOOD_WALL_SIGN));
		DRIFTWOOD_HANGING_SIGN = registerItem("driftwood_hanging_sign",
			new HangingSignItem(
				ModBlocks.DRIFTWOOD_HANGING_SIGN,
				ModBlocks.DRIFTWOOD_HANGING_WALL_SIGN,
				new FabricItemSettings().maxCount(16)));
		PORCUPINE_SPAWN_EGG = registerItem("porcupine_spawn_egg",
			new SpawnEggItem(ModEntities.PORCUPINE, 0xa86518, 0x3b260f, new FabricItemSettings()));
		// Add them to creative tabs
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
			.register(ModItems::itemGroupIngredients);
	}
}
