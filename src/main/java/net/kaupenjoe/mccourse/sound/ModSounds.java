package net.kaupenjoe.mccourse.sound;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
	public static SoundEvent METAL_DETECTOR_FOUND_ORE;

	@SuppressWarnings("SameParameterValue")
	private static SoundEvent registerSoundEvent(String name) {
		Identifier identifier = new ModIdentifier(name);
		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}

	public static void registerSounds() {
		MCCourseMod.LOGGER.info("Registering Mod Sounds for " + MCCourseMod.MOD_ID);
		METAL_DETECTOR_FOUND_ORE = registerSoundEvent("metal_detector_found_ore");
	}
}
