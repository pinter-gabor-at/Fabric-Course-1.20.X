package net.kaupenjoe.mccourse.util;

import net.kaupenjoe.mccourse.item.ModItems;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelPredicateProvider {
	public static void registerModModels() {
		ModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, new ModIdentifier("on"),
			(stack, world, entity, seed) -> stack.hasNbt() ? 1f : 0f);
		registerBow(ModItems.PINK_GARNET_BOW);
	}

	private static void registerBow(Item bow) {
		ModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
			(stack, world, entity, seed) ->
				entity != null && entity.getActiveItem() == stack ?
					(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20f : 0f);
		ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
			(stack, world, entity, seed) ->
				entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ?
					1f : 0f);
	}
}
