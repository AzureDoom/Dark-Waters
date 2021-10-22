package mod.azure.darkwaters.client.renders;

import mod.azure.darkwaters.client.models.CraekenModel;
import mod.azure.darkwaters.entity.CraekenEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CraekenRender extends GeoEntityRenderer<CraekenEntity> {

	public CraekenRender(EntityRendererFactory.Context renderManagerIn) {
		super(renderManagerIn, new CraekenModel());
	}

	@Override
	public RenderLayer getRenderType(CraekenEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}
}