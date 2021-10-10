package mod.azure.darkwaters.client.models;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.AberrationEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AberrationModel extends AnimatedGeoModel<AberrationEntity> {

	@Override
	public Identifier getAnimationFileLocation(AberrationEntity animatable) {
		return new Identifier(DarkWatersMod.MODID, "animations/aberration.animation.json");
	}

	@Override
	public Identifier getModelLocation(AberrationEntity object) {
		return new Identifier(DarkWatersMod.MODID, "geo/aberration.geo.json");
	}

	@Override
	public Identifier getTextureLocation(AberrationEntity object) {
		return new Identifier(DarkWatersMod.MODID, "textures/entity/abberation_top.png");
	}

}
