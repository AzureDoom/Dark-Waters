package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MiraidEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class MiraidModel extends GeoModel<MiraidEntity> {

	@Override
	public ResourceLocation getAnimationResource(MiraidEntity animatable) {
		return new ResourceLocation(DarkWatersMod.MODID, "animations/miraid.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MiraidEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "geo/miraid.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MiraidEntity object) {
		return new ResourceLocation(DarkWatersMod.MODID, "textures/entity/miraid.png");
	}

	@Override
	public RenderType getRenderType(MiraidEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
