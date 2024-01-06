package net.kaupenjoe.mccourse.util;

import static net.kaupenjoe.mccourse.mixin.BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.command.ReturnHomeCommand;
import net.kaupenjoe.mccourse.command.SetHomeCommand;
import net.kaupenjoe.mccourse.event.AttackEntityHandler;
import net.kaupenjoe.mccourse.event.PlayerCopyHandler;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.potion.ModPotions;

import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {
	public static void registerModStuffs() {
		registerFuels();
		registerModCompostables();
		registerCommands();
		registerEvents();
		registerPotionRecipes();
		registerCustomTrades();
	}

	private static void registerFuels() {
		FuelRegistry registry = FuelRegistry.INSTANCE;
		registry.add(ModItems.PEAT_BRICK, 200);
	}

	private static void registerModCompostables() {
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CAULIFLOWER, 0.5f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CAULIFLOWER_SEEDS, 0.25f);
	}

	private static void registerCommands() {
		CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
		CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
	}

	private static void registerEvents() {
		AttackEntityCallback.EVENT.register(new AttackEntityHandler());
		ServerPlayerEvents.COPY_FROM.register(new PlayerCopyHandler());
	}

	private static void registerPotionRecipes() {
		invokeRegisterPotionRecipe(
			Potions.AWKWARD, ModItems.PINK_GARNET, ModPotions.SLIMEY_POTION);
	}

	private static void registerCustomTrades() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 0,
			factories -> {
				MCCourseMod.LOGGER.info("add farmer");
				factories.add((entity, random) -> {
					MCCourseMod.LOGGER.info("exec farmer"); // TODO: Never called. But why?
					return new TradeOffer(
						new ItemStack(Items.EMERALD, 2),
						new ItemStack(ModItems.CAULIFLOWER, 2),
						6, 2, 0.02f
					);
				});
			});
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 3,
			factories -> {
				MCCourseMod.LOGGER.info("add toolsmith");
				factories.add((entity, random) -> {
					MCCourseMod.LOGGER.info("exec toolsmith"); // TODO: Never called. But why?
					return new TradeOffer(
						new ItemStack(Items.EMERALD, 16),
						new ItemStack(ModItems.PINK_GARNET_PAXEL, 1),
						2, 6, 0.08f
					);
				});
			});
	}
}
