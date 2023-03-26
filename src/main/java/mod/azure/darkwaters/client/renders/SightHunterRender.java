package mod.azure.darkwaters.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.darkwaters.client.models.SightHunterModel;
import mod.azure.darkwaters.entity.SightHunterEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SightHunterRender extends GeoEntityRenderer<SightHunterEntity> {

	public SightHunterRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new SightHunterModel());
	}

	@Override
	public void actuallyRender(PoseStack poseStack, SightHunterEntity animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, 0.7F);
	}

	@Override
	protected float getDeathMaxRotation(SightHunterEntity entityLivingBaseIn) {
		return 0.0F;
	}
}