package mod.azure.darkwaters.client.models;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.MiraidHallucinationEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class MiraidHallucinationModel extends GeoModel<MiraidHallucinationEntity> {

	@Override
	public ResourceLocation getAnimationResource(MiraidHallucinationEntity animatable) {
		return DarkWatersMod.modResource("animations/miraid_hallucination.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MiraidHallucinationEntity object) {
		return DarkWatersMod.modResource("geo/miraid_hallucination.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MiraidHallucinationEntity object) {
		return DarkWatersMod.modResource("textures/entity/" + (object.getTextureState() == true ? "miraid_hallucination_angry" : "miraid_hallucination") + ".png");
	}

	@Override
	public RenderType getRenderType(MiraidHallucinationEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}
