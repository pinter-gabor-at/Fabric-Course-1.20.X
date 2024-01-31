package net.kaupenjoe.mccourse.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;

@Mixin(ItemRenderer.class)
public interface ItemRendererAccessor {
	@Accessor
	ItemModels getModels();
}
