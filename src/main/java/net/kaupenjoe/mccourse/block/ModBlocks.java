package net.kaupenjoe.mccourse.block;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

public class ModBlocks {
	public static Block PINK_GARNET_BLOCK;
	public static Block RAW_PINK_GARNET_BLOCK;

	/**
	 * Register {@link Block} and a corresponding {@link BlockItem}
	 * @param name name of the {@link Block}
	 * @param block the {@link Block}
	 * @return the {@link Block}
	 */
	@SuppressWarnings("UnusedReturnValue")
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
	@SuppressWarnings("UnusedReturnValue")
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
	}
}
