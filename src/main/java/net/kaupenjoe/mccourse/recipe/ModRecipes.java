package net.kaupenjoe.mccourse.recipe;

import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {
	public static void registerRecipes() {
		Registry.register(Registries.RECIPE_SERIALIZER,
			new ModIdentifier(GemEmpoweringRecipe.Serializer.ID),
			GemEmpoweringRecipe.Serializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE,
			new ModIdentifier(GemEmpoweringRecipe.Type.ID),
			GemEmpoweringRecipe.Type.INSTANCE);
	}
}
