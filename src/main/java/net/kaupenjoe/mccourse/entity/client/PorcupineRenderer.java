package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.entity.custom.PorcupineEntity;
import net.kaupenjoe.mccourse.entity.layer.ModModelLayers;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PorcupineRenderer extends MobEntityRenderer<PorcupineEntity, PorcupineModel<PorcupineEntity>> {
	private static final Identifier TEXTURE = new ModIdentifier("textures/entity/porcupine.png");

	public PorcupineRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new PorcupineModel<>(ctx.getPart(ModModelLayers.PORCUPINE)), 0.6f);
	}

	@Override
	public Identifier getTexture(PorcupineEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(PorcupineEntity livingEntity, float f, float g, MatrixStack matrixStack,
		VertexConsumerProvider vertexConsumerProvider, int i) {
		if (livingEntity.isBaby()) {
			matrixStack.scale(0.5f, 0.5f, 0.5f);
		}
		super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}
}
