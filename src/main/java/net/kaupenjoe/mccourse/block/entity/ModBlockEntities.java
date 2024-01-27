package net.kaupenjoe.mccourse.block.entity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import team.reborn.energy.api.EnergyStorage;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;

public class ModBlockEntities {
	public static BlockEntityType<GemEmpoweringStationBlockEntity> GEM_EMPOWERING_STATION_BE;

    public static void registerBlockEntities() {
        MCCourseMod.LOGGER.info("Registering Block Entities for " + MCCourseMod.MOD_ID);
		GEM_EMPOWERING_STATION_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new ModIdentifier("gem_empowering_block_entity"),
			FabricBlockEntityTypeBuilder.create(
					GemEmpoweringStationBlockEntity::new,
					ModBlocks.GEM_EMPOWERING_STATION)
				.build(null));
		EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) ->
			blockEntity.energyStorage, GEM_EMPOWERING_STATION_BE);
		FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) ->
			blockEntity.fluidStorage, GEM_EMPOWERING_STATION_BE);
	}
}
