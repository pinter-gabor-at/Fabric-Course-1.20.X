package net.kaupenjoe.mccourse.potion;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.effect.ModEffects;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModPotions {
	public static Potion SLIMEY_POTION;

	@SuppressWarnings("SameParameterValue")
	private static Potion registerPotion(String name, Potion potion) {
		return Registry.register(Registries.POTION, new ModIdentifier(name), potion);
	}

	public static void registerPotions() {
		MCCourseMod.LOGGER.info("Registering Potions for " + MCCourseMod.MOD_ID);
		SLIMEY_POTION = registerPotion("slimey_potion",
			new Potion(new StatusEffectInstance(ModEffects.SLIMEY, 200, 0)));
	}
}
