package net.kaupenjoe.mccourse.world.biome;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;
import terrablender.api.Region;
import terrablender.api.RegionType;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public class ModOverworldRegion extends Region {
	public ModOverworldRegion(Identifier name, int weight) {
		super(name, RegionType.OVERWORLD, weight);
	}

	@Override
	public void addBiomes(Registry<Biome> registry,
		Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
		this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
			modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.FOREST, ModBiomes.TEST_BIOME);
			modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.PLAINS, ModBiomes.TEST_BIOME_2);
		});
	}
}
