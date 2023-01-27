package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MohastEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class MohastModel extends GeoModel<MohastEntity> {

	@Override
	public ResourceLocation getAnimationResource(MohastEntity animatable) {
		return new ResourceLocation(DarkWatersMod.MODID, "animations/mohast.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MohastEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "geo/mohast.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MohastEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "textures/entity/mohast.png");
	}

	@Override
	public RenderType getRenderType(MohastEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
