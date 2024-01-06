package net.kaupenjoe.mccourse;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.particle.ModParticles;
import net.kaupenjoe.mccourse.particle.PinkGarnetParticle;
import net.kaupenjoe.mccourse.util.ModModelPredicateProvider;

import net.minecraft.client.render.RenderLayer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

public class MCCourseModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAULIFLOWER_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PETUNIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PETUNIA, RenderLayer.getCutout());
        ModModelPredicateProvider.registerModModels();
        ParticleFactoryRegistry.getInstance()
			.register(ModParticles.PINK_GARNET_PARTICLE, PinkGarnetParticle.Factory::new);
    }
}
