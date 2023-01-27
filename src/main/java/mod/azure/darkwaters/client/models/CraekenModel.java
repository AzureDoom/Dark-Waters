package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.CraekenEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class CraekenModel extends GeoModel<CraekenEntity> {

	@Override
	public ResourceLocation getAnimationResource(CraekenEntity animatable) {
		return new ResourceLocation(DarkWatersMod.MODID, "animations/craeken.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CraekenEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "geo/craeken.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CraekenEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "textures/entity/craeken.png");
	}

	@Override
	public RenderType getRenderType(CraekenEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
