package net.kaupenjoe.mccourse.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBoats {
	public static Identifier DRIFTWOOD_BOAT_ID =
		new ModIdentifier("driftwood_boat");
	public static Identifier DRIFTWOOD_CHEST_BOAT_ID =
		new ModIdentifier("driftwood_chest_boat");
	public static RegistryKey<TerraformBoatType> DRIFTWOOD_BOAT_KEY =
		TerraformBoatTypeRegistry.createKey(DRIFTWOOD_BOAT_ID);

	public static void registerBoats() {
		TerraformBoatType driftwoodBoat = new TerraformBoatType.Builder()
			.item(ModItems.DRIFTWOOD_BOAT)
			.chestItem(ModItems.DRIFTWOOD_CHEST_BOAT)
			.planks(ModBlocks.DRIFTWOOD_PLANKS.asItem())
			.build();
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, DRIFTWOOD_BOAT_KEY, driftwoodBoat);
	}
}
