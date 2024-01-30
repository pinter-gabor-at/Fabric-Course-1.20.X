package net.kaupenjoe.mccourse.datagen;

import java.util.concurrent.CompletableFuture;

import net.kaupenjoe.mccourse.fluid.ModFluids;

import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.FluidTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class ModFluidTagProvider extends FabricTagProvider.FluidTagProvider {
	public ModFluidTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		this.getOrCreateTagBuilder(FluidTags.WATER)
			.add(ModFluids.FLOWING_SOAP_WATER)
			.add(ModFluids.STILL_SOAP_WATER);
	}
}
