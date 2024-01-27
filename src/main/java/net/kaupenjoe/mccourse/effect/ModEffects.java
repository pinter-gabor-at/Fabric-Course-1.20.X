package net.kaupenjoe.mccourse.effect;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEffects {
	public static StatusEffect SLIMEY;

	@SuppressWarnings("SameParameterValue")
	private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
		return Registry.register(Registries.STATUS_EFFECT, new ModIdentifier(name), statusEffect);
	}

	public static void registerEffects() {
		MCCourseMod.LOGGER.info("Registering Mod Effects for " + MCCourseMod.MOD_ID);
		SLIMEY = registerStatusEffect("slimey",
			new SlimeyEffect(StatusEffectCategory.NEUTRAL, 0x36ebab)
				.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
					"7107DE5E-7CE8-4030-940E-514C1F160890", -0.25f,
					EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
	}
}
