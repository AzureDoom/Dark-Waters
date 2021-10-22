package mod.azure.darkwaters.client.renders;

import mod.azure.darkwaters.client.models.MohastModel;
import mod.azure.darkwaters.entity.MohastEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MohastRender extends GeoEntityRenderer<MohastEntity> {

	public MohastRender(EntityRendererFactory.Context renderManagerIn) {
		super(renderManagerIn, new MohastModel());
	}

	@Override
	public RenderLayer getRenderType(MohastEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}
}