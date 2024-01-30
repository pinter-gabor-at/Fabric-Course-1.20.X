package net.kaupenjoe.mccourse.util;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;

public class ModWoodTypes {
	public static final WoodType DRIFTWOOD = WoodTypeBuilder
		.copyOf(WoodType.OAK)
		.register(new ModIdentifier("driftwood"), BlockSetType.OAK);
}
