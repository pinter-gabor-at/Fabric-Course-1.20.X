package net.kaupenjoe.mccourse.util;

import net.kaupenjoe.mccourse.MCCourseMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
	public static void registerModTags() {
		Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS =
			Blocks.createBlockTag("metal_detector_detectable_blocks");
	}

    public static class Blocks {
		public static TagKey<Block> METAL_DETECTOR_DETECTABLE_BLOCKS;
        public static final TagKey<Block> PAXEL_MINEABLE =
                createBlockTag("mineable/paxel");

		@SuppressWarnings({"SameParameterValue", "unused"})
        private static TagKey<Block> createBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(MCCourseMod.MOD_ID, name));
        }

		@SuppressWarnings({"SameParameterValue", "unused"})
        private static TagKey<Block> createCommonBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }

    public static class Items {
		@SuppressWarnings({"SameParameterValue", "unused"})
        private static TagKey<Item> createItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MCCourseMod.MOD_ID, name));
        }

		@SuppressWarnings({"SameParameterValue", "unused"})
        private static TagKey<Item> createCommonItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }
}
