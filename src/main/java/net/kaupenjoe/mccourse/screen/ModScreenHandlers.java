package net.kaupenjoe.mccourse.screen;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

public class ModScreenHandlers {
	public static ScreenHandlerType<GemEmpoweringScreenHandler> GEM_EMPOWERING_SCREEN_HANDLER;

	public static void registerScreenHandler() {
		MCCourseMod.LOGGER.info("Registering Screen Handlers for " + MCCourseMod.MOD_ID);
		GEM_EMPOWERING_SCREEN_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER,
				new ModIdentifier("gem_empowering_screen_handler"),
				new ExtendedScreenHandlerType<>(GemEmpoweringScreenHandler::new));
	}
}
