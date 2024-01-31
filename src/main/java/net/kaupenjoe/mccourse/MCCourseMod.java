package net.kaupenjoe.mccourse;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.effect.ModEffects;
import net.kaupenjoe.mccourse.enchantment.ModEnchantments;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.item.ModItemGroup;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.painting.ModPaintings;
import net.kaupenjoe.mccourse.particle.ModParticles;
import net.kaupenjoe.mccourse.potion.ModPotions;
import net.kaupenjoe.mccourse.recipe.ModRecipes;
import net.kaupenjoe.mccourse.screen.ModScreenHandlers;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.kaupenjoe.mccourse.util.ModLootTableModifiers;
import net.kaupenjoe.mccourse.util.ModRegistries;
import net.kaupenjoe.mccourse.util.ModTags;
import net.kaupenjoe.mccourse.villager.ModVillagers;
import net.kaupenjoe.mccourse.world.gen.ModWorldGeneration;
import net.kaupenjoe.mccourse.world.tree.ModFoliagePlacerTypes;
import net.kaupenjoe.mccourse.world.tree.ModTrunkPlacerTypes;
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
		ModSounds.registerSounds();
		ModEntities.registerModEntities();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModEnchantments.registerModEnchantments();
		ModLootTableModifiers.modifyLootTables();
		ModPaintings.registerPaintings();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModParticles.registerParticles();
		ModRegistries.registerModStuffs();
		ModVillagers.registerVillagers();
		ModFluids.registerFluids();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandler();
		ModRecipes.registerRecipes();
		ModWorldGeneration.generateModWorldGeneration();
		ModTrunkPlacerTypes.register();
		ModFoliagePlacerTypes.register();
	}
}
