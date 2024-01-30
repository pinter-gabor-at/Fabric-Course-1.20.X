package net.kaupenjoe.mccourse.enchantment;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEnchantments {
	public static Enchantment LIGHTNING_STRIKER;

	@SuppressWarnings("SameParameterValue")
	private static Enchantment register(String name, Enchantment enchantment) {
		return Registry.register(Registries.ENCHANTMENT, new ModIdentifier(name), enchantment);
	}

	public static void registerModEnchantments() {
		MCCourseMod.LOGGER.info("Registering ModEnchantments for " + MCCourseMod.MOD_ID);
		LIGHTNING_STRIKER = register("lightning_striker",
			new LightningStrikerEnchantment(Enchantment.Rarity.COMMON,
				EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND));
	}
}
