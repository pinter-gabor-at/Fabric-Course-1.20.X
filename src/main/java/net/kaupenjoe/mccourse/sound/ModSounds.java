package net.kaupenjoe.mccourse.sound;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
	public static SoundEvent METAL_DETECTOR_FOUND_ORE;
	public static SoundEvent PINK_GARNET_LAMP_BREAK;
	public static SoundEvent PINK_GARNET_LAMP_STEP;
	public static SoundEvent PINK_GARNET_LAMP_PLACE;
	public static SoundEvent PINK_GARNET_LAMP_HIT;
	public static SoundEvent PINK_GARNET_LAMP_FALL;
	public static BlockSoundGroup PINK_GARNET_LAMP_SOUNDS;

	private static SoundEvent registerSoundEvent(String name) {
		Identifier identifier = new ModIdentifier(name);
		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}

	public static void registerSounds() {
		MCCourseMod.LOGGER.info("Registering Mod Sounds for " + MCCourseMod.MOD_ID);
		METAL_DETECTOR_FOUND_ORE = registerSoundEvent("metal_detector_found_ore");
		PINK_GARNET_LAMP_BREAK = registerSoundEvent("pink_garnet_lamp_break");
		PINK_GARNET_LAMP_STEP = registerSoundEvent("pink_garnet_lamp_step");
		PINK_GARNET_LAMP_PLACE = registerSoundEvent("pink_garnet_lamp_place");
		PINK_GARNET_LAMP_HIT = registerSoundEvent("pink_garnet_lamp_hit");
		PINK_GARNET_LAMP_FALL = registerSoundEvent("pink_garnet_lamp_fall");
		PINK_GARNET_LAMP_SOUNDS = new BlockSoundGroup(1f, 1f,
			PINK_GARNET_LAMP_BREAK, PINK_GARNET_LAMP_STEP, PINK_GARNET_LAMP_PLACE,
			PINK_GARNET_LAMP_HIT, PINK_GARNET_LAMP_FALL);
	}
}
