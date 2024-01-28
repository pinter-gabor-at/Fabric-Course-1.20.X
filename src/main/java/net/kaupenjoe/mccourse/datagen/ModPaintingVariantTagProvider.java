package net.kaupenjoe.mccourse.datagen;

import java.util.concurrent.CompletableFuture;

import net.kaupenjoe.mccourse.painting.ModPaintings;

import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.PaintingVariantTags;

public class ModPaintingVariantTagProvider extends TagProvider<PaintingVariant> {
	public ModPaintingVariantTagProvider(DataOutput output,
		CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
		super(output, RegistryKeys.PAINTING_VARIANT, registryLookupFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup lookup) {
		getOrCreateTagBuilder(PaintingVariantTags.PLACEABLE)
			.addOptional(ModPaintings.SAW_THEM.getId())
			.addOptional(ModPaintings.SHRIMP.getId())
			.addOptional(ModPaintings.WORLD.getId());
	}
}
