package net.kaupenjoe.mccourse.entity.ai;

import net.kaupenjoe.mccourse.entity.custom.PorcupineEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Hand;

public class PorcupineAttackGoal extends MeleeAttackGoal {
	private final PorcupineEntity entity;
	private int attackDelay = 20;
	private int ticksUntilNextAttack = 20;
	private boolean shouldCountTillNextAttack = false;

	public PorcupineAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
		super(mob, speed, pauseWhenMobIdle);
		entity = ((PorcupineEntity) mob);
	}

	@Override
	public void start() {
		super.start();
		attackDelay = 20;
		ticksUntilNextAttack = 20;
	}

	@Override
	protected void attack(LivingEntity target) {
		if (canAttack(target)) {
			shouldCountTillNextAttack = true;
			if (isTimeToStartAttackAnimation()) {
				entity.setAttacking(true);
			}
			if (isTimeToAttack()) {
				this.mob.getLookControl().lookAt(target.getX(), target.getEyeY(), target.getZ());
				performAttack(target);
			}
		} else {
			resetAttackCooldown();
			shouldCountTillNextAttack = false;
			entity.setAttacking(false);
			entity.attackAnimationTimeout = 0;
		}
	}

	protected void resetAttackCooldown() {
		this.ticksUntilNextAttack = this.getTickCount(attackDelay * 2);
	}

	protected boolean isTimeToStartAttackAnimation() {
		return this.ticksUntilNextAttack <= attackDelay;
	}

	protected boolean isTimeToAttack() {
		return this.ticksUntilNextAttack <= 0;
	}

	protected void performAttack(LivingEntity pEnemy) {
		this.resetAttackCooldown();
		this.mob.swingHand(Hand.MAIN_HAND);
		this.mob.tryAttack(pEnemy);
	}

	@Override
	public void tick() {
		super.tick();
		if (shouldCountTillNextAttack) {
			this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
		}
	}

	@Override
	public void stop() {
		entity.setAttacking(false);
		super.stop();
	}
}
