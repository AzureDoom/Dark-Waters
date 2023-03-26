package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.CraekenEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class CraekenModel extends GeoModel<CraekenEntity> {

	@Override
	public ResourceLocation getAnimationResource(CraekenEntity animatable) {
		return DarkWatersMod.modResource("animations/craeken.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CraekenEntity object) {
		return DarkWatersMod.modResource("geo/craeken.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CraekenEntity object) {
		return DarkWatersMod.modResource("textures/entity/craeken.png");
	}

	@Override
	public RenderType getRenderType(CraekenEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
