package net.kaupenjoe.mccourse.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3d;

// Climbing Effect by SameDifferent: https://github.com/samedifferent/TrickOrTreat/blob/master/LICENSE
// MIT License!
public class SlimeyEffect extends StatusEffect {
	protected SlimeyEffect(StatusEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity.horizontalCollision) {
			Vec3d v = entity.getVelocity();
			entity.setVelocity(v.x * 0.92, 0.2, v.z * 0.92);
		}
		super.applyUpdateEffect(entity, amplifier);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
}
