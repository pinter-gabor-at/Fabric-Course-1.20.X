package net.kaupenjoe.mccourse.block;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.custom.CauliflowerCropBlock;
import net.kaupenjoe.mccourse.block.custom.PinkGarnetLampBlock;
import net.kaupenjoe.mccourse.block.custom.SoundBlock;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

public class ModBlocks {
	public static Block PINK_GARNET_BLOCK;
	public static Block RAW_PINK_GARNET_BLOCK;
	public static Block PINK_GARNET_ORE;
	public static Block DEEPSLATE_PINK_GARNET_ORE;
	public static Block END_STONE_PINK_GARNET_ORE;
	public static Block NETHER_PINK_GARNET_ORE;
	public static Block SOUND_BLOCK;
	public static Block PINK_GARNET_STAIRS;
	public static Block PINK_GARNET_SLAB;
	public static Block PINK_GARNET_BUTTON;
	public static Block PINK_GARNET_PRESSURE_PLATE;
	public static Block PINK_GARNET_FENCE;
	public static Block PINK_GARNET_FENCE_GATE;
	public static Block PINK_GARNET_WALL;
	public static Block PINK_GARNET_DOOR;
	public static Block PINK_GARNET_TRAPDOOR;
	public static Block PINK_GARNET_LAMP_BLOCK;
	public static Block CAULIFLOWER_CROP;
	public static Block PETUNIA;
	public static Block POTTED_PETUNIA;

	/**
	 * Register {@link Block} <b>without</b> a corresponding {@link BlockItem}
	 * @param name name of the {@link Block}
	 * @param block the {@link Block}
	 * @return the {@link Block}
	 */
	@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
		return Registry.register(Registries.BLOCK, new ModIdentifier(name), block);
    }

	/**
	 * Register {@link Block} and a corresponding {@link BlockItem}
	 * @param name name of the {@link Block}
	 * @param block the {@link Block}
	 * @return the {@link Block}
	 */
	@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new ModIdentifier(name), block);
    }

	/**
	 * Register a {@link BlockItem}
	 * @param name name of the {@link Block}
	 * @param block the {@link Block}
	 * @return the {@link Block}
	 */
	@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registries.ITEM, new ModIdentifier(name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        MCCourseMod.LOGGER.info("Registering ModBlocks for " + MCCourseMod.MOD_ID);
		PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
			new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
			new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		PINK_GARNET_ORE = registerBlock("pink_garnet_ore",
			new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
				FabricBlockSettings.copyOf(Blocks.STONE)));
		DEEPSLATE_PINK_GARNET_ORE = registerBlock("deepslate_pink_garnet_ore",
			new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
				FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
		END_STONE_PINK_GARNET_ORE = registerBlock("end_stone_pink_garnet_ore",
			new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
				FabricBlockSettings.copyOf(Blocks.END_STONE)));
		NETHER_PINK_GARNET_ORE = registerBlock("nether_pink_garnet_ore",
			new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
				FabricBlockSettings.copyOf(Blocks.NETHERRACK)));
		SOUND_BLOCK = registerBlock("sound_block",
			new SoundBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		PINK_GARNET_STAIRS = registerBlock("pink_garnet_stairs",
			new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(),
				FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		PINK_GARNET_SLAB = registerBlock("pink_garnet_slab",
			new SlabBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		PINK_GARNET_BUTTON = registerBlock("pink_garnet_button",
			new ButtonBlock(BlockSetType.IRON, 10,
				FabricBlockSettings.copyOf(Blocks.STONE_BUTTON)));
		PINK_GARNET_PRESSURE_PLATE = registerBlock("pink_garnet_pressure_plate",
			new PressurePlateBlock(BlockSetType.IRON,
				FabricBlockSettings.copyOf(Blocks.STONE_PRESSURE_PLATE)));
		PINK_GARNET_FENCE = registerBlock("pink_garnet_fence",
			new FenceBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		PINK_GARNET_FENCE_GATE = registerBlock("pink_garnet_fence_gate",
			new FenceGateBlock(WoodType.ACACIA,
				FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		PINK_GARNET_WALL = registerBlock("pink_garnet_wall",
			new WallBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
		PINK_GARNET_DOOR = registerBlock("pink_garnet_door",
			new DoorBlock(BlockSetType.IRON,
				FabricBlockSettings.copyOf(Blocks.IRON_DOOR)));
		PINK_GARNET_TRAPDOOR = registerBlock("pink_garnet_trapdoor",
			new TrapdoorBlock(BlockSetType.IRON,
				FabricBlockSettings.copyOf(Blocks.IRON_TRAPDOOR)));
		PINK_GARNET_LAMP_BLOCK = registerBlock("pink_garnet_lamp_block",
			new PinkGarnetLampBlock(FabricBlockSettings.create()
				.mapColor(MapColor.RAW_IRON_PINK)
				.instrument(Instrument.BASEDRUM)
				.strength(4f)
				.requiresTool()
				.luminance(state -> state.get(PinkGarnetLampBlock.CLICKED) ? 15 : 0)));
		CAULIFLOWER_CROP = registerBlockWithoutBlockItem("cauliflower_crop",
			new CauliflowerCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
		PETUNIA = registerBlock("petunia",
			new FlowerBlock(StatusEffects.BAD_OMEN, 4, FabricBlockSettings.copyOf(Blocks.ALLIUM)));
		POTTED_PETUNIA = registerBlockWithoutBlockItem("potted_petunia",
			new FlowerPotBlock(PETUNIA, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM)));
    }
}
