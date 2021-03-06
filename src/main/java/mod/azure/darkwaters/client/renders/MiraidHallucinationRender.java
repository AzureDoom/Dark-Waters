package mod.azure.darkwaters.client.renders;

import mod.azure.darkwaters.client.models.MiraidHallucinationModel;
import mod.azure.darkwaters.entity.MiraidHallucinationEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MiraidHallucinationRender extends GeoEntityRenderer<MiraidHallucinationEntity> {

	public MiraidHallucinationRender(EntityRendererFactory.Context renderManagerIn) {
		super(renderManagerIn, new MiraidHallucinationModel());
	}

	@Override
	public RenderLayer getRenderType(MiraidHallucinationEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void render(GeoModel model, MiraidHallucinationEntity animatable, float partialTicks, RenderLayer type,
			MatrixStack matrixStackIn, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
			int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder,
				packedLightIn, packedOverlayIn, red, green, blue, 0.7F);
	}
}