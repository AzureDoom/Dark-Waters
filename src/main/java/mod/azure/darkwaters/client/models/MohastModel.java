package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MohastEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class MohastModel extends GeoModel<MohastEntity> {

	@Override
	public ResourceLocation getAnimationResource(MohastEntity animatable) {
		return DarkWatersMod.modResource("animations/mohast.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MohastEntity object) {
		return DarkWatersMod.modResource("geo/mohast.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MohastEntity object) {
		return DarkWatersMod.modResource("textures/entity/mohast.png");
	}

	@Override
	public RenderType getRenderType(MohastEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
