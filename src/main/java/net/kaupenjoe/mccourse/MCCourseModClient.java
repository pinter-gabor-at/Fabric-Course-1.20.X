package net.kaupenjoe.mccourse;

import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.block.entity.renderer.GemEmpoweringBlockEntityRenderer;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.networking.ModMessages;
import net.kaupenjoe.mccourse.particle.ModParticles;
import net.kaupenjoe.mccourse.particle.PinkGarnetParticle;
import net.kaupenjoe.mccourse.screen.GemEmpoweringScreen;
import net.kaupenjoe.mccourse.screen.ModScreenHandlers;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import net.kaupenjoe.mccourse.util.ModModelPredicateProvider;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.ModelIdentifier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;

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
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SOAP_WATER, ModFluids.FLOWING_SOAP_WATER,
                SimpleFluidRenderHandler.coloredWater(0xA1E038D0));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_SOAP_WATER, ModFluids.FLOWING_SOAP_WATER);
		ModelLoadingPlugin.register(pluginContext ->
			new ModelIdentifier(new ModIdentifier("radiation_staff_3d"), "inventory"));
        HandledScreens.register(ModScreenHandlers.GEM_EMPOWERING_SCREEN_HANDLER, GemEmpoweringScreen::new);

        ModMessages.registerS2CPackets();

        BlockEntityRendererFactories.register(ModBlockEntities.GEM_EMPOWERING_STATION_BE,
			GemEmpoweringBlockEntityRenderer::new);
    }
}
