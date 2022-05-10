package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.CraekenEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class CraekenModel extends AnimatedTickingGeoModel<CraekenEntity> {

	@Override
	public Identifier getAnimationResource(CraekenEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/craeken.animation.json");
	}

	@Override
	public Identifier getModelResource(CraekenEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/craeken.geo.json");
	}

	@Override
	public Identifier getTextureResource(CraekenEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/craeken.png");
	}

}
