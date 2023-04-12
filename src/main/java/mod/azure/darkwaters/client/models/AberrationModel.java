package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.AberrationEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class AberrationModel extends GeoModel<AberrationEntity> {

	@Override
	public ResourceLocation getAnimationResource(AberrationEntity animatable) {
		return new ResourceLocation("azurelib", "animations/aberration.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AberrationEntity object) {
		return DarkWatersMod.modResource("geo/aberration.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AberrationEntity object) {
		return DarkWatersMod.modResource("textures/entity/aberration.png");
	}

	@Override
	public RenderType getRenderType(AberrationEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
