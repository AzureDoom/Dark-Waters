package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.SightHunterEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class SightHunterModel extends GeoModel<SightHunterEntity> {

	@Override
	public ResourceLocation getAnimationResource(SightHunterEntity animatable) {
		return new ResourceLocation(DarkWatersMod.MODID, "animations/sight_hunter.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SightHunterEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "geo/sight_hunter.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SightHunterEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "textures/entity/sight_hunter.png");
	}

	@Override
	public RenderType getRenderType(SightHunterEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
