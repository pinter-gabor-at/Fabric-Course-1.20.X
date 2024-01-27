package net.kaupenjoe.mccourse.fluid;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

public class ModFluids {
	public static FlowableFluid STILL_SOAP_WATER;
	public static FlowableFluid FLOWING_SOAP_WATER;
	public static Block SOAP_WATER_BLOCK;
	public static Item SOAP_WATER_BUCKET;

	public static void registerFluids() {
		MCCourseMod.LOGGER.info("Registering Fluid for " + MCCourseMod.MOD_ID);
		STILL_SOAP_WATER = Registry.register(Registries.FLUID,
			new ModIdentifier("soap_water"),
			new SoapWaterFluid.Still());
		FLOWING_SOAP_WATER = Registry.register(Registries.FLUID,
			new ModIdentifier("flowing_soap_water"),
			new SoapWaterFluid.Flowing());
		SOAP_WATER_BLOCK = Registry.register(Registries.BLOCK,
			new ModIdentifier("soap_water_block"),
			new FluidBlock(STILL_SOAP_WATER,
				FabricBlockSettings.copyOf(Blocks.WATER)
					.replaceable().liquid()));
		SOAP_WATER_BUCKET = Registry.register(Registries.ITEM,
			new ModIdentifier("soap_water_bucket"),
			new BucketItem(STILL_SOAP_WATER,
				new FabricItemSettings()
					.recipeRemainder(Items.BUCKET).maxCount(1)));
	}
}
