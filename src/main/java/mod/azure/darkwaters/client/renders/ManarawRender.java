package mod.azure.darkwaters.client.renders;

import mod.azure.darkwaters.client.models.ManarawModel;
import mod.azure.darkwaters.entity.ManarawEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ManarawRender extends GeoEntityRenderer<ManarawEntity> {

	public ManarawRender(EntityRendererFactory.Context renderManagerIn) {
		super(renderManagerIn, new ManarawModel());
	}

	@Override
	public RenderLayer getRenderType(ManarawEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
	}
}