package net.kaupenjoe.mccourse.particle;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

public class ModParticles {
	public static DefaultParticleType PINK_GARNET_PARTICLE;

	@SuppressWarnings("SameParameterValue")
	private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
		return Registry.register(Registries.PARTICLE_TYPE, new ModIdentifier(name), particleType);
	}

	public static void registerParticles() {
		MCCourseMod.LOGGER.info("Registering Particles for " + MCCourseMod.MOD_ID);
		PINK_GARNET_PARTICLE =
			registerParticle("pink_garnet_particle", FabricParticleTypes.simple());
	}
}
