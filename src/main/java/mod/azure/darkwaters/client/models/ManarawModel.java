package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.ManarawEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class ManarawModel extends GeoModel<ManarawEntity> {

	@Override
	public ResourceLocation getAnimationResource(ManarawEntity animatable) {
		return DarkWatersMod.modResource("animations/manaraw.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ManarawEntity object) {
		return DarkWatersMod.modResource("geo/manaraw.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ManarawEntity object) {
		return DarkWatersMod.modResource("textures/entity/manaraw.png");
	}

	@Override
	public RenderType getRenderType(ManarawEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
