package net.kaupenjoe.mccourse.item.custom;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.kaupenjoe.mccourse.item.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModArmorItem extends ArmorItem {
	/**
	 * Map {@link ArmorMaterial} to corresponding {@link StatusEffectInstance}
	 */
	protected static Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP;

	public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
		super(material, type, settings);
		MATERIAL_TO_EFFECT_MAP =
			new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>()
				.put(ModArmorMaterials.PINK_GARNET, new StatusEffectInstance(StatusEffects.HASTE, 400, 1))
				.build();
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClient() && entity instanceof PlayerEntity player) {
			if (hasFullSuitOfArmorOn(player)) {
				evaluateArmorEffects(player);
			}
		}

		super.inventoryTick(stack, world, entity, slot, selected);
	}

	/**
	 * If player wears a full armor, made entirely of {@link ArmorMaterial} and that {@link ArmorMaterial} has a
	 * corresponding {@link StatusEffectInstance} in {@link #MATERIAL_TO_EFFECT_MAP}, then apply that statuseffect
	 */
	private void evaluateArmorEffects(PlayerEntity player) {
		for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
			ArmorMaterial armorMaterial = entry.getKey();
			StatusEffectInstance statusEffect = entry.getValue();

			if (hasCorrectArmorOn(armorMaterial, player)) {
				applyStatusEffect(player, statusEffect);
				break;
			}
		}
	}

	/**
	 * Apply statusEffect to player
	 */
	private void applyStatusEffect(PlayerEntity player, StatusEffectInstance statusEffect) {
		boolean hasPlayerEffectAlready = player.hasStatusEffect(statusEffect.getEffectType());

		if (!hasPlayerEffectAlready) {
			player.addStatusEffect(new StatusEffectInstance(statusEffect.getEffectType(),
				statusEffect.getDuration(), statusEffect.getAmplifier()));
		}
	}

	/**
	 * @return {@link ArmorMaterial} if player wears a full armor made of the same material
	 */
	protected @Nullable ArmorMaterial getFullArmorMaterial(@NotNull PlayerEntity player) {
		ArmorMaterial armorMaterial = null;
		for (ItemStack armorStack : player.getArmorItems()) {
			Item item = armorStack.getItem();
			if (item instanceof ArmorItem) {
				if (armorMaterial == null) {
					armorMaterial = ((ArmorItem) item).getMaterial();
				} else if (armorMaterial != ((ArmorItem) item).getMaterial()) {
					return null;
				}
			} else {
				return null;
			}
		}
		return armorMaterial;
	}

	/**
	 * @return true if player wears a full armor made of armorMaterial
	 */
	@Deprecated
	private boolean hasCorrectArmorOn(ArmorMaterial armorMaterial, PlayerEntity player) {
		for (ItemStack armorStack : player.getArmorItems()) {
			if (!(armorStack.getItem() instanceof ArmorItem)) {
				return false;
			}
		}

		ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
		ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

		return helmet.getMaterial() == armorMaterial && chestplate.getMaterial() == armorMaterial &&
			leggings.getMaterial() == armorMaterial && boots.getMaterial() == armorMaterial;
	}

	/**
	 * @return true if the player wears a full armor
	 */
	@Deprecated
	private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack chestplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !boots.isEmpty() && !leggings.isEmpty()
			&& !chestplate.isEmpty() && !helmet.isEmpty();
	}
}
