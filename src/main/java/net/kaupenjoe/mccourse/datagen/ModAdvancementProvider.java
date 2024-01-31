package net.kaupenjoe.mccourse.datagen;

import java.util.Optional;
import java.util.function.Consumer;

import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;

public class ModAdvancementProvider extends FabricAdvancementProvider {
	public ModAdvancementProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
		var rootAdvancement = Advancement.Builder.create()
			.display(new AdvancementDisplay(new ItemStack(ModItems.PINK_GARNET),
				Text.literal("MC Course"),
				Text.literal("The Power lies in the Pink Garnet!"),
				Optional.of(new ModIdentifier("textures/block/pink_garnet_ore.png")),
				AdvancementFrame.TASK,
				true, true, false))
			.criterion("has_pink_garnet",
				InventoryChangedCriterion.Conditions.items(ModItems.PINK_GARNET))
			.build(consumer, new ModIdentifier("mccourse").toString());
		Advancement.Builder.create()
			.display(new AdvancementDisplay(new ItemStack(ModItems.METAL_DETECTOR),
				Text.literal("Metal Detector"),
				Text.literal("Batteries not included! (Actually doesn't need batteries)"),
				Optional.of(new ModIdentifier("block/pink_garnet_ore")),
				AdvancementFrame.TASK,
				true, true, false))
			.criterion("has_used_metal_detector",
				InventoryChangedCriterion.Conditions.items(ModItems.METAL_DETECTOR))
			.parent(rootAdvancement)
			.build(consumer, new ModIdentifier("metal_detector").toString());
	}
}
