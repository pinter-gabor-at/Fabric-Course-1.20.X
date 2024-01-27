package net.kaupenjoe.mccourse.mixin;

import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
	@ModifyVariable(method =
		"renderItem(Lnet/minecraft/item/ItemStack;" +
			"Lnet/minecraft/client/render/model/json/ModelTransformationMode;" +
			"Z" +
			"Lnet/minecraft/client/util/math/MatrixStack;" +
			"Lnet/minecraft/client/render/VertexConsumerProvider;" +
			"I" +
			"I" +
			"Lnet/minecraft/client/render/model/BakedModel;)V",
		at = @At(value = "HEAD"), argsOnly = true)
	private BakedModel useRadiationStaffModel(BakedModel value,
		ItemStack stack,
		ModelTransformationMode renderMode,
		boolean leftHanded,
		MatrixStack matrices,
		VertexConsumerProvider vertexConsumers,
		int light,
		int overlay) {
		if (stack.isOf(ModItems.RADIATION_STAFF) && renderMode != ModelTransformationMode.GUI) {
			return ((ItemRendererAccessor) this).getModels().getModelManager()
				.getModel(new ModelIdentifier(
					new ModIdentifier("radiation_staff_3d"), "inventory"));
		}
		return value;
	}
}
