package mod.azure.darkwaters.client.renders;

import mod.azure.darkwaters.client.models.SightHunterModel;
import mod.azure.darkwaters.entity.SightHunterEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SightHunterRender extends GeoEntityRenderer<SightHunterEntity> {

	public SightHunterRender(EntityRendererFactory.Context renderManagerIn) {
		super(renderManagerIn, new SightHunterModel());
	}

	@Override
	public RenderLayer getRenderType(SightHunterEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
	}

	@Override
	public void render(GeoModel model, SightHunterEntity animatable, float partialTicks, RenderLayer type,
			MatrixStack matrixStackIn, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
			int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder,
				packedLightIn, packedOverlayIn, red, green, blue, 0.7F);
	}
}