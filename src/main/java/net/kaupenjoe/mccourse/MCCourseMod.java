package net.kaupenjoe.mccourse;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.enchantment.ModEnchantments;
import net.kaupenjoe.mccourse.item.ModItemGroup;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.kaupenjoe.mccourse.util.ModRegistries;
import net.kaupenjoe.mccourse.util.ModTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class MCCourseMod implements ModInitializer {
	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModTags.registerModTags();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModRegistries.registerModStuffs();
		ModEnchantments.registerModEnchantments();
		ModSounds.registerSounds();
	}
}
