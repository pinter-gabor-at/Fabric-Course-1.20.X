package net.kaupenjoe.mccourse.entity;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.DiceProjectileEntity;
import net.kaupenjoe.mccourse.entity.custom.MagicProjectileEntity;
import net.kaupenjoe.mccourse.entity.custom.PorcupineEntity;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

public class ModEntities {
	public static EntityType<PorcupineEntity> PORCUPINE;
	public static EntityType<DiceProjectileEntity> THROWN_DICE_PROJECTILE;
	public static EntityType<MagicProjectileEntity> MAGIC_PROJECTILE;

	public static void registerModEntities() {
		MCCourseMod.LOGGER.info("Registering Mod Entities for " + MCCourseMod.MOD_ID);
		PORCUPINE = Registry.register(Registries.ENTITY_TYPE,
			new ModIdentifier("porcupine"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PorcupineEntity::new)
				.dimensions(EntityDimensions.fixed(0.75f, 0.75f))
				.build());
		THROWN_DICE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
			new ModIdentifier("dice_projectile"),
			FabricEntityTypeBuilder.<DiceProjectileEntity>create(SpawnGroup.MISC, DiceProjectileEntity::new)
				.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
				.build());
		MAGIC_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
			new ModIdentifier("magic_projectile"),
			FabricEntityTypeBuilder.<MagicProjectileEntity>create(SpawnGroup.MISC, MagicProjectileEntity::new)
				.dimensions(EntityDimensions.fixed(0.5f, 0.5f))
				.build());
	}
}