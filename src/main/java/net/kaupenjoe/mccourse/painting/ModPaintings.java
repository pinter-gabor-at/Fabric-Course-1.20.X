package net.kaupenjoe.mccourse.painting;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModPaintings {
	public static Painting SAW_THEM;
	public static Painting SHRIMP;
	public static Painting WORLD;

	private static Painting registerPainting(Painting painting) {
		return Registry.register(Registries.PAINTING_VARIANT, painting.getId(), painting);
	}

	public static void registerPaintings() {
		MCCourseMod.LOGGER.info("Registering Paintings for " + MCCourseMod.MOD_ID);
		SAW_THEM = registerPainting(new Painting(new ModIdentifier("saw_them"), 16, 16));
		SHRIMP = registerPainting(new Painting(new ModIdentifier("shrimp"), 32, 16));
		WORLD = registerPainting(new Painting(new ModIdentifier("world"), 32, 32));
	}
}
