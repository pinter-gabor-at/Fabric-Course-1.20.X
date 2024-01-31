package net.kaupenjoe.mccourse.entity.layer;

import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.client.render.entity.model.EntityModelLayer;

public class ModModelLayers {
	public static final EntityModelLayer PORCUPINE =
		new EntityModelLayer(new ModIdentifier("porcupine"), "main");
	public static final EntityModelLayer MAGIC_PROJECTILE =
		new EntityModelLayer(new ModIdentifier("magic_projectile"), "main");
}
